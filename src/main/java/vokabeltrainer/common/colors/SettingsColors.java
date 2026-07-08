package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class SettingsColors
{
   private SettingsColors()
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
}
