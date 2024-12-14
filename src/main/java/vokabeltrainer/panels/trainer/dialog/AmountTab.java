package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTable;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTableModel;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.FieldOfTraining;

public class AmountTab extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -5609291190819549709L;

   private Translator translator = Common.getTranslator();

   public AmountTab(StartTrainingView dialog)
   {
      setLayout(new BorderLayout());

      JLabel question = new JLabel(translator.realisticTranslate(
            Translation.WIE_VIELE_NEUE_WOERTER_MOECHTEN_SIE_LERNEN_));
      question.setFont(ApplicationFonts.getButtonFont());
      question.setOpaque(false);
      question.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      add(question, BorderLayout.NORTH);

      JPanel center = new JPanel();
      center.setOpaque(false);
      center.setLayout(new BorderLayout());

      TrainingTableModel model = Data.findTrainingModel(
            dialog.getLanguageDirection(), dialog.getFieldOfTraining());
      TrainingTable table = new TrainingTable(model);

      JScrollPane scroller = new JScrollPane(table);
      center.add(scroller);

      add(center, BorderLayout.CENTER);

      JPanel buttonWrapper = new JPanel(new FlowLayout());

      JButton cancelButton = new JButton(
            translator.realisticTranslate(Translation.ABBRECHEN));
      cancelButton.setFont(ApplicationFonts.getButtonFont());
      cancelButton.setIcon(new ImageIcon(ApplicationImages.getCancel()));

      JButton nextButton = new JButton(
            translator.realisticTranslate(Translation.WEITER));
      nextButton.setFont(ApplicationFonts.getButtonFont());
      nextButton.setIcon(new ImageIcon(ApplicationImages.getArrow()));

      buttonWrapper.add(cancelButton);
      buttonWrapper.add(nextButton);
      add(buttonWrapper, BorderLayout.SOUTH);

      nextButton.addActionListener(event -> {
         dialog.setNewExpressions(table.findNewExpressions(
               dialog.getLanguageDirection(), dialog.getFieldOfTraining()));
         if (dialog
               .getFieldOfTraining() != FieldOfTraining.AREA_SELECTED_TEMPORARY)
         {
            dialog.setOldExpressions(table.findOldToBeRepeatedExpressions());
         }
         dialog.initTraining();
      });

      cancelButton.addActionListener(event -> {
         dialog.cancelTrainingStart();
      });
   }
}
