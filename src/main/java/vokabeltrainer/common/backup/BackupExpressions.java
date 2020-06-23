package vokabeltrainer.common.backup;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.UUID;

import vokabeltrainer.Settings;

public class BackupExpressions
{
   private StringJoiner backupErrorMessage = new StringJoiner("\n");

   public String makeBackupNow()
   {
      backupErrorMessage.setEmptyValue("");

      File customDir = new File(Settings.getExpressionPath());
      
      makeNewBackup(customDir, makeUniqueDirectoryName());

      if (findNumberofBackups(customDir) >= Settings.getNumberOfBackups())
      {
         try
         {
            dealWithBackupHour(findOldestBackupMinuteDir(customDir), customDir);
         }
         catch (Exception e)
         {
            backupErrorMessage.add("[01] Backup: " + e.getMessage());
         }
      }

      while (findNumberofBackups(customDir) >= Settings.getNumberOfBackups())
      {
         try
         {
            Files.walk(findOldestBackupMinuteDir(customDir).toPath())
                  .sorted(Comparator.reverseOrder()).map(Path::toFile)
                  .forEach(File::delete);
         }
         catch (Exception e)
         {
            backupErrorMessage.add("[05] " + BackupExpressionType.BackupMinute.name()
                  + ": delete old backup: " + e.getMessage());
            break;
         }
      }

      return backupErrorMessage.toString();
   }

   private int findNumberofBackups(File customDir)
   {
      int counter = 0;
      for (String name : customDir.list())
      {
         if (name.startsWith(BackupExpressionType.BackupMinute.name()))
         {
            counter++;
         }
      }
      return counter;
   }

   private File findOldestBackupMinuteDir(File customDir)
   {
      long lastModified = Long.MAX_VALUE;
      File oldestBackupDir = null;

      for (String child : customDir.list())
      {
         File file = new File(Settings.getExpressionPath() + File.separator + child);
         if (child.startsWith(BackupExpressionType.BackupMinute.name()))
         {
            if (file.lastModified() < lastModified)
            {
               lastModified = file.lastModified();
               oldestBackupDir = file;
            }
         }
      }
      return oldestBackupDir;
   }

   private void makeNewBackup(File fromDir, String backupDirName)
   {
      if (fromDir.list().length == 0)
      {
         return;
      }
      else
      {
         File file = new File(
               Settings.getExpressionPath() + File.separator + backupDirName);
         if (!file.exists())
         {
            file.mkdirs();
         }
      }

      for (String filename : fromDir.list())
      {
         File originFile = new File(
               fromDir.getPath() + File.separator + filename);
         if (filename.endsWith(".txt"))
         {
            File target = new File(Settings.getExpressionPath() + File.separator
                  + backupDirName + File.separator + filename);
            try
            {
               Files.copy(originFile.toPath(), target.toPath(),
                     StandardCopyOption.REPLACE_EXISTING);
            }
            catch (Exception e)
            {
               backupErrorMessage.add("[02] " + backupDirName + File.separator
                     + filename + " new backup failed: " + e.getMessage());
            }
         }
      }
   }

   private String makeUniqueDirectoryName()
   {
      String name = BackupExpressionType.BackupMinute.name()
            + UUID.randomUUID().toString();
      File file = new File(Settings.getExpressionPath() + File.separator + name);
      if (!file.exists())
      {
         return name;
      }
      return makeUniqueDirectoryName();
   }

   private LocalDate getLastModified(long lastModified)
   {
      return Instant.ofEpochMilli(lastModified).atZone(ZoneId.systemDefault())
            .toLocalDate();
   }
   
   private LocalDateTime getLastModifiedTime(long lastModified)
   {
      return Instant.ofEpochMilli(lastModified).atZone(ZoneId.systemDefault())
            .toLocalDateTime();
   }

   
   private void dealWithBackupHour(File oldestBackupDir, File customDir)
   {
      LocalDateTime lastHour = LocalDateTime.now().minusHours(1L);

      File backupHourDir = new File(
            Settings.getExpressionPath() + File.separator + BackupExpressionType.BackupHour.name());
      if (!backupHourDir.exists())
      {
         backupHourDir.mkdirs();
         makeNewBackup(oldestBackupDir, BackupExpressionType.BackupHour.name());
      }
      else if (backupHourDir.exists()
            && getLastModifiedTime(backupHourDir.lastModified()).isBefore(lastHour))
      {
         dealWithBackupDay(backupHourDir, customDir);
         backupNEWdeleteOLD(oldestBackupDir, backupHourDir,
               BackupExpressionType.BackupHour);
      }
   }
   
