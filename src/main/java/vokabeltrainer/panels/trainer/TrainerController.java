package vokabeltrainer.panels.trainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import vokabeltrainer.ApplicationImages;
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
      expressionsToBeTested = new ArrayList<>(allExpressions.size());
      expressionsToBeTested.addAll(allExpressions);
      trainerView.getWordsToDo()
            .setText(String.valueOf(expressionsToBeTested.size()));

      Collections.shuffle(expressionsToBeTested);
      trainerView.init();
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
                  .setText(currentExpression.getAdditionalInfoGerman());
            break;
         case HEBREW:
            trainerView.getAdditionalInfoField()
                  .setText(currentExpression.getAdditionalInfoHebrew());
            break;
         }
      }
      else
      {
         trainerView.getAdditionalInfoField().setText("");
      }
   }

   public void setTranscription()
   {
      if (trainerView.getTranscriptionField().getText().isEmpty())
      {
         trainerView.getTranscriptionField()
               .setText(currentExpression.getHebrewInLatin());
      }
      else
      {
         trainerView.getTranscriptionField().setText("");
      }
   }

   public void setNextTest()
   {
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
         trainerView.disableSendButton();

         if (Language.GERMAN.equals(this.languageDirection))
         {
            Result result = Resultfactory.getResultDtoH(currentExpression,
                  trainerView.getAnswerField().getText().trim());
            trainerView.prepareDtoHFeedbackPanel(result);
            if (result.isOkay())
            {
               currentExpression.getTrainingStatusDToH().setTrys(
                     currentExpression.getTrainingStatusDToH().getTrys() - 1);
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
            reactToAnswer(result.isOkay());
         }
         else
         {
            trainerView.prepareHtoDFeedbackPanel();
         }
         trainerView.getFeedbackPanel().validate();
         trainerView.getFeedbackPanel().repaint();
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
      }
      trainerView.enableHtoDAnswerButtons(false);
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
      if (currentExpression.getTrainingStatusHToD().getTrys() < 4)
      {
         currentExpression.getTrainingStatusHToD().setTrys(
               currentExpression.getTrainingStatusHToD().getTrys() + 1);
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
         trainerView.showResultBlue();
      }
      else if (okay)
      {
         expressionsToBeTested.remove(0);
         trainerView.showResultGreen();
      }
      else
      {
         expressionsToBeTested.add(currentExpression);
         trainerView.showResultRed();
      }

      Collections.shuffle(expressionsToBeTested, new Random(System.nanoTime()));

      if (!expressionsToBeTested.isEmpty())
      {
         trainerView.getNextWordButton().setEnabled(true);
      }
      else
      {
         stopTraining(true);
      }
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

}
