package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.FieldOfTraining;

public class FieldOfTrainingTab extends JPanel
{
   private static final long serialVersionUID = -2560396853976699409L;

   private ButtonGroup areaGroup;
   private JRadioButton chapterButton;
   private JRadioButton selectedButton;
   private JRadioButton onceButton;
   private JButton nextButton;
   private JButton cancelButton;

   private Translator translator;

   public FieldOfTrainingTab(App app, Common common, Model model, StartTrainingView dialog)
   {
      translator = common.getTranslator();
      setLayout(new BorderLayout());
      setBackground(app.appColors.getBackground());
      setOpaque(true);

      JLabel question = new JLabel(translator.realisticTranslate(app, 
            Translation.WOHER_SOLLEN_DIE_VOKABELN_STAMMEN_));
      question.setForeground(app.appColors.trainer.getTextForeground());
      question.setFont(app.appFonts.buttonFont);
      question.setOpaque(false);
      question.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      add(question, BorderLayout.NORTH);

      JPanel center = new JPanel();
      center.setOpaque(false);
      center.setLayout(new FlowLayout());
      areaGroup = new ButtonGroup();

      JPanel vertical = new JPanel();
      vertical.setLayout(new BoxLayout(vertical, 1));
      vertical.setOpaque(false);

      chapterButton = new JRadioButton(
            translator.realisticTranslate(app, Translation.AUS_DEN_LEKTIONEN));
      chapterButton.setBackground(app.appColors.trainer.getButton());
      chapterButton.setForeground(app.appColors.trainer.getTextForeground());
      chapterButton.setFont(app.appFonts.toolbarButtonFont);
      chapterButton.setActionCommand(FieldOfTraining.AREA_CHAPTER.name());
      areaGroup.add(chapterButton);

      selectedButton = new JRadioButton(translator
            .realisticTranslate(app, Translation.AUS_DEN_AUSGEWAEHLTEN_WOERTERN));
      selectedButton.setBackground(app.appColors.trainer.getButton());
      selectedButton.setForeground(app.appColors.trainer.getTextForeground());
      selectedButton.setFont(app.appFonts.toolbarButtonFont);
      selectedButton.setActionCommand(FieldOfTraining.AREA_SELECTED.name());
      areaGroup.add(selectedButton);

      onceButton = new JRadioButton(translator.realisticTranslate(app, 
            Translation.AUS_DEN_AUSGEWAEHLTEN_WOERTERN_EINMAL));
      onceButton.setBackground(app.appColors.trainer.getButton());
      onceButton.setForeground(app.appColors.trainer.getTextForeground());
      onceButton.setFont(app.appFonts.toolbarButtonFont);
      onceButton
            .setActionCommand(FieldOfTraining.AREA_SELECTED_TEMPORARY.name());
      areaGroup.add(onceButton);

      vertical.add(chapterButton);
      vertical.add(selectedButton);
      vertical.add(onceButton);
      center.add(vertical);

      add(center, BorderLayout.CENTER);

      JPanel buttonWrapper = new JPanel(new FlowLayout());
      buttonWrapper.setOpaque(false);

      cancelButton = new JButton(
            translator.realisticTranslate(app, Translation.ABBRECHEN));
      cancelButton.setBackground(app.appColors.trainer.getButton());
      cancelButton.setForeground(app.appColors.trainer.getButtonForeground());
      cancelButton.setFont(app.appFonts.buttonFont);
      cancelButton.setIcon(new ImageIcon(app.appImages.getCancel()));

      nextButton = new JButton(
            translator.realisticTranslate(app, Translation.WEITER));
      nextButton.setBackground(app.appColors.trainer.getButton());
      nextButton.setForeground(app.appColors.trainer.getButtonForeground());
      nextButton.setFont(app.appFonts.buttonFont);
      nextButton.setIcon(new ImageIcon(app.appImages.getArrow()));
      nextButton.setEnabled(false);

      buttonWrapper.add(cancelButton);
      buttonWrapper.add(nextButton);
      add(buttonWrapper, BorderLayout.SOUTH);

      chapterButton.addActionListener(_ -> {
         dialog.setFieldOfTraining(FieldOfTraining.AREA_CHAPTER);
         removeTabsToTheRight(dialog);
         nextButton.setEnabled(true);
      });

      selectedButton.addActionListener(_ -> {
         dialog.setFieldOfTraining(FieldOfTraining.AREA_SELECTED);
         removeTabsToTheRight(dialog);
         nextButton.setEnabled(true);
      });

      onceButton.addActionListener(_ -> {
         dialog.setFieldOfTraining(FieldOfTraining.AREA_SELECTED_TEMPORARY);
         removeTabsToTheRight(dialog);
         nextButton.setEnabled(true);
      });

      nextButton.addActionListener(_ -> {

         if (dialog.getTabbedPane().getTabCount() == 1)
         {
            dialog.getTabbedPane().addTab(
                  translator.realisticTranslate(app, Translation.RICHTUNG),
                  new ImageIcon(app.appImages.getArrow()),
                  new DirectionTab(app, common, model, dialog));
         }
         dialog.getTabbedPane().setSelectedIndex(1);
      });

      cancelButton.addActionListener(_ -> {
         dialog.cancelTrainingStart();
      });
   }

   private void removeTabsToTheRight(StartTrainingView dialog)
   {
      if (dialog.getTabbedPane().getTabCount() == 2)
      {
         dialog.getTabbedPane().remove(1);
      }
      else if (dialog.getTabbedPane().getTabCount() == 3)
      {
         dialog.getTabbedPane().remove(2);
         dialog.getTabbedPane().remove(1);
      }
      else if (dialog.getTabbedPane().getTabCount() == 4)
      {
         dialog.getTabbedPane().remove(3);
         dialog.getTabbedPane().remove(2);
         dialog.getTabbedPane().remove(1);
      }
   }
}
