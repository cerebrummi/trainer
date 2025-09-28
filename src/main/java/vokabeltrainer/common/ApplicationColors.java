package vokabeltrainer.common;

import java.awt.Color;

public class ApplicationColors
{
   public static Color lightBlue = new Color(215, 231, 247);
   public static Color lightGrayBlue = new Color(150, 165, 180);
   public static Color mediumBlue = new Color(164, 190, 217);
   public static Color mediumSilverBlue = new Color(162, 180, 202);
   public static Color darkSilverBlue = new Color(62, 80, 102);
   public static Color shadyBlue = new Color(44, 51, 73);
   public static Color shadyBlueLight = new Color(70, 85, 130);
   public static Color gold = new Color(169, 136, 103);
   public static Color darkGold = new Color(143, 101, 58);
   public static Color lightGold = new Color(209, 191, 173);
   public static Color lightGrayGold = new Color(203, 191, 180);
   public static Color mediumGold = new Color(228, 213, 197);
   public static Color backgroundGold = new Color(223, 210, 198);
   public static Color veryLightGold = new Color(247, 240, 232);
   public static Color green = new Color(181, 192, 81);
   public static Color texturedBackgroundColor = new Color(225, 216, 211);
   public static Color texturedBackgroundColorLight = new Color(230, 221, 217);
   public static Color white = new Color(255, 255, 255);
   public static Color lightYellow = new Color(255, 255, 235);
   public static Color darkRed = new Color(216, 0, 0);
   public static Color rose = new Color(247, 215, 215);
   public static Color sunflowerYellow = new Color(255, 220, 35);
   public static Color darkGreen = new Color(110,110,70);
   public static Color transparent = new Color(0,0,0,0);
   public static Color slategray = new Color(81,81,91);
   
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
      return darkGold;
   }

   public static Color getLightGold()
   {
      if(Settings.isDarkmodeOn())
      {
         return mediumSilverBlue;
      }
      return lightGold;
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
         return mediumSilverBlue;
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
         return white;
      }
      return Color.BLACK;
   }

   
}

