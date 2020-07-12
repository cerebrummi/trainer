package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.Common;
import vokabeltrainer.panels.dictionary.DictionaryController;
import vokabeltrainer.panels.dictionary.DictionaryViewConnector;
import vokabeltrainer.panels.trainer.TrainerController;
import vokabeltrainer.panels.trainer.dialog.StartTrainingController;
import vokabeltrainer.panels.trainer.dialog.StartTrainingView;

public class MainView extends JPanel
{
   private static final long serialVersionUID = -8541715304466855004L;

   private JButton vocabularyCardsButton;
   private JButton dictionaryButton;
   private JButton startButton;
   private JButton statisticsButton;
   private JButton aboutButton;
   private JButton letterPicturesButton;
   private JPanel activeComponent;
   private DictionaryViewConnector dictionaryPanel;
   private StatisticsPanel statisticsPanel;
   private StartPanel startPanel;
   private SettingsPanel settingsPanel;
   private AlefbetPanel letterPicturesPanel;

   public MainView()
   {
      initContent();
      initController();
      activeComponent = startPanel;
      add(activeComponent, BorderLayout.CENTER);
   }

   private void initContent()
   {
      setLayout(new BorderLayout());
      setBorder(BorderFactory.createEmptyBorder());
      add(initToolBar(), BorderLayout.PAGE_START);

      startPanel = new StartPanel();
      dictionaryPanel = new DictionaryController().getDictionaryPanel();
      letterPicturesPanel = new AlefbetPanel();
      statisticsPanel = new StatisticsPanel();
      settingsPanel = new SettingsPanel();
   }

   private Component initToolBar()
   {
      JToolBar toolBar = new JToolBar("Werkzeugleiste");
      toolBar.setOpaque(true);
      toolBar.setBackground(Settings.getLightGold());
      toolBar.setRollover(true);
      toolBar.setFloatable(false);
      toolBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

      startButton = new JButton("Start");
      vocabularyCardsButton = new JButton("Vokabelkarten");
      dictionaryButton = new JButton("Wörterbuch");
      statisticsButton = new JButton("Statistik");
      aboutButton = new JButton(new ImageIcon(ApplicationImages.getLogo24()));
      letterPicturesButton = new JButton("Alefbet");

      startButton.setFont(Settings.getToolBarButtonFont());
      vocabularyCardsButton.setFont(Settings.getToolBarButtonFont());
      dictionaryButton.setFont(Settings.getToolBarButtonFont());
      statisticsButton.setFont(Settings.getToolBarButtonFont());
      letterPicturesButton.setFont(Settings.getToolBarButtonFont());

      toolBar.add(startButton);
      toolBar.add(dictionaryButton);
      toolBar.add(vocabularyCardsButton);
      toolBar.add(letterPicturesButton);
      toolBar.add(statisticsButton);
      toolBar.add(Box.createHorizontalGlue());
      toolBar.add(aboutButton);
      return toolBar;
   }

   private void initController()
   {
      startButton.addActionListener(event -> {
         moveToStartPanel();
      });

      dictionaryButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = (JPanel) dictionaryPanel;
         add(activeComponent, BorderLayout.CENTER);
         validate();
         repaint();
      });

      vocabularyCardsButton.addActionListener(event -> {
         StartTrainingView dialog = new StartTrainingController()
               .getStartTrainingView();
         dialog.setLocationRelativeTo(null);
         dialog.setVisible(true);

         
         if (dialog.isTraining())
         {
            if(dialog.getNewExpressions().isEmpty() && dialog.getOldExpressions().isEmpty())
            {
               this.showNoWordsForTraining();
               return;
            }
            
            if (activeComponent != null)
            {
               remove(activeComponent);
            }

            TrainerView trainerPanel = new TrainerController(
                  dialog.getLanguageDirection(), dialog.getFieldOfTraining(),
                  dialog.getNewExpressions(), dialog.getOldExpressions()).getTrainerView();

            activeComponent = trainerPanel;
            add(activeComponent, BorderLayout.CENTER);
            validate();
            repaint();
         }
      });

      letterPicturesButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = letterPicturesPanel;
         add(activeComponent, BorderLayout.CENTER);
         validate();
         repaint();
      });

      statisticsButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = statisticsPanel;
         statisticsPanel.setValues();
         add(activeComponent, BorderLayout.CENTER);
         validate();
         repaint();
      });

      aboutButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = settingsPanel;

         add(activeComponent, BorderLayout.CENTER);
         validate();
         repaint();
      });
   }

   public void moveToStartPanel()
   {
      if (activeComponent != null)
      {
         remove(activeComponent);
      }
      activeComponent = startPanel;
      add(activeComponent, BorderLayout.CENTER);
      validate();
      repaint();
   }
   
   private void showNoWordsForTraining()
   {
      JOptionPane.showMessageDialog(Common.getjFrame(), "", "Cerebrummi©",
            JOptionPane.PLAIN_MESSAGE,
            new ImageIcon(TextImage.make("Keine Worte zum Üben ausgewählt.",
                  "Bitte neue Worte durch Eingabe einer Anzahl",
                  "auswählen, um dann das Training zu starten.")));
   }

   public JButton getVocabularyCardsButton()
   {
      return vocabularyCardsButton;
   }

   public JButton getDictionaryButton()
   {
      return dictionaryButton;
   }

   public JButton getStartButton()
   {
      return startButton;
   }

   public JButton getStatisticsButton()
   {
      return statisticsButton;
   }

   public JButton getAboveButton()
   {
      return aboutButton;
   }

   public JButton getLetterPicturesButton()
   {
      return letterPicturesButton;
   }

   public DictionaryViewConnector getDictionaryPanel()
   {
      return dictionaryPanel;
   }

}
