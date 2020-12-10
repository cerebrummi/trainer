package vokabeltrainer;

import java.awt.Color;
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

   private static LinkedList<Database> oldChosenDatabases = new LinkedList<>();
   private static LinkedList<Database> chosenDatabases = new LinkedList<>();
   private static Database[] availableDatabases = { Database.ROSENGARTEN };

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

   public static Color getLightBlue()
   {
      return new Color(215, 231, 247);
   }
   
   public static Color getLightGrayBlue()
   {
      return new Color(150, 165, 180);
   }

   public static Color getMediumBlue()
   {
      return new Color(164, 190, 217);
   }

   public static Color getShadyBlue()
   {
      return new Color(44, 51, 73);
   }

   public static Color getGold()
   {
      return new Color(169, 136, 103);
   }

   public static Color getDarkGold()
   {
      return new Color(143, 101, 58);
   }

   public static Color getLightGold()
   {
      return new Color(209, 191, 173);
   }

   public static Color getLightGrayGold()
   {
      return new Color(203, 191, 180);
   }

   public static Color getMediumGold()
   {
      return new Color(228, 213, 197);
   }

   public static Color getBackgroundGold()
   {
      return new Color(223, 210, 198);
   }

   public static Color getVeryLightGold()
   {
      return new Color(247, 240, 232);
   }

   public static Color getGreen()
   {
      return new Color(181, 192, 81);
   }

   public static Color getTransparent()
   {
      return new Color(0, 0, 0, 0);
   }

   public static Color getTexturedBackgroundColor()
   {
      return new Color(225, 216, 211);
   }

   public static Color getTexturedBackgroundColorLight()
   {
      return new Color(230, 221, 217);
   }

   public static Color getWhite()
   {
      return Color.WHITE;
   }

   public static Color getLightYellow()
   {
      return new Color(255, 255, 235);
   }

   public static int getKeyboardWidth()
   {
      return 460;
   }

   public static Color getDarkRed()
   {
      return new Color(216, 0, 0);
   }

   public static Color getRose()
   {
      return new Color(247, 215, 215);
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
}
