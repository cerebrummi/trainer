package vokabeltrainer.common.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

import vokabeltrainer.types.Expression;
import vokabeltrainer.types.LanguageDirection;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public final class SaveTraining
{
   private int counter;

   public boolean save(App app, Model model, View view)
   {
      ProgressMonitor bar = new ProgressMonitor(null,
            "Die Traingsdaten werden gespeichert.", "", 0, 100);
      int progress = 0;
      bar.setProgress(progress);
      bar.setMillisToPopup(1000);
      bar.setMillisToDecideToPopup(1000);

      UUID uuidSearchLock = UUID.randomUUID();
      if (model.data.lockDataBase(uuidSearchLock))
      {
         try
         {
            counter = 0;
            File customDir = new File(app.settings.getTrainingPath());
            if (!customDir.exists())
            {
               try
               {
                  customDir.mkdirs();
               }
               catch (Exception e)
               {
                  JOptionPane.showMessageDialog(view.getjFrame(),
                        "Fehler beim Speichern des Trainings.", "Fehlermeldung",
                        JOptionPane.ERROR_MESSAGE);
                  return false;
               }
            }

            for (LanguageDirection languageDirection : LanguageDirection
                  .values())
            {
               doSave(app, model, languageDirection);
               progress += 100 / ExpressionKind.values().length;
               bar.setProgress(progress);
            }

            Preferences preferences = Preferences.userRoot()
                  .node(CerebrummiNodes.getNode());
            preferences.putInt(CerebrummiNodes.getTrainingNode(), counter);

            progress = 100;
            bar.setProgress(progress);
            return true;
         }
         catch (Exception e)
         {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view.getjFrame(),
                  "Es hat beim Speichern des Trainings einen Fehler gegeben.\n"
                        + e.getMessage(),
                  "Fehler", JOptionPane.ERROR_MESSAGE);
         }
         finally
         {
            model.data.unlockDataBase(uuidSearchLock);
         }
      }
      return false;
   }

   private void doSave(App app, Model model, LanguageDirection languageDirection) throws IOException
   {
      File file = new File(app.settings.getTrainingPath() + File.separator
            + languageDirection.name() + ".txt");
      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      for (Expression expression : getAllValues(model))
      {
         if (languageDirection.name()
               .contains(expression.getLL().getLltype().name()))
         {
            if (expression.getTrainingStatus(languageDirection.getDirection())
                  .isTrainingStarted())
            {
               joiner.add(expression.getTrainingPrintLine(languageDirection));
               counter++;
            }
         }
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();

   }

   private Collection<Expression> getAllValues(Model model)
   {
      List<Expression> list = new ArrayList<>();
      list.addAll(model.data.getAlleMapValues());
      list.addAll(model.data.getNewMapValues());
      return list;
   }
}
