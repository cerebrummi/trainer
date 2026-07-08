package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class TrainerColors
{

   private TrainerColors()
   {
      // nothing
   }

   public static Color getTextForeground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.white;
      }
      return ApplicationColors.darkGold;
   }

   public static Color getTextBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.lightBlue;
      }
      return ApplicationColors.white;
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

   public static Color getPanelBackgroundDark()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.slategray;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getTransparent()
   {
      return ApplicationColors.transparent;
   }
}
