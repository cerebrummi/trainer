package vokabeltrainer;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import vokabeltrainer.common.Main;
import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.types.LanguageSettings;

public class Settings
{
   private final static Font buttonFont = Main.getGermanFont(16F);
   private final static Font toolbarButtonFont = Main.getHeaderFont(26F);
   private final static Font secondaryToolbarButtonFont = Main
         .getHeaderFont(18F);

   private static boolean soundOn = true;
   private static String chosenExpressionPath = null;
   private static float volume = -20;
   private static boolean letterImagesOn = true;
   private static boolean simpleHebrewInput = true;
   private static String version = "3.0";

   private static LinkedList<Database> oldChosenDatabases = new LinkedList<>();
   private static LinkedList<Database> chosenDatabases = new LinkedList<>();
   private static Database[] availableDatabases = {
         Database.GRUNDWORTSCHATZADAADAMA 
         };
   
   private static String rememberDatabaseForInput = "";
   private static String rememberChapterForInput = "";

   private Settings()
   {
   }

   public static String getVersion()
   {
      return version;
   }

   public static int dictionaryTableRowHeight()
   {
      return 225;
   }

   public static Font getToolBarButtonFont()
   {
      return toolbarButtonFont;
   }

   public static Font getSecondaryToolBarButtonFont()
   {
      return secondaryToolbarButtonFont;
   }

   public static Font getButtonFont()
   {
      return buttonFont;
   }

   public static LanguageSettings getLanguage()
   {
      return LanguageSettings.GERMAN;
   }

   public static String getExpressionFolder()
   {
      return "cerebrummi-hebrewtrainer";
   }

   public static String getExpressionPath()
   {
      if (chosenExpressionPath == null)
      {
         return System.getProperty("user.home");
      }
      return chosenExpressionPath;
   }

   public static String getExpressionPathFolder()
   {
      return getExpressionPath() + File.separator + getExpressionFolder();
   }

   public static String getTrainingPath()
   {
      return getExpressionPath() + File.separator + getTrainingFolder();
   }

   private static String getTrainingFolder()
   {
      return getExpressionFolder() + "-training";
   }
   
   public static String getLanguagePath()
   {
      return getExpressionPath() + File.separator + getLanguagesFolder();
   }
   
   private static String getLanguagesFolder()
   {
      return getExpressionFolder() + "-languages";
   }

   public static int getKeyboardWidth()
   {
      return 474;
   }

   public static String getWindowTitle()
   {
      return "Cerebrummi©";
   }

   public static BufferedImage getSound()
   {
      if (soundOn)
      {
         return ApplicationImages.getSoundOn();
      }
      return ApplicationImages.getSoundOff();
   }

   public static boolean isSoundOn()
   {
      return soundOn;
   }

   public static void toggleSoundOnOff()
   {
      soundOn = !soundOn;
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSoundNode(), soundOn);
   }

   public static void setSoundOn(boolean soundOn)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSoundNode(), soundOn);
      Settings.soundOn = soundOn;
   }

   public static boolean isLetterImagesOn()
   {
      return letterImagesOn;
   }

   public static void toggleLetterImagesOnOff()
   {
      letterImagesOn = !letterImagesOn;
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getLetterPicturesNode(),
                  letterImagesOn);
   }

   public static void setLetterImagesOn(boolean letterImagesOn)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getLetterPicturesNode(),
                  letterImagesOn);
      Settings.letterImagesOn = letterImagesOn;
   }

   public static void setChoosenExpressionPath(String choosenExpressionPath)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .put(CerebrummiNodes.getChoosenExpressionPathNode(),
                  choosenExpressionPath);

      Settings.chosenExpressionPath = choosenExpressionPath;
   }

   public static void setChosenDatabases(List<Database> chosenDatabases)
   {
      Settings.chosenDatabases = new LinkedList<>();
      Settings.chosenDatabases.addAll(chosenDatabases);
   }

   public static boolean isDatabaseChoosen(Database database)
   {
      return Settings.chosenDatabases.contains(database);
   }

   public static LinkedList<Database> getChosenDatabases()
   {
      return Settings.chosenDatabases;
   }

   public static void addChosenDatabase(Database chosen)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      try
      {
         if (!preferences.nodeExists(CerebrummiNodes.getChoosenDatabases()))
         {
            preferences.put(CerebrummiNodes.getChoosenDatabases(), "");
         }
      }
      catch (BackingStoreException e)
      {
         // nothing
      }
      preferences = preferences.node(CerebrummiNodes.getChoosenDatabases());
      preferences.putBoolean(chosen.name().toLowerCase(), true);

      Settings.chosenDatabases.add(chosen);
   }

   public static void removeChosenDatabase(Database chosen)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      try
      {
         if (!preferences.nodeExists(CerebrummiNodes.getChoosenDatabases()))
         {
            preferences.put(CerebrummiNodes.getChoosenDatabases(), "");
         }
      }
      catch (BackingStoreException e)
      {
         // nothing
      }
      preferences = preferences.node(CerebrummiNodes.getChoosenDatabases());

      preferences.putBoolean(chosen.name().toLowerCase(), false);

      Settings.chosenDatabases.remove(chosen);
   }

   public static LinkedList<Database> getOldChosenDatabases()
   {
      return oldChosenDatabases;
   }

   public static void setOldChosenDatabases(
         LinkedList<Database> oldChosenDatabases)
   {
      Settings.oldChosenDatabases = oldChosenDatabases;
   }

   public static float getVolume()
   {
      return volume;
   }

   public static void setVolume(float volume)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putInt(CerebrummiNodes.getVolumeNode(), (int) volume);
      Settings.volume = volume;
   }

   public static List<Database> getAvailableDatabases()
   {
      return Arrays.asList(availableDatabases);
   }
   
   public static Database[] getAvailableDatabasesAsArray()
   {
      return availableDatabases;
   }

   public static boolean isSimpleHebrewInput()
   {
      return simpleHebrewInput;
   }

   public static void setSimpleHebrewInput(boolean simpleHebrewInput)
   {
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getSimpleHebrewNode(),
                  simpleHebrewInput);
      Settings.simpleHebrewInput = simpleHebrewInput;
   }

   public static void toggleSimpleHebrewInput()
   {
      simpleHebrewInput = !simpleHebrewInput;
      Preferences preferences = Preferences
            .userRoot()
            .node(CerebrummiNodes.getNode());
      preferences
            .putBoolean(CerebrummiNodes.getSimpleHebrewNode(),
                  simpleHebrewInput);
   }

   public static String getRememberDatabaseForInput()
   {
      return rememberDatabaseForInput;
   }

   public static void setRememberDatabaseForInput(String rememberDatabaseForInput)
   {
      Settings.rememberDatabaseForInput = rememberDatabaseForInput;
   }

   public static String getRememberChapterForInput()
   {
      return rememberChapterForInput;
   }

   public static void setRememberChapterForInput(String rememberChapterForInput)
   {
      Settings.rememberChapterForInput = rememberChapterForInput;
   }
}
