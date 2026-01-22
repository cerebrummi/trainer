package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
import vokabeltrainer.types.LanguageDirection;

public class DirectionTab extends JPanel
{
   private static final long serialVersionUID = -5986907667521647559L;

   private ButtonGroup directionGroup;
   private JRadioButton ownToNikudButton;
   private JRadioButton hebrewToOwnButton;
   private JRadioButton ownToSwedishButton;
   private JRadioButton swedishToOwnButton;
   private JRadioButton ownToGermanButton;
   private JRadioButton germanToOwnButton;
   private JButton nextButton;
   private JButton cancelButton;
   
   private Translator translator = Common.getTranslator();

   public DirectionTab(StartTrainingView dialog)
   {
      setLayout(new BorderLayout());
      setBackground(TrainerColors.getPanelBackgroundDark());
      setOpaque(true);

      JLabel question = new JLabel(translator.realisticTranslate(Translation.IN_WELCHER_RICHTUNG_WOLLEN_SIE_LERNEN_));
      question.setForeground(TrainerColors.getTextForeground());
      question.setFont(ApplicationFonts.getButtonFont());
      question.setOpaque(false);
      question.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      add(question, BorderLayout.NORTH);

      JPanel center = new JPanel();
      center.setOpaque(false);
      center.setBackground(TrainerColors.getTransparent());
      center.setLayout(new FlowLayout());
      directionGroup = new ButtonGroup();

      JPanel vertical = new JPanel();
      vertical.setLayout(new BoxLayout(vertical, 1));
      vertical.setOpaque(false);
      vertical.setBackground(TrainerColors.getTransparent());
      
      ownToNikudButton = new JRadioButton(translator.realisticTranslate(Translation.DEUTSCH)
            + " >> "
            + translator.realisticTranslate(Translation.HEBRAEISCH_));
      ownToNikudButton.setForeground(TrainerColors.getTextForeground());
      ownToNikudButton.setFont(ApplicationFonts.getToolbarButtonFont());
      directionGroup.add(ownToNikudButton);

      hebrewToOwnButton = new JRadioButton(translator.realisticTranslate(Translation.HEBRAEISCH_)
            + " >> "
            + translator.realisticTranslate(Translation.DEUTSCH));
      hebrewToOwnButton.setForeground(TrainerColors.getTextForeground());
      hebrewToOwnButton.setFont(ApplicationFonts.getToolbarButtonFont());
      directionGroup.add(hebrewToOwnButton);
      
      ownToSwedishButton = new JRadioButton(translator.realisticTranslate(Translation.DEUTSCH)
            + " >> "
            + translator.realisticTranslate(Translation.SCHWEDISCH));
      ownToSwedishButton.setForeground(TrainerColors.getTextForeground());
      ownToSwedishButton.setFont(ApplicationFonts.getToolbarButtonFont());
      directionGroup.add(ownToSwedishButton);
      
      swedishToOwnButton = new JRadioButton(translator.realisticTranslate(Translation.SCHWEDISCH)
            + " >> "
            + translator.realisticTranslate(Translation.DEUTSCH));
      swedishToOwnButton.setFont(ApplicationFonts.getToolbarButtonFont());
      swedishToOwnButton.setForeground(TrainerColors.getTextForeground());
      directionGroup.add(swedishToOwnButton);
      
      ownToGermanButton = new JRadioButton(translator.realisticTranslate(Translation.DEUTSCH)
            + " >> "
            + translator.realisticTranslate(Translation.GERMAN));
      ownToGermanButton.setFont(ApplicationFonts.getToolbarButtonFont());
      ownToGermanButton.setForeground(TrainerColors.getTextForeground());
      directionGroup.add(ownToGermanButton);
      
      germanToOwnButton = new JRadioButton(translator.realisticTranslate(Translation.GERMAN)
            + " >> "
            + translator.realisticTranslate(Translation.DEUTSCH));
      germanToOwnButton.setFont(ApplicationFonts.getToolbarButtonFont());
      germanToOwnButton.setForeground(TrainerColors.getTextForeground());
      directionGroup.add(germanToOwnButton);

      vertical.add(ownToNikudButton);
      vertical.add(ownToSwedishButton);
      vertical.add(ownToGermanButton);
      vertical.add(Box.createRigidArea(new Dimension(30, 30)));
      vertical.add(hebrewToOwnButton);
      vertical.add(swedishToOwnButton);
      vertical.add(germanToOwnButton);
      vertical.add(Box.createRigidArea(new Dimension(30, 30)));

      center.add(vertical);

      add(center, BorderLayout.CENTER);

      JPanel buttonWrapper = new JPanel(new FlowLayout());
      buttonWrapper.setOpaque(false);

      cancelButton = new JButton(translator.realisticTranslate(Translation.ABBRECHEN));
      cancelButton.setFont(ApplicationFonts.getButtonFont());
      cancelButton.setBackground(TrainerColors.getButton());
      cancelButton.setForeground(TrainerColors.getButtonForeground());
      cancelButton.setIcon(new ImageIcon(ApplicationImages.getCancel()));

      nextButton = new JButton(translator.realisticTranslate(Translation.WEITER));
      nextButton.setFont(ApplicationFonts.getButtonFont());
      nextButton.setBackground(TrainerColors.getButton());
      nextButton.setForeground(TrainerColors.getButtonForeground());
      nextButton.setIcon(new ImageIcon(ApplicationImages.getArrow()));
      nextButton.setEnabled(false);

      buttonWrapper.add(cancelButton);
      buttonWrapper.add(nextButton);
      add(buttonWrapper, BorderLayout.SOUTH);
      
      ownToNikudButton.addActionListener(_ -> {
         removeTabsToTheRight(dialog);
         dialog.setLanguageDirection(LanguageDirection.OWN_TO_HEBREW);
         nextButton.setEnabled(true);
      });
      
      ownToSwedishButton.addActionListener(_ -> {
         removeTabsToTheRight(dialog);
         dialog.setLanguageDirection(LanguageDirection.OWN_TO_SWEDISH);
         nextButton.setEnabled(true);
      });
      
      ownToGermanButton.addActionListener(_ -> {
         removeTabsToTheRight(dialog);
         dialog.setLanguageDirection(LanguageDirection.OWN_TO_GERMAN);
         nextButton.setEnabled(true);
      });

      hebrewToOwnButton.addActionListener(_ -> {
         removeTabsToTheRight(dialog);
         dialog.setLanguageDirection(LanguageDirection.HEBREW_TO_OWN);
         nextButton.setEnabled(true);
      });
      
      swedishToOwnButton.addActionListener(_ -> {
         removeTabsToTheRight(dialog);
         dialog.setLanguageDirection(LanguageDirection.SWEDISH_TO_OWN);
         nextButton.setEnabled(true);
      });
      
      germanToOwnButton.addActionListener(_ -> {
         removeTabsToTheRight(dialog);
         dialog.setLanguageDirection(LanguageDirection.GERMAN_TO_OWN);
         nextButton.setEnabled(true);
      });

      nextButton.addActionListener(_ -> {
         if (dialog.getTabbedPane().getTabCount() == 2 && FieldOfTraining.AREA_CHAPTER.compareTo(dialog.getFieldOfTraining())==0)
         {
            dialog.getTabbedPane().addTab(translator.realisticTranslate(Translation.DATENBANKEN_ANSEHEN),
                  new ImageIcon(ApplicationImages.getArrow()),
                  new DatabaseTab(dialog));
         }
         else
         {
        	 dialog.setDatabaseNames(null);
             dialog.getTabbedPane().addTab(translator.realisticTranslate(Translation.WIE_VIELE),
                   new ImageIcon(ApplicationImages.getArrow()),
                   new AmountTab(dialog));
         }
         dialog.getTabbedPane().setSelectedIndex(2);
      });
     

      cancelButton.addActionListener(_ -> {
         dialog.cancelTrainingStart();
      });
   }

   private void removeTabsToTheRight(StartTrainingView dialog)
   {
      if (dialog.getTabbedPane().getTabCount() == 3)
      {
         dialog.getTabbedPane().remove(2);
      }
      else if (dialog.getTabbedPane().getTabCount() == 4)
      {
         dialog.getTabbedPane().remove(3);
         dialog.getTabbedPane().remove(2);
      }
   }
   
}
