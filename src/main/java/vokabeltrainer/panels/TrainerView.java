package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import vokabeltrainer.ComponentTitledBorder;
import vokabeltrainer.InfoTextField;
import vokabeltrainer.InputLanguagePanel;
import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.NikudDocument;
import vokabeltrainer.editing.SwedishDocument;
import vokabeltrainer.keyboards.KeyboardGermanStandard;
import vokabeltrainer.keyboards.KeyboardHebrewAllLetters;
import vokabeltrainer.keyboards.KeyboardSwedishStandard;
import vokabeltrainer.keyboards.OneFocusTraversalPolicy;
import vokabeltrainer.panels.letterpicture.LetterPictureWordPanel;
import vokabeltrainer.panels.trainer.HebrewAnswerWordPanel;
import vokabeltrainer.panels.trainer.ImagePanelBlue;
import vokabeltrainer.panels.trainer.ImagePanelError;
import vokabeltrainer.panels.trainer.ImagePanelGreen;
import vokabeltrainer.panels.trainer.ImagePanelStart;
import vokabeltrainer.panels.trainer.Result;
import vokabeltrainer.panels.trainer.TrainerControllerConnector;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.EnterAction;
import vokabeltrainer.table.list.editor.ImageButton;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.LanguageDirection;

public class TrainerView extends JPanel
{
   private static final long serialVersionUID = -6552073033311684589L;

   private LetterPictureWordPanel pictureWordPanelPlene;
   private LetterPictureWordPanel pictureWordPanelDefektiv;
   private JPanel swapPanel;
   private ImagePanelGreen imageFieldGreen;
   private ImagePanelStart imageFieldStart;
   private ImagePanelError imageFieldError;
   private ImagePanelBlue imageFieldBlue;
   private CardLayout cardLayout;
   private JPanel questionPanel;
   private JTextArea questionField;
   private InputLanguagePanel questionFieldLL;
   private JTextPane grammarInfoField;
   private JTextPane additionalInfoField;
   private InfoTextField answerField;
   private JLabel languageDirectionLabel;
   private JCheckBox additionalInfo;
   private JCheckBox grammarInfo;
   private JPanel answerPanel;
   private JButton sendButton;
   private LanguageDirection languageDirection;
   private JPanel feedbackPanel;
   private JButton nextWordButton;
   private JLabel wordsRight;
   private JLabel wordsWrong;
   private JLabel wordsToDo;
   private int wordsRightNumber;
   private int wordsWrongNumber;
   private JButton answerOkay;
   private JButton answerUndecided;
   private JButton answerNotOkay;
   private JButton stopTrainingButton;
   private JButton soundButton;
   private KeyboardHebrewAllLetters keyboardNikud;
   private KeyboardSwedishStandard keyboardSwedish;
   private KeyboardGermanStandard keyboardGerman;
   private TrainerControllerConnector connector;
   private JButton infoStopTrainingButton;
   private JPanel infoStopTrainingPanel;
   private JSlider soundslider;
   private JCheckBox pictureToggleBox;
   private JPanel verticalTrainerPanel;
   private Translator translator;
   private JPanel textFieldPanelWrapper;

   private JButton imageButton = new ImageButton();
   private JButton wordSoundButton = new ImageButton();

   public TrainerView(App app, Common common, Model model, View view, TrainerControllerConnector connector)
   {
      this.connector = connector;
      translator = common.getTranslator();
      this.languageDirection = connector.getLanguageDirection();
      BullsEyeLayout trainerLayout = new BullsEyeLayout(this);
      setLayout(trainerLayout);
      setBackground(app.appColors.getBackground());
      verticalTrainerPanel = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(verticalTrainerPanel);
      verticalTrainerPanel.setLayout(verticalLayout);
      verticalTrainerPanel.setOpaque(false);
      setBorder(BorderFactory.createEmptyBorder());
      initGui(app);
      this.add(verticalTrainerPanel);
      initController(app, common, model, view);
   }

   public void init(App app, Common common, View view)
   {
      initTextField(app, common, view, languageDirection);
      initQuestionPanel(app, view, languageDirection);

      switch (app.settings.getMyWritingDirection())
      {
      case LEFT_TO_RIGHT:
         questionField
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         questionFieldLL
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         break;
      case RIGHT_TO_LEFT:
         questionField
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         questionFieldLL
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         break;
      }

      switch (languageDirection)
      {
      case OWN_TO_HEBREW:
         languageDirectionLabel
               .setText(translator.realisticTranslate(app, Translation.DEUTSCH)
                     + translator.realisticTranslate(app, Translation.HEBRAEISCH));
         answerField
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         break;
      case OWN_TO_SWEDISH:
         languageDirectionLabel
               .setText(translator.realisticTranslate(app, Translation.DEUTSCH)
                     + translator.realisticTranslate(app, Translation.SCHWEDISCH));
         answerField
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         break;
      case OWN_TO_GERMAN:
         languageDirectionLabel
               .setText(translator.realisticTranslate(app, Translation.DEUTSCH)
                     + translator.realisticTranslate(app, Translation.GERMAN));
         answerField
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         break;
      case HEBREW_TO_OWN:
         languageDirectionLabel.setText(
               translator.realisticTranslate(app, Translation.HEBRAEISCH) + " >> "
                     + translator.realisticTranslate(app, Translation.DEUTSCH));
         questionField
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         break;
      case SWEDISH_TO_OWN:
         languageDirectionLabel.setText(
               translator.realisticTranslate(app, Translation.SCHWEDISCH) + " >> "
                     + translator.realisticTranslate(app, Translation.DEUTSCH));
         questionField
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         break;
      case GERMAN_TO_OWN:
         languageDirectionLabel.setText(
               translator.realisticTranslate(app, Translation.GERMAN) + " >> "
                     + translator.realisticTranslate(app, Translation.DEUTSCH));
         questionField
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         break;
      }

      connector.setNextTest(app);

      this.textFieldPanelWrapper.validate();
      this.textFieldPanelWrapper.repaint();
      this.questionPanel.validate();
      this.questionPanel.repaint();
      answerField.setRequestFocusEnabled(true);
   }

