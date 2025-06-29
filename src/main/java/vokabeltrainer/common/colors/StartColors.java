package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class StartColors
{

   private StartColors()
   {
      
   }
   
   public static Color getDatabase_Header()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.gold;
      }
      return ApplicationColors.mediumSilverBlue;
   }

   public static Color getBackgroundGold_start()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getDarkGold_start()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.white;
      }
      return ApplicationColors.darkGold;
   }

   public static Color getTransparent()
   {
      return ApplicationColors.transparent;
   }
   
}
