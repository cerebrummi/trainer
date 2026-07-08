package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class StartColors
{

   private StartColors()
   {
      // nothing
   }

   public static Color getDatabase_Header()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.mediumSilverBlue;
   }

   public static Color getPanelBackground()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlueLight;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getDatabase_HeaderText()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.white;
      }
      return ApplicationColors.darkGold;
   }

   public static Color getTransparent()
   {
      return ApplicationColors.transparent;
   }

   public static Color getDatabase_Item()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.gold;
      }
      return ApplicationColors.lightBlue;
   }

   public static Color getDatabase_Tipp()
   {
      if (Settings.isDarkmodeOn())
      {
         return ApplicationColors.gold;
      }
      return ApplicationColors.shadyBlue;
   }

}