   private void initGui(App app)
   {
      verticalTrainerPanel.add(initTextFieldPanel(app));
      verticalTrainerPanel.add(initTopPanel(app));
      pictureWordPanelPlene = new LetterPictureWordPanel();
      pictureWordPanelPlene.setPreferredSize(new Dimension(1200, 110));
      pictureWordPanelPlene.setOpaque(true);
      pictureWordPanelPlene
            .setBackground(app.appColors.getBeigeGold());

      JScrollPane scroller = new JScrollPane(pictureWordPanelPlene);
      scroller.setMinimumSize(new Dimension(1200, 110));
      scroller.setMaximumSize(new Dimension(1200, 110));
      scroller.setBorder(BorderFactory.createEmptyBorder());
      scroller.setOpaque(true);
      scroller.setBackground(app.appColors.getBeigeGold());
      verticalTrainerPanel.add(scroller);

      pictureWordPanelDefektiv = new LetterPictureWordPanel();
      pictureWordPanelDefektiv.setPreferredSize(new Dimension(1200, 110));
      pictureWordPanelDefektiv.setOpaque(true);
      pictureWordPanelDefektiv
            .setBackground(app.appColors.getBeigeGold());

      JScrollPane scroller2 = new JScrollPane(pictureWordPanelDefektiv);
      scroller2.setMinimumSize(new Dimension(1200, 110));
      scroller2.setMaximumSize(new Dimension(1200, 110));
      scroller2.setBorder(BorderFactory.createEmptyBorder());
      scroller2.setOpaque(true);
      scroller2.setBackground(app.appColors.getBeigeGold());

      verticalTrainerPanel.add(scroller2);
   }

   private Component initTextFieldPanel(App app)
   {
      textFieldPanelWrapper = new JPanel();
      BullsEyeLayout wrapperLayout = new BullsEyeLayout(textFieldPanelWrapper);
      textFieldPanelWrapper.setLayout(wrapperLayout);
      textFieldPanelWrapper.setBackground(app.appColors.trainer.getTextBackground());

      return textFieldPanelWrapper;
   }

