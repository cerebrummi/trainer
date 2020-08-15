package vokabeltrainer.panels.success;

import java.awt.BorderLayout;
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

   static void addContent(Repetition repetition, JPanel panel,
         Language direction)
   {
      panel.removeAll();
      SuccessTableModel model = Data.findSuccessModel(direction, repetition);
      SuccessTable table = new SuccessTable(model);
      JPanel buttonPanel = new JPanel();
      buttonPanel.setBackground(Settings.getDarkGold());
      buttonPanel.setOpaque(true);
      buttonPanel.setLayout(new TrainLayout(buttonPanel, 15));
      JButton selectAllButton = new JButton("alle auswählen");
      selectAllButton.setIcon(new ImageIcon(ApplicationImages.getSelect()));
      selectAllButton.setFont(Settings.getButtonFont());
      selectAllButton.addActionListener(event -> {

         for (Vector<SuccessTableRow> row : model.getData())
         {
            row.get(0).getExpression().setSelected(true);
         }
         model.fireTableDataChanged();

      });
      JButton unselectAllButton = new JButton("alle nicht auswählen");
      unselectAllButton.setIcon(new ImageIcon(ApplicationImages.getClear()));
      unselectAllButton.setFont(Settings.getButtonFont());
      unselectAllButton.addActionListener(event -> {

         for (Vector<SuccessTableRow> row : model.getData())
         {
            row.get(0).getExpression().setSelected(false);
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
            Expression expression = row.get(0).getExpression();
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
      panel.add(new JScrollPane(table), BorderLayout.CENTER);
      panel.add(buttonPanel, BorderLayout.SOUTH);
   }
}
