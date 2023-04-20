package vokabeltrainer.common;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

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
		private boolean isImageForExpressionAvailable(UUID uuid) 
		{
			// TODO
			return false;
		}
	}
	
	
	
}
