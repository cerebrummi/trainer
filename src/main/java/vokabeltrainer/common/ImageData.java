package vokabeltrainer.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.prefs.Preferences;

import vokabeltrainer.CerebrummiNodes;
import vokabeltrainer.Settings;
import vokabeltrainer.cmd.DirectoryHelper;

public final class ImageData {

	private static final AtomicBoolean databaseInUse = new AtomicBoolean(false);
	private static volatile UUID uuidDataBaseLock;
	private static ImageDataBase database;

	private ImageData() {

	}

	static void initImageDataBase() 
	{
		database = new ImageDataBase();
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
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	private static ImageDataBase getDataBaseAtomic() 
	{
		checkDataBaseInUseAndWait();
		return database;
	}

	public static boolean isImageForExpressionAvailable(UUID uuid) 
	{
		if (uuid == null)
		{
			return false;
		}
		return getDataBaseAtomic().isImageForExpressionAvailable(uuid);
	}
	
	public static void saveImage(String image, UUID uuid) 
	{
		if (uuid == null)
		{
			return;
		}
		getDataBaseAtomic().saveImage(image, uuid);
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
	// ################### ImageDataBase #######################
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

	private static class ImageDataBase 
	{
		private final ConcurrentMap<UUID, String> imageMap = new ConcurrentHashMap<>(
	            findNumberOfAllVocabulary() + 100);
		
		ImageDataBase()
		{
			if(!checkDirectory())
			{
				return;
			}
			readImagesAvailable();
		}
		
		private boolean isImageForExpressionAvailable(UUID uuid) 
		{
			return imageMap.containsKey(uuid);
		}

		private void saveImage(String image, UUID uuid) 
		{
			if(!checkDirectory())
			{
				return;
			}
		    
            String imageType = findImageType(image);

            if(imageType == null)
            {
            	return;
            }
            
            try 
            {
				Files.copy(Paths.get(image), Paths.get(Settings.getImagePath() + File.separator + uuid.toString() + imageType), StandardCopyOption.REPLACE_EXISTING);
			} 
            catch (IOException e) 
            {
				// nothing
			}
		}

		private String findImageType(String image) 
		{
			image = image.toLowerCase();
			int length = image.length();
            char dot3 = image.charAt(length-3);
            char dot4 = image.charAt(length-4);
            int dotIndex;
            if(dot3 == '.')
            {
            	dotIndex = 3;
            }
            else if(dot4 == '.')
            {
            	dotIndex = 4;
            }
            else
            {
            	return null;
            }             
            String imageType = image.substring(length - dotIndex);
			return imageType;
		}

		private boolean checkDirectory() 
		{
			File customDir = new File(Settings.getImagePath());
            if (!customDir.exists())
            {
            	if(!DirectoryHelper.makeDirectory(customDir))
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
	      // ################# available images ######################
	      // #########################################################
	      private void readImagesAvailable()
	      {
	    	  try 
	    	  {
				Files.walk(Paths.get(Settings.getImagePath()))
				     .filter(Files::isRegularFile)
				     .forEach(file -> addToImageMap(file));
	    	  } 
	    	  catch (IOException e) 
	    	  {
				// nothing
	    	  }
	      }

		private void addToImageMap(Path file) 
		{
			File image = file.toFile();
			String imageType = findImageType(file.toString());
            if(imageType == null)
            {
            	return;
            }
            UUID uuid = UUID.fromString(image.getName().substring(0,image.getName().length() - imageType.length()));
            imageMap.putIfAbsent(uuid, imageType);
		}
	}
}
