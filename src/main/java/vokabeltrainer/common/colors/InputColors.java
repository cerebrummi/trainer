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

   public static Color getPanelBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlueLight;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getButtonBorder()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.green;
      }
      return ApplicationColors.green;
   }

   public static Color getButton()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.backgroundGold;
   }
}
