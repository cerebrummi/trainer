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
   
   public static Color getToday()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.green;
      }
      return ApplicationColors.green;
   }

   public static Color getPanelBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlueLight;
      }
      return ApplicationColors.backgroundGold;
   }
}
