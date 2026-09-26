package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.Serial;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTable;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTableModel;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.FieldOfTraining;

public class AmountTab extends JPanel
{
   @Serial
   private static final long serialVersionUID = -5609291190819549709L;

   private Translator translator;

   public AmountTab(App app, Common common, Model model, StartTrainingView dialog)
   {
      translator = common.getTranslator();
      setLayout(new BorderLayout());
      setBackground(app.appColors.getBackground());
      setOpaque(true);

      JLabel question = new JLabel(translator.realisticTranslate(app, 
            Translation.WIE_VIELE_NEUE_WOERTER_MOECHTEN_SIE_LERNEN_));
      question.setForeground(app.appColors.trainer.getTextForeground());
      question.setFont(app.appFonts.buttonFont);
      question.setOpaque(false);
      question.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      add(question, BorderLayout.NORTH);

      JPanel center = new JPanel();
      center.setOpaque(false);
      center.setLayout(new BorderLayout());

      TrainingTableModel tableModel = model.data.findTrainingModel(app, common,
            dialog.getLanguageDirection(), dialog.getFieldOfTraining(),
            dialog.getDatabaseNames());
      TrainingTable table = new TrainingTable(app, common, tableModel);

      JScrollPane scroller = new JScrollPane(table);
      scroller.setForeground(app.appColors.trainer.getTextForeground());
      scroller.setBackground(app.appColors.trainer.getTextBackground());
      center.add(scroller);

      add(center, BorderLayout.CENTER);

      JPanel buttonWrapper = new JPanel(new FlowLayout());
      buttonWrapper.setOpaque(false);

      JButton cancelButton = new JButton(
            translator.realisticTranslate(app, Translation.ABBRECHEN));
      cancelButton.setFont(app.appFonts.buttonFont);
      cancelButton.setBackground(app.appColors.trainer.getButton());
      cancelButton.setForeground(app.appColors.trainer.getButtonForeground());
      cancelButton.setIcon(new ImageIcon(app.appImages.getCancel()));

      JButton nextButton = new JButton(
            translator.realisticTranslate(app, Translation.WEITER));
      nextButton.setFont(app.appFonts.buttonFont);
      nextButton.setBackground(app.appColors.trainer.getButton());
      nextButton.setForeground(app.appColors.trainer.getButtonForeground());
      nextButton.setIcon(new ImageIcon(app.appImages.getArrow()));

      buttonWrapper.add(cancelButton);
      buttonWrapper.add(nextButton);
      add(buttonWrapper, BorderLayout.SOUTH);

      nextButton.addActionListener(_ -> {
         dialog.setNewExpressions(table.findNewExpressions(
               dialog.getLanguageDirection(), dialog.getFieldOfTraining()));
         if (dialog
               .getFieldOfTraining() != FieldOfTraining.AREA_SELECTED_TEMPORARY)
         {
            dialog.setOldExpressions(table.findOldToBeRepeatedExpressions());
         }
         dialog.initTraining();
      });

      cancelButton.addActionListener(_ -> {
         dialog.cancelTrainingStart();
      });
   }
}
