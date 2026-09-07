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
import javax.swing.SwingWorker;

import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.TextImage;
import vokabeltrainer.TextImageWithPicture;
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.SaveTraining;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.TrainerView;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.FieldOfTraining;
import vokabeltrainer.types.LanguageDirection;

public class TrainerController implements TrainerControllerConnector
{
   private TrainerView trainerView;
   private LanguageDirection languageDirection;
   private FieldOfTraining fieldOfTraining;
   private List<Expression> newExpressions;
   private List<Expression> oldExpressions;
   private Expression currentExpression;
   private int newWordsToLearn;
   private int oldWordsToRepeat;
   private Set<Expression> allExpressions;
   private List<Expression> expressionsToBeTested;
   private Translator translator;

   public TrainerController(App app, Common common, Model model, View view, LanguageDirection languageDirection,
         FieldOfTraining fieldOfTraining, List<Expression> newExpressions,
         List<Expression> oldExpressions)
   {
      translator = common.getTranslator();
      this.languageDirection = languageDirection;
      this.fieldOfTraining = fieldOfTraining;
      this.newExpressions = newExpressions;
      this.oldExpressions = oldExpressions;
      allExpressions = new HashSet<>();

      trainerView = new TrainerView(app, common, model, view, this);

      newWordsToLearn = this.newExpressions.size();

      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         oldWordsToRepeat = this.oldExpressions.size();
         allExpressions.addAll(this.oldExpressions);
      }
      else
      {
         oldWordsToRepeat = 0;
      }
      allExpressions.addAll(this.newExpressions);
      expressionsToBeTested = new LinkedList<>();
      expressionsToBeTested.addAll(allExpressions);
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         if (Direction.OWN_TO_NEW == languageDirection.getDirection())
         {
            expressionsToBeTested.forEach(expression -> {
               expression.getTrainingStatusDToLL().setTotalTrys(
                     expression.getTrainingStatusDToLL().getTrys());
            });
         }
         else
         {
            expressionsToBeTested.forEach(expression -> {
               expression.getTrainingStatusLLToD().setTotalTrys(
                     expression.getTrainingStatusLLToD().getTrys());
            });
         }
      }

      trainerView.getWordsToDo()
            .setText(String.valueOf(expressionsToBeTested.size()));

      Collections.shuffle(expressionsToBeTested);
      trainerView.init(app, common, view);
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

   public LanguageDirection getLanguageDirection()
   {
      return languageDirection;
   }

   public FieldOfTraining getFieldOfTraining()
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
         trainerView.getAdditionalInfoField()
               .setText(currentExpression.getAdditionalInfo());
      }
      else
      {
         trainerView.getAdditionalInfoField().setText("");
      }
      trainerView.getFocusTraversalPolicy().getFirstComponent(null)
            .requestFocus();
   }

   @Override
   public void setGrammarInfo()
   {
      if (trainerView.getGrammarInfoField().getText().isEmpty())
      {
         trainerView.getGrammarInfoField()
               .setText(currentExpression.getGrammarInfo(true));
      }
      else
      {
         trainerView.getGrammarInfoField().setText("");
      }
      trainerView.getFocusTraversalPolicy().getFirstComponent(null)
            .requestFocus();
   }

   private void setGrammarInfo(boolean show)
   {
      if (show)
      {
         setGrammarInfo();
      }
   }

   public void setNextTest(App app)
   {
      trainerView.getWordPanelPlene().clear(app);
      trainerView.getWordPanelDefektiv().clear(app);
      currentExpression = expressionsToBeTested.get(0);

      switch (languageDirection)
      {
      case OWN_TO_HEBREW:
      case OWN_TO_SWEDISH:
      case OWN_TO_GERMAN:
         trainerView.getQuestionFieldGerman()
               .setText(currentExpression.getOwnLanguage());
         trainerView.getQuestionFieldGerman().validate();
         trainerView.getQuestionFieldGerman().repaint();
         trainerView.getGrammarInfo()
               .setSelected(currentExpression.isVisible()); // only in this
                                                            // languageDirection
         this.setGrammarInfo(currentExpression.isVisible());
         break;
      case HEBREW_TO_OWN:
         if (currentExpression.getLL().isSimpleHebrew())
         {
            trainerView.getQuestionFieldLL()
                  .setHebrewFieldText(currentExpression.getLL().getHebrew());
            trainerView.getQuestionFieldLL()
                  .setLayoutNoKeyboard(Selection.SIMPLE);
         }
         else
         {
            trainerView.getQuestionFieldLL().setPleneFieldText(
                  currentExpression.getLL().getHebrewPlene());
            trainerView.getQuestionFieldLL().setDefektivFieldText(
                  currentExpression.getLL().getHebrewDefektiv());
            trainerView.getQuestionFieldLL()
                  .setLayoutNoKeyboard(Selection.PLENE_DEFEKTIV);
         }
         trainerView.getQuestionFieldLL().revalidate();
         trainerView.getQuestionFieldLL().repaint();
         break;
      case SWEDISH_TO_OWN:
         trainerView.getQuestionFieldLL()
               .setSwedishFieldText(currentExpression.getLL().getSwedish());
         trainerView.getQuestionFieldLL()
               .setLayoutNoKeyboard(Selection.SWEDISH);
         trainerView.getQuestionFieldLL().revalidate();
         trainerView.getQuestionFieldLL().repaint();
         break;
      case GERMAN_TO_OWN:
         trainerView.getQuestionFieldLL()
               .setGermanFieldText(currentExpression.getLL().getGerman());
         trainerView.getQuestionFieldLL().setLayoutNoKeyboard(Selection.GERMAN);
         trainerView.getQuestionFieldLL().revalidate();
         trainerView.getQuestionFieldLL().repaint();
         break;
      }

      trainerView.enableSendButton();
   }

   @Override
   public void send(App app, Common common, Model model, View view)
   {
      try
      {
         switch (languageDirection)
         {
         case OWN_TO_SWEDISH:
         case OWN_TO_HEBREW:
         case OWN_TO_GERMAN:
            BestResult bestResult;
            if (LanguageDirection.OWN_TO_HEBREW == languageDirection)
            {
               bestResult = NikudResultFactory.getBestResultPossible(app,
                     currentExpression,
                     trainerView.getAnswerField().getText().trim(),
                     app.appFonts.hebrewFont.deriveFont(30F));
            }
            else if (LanguageDirection.OWN_TO_SWEDISH == languageDirection)
            {
               bestResult = SwedishResultFactory.getBestResultPossible(app,
                     currentExpression,
                     trainerView.getAnswerField().getText().trim());
            }
            else
            {
               bestResult = GermanResultFactory.getBestResultPossible(app,
                     currentExpression,
                     trainerView.getAnswerField().getText().trim());
            }
            Result result = bestResult.getBestResult();
            if (result.isAnswerEmpty())
            {
               JOptionPane.showMessageDialog(view.getjFrame(), "",
                    app.settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                     new ImageIcon(TextImage.make(app, translator.realisticTranslate(app, 
                           Translation.BITTE_SCHREIBEN_SIE_EINE_ANTWORT))));
               return;
            }
            else if (result.isDictionaryEmpty())
            {
               JOptionPane.showMessageDialog(view.getjFrame(), "",
                     app.settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                     new ImageIcon(TextImage.make(app, translator.realisticTranslate(app, 
                           Translation.IHR_TRAININGSWORT_ENTHAELT_KEINE_BUCHSTABEN_),
                           translator.realisticTranslate(app, 
                                 Translation.BITTE_LOESCHEN_SIE_DIESEN_AUSDRUCK),
                           translator.realisticTranslate(app, 
                                 Translation.AUS_KAPITEL) + " "
                                 + currentExpression.getChapter().getName())));
               return;
            }
            trainerView.prepareDtoNikudFeedbackPanel(app, common, result);
            if (result.isOkay())
            {
               resultDtoIsOkay(common);
            }
            else
            {
               resultDtoIsNotOkay(common);
            }
            reactToAnswer(app, common, model, view, result.isOkay());
            break;
         case HEBREW_TO_OWN:
         case SWEDISH_TO_OWN:
         case GERMAN_TO_OWN:
            trainerView.prepareHtoDFeedbackPanel(app, common, model, view);
            break;
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

   private void resultDtoIsNotOkay(Common common)
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         if (currentExpression.getTrainingStatusDToLL().getTrys() < 4)
         {
            currentExpression.getTrainingStatusDToLL().setTrys(
                  currentExpression.getTrainingStatusDToLL().getTrys() + 1);
            currentExpression.getTrainingStatusDToLL().setTotalTrys(
                  currentExpression.getTrainingStatusDToLL().getTotalTrys()
                        + 1);
            expressionsToBeTested.add(currentExpression);
         }
         else
         {
            currentExpression.getTrainingStatusDToLL().previousRepetition(common);
         }
      }
      else
      {
         if (currentExpression.getTemporaryTrainingStatus().getTrys() < 4)
         {
            currentExpression.getTemporaryTrainingStatus().setTrys(
                  currentExpression.getTemporaryTrainingStatus().getTrys() + 1);
            currentExpression.getTemporaryTrainingStatus().setTotalTrys(
                  currentExpression.getTemporaryTrainingStatus().getTotalTrys()
                        + 1);
            expressionsToBeTested.add(currentExpression);
         }
      }
   }

   private void resultDtoIsOkay(Common common)
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         currentExpression.getTrainingStatusDToLL().setTrys(
               currentExpression.getTrainingStatusDToLL().getTrys() - 1);
         if (currentExpression.getTrainingStatusDToLL().getTrys() == 0)
         {
            currentExpression.getTrainingStatusDToLL().nextRepetition(common);
            currentExpression.getTrainingStatusDToLL().setTrys(1);
         }
      }
      else
      {
         currentExpression.getTemporaryTrainingStatus().setTrys(
               currentExpression.getTemporaryTrainingStatus().getTrys() - 1);
      }
      expressionsToBeTested.remove(0);
   }

   @Override
   public void resultHtoDOkay(App app, Common common, Model model, View view)
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         currentExpression.getTrainingStatusLLToD().setTrys(
               currentExpression.getTrainingStatusLLToD().getTrys() - 1);
         if (currentExpression.getTrainingStatusLLToD().getTrys() == 0)
         {
            currentExpression.getTrainingStatusLLToD().nextRepetition(common);
            currentExpression.getTrainingStatusLLToD().setTrys(1);
         }
      }
      else
      {
         currentExpression.getTemporaryTrainingStatus().setTrys(
               currentExpression.getTemporaryTrainingStatus().getTrys() - 1);
      }
      trainerView.enableHtoDAnswerButtons(false);
      expressionsToBeTested.remove(0);
      reactToAnswer(app, common, model, view, true);
   }

   @Override
   public void resultHtoDUndecided(App app, Common common, Model model, View view)
   {
      trainerView.enableHtoDAnswerButtons(false);
      reactToAnswer(app, common, model, view, null);
   }

   @Override
   public void resultHtoDFalse(App app, Common common, Model model, View view)
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY != this.fieldOfTraining)
      {
         if (currentExpression.getTrainingStatusLLToD().getTotalTrys() < 4)
         {
            currentExpression.getTrainingStatusLLToD().setTrys(
                  currentExpression.getTrainingStatusLLToD().getTrys() + 1);
            currentExpression.getTrainingStatusLLToD().setTotalTrys(
                  currentExpression.getTrainingStatusLLToD().getTotalTrys()
                        + 1);
            expressionsToBeTested.add(currentExpression);
         }
         else
         {
            currentExpression.getTrainingStatusLLToD().previousRepetition(common);
         }
      }
      else
      {
         if (currentExpression.getTemporaryTrainingStatus().getTrys() < 4)
         {
            currentExpression.getTemporaryTrainingStatus().setTrys(
                  currentExpression.getTemporaryTrainingStatus().getTrys() + 1);
            currentExpression.getTemporaryTrainingStatus().setTotalTrys(
                  currentExpression.getTemporaryTrainingStatus().getTotalTrys()
                        + 1);
            expressionsToBeTested.add(currentExpression);
         }
      }
      trainerView.enableHtoDAnswerButtons(false);
      reactToAnswer(app, common, model, view, false);
   }

   public void reactToAnswer(App app, Common common, Model model, View view, Boolean okay)
   {
      if (okay == null)
      {
         if (app.settings.isSoundOn())
         {
            reactUndecidedWithSoundOn(app);
         }
         else
         {
            trainerView.showResultBlue(app);
         }
      }
      else if (okay)
      {
         if (app.settings.isSoundOn())
         {
            reactOkayWithSoundOn(app);
         }
         else
         {
            trainerView.showResultGreen(app);
         }
      }
      else
      {
         if (app.settings.isSoundOn())
         {
            reactFalseWithSoundOn(app);
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
         stopTraining(app, common, model, view, true);
      }
   }

   private void reactFalseWithSoundOn(App app)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            try (Clip clip = AudioSystem.getClip())
            {
               clip.open(app.appSound.getSplotchSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(app.settings.getVolume());
               clip.start();
               do
               {
                  Thread.sleep(100);
               } while (clip.isRunning());
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
            catch (InterruptedException e)
            {
               // nothing
            }
            return null;
         }
      }.execute();

      trainerView.showResultRed();
   }

   private void reactOkayWithSoundOn(App app)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            try (Clip clip = AudioSystem.getClip())
            {
               clip.open(app.appSound.getClappingSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(app.settings.getVolume());
               clip.start();
               do
               {
                  Thread.sleep(100);
               } while (clip.isRunning());
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
            catch (InterruptedException e)
            {
               // nothing
            }
            return null;
         }
      }.execute();

      trainerView.showResultGreen(app);
   }

   private void reactUndecidedWithSoundOn(App app)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            try (Clip clip = AudioSystem.getClip())
            {
               clip.open(app.appSound.getWaveSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(app.settings.getVolume());
               clip.start();
               do
               {
                  Thread.sleep(100);
               } while (clip.isRunning());
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
            catch (InterruptedException e)
            {
               // nothing
            }
            return null;
         }

      }.execute();

      trainerView.showResultBlue(app);
   }

   public void stopTraining(App app, Common common, Model model, View view, boolean finished)
   {
      view.getMainJPanel().moveToStatisticsPanel(app, common, model);

      if (finished)
      {
         if (newWordsToLearn > 0 && oldWordsToRepeat > 0)
         {
            JOptionPane.showMessageDialog(view.getjFrame(), "",
                 app.settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(TextImageWithPicture.make(app,
                       app.appImages.getReward(),
                        translator.realisticTranslate(app, 
                              Translation.WUNDERBAR__SIE_HABEN_DIESE),
                        translator.realisticTranslate(app, 
                              Translation.TRAININGSEINHEIT_ERFOLGREICH),
                        translator.realisticTranslate(app, Translation.BEENDET_),
                        translator.realisticTranslate(app, Translation.SIE_HABEN)
                              + " " + newWordsToLearn + " "
                              + translator.realisticTranslate(app, 
                                    Translation.NEUE_WOERTER),
                        translator.realisticTranslate(app, Translation.UND) + " "
                              + oldWordsToRepeat + " "
                              + translator.realisticTranslate(app, 
                                    Translation.BEKANNTE_WOERTER)
                              + " " + translator.realisticTranslate(app, 
                                    Translation.BEARBEITET_))));
         }
         else if (newWordsToLearn > 0 && oldWordsToRepeat == 0)
         {
            JOptionPane.showMessageDialog(view.getjFrame(), "",
                 app.settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(TextImageWithPicture.make(app,
                        app.appImages.getReward(),
                        translator.realisticTranslate(app, 
                              Translation.WUNDERBAR__SIE_HABEN_DIESE),
                        translator.realisticTranslate(app, 
                              Translation.TRAININGSEINHEIT_ERFOLGREICH),
                        translator.realisticTranslate(app, Translation.BEENDET_),
                        translator.realisticTranslate(app, Translation.SIE_HABEN)
                              + " " + newWordsToLearn + " "
                              + translator.realisticTranslate(app, 
                                    Translation.NEUE_WOERTER),
                        translator
                              .realisticTranslate(app, Translation.BEARBEITET_))));
         }
         else if (newWordsToLearn == 0 && oldWordsToRepeat > 0)
         {
            JOptionPane.showMessageDialog(view.getjFrame(), "",
                  app.settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(TextImageWithPicture.make(app,
                        app.appImages.getReward(),
                        translator.realisticTranslate(app, 
                              Translation.WUNDERBAR__SIE_HABEN_DIESE),
                        translator.realisticTranslate(app, 
                              Translation.TRAININGSEINHEIT_ERFOLGREICH),
                        translator.realisticTranslate(app, Translation.BEENDET_),
                        translator.realisticTranslate(app, Translation.SIE_HABEN)
                              + " " + oldWordsToRepeat + " "
                              + translator.realisticTranslate(app, 
                                    Translation.BEKANNTE_WOERTER)
                              + " " + translator.realisticTranslate(app, 
                                    Translation.BEARBEITET_))));
         }
      }
      else if (trainerView.getWordsRight().getText().equals("0"))
      {
         JOptionPane.showMessageDialog(view.getjFrame(), "",
               app.settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(TextImage.make(app, translator.realisticTranslate(app, 
                     Translation.DAS_TRAINING_WURDE_ABGEBROCHEN_))));
      }
      else if (trainerView.getWordsRight().getText().equals("1"))
      {
         JOptionPane.showMessageDialog(view.getjFrame(), "",
               app.settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(TextImage.make(app,
                     translator.realisticTranslate(app, 
                           Translation.DAS_TRAINING_WURDE_ABGEBROCHEN_),
                     translator.realisticTranslate(app, 
                           Translation.SIE_HABEN_1NE_RICHTIGE),
                     translator
                           .realisticTranslate(app, Translation.ANTWORT_GEGEBEN_))));
      }
      else
      {
         JOptionPane.showMessageDialog(view.getjFrame(), "",
               app.settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
               new ImageIcon(TextImage.make(app,
                     translator.realisticTranslate(app, 
                           Translation.DAS_TRAINING_WURDE_ABGEBROCHEN_),
                     translator.realisticTranslate(app, Translation.SIE_HABEN) + " "
                           + trainerView.getWordsRight().getText() + " "
                           + translator
                                 .realisticTranslate(app, Translation.RICHTIGE),
                     translator.realisticTranslate(app, 
                           Translation.ANTWORTEN_GEGEBEN_))));
      }

      saveTraining(app, model, view);
   }

   private void saveTraining(App app, Model model, View view)
   {
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY == this.fieldOfTraining)
      {
         return;
      }
      SaveTraining saveTraining = new SaveTraining();
      SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            int counter = 0;
            while (counter < 10 && !saveTraining.save(app, model, view))
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
               JOptionPane
                     .showMessageDialog(view.getjFrame(),
                           translator
                                 .realisticTranslate(app, Translation.FEHLERMELDUNG)
                                 + "\n"
                                 + translator.realisticTranslate(app, 
                                       Translation.DIE_TRAININGSDATEN_KONTEN)
                                 + "\n"
                                 + translator.realisticTranslate(app, 
                                       Translation.NICHT_GESPEICHERT_WERDEN_),
                                 app.settings.getWindowTitle(),
                           JOptionPane.WARNING_MESSAGE);
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
   public void toggleSound(App app)
   {
      app.settings.toggleSoundOnOff();
      trainerView.getSoundButton().setIcon(new ImageIcon(app.settings.getSound()));
   }

   @Override
   public void toggleLetterPictures(App app)
   {
      app.settings.toggleLetterImagesOnOff();
   }
}
