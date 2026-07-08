package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class SuccessColors
{

   private SuccessColors()
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

   public static Color getPanelBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlueLight;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getPanelBackgroundLight()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.mediumSilverBlue;
   }

   public static Color getTableBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.slategray;
      }
      return ApplicationColors.white;
   }
}
