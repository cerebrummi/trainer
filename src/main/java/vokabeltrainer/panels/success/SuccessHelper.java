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

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
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

   static JPanel makePanel(App app, JPanel panel)
   {
      JPanel wrapper = new JPanel();
      BullsEyeLayout wrapperLayout = new BullsEyeLayout(wrapper);
      wrapper.setLayout(wrapperLayout);
      wrapper.setBackground(app.appColors.success.getPanelBackgroundLight());
      wrapper.setOpaque(true);
      TotemLayout totemLayout = new TotemLayout(panel);
      panel.setLayout(totemLayout);
      panel.setBackground(app.appColors.success.getPanelBackgroundLight());
      panel.setOpaque(true);
      wrapper.add(panel);
      return wrapper;
   }

   static void addContent(App app, Common common, Model model, View view, Repetition repetition, JPanel panel,
         Direction direction)
   {
      Translator translator = common.getTranslator();
      panel.removeAll();
      panel.setOpaque(true);
      panel.setBackground(app.appColors.success.getPanelBackgroundLight());

      SuccessTableModel tableModel = model.data.findSuccessModel(direction, repetition);
      SuccessTable table = new SuccessTable(app, common, tableModel);

      JScrollPane scroller = new JScrollPane(table);
      scroller.setMinimumSize(new Dimension(1200, 600));
      scroller.setMaximumSize(new Dimension(1500, 800));
      scroller.setBackground(app.appColors.success.getPanelBackground());
      scroller.setOpaque(true);
      scroller.getViewport().setBackground(app.appColors.success.getTableBackground());
      scroller.getViewport().setOpaque(true);

      JPanel tablePanel = new JPanel();
      BullsEyeLayout tableLayout = new BullsEyeLayout(tablePanel);
      tablePanel.setLayout(tableLayout);
      tablePanel.setOpaque(true);
      tablePanel.setBackground(app.appColors.success.getPanelBackgroundLight());
      tablePanel.add(scroller);

      panel.add(tablePanel);

      if (repetition != null)
      {
         JPanel fillerPanel = new JPanel(new FlowLayout());
         fillerPanel.setBackground(app.appColors.success.getPanelBackgroundLight());
         fillerPanel.setOpaque(true);
         fillerPanel.setMinimumSize(new Dimension(1200, 15));
         fillerPanel.setMaximumSize(new Dimension(1500, 15));

         JPanel buttonWrapperPanel = new JPanel();
         BullsEyeLayout buttonWrapperLayout = new BullsEyeLayout(
               buttonWrapperPanel);
         buttonWrapperPanel.setLayout(buttonWrapperLayout);
         buttonWrapperPanel
               .setBackground(app.appColors.success.getPanelBackgroundLight());

         JPanel buttonPanel = new JPanel();
         buttonPanel.setBackground(app.appColors.success.getPanelBackgroundLight());
         buttonPanel.setOpaque(true);
         buttonPanel.setLayout(new TrainLayout(buttonPanel, 15));
         buttonPanel.setMinimumSize(new Dimension(1200, 40));
         buttonPanel.setMaximumSize(new Dimension(1500, 40));

         JButton selectAllButton = new JButton(
               translator.realisticTranslate(app, Translation.ALLE_AUSWAEHLEN));
         selectAllButton.setIcon(new ImageIcon(app.appImages.getSelect()));
         selectAllButton.setFont(app.appFonts.buttonFont);
         selectAllButton.setForeground(app.appColors.success.getTableBackground());
         selectAllButton.addActionListener(_ -> {

            for (Vector<SuccessTableRow> row : tableModel.getData())
            {
               row.get(0).getExpression().setSelected(true);
            }
            tableModel.fireTableDataChanged();

         });
         JButton unselectAllButton = new JButton(translator
               .realisticTranslate(app, Translation.ALLE_NICHT_AUSWAEHLEN));
         unselectAllButton.setIcon(new ImageIcon(app.appImages.getClear()));
         unselectAllButton.setFont(app.appFonts.buttonFont);
         unselectAllButton.setForeground(app.appColors.success.getTableBackground());
         unselectAllButton.addActionListener(_ -> {

            for (Vector<SuccessTableRow> row : tableModel.getData())
            {
               row.get(0).getExpression().setSelected(false);
            }
            tableModel.fireTableDataChanged();

         });
         JButton moveButton = new JButton(translator
               .realisticTranslate(app, Translation.AUSGEWAEHLTE_WOERTER_ZU) + " \""
               + translator.realisticTranslate(app, Translation.VORRAT) + "\" "
               + translator.realisticTranslate(app, Translation.VERSCHIEBEN));
         moveButton.setIcon(new ImageIcon(app.appImages.getBack()));
         moveButton.setFont(app.appFonts.buttonFont);
         moveButton.setForeground(app.appColors.success.getTableBackground());
         moveButton.addActionListener(_ -> {
            List<Vector<SuccessTableRow>> rows = new ArrayList<>();
            for (Vector<SuccessTableRow> row : tableModel.getData())
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
               tableModel.getData().remove(row);
            }
            SaveTraining saver = new SaveTraining();
            saver.save(app, model, view);
            tableModel.fireTableDataChanged();

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
