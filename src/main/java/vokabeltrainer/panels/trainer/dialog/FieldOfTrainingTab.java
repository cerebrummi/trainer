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

import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.colors.TrainerColors;
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
   
   private Translator translator = Common.getTranslator();

   public FieldOfTrainingTab(StartTrainingView dialog)
   {
      setLayout(new BorderLayout());
      setBackground(TrainerColors.getPanelBackground());

      JLabel question = new JLabel(translator.realisticTranslate(Translation.WOHER_SOLLEN_DIE_VOKABELN_STAMMEN_));
      question.setForeground(TrainerColors.getTextForeground());
      question.setFont(ApplicationFonts.getButtonFont());
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

      chapterButton = new JRadioButton(translator.realisticTranslate(Translation.AUS_DEN_LEKTIONEN));
      chapterButton.setBackground(TrainerColors.getButton());
      chapterButton.setForeground(TrainerColors.getTextForeground());
      chapterButton.setFont(ApplicationFonts.getToolbarButtonFont());
      chapterButton.setActionCommand(FieldOfTraining.AREA_CHAPTER.name());
      areaGroup.add(chapterButton);

      selectedButton = new JRadioButton(translator.realisticTranslate(Translation.AUS_DEN_AUSGEWAEHLTEN_WOERTERN));
      selectedButton.setBackground(TrainerColors.getButton());
      selectedButton.setForeground(TrainerColors.getTextForeground());
      selectedButton.setFont(ApplicationFonts.getToolbarButtonFont());
      selectedButton.setActionCommand(FieldOfTraining.AREA_SELECTED.name());
      areaGroup.add(selectedButton);
      
      onceButton = new JRadioButton(translator.realisticTranslate(Translation.AUS_DEN_AUSGEWAEHLTEN_WOERTERN_EINMAL));
      onceButton.setBackground(TrainerColors.getButton());
      onceButton.setForeground(TrainerColors.getTextForeground());
      onceButton.setFont(ApplicationFonts.getToolbarButtonFont());
      onceButton.setActionCommand(FieldOfTraining.AREA_SELECTED_TEMPORARY.name());
      areaGroup.add(onceButton);

      vertical.add(chapterButton);
      vertical.add(selectedButton);
      vertical.add(onceButton);
      center.add(vertical);

      add(center, BorderLayout.CENTER);

      JPanel buttonWrapper = new JPanel(new FlowLayout());
      buttonWrapper.setOpaque(false);
      
      cancelButton = new JButton(translator.realisticTranslate(Translation.ABBRECHEN));
      cancelButton.setBackground(TrainerColors.getButton());
      cancelButton.setForeground(TrainerColors.getButtonForeground());
      cancelButton.setFont(ApplicationFonts.getButtonFont());
      cancelButton.setIcon(new ImageIcon(ApplicationImages.getCancel()));
      
      nextButton = new JButton(translator.realisticTranslate(Translation.WEITER));
      nextButton.setBackground(TrainerColors.getButton());
      nextButton.setForeground(TrainerColors.getButtonForeground());
      nextButton.setFont(ApplicationFonts.getButtonFont());
      nextButton.setIcon(new ImageIcon(ApplicationImages.getArrow()));
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
            dialog.getTabbedPane().addTab(translator.realisticTranslate(Translation.RICHTUNG),
                  new ImageIcon(ApplicationImages.getArrow()),
                  new DirectionTab(dialog));
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
   }
}
