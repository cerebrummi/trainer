package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
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
   private JButton successButton;
   private Component activeComponent;
   private DictionaryViewConnector dictionaryPanel;
   private StatisticsPanel statisticsPanel;
   private StartPanel startPanel;
   private SettingsPanel settingsPanel;
   private AlefbetPanel letterPicturesPanel;
   private SuccessPanel successPanel;

   private JMenuBar menuBar;

   public MainView()
   {
      initContent();
      initController();
      activeComponent = startPanel;
      add(activeComponent);
   }

   private void initContent()
   {
      setLayout(new BorderLayout());

      initToolBar();

      startPanel = new StartPanel();
      dictionaryPanel = new DictionaryController().getDictionaryPanel();
      letterPicturesPanel = new AlefbetPanel();
      statisticsPanel = new StatisticsPanel();
      settingsPanel = new SettingsPanel();
      successPanel = new SuccessPanel();
   }

   private void initToolBar()
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(Settings.getLightGold());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200,80));
      menuBar.setMaximumSize(new Dimension(6000,80));

      startButton = new JButton("Start");
      vocabularyCardsButton = new JButton("Vokabelkarten");
      dictionaryButton = new JButton("Wörterbuch");
      statisticsButton = new JButton("Trainingsübersicht");
      aboutButton = new JButton(new ImageIcon(ApplicationImages.getLogo24()));
      letterPicturesButton = new JButton("Alefbet");
      successButton = new JButton("Karteikasten");

      startButton.setFont(Settings.getToolBarButtonFont());
      vocabularyCardsButton.setFont(Settings.getToolBarButtonFont());
      dictionaryButton.setFont(Settings.getToolBarButtonFont());
      statisticsButton.setFont(Settings.getToolBarButtonFont());
      letterPicturesButton.setFont(Settings.getToolBarButtonFont());
      successButton.setFont(Settings.getToolBarButtonFont());

      menuBar.add(startButton);
      menuBar.add(dictionaryButton);
      menuBar.add(vocabularyCardsButton);
      menuBar.add(letterPicturesButton);
      menuBar.add(statisticsButton);
      menuBar.add(successButton);
      menuBar.add(Box.createHorizontalGlue());
      menuBar.add(aboutButton);
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
         activeComponent = (Component) dictionaryPanel;
         dictionaryPanel.setValues();
         add(activeComponent);
         validate();
         repaint();
      });

      vocabularyCardsButton.addActionListener(event -> {
         Data.determineReloadDatabases();
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
            add(activeComponent);
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
         add(activeComponent);
         validate();
         repaint();
      });

      statisticsButton.addActionListener(event -> {
         Data.determineReloadDatabases();
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = statisticsPanel;
         statisticsPanel.setValues();
         add(activeComponent);
         validate();
         repaint();
      });
      
      successButton.addActionListener(event -> {
         Data.determineReloadDatabases();
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = successPanel;
         successPanel.reset();
         add(activeComponent);
         validate();
         repaint();
      });

      aboutButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = new JScrollPane(settingsPanel);
         settingsPanel.setValues();
         add(activeComponent);
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
      add(activeComponent);
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

   public JMenuBar getMenuBar()
   {
      return menuBar;
   }

}
