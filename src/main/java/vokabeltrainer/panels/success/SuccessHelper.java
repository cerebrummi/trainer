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

import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.colors.SuccessColors;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Data;
import vokabeltrainer.common.main.SaveTraining;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.success.table.SuccessTable;
import vokabeltrainer.panels.success.table.SuccessTableModel;
import vokabeltrainer.panels.success.table.SuccessTableRow;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;
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
      wrapper.setBackground(SuccessColors.getPanelBackgroundLight());
      wrapper.setOpaque(true);
      TotemLayout totemLayout = new TotemLayout(panel);
      panel.setLayout(totemLayout);
      panel.setBackground(SuccessColors.getPanelBackgroundLight());
      panel.setOpaque(true);
      wrapper.add(panel);
      return wrapper;
   }

   static void addContent(Common common, View view, Repetition repetition, JPanel panel,
         Direction direction)
   {
      Translator translator = common.getTranslator();
      panel.removeAll();
      panel.setOpaque(true);
      panel.setBackground(SuccessColors.getPanelBackgroundLight());

      SuccessTableModel model = Data.findSuccessModel(direction, repetition);
      SuccessTable table = new SuccessTable(common, model);

      JScrollPane scroller = new JScrollPane(table);
      scroller.setMinimumSize(new Dimension(1200, 600));
      scroller.setMaximumSize(new Dimension(1500, 800));
      scroller.setBackground(SuccessColors.getPanelBackground());
      scroller.setOpaque(true);
      scroller.getViewport().setBackground(SuccessColors.getTableBackground());
      scroller.getViewport().setOpaque(true);

      JPanel tablePanel = new JPanel();
      BullsEyeLayout tableLayout = new BullsEyeLayout(tablePanel);
      tablePanel.setLayout(tableLayout);
      tablePanel.setOpaque(true);
      tablePanel.setBackground(SuccessColors.getPanelBackgroundLight());
      tablePanel.add(scroller);

      panel.add(tablePanel);

      if (repetition != null)
      {
         JPanel fillerPanel = new JPanel(new FlowLayout());
         fillerPanel.setBackground(SuccessColors.getPanelBackgroundLight());
         fillerPanel.setOpaque(true);
         fillerPanel.setMinimumSize(new Dimension(1200, 15));
         fillerPanel.setMaximumSize(new Dimension(1500, 15));

         JPanel buttonWrapperPanel = new JPanel();
         BullsEyeLayout buttonWrapperLayout = new BullsEyeLayout(
               buttonWrapperPanel);
         buttonWrapperPanel.setLayout(buttonWrapperLayout);
         buttonWrapperPanel
               .setBackground(SuccessColors.getPanelBackgroundLight());

         JPanel buttonPanel = new JPanel();
         buttonPanel.setBackground(SuccessColors.getPanelBackgroundLight());
         buttonPanel.setOpaque(true);
         buttonPanel.setLayout(new TrainLayout(buttonPanel, 15));
         buttonPanel.setMinimumSize(new Dimension(1200, 40));
         buttonPanel.setMaximumSize(new Dimension(1500, 40));

         JButton selectAllButton = new JButton(
               translator.realisticTranslate(Translation.ALLE_AUSWAEHLEN));
         selectAllButton.setIcon(new ImageIcon(ApplicationImages.getSelect()));
         selectAllButton.setFont(ApplicationFonts.buttonFont);
         selectAllButton.setForeground(SuccessColors.getTextForeground());
         selectAllButton.addActionListener(_ -> {

            for (Vector<SuccessTableRow> row : model.getData())
            {
               row.get(0).getExpression().setSelected(true);
            }
            model.fireTableDataChanged();

         });
         JButton unselectAllButton = new JButton(translator
               .realisticTranslate(Translation.ALLE_NICHT_AUSWAEHLEN));
         unselectAllButton.setIcon(new ImageIcon(ApplicationImages.getClear()));
         unselectAllButton.setFont(ApplicationFonts.buttonFont);
         unselectAllButton.setForeground(SuccessColors.getTextForeground());
         unselectAllButton.addActionListener(_ -> {

            for (Vector<SuccessTableRow> row : model.getData())
            {
               row.get(0).getExpression().setSelected(false);
            }
            model.fireTableDataChanged();

         });
         JButton moveButton = new JButton(translator
               .realisticTranslate(Translation.AUSGEWAEHLTE_WOERTER_ZU) + " \""
               + translator.realisticTranslate(Translation.VORRAT) + "\" "
               + translator.realisticTranslate(Translation.VERSCHIEBEN));
         moveButton.setIcon(new ImageIcon(ApplicationImages.getBack()));
         moveButton.setFont(ApplicationFonts.buttonFont);
         moveButton.setForeground(SuccessColors.getTextForeground());
         moveButton.addActionListener(_ -> {
            List<Vector<SuccessTableRow>> rows = new ArrayList<>();
            for (Vector<SuccessTableRow> row : model.getData())
            {
               Expression expression = row.get(0).getExpression();
               if (expression.isSelected() && Direction.OWN_TO_NEW == direction)
               {
                  expression.setTrainingStatusDToLL(new TrainingStatus());
                  expression.setSelected(false);
                  rows.add(row);
               }
               else if (expression.isSelected()
                     && Direction.NEW_TO_OWN == direction)
               {
                  expression.setTrainingStatusLLToD(new TrainingStatus());
                  expression.setSelected(false);
                  rows.add(row);
               }
            }
            for (Vector<SuccessTableRow> row : rows)
            {
               model.getData().remove(row);
            }
            SaveTraining saver = new SaveTraining();
            saver.save(view);
            model.fireTableDataChanged();

         });
         buttonPanel.add(selectAllButton);
         buttonPanel.add(unselectAllButton);
         buttonPanel.add(moveButton);

         buttonWrapperPanel.add(buttonPanel);

         panel.add(fillerPanel);
         panel.add(buttonWrapperPanel);
      }
   }
}