   private void initTextField(App app, Common common, View view, LanguageDirection languageDirection)
   {
      textFieldPanelWrapper.removeAll();

      questionField = new JTextArea();
      questionField.setFont(
            view.getNimbus().getDefaults().getFont("internationalFont"));
      questionField.setLineWrap(true);
      questionField.setWrapStyleWord(true);
      questionField.setOpaque(true);
      questionField.setBackground(app.appColors.trainer.getTextBackground());
      questionField.setForeground(app.appColors.trainer.getInfoTextForeground());
      questionField.setBorder(
            BorderFactory.createTitledBorder(translator.realisticTranslate(app, 
                  Translation.WIE_LAUTET_DIE_UEBERSETZUNG_DIESES_BEGRIFFES_)));
      questionField.setMinimumSize(new Dimension(600, 160));
      questionField.setMaximumSize(new Dimension(1268, 160));
      questionField.setEditable(false);

      questionFieldLL = new InputLanguagePanel(app, common, Selection.SIMPLE, 160, 10, false,
            this, 1268, app.appColors.trainer.getTextBackground());
      questionFieldLL.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(app.appColors.getLightBlue()),
            translator.realisticTranslate(app, 
                  Translation.WIE_LAUTET_DIE_UEBERSETZUNG_DIESES_BEGRIFFES_)));
      questionFieldLL.setMinimumSize(new Dimension(600, 160));
      questionFieldLL.setMaximumSize(new Dimension(1268, 160));
      questionFieldLL.setEditable(false);
      questionFieldLL.setEnabled(false);

      switch (languageDirection)
      {
      case OWN_TO_SWEDISH:
      case OWN_TO_HEBREW:
      case OWN_TO_GERMAN:
         textFieldPanelWrapper.add(questionField);
         break;
      case HEBREW_TO_OWN:
         questionFieldLL.setLayoutNoKeyboard(Selection.SIMPLE);
         textFieldPanelWrapper.add(questionFieldLL);
         break;
      case SWEDISH_TO_OWN:
         questionFieldLL.setLayoutNoKeyboard(Selection.SWEDISH);
         textFieldPanelWrapper.add(questionFieldLL);
         break;
      case GERMAN_TO_OWN:
         questionFieldLL.setLayoutNoKeyboard(Selection.GERMAN);
         textFieldPanelWrapper.add(questionFieldLL);
         break;
      }
   }

   private Component initTopPanel(App app)
   {
      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.setBackground(app.appColors.getBeigeGold());

      questionPanel = new JPanel();
      questionPanel.setLayout(new TotemLayout(questionPanel, 10));
      questionPanel
            .setBackground(app.appColors.getBeigeGold());

      horizontal.add(initLeftPanel(app));
      horizontal.add(questionPanel);
      horizontal.add(initRightPanel(app));

      return horizontal;
   }

   private JPanel initLeftPanel(App app)
   {
      Font labelFont = app.appFonts.germanBoldFont.deriveFont(15F);

      JPanel verticalLeftPanel = new JPanel();
      verticalLeftPanel.setLayout(new TotemLayout(verticalLeftPanel, 15));
      verticalLeftPanel.setBackground(app.appColors.getGold());

      JPanel choices = new JPanel();
      choices.setLayout(new TrainLayout(choices, 15));
      choices.setBorder(BorderFactory.createEmptyBorder(30, 15, 0, 15));
      choices.setBackground(app.appColors.getGold());
      JPanel choicesLeft = new JPanel();
      choicesLeft.setLayout(new TotemLayout(choicesLeft));
      choicesLeft.setBackground(app.appColors.getGold());
      JPanel choicesRight = new JPanel();
      choicesRight.setLayout(new TotemLayout(choicesRight));
      choicesRight.setBackground(app.appColors.getGold());
      choices.add(choicesLeft);
      choices.add(choicesRight);

      JLabel label3 = new JLabel(
            translator.realisticTranslate(app, Translation.RICHTUNG));
      label3.setFont(labelFont);
      label3.setBackground(app.appColors.getGold());
      label3.setForeground(Color.WHITE);
      choicesLeft.add(label3);

      languageDirectionLabel = new JLabel();
      languageDirectionLabel.setFont(labelFont);
      languageDirectionLabel.setBackground(app.appColors.getGold());
      languageDirectionLabel.setForeground(Color.WHITE);
      choicesRight.add(languageDirectionLabel);

      JPanel numbers = new JPanel();
      numbers.setLayout(new TrainLayout(numbers, 15));
      numbers.setBackground(app.appColors.getGold());
      numbers.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

      JPanel numbersLeft = new JPanel();
      numbersLeft.setLayout(new TotemLayout(numbersLeft));
      numbersLeft.setBackground(app.appColors.getGold());
      JLabel wordsRightLabel = new JLabel(
            translator.realisticTranslate(app, Translation.RICHTIG));
      wordsRightLabel.setBackground(app.appColors.getGold());
      wordsRightLabel.setFont(labelFont);
      wordsRightLabel.setForeground(Color.WHITE);
      JLabel wordsWrongLabel = new JLabel(
            translator.realisticTranslate(app, Translation.FALSCH));
      wordsWrongLabel.setBackground(app.appColors.getGold());
      wordsWrongLabel.setFont(labelFont);
      wordsWrongLabel.setForeground(Color.WHITE);
      JLabel wordsToDoLabel = new JLabel(
            translator.realisticTranslate(app, Translation.ZU_TUN));
      wordsToDoLabel.setBackground(app.appColors.getGold());
      wordsToDoLabel.setFont(labelFont);
      wordsToDoLabel.setForeground(Color.WHITE);
      numbersLeft.add(wordsRightLabel);
      numbersLeft.add(wordsWrongLabel);
      numbersLeft.add(wordsToDoLabel);

      JPanel numbersRight = new JPanel();
      numbersRight.setLayout(new TotemLayout(numbersRight));
      numbersRight.setBackground(app.appColors.getGold());
      wordsRight = new JLabel(String.valueOf(wordsRightNumber));
      wordsRight.setBackground(app.appColors.getGold());
      wordsRight.setForeground(Color.WHITE);
      wordsRight.setFont(labelFont);
      wordsWrong = new JLabel(String.valueOf(wordsWrongNumber));
      wordsWrong.setBackground(app.appColors.getGold());
      wordsWrong.setFont(labelFont);
      wordsWrong.setForeground(Color.WHITE);
      wordsToDo = new JLabel(" ");
      wordsToDo.setBackground(app.appColors.getGold());
      wordsToDo.setFont(labelFont);
      wordsToDo.setForeground(Color.WHITE);
      numbersRight.add(wordsRight);
      numbersRight.add(wordsWrong);
      numbersRight.add(wordsToDo);
      numbers.add(numbersLeft);
      numbers.add(numbersRight);

      JPanel numberFiller = new JPanel(new FlowLayout());
      numberFiller.setBackground(app.appColors.getGold());
      numberFiller.setMinimumSize(new Dimension(60, 30));
      numberFiller.setMaximumSize(new Dimension(280, 60));

      nextWordButton = new JButton(
            translator.realisticTranslate(app, Translation.NAECHSTES_WORT));
      nextWordButton.setIcon(new ImageIcon(app.appImages.getStart()));
      nextWordButton.setEnabled(false);
      nextWordButton.setForeground(app.appColors.trainer.getTextForeground());

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));

      JPanel soundFiller = new JPanel(new FlowLayout());
      soundFiller.setOpaque(true);
      soundFiller.setBackground(app.appColors.getGold());
      soundFiller.setMinimumSize(new Dimension(60, 60));
      soundFiller.setMaximumSize(new Dimension(350, 100));

      soundButton = new JButton(new ImageIcon(app.settings.getSound()));
      soundButton.setBorder(BorderFactory.createEmptyBorder());
      soundButton.setOpaque(false);
      soundButton.setBackground(app.appColors.getTransparent());

      soundFiller.add(soundButton);
      horizontal.add(soundFiller);

      soundslider = new JSlider();
      soundslider.setMinimum(-30);
      soundslider.setMaximum(5);
      soundslider.setValue((int) app.settings.getVolume());
      soundslider.setMajorTickSpacing(5);
      soundslider.setMinorTickSpacing(1);
      soundslider.setPaintTicks(true);
      soundslider.setPaintLabels(true);
      soundslider.setSnapToTicks(true);

      pictureToggleBox = new JCheckBox(
            translator.realisticTranslate(app, Translation.BILDERBUCHSTABEN));
      pictureToggleBox.setSelected(app.settings.isLetterImagesOn());

      infoStopTrainingPanel = new JPanel(new BorderLayout());
      infoStopTrainingPanel.setMinimumSize(new Dimension(150, 40));
      infoStopTrainingPanel.setMaximumSize(new Dimension(280, 40));
      infoStopTrainingPanel.setBackground(app.appColors.getGold());

      infoStopTrainingButton = new JButton(
            new ImageIcon(app.appImages.getInfoButtonIcon()));
      infoStopTrainingButton.setBackground(new Color(0, 0, 0, 0));
      infoStopTrainingButton.setMinimumSize(new Dimension(14, 26));
      infoStopTrainingButton.setMaximumSize(new Dimension(14, 32));
      infoStopTrainingButton.setMargin(new Insets(0, 0, 0, 0));

      infoStopTrainingPanel.add(infoStopTrainingButton, BorderLayout.WEST);

      stopTrainingButton = new JButton(
            translator.realisticTranslate(app, Translation.ABBRECHEN));
      stopTrainingButton.setIcon(new ImageIcon(app.appImages.getStop()));
      stopTrainingButton.setForeground(app.appColors.trainer.getTextForeground());

      verticalLeftPanel.add(choices);
      verticalLeftPanel.add(numbers);
      verticalLeftPanel.add(numberFiller);
      verticalLeftPanel.add(nextWordButton);
      verticalLeftPanel.add(horizontal);
      verticalLeftPanel.add(soundslider);
      verticalLeftPanel.add(pictureToggleBox);
      verticalLeftPanel.add(infoStopTrainingPanel);
      verticalLeftPanel.add(stopTrainingButton);

      return verticalLeftPanel;
   }

   private void initQuestionPanel(App app, View view, LanguageDirection languageDirection)
   {
      questionPanel.removeAll();
      questionPanel
            .setBackground(app.appColors.getBeigeGold());

      JPanel additionalInfoPanel = new JPanel();
      additionalInfoPanel.setLayout(new TrainLayout(additionalInfoPanel, 15));
      additionalInfoPanel
            .setBackground(app.appColors.getBeigeGold());

      additionalInfo = new JCheckBox();
      additionalInfo.setFont(app.appFonts.germanFont.deriveFont(15F));
      additionalInfo.setMinimumSize(
            new Dimension(app.settings.getKeyboardWidth() / 2 - 8, 30));
      additionalInfo.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() / 2 - 7, 30));

      additionalInfoField = new JTextPane();
      additionalInfoField.setBorder(
            new ComponentTitledBorder(additionalInfo, additionalInfoField,
                  new TitledBorder(translator
                        .realisticTranslate(app, Translation.WEITERE_INFORMATIONEN)),
                  35));
      additionalInfoField.setEditable(false);

      additionalInfo.addActionListener(_ -> connector.setAdditionalInfo());

      JScrollPane scrollerAdditionalInfo = new JScrollPane(additionalInfoField);
      scrollerAdditionalInfo.setMinimumSize(
            new Dimension(app.settings.getKeyboardWidth() / 2 - 8, 100));
      scrollerAdditionalInfo.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() / 2 - 7, 100));
      scrollerAdditionalInfo.setBorder(BorderFactory.createEmptyBorder());
      scrollerAdditionalInfo
            .setViewportBorder(BorderFactory.createEmptyBorder());
      scrollerAdditionalInfo.getViewport()
            .setBackground(app.appColors.getTransparent());
      scrollerAdditionalInfo.getViewport().setOpaque(false);

      grammarInfo = new JCheckBox();
      grammarInfo.setFont(app.appFonts.germanFont.deriveFont(15F));
      grammarInfo.setMinimumSize(
            new Dimension(app.settings.getKeyboardWidth() / 2 - 8, 30));
      grammarInfo.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() / 2 - 7, 30));

      grammarInfoField = new JTextPane();
      grammarInfoField.setFont(app.appFonts.germanFont.deriveFont(15F));
      grammarInfoField
            .setBorder(new ComponentTitledBorder(grammarInfo, grammarInfoField,
                  new TitledBorder(
                        translator.realisticTranslate(app, Translation.GRAMMATIK)),
                  35));
      grammarInfoField.setEditable(false);

      grammarInfo.addActionListener(_ -> connector.setGrammarInfo()); // needs
                                                                      // to
                                                                      // be
                                                                      // here!

      JScrollPane scrollerGrammarInfo = new JScrollPane(grammarInfoField);
      scrollerGrammarInfo.setMinimumSize(
            new Dimension(app.settings.getKeyboardWidth() / 2 - 8, 100));
      scrollerGrammarInfo.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() / 2 - 7, 100));
      scrollerGrammarInfo.setBorder(BorderFactory.createEmptyBorder());
      scrollerGrammarInfo.setViewportBorder(BorderFactory.createEmptyBorder());
      scrollerGrammarInfo.getViewport()
            .setBackground(app.appColors.getTransparent());
      scrollerGrammarInfo.getViewport().setOpaque(false);

      additionalInfoPanel.add(scrollerGrammarInfo);
      additionalInfoPanel.add(scrollerAdditionalInfo);

      questionPanel.add(additionalInfoPanel);

      answerPanel = new JPanel();
      answerPanel.setLayout(new TotemLayout(answerPanel));
      answerPanel.setBackground(app.appColors.getBeigeGold());

      questionPanel.add(answerPanel);

      switch (languageDirection)
      {
      case OWN_TO_HEBREW:
         answerPanel.removeAll();
         answerPanel
               .setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 308));
         answerPanel
               .setMaximumSize(new Dimension(app.settings.getKeyboardWidth(), 444));
         answerField = new InfoTextField(app, view,
               translator.realisticTranslate(app, Translation.ANTWORTFELD),
               translator.realisticTranslate(app, Translation.ANTWORTFELD) + ":",
               translator.realisticTranslate(app, 
                     Translation.MIT_DER_HEBRAEISCHEN_TASTATUR),
               translator.realisticTranslate(app, 
                     Translation.BITTE_DIE_ANTWORT_SCHREIBEN));
         answerField.setDocument(new NikudDocument(true));
         answerField
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

         keyboardNikud = new KeyboardHebrewAllLetters(app, answerField,
               new ArrayList<JTextComponent>(), 80, false);

         answerPanel.add(answerField);
         answerPanel.add(keyboardNikud);
         break;
      case OWN_TO_SWEDISH:
         answerPanel.removeAll();
         answerPanel
               .setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 308));
         answerPanel
               .setMaximumSize(new Dimension(app.settings.getKeyboardWidth(), 444));
         answerField = new InfoTextField(app, view,
               translator.realisticTranslate(app, Translation.ANTWORTFELD),
               translator.realisticTranslate(app, Translation.ANTWORTFELD) + ":",
               translator.realisticTranslate(app, 
                     Translation.MIT_DER_SCHWEDISCHEN_TASTATUR),
               translator.realisticTranslate(app, 
                     Translation.BITTE_DIE_ANTWORT_SCHREIBEN));
         answerField.setDocument(new SwedishDocument(true));
         answerField
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

         keyboardSwedish = new KeyboardSwedishStandard(app, answerField,
               new ArrayList<JTextComponent>(), 80);

         answerPanel.add(answerField);
         answerPanel.add(keyboardSwedish.makeRegularKeyboard(app));
         break;
      case OWN_TO_GERMAN:
         answerPanel.removeAll();
         answerPanel
               .setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 308));
         answerPanel
               .setMaximumSize(new Dimension(app.settings.getKeyboardWidth(), 444));
         answerField = new InfoTextField(app, view,
               translator.realisticTranslate(app, Translation.ANTWORTFELD),
               translator.realisticTranslate(app, Translation.ANTWORTFELD) + ":",
               translator.realisticTranslate(app, 
                     Translation.MIT_DER_DEUTSCHEN_TASTATUR),
               translator.realisticTranslate(app, 
                     Translation.BITTE_DIE_ANTWORT_SCHREIBEN));
         answerField.setDocument(new GermanDocument(true));
         answerField
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

         keyboardGerman = new KeyboardGermanStandard(app, answerField,
               new ArrayList<JTextComponent>(), 80);

         answerPanel.add(answerField);
         answerPanel.add(keyboardGerman.makeRegularKeyboard(app));
         break;
      case SWEDISH_TO_OWN:
      case HEBREW_TO_OWN:
      case GERMAN_TO_OWN:
         answerPanel.removeAll();
         answerPanel
               .setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 80));
         answerPanel
               .setMaximumSize(new Dimension(app.settings.getKeyboardWidth(), 80));
         answerField = new InfoTextField(app, view,
               translator.realisticTranslate(app, Translation.ANTWORTFELD),
               translator.realisticTranslate(app, Translation.ANTWORTFELD) + ":",
               translator.realisticTranslate(app, 
                     Translation.SIE_KOENNEN_HIER_DIE_LOESUNG_SCHREIBEN),
               translator.realisticTranslate(app, 
                     Translation.ODER_EINfACH_LAUT_NENNEN_ODER_DENKEN));
         answerField.setDocument(new GermanDocument(true));
         answerField
               .setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 80));
         answerField
               .setMaximumSize(new Dimension(app.settings.getKeyboardWidth(), 80));
         answerPanel.add(answerField);
         break;
      }

      this.setFocusCycleRoot(true);
      FocusTraversalPolicy focusPolicy = new OneFocusTraversalPolicy(
            answerField);
      this.setFocusTraversalPolicy(focusPolicy);
      focusPolicy.getDefaultComponent(null);
      answerField.requestFocusInWindow();

      questionPanel.validate();
      questionPanel.repaint();

      if (LanguageDirection.OWN_TO_HEBREW.equals(languageDirection))
      {
         this.pictureToggleBox.setVisible(true);
      }
      else
      {
         this.pictureToggleBox.setVisible(false);
      }
   }

   private Component initRightPanel(App app)
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setOpaque(true);
      vertical.setBackground(app.appColors.getBeigeGold());

      imageFieldGreen = new ImagePanelGreen();
      imageFieldGreen.setBorder(BorderFactory.createEmptyBorder());
      imageFieldGreen.setMinimumSize(new Dimension(501, 240));
      imageFieldGreen.setMaximumSize(new Dimension(501, 240));
      imageFieldGreen.setBorder(null);

      imageFieldBlue = new ImagePanelBlue();
      imageFieldBlue.setBorder(BorderFactory.createEmptyBorder());
      imageFieldBlue.setMinimumSize(new Dimension(501, 240));
      imageFieldBlue.setMaximumSize(new Dimension(501, 240));
      imageFieldBlue.setBorder(null);

      imageFieldStart = new ImagePanelStart(app);
      imageFieldStart.setBorder(BorderFactory.createEmptyBorder());
      imageFieldStart.setMinimumSize(new Dimension(501, 240));
      imageFieldStart.setMaximumSize(new Dimension(501, 240));
      imageFieldStart.setBorder(null);

      imageFieldError = new ImagePanelError(app);
      imageFieldError.setBorder(BorderFactory.createEmptyBorder());
      imageFieldError.setMinimumSize(new Dimension(501, 240));
      imageFieldError.setMaximumSize(new Dimension(501, 240));
      imageFieldError.setBorder(null);

      cardLayout = new CardLayout();
      swapPanel = new JPanel(cardLayout);
      swapPanel.setMinimumSize(new Dimension(501, 209));
      swapPanel.setMaximumSize(new Dimension(520, 209));
      swapPanel.setBorder(BorderFactory.createEmptyBorder());
      swapPanel.setBackground(app.appColors.getBeigeGold());

      swapPanel.add("START", imageFieldStart);
      swapPanel.add("GREEN", imageFieldGreen);
      swapPanel.add("BLUE", imageFieldBlue);
      swapPanel.add("RED", imageFieldError);
      cardLayout.show(swapPanel, "START");

      switch (languageDirection)
      {
      case OWN_TO_SWEDISH:
      case OWN_TO_HEBREW:
      case OWN_TO_GERMAN:
         sendButton = new JButton(
               translator.realisticTranslate(app, Translation.ANTWORT_ABSENDEN));
         break;
      case HEBREW_TO_OWN:
      case SWEDISH_TO_OWN:
      case GERMAN_TO_OWN:
         sendButton = new JButton(
               translator.realisticTranslate(app, Translation.ANTWORT_ANSCHAUEN));
         break;
      }

      sendButton.setFont(app.appFonts.buttonFont);
      sendButton.setForeground(app.appColors.trainer.getTextForeground());
      sendButton.setIcon(new ImageIcon(app.appImages.getSend()));
      sendButton.setMinimumSize(new Dimension(300, 40));
      sendButton.setMaximumSize(new Dimension(501, 40));
      sendButton.setEnabled(false);

      feedbackPanel = new JPanel();
      feedbackPanel.setLayout(new TotemLayout(feedbackPanel));
      feedbackPanel.setMinimumSize(new Dimension(501, 230));
      feedbackPanel.setMaximumSize(new Dimension(501, 230));
      feedbackPanel.setOpaque(true);
      feedbackPanel
            .setBackground(app.appColors.getBeigeGold());

      vertical.add(swapPanel);
      vertical.add(sendButton);
      vertical.add(feedbackPanel);
      return vertical;
   }

   private void initController(App app, Common common, Model model, View view)
   {
      sendButton.addActionListener(_ -> connector.send(app, common, model, view));

      nextWordButton.addActionListener(_ -> nextWord(app));

      infoStopTrainingButton.addActionListener(
            _ -> JOptionPane.showMessageDialog(infoStopTrainingPanel, "",
                  app.settings.getWindowTitle(), JOptionPane.INFORMATION_MESSAGE,
                  new ImageIcon(TextImage.make(app,
                        translator.realisticTranslate(app, 
                              Translation.WENN_SIE_AUF_ABBRECHEN_DRUECKEN_),
                        translator.realisticTranslate(app, 
                              Translation.WERDEN_ALLE_GELERNTEN_ANTWORTEN),
                        translator
                              .realisticTranslate(app, Translation.GESPEICHERT_)))));

      infoStopTrainingButton.addMouseListener(new MouseListener()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {

         }

         @Override
         public void mousePressed(MouseEvent e)
         {

         }

         @Override
         public void mouseReleased(MouseEvent e)
         {

         }

         @Override
         public void mouseEntered(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         }

      });

      stopTrainingButton.addActionListener(_ -> {
         connector.stopTraining(app, common, model, view, false);
      });

      this.soundButton.addActionListener(_ -> connector.toggleSound(app));

      soundslider.addChangeListener(_ -> {
         if (!soundslider.getValueIsAdjusting())
         {
            app.settings.setVolume(soundslider.getValue());
         }
      });

      pictureToggleBox.addActionListener(_ -> connector.toggleLetterPictures(app));

      initControllerImageButton(app, common, model, view);
      initControllerWordButton();
   }

   private void initControllerWordButton()
   {
      wordSoundButton.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent event)
         {
            // not implemented, no Sounds available yet
            // SoundAction sound = new SoundAction();
         }
      });
   }

   private void initControllerImageButton(App app, Common common, Model model, View view)
   {
      imageButton.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent event)
         {
            EnterAction images = new EnterAction(app, common, model, view);
            images.showEditorPicture(app, common, model, view, connector.getCurrentExpression(), false);
         }
      });
   }

   public void setHtoDanswerButtons(App app, Common common, Model model, View view)
   {
      answerOkay = new JButton(
            new ImageIcon(app.appImages.getAnswerOkay()));
      answerOkay.addActionListener(_ -> {
         connector.resultHtoDOkay(app, common, model, view);
      });
      answerOkay.setMinimumSize(new Dimension(150, 110));
      answerOkay.setMaximumSize(new Dimension(150, 110));
      answerOkay.setPreferredSize(new Dimension(150, 110));
      answerOkay.setSize(150, 110);

      answerUndecided = new JButton(
            new ImageIcon(app.appImages.getAnswerUndecided()));
      answerUndecided.addActionListener(_ -> {
         connector.resultHtoDUndecided(app, common, model, view);
      });
      answerUndecided.setMinimumSize(new Dimension(150, 110));
      answerUndecided.setMaximumSize(new Dimension(150, 110));
      answerUndecided.setPreferredSize(new Dimension(150, 110));
      answerUndecided.setSize(150, 110);

      answerNotOkay = new JButton(
            new ImageIcon(app.appImages.getAnswerNotOkay()));
      answerNotOkay.addActionListener(_ -> {
         connector.resultHtoDFalse(app, common, model, view);
      });
      answerNotOkay.setMinimumSize(new Dimension(150, 110));
      answerNotOkay.setMaximumSize(new Dimension(150, 110));
      answerNotOkay.setPreferredSize(new Dimension(150, 110));
      answerNotOkay.setSize(150, 110);
   }

   public void enableHtoDAnswerButtons(boolean b)
   {
      this.answerOkay.setEnabled(b);
      this.answerUndecided.setEnabled(b);
      this.answerNotOkay.setEnabled(b);
   }

   public void prepareHtoDFeedbackPanel(App app, Common common, Model model, View view)
   {
      JPanel outerVertical = new JPanel();
      outerVertical.setLayout(new TotemLayout(outerVertical));

      JPanel outerHorizontal = new JPanel();
      outerHorizontal.setLayout(new TrainLayout(outerHorizontal));

      JPanel answerPanel1 = new JPanel();
      answerPanel1.setLayout(new TotemLayout(answerPanel1));
      answerPanel1.setBackground(app.appColors.trainer.getBackground());
      JLabel correctAnswer = new JLabel(translator
            .realisticTranslate(app, Translation.DIE_RICHTIGE_ANTWORT_LAUTET_));
      correctAnswer.setFont(app.appFonts.germanFont.deriveFont(16F));
      correctAnswer.setForeground(app.appColors.trainer.getTextForeground());
      correctAnswer.setMinimumSize(new Dimension(300, 30));
      correctAnswer.setMaximumSize(new Dimension(510, 30));
      JLabel correctAnswer2 = new JLabel(
            connector.getCurrentExpression().getOwnLanguage());
      correctAnswer2.setFont(app.appFonts.germanFont.deriveFont(20F));
      correctAnswer2.setMinimumSize(new Dimension(300, 30));
      correctAnswer2.setMaximumSize(new Dimension(510, 30));
      JTextField correctAnswer3 = new JTextField(
            connector.getCurrentExpression().getGrammarInfo(false));
      correctAnswer3.setFont(app.appFonts.germanFont.deriveFont(16F));
      correctAnswer3.setEditable(false);
      correctAnswer3.setBackground(app.appColors.getTransparent());
      correctAnswer3.setOpaque(false);
      correctAnswer3.setBorder(BorderFactory.createEmptyBorder());

      imageButton = new ImageButton(app.appImages.getIcon_eye());
      imageButton.setMinimumSize(new Dimension(60, 60));
      imageButton.setMaximumSize(new Dimension(60, 60));
      initControllerImageButton(app, common, model, view);

      wordSoundButton = new ImageButton(app.appImages.getIcon_notes());
      wordSoundButton.setMinimumSize(new Dimension(60, 60));
      wordSoundButton.setMaximumSize(new Dimension(60, 60));
      initControllerWordButton();

      answerPanel1.add(correctAnswer);
      answerPanel1.add(correctAnswer2);
      answerPanel1.add(correctAnswer3);

      JPanel answerPanel2 = new JPanel();
      answerPanel2.setLayout(new GridLayout(1, 3));
      answerPanel2.setMinimumSize(new Dimension(501, 100));
      answerPanel2.setMaximumSize(new Dimension(501, 100));
      answerPanel2.setBackground(app.appColors.trainer.getPanelBackground());

      setHtoDanswerButtons(app, common, model, view);
      answerPanel2.add(answerOkay);
      answerPanel2.add(answerUndecided);
      answerPanel2.add(answerNotOkay);

      enableHtoDAnswerButtons(true);

      outerHorizontal.add(answerPanel1);
      outerHorizontal.add(imageButton);
      outerHorizontal.add(wordSoundButton);

      outerVertical.add(outerHorizontal);
      outerVertical.add(answerPanel2);

      feedbackPanel.add(outerVertical);
   }

   public void nextWord(App app)
   {
      nextWordButton.setEnabled(false);
      cardLayout.show(swapPanel, "START");
      answerField.setText("");
      feedbackPanel.removeAll();
      feedbackPanel.validate();
      feedbackPanel.repaint();
      additionalInfo.setSelected(false);
      additionalInfoField.setText("");
      additionalInfoField.validate();
      additionalInfoField.repaint();
      grammarInfo.setSelected(false);
      grammarInfoField.setText("");
      grammarInfoField.validate();
      grammarInfoField.repaint();
      pictureWordPanelPlene.removeAll();
      pictureWordPanelDefektiv.removeAll();

      connector.setNextTest(app);
      answerField.grabFocus();
      answerField.requestFocusInWindow();
   }

   public JTextPane getAdditionalInfoField()
   {
      return additionalInfoField;
   }

   public JCheckBox getAdditionalInfo()
   {
      return additionalInfo;
   }

   public JCheckBox getGrammarInfo()
   {
      return grammarInfo;
   }

   public JButton getAnswerOkay()
   {
      return answerOkay;
   }

   public JButton getAnswerUndecided()
   {
      return answerUndecided;
   }

   public JButton getAnswerNotOkay()
   {
      return answerNotOkay;
   }

   public JLabel getWordsToDo()
   {
      return wordsToDo;
   }

   public JTextArea getQuestionFieldGerman()
   {
      return questionField;
   }

   public InputLanguagePanel getQuestionFieldLL()
   {
      return questionFieldLL;
   }

   public LetterPictureWordPanel getWordPanelPlene()
   {
      return pictureWordPanelPlene;
   }

   public LetterPictureWordPanel getWordPanelDefektiv()
   {
      return pictureWordPanelDefektiv;
   }

   public InfoTextField getAnswerField()
   {
      return answerField;
   }

   public JPanel getFeedbackPanel()
   {
      return feedbackPanel;
   }

   public JLabel getWordsRight()
   {
      return wordsRight;
   }

   public void disableSendButton()
   {
      sendButton.setEnabled(false);
   }

   public void enableSendButton()
   {
      sendButton.setEnabled(true);
   }

   public CardLayout getCardLayout()
   {
      return cardLayout;
   }

   public JButton getNextWordButton()
   {
      return nextWordButton;
   }

   public JButton getSoundButton()
   {
      return soundButton;
   }

   public JCheckBox getPictureToggleBox()
   {
      return pictureToggleBox;
   }

   public JTextPane getGrammarInfoField()
   {
      return grammarInfoField;
   }

   public void showResultBlue(App app)
   {
      ImagePanelBlue.setNextImage(app);
      cardLayout.show(swapPanel, "BLUE");
   }

   public void showResultGreen(App app)
   {
      ImagePanelGreen.setNextImage(app);
      cardLayout.show(swapPanel, "GREEN");
      wordsRightNumber++;
      wordsRight.setText(String.valueOf(wordsRightNumber));
      wordsToDo.setText(
            String.valueOf(connector.getExpressionsToBeTested().size()));
   }

   public void showResultRed()
   {
      cardLayout.show(swapPanel, "RED");
      wordsWrongNumber++;
      wordsWrong.setText(String.valueOf(wordsWrongNumber));
      wordsToDo.setText(
            String.valueOf(connector.getExpressionsToBeTested().size()));
   }

   public void prepareDtoNikudFeedbackPanel(App app, Common common, Result result)
   {
      HebrewAnswerWordPanel answerPanel = new HebrewAnswerWordPanel(app, result);

      JScrollPane scrollPane = new JScrollPane(answerPanel);
      scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      scrollPane.setBorder(BorderFactory.createEmptyBorder());
      scrollPane.setMinimumSize(new Dimension(501, 120));
      scrollPane.setMaximumSize(new Dimension(501, 120));
      scrollPane.setOpaque(true);
      scrollPane.getViewport()
            .setBackground(app.appColors.trainer.getPanelBackground());

      JPanel fillerAnswerPanel = new JPanel();
      TotemLayout fillerAnswerLayout = new TotemLayout(fillerAnswerPanel);
      fillerAnswerPanel.setLayout(fillerAnswerLayout);
      fillerAnswerPanel.setMinimumSize(new Dimension(501, 1));
      fillerAnswerPanel.setMaximumSize(new Dimension(501, 100));
      fillerAnswerPanel.setOpaque(true);
      fillerAnswerPanel.setBackground(app.appColors.trainer.getPanelBackground());

      feedbackPanel.add(scrollPane);
      feedbackPanel.add(fillerAnswerPanel);
      if (result.getExpression().getLL().isSimpleHebrew())
      {
         pictureWordPanelPlene
               .displayNikudWord(app, common, result.getExpression().getLL().getHebrew());
      }
      else if (result.getExpression().getLL().isSwedish())
      {
         // nothing
      }
      else if (result.getExpression().getLL().isGerman())
      {
         // nothing
      }
      else
      {
         pictureWordPanelPlene.displayNikudWord(app, common,
               result.getExpression().getLL().getHebrewPlene());
         pictureWordPanelDefektiv.displayNikudWord(app, common,
               result.getExpression().getLL().getHebrewDefektiv());
      }
   }
}
