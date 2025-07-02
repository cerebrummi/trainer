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

   public static Color getPanelBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlueLight;
      }
      return ApplicationColors.backgroundGold;
   }
   
   public static Color getBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.mediumSilverBlue;
   }
}
