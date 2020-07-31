package vokabeltrainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import vokabeltrainer.common.Main;
import vokabeltrainer.types.LanguageSettings;

public class Settings
{
   private final static Font buttonFont = Main.getGermanFont(16F);
   private final static Font toolbarButtonFont = Main.getHeaderFont(26F);
   private final static Font secondaryToolbarButtonFont = Main
         .getHeaderFont(18F);
   
   private static boolean soundOn = true;
      

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
      return "de.copepod.hebrewtrainer";
   }

   public static String getExpressionPath()
   {
      return System.getProperty("user.home") + File.separator + getExpressionFolder();
   }

   public static String getTrainingPath()
   {
      return getExpressionPath() + File.separator + getTrainingFolder();
   }

   private static String getTrainingFolder()
   {
      return "training";
   }

   public static String getNode()
   {
      return File.separator + "de" + File.separator + "copepod" + File.separator
            + "hebrewtrainer";
   }

   public static String getExpressionNode()
   {
      return "vocabulary";
   }

   public static String getTrainingNode()
   {
      return "training";
   }

   public static int getNumberOfBackups()
   {
      return 5;
   }

   public static Color getLightBlue()
   {
      return new Color(215, 231, 247);
   }

   public static Color getMediumBlue()
   {
      return new Color(164, 190, 217);
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
      if(soundOn)
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
   }

   public static void setSoundOn(boolean soundOn)
   {
      Settings.soundOn = soundOn;
   }
}
