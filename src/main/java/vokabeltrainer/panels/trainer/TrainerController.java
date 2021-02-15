package vokabeltrainer.panels.trainer;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.ApplicationSound;
import vokabeltrainer.Command;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.TextImageWithPicture;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.SaveTraining;
import vokabeltrainer.panels.TrainerView;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class TrainerController implements TrainerControllerConnector
{
   private TrainerView trainerView;
   private Language languageDirection;
   private Command fieldOfTraining;
   private List<Expression> newExpressions;
   private List<Expression> oldExpressions;
   private Expression currentExpression;
   private int newWordsToLearn;
   private int oldWordsToRepeat;
   private Set<Expression> allExpressions;
   private List<Expression> expressionsToBeTested;

   public TrainerController(Language languageDirection, Command fieldOfTraining,
         List<Expression> newExpressions, List<Expression> oldExpressions)
   {
      this.languageDirection = languageDirection;
      this.fieldOfTraining = fieldOfTraining;
      this.newExpressions = newExpressions;
      this.oldExpressions = oldExpressions;
      allExpressions = new HashSet<>();

      trainerView = new TrainerView(this);

      newWordsToLearn = this.newExpressions.size();
      oldWordsToRepeat = this.oldExpressions.size();
      allExpressions.addAll(this.oldExpressions);
      allExpressions.addAll(this.newExpressions);
      expressionsToBeTested = new LinkedList<>();
      expressionsToBeTested.addAll(allExpressions);
      if (languageDirection == Language.GERMAN) // DtoH
      {
         expressionsToBeTested.forEach(expression -> {
            expression.getTrainingStatusDToH()
                  .setTotalTrys(expression.getTrainingStatusDToH().getTrys());
         });
      }
      else // HtoD
      {
         expressionsToBeTested.forEach(expression -> {
            expression.getTrainingStatusHToD()
                  .setTotalTrys(expression.getTrainingStatusHToD().getTrys());
         });
      }

      trainerView.getWordsToDo()
            .setText(String.valueOf(expressionsToBeTested.size()));

      Collections.shuffle(expressionsToBeTested);
      trainerView.init();
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            trainerView.getAnswerField().grabFocus();
            trainerView.getAnswerField().requestFocus();// or inWindow
         }
      });
   }

   public TrainerView getTrainerView()
   {
      return trainerView;
   }

   public Language getLanguageDirection()
   {
      return languageDirection;
   }

   public Command getFieldOfTraining()
   {
      return fieldOfTraining;
   }

   public List<Expression> getNewExpressions()
   {
      return newExpressions;
   }

   public List<Expression> getOldExpressions()
   {
      return oldExpressions;
   }

   public void setAdditionalInfo()
   {
      if (trainerView.getAdditionalInfoField().getText().isEmpty())
      {
         switch (languageDirection)
         {
         case GERMAN:
            trainerView.getAdditionalInfoField()
                  .setText(currentExpression.getAdditionalInfoGerman(true));
            break;
         case HEBREW:
            trainerView.getAdditionalInfoField()
                  .setText(currentExpression.getAdditionalInfoHebrew(true));
            break;
         }
      }
      else
      {
         trainerView.getAdditionalInfoField().setText("");
      }
      trainerView.getFocusTraversalPolicy().getFirstComponent(null)
      .requestFocus();
   }

   public void setNextTest()
   {
      trainerView.getWordPanel().clear();
      currentExpression = expressionsToBeTested.get(0);

      switch (languageDirection)
      {
      case GERMAN:
         trainerView.getQuestionField().setText(currentExpression.getGerman());
         break;
      case HEBREW:
         trainerView.getQuestionField().setText(currentExpression.getHebrew());
         break;
      }

      trainerView.enableSendButton();
   }

   @Override
   public void send()
   {
      try
      {
         if (Language.GERMAN.equals(this.languageDirection)) // DtoH
         {
            Result result = Resultfactory.getResultDtoHScentence(currentExpression,
                  trainerView.getAnswerField().getText().trim());
            if (result.isAnswerEmpty())
            {
               JOptionPane.showMessageDialog(Common.getjFrame(), "",
                     Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                     new ImageIcon(TextImage
                           .make("Bitte schreiben Sie eine Antwort.")));
               return;
            }
            else if (result.isDictionaryEmpty())
            {
               JOptionPane.showMessageDialog(Common.getjFrame(), "",
                     Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                     new ImageIcon(TextImage.make(
                           "Ihr Trainingswort enthält keine Buchstaben.",
                           "Bitte löschen Sie diesen Ausdruck",
                           "aus Kapitel " + currentExpression.getChapter())));
               return;
            }
            trainerView.prepareDtoHFeedbackPanel(result);
            if (result.isOkay())
            {
               currentExpression.getTrainingStatusDToH().setTrys(
                     currentExpression.getTrainingStatusDToH().getTrys() - 1);
               if (currentExpression.getTrainingStatusDToH().getTrys() == 0)
               {
                  currentExpression.getTrainingStatusDToH().nextRepetition();
                  currentExpression.getTrainingStatusDToH().setTrys(1);
               }
               expressionsToBeTested.remove(0);
            }
            else
            {
               if (currentExpression.getTrainingStatusDToH().getTotalTrys() < 4)
               {
                  currentExpression.getTrainingStatusDToH().setTrys(
                        currentExpression.getTrainingStatusDToH().getTrys()
                              + 1);
                  currentExpression.getTrainingStatusDToH().setTotalTrys(
                        currentExpression.getTrainingStatusDToH().getTotalTrys()
                              + 1);
                  expressionsToBeTested.add(currentExpression);
               }
               else
               {
                  currentExpression.getTrainingStatusDToH()
                        .previousRepetition();
               }
            }
            reactToAnswer(result.isOkay());
         }
         else if (Language.TO_NIKUD.equals(languageDirection))
         {
            // TODO
         }
         else // HtoD
         {
            trainerView.prepareHtoDFeedbackPanel();
         }
         trainerView.getFeedbackPanel().validate();
         trainerView.getFeedbackPanel().repaint();
         trainerView.disableSendButton();
      }
      catch (Exception e1)
      {
         e1.printStackTrace();
      }
   }

   @Override
   public void resultHtoDOkay()
   {
      currentExpression.getTrainingStatusHToD()
            .setTrys(currentExpression.getTrainingStatusHToD().getTrys() - 1);
      if (currentExpression.getTrainingStatusHToD().getTrys() == 0)
      {
         currentExpression.getTrainingStatusHToD().nextRepetition();
         currentExpression.getTrainingStatusHToD().setTrys(1);
      }
      trainerView.enableHtoDAnswerButtons(false);
      expressionsToBeTested.remove(0);
      reactToAnswer(true);
   }

   @Override
   public void resultHtoDUndecided()
   {
      trainerView.enableHtoDAnswerButtons(false);
      reactToAnswer(null);
   }

   @Override
   public void resultHtoDFalse()
   {
      if (currentExpression.getTrainingStatusHToD().getTotalTrys() < 4)
      {
         currentExpression.getTrainingStatusHToD().setTrys(
               currentExpression.getTrainingStatusHToD().getTrys() + 1);
         currentExpression.getTrainingStatusHToD().setTotalTrys(
               currentExpression.getTrainingStatusHToD().getTotalTrys() + 1);
         expressionsToBeTested.add(currentExpression);
      }
      else
      {
         currentExpression.getTrainingStatusHToD().previousRepetition();
      }
      trainerView.enableHtoDAnswerButtons(false);
      reactToAnswer(false);
   }

   public void reactToAnswer(Boolean okay)
   {
      if (okay == null)
      {
         if (Settings.isSoundOn())
         {
            reactUndecidedWithSoundOn();
         }
         else
         {
            trainerView.showResultBlue();
         }
      }
      else if (okay)
      {
         if (Settings.isSoundOn())
         {
            reactOkayWithSoundOn();
         }
         else
         {
            trainerView.showResultGreen();
         }
      }
      else
      {
         if (Settings.isSoundOn())
         {
            reactFalseWithSoundOn();
         }
         else
         {
            trainerView.showResultRed();
         }
      }

      if (!expressionsToBeTested.isEmpty())
      {
         Collections.shuffle(expressionsToBeTested,
               new Random(System.nanoTime()));
         trainerView.getNextWordButton().setEnabled(true);
      }
      else
      {
         stopTraining(true);
      }
   }

   private void reactFalseWithSoundOn()
   {
      try
      {
         Clip clip = AudioSystem.getClip();
         clip.open(ApplicationSound.getSplotchSound());
         FloatControl volume = (FloatControl) clip
               .getControl(FloatControl.Type.MASTER_GAIN);
         volume.setValue(Settings.getVolume());
         clip.start();
      }
      catch (LineUnavailableException | IOException e)
      {
         // nothing
      }

      trainerView.showResultRed();
   }

   private void reactOkayWithSoundOn()
   {
      try
      {
         Clip clip = AudioSystem.getClip();
         clip.open(ApplicationSound.getClappingSound());
         FloatControl volume = (FloatControl) clip
               .getControl(FloatControl.Type.MASTER_GAIN);
         volume.setValue(Settings.getVolume());
         clip.start();
      }
      catch (LineUnavailableException | IOException e)
      {
         // nothing
      }

      trainerView.showResultGreen();
   }

   private void reactUndecidedWithSoundOn()
   {
      try
      {
         Clip clip = AudioSystem.getClip();
         clip.open(ApplicationSound.getWaveSound());
         FloatControl volume = (FloatControl) clip
               .getControl(FloatControl.Type.MASTER_GAIN);
         volume.setValue(Settings.getVolume());
         clip.start();
      }
      catch (LineUnavailableException | IOException e)
      {
         // nothing
      }

      trainerView.showResultBlue();
   }

   public void stopTraining(boolean finished)
   {
      Common.getMainJPanel().moveToStartPanel();

      if (finished)
      {
         if (newWordsToLearn > 0 && oldWordsToRepeat > 0)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(
                        TextImageWithPicture.make(ApplicationImages.getReward(),
                              "Wunderbar, sie haben diese",
                              "Trainingseinheit erfolgreich", "beendet.",
                              "Sie haben " + newWordsToLearn + " neue Wörter",
                              "und " + oldWordsToRepeat
                                    + " bekannte Wörter bearbeitet.")));
         }
         else if (newWordsToLearn > 0 && oldWordsToRepeat == 0)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(
                        TextImageWithPicture.make(ApplicationImages.getReward(),
                              "Wunderbar, sie haben diese",
                              "Trainingseinheit erfolgreich", "beendet.",
                              "Sie haben " + newWordsToLearn + " neue Wörter",
                              "bearbeitet.")));
         }
         else if (newWordsToLearn == 0 && oldWordsToRepeat > 0)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(
                        TextImageWithPicture.make(ApplicationImages.getReward(),
                              "Wunderbar, sie haben diese",
                              "Trainingseinheit erfolgreich", "beendet.",
                              "Sie haben " + oldWordsToRepeat
                                    + " bekannte Wörter bearbeitet.")));
         }
      }
      else if (trainerView.getWordsRight().getText().equals("0"))
      {
         JOptionPane.showMessageDialog(Common.getjFrame(), "",
               Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(
                     TextImage.make("Das Training wurde abgebrochen.")));
      }
      else if (trainerView.getWordsRight().getText().equals("1"))
      {
         JOptionPane.showMessageDialog(Common.getjFrame(), "",
               Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(TextImage.make("Das Training wurde abgebrochen.",
                     "Sie haben 1ne richtige", "Antwort gegeben.")));
      }
      else
      {
         JOptionPane.showMessageDialog(Common.getjFrame(), "",
               Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(TextImage.make("Das Training wurde abgebrochen.",
                     "Sie haben " + trainerView.getWordsRight().getText()
                           + " richtige",
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

   @Override
   public void removeFirstExpressionToBeTested()
   {
      expressionsToBeTested.remove(0);
   }

   public int getNewWordsToLearn()
   {
      return newWordsToLearn;
   }

   public int getOldWordsToRepeat()
   {
      return oldWordsToRepeat;
   }

   public Expression getCurrentExpression()
   {
      return currentExpression;
   }

   public List<Expression> getExpressionsToBeTested()
   {
      return expressionsToBeTested;
   }

   @Override
   public void toggleSound()
   {
      Settings.toggleSoundOnOff();
      trainerView.getSoundButton().setIcon(new ImageIcon(Settings.getSound()));
   }

}
