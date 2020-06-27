package vokabeltrainer.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Command;
import vokabeltrainer.InfoTextField;
import vokabeltrainer.KeyboardHebrew;
import vokabeltrainer.OneFocusTraversalPolicy;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.TextImageWithPicture;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.SaveTraining;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.HebrewDocument;
import vokabeltrainer.panels.letterpicture.LetterPictureWordPanel;
import vokabeltrainer.panels.trainer.HebrewAnswerWordPanel;
import vokabeltrainer.panels.trainer.ImagePanelBlue;
import vokabeltrainer.panels.trainer.ImagePanelError;
import vokabeltrainer.panels.trainer.ImagePanelGreen;
import vokabeltrainer.panels.trainer.ImagePanelStart;
import vokabeltrainer.panels.trainer.TrainerControllerConnector;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Expression;
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
   private JTextField genderNumerusField;
   private JTextField transcriptionField;
   private InfoTextField answerField;
   private JLabel newWordsLabel;
   private JLabel oldWordsLabel;
   private JLabel languageDirectionLabel;
   private JCheckBox additionalInfo;
   private JCheckBox transcription;
   private JPanel answerPanel;
   private JButton sendButton;
   private Language languageDirection;
   private List<Expression> expressionsToBeTested;
   private Expression currentExpression;
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
   private int newWordsToLearn;
   private int oldWordsToRepeat;
   private Set<Expression> allExpressions;

   private KeyboardHebrew keyboard;

   private TrainerControllerConnector connector;

   public TrainerView(TrainerControllerConnector connector)
   {
      this.connector = connector;
      allExpressions = new HashSet<>();
      setLayout(new TotemLayout(this));
      this.setBorder(BorderFactory.createEmptyBorder());

      setOpaque(false);
      initGui();
      initController();
   }

   public void init()
   {
      this.languageDirection = connector.getLanguageDirection();

      newWordsToLearn = connector.getNewExpressions().size();
      oldWordsToRepeat = connector.getOldExpressions().size();
      System.out.println("=================TrainerView=NEW=================");
      for(Expression e: connector.getNewExpressions())
      {
         System.out.println(e.getExpressionPrintLine());
      }
      System.out.println("=================TrainerView=OLD=================");
      for(Expression e: connector.getOldExpressions())
      {
         System.out.println(e.getExpressionPrintLine());
      }
      allExpressions.addAll(connector.getOldExpressions());
      allExpressions.addAll(connector.getNewExpressions());
      expressionsToBeTested = new ArrayList<>(allExpressions.size());
      expressionsToBeTested.addAll(allExpressions);
      wordsToDo.setText(String.valueOf(expressionsToBeTested.size()));

      Collections.shuffle(expressionsToBeTested);

      String fieldOfTrainingString = "";
      if (connector.getFieldOfTraining().equals(Command.AREA_ALL))
      {
         fieldOfTrainingString = "alle Wörter";
      }
      else if (connector.getFieldOfTraining().equals(Command.AREA_CHAPTER))
      {
         fieldOfTrainingString = "Lektionen";
      }
      else if (connector.getFieldOfTraining()
            .equals(Command.AREA_EXPRESSION_KIND))
      {
         fieldOfTrainingString = "Wortarten";
      }
      else if (connector.getFieldOfTraining().equals(Command.AREA_SELECTED))
      {
         fieldOfTrainingString = "ausgewählte Wörter";
      }

      newWordsLabel.setText(
            fieldOfTrainingString + (fieldOfTrainingString.isEmpty() ? "" : " ")
                  + this.newWordsToLearn);
      oldWordsLabel.setText(String.valueOf(oldWordsToRepeat));

      languageDirectionLabel.setText(
            languageDirection.equals(Language.GERMAN) ? "Deutsch >> Hebräisch"
                  : "Hebräisch >> Deutsch");

      initQuestionPanel(languageDirection);

      setExpressionForTest();
      this.questionPanel.validate();
      this.questionPanel.repaint();
   }

   private void setExpressionForTest()
   {
      currentExpression = expressionsToBeTested.get(0);

      switch (languageDirection)
      {
      case GERMAN:
         this.questionField.setText(currentExpression.getGerman());
         break;
      case HEBREW:
         this.questionField.setText(currentExpression.getHebrew());
      }

      setAdditionalInfo();

      setTranscription();

      this.sendButton.setEnabled(true);
   }

   private void setTranscription()
   {
      if (Settings.getTranscription())
      {
         this.transcriptionField.setText(currentExpression.getHebrewInLatin());
      }
      else
      {
         this.transcriptionField.setText("");
      }
   }

   private void setAdditionalInfo()
   {
      switch (languageDirection)
      {
      case GERMAN:
         if (Settings.getAdditionalInfo())
         {
            this.genderNumerusField
                  .setText(currentExpression.getAdditionalInfoGerman());
         }
         else
         {
            this.genderNumerusField.setText("");
         }
         break;
      case HEBREW:
         if (Settings.getAdditionalInfo())
         {
            this.genderNumerusField
                  .setText(currentExpression.getAdditionalInfoHebrew());
         }
         else
         {
            this.genderNumerusField.setText("");
         }
      }
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

      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 31));
      vertical.setBackground(Settings.getGold());

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

      JLabel label1 = new JLabel("NEU");
      label1.setFont(labelFont);
      label1.setBackground(Settings.getGold());
      label1.setForeground(Color.WHITE);
      choicesLeft.add(label1);
      JLabel label2 = new JLabel("ALT");
      label2.setFont(labelFont);
      label2.setBackground(Settings.getGold());
      label2.setForeground(Color.WHITE);
      choicesLeft.add(label2);
      JLabel label3 = new JLabel("RICHTUNG");
      label3.setFont(labelFont);
      label3.setBackground(Settings.getGold());
      label3.setForeground(Color.WHITE);
      choicesLeft.add(label3);

      newWordsLabel = new JLabel();
      newWordsLabel.setFont(labelFont);
      newWordsLabel.setBackground(Settings.getGold());
      newWordsLabel.setForeground(Color.WHITE);
      oldWordsLabel = new JLabel();
      oldWordsLabel.setFont(labelFont);
      oldWordsLabel.setBackground(Settings.getGold());
      oldWordsLabel.setForeground(Color.WHITE);
      languageDirectionLabel = new JLabel();
      languageDirectionLabel.setFont(labelFont);
      languageDirectionLabel.setBackground(Settings.getGold());
      languageDirectionLabel.setForeground(Color.WHITE);
      choicesRight.add(newWordsLabel);
      choicesRight.add(oldWordsLabel);
      choicesRight.add(languageDirectionLabel);

      JPanel showOptions = new JPanel();
      showOptions.setLayout(new TotemLayout(showOptions, 15));
      showOptions.setBackground(Settings.getGold());
      showOptions.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
      additionalInfo = new JCheckBox("weitere Informationen anzeigen");
      additionalInfo.setFont(labelFont);
      additionalInfo.setSelected(Settings.getAdditionalInfo());
      additionalInfo.setForeground(Color.WHITE);
      transcription = new JCheckBox("Lautschrift anzeigen");
      transcription.setFont(labelFont);
      transcription.setSelected(Settings.getTranscription());
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

      JPanel filler = new JPanel();
      filler.setBackground(Settings.getGold());
      filler.setMinimumSize(new Dimension(150, 90));
      filler.setMaximumSize(new Dimension(250, 150));

      stopTrainingButton = new JButton("abbrechen");
      stopTrainingButton.setIcon(new ImageIcon(ApplicationImages.getStop()));

      vertical.add(choices);
      vertical.add(showOptions);
      vertical.add(numbers);
      vertical.add(nextWordButton);
      vertical.add(filler);
      vertical.add(stopTrainingButton);

      return vertical;
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

      genderNumerusField = new JTextField();
      genderNumerusField.setFont(Main.getGermanFont(15F));
      genderNumerusField.setBackground(Settings.getTexturedBackgroundColor());
      genderNumerusField.setBorder(BorderFactory.createTitledBorder(

            "weitere Informationen"));
      genderNumerusField
            .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 55));
      genderNumerusField
            .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 55));
      genderNumerusField.setEditable(false);

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
      questionPanel.add(genderNumerusField);
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
         answerField = new InfoTextField("Hier bitte die Lösung schreiben:",
               "Bitte in das Textfeld klicken,",
               "dann mit der hebräischen Tastatur", "die Antwort schreiben.");
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
               new ArrayList<JTextComponent>(), 80);
         answerPanel.add(keyboard);
      }
      else
      {
         answerPanel.removeAll();
         answerPanel
               .setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerPanel
               .setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 80));
         answerField = new InfoTextField("Hier bitte die Lösung schreiben:",
               "Bitte in das Textfeld klicken", "dann die Antwort schreiben.");
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

      sendButton = new JButton("Antwort absenden");
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
      sendButton.addActionListener(event -> {
         try
         {
            sendButton.setEnabled(false);
            boolean okay = false;
            if (Language.GERMAN.equals(this.languageDirection))
            {
               HebrewAnswerWordPanel answerPanel = new HebrewAnswerWordPanel(
                     currentExpression, answerField.getText().trim());
               okay = answerPanel.isOkay();
               JScrollPane scrollPane = new JScrollPane(answerPanel);
               scrollPane.setComponentOrientation(
                     ComponentOrientation.RIGHT_TO_LEFT);
               scrollPane.setBorder(BorderFactory.createEmptyBorder());
               scrollPane.setPreferredSize(new Dimension(501, 86));
               feedbackPanel.add(scrollPane);
               wordPanel.displayWord(currentExpression.getHebrew());
               if (okay)
               {
                  currentExpression.getTrainingStatusDToH().setTrys(
                        currentExpression.getTrainingStatusDToH().getTrys()
                              - 1);
                  if (currentExpression.getTrainingStatusDToH().getTrys() == 0)
                  {
                     currentExpression.getTrainingStatusDToH().nextRepetition();
                  }
               }
               else
               {
                  if (currentExpression.getTrainingStatusDToH().getTrys() < 4)
                  {
                     currentExpression.getTrainingStatusDToH().setTrys(
                           currentExpression.getTrainingStatusDToH().getTrys()
                                 + 1);
                  }
                  else
                  {
                     currentExpression.getTrainingStatusDToH()
                           .previousRepetition();
                  }
               }
               reactToAnswer(okay);
            }
            else
            {
               JPanel answerPanel1 = new JPanel();
               answerPanel1.setLayout(new TotemLayout(answerPanel1, 5));
               JLabel correctAnswer = new JLabel(
                     "Die richtige Antwort lautet:");
               correctAnswer.setFont(Main.getGermanFont(16F));
               correctAnswer.setMinimumSize(new Dimension(490, 30));
               correctAnswer.setMaximumSize(new Dimension(510, 30));
               JLabel correctAnswer2 = new JLabel(
                     currentExpression.getGerman());
               correctAnswer2.setFont(Main.getGermanFont(20F));
               correctAnswer2.setMinimumSize(new Dimension(490, 30));
               correctAnswer2.setMaximumSize(new Dimension(510, 30));
               JLabel correctAnswer3 = new JLabel(
                     currentExpression.getAdditionalInfoGerman());
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
               answerOkay = new JButton(
                     new ImageIcon(ApplicationImages.getAnswerOkay()));
               answerOkay.addActionListener(event2 -> {
                  currentExpression.getTrainingStatusHToD().setTrys(
                        currentExpression.getTrainingStatusHToD().getTrys()
                              - 1);
                  if (currentExpression.getTrainingStatusHToD().getTrys() == 0)
                  {
                     currentExpression.getTrainingStatusHToD().nextRepetition();
                  }
                  enableAnswerButtons(false);
                  reactToAnswer(true);
               });
               answerOkay.setMinimumSize(new Dimension(167, 100));
               answerOkay.setMaximumSize(new Dimension(167, 100));
               answerOkay.setPreferredSize(new Dimension(167, 100));
               answerOkay.setSize(167, 105);

               answerUndecided = new JButton(
                     new ImageIcon(ApplicationImages.getAnswerUndecided()));
               answerUndecided.addActionListener(event2 -> {
                  enableAnswerButtons(false);
                  reactToAnswer(null);
               });
               answerUndecided.setMinimumSize(new Dimension(167, 100));
               answerUndecided.setMaximumSize(new Dimension(167, 100));
               answerUndecided.setPreferredSize(new Dimension(167, 100));
               answerUndecided.setSize(167, 105);

               answerNotOkay = new JButton(
                     new ImageIcon(ApplicationImages.getAnswerNotOkay()));
               answerNotOkay.addActionListener(event2 -> {
                  if (currentExpression.getTrainingStatusHToD().getTrys() < 4)
                  {
                     currentExpression.getTrainingStatusHToD().setTrys(
                           currentExpression.getTrainingStatusHToD().getTrys()
                                 + 1);
                  }
                  else
                  {
                     currentExpression.getTrainingStatusHToD()
                           .previousRepetition();
                  }
                  enableAnswerButtons(false);
                  reactToAnswer(false);
               });
               answerNotOkay.setMinimumSize(new Dimension(167, 100));
               answerNotOkay.setMaximumSize(new Dimension(167, 100));
               answerNotOkay.setPreferredSize(new Dimension(167, 100));
               answerUndecided.setSize(167, 105);

               answerPanel2.add(answerOkay);
               answerPanel2.add(answerUndecided);
               answerPanel2.add(answerNotOkay);

               enableAnswerButtons(true);

               feedbackPanel.add(answerPanel1);
               feedbackPanel.add(answerPanel2);
            }
            feedbackPanel.validate();
            feedbackPanel.repaint();
         }
         catch (Exception e1)
         {
            e1.printStackTrace();
         }
      });

      nextWordButton.addActionListener(event -> {
         nextWordButton.setEnabled(false);
         cardLayout.show(swapPanel, "START");
         answerField.setText("");
         feedbackPanel.removeAll();
         feedbackPanel.validate();
         feedbackPanel.repaint();
         wordPanel.removeAll();
         setExpressionForTest();
         answerField.grabFocus();
         answerField.requestFocusInWindow();
      });

      stopTrainingButton.addActionListener(event -> {
         this.stopTraining(false);
      });

      this.additionalInfo.addActionListener(event -> {
         Settings.setAdditionalInfo(additionalInfo.isSelected());
         this.setAdditionalInfo();
      });

      this.transcription.addActionListener(event -> {
         Settings.setTranscription(transcription.isSelected());
         this.setTranscription();
      });
   }

   private void enableAnswerButtons(boolean b)
   {
      this.answerOkay.setEnabled(b);
      this.answerUndecided.setEnabled(b);
      this.answerNotOkay.setEnabled(b);
   }

   private void reactToAnswer(Boolean okay)
   {
      if (okay == null)
      {
         cardLayout.show(swapPanel, "BLUE");
      }
      else if (okay)
      {
         cardLayout.show(swapPanel, "GREEN");
         expressionsToBeTested.remove(0);
         wordsRightNumber++;
         wordsRight.setText(String.valueOf(wordsRightNumber));
         wordsToDo.setText(String.valueOf(expressionsToBeTested.size()));
      }
      else
      {
         cardLayout.show(swapPanel, "RED");
         expressionsToBeTested.add(currentExpression);
         wordsWrongNumber++;
         wordsWrong.setText(String.valueOf(wordsWrongNumber));
         wordsToDo.setText(String.valueOf(expressionsToBeTested.size()));
      }

      Collections.shuffle(expressionsToBeTested, new Random(System.nanoTime()));

      if (!expressionsToBeTested.isEmpty())
      {
         nextWordButton.setEnabled(true);
      }
      else
      {
         stopTraining(true);
      }
   }

   private void stopTraining(boolean finished)
   {
      Common.getMainJPanel().moveToStartPanel();

      if (finished)
      {
         if (newWordsToLearn > 0 && oldWordsToRepeat > 0)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(TextImageWithPicture.make(
                        ApplicationImages.getReward(), "Bravo, sie haben diese",
                        "Trainingseinheit erfolgreich", "beendet.",
                        "Sie haben " + newWordsToLearn + " neue Wörter",
                        "und " + oldWordsToRepeat
                              + " bekannte Wörter bearbeitet.")));
         }
         else if (newWordsToLearn > 0 && oldWordsToRepeat == 0)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(TextImageWithPicture.make(
                        ApplicationImages.getReward(), "Bravo, sie haben diese",
                        "Trainingseinheit erfolgreich", "beendet.",
                        "Sie haben " + newWordsToLearn + " neue Wörter",
                        "bearbeitet.")));
         }
         else if (newWordsToLearn == 0 && oldWordsToRepeat > 0)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(TextImageWithPicture.make(
                        ApplicationImages.getReward(), "Bravo, sie haben diese",
                        "Trainingseinheit erfolgreich", "beendet.",
                        "Sie haben " + oldWordsToRepeat
                              + " bekannte Wörter bearbeitet.")));
         }
      }
      else
      {
         JOptionPane.showMessageDialog(Common.getjFrame(), "",
               Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(TextImage.make("Das Training wurde abgebrochen.",
                     "Sie haben " + wordsRightNumber + " richtige",
                     "Antworten gegeben.")));
      }

      saveTraining();
   }

   private void saveTraining()
   {
      SaveTraining saveTraining = new SaveTraining();
      SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            int counter = 0;
            while (counter < 10 && !saveTraining.save())
            {
               try
               {
                  Thread.sleep(200);
               }
               catch (InterruptedException e)
               {

               }
               counter++;
            }

            if (counter == 10)
            {
               JOptionPane.showMessageDialog(Common.getjFrame(),
                     "Fehlermeldung\nDie Trainingsdaten konnten\nnicht gespeichert werden.",
                     Settings.getWindowTitle(), JOptionPane.WARNING_MESSAGE);
            }
         }
      });
   }
}
