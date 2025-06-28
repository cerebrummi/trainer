package vokabeltrainer.common;

import java.awt.Color;

public class ApplicationColors
{
   private static Color lightBlue = new Color(215, 231, 247);
   private static Color lightGrayBlue = new Color(150, 165, 180);
   private static Color mediumBlue = new Color(164, 190, 217);
   private static Color mediumSilverBlue = new Color(162, 180, 202);
   private static Color shadyBlue = new Color(44, 51, 73);
   private static Color gold = new Color(169, 136, 103);
   private static Color darkgold = new Color(143, 101, 58);
   private static Color lightgold = new Color(209, 191, 173);
   private static Color lightGrayGold = new Color(203, 191, 180);
   private static Color mediumGold = new Color(228, 213, 197);
   private static Color backgroundGold = new Color(223, 210, 198);
   private static Color veryLightGold = new Color(247, 240, 232);
   private static Color green = new Color(181, 192, 81);
   private static Color texturedBackgroundColor = new Color(225, 216, 211);
   private static Color texturedBackgroundColorLight = new Color(230, 221, 217);
   private static Color white = new Color(255, 255, 255);
   private static Color lightYellow = new Color(255, 255, 235);
   private static Color darkRed = new Color(216, 0, 0);
   private static Color rose = new Color(247, 215, 215);
   private static Color sunflowerYellow = new Color(255, 220, 35);
   private static Color darkGreen = new Color(110,110,70);
   
   private ApplicationColors()
   {
      // nothing
   }

   public static Color getLightBlue()
   {
      if(Settings.isDarkmodeOn())
      {
         return Color.DARK_GRAY; // list background color A
      }
      return lightBlue;
   }
   
   public static Color getLightGrayBlue()
   {
      if(Settings.isDarkmodeOn())
      {
         return shadyBlue;
      }
      return lightGrayBlue;
   }

   public static Color getMediumBlue()
   {
      if(Settings.isDarkmodeOn())
      {
         return shadyBlue;
      }
      return mediumBlue;
   }
   
   public static Color getMediumSilverBlue()
   {
      if(Settings.isDarkmodeOn())
      {
         return darkGreen;
      }
      return mediumSilverBlue;
   }

   public static Color getShadyBlue()
   {
      if(Settings.isDarkmodeOn())
      {
         return white;
      }
      return shadyBlue;
   }

   public static Color getGold()
   {
      if(Settings.isDarkmodeOn())
      {
         return darkGreen;
      }
      return gold;
   }

   public static Color getDarkGold()
   {
      if(Settings.isDarkmodeOn())
      {
         return white;
      }
      return darkgold;
   }

   public static Color getLightGold()
   {
      if(Settings.isDarkmodeOn())
      {
         return shadyBlue;
      }
      return lightgold;
   }

   public static Color getLightGrayGold()
   {
      if(Settings.isDarkmodeOn())
      {
         return shadyBlue;
      }
      return lightGrayGold;
   }

   public static Color getMediumGold()
   {
      if(Settings.isDarkmodeOn())
      {
         return mediumBlue;
      }
      return mediumGold;
   }

   public static Color getBackgroundGold()
   {
      if(Settings.isDarkmodeOn())
      {
         return shadyBlue;
      }
      return backgroundGold;
   }

   public static Color getVeryLightGold()
   {
      if(Settings.isDarkmodeOn())
      {
         return darkGreen; // list background color B
      }
      return veryLightGold;
   }

   public static Color getGreen()
   {
      if(Settings.isDarkmodeOn())
      {
         return darkGreen;
      }
      return green;
   }

   public static Color getTransparent()
   {
      return new Color(0, 0, 0, 0);
   }

   public static Color getTexturedBackgroundColor()
   {
      if(Settings.isDarkmodeOn())
      {
         return shadyBlue;
      }
      return texturedBackgroundColor;
   }

   public static Color getTexturedBackgroundColorLight()
   {
      if(Settings.isDarkmodeOn())
      {
         return shadyBlue;
      }
      return texturedBackgroundColorLight;
   }

   public static Color getWhite()
   {
      if(Settings.isDarkmodeOn())
      {
         return shadyBlue;
      }
      return white;
   }

   public static Color getLightYellow()
   {
      if(Settings.isDarkmodeOn())
      {
         return mediumSilverBlue;
      }
      return lightYellow;
   }
   
   public static Color getDarkRed()
   {
      if(Settings.isDarkmodeOn())
      {
         return shadyBlue;
      }
      return darkRed;
   }

   public static Color getRose()
   {
      if(Settings.isDarkmodeOn())
      {
         return texturedBackgroundColor;
      }
      return rose;
   }

   public static Color getSunflowerYellow()
   {
      if(Settings.isDarkmodeOn())
      {
         return mediumBlue;
      }
      return sunflowerYellow;
   }


   public static Color getGray()
   {
      if(Settings.isDarkmodeOn())
      {
         return mediumSilverBlue;
      }
      return Color.GRAY;
   }

   public static Color getBlack()
   {
      if(Settings.isDarkmodeOn())
      {
         return mediumBlue;
      }
      return Color.BLACK;
   }

   public static Color getBackgroundGold_start()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.mediumSilverBlue;
      }
      return getBackgroundGold();
   }

   public static Color getDarkGold_start()
   {
      if(Settings.isDarkmodeOn())
      {
         return ApplicationColors.white;
      }
      return getDarkGold();
   }
}

