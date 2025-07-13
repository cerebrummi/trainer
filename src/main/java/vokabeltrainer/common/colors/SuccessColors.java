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
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.white;
      }
      return ApplicationColors.darkGold;
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
