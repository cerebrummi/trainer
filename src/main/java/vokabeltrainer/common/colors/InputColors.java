package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class InputColors
{

   private InputColors()
   {
      // nothing
   }

   public static Color getEditorBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.mediumSilverBlue;
   }

   public static Color getTextEditorBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.lightGold;
      }
      return ApplicationColors.veryLightGold;
   }

   public static Color getPanelBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlueLight;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getButtonBorder()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.green;
      }
      return ApplicationColors.green;
   }

   public static Color getButton()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getButton2()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.lightGold;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getTransparent()
   {
      return new Color(0, 0, 0, 0);
   }

   public static Color getTextBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getTextForeground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.white;
      }
      return ApplicationColors.darkGold;
   }

   public static Color getInfoTextForeground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlue;
      }
      return ApplicationColors.darkGold;
   }
}
