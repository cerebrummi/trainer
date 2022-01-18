package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTable;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTableModel;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class AmountTab extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -5609291190819549709L;
   
   private Translator translator = Common.getTranslator();

   public AmountTab(StartTrainingView dialog)
   {
      setLayout(new BorderLayout());

      JLabel question = new JLabel(translator.realisticTranslate(Translation.WIE_VIELE_NEUE_WOERTER_MOECHTEN_SIE_LERNEN_));
      question.setFont(Settings.getButtonFont());
      question.setOpaque(false);
      question.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      add(question, BorderLayout.NORTH);

      JPanel center = new JPanel();
      center.setOpaque(false);
      center.setLayout(new FlowLayout());

      TrainingTableModel model = Data.findTrainingModel(
            dialog.getLanguageDirection(), dialog.getFieldOfTraining());
      TrainingTable table = new TrainingTable(model);
      JScrollPane scroller = new JScrollPane(table);
      scroller.setMinimumSize(new Dimension(900, 260));
      scroller.setPreferredSize(new Dimension(900, 260));
      scroller.setMaximumSize(new Dimension(900, 260));
      center.add(scroller);

      add(center, BorderLayout.CENTER);

      JPanel buttonWrapper = new JPanel(new FlowLayout());
      
      JButton cancelButton = new JButton(translator.realisticTranslate(Translation.ABBRECHEN));
      cancelButton.setFont(Settings.getButtonFont());
      cancelButton.setIcon(new ImageIcon(ApplicationImages.getCancel()));
      
      JButton nextButton = new JButton(translator.realisticTranslate(Translation.WEITER));
      nextButton.setFont(Settings.getButtonFont());
      nextButton.setIcon(new ImageIcon(ApplicationImages.getArrow()));
      
      buttonWrapper.add(cancelButton);
      buttonWrapper.add(nextButton);
      add(buttonWrapper, BorderLayout.SOUTH);
      
      nextButton.addActionListener(event -> {
         dialog.setNewExpressions(table.findNewExpressions(dialog.getLanguageDirection()));
         dialog.setOldExpressions(table.findOldToBeRepeatedExpressions());
         dialog.initTraining();
      });
      
      cancelButton.addActionListener(event -> {
         dialog.cancelTrainingStart();
      });
   }
}
