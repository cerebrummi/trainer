package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class DictionaryColors
{

   private DictionaryColors()
   {
      // nothing
   }

   public static Color getInfoTextForeground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlue;
      }
      return ApplicationColors.darkGold;
   }

   public static Color getPanelBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlueLight;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.mediumSilverBlue;
   }

   public static Color getButton()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getButtonForeground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlue;
      }
      return ApplicationColors.darkGold;
   }

   public static Color getLightGrayGold()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.lightGrayGold;
      }
      return ApplicationColors.lightGrayGold;
   }

}
