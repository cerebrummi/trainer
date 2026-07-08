package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class TableColors
{

   private TableColors()
   {
      // nothing
   }

   public static Color getRow1()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.lightBlue;
   }

   public static Color getRow2()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.lightGold;
      }
      return ApplicationColors.veryLightGold;
   }
}
