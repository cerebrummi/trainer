package vokabeltrainer.common;

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

import vokabeltrainer.CerebrummiNodes;
import vokabeltrainer.Settings;
import vokabeltrainer.panels.notifications.OkayExpressionsSavedNotification;
import vokabeltrainer.types.Expression;
import vokabeltrainer.cmd.DirectoryHelper;

public final class SaveExpressions
{
   private static final String HEADER_CSV = "UUID\tchapter\tGerman\tHebrew\tHebrew in Latin\texpression kinds\tgender\tnumerus\tgrammatical person\tbinjan\tverb conjugation\tverb strength\tverb type\tzusätzliche Informationen\tsearchwords German\tsearchwords Hebrew\tletzte Änderung";
   private int counter;

   public boolean save()
   {
      ProgressMonitor bar = new ProgressMonitor(null,
            "Die Daten werden gespeichert.", "", 0, 100);
      int progress = 0;
      bar.setProgress(progress);
      bar.setMillisToPopup(1000);
      bar.setMillisToDecideToPopup(1000);

      UUID uuidSearchLock = UUID.randomUUID();
      if (Data.lockDataBase(uuidSearchLock))
      {
         try
         {
            counter = 0;
            File customDir = new File(Settings.getExpressionPathFolder());
            if (!customDir.exists())
            {
               if (!DirectoryHelper.makeExpressionDirectory(customDir))
               {
                  JOptionPane.showMessageDialog(Common.getjFrame(),
                        "Es hat beim Speichern einen Fehler gegeben.\n"
                              + "Wählen Sie unter Einstellungen einen anderen Speicherort.",
                        "Fehler", JOptionPane.ERROR_MESSAGE);
               }
            }

            for (LetterForSaving letter : LetterForSaving.values())
            {
               save(letter);
               progress += 100 / LetterForSaving.values().length;
               bar.setProgress(progress);
            }
            Preferences preferences = Preferences.userRoot()
                  .node(CerebrummiNodes.getNode());
            preferences.putInt(CerebrummiNodes.getExpressionNode(), counter);
            saveDeletedExpressions();
            Data.integrateNewExpressions();
            progress = 100;
            bar.setProgress(progress);
            OkayExpressionsSavedNotification.display();
            return true;
         }
         catch (Exception e)
         {
            JOptionPane
                  .showMessageDialog(Common.getjFrame(),
                        "Es hat beim Speichern einen Fehler gegeben.\n"
                              + e.getMessage(),
                        "Fehler", JOptionPane.ERROR_MESSAGE);
         }
         finally
         {
            Data.unlockDataBase(uuidSearchLock);
         }
      }
      return false;
   }

   private void saveDeletedExpressions() throws IOException
   {
      File file = new File(Settings.getExpressionPathFolder() + File.separator
            + "DELETED.csv");
      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add(HEADER_CSV);
      for (Expression expression : Data.getDeletedMapValues())
      {
         joiner.add(expression.getExpressionPrintLineForSaving());
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();
   }

   private void save(LetterForSaving letter) throws IOException
   {
      File file = new File(Settings.getExpressionPathFolder() + File.separator
            + letter.name() + ".csv");
      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add(HEADER_CSV);
      for (Expression expression : getValues(letter))
      {
         joiner.add(expression.getExpressionPrintLineForSaving());
         counter++;
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();
   }

   private Collection<Expression> getValues(LetterForSaving letter)
   {
      List<Expression> list = new ArrayList<>();

      for (Expression expression : Data.getAlleMapValues())
      {
         if (expression.isDoNotChange())
         {
            continue;
         }
         if (expression.getLetterForSaving().equals(letter))
         {

            list.add(expression);
         }
      }

      for (Expression expression : Data.getNewMapValues())
      {
         if (expression.getLetterForSaving().equals(letter))
         {
            list.add(expression);
         }
      }

      return list;
   }
}
