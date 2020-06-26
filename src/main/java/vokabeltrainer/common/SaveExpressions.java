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
import vokabeltrainer.Settings;
import vokabeltrainer.common.backup.BackupExpressions;
import vokabeltrainer.panels.notifications.OkayExpressionsSavedNotification;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.ExpressionKind;

public final class SaveExpressions
{
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
            File customDir = new File(Settings.getExpressionPath());
            if (!customDir.exists())
            {
               try
               {
                  customDir.mkdirs();
               }
               catch (Exception e)
               {
                  JOptionPane.showMessageDialog(Common.getjFrame(),
                        "Fehler beim Speichern.\nÄndern Sie den Ort zum Abspeichern in den Einstellungen.",
                        "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
            }

            if(customDir.list().length > 1)
            {
               String backupErrorMessage = new BackupExpressions().makeBackupNow();
               if (!backupErrorMessage.isEmpty() && JOptionPane.showConfirmDialog(
                     Common.getjFrame(),
                     "Es gab einen Fehler beim Backup.\nWollen Sie trotzdem speichern?\n"
                           + backupErrorMessage,
                     "Fehlermeldung", JOptionPane.WARNING_MESSAGE) == 1)
               {
                  return false;
               }
            }

            for (ExpressionKind kind : ExpressionKind.values())
            {
               save(kind);
               progress += 100 / ExpressionKind.values().length;
               bar.setProgress(progress);
            }
            Preferences preferences = Preferences.userRoot()
                  .node(Settings.getNode());
            preferences.putInt(Settings.getExpressionNode(), counter);
            saveDeletedExpressions();
            progress = 100;
            bar.setProgress(progress);
            OkayExpressionsSavedNotification.display();
            return true;
         }
         catch (Exception e)
         {
            e.printStackTrace();
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
      File file = new File(Settings.getExpressionPath() + File.separator + "DELETED.txt");
      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      for (Expression expression : Data.getDeletedMapValues())
      {
         joiner.add(expression.getExpressionPrintLine());
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();
   }

   private void save(ExpressionKind kind) throws IOException
   {
      File file = new File(
            Settings.getExpressionPath() + File.separator + kind.name() + ".txt");
      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      for (Expression expression : getValues(kind))
      {
         joiner.add(expression.getExpressionPrintLine());
         counter++;
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();
   }

   private Collection<Expression> getValues(ExpressionKind kind)
   {
      List<Expression> list = new ArrayList<>();

      for (Expression expression : Data.getAlleMapValues())
      {
         if (expression.getKind().equals(kind))
         {
            list.add(expression);
         }
      }

      for (Expression expression : Data.getNewMapValues())
      {
         if (expression.getKind().equals(kind))
         {
            list.add(expression);
         }
      }

      return list;
   }
}
