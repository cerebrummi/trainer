package vokabeltrainer.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.prefs.Preferences;

import javax.sound.sampled.AudioFormat;

import vokabeltrainer.cmd.DirectoryHelper;

public final class SoundData
{

   private static final AtomicBoolean databaseInUse = new AtomicBoolean(false);
   private static volatile UUID uuidDataBaseLock;
   private static SoundDataBase database;
   public static AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true,
	         false);

   private SoundData()
   {

   }

   static void initImageDataBase()
   {
      database = new SoundDataBase();
   }

   static boolean lockDataBase(UUID uuid)
   {
      if (databaseInUse.get())
      {
         return false;
      }

      uuidDataBaseLock = uuid;
      databaseInUse.set(true);
      return true;
   }

   static boolean unlockDataBase(UUID uuid)
   {
      if (uuidDataBaseLock.equals(uuid))
      {
         databaseInUse.set(false);
         return true;
      }
      return false;
   }

   private static void checkDataBaseInUseAndWait()
   {
      while (databaseInUse.get())
      {
         try
         {
            Thread.sleep(100);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
   }

   private static SoundDataBase getDataBaseAtomic()
   {
      checkDataBaseInUseAndWait();
      return database;
   }

   public static boolean isSoundForExpressionAvailable(UUID uuid)
   {
      if (uuid == null)
      {
         return false;
      }
      return getDataBaseAtomic().isSoundForExpressionAvailable(uuid);
   }

   public static void saveSound(String path, UUID uuid)
   {
      if (uuid == null)
      {
         return;
      }
      getDataBaseAtomic().saveSound(uuid, path);
   }

   public static byte[] loadSound(UUID uuid)
   {
      if (uuid == null)
      {
         return null;
      }
      return getDataBaseAtomic().loadSound(uuid);
   }

   public static byte[] loadSoundOriginal(UUID uuid)
   {
      if (uuid == null)
      {
         return null;
      }
      return getDataBaseAtomic().loadSoundOriginal(uuid);
   }

   public static void deleteSound(UUID uuid)
   {
      if (uuid == null)
      {
         return;
      }
      getDataBaseAtomic().deleteSound(uuid);
   }

   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // ################### SoundDataBase #######################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################

   private static class SoundDataBase
   {
      private final ConcurrentMap<UUID, byte[]> soundMap = new ConcurrentHashMap<>(
            findNumberOfAllVocabulary() + 100);

      SoundDataBase()
      {
         if (!checkDirectory())
         {
            return;
         }
         readSoundsAvailable();
      }

      private void deleteSound(UUID uuid)
      {
         try
         {
            Files.delete(Paths.get(Settings.getImagePath() + File.separator
                  + uuid.toString()));
         }
         catch (IOException e)
         {
            // nothing
         }

         soundMap.remove(uuid);
      }

      private byte[] loadSound(UUID uuid)
      {
         return soundMap.get(uuid);
      }
      
      private byte[] loadSoundOriginal(UUID uuid)
      {
    	  InputStream in = null;
         try
         {
            if(Files.walk(Paths.get(Settings.getSoundPath()))
                  .filter(Files::isRegularFile)
                  .filter(file -> file.getFileName().toString().contains(uuid.toString())) == null)
            {
               return null;
            }
            
            Path path = Files.walk(Paths.get(Settings.getSoundPath()))
                  .filter(Files::isRegularFile)
                  .filter(file -> file.getFileName().toString().contains(uuid.toString()))
                  .findFirst().get();
            
            in = new FileInputStream(path.toFile());
            
            return in.readAllBytes();
         }
         catch (IOException e)
         {
            // nothing
         }
         finally
         {
        	 try 
        	 {
				in.close();
			 } catch (IOException e) 
        	 {
				// nothing
			 }
         }
         return null;
      }

      private boolean isSoundForExpressionAvailable(UUID uuid)
      {
         return soundMap.containsKey(uuid);
      }

      private void saveSound(UUID uuid, String path)
      {
         if (!checkDirectory())
         {
            return;
         }


         try
         {
            Files.copy(Paths.get(path),
                  Paths.get(Settings.getSoundPath() + File.separator
                        + uuid.toString()),
                  StandardCopyOption.REPLACE_EXISTING);
         }
         catch (IOException e)
         {
            return;
         }

         addToSoundMap(uuid.toString());
      }

      private boolean checkDirectory()
      {
         File customDir = new File(Settings.getImagePath());
         if (!customDir.exists())
         {
            if (!DirectoryHelper.makeDirectory(customDir))
            {
               return false;
            }
         }
         return true;
      }

      private int findNumberOfAllVocabulary()
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         int numberOfVocabulary = preferences
               .getInt(CerebrummiNodes.getExpressionNode(), 0);
         if (numberOfVocabulary > 30000)
         {
            numberOfVocabulary = 30000;
         }
         return numberOfVocabulary;
      }

      // #########################################################
      // ################# available sounds ######################
      // #########################################################
      private void readSoundsAvailable()
      {
         try
         {
            Files.walk(Paths.get(Settings.getSoundPath()))
                  .filter(Files::isRegularFile)
                  .forEach(path -> addToSoundMap(path.getFileName().toString()));
         }
         catch (IOException e)
         {
            // nothing
         }
      }

      private void addToSoundMap(String uuidString)
      {
         UUID uuid = UUID.fromString(uuidString);
         

         try(InputStream in = new FileInputStream(Settings.getSoundPath()+uuidString))
         {
            soundMap.put(uuid, in.readAllBytes());
         }
         catch (Exception e)
         {
            // nothing;
         }
      }
   }
}
