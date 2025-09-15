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
import vokabeltrainer.common.Main;
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

   private JButton questionsAndAnswersButton;

   private JButton darkmodeButton;

   private JButton sentencesButton;
   private JButton sentences_1_Button;
   private JButton sentences_2_Button;
   private JButton sentences_3_Button;
   private JButton sentences_4_Button;
   private JButton sentences_5_Button;
   private JButton sentences_6_Button;
   private JButton sentences_7_Button;
   private JButton sentences_8_Button;
   private JButton sentences_9_Button;
   private JButton sentences_10_Button;
   private JButton sentences_11_Button;
   private JButton sentences_12_Button;
   private JButton sentences_13_Button;
   private JButton sentences_14_Button;
   private JButton sentences_15_Button;
   private JButton sentences_16_Button;
   private JButton sentences_17_Button;
   private JButton sentences_18_Button;
   private JButton sentences_19_Button;
   private JButton sentences_20_Button;
   private JButton sentences_21_Button;
   private JButton sentences_22_Button;
   private JButton sentences_23_Button;
   private JButton sentences_24_Button;

   private JMenuBar menuBar;

   private JButton backButton;
   private TranslationPanel languagePanel;
   private JPanel sentencesPanel;

   private Translator translator;

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
      this.setOpaque(false);
      this.setBackground(ApplicationColors.getTransparent());

      translator = Common.getTranslator();

      initToolBar();

      startPanel = new StartPanel();
      inputPanel = new InputPanel();
      dictionaryPanel = new DictionaryController().getDictionaryPanel();
      letterPicturesPanel = new AlefbetPanel();
      statisticsPanel = new StatisticsPanel();
      settingsPanel = new SettingsPanel();
      successPanel = new SuccessPanel();
   }

   private void initLanguageContent()
   {
      this.removeAll();

      initLanguageToolBar();
      languagePanel = new TranslationPanel();
      add(languagePanel);
      initBackController();

      Main.resetMenuBar();
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

      Main.resetMenuBar();
      this.validate();
      this.repaint();
      
   }

   private void initSentencesContent()
   {
      this.removeAll();

      initSentencesToolBar();
      initBackController();

      Main.resetMenuBar();
      this.validate();
      this.repaint();
   }

   private void initQuestionsAndAnswersContent()
   {
      this.removeAll();

      this.initQuestionsAndAnswersToolBar();
      initBackController();

      Main.resetMenuBar();
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
         Main.resetMenuBar();
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

   private void initSentencesToolBar()
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(ApplicationColors.getLightGold());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200, 80));
      menuBar.setMaximumSize(new Dimension(6000, 80));

      backButton = new JButton(new ImageIcon(ApplicationImages.getBack()));
      sentences_1_Button = new JButton("1");
      sentences_1_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(1);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_2_Button = new JButton("2");
      sentences_2_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(2);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_3_Button = new JButton("3");
      sentences_3_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(3);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_4_Button = new JButton("4");
      sentences_4_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(4);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_5_Button = new JButton("5");
      sentences_5_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(5);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_6_Button = new JButton("6");
      sentences_6_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(6);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_7_Button = new JButton("7");
      sentences_7_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(7);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_8_Button = new JButton("8");
      sentences_8_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(8);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_9_Button = new JButton("9");
      sentences_9_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(9);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_10_Button = new JButton("10");
      sentences_10_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(10);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_11_Button = new JButton("11");
      sentences_11_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(11);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_12_Button = new JButton("12");
      sentences_12_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(12);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_13_Button = new JButton("13");
      sentences_13_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(13);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_14_Button = new JButton("14");
      sentences_14_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(14);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_15_Button = new JButton("15");
      sentences_15_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(15);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_16_Button = new JButton("16");
      sentences_16_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(16);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_17_Button = new JButton("17");
      sentences_17_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(17);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_18_Button = new JButton("18");
      sentences_18_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(18);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_19_Button = new JButton("19");
      sentences_19_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(19);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_20_Button = new JButton("20");
      sentences_20_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(20);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_21_Button = new JButton("21");
      sentences_21_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(21);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_22_Button = new JButton("22");
      sentences_22_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(22);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_23_Button = new JButton("23");
      sentences_23_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(23);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_24_Button = new JButton("24");
      sentences_24_Button.addActionListener(_ -> {
         this.removeAll();
         sentencesPanel = new SentencesPanel(24);
         this.add(sentencesPanel);
         validate();
         repaint();
      });
      sentences_1_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_2_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_3_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_4_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_5_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_6_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_7_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_8_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_9_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_10_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_11_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_12_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_13_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_14_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_15_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_16_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_17_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_18_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_19_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_20_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_21_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_22_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_23_Button.setFont(ApplicationFonts.getToolbarButtonFont());
      sentences_24_Button.setFont(ApplicationFonts.getToolbarButtonFont());

      menuBar.add(backButton);
      menuBar.add(sentences_1_Button);
      menuBar.add(sentences_2_Button);
      menuBar.add(sentences_3_Button);
      menuBar.add(sentences_4_Button);
      menuBar.add(sentences_5_Button);
      menuBar.add(sentences_6_Button);
      menuBar.add(sentences_7_Button);
      menuBar.add(sentences_8_Button);
      menuBar.add(sentences_9_Button);
      menuBar.add(sentences_10_Button);
      menuBar.add(sentences_11_Button);
      menuBar.add(sentences_12_Button);
      menuBar.add(sentences_13_Button);
      menuBar.add(sentences_14_Button);
      menuBar.add(sentences_15_Button);
      menuBar.add(sentences_16_Button);
      menuBar.add(sentences_17_Button);
      menuBar.add(sentences_18_Button);
      menuBar.add(sentences_19_Button);
      menuBar.add(sentences_20_Button);
      menuBar.add(sentences_21_Button);
      menuBar.add(sentences_22_Button);
      menuBar.add(sentences_23_Button);
      menuBar.add(sentences_24_Button);
   }

   private void initQuestionsAndAnswersToolBar()
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
      sentencesButton = new JButton(
            translator.realisticTranslate(Translation.SAETZE));
      questionsAndAnswersButton = new JButton(
            new ImageIcon(ApplicationImages.getQuestionsAndAnswers2()));
      darkmodeButton = new JButton(
            new ImageIcon(ApplicationImages.getDarkmode()));

      startButton.setFont(ApplicationFonts.getToolbarButtonFont());
      inputButton.setFont(ApplicationFonts.getToolbarButtonFont());
      vocabularyCardsButton.setFont(ApplicationFonts.getToolbarButtonFont());
      dictionaryButton.setFont(ApplicationFonts.getToolbarButtonFont());
      statisticsButton.setFont(ApplicationFonts.getToolbarButtonFont());
      letterPicturesButton.setFont(ApplicationFonts.getToolbarButtonFont());
      successButton.setFont(ApplicationFonts.getToolbarButtonFont());
      languageButton.setFont(ApplicationFonts.getToolbarButtonFont());
      sentencesButton.setFont(ApplicationFonts.getToolbarButtonFont());
      questionsAndAnswersButton
            .setFont(ApplicationFonts.getToolbarButtonFont());
      darkmodeButton.setFont(ApplicationFonts.getToolbarButtonFont());
      
      startButton.setBackground(MainColors.getButtonBackground());
      inputButton.setBackground(MainColors.getButtonBackground());
      vocabularyCardsButton.setBackground(MainColors.getButtonBackground());
      dictionaryButton.setBackground(MainColors.getButtonBackground());
      statisticsButton.setBackground(MainColors.getButtonBackground());
      letterPicturesButton.setBackground(MainColors.getButtonBackground());
      successButton.setBackground(MainColors.getButtonBackground());
      languageButton.setBackground(MainColors.getButtonBackground());
      sentencesButton.setBackground(MainColors.getButtonBackground());
      questionsAndAnswersButton.setBackground(MainColors.getButtonBackground());
      darkmodeButton.setBackground(MainColors.getButtonBackground());
      aboutButton.setBackground(MainColors.getButtonBackground());

      menuBar.add(startButton);
      menuBar.add(inputButton);
      menuBar.add(dictionaryButton);
      menuBar.add(vocabularyCardsButton);
      menuBar.add(letterPicturesButton);
      menuBar.add(statisticsButton);
      menuBar.add(successButton);
      menuBar.add(sentencesButton);
      // menuBar.add(questionsAndAnswersButton);
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

      sentencesButton.addActionListener(_ -> {
         initSentencesContent();
      });

      this.questionsAndAnswersButton.addActionListener(_ -> {
         this.initQuestionsAndAnswersContent();
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
