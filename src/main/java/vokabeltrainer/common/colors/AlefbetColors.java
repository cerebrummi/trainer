package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public class AlefbetColors
{

   private AlefbetColors()
   {
      //nothing
   }

   public static Color getPanelBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlueLight;
      }
      return ApplicationColors.backgroundGold;
   }
   
   public static Color getButton()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.backgroundGold;
   }
   
   public static Color getButtonForeground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.shadyBlue;
      }
      return ApplicationColors.darkGold;
   }
   
   public static Color getButton2()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.white;
      }
      return ApplicationColors.backgroundGold;
   }

   
   public static Color getTextForeground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.white;
      }
      return ApplicationColors.darkGold;
   }
   
   public static Color getKeyboardBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.backgroundGold;
   }

   public static Color getTextBackground()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return ApplicationColors.white;
   }
}