   private void dealWithBackupDay(File backupHourDir, File customDir)
   {
      LocalDate yesterday = LocalDate.now().minusDays(1L);

      File backupDayDir = new File(
            Settings.getExpressionPath() + File.separator + BackupExpressionType.BackupDay.name());
      if (!backupDayDir.exists())
      {
         backupDayDir.mkdirs();
         makeNewBackup(backupHourDir, BackupExpressionType.BackupDay.name());
      }
      else if (backupDayDir.exists()
            && getLastModified(backupDayDir.lastModified()).isBefore(yesterday))
      {
         dealWithBackupWeek(backupDayDir, customDir);
         backupNEWdeleteOLD(backupHourDir, backupDayDir,
               BackupExpressionType.BackupDay);
      }
   }

   private void dealWithBackupWeek(File backupDayDir, File customDir)
   {
      LocalDate lastWeek = LocalDate.now().minusWeeks(1L);
      File backupWeekDir = new File(
            Settings.getExpressionPath() + File.separator + BackupExpressionType.BackupWeek.name());
      if (!backupWeekDir.exists())
      {
         backupWeekDir.mkdirs();
         makeNewBackup(backupDayDir, BackupExpressionType.BackupWeek.name());
      }
      else if (backupWeekDir.exists()
            && getLastModified(backupWeekDir.lastModified()).isBefore(lastWeek))
      {
         dealWithBackupMonth(backupWeekDir, customDir);
         backupNEWdeleteOLD(backupDayDir, backupWeekDir, BackupExpressionType.BackupWeek);
      }
   }

   private void dealWithBackupMonth(File backupWeekDir, File customDir)
   {
      LocalDate lastMonth = LocalDate.now().minusMonths(1L);
      File backupMonthDir = new File(Settings.getExpressionPath() + File.separator
            + BackupExpressionType.BackupMonth.name());
      if (!backupMonthDir.exists())
      {
         backupMonthDir.mkdirs();
         makeNewBackup(backupWeekDir, BackupExpressionType.BackupMonth.name());
      }
      else if (backupMonthDir.exists()
            && getLastModified(backupMonthDir.lastModified())
                  .isBefore(lastMonth))
      {
         dealWithBackupYear(backupMonthDir, customDir);
         backupNEWdeleteOLD(backupWeekDir, backupMonthDir,
               BackupExpressionType.BackupMonth);
      }
   }

   private void dealWithBackupYear(File backupMonthDir, File customDir)
   {
      LocalDate lastyear = LocalDate.now().minusYears(1L);
      File backupYearDir = new File(
            Settings.getExpressionPath() + File.separator + "backupYear");
      if (!backupYearDir.exists())
      {
         backupYearDir.mkdirs();
         makeNewBackup(backupMonthDir, BackupExpressionType.BackupYear.name());
      }
      else if (backupYearDir.exists()
            && getLastModified(backupYearDir.lastModified()).isBefore(lastyear))
      {
         backupNEWdeleteOLD(backupMonthDir, backupYearDir,
               BackupExpressionType.BackupYear);
      }
   }

   private void backupNEWdeleteOLD(File backupOLDDir, File backupNEWDir,
         BackupExpressionType temporalKind)
   {    
      // remove files from backupNEWDir
      if(backupNEWDir.exists())
      {
         for(File child : backupNEWDir.listFiles())
         {
            child.delete();
         }
      }
      
      try
      {
         Files.move(backupOLDDir.toPath(), backupNEWDir.toPath(),
               StandardCopyOption.REPLACE_EXISTING);
      }
      catch (Exception e)
      {
         backupErrorMessage.add("[03] " + temporalKind.name()
               + ": write new backup: " + e.getMessage());
         
         e.printStackTrace();
      }
   }
}
