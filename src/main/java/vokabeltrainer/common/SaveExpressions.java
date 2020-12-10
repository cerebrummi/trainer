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
   private static final String HEADER_CSV = "UUID\tUrsprung\tDatenbank\tchapter\tGerman\tHebrew\texpression kinds\tgender\tnumerus\tgrammatical person\tbinjan\tverb conjugation\tverb strength\tverb type\tzusätzliche Informationen\tsearchwords German\tsearchwords Hebrew\tletzte Änderung";
   private int counter;
   private String exportpath = "";
   private boolean takeSelectedOnlyIntoAccount;
   private boolean takeOriginIntoAccount;
   private String origin;
   private boolean overwriteDatabaseNames;
   private String databaseName;

   public SaveExpressions()
   {

   }

   public SaveExpressions(String exportpath)
   {
      this.exportpath = exportpath + File.separator
            + Settings.getExpressionFolder();
   }

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
            if (this.exportpath.isEmpty())
            {
               File customDir = new File(Settings.getExpressionPathFolder());
               if (!customDir.exists())
               {
                  if (!DirectoryHelper.makeExpressionDirectory(customDir))
                  {
                     JOptionPane.showMessageDialog(Common.getjFrame(),
                           "Es hat beim Speichern einen Fehler gegeben.\n"
                                 + "Wählen Sie einen anderen Speicherort.",
                           "Fehler", JOptionPane.ERROR_MESSAGE);
                  }
               }
            }
            else
            {
               File customDir = new File(exportpath);
               if (!customDir.exists())
               {
                  if (!DirectoryHelper.makeExpressionDirectory(customDir))
                  {
                     JOptionPane.showMessageDialog(Common.getjFrame(),
                           "Es hat beim Speichern einen Fehler gegeben.\n"
                                 + "Wählen Sie einen anderen Speicherort.",
                           "Fehler", JOptionPane.ERROR_MESSAGE);
                  }
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
      File file;
      if (exportpath.isEmpty())
      {
         file = new File(Settings.getExpressionPathFolder() + File.separator
               + "DELETED.csv");
      }
      else
      {
         file = new File(exportpath + File.separator + "DELETED.csv");
      }

      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add(HEADER_CSV);
      for (Expression expression : Data.getDeletedMapValues())
      {
         if (isMarkedandMarked(expression) || isOriginandOrigin(expression)
               || isAll())
         {
            joiner.add(expression.getExpressionPrintLineForSaving());
         }
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();
   }

   private boolean isAll()
   {
      return !takeSelectedOnlyIntoAccount && !takeOriginIntoAccount;
   }

   private boolean isOriginandOrigin(Expression expression)
   {
      return takeOriginIntoAccount
            && expression.getChapter().getDatabaseName().equals(origin);
   }

   private boolean isMarkedandMarked(Expression expression)
   {
      return takeSelectedOnlyIntoAccount && expression.isSelected();
   }

   private void save(LetterForSaving letter) throws IOException
   {
      File file;
      if (exportpath.isEmpty())
      {
         file = new File(Settings.getExpressionPathFolder() + File.separator
               + letter.name() + ".csv");
      }
      else
      {
         file = new File(exportpath + File.separator + letter.name() + ".csv");
      }
      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add(HEADER_CSV);
      for (Expression expression : getValues(letter))
      {
         if (isMarkedandMarked(expression) || isOriginandOrigin(expression)
               || isAll())
         {
            if (overwriteDatabaseNames && databaseName != null)
            {
               joiner.add(
                     expression.getExpressionPrintLineForSaving(databaseName));
            }
            else
            {
               joiner.add(expression.getExpressionPrintLineForSaving());
            }
            counter++;
         }
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

   public void save(String databaseName, boolean overwriteDatabaseNames)
   {
      this.databaseName = databaseName;
      this.overwriteDatabaseNames = overwriteDatabaseNames;
      save();
   }

   @SuppressWarnings("unused")
   public void save(String databaseName, boolean overwriteDatabaseNames,
         boolean b)
   {
      this.databaseName = databaseName;
      this.overwriteDatabaseNames = overwriteDatabaseNames;
      this.takeSelectedOnlyIntoAccount = true;
      save();
   }

   public void save(String databaseName, boolean overwriteDatabaseNames,
         String databaseChoosen)
   {
      this.databaseName = databaseName;
      this.overwriteDatabaseNames = overwriteDatabaseNames;
      this.takeOriginIntoAccount = true;
      this.origin = databaseChoosen;
      save();
   }
}
