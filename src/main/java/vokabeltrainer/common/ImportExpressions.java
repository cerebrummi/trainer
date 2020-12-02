package vokabeltrainer.common;

import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.ProgressMonitor;

import vokabeltrainer.Settings;
import vokabeltrainer.panels.settings.InputDatabaseNameDialog;

public final class ImportExpressions
{

   private String databaseName;
   private String databasePath;
   private boolean overwriteDatabaseNames;

   public boolean importExpressions()
   {
      ProgressMonitor bar = new ProgressMonitor(null,
            "Die Daten werden gespeichert.", "", 0, 100);
      int progress = 0;
      bar.setProgress(progress);
      bar.setMillisToPopup(1000);
      bar.setMillisToDecideToPopup(1000);

      UUID uuidSearchLock = UUID.randomUUID();
      try
      {
         if (Data.lockDataBase(uuidSearchLock))
         {
            InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(
                  "Bitte einen Namen für die Datenbank eingeben.");
            dialog.setVisible(true);

            if (!dialog.isStartImport())
            {
               dialog.dispose();
               return false;
            }

            if (dialog.getDatabaseName().isBlank())
            {
               dialog.dispose();
               return false;
            }

            databaseName = dialog.getDatabaseName();
            overwriteDatabaseNames = dialog.isOverwrite();
            dialog.dispose();

            JFileChooser folderChooser = new JFileChooser(
                  Settings.getExpressionPath());
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = folderChooser.showOpenDialog(Common.getjFrame());

            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
               databasePath = folderChooser.getSelectedFile().getPath();

               return (Data.importDatabase(databasePath, databaseName, overwriteDatabaseNames));
            }

            return false;
         }
      }
      catch (Exception e)
      {

      }
      finally
      {
         Data.unlockDataBase(uuidSearchLock);
      }
      return false;
   }

}
