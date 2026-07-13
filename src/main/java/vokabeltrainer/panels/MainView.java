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
import vokabeltrainer.TextImage;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Initializer;
import vokabeltrainer.common.Settings;
import vokabeltrainer.common.colors.MainColors;
import vokabeltrainer.panels.dictionary.DictionaryController;
import vokabeltrainer.panels.dictionary.DictionaryViewConnector;
import vokabeltrainer.panels.trainer.TrainerController;
import vokabeltrainer.panels.trainer.dialog.StartTrainingController;
import vokabeltrainer.panels.trainer.dialog.StartTrainingView;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class MainView extends JPanel
{
   private static final long serialVersionUID = -8541715304466855004L;

   private JButton vocabularyCardsButton;
   private JButton dictionaryButton;
   private JButton startButton;
   private JButton inputButton;
   private JButton statisticsButton;
   private JButton aboutButton;
   private JButton letterPicturesButton;
   private JButton successButton;
   private Component activeComponent;
   private InputPanel inputPanel;
   private DictionaryViewConnector dictionaryPanel;
   private StatisticsPanel statisticsPanel;
   private StartPanel startPanel;
   private SettingsPanel settingsPanel;
   private AlefbetPanel letterPicturesPanel;
   private SuccessPanel successPanel;
   private JButton languageButton;

   private JButton darkmodeButton;

   private JMenuBar menuBar;

   private JButton backButton;
   private TranslationPanel languagePanel;
   
   private Initializer initializer;

   private Translator translator;

   public MainView(Initializer initializer)
   {
      this.initializer = initializer;
      initContent();
      initController();
      activeComponent = startPanel;
      add(activeComponent);
   }

   private void initContent()
   {
      setLayout(new BorderLayout());
      this.setOpaque(false);
      this.setBackground(ApplicationColors.getTransparent());

      translator = Common.getTranslator();

      initToolBar();

      startPanel = new StartPanel();
      inputPanel = new InputPanel();
      dictionaryPanel = new DictionaryController().getDictionaryPanel();
      letterPicturesPanel = new AlefbetPanel();
      statisticsPanel = new StatisticsPanel();
      settingsPanel = new SettingsPanel(initializer);
      successPanel = new SuccessPanel();
   }

   private void initLanguageContent()
   {
      this.removeAll();

      initLanguageToolBar();
      languagePanel = new TranslationPanel();
      add(languagePanel);
      initBackController();

      initializer.resetMenuBar();
      this.validate();
      this.repaint();
   }

   private void initColormodeContent()
   {
      this.removeAll();

      initColorToolBar();
      ColorPanel colorPanel = new ColorPanel();
      add(colorPanel);
      initBackController();

      initializer.resetMenuBar();
      this.validate();
      this.repaint();

   }

   private void initBackController()
   {
      backButton.addActionListener(_ -> {
         this.removeAll();
         initContent();
         initController();
         activeComponent = startPanel;
         add(activeComponent);
         initializer.resetMenuBar();
         Common.getjFrame().validate();
         Common.getjFrame().repaint();
      });
   }

   private void initLanguageToolBar()
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(ApplicationColors.getLightGold());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200, 80));
      menuBar.setMaximumSize(new Dimension(6000, 80));

      backButton = new JButton(new ImageIcon(ApplicationImages.getBack()));

      menuBar.add(backButton);
   }

   private void initColorToolBar()
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(ApplicationColors.getLightGold());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200, 80));
      menuBar.setMaximumSize(new Dimension(6000, 80));

      backButton = new JButton(new ImageIcon(ApplicationImages.getBack()));

      menuBar.add(backButton);

   }

   private void initToolBar()
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(MainColors.getToolbarBackground());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200, 80));
      menuBar.setMaximumSize(new Dimension(6000, 80));

      startButton = new JButton(
            translator.realisticTranslate(Translation.DATEN),
            new ImageIcon(ApplicationImages.getLogoFolder()));
      inputButton = new JButton(
            translator.realisticTranslate(Translation.EINGABE));
      vocabularyCardsButton = new JButton(
            translator.realisticTranslate(Translation.VOKABELN_ABFRAGEN));
      dictionaryButton = new JButton(
            translator.realisticTranslate(Translation.WOERTERBUCH));
      statisticsButton = new JButton(
            translator.realisticTranslate(Translation.TRAININGSUEBERSICHT));
      aboutButton = new JButton(new ImageIcon(ApplicationImages.getLogo24()));
      letterPicturesButton = new JButton(
            translator.realisticTranslate(Translation.ALEFBET));
      successButton = new JButton(
            translator.realisticTranslate(Translation.KARTEIKASTEN));
      languageButton = new JButton(new ImageIcon(ApplicationImages.getL18n()));
      darkmodeButton = new JButton(
            new ImageIcon(ApplicationImages.getDarkmode()));

      startButton.setFont(ApplicationFonts.toolbarButtonFont);
      inputButton.setFont(ApplicationFonts.toolbarButtonFont);
      vocabularyCardsButton.setFont(ApplicationFonts.toolbarButtonFont);
      dictionaryButton.setFont(ApplicationFonts.toolbarButtonFont);
      statisticsButton.setFont(ApplicationFonts.toolbarButtonFont);
      letterPicturesButton.setFont(ApplicationFonts.toolbarButtonFont);
      successButton.setFont(ApplicationFonts.toolbarButtonFont);
      languageButton.setFont(ApplicationFonts.toolbarButtonFont);
      darkmodeButton.setFont(ApplicationFonts.toolbarButtonFont);

      startButton.setBackground(MainColors.getButtonBackground());
      inputButton.setBackground(MainColors.getButtonBackground());
      vocabularyCardsButton.setBackground(MainColors.getButtonBackground());
      dictionaryButton.setBackground(MainColors.getButtonBackground());
      statisticsButton.setBackground(MainColors.getButtonBackground());
      letterPicturesButton.setBackground(MainColors.getButtonBackground());
      successButton.setBackground(MainColors.getButtonBackground());
      languageButton.setBackground(MainColors.getButtonBackground());
      darkmodeButton.setBackground(MainColors.getButtonBackground());
      aboutButton.setBackground(MainColors.getButtonBackground());

      menuBar.add(startButton);
      menuBar.add(inputButton);
      menuBar.add(dictionaryButton);
      menuBar.add(vocabularyCardsButton);
      menuBar.add(letterPicturesButton);
      menuBar.add(statisticsButton);
      menuBar.add(successButton);
      menuBar.add(languageButton);
      menuBar.add(darkmodeButton);
      menuBar.add(Box.createHorizontalGlue());
      menuBar.add(aboutButton);
   }

   private void initController()
   {
      startButton.addActionListener(_ -> {
         moveToStartPanel();
      });

      inputButton.addActionListener(_ -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = (Component) inputPanel;
         inputPanel.reset();
         add(activeComponent);
         validate();
         repaint();
      });

      dictionaryButton.addActionListener(_ -> {
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

      vocabularyCardsButton.addActionListener(_ -> {

         if (Settings.isSchabbat_modus() && Common.isSchabbat())
         {
            JOptionPane.showMessageDialog(this, "", Settings.getWindowTitle(),
                  JOptionPane.INFORMATION_MESSAGE,
                  new ImageIcon(TextImage.make(
                        translator
                              .realisticTranslate(Translation.ES_IST_SCHABBAT),
                        translator.realisticTranslate(
                              Translation.DAS_TRAINING_IST_ABGESCHALTET))));
            return;
         }

         Data.determineReloadDatabases();
         StartTrainingView dialog = new StartTrainingController()
               .getStartTrainingView();
         dialog.setLocationRelativeTo(null);
         dialog.setVisible(true);

         if (dialog.isTraining())
         {
            if (dialog.getNewExpressions().isEmpty()
                  && (dialog.getOldExpressions() == null
                        || dialog.getOldExpressions().isEmpty()))
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
                  dialog.getNewExpressions(), dialog.getOldExpressions())
                        .getTrainerView();

            activeComponent = trainerPanel;
            add(activeComponent);
            validate();
            repaint();
         }
      });

      letterPicturesButton.addActionListener(_ -> {
         if (activeComponent != null)
         {
            remove(activeComponent);
         }
         activeComponent = letterPicturesPanel;
         add(activeComponent);
         validate();
         repaint();
      });

      statisticsButton.addActionListener(_ -> {
         Data.determineReloadDatabases();
         moveToStatisticsPanel();
      });

      successButton.addActionListener(_ -> {
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

      aboutButton.addActionListener(_ -> {
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

      languageButton.addActionListener(_ -> {
         initLanguageContent();
      });

      darkmodeButton.addActionListener(_ -> {
         initColormodeContent();
      });
   }

   public void moveToStartPanel()
   {
      if (activeComponent != null)
      {
         remove(activeComponent);
      }
      startPanel.setValues();
      activeComponent = startPanel;
      add(activeComponent);
      validate();
      repaint();
   }

   public void moveToStatisticsPanel()
   {
      if (activeComponent != null)
      {
         remove(activeComponent);
      }
      statisticsPanel.setValues();
      activeComponent = statisticsPanel;
      add(activeComponent);
      validate();
      repaint();
   }

   private void showNoWordsForTraining()
   {
      JOptionPane.showMessageDialog(Common.getjFrame(), "",
            Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
            new ImageIcon(TextImage.make(
                  translator.realisticTranslate(
                        Translation.KEINE_WORTE_ZUM_UEBEN_AUSGEWAEHLT),
                  translator.realisticTranslate(
                        Translation.BITTE_NEUE_WORTE_DURCH_EINGABE_EINER_ANZAHL),
                  translator.realisticTranslate(
                        Translation.AUSWAHLEN__UM_DANN_DAS_TRAINING_ZU_STARTEN_))));
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
