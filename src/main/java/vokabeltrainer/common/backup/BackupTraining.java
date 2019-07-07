package vokabeltrainer.common.backup;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.StringJoiner;

import vokabeltrainer.Settings;
import vokabeltrainer.types.Language;

public class BackupTraining
{
   private StringJoiner backupErrorMessage = new StringJoiner("\n");

   public String makeBackupNow()
   {
      backupErrorMessage.setEmptyValue("");

      backup(Language.GERMAN);
      backup(Language.HEBREW);

      return backupErrorMessage.toString();
   }

   private void backup(Language languageDirection)
   {
      File currentFile = new File(Settings.getTrainingPath() + File.separator
            + languageDirection.name() + ".txt");
      File backupFile = new File(Settings.getTrainingPath() + File.separator
            + languageDirection.name() + "backup.txt");
      
      if(currentFile.exists())
      {
         try
         {
            Files.copy(currentFile.toPath(), backupFile.toPath(),
                  StandardCopyOption.REPLACE_EXISTING);
         }
         catch (Exception e)
         {
            backupErrorMessage.add("TrainingBackup "+ languageDirection.name()+" "+ backupFile.getAbsolutePath()
                  + " new backup failed: " + e.getMessage());
         }
      }
   } 
}
