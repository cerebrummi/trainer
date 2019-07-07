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

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Command;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;

public class NewWordsTab extends BackgroundPanelTiled
{
   private static final long serialVersionUID = 4309983650927883172L;

   private JButton nextButton;
   private ButtonGroup newWordsYesNoGroup;
   private JRadioButton yesNewWordsButton;
   private JRadioButton noNewWordsButton;
   private JButton cancelButton;

   public NewWordsTab(StartTrainingDialog dialog)
   {
      setLayout(new BorderLayout());

      JLabel question = new JLabel("Wollen Sie jetzt auch neue Wörter lernen?");
      question.setFont(Settings.getButtonFont());
      question.setOpaque(false);
      question.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      add(question, BorderLayout.NORTH);

      JPanel center = new JPanel();
      center.setOpaque(false);
      center.setLayout(new FlowLayout());
      newWordsYesNoGroup = new ButtonGroup();

      JPanel vertical = new JPanel();
      vertical.setLayout(new BoxLayout(vertical, 1));
      vertical.setOpaque(false);

      yesNewWordsButton = new JRadioButton(
            "Ja, ich möchte auch neue Wörter lernen.");
      yesNewWordsButton.setFont(Settings.getToolBarButtonFont());
      yesNewWordsButton.setActionCommand(Command.YES_NEW_WORD.name());
      newWordsYesNoGroup.add(yesNewWordsButton);

      noNewWordsButton = new JRadioButton(
            "Nein, ich möchte nur Wörter wiederholen.");
      noNewWordsButton.setFont(Settings.getToolBarButtonFont());
      noNewWordsButton.setActionCommand(Command.NO_NEW_WORD.name());
      newWordsYesNoGroup.add(noNewWordsButton);

      JLabel oldWordsDToH = new JLabel();
      oldWordsDToH.setText("Wiederholung Deutsch >>> Hebräisch: "
            + dialog.getOldExpressionsDToH().size() + " Wörter");
      oldWordsDToH.setFont(Main.getGermanFont(20F));

      JLabel oldWordsHToD = new JLabel();
      oldWordsHToD.setText("Wiederholung Hebräisch >>> Deutsch: "
            + dialog.getOldExpressionsHToD().size() + " Wörter");
      oldWordsHToD.setFont(Main.getGermanFont(20F));

      vertical.add(yesNewWordsButton);
      vertical.add(noNewWordsButton);
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

      this.yesNewWordsButton.addActionListener(event -> {
         dialog.setNewWords(true);
         nextButton.setEnabled(true);
      });

      this.noNewWordsButton.addActionListener(event -> {
         if (dialog.getNewWords() == null || dialog.getNewWords() == true)
         {
            for (int i = 2; i < dialog.getTabbedPane().getTabCount();)
            {
               dialog.getTabbedPane().remove(i);
            }
         }
         dialog.setNewWords(false);
         nextButton.setEnabled(true);
      });

      this.nextButton.addActionListener(event -> {
         if (!dialog.getNewWords() && dialog.getOldExpressionsDToH().isEmpty()
               && dialog.getOldExpressionsHToD().isEmpty())
         {
            dialog.showNowWordsForTraining();
            return;
         }

         if (dialog.getTabbedPane().getTabCount() == 1)
         {
            dialog.getTabbedPane().addTab("RICHTUNG",
                  new ImageIcon(ApplicationImages.getArrow()),
                  new DirectionTab(dialog));
         }
         dialog.getTabbedPane().setSelectedIndex(1);
      });

      cancelButton.addActionListener(event -> {
         dialog.cancelTrainingStart();
      });
   }
}
