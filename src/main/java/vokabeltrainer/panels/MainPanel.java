package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.panels.dictionary.DictionaryController;
import vokabeltrainer.panels.trainer.dialog.StartTrainingController;
import vokabeltrainer.panels.trainer.dialog.StartTrainingView;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class MainPanel extends JPanel
{
   private static final long serialVersionUID = -8541715304466855004L;

   private JButton vocabularyCardsButton;
   private JButton dictionaryButton;
   private JButton startButton;
   private JButton statisticsButton;
   private JButton settingsButton;
   private JButton aboveButton;
   private JButton letterPicturesButton;
   private JPanel activeComponent;
   private DictionaryView dictionaryPanel;
   private StatisticsPanel statisticsPanel;
   private StartPanel startPanel;
   private SettingsPanel settingsPanel;
   private LetterPicturesPanel letterPicturesPanel;

   public MainPanel()
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
      letterPicturesPanel = new LetterPicturesPanel();
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
      settingsButton = new JButton("Einstellungen");
      aboveButton = new JButton(new ImageIcon(ApplicationImages.getLogo24()));
      letterPicturesButton = new JButton("Alefbet - Alfabet");

      startButton.setFont(Settings.getToolBarButtonFont());
      vocabularyCardsButton.setFont(Settings.getToolBarButtonFont());
      dictionaryButton.setFont(Settings.getToolBarButtonFont());
      statisticsButton.setFont(Settings.getToolBarButtonFont());
      settingsButton.setFont(Settings.getToolBarButtonFont());
      letterPicturesButton.setFont(Settings.getToolBarButtonFont());

      toolBar.add(startButton);
      toolBar.add(dictionaryButton);
      toolBar.add(vocabularyCardsButton);
      toolBar.add(letterPicturesButton);
      toolBar.add(statisticsButton);
      toolBar.add(Box.createHorizontalGlue());
      toolBar.add(settingsButton);
      toolBar.add(aboveButton);
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
         activeComponent = dictionaryPanel;
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
            if (activeComponent != null)
            {
               remove(activeComponent);
            }

            Set<Expression> oldExpressions;
            if (Language.GERMAN.equals(dialog.getLanguageDirection()))
            {
               oldExpressions = dialog.getOldExpressionsDToH();
            }
            else
            {
               oldExpressions = dialog.getOldExpressionsHToD();
            }

            TrainerPanel trainerPanel = new TrainerPanel();
            trainerPanel.init(dialog.getLanguageDirection(),
                  dialog.getFieldOfTraining(), dialog.getNewExpressions(),
                  oldExpressions);
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

      settingsButton.addActionListener(event -> {
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

   public JButton getSettingsButton()
   {
      return settingsButton;
   }

   public JButton getAboveButton()
   {
      return aboveButton;
   }

   public JButton getLetterPicturesButton()
   {
      return letterPicturesButton;
   }

   public DictionaryView getDictionaryPanel()
   {
      return dictionaryPanel;
   }

}
