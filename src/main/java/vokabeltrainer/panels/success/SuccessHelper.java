package vokabeltrainer.panels.success;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.SaveTraining;
import vokabeltrainer.panels.success.table.SuccessTable;
import vokabeltrainer.panels.success.table.SuccessTableModel;
import vokabeltrainer.panels.success.table.SuccessTableRow;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Repetition;
import vokabeltrainer.types.TrainingStatus;

public class SuccessHelper
{

   private SuccessHelper()
   {
      // nothing
   }
   
   static JPanel makePanel(JPanel panel)
   {
      JPanel wrapper = new JPanel();
      BullsEyeLayout wrapperLayout = new BullsEyeLayout(wrapper);
      wrapper.setLayout(wrapperLayout);
      wrapper.setBackground(Settings.getDarkGold());
      wrapper.setOpaque(true);
      TotemLayout totemLayout = new TotemLayout(panel);
      panel.setLayout(totemLayout);
      panel.setBackground(Settings.getDarkGold());
      panel.setOpaque(true);
      wrapper.add(panel);
      return wrapper;
   }

   static void addContent(Repetition repetition, JPanel panel,
         Language direction)
   {
      panel.removeAll();
      SuccessTableModel model = Data.findSuccessModel(direction, repetition);
      SuccessTable table = new SuccessTable(model);
      
      JScrollPane scroller = new JScrollPane(table);
      scroller.setMinimumSize(new Dimension(1017,468));
      scroller.setMaximumSize(new Dimension(1017,468));
      scroller.setBackground(Settings.getDarkGold());
      scroller.setOpaque(true);
      scroller.getViewport().setBackground(Settings.getLightBlue());
      scroller.getViewport().setOpaque(true);
      
      JPanel tablePanel = new JPanel();
      BullsEyeLayout tableLayout = new BullsEyeLayout(tablePanel);
      tablePanel.setLayout(tableLayout);
      tablePanel.setBackground(Settings.getDarkGold());
      tablePanel.add(scroller);
      
      JPanel fillerPanel = new JPanel(new FlowLayout());
      fillerPanel.setBackground(Settings.getDarkGold());
      fillerPanel.setOpaque(true);
      fillerPanel.setMinimumSize(new Dimension(1017,15));
      fillerPanel.setMaximumSize(new Dimension(1017,15));
      
      JPanel buttonWrapperPanel = new JPanel();
      BullsEyeLayout buttonWrapperLayout = new BullsEyeLayout(buttonWrapperPanel);
      buttonWrapperPanel.setLayout(buttonWrapperLayout);
      buttonWrapperPanel.setBackground(Settings.getDarkGold());
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setBackground(Settings.getDarkGold());
      buttonPanel.setOpaque(true);
      buttonPanel.setLayout(new TrainLayout(buttonPanel, 15));
      buttonPanel.setMinimumSize(new Dimension(935,40));
      buttonPanel.setMaximumSize(new Dimension(935,40));
      
      JButton selectAllButton = new JButton("alle auswählen");
      selectAllButton.setIcon(new ImageIcon(ApplicationImages.getSelect()));
      selectAllButton.setFont(Settings.getButtonFont());
      selectAllButton.addActionListener(event -> {

         for (Vector<SuccessTableRow> row : model.getData())
         {
            row.get(0).getGrammaticalEnum().setSelected(true);
         }
         model.fireTableDataChanged();

      });
      JButton unselectAllButton = new JButton("alle nicht auswählen");
      unselectAllButton.setIcon(new ImageIcon(ApplicationImages.getClear()));
      unselectAllButton.setFont(Settings.getButtonFont());
      unselectAllButton.addActionListener(event -> {

         for (Vector<SuccessTableRow> row : model.getData())
         {
            row.get(0).getGrammaticalEnum().setSelected(false);
         }
         model.fireTableDataChanged();

      });
      JButton moveButton = new JButton(
            "ausgewählte Wörter zu \"Ungelernt\" verschieben");
      moveButton.setIcon(new ImageIcon(ApplicationImages.getBack()));
      moveButton.setFont(Settings.getButtonFont());
      moveButton.addActionListener(event -> {
         List<Vector<SuccessTableRow>> rows = new ArrayList<>();
         for (Vector<SuccessTableRow> row : model.getData())
         {
            Expression expression = row.get(0).getGrammaticalEnum();
            if (expression.isSelected() && Language.GERMAN == direction)
            {
               expression.setTrainingStatusDToH(new TrainingStatus());
               expression.setSelected(false);
               rows.add(row);
            }
            else if (expression.isSelected() && Language.HEBREW == direction)
            {
               expression.setTrainingStatusHToD(new TrainingStatus());
               expression.setSelected(false);
               rows.add(row);
            }
         }
         for (Vector<SuccessTableRow> row : rows)
         {
            model.getData().remove(row);
         }
         SaveTraining saver = new SaveTraining();
         saver.save();
         model.fireTableDataChanged();

      });
      buttonPanel.add(selectAllButton);
      buttonPanel.add(unselectAllButton);
      buttonPanel.add(moveButton);
      
      buttonWrapperPanel.add(buttonPanel);
      
      panel.add(tablePanel);
      panel.add(fillerPanel);
      panel.add(buttonWrapperPanel);
   }
}
