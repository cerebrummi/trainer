package vokabeltrainer.common.main;

import java.util.UUID;

import javax.swing.ProgressMonitor;

public final class ImportExpressions
{
   private Model model;
   
   public ImportExpressions(Model model)
   {
      this.model = model;
   }
   
   public boolean importExpressions(App app, Common common, String databaseName,
         boolean overwriteDatabaseNames, String databasePath)
   {
      ProgressMonitor bar = new ProgressMonitor(null,
            "Die Daten werden geladen.", "", 0, 100);
      int progress = 0;
      bar.setProgress(progress);
      bar.setMillisToPopup(1000);
      bar.setMillisToDecideToPopup(1000);

      UUID uuidSearchLock = UUID.randomUUID();
      try
      {
         if (model.data.lockDataBase(uuidSearchLock))
         {
            return (model.data.importDatabase(app, common, databasePath, databaseName,
                  overwriteDatabaseNames));
         }
         return false;
      }
      catch (Exception e)
      {
         // nothing
      }
      finally
      {
         model.data.unlockDataBase(uuidSearchLock);
      }
      return false;
   }

}
