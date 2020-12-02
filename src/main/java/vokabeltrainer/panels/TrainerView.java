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
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.InfoTextField;
import vokabeltrainer.KeyboardHebrew;
import vokabeltrainer.KeyboardHebrewSimple;
import vokabeltrainer.OneFocusTraversalPolicy;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.HebrewDocument;
import vokabeltrainer.panels.letterpicture.LetterPictureWordPanel;
import vokabeltrainer.panels.trainer.HebrewAnswerWordPanel;
import vokabeltrainer.panels.trainer.ImagePanelBlue;
import vokabeltrainer.panels.trainer.ImagePanelError;
import vokabeltrainer.panels.trainer.ImagePanelGreen;
import vokabeltrainer.panels.trainer.ImagePanelStart;
import vokabeltrainer.panels.trainer.Result;
import vokabeltrainer.panels.trainer.TrainerControllerConnector;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Language;

public class TrainerView extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -6552073033311684589L;

   private LetterPictureWordPanel wordPanel;
   private JPanel swapPanel;
   private ImagePanelGreen imageFieldGreen;
   private ImagePanelStart imageFieldStart;
   private ImagePanelError imageFieldError;
   private ImagePanelBlue imageFieldBlue;
   private CardLayout cardLayout;
   private JPanel questionPanel;
   private JTextField questionField;
   private JTextField additionalInfoField;
   private JTextField transcriptionField;
   private InfoTextField answerField;
   private JLabel languageDirectionLabel;
   private JCheckBox additionalInfo;
   private JCheckBox transcription;
   private JPanel answerPanel;
   private JButton sendButton;
   private Language languageDirection;
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

   private KeyboardHebrew keyboard;
   private KeyboardHebrewSimple simpleKeyboard;
   private CardLayout keyboardCardLayout;
   private JPanel keyboardSwapPanel;
   private JCheckBox keyboardHints;

   private TrainerControllerConnector connector;

   private JButton infoStopTrainingButton;

   private JPanel infoStopTrainingPanel;

   private JSlider soundslider;

   public TrainerView(TrainerControllerConnector connector)
   {
      this.connector = connector;
      this.languageDirection = connector.getLanguageDirection();
      setLayout(new TotemLayout(this));
      setBorder(BorderFactory.createEmptyBorder());
      setOpaque(false);
      initGui();
      initController();
   }

   public void init()
   {
      languageDirectionLabel.setText(
            languageDirection.equals(Language.GERMAN) ? "Deutsch >> Hebräisch"
                  : "Hebräisch >> Deutsch");

      initQuestionPanel(languageDirection);

      connector.setNextTest();

      this.questionPanel.validate();
      this.questionPanel.repaint();
      answerField.setRequestFocusEnabled(true);
   }

   private void initGui()
   {
      initTopPanel();
      initPictureWordPanel();
   }

   private void initTopPanel()
   {
      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 15));

      questionPanel = new JPanel();
      questionPanel.setLayout(new TotemLayout(questionPanel, 10));
      questionPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

      horizontal.add(initLeftPanel());
      horizontal.add(questionPanel);
      horizontal.add(initRightPanel());

      this.add(horizontal);
   }

   private JPanel initLeftPanel()
   {
      Font labelFont = Main.getGermanBoldFont(15F);

      JPanel verticalLeftPanel = new JPanel();
      verticalLeftPanel.setLayout(new TotemLayout(verticalLeftPanel, 15));
      verticalLeftPanel.setBackground(Settings.getGold());

      JPanel choices = new JPanel();
      choices.setLayout(new TrainLayout(choices, 15));
      choices.setBorder(BorderFactory.createEmptyBorder(30, 15, 0, 15));
      choices.setBackground(Settings.getGold());
      JPanel choicesLeft = new JPanel();
      choicesLeft.setLayout(new TotemLayout(choicesLeft));
      choicesLeft.setBackground(Settings.getGold());
      JPanel choicesRight = new JPanel();
      choicesRight.setLayout(new TotemLayout(choicesRight));
      choicesRight.setBackground(Settings.getGold());
      choices.add(choicesLeft);
      choices.add(choicesRight);

      JLabel label3 = new JLabel("RICHTUNG");
      label3.setFont(labelFont);
      label3.setBackground(Settings.getGold());
      label3.setForeground(Color.WHITE);
      choicesLeft.add(label3);

      languageDirectionLabel = new JLabel();
      languageDirectionLabel.setFont(labelFont);
      languageDirectionLabel.setBackground(Settings.getGold());
      languageDirectionLabel.setForeground(Color.WHITE);
      choicesRight.add(languageDirectionLabel);

      JPanel showOptions = new JPanel();
      showOptions.setLayout(new TotemLayout(showOptions, 15));
      showOptions.setBackground(Settings.getGold());
      showOptions.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
      additionalInfo = new JCheckBox("weitere Informationen anzeigen");
      additionalInfo.setFont(labelFont);
      additionalInfo.setForeground(Color.WHITE);
      transcription = new JCheckBox("Lautschrift anzeigen");
      transcription.setFont(labelFont);
      transcription.setForeground(Color.WHITE);
      showOptions.add(additionalInfo);
      showOptions.add(transcription);

      JPanel numbers = new JPanel();
      numbers.setLayout(new TrainLayout(numbers, 15));
      numbers.setBackground(Settings.getGold());
      numbers.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
      JPanel numbersLeft = new JPanel();
      numbersLeft.setLayout(new TotemLayout(numbersLeft));
      numbersLeft.setBackground(Settings.getGold());
      JLabel wordsRightLabel = new JLabel("RICHTIG");
      wordsRightLabel.setBackground(Settings.getGold());
      wordsRightLabel.setFont(labelFont);
      wordsRightLabel.setForeground(Color.WHITE);
      JLabel wordsWrongLabel = new JLabel("FALSCH");
      wordsWrongLabel.setBackground(Settings.getGold());
      wordsWrongLabel.setFont(labelFont);
      wordsWrongLabel.setForeground(Color.WHITE);
      JLabel wordsToDoLabel = new JLabel("ZU TUN");
      wordsToDoLabel.setBackground(Settings.getGold());
      wordsToDoLabel.setFont(labelFont);
      wordsToDoLabel.setForeground(Color.WHITE);
      numbersLeft.add(wordsRightLabel);
      numbersLeft.add(wordsWrongLabel);
      numbersLeft.add(wordsToDoLabel);
      JPanel numbersRight = new JPanel();
      numbersRight.setLayout(new TotemLayout(numbersRight));
      numbersRight.setBackground(Settings.getGold());
      wordsRight = new JLabel(String.valueOf(wordsRightNumber));
      wordsRight.setBackground(Settings.getGold());
      wordsRight.setForeground(Color.WHITE);
      wordsRight.setFont(labelFont);
      wordsWrong = new JLabel(String.valueOf(wordsWrongNumber));
      wordsWrong.setBackground(Settings.getGold());
      wordsWrong.setFont(labelFont);
      wordsWrong.setForeground(Color.WHITE);
      wordsToDo = new JLabel(" ");
      wordsToDo.setBackground(Settings.getGold());
      wordsToDo.setFont(labelFont);
      wordsToDo.setForeground(Color.WHITE);
      numbersRight.add(wordsRight);
      numbersRight.add(wordsWrong);
      numbersRight.add(wordsToDo);
      numbers.add(numbersLeft);
      numbers.add(numbersRight);

      nextWordButton = new JButton("nächstes Wort");
      nextWordButton.setIcon(new ImageIcon(ApplicationImages.getStart()));
      nextWordButton.setEnabled(false);

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));

      JPanel soundFiller = new JPanel(new FlowLayout());
      soundFiller.setBackground(Settings.getGold());
      soundFiller.setMinimumSize(new Dimension(60, 60));
      soundFiller.setMaximumSize(new Dimension(280, 100));

      soundButton = new JButton(new ImageIcon(Settings.getSound()));
      soundButton.setBorder(BorderFactory.createEmptyBorder());
      soundButton.setOpaque(false);
      soundButton.setBackground(new Color(0, 0, 0, 0));

      soundFiller.add(soundButton);
      horizontal.add(soundFiller);

      soundslider = new JSlider();
      soundslider.setMinimum(-30);
      soundslider.setMaximum(5);
      soundslider.setValue((int)Settings.getVolume());
      soundslider.setMajorTickSpacing(5);
      soundslider.setMinorTickSpacing(1);
      soundslider.setPaintTicks(true);
      soundslider.setPaintLabels(true);
      soundslider.setSnapToTicks(true);

      if (Language.GERMAN.equals(languageDirection))
      {
         keyboardHints = new JCheckBox("Tastatur Beschriftung");
         keyboardHints.setFont(labelFont);
         keyboardHints.setForeground(Color.WHITE);
         keyboardHints.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
      }

      infoStopTrainingPanel = new JPanel(new BorderLayout());
      infoStopTrainingPanel.setMinimumSize(new Dimension(150, 40));
      infoStopTrainingPanel.setMinimumSize(new Dimension(280, 40));
      infoStopTrainingPanel.setBackground(Settings.getGold());

      infoStopTrainingButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      infoStopTrainingButton.setBackground(new Color(0, 0, 0, 0));
      infoStopTrainingButton.setMinimumSize(new Dimension(14, 26));
      infoStopTrainingButton.setMaximumSize(new Dimension(14, 32));
      infoStopTrainingButton.setMargin(new Insets(0, 0, 0, 0));

      infoStopTrainingPanel.add(infoStopTrainingButton, BorderLayout.WEST);

      stopTrainingButton = new JButton("abbrechen");
      stopTrainingButton.setIcon(new ImageIcon(ApplicationImages.getStop()));

      verticalLeftPanel.add(choices);
      verticalLeftPanel.add(showOptions);
      verticalLeftPanel.add(numbers);
      verticalLeftPanel.add(nextWordButton);
      verticalLeftPanel.add(horizontal);
      verticalLeftPanel.add(soundslider);
      if (Language.GERMAN.equals(languageDirection))
      {
         verticalLeftPanel.add(keyboardHints);
      }
      verticalLeftPanel.add(infoStopTrainingPanel);
      verticalLeftPanel.add(stopTrainingButton);

      return verticalLeftPanel;
   }

   private void initPictureWordPanel()
   {
      wordPanel = new LetterPictureWordPanel();
      this.add(wordPanel);
   }

   private void initQuestionPanel(Language languageDirection)
   {
      questionPanel.removeAll();
      if (Language.GERMAN.equals(languageDirection))
      {
         questionField = new JTextField();
         questionField.setBackground(Settings.getLightBlue());
         questionField.setFont(Main.getGermanFont(20F));
         questionField.setBorder(BorderFactory.createTitledBorder(
               BorderFactory.createEmptyBorder(),
               "Wie lautet die Übersetzung dieses Begriffes?"));
         questionField
               .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 70));
         questionField
               .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 70));
         questionField.setEditable(false);
      }
      else
      {
         questionField = new JTextField();
         questionField
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         questionField.setBackground(Settings.getLightBlue());
         questionField.setFont(Main.getHebrewFont(20F));
         questionField.setBorder(BorderFactory.createTitledBorder(
               "Wie lautet die Übersetzung dieses Begriffes?"));
         questionField
               .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 70));
         questionField
               .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 70));
         questionField.setEditable(false);
      }

      additionalInfoField = new JTextField();
      additionalInfoField.setFont(Main.getGermanFont(15F));
      additionalInfoField.setBackground(Settings.getTexturedBackgroundColor());
      additionalInfoField.setBorder(
            BorderFactory.createTitledBorder("weitere Informationen"));
      additionalInfoField
            .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 55));
      additionalInfoField
            .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 55));
      additionalInfoField.setEditable(false);

      transcriptionField = new JTextField();
      transcriptionField.setFont(Main.getGermanFont(15F));
      transcriptionField.setBackground(Settings.getTexturedBackgroundColor());
      transcriptionField
            .setBorder(BorderFactory.createTitledBorder("Lautschrift"));
      transcriptionField
            .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 55));
      transcriptionField
            .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 55));
      transcriptionField.setEditable(false);

      questionPanel.add(questionField);
      questionPanel.add(additionalInfoField);
      questionPanel.add(transcriptionField);

      answerPanel = new JPanel();
      answerPanel.setLayout(new TotemLayout(answerPanel));

      questionPanel.add(answerPanel);

      if (Language.GERMAN.equals(languageDirection))
      {
         answerPanel.removeAll();
         answerPanel
               .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 308));
         answerPanel
               .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 308));
         answerField = new InfoTextField("Antwortfeld", "Antwortfeld:",
               "Mit der hebräischen Tastatur", "bitte die Antwort schreiben.");
         answerField.setDocument(new HebrewDocument(true));
         answerField
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

         this.setFocusCycleRoot(true);
         FocusTraversalPolicy focusPolicy = new OneFocusTraversalPolicy(
               answerField);
         this.setFocusTraversalPolicy(focusPolicy);
         focusPolicy.getDefaultComponent(null);
         answerField.requestFocusInWindow();

         keyboard = new KeyboardHebrew(answerField,
               new ArrayList<JTextComponent>(), 80, false);
         simpleKeyboard = new KeyboardHebrewSimple(answerField,
               new ArrayList<JTextComponent>(), 80);

         keyboardCardLayout = new CardLayout();
         keyboardSwapPanel = new JPanel(this.keyboardCardLayout);
         keyboardSwapPanel.add("HINTS", keyboard);
         keyboardSwapPanel.add("BLANK", simpleKeyboard);

         answerPanel.add(answerField);
         answerPanel.add(keyboardSwapPanel);
         keyboardCardLayout.show(keyboardSwapPanel, "BLANK");
      }
      else
      {
         answerPanel.removeAll();
         answerPanel
               .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerPanel
               .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerField = new InfoTextField("Antwortfeld", "Antwortfeld:",
               "Sie können hier die Lösung schreiben",
               "oder einfach laut nennen oder denken.");
         answerField.setDocument(new GermanDocument(true));
         answerField.setFont(Main.getGermanFont(20F));
         answerField
               .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerField
               .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerPanel.add(answerField);
      }
      questionPanel.validate();
      questionPanel.repaint();
   }

   private Component initRightPanel()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 15));

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

      imageFieldStart = new ImagePanelStart();
      imageFieldStart.setBorder(BorderFactory.createEmptyBorder());
      imageFieldStart.setMinimumSize(new Dimension(501, 240));
      imageFieldStart.setMaximumSize(new Dimension(501, 240));
      imageFieldStart.setBorder(null);

      imageFieldError = new ImagePanelError();
      imageFieldError.setBorder(BorderFactory.createEmptyBorder());
      imageFieldError.setMinimumSize(new Dimension(501, 240));
      imageFieldError.setMaximumSize(new Dimension(501, 240));
      imageFieldError.setBorder(null);

      cardLayout = new CardLayout();
      swapPanel = new JPanel(cardLayout);
      swapPanel.setMinimumSize(new Dimension(501, 209));
      swapPanel.setMaximumSize(new Dimension(520, 209));
      swapPanel.setBorder(BorderFactory.createEmptyBorder());
      swapPanel.add("START", imageFieldStart);
      swapPanel.add("GREEN", imageFieldGreen);
      swapPanel.add("BLUE", imageFieldBlue);
      swapPanel.add("RED", imageFieldError);
      cardLayout.show(swapPanel, "START");

      if (Language.GERMAN.equals(languageDirection))
      {
         sendButton = new JButton("Antwort absenden");
      }
      else
      {
         sendButton = new JButton("Antwort anschauen");
      }
      sendButton.setFont(Settings.getButtonFont());
      sendButton.setIcon(new ImageIcon(ApplicationImages.getSend()));
      sendButton.setMinimumSize(new Dimension(300, 40));
      sendButton.setMaximumSize(new Dimension(501, 40));
      sendButton.setEnabled(false);

      feedbackPanel = new JPanel();
      feedbackPanel.setLayout(new TotemLayout(feedbackPanel, 15));
      feedbackPanel.setMinimumSize(new Dimension(501, 215));
      feedbackPanel.setMinimumSize(new Dimension(501, 215));

      vertical.add(swapPanel);
      vertical.add(sendButton);
      vertical.add(feedbackPanel);
      return vertical;
   }

   private void initController()
   {
      sendButton.addActionListener(event -> connector.send());

      nextWordButton.addActionListener(event -> nextWord());

      infoStopTrainingButton.addActionListener(
            event -> JOptionPane.showMessageDialog(infoStopTrainingPanel, "",
                  "Cerebrummi©", JOptionPane.INFORMATION_MESSAGE,
                  new ImageIcon(TextImage.make(
                        "Wenn Sie auf abbrechen drücken,",
                        "werden alle gelernten Antworten", "gespeichert."))));

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

      stopTrainingButton.addActionListener(event -> {
         connector.stopTraining(false);
      });

      this.additionalInfo.addActionListener(event -> {
         connector.setAdditionalInfo();
      });

      this.transcription.addActionListener(event -> {
         connector.setTranscription();
      });

      this.soundButton.addActionListener(event -> connector.toggleSound());

      if (Language.GERMAN.equals(languageDirection))
      {
         this.keyboardHints.addActionListener(event -> {
            if (keyboardHints.isSelected())
            {
               keyboardCardLayout.show(keyboardSwapPanel, "HINTS");
            }
            else
            {
               keyboardCardLayout.show(keyboardSwapPanel, "BLANK");
            }
         });
      }

      soundslider.addChangeListener(event -> {
         if(!soundslider.getValueIsAdjusting())
         {
            Settings.setVolume(soundslider.getValue());
         }
      });
   }

   public void setHtoDanswerButtons()
   {
      answerOkay = new JButton(
            new ImageIcon(ApplicationImages.getAnswerOkay()));
      answerOkay.addActionListener(event2 -> {
         connector.resultHtoDOkay();
      });
      answerOkay.setMinimumSize(new Dimension(167, 100));
      answerOkay.setMaximumSize(new Dimension(167, 100));
      answerOkay.setPreferredSize(new Dimension(167, 100));
      answerOkay.setSize(167, 105);

      answerUndecided = new JButton(
            new ImageIcon(ApplicationImages.getAnswerUndecided()));
      answerUndecided.addActionListener(event2 -> {
         connector.resultHtoDUndecided();
      });
      answerUndecided.setMinimumSize(new Dimension(167, 100));
      answerUndecided.setMaximumSize(new Dimension(167, 100));
      answerUndecided.setPreferredSize(new Dimension(167, 100));
      answerUndecided.setSize(167, 105);

      answerNotOkay = new JButton(
            new ImageIcon(ApplicationImages.getAnswerNotOkay()));
      answerNotOkay.addActionListener(event2 -> {
         connector.resultHtoDFalse();
      });
      answerNotOkay.setMinimumSize(new Dimension(167, 100));
      answerNotOkay.setMaximumSize(new Dimension(167, 100));
      answerNotOkay.setPreferredSize(new Dimension(167, 100));
      answerUndecided.setSize(167, 105);
   }

   public void enableHtoDAnswerButtons(boolean b)
   {
      this.answerOkay.setEnabled(b);
      this.answerUndecided.setEnabled(b);
      this.answerNotOkay.setEnabled(b);
   }

   public void prepareHtoDFeedbackPanel()
   {
      JPanel answerPanel1 = new JPanel();
      answerPanel1.setLayout(new TotemLayout(answerPanel1, 5));
      JLabel correctAnswer = new JLabel("Die richtige Antwort lautet:");
      correctAnswer.setFont(Main.getGermanFont(16F));
      correctAnswer.setMinimumSize(new Dimension(490, 30));
      correctAnswer.setMaximumSize(new Dimension(510, 30));
      JLabel correctAnswer2 = new JLabel(
            connector.getCurrentExpression().getGerman());
      correctAnswer2.setFont(Main.getGermanFont(20F));
      correctAnswer2.setMinimumSize(new Dimension(490, 30));
      correctAnswer2.setMaximumSize(new Dimension(510, 30));
      JLabel correctAnswer3 = new JLabel(
            connector.getCurrentExpression().getAdditionalInfoGerman());
      correctAnswer3.setFont(Main.getGermanFont(16F));
      correctAnswer3.setMinimumSize(new Dimension(490, 30));
      correctAnswer3.setMaximumSize(new Dimension(510, 30));

      answerPanel1.add(correctAnswer);
      answerPanel1.add(correctAnswer2);
      answerPanel1.add(correctAnswer3);

      JPanel answerPanel2 = new JPanel();
      answerPanel2.setLayout(new GridLayout(1, 3));
      answerPanel2.setMinimumSize(new Dimension(501, 100));
      answerPanel2.setMaximumSize(new Dimension(501, 100));

      feedbackPanel.add(answerPanel1);
      feedbackPanel.add(answerPanel2);

      setHtoDanswerButtons();
      answerPanel2.add(answerOkay);
      answerPanel2.add(answerUndecided);
      answerPanel2.add(answerNotOkay);

      enableHtoDAnswerButtons(true);
   }

   public void nextWord()
   {
      nextWordButton.setEnabled(false);
      cardLayout.show(swapPanel, "START");
      answerField.setText("");
      feedbackPanel.removeAll();
      feedbackPanel.validate();
      feedbackPanel.repaint();
      additionalInfo.setSelected(false);
      transcription.setSelected(false);
      wordPanel.removeAll();
      additionalInfoField.setText("");
      transcriptionField.setText("");
      connector.setNextTest();
      answerField.grabFocus();
      answerField.requestFocusInWindow();
   }

   public JTextField getAdditionalInfoField()
   {
      return additionalInfoField;
   }

   public JTextField getTranscriptionField()
   {
      return transcriptionField;
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

   public JTextField getQuestionField()
   {
      return questionField;
   }

   public LetterPictureWordPanel getWordPanel()
   {
      return wordPanel;
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

   public void showResultBlue()
   {
      cardLayout.show(swapPanel, "BLUE");
   }

   public void showResultGreen()
   {
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

   public void prepareDtoHFeedbackPanel(Result result)
   {
      HebrewAnswerWordPanel answerPanel = new HebrewAnswerWordPanel(result);
      JScrollPane scrollPane = new JScrollPane(answerPanel);
      scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      scrollPane.setBorder(BorderFactory.createEmptyBorder());
      scrollPane.setPreferredSize(new Dimension(501, 86 + 34));
      feedbackPanel.add(scrollPane);
      wordPanel.displayWord(result.getExpression().getHebrew());
   }
}
