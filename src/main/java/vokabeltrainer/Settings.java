package vokabeltrainer;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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

   private static LinkedList<Database> oldChosenDatabases = new LinkedList<>();
   private static LinkedList<Database> chosenDatabases = new LinkedList<>();
   private static Database[] availableDatabases = { };

   private Settings()
   {
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
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSoundNode(), soundOn);
   }

   public static void setSoundOn(boolean soundOn)
   {
      Preferences preferences = Preferences.userRoot()
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
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getLetterPicturesNode(), letterImagesOn);
   }

   public static void setLetterImagesOn(boolean letterImagesOn)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getLetterPicturesNode(), letterImagesOn);
      Settings.letterImagesOn = letterImagesOn;
   }

   public static void setChoosenExpressionPath(String choosenExpressionPath)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.put(CerebrummiNodes.getChoosenExpressionPathNode(),
            choosenExpressionPath);

      Settings.chosenExpressionPath = choosenExpressionPath;
   }

   public static LinkedList<Database> getChosenDatabases()
   {
      return chosenDatabases;
   }

   public static void setChosenDatabases(LinkedList<Database> chosenDatabases)
   {
      Settings.chosenDatabases = chosenDatabases;
   }

   public static void addChosenDatabase(Database chosen)
   {
      Settings.chosenDatabases.add(chosen);
   }

   public static void removeChosenDatabase(Database chosen)
   {
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
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putInt(CerebrummiNodes.getVolumeNode(), (int)volume);
      Settings.volume = volume;
   }

   public static List<Database> getAvailableDatabases()
   {
      return Arrays.asList(availableDatabases);
   }

   public static boolean isSimpleHebrewInput()
   {
      return simpleHebrewInput;
   }

   public static void setSimpleHebrewInput(boolean simpleHebrewInput)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSimpleHebrewNode(), simpleHebrewInput);
      Settings.simpleHebrewInput = simpleHebrewInput;
   }
   
   public static void toggleSimpleHebrewInput()
   {
      simpleHebrewInput = !simpleHebrewInput;
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSimpleHebrewNode(), simpleHebrewInput);
   }
}
