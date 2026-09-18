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
import javax.swing.SwingUtilities;

import vokabeltrainer.TextImage;
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
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

   private Translator translator;

   public MainView(App app, Common common)
   {
      this.translator = common.getTranslator();
      initToolBar(app);
   }
   
   public void initContent(App app, Common common, Model model, View view)
   {
      setLayout(new BorderLayout());
      this.setOpaque(false);
      this.setBackground(app.appColors.getTransparent());
      app.readColorModes();
      view.setUI(app, common);

      startPanel = new StartPanel(app, common, model, view);
      inputPanel = new InputPanel(app, common, model, view);
      dictionaryPanel = new DictionaryController(app, common, model, view).getDictionaryPanel();
      letterPicturesPanel = new AlefbetPanel(app, common);
      statisticsPanel = new StatisticsPanel(app, common);
      settingsPanel = new SettingsPanel(app, common, model, view);
      successPanel = new SuccessPanel(app, common, model, view);
      activeComponent = startPanel;

      add(activeComponent);
   }
   
   private void resetMenuBar(View view)
   {
      SwingUtilities.invokeLater(() -> {
         view.getjFrame()
               .setJMenuBar(getMenuBar());
         view.getjFrame().validate();
         view.getjFrame().repaint();
      });
   }

   private void initLanguageContent(App app, Common common, Model model, View view)
   {
      this.removeAll();

      initLanguageToolBar(app);
      languagePanel = new TranslationPanel(app, common);
      add(languagePanel);
      initBackController(app, common, model, view);

      resetMenuBar(view);
      this.validate();
      this.repaint();
   }

   private void initColormodeContent(App app, Common common, Model model, View view)
   {
      this.removeAll();

      initColorToolBar(app);
      ColorPanel colorPanel = new ColorPanel(app, common, view);
      add(colorPanel);
      initBackController(app, common, model, view);

      resetMenuBar(view);
      this.validate();
      this.repaint();

   }

   private void initBackController(App app, Common common, Model model, View view)
   {
      backButton.addActionListener(_ -> {
         this.removeAll();
         initContent(app, common, model, view);
         initToolBar(app);
         initController(app, common, model, view);
         activeComponent = startPanel;
         add(activeComponent);
         resetMenuBar(view);
         view.getjFrame().validate();
         view.getjFrame().repaint();
      });
   }

   private void initLanguageToolBar(App app)
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(app.appColors.getLightGold());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200, 80));
      menuBar.setMaximumSize(new Dimension(6000, 80));

      backButton = new JButton(new ImageIcon(app.appImages.getBack()));

      menuBar.add(backButton);
   }

   private void initColorToolBar(App app)
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(app.appColors.getLightGold());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200, 80));
      menuBar.setMaximumSize(new Dimension(6000, 80));

      backButton = new JButton(new ImageIcon(app.appImages.getBack()));

      menuBar.add(backButton);

   }

   public void initToolBar(App app)
   {
      menuBar = new JMenuBar();
      menuBar.setOpaque(true);
      menuBar.setBackground(app.appColors.main.getToolbarBackground());
      menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      menuBar.setMinimumSize(new Dimension(1200, 80));
      menuBar.setMaximumSize(new Dimension(6000, 80));

      startButton = new JButton(
            translator.realisticTranslate(app, Translation.DATEN),
            new ImageIcon(app.appImages.getLogoFolder()));
      inputButton = new JButton(
            translator.realisticTranslate(app, Translation.EINGABE));
      vocabularyCardsButton = new JButton(
            translator.realisticTranslate(app, Translation.VOKABELN_ABFRAGEN));
      dictionaryButton = new JButton(
            translator.realisticTranslate(app, Translation.WOERTERBUCH));
      statisticsButton = new JButton(
            translator.realisticTranslate(app, Translation.TRAININGSUEBERSICHT));
      aboutButton = new JButton(new ImageIcon(app.appImages.getLogo24()));
      letterPicturesButton = new JButton(
            translator.realisticTranslate(app, Translation.ALEFBET));
      successButton = new JButton(
            translator.realisticTranslate(app, Translation.KARTEIKASTEN));
      languageButton = new JButton(new ImageIcon(app.appImages.getL18n()));
      darkmodeButton = new JButton(
            new ImageIcon(app.appImages.getDarkmode()));

      startButton.setFont(app.appFonts.toolbarButtonFont);
      inputButton.setFont(app.appFonts.toolbarButtonFont);
      vocabularyCardsButton.setFont(app.appFonts.toolbarButtonFont);
      dictionaryButton.setFont(app.appFonts.toolbarButtonFont);
      statisticsButton.setFont(app.appFonts.toolbarButtonFont);
      letterPicturesButton.setFont(app.appFonts.toolbarButtonFont);
      successButton.setFont(app.appFonts.toolbarButtonFont);
      languageButton.setFont(app.appFonts.toolbarButtonFont);
      darkmodeButton.setFont(app.appFonts.toolbarButtonFont);

      startButton.setBackground(app.appColors.main.getButtonBackground());
      inputButton.setBackground(app.appColors.main.getButtonBackground());
      vocabularyCardsButton.setBackground(app.appColors.main.getButtonBackground());
      dictionaryButton.setBackground(app.appColors.main.getButtonBackground());
      statisticsButton.setBackground(app.appColors.main.getButtonBackground());
      letterPicturesButton.setBackground(app.appColors.main.getButtonBackground());
      successButton.setBackground(app.appColors.main.getButtonBackground());
      languageButton.setBackground(app.appColors.main.getButtonBackground());
      darkmodeButton.setBackground(app.appColors.main.getButtonBackground());
      aboutButton.setBackground(app.appColors.main.getButtonBackground());

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

   public void initController(App app, Common common, Model model, View view)
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
         inputPanel.reset(app, common, model);
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
         dictionaryPanel.setValues(app, common, model, view);
         add(activeComponent);
         validate();
         repaint();
      });

      vocabularyCardsButton.addActionListener(_ -> {

         if (app.settings.isSchabbat_modus() && common.isSchabbat())
         {
            JOptionPane.showMessageDialog(this, "", app.settings.getWindowTitle(),
                  JOptionPane.INFORMATION_MESSAGE,
                  new ImageIcon(TextImage.make(app,
                        translator
                              .realisticTranslate(app, Translation.ES_IST_SCHABBAT),
                        translator.realisticTranslate(app, 
                              Translation.DAS_TRAINING_IST_ABGESCHALTET))));
            return;
         }

         model.data.determineReloadDatabases(app, common, view);
         StartTrainingView dialog = new StartTrainingController(app, common, model, view)
               .getStartTrainingView();
         dialog.setLocationRelativeTo(null);
         dialog.setVisible(true);

         if (dialog.isTraining())
         {
            if (dialog.getNewExpressions().isEmpty()
                  && (dialog.getOldExpressions() == null
                        || dialog.getOldExpressions().isEmpty()))
            {
               this.showNoWordsForTraining(app, view);
               return;
            }

            if (activeComponent != null)
            {
               remove(activeComponent);
            }

            TrainerView trainerPanel = new TrainerController(app, common, model, view,
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
         model.data.determineReloadDatabases(app, common, view);
         moveToStatisticsPanel(app, common, model);
      });

      successButton.addActionListener(_ -> {
         model.data.determineReloadDatabases(app, common, view);
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
         settingsPanel.setValues(app);
         add(activeComponent);
         validate();
         repaint();
      });

      languageButton.addActionListener(_ -> {
         initLanguageContent(app, common, model, view);
      });

      darkmodeButton.addActionListener(_ -> {
         initColormodeContent(app, common, model, view);
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

   public void moveToStatisticsPanel(App app, Common common, Model model)
   {
      if (activeComponent != null)
      {
         remove(activeComponent);
      }
      statisticsPanel.setValues(app, common, model);
      activeComponent = statisticsPanel;
      add(activeComponent);
      validate();
      repaint();
   }

   private void showNoWordsForTraining(App app, View view)
   {
      JOptionPane.showMessageDialog(view.getjFrame(), "",
            app.settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
            new ImageIcon(TextImage.make(app,
                  translator.realisticTranslate(app, 
                        Translation.KEINE_WORTE_ZUM_UEBEN_AUSGEWAEHLT),
                  translator.realisticTranslate(app, 
                        Translation.BITTE_NEUE_WORTE_DURCH_EINGABE_EINER_ANZAHL),
                  translator.realisticTranslate(app, 
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
