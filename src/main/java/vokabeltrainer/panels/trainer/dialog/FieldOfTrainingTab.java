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

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Command;
import vokabeltrainer.Settings;

public class FieldOfTrainingTab extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -2560396853976699409L;

   private ButtonGroup areaGroup;
   private JRadioButton chapterButton;
   private JRadioButton expressionKindButton;
   private JRadioButton allButton;
   private JRadioButton selectedButton;
   private JButton nextButton;
   private JButton cancelButton;

   public FieldOfTrainingTab(StartTrainingDialog dialog)
   {
      setLayout(new BorderLayout());

      JLabel question = new JLabel("Woher sollen die neuen Vokabeln stammen?");
      question.setFont(Settings.getButtonFont());
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

      chapterButton = new JRadioButton("Aus den Lektionen.");
      chapterButton.setFont(Settings.getToolBarButtonFont());
      chapterButton.setActionCommand(Command.AREA_CHAPTER.name());
      areaGroup.add(chapterButton);

      expressionKindButton = new JRadioButton("Aus den Wortarten.");
      expressionKindButton.setFont(Settings.getToolBarButtonFont());
      expressionKindButton
            .setActionCommand(Command.AREA_EXPRESSION_KIND.name());
      areaGroup.add(expressionKindButton);

      allButton = new JRadioButton("Aus allen Wörtern.");
      allButton.setFont(Settings.getToolBarButtonFont());
      allButton.setActionCommand(Command.AREA_ALL.name());
      areaGroup.add(allButton);

      selectedButton = new JRadioButton("Aus den ausgewählten Wörtern.");
      selectedButton.setFont(Settings.getToolBarButtonFont());
      selectedButton.setActionCommand(Command.AREA_SELECTED.name());
      areaGroup.add(selectedButton);

      vertical.add(chapterButton);
      vertical.add(expressionKindButton);
      vertical.add(selectedButton);
      vertical.add(allButton);
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

      chapterButton.addActionListener(event -> {
         if (dialog.getFieldOfTraining() == null)
         {
            dialog.setFieldOfTraining(Command.AREA_CHAPTER);
         }
         else if (dialog.getFieldOfTraining().equals(Command.AREA_CHAPTER))
         {
            // do nothing
         }
         else
         {
            dialog.setFieldOfTraining(Command.AREA_CHAPTER);
            if (dialog.getTabbedPane().getTabCount() == 4)
            {
               dialog.getTabbedPane().remove(3);
            }
         }
         nextButton.setEnabled(true);
      });

      expressionKindButton.addActionListener(event -> {
         if (dialog.getFieldOfTraining() == null)
         {
            dialog.setFieldOfTraining(Command.AREA_EXPRESSION_KIND);
         }
         else if (dialog.getFieldOfTraining()
               .equals(Command.AREA_EXPRESSION_KIND))
         {
            // do nothing
         }
         else
         {
            dialog.setFieldOfTraining(Command.AREA_EXPRESSION_KIND);
            if (dialog.getTabbedPane().getTabCount() == 4)
            {
               dialog.getTabbedPane().remove(3);
            }
         }
         nextButton.setEnabled(true);
      });

      selectedButton.addActionListener(event -> {
         if (dialog.getFieldOfTraining() == null)
         {
            dialog.setFieldOfTraining(Command.AREA_SELECTED);
         }
         else if (dialog.getFieldOfTraining().equals(Command.AREA_SELECTED))
         {
            // do nothing
         }
         else
         {
            dialog.setFieldOfTraining(Command.AREA_SELECTED);
            if (dialog.getTabbedPane().getTabCount() == 4)
            {
               dialog.getTabbedPane().remove(3);
            }
         }
         nextButton.setEnabled(true);
      });

      allButton.addActionListener(event -> {
         if (dialog.getFieldOfTraining() == null)
         {
            dialog.setFieldOfTraining(Command.AREA_ALL);
         }
         else if (dialog.getFieldOfTraining().equals(Command.AREA_ALL))
         {
            // do nothing
         }
         else
         {
            dialog.setFieldOfTraining(Command.AREA_ALL);
            if (dialog.getTabbedPane().getTabCount() == 4)
            {
               dialog.getTabbedPane().remove(3);
            }
         }
         nextButton.setEnabled(true);
      });

      nextButton.addActionListener(event -> {

         if (dialog.getTabbedPane().getTabCount() == 3)
         {
            dialog.getTabbedPane().addTab("WIEVIELE",
                  new ImageIcon(ApplicationImages.getArrow()),
                  new AmountTab(dialog));
         }
         dialog.getTabbedPane().setSelectedIndex(3);
      });
      
      cancelButton.addActionListener(event -> {
         dialog.cancelTrainingStart();
      });
   }
}
