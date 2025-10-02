package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class StatisticsColors
{

   private StatisticsColors()
   {
      // nothing
   }

   public static Color getTextForeground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.white;
      }
      return ApplicationColors.darkGold;
   }

   public static Color getTextBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.lightBlue;
      }
      return ApplicationColors.white;
   }
   
   public static Color getToday()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.green;
      }
      return ApplicationColors.green;
   }
   
   public static Color getToLate()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.rose;
      }
      return ApplicationColors.rose;
   }
   
   public static Color getFuture()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.lightBlue;
      }
      return ApplicationColors.lightBlue;
   }

   public static Color getPanelBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlueLight;
      }
      return ApplicationColors.backgroundGold;
   }
   
   public static Color getTableBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.veryLightGold;
   }
   
   public static Color getTableCellBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumBlue;
      }
      return ApplicationColors.lightGold;
   }
   
   public static Color getTableCellHighlightBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.veryLightGold;
   }

   public static Color getSelectedBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.slategray;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getTextForegroundInvers()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.black;
      }
      return ApplicationColors.white;
   }
}
