package vokabeltrainer.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

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
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;

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

   private JPanel layoutPanel;

   public MainView()
   {
      initContent();
      initController();
      activeComponent = startPanel;
      layoutPanel.add(activeComponent);
   }

   private void initContent()
   {
      setLayout(new BullsEyeLayout(this));
      layoutPanel = new JPanel();
      TotemLayout layoutPanelLayout = new TotemLayout(layoutPanel);
      layoutPanel.setLayout(layoutPanelLayout);      
      
      setBorder(BorderFactory.createLineBorder(Color.YELLOW));
      layoutPanel.add(initToolBar());

      startPanel = new StartPanel();
      dictionaryPanel = new DictionaryController().getDictionaryPanel();
      letterPicturesPanel = new AlefbetPanel();
      statisticsPanel = new StatisticsPanel();
      settingsPanel = new SettingsPanel();
      successPanel = new SuccessPanel();
      
      add(layoutPanel);
   }

   private Component initToolBar()
   {
      JToolBar toolBar = new JToolBar("Werkzeugleiste");
      toolBar.setOpaque(true);
      toolBar.setBackground(Settings.getLightGold());
      toolBar.setRollover(true);
      toolBar.setFloatable(false);
      toolBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      toolBar.setMinimumSize(new Dimension(1200,80));
      toolBar.setMaximumSize(new Dimension(6000,80));

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

      toolBar.add(startButton);
      toolBar.add(dictionaryButton);
      toolBar.add(vocabularyCardsButton);
      toolBar.add(letterPicturesButton);
      toolBar.add(statisticsButton);
      toolBar.add(successButton);
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
            layoutPanel.remove(activeComponent);
         }
         activeComponent = (Component) dictionaryPanel;
         dictionaryPanel.setValues();
         layoutPanel.add(activeComponent);
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
               layoutPanel.remove(activeComponent);
            }

            TrainerView trainerPanel = new TrainerController(
                  dialog.getLanguageDirection(), dialog.getFieldOfTraining(),
                  dialog.getNewExpressions(), dialog.getOldExpressions()).getTrainerView();

            activeComponent = trainerPanel;
            layoutPanel.add(activeComponent);
            validate();
            repaint();
         }
      });

      letterPicturesButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            layoutPanel.remove(activeComponent);
         }
         activeComponent = letterPicturesPanel;
         layoutPanel.add(activeComponent);
         validate();
         repaint();
      });

      statisticsButton.addActionListener(event -> {
         Data.determineReloadDatabases();
         if (activeComponent != null)
         {
            layoutPanel.remove(activeComponent);
         }
         activeComponent = statisticsPanel;
         statisticsPanel.setValues();
         layoutPanel.add(activeComponent);
         validate();
         repaint();
      });
      
      successButton.addActionListener(event -> {
         Data.determineReloadDatabases();
         if (activeComponent != null)
         {
            layoutPanel.remove(activeComponent);
         }
         activeComponent = successPanel;
         successPanel.reset();
         layoutPanel.add(activeComponent);
         validate();
         repaint();
      });

      aboutButton.addActionListener(event -> {
         if (activeComponent != null)
         {
            layoutPanel.remove(activeComponent);
         }
         activeComponent = new JScrollPane(settingsPanel);

         layoutPanel.add(activeComponent);
         validate();
         repaint();
      });
   }

   public void moveToStartPanel()
   {
      if (activeComponent != null)
      {
         layoutPanel.remove(activeComponent);
      }
      activeComponent = startPanel;
      layoutPanel.add(activeComponent);
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
