package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.types.Language;

public class DirectionTab extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -5986907667521647559L;

   private ButtonGroup directionGroup;
   private JRadioButton germanToHebrewButton;
   private JRadioButton hebrewToGermanButton;
   private JButton nextButton;
   private JButton cancelButton;

   public DirectionTab(StartTrainingDialog dialog)
   {

      setLayout(new BorderLayout());

      JLabel question = new JLabel("In welcher Richtung wollen Sie lernen?");
      question.setFont(Settings.getButtonFont());
      question.setOpaque(false);
      question.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      add(question, BorderLayout.NORTH);

      JPanel center = new JPanel();
      center.setOpaque(false);
      center.setLayout(new FlowLayout());
      directionGroup = new ButtonGroup();

      JPanel vertical = new JPanel();
      vertical.setLayout(new BoxLayout(vertical, 1));
      vertical.setOpaque(false);

      germanToHebrewButton = new JRadioButton("Deutsch >>> Hebräisch");
      germanToHebrewButton.setFont(Settings.getToolBarButtonFont());
      directionGroup.add(germanToHebrewButton);

      hebrewToGermanButton = new JRadioButton("Hebräisch >>> Deutsch");
      hebrewToGermanButton.setFont(Settings.getToolBarButtonFont());
      directionGroup.add(hebrewToGermanButton);

      JLabel oldWordsDToH = new JLabel();
      oldWordsDToH.setText("Wiederholung Deutsch >>> Hebräisch: "
            + dialog.getOldExpressionsDToH().size() + " Wörter");
      oldWordsDToH.setFont(Main.getGermanFont(20F));

      JLabel oldWordsHToD = new JLabel();
      oldWordsHToD.setText("Wiederholung Hebräisch >>> Deutsch: "
            + dialog.getOldExpressionsHToD().size() + " Wörter");
      oldWordsHToD.setFont(Main.getGermanFont(20F));
      
      vertical.add(germanToHebrewButton);
      vertical.add(hebrewToGermanButton);
      vertical.add(Box.createRigidArea(new Dimension(30, 30)));
      vertical.add(oldWordsDToH);
      vertical.add(oldWordsHToD);
      center.add(vertical);

      add(center, BorderLayout.CENTER);

      JPanel buttonWrapper = new JPanel(new FlowLayout());

      cancelButton = new JButton("abbrechen");
      cancelButton.setFont(Settings.getButtonFont());
      cancelButton.setIcon(new ImageIcon(ApplicationImages.getCancel()));

      nextButton = new JButton("weiter");
      nextButton.setFont(Settings.getButtonFont());
      nextButton.setIcon(new ImageIcon(ApplicationImages.getArrow()));
      nextButton.setEnabled(false);

      buttonWrapper.add(cancelButton);
      buttonWrapper.add(nextButton);
      add(buttonWrapper, BorderLayout.SOUTH);

      germanToHebrewButton.addActionListener(event -> {
         if (dialog.getTabbedPane().getTabCount() == 4
               && !Language.GERMAN.equals(dialog.getLanguageDirection()))
         {
            dialog.getTabbedPane().remove(3);
         }
         dialog.setLanguageDirection(Language.GERMAN);
         nextButton.setEnabled(true);
      });

      hebrewToGermanButton.addActionListener(event -> {
         if (dialog.getTabbedPane().getTabCount() == 4
               && !Language.HEBREW.equals(dialog.getLanguageDirection()))
         {
            dialog.getTabbedPane().remove(3);
         }
         dialog.setLanguageDirection(Language.HEBREW);
         nextButton.setEnabled(true);
      });

      nextButton.addActionListener(event -> {
         if (Language.GERMAN.equals(dialog.getLanguageDirection())
               && !dialog.getNewWords()
               && dialog.getOldExpressionsDToH().isEmpty())
         {
            dialog.showNowWordsForTraining();
            return;
         }
         if (Language.HEBREW.equals(dialog.getLanguageDirection())
               && !dialog.getNewWords()
               && dialog.getOldExpressionsHToD().isEmpty())
         {
            dialog.showNowWordsForTraining();
            return;
         }

         if (dialog.getNewWords() == false)
         {
            dialog.setNewExpressions(Collections.emptyList());
            dialog.initTraining();
            return;
         }
         if (dialog.getTabbedPane().getTabCount() == 2)
         {
            dialog.getTabbedPane().addTab("GEBIET",
                  new ImageIcon(ApplicationImages.getArrow()),
                  new FieldOfTrainingTab(dialog));
         }
         dialog.getTabbedPane().setSelectedIndex(2);
      });

      cancelButton.addActionListener(event -> {
         dialog.cancelTrainingStart();
      });
   }
}
