package vokabeltrainer.common.main;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;

public class AppColors implements ColorBase
{
   private Settings settings;
   
   public AppColors(Settings settings)
   {
      this.settings = settings;
   }
   
   Color getLightBlue()
   {
      if (settings.isDarkmodeOn())
         return slategray;
      return lightBlue;
   }

   public Color getLightGrayBlue()
   {
      if (settings.isDarkmodeOn())
         return shadyBlue;
      return lightGrayBlue;
   }

   Color getMediumBlue()
   {
      if (settings.isDarkmodeOn())
         return shadyBlue;
      return mediumBlue;
   }

   Color getMediumSilverBlue()
   {
      if (settings.isDarkmodeOn())
         return darkGreen;
      return mediumSilverBlue;
   }

   Color getShadyBlue()
   {
      if (settings.isDarkmodeOn())
         return white;
      return shadyBlue;
   }

   public Color getGold()
   {
      if (settings.isDarkmodeOn())
         return darkGreen;
      return gold;
   }

   Color getDarkGold()
   {
      if (settings.isDarkmodeOn())
         return white;
      return darkGold;
   }

   Color getLightGold()
   {
      if (settings.isDarkmodeOn())
         return mediumSilverBlue;
      return lightGold;
   }

   Color getLightGrayGold()
   {
      if (settings.isDarkmodeOn())
         return shadyBlue;
      return lightGrayGold;
   }

   Color getMediumGold()
   {
      if (settings.isDarkmodeOn())
         return mediumBlue;
      return mediumGold;
   }

   Color getBackgroundGold()
   {
      if (settings.isDarkmodeOn())
         return shadyBlue;
      return backgroundGold;
   }

   Color getVeryLightGold()
   {
      if (settings.isDarkmodeOn())
         return darkGreen; // list background color B
      return veryLightGold;
   }

   Color getGreen()
   {
      if (settings.isDarkmodeOn())
         return darkGreen;
      return green;
   }

   Color getSelectionGreen()
   {
      if (settings.isDarkmodeOn())
         return sunflowerYellow;
      return green;
   }

   public Color getTransparent()
   {
      return transparent;
   }

   Color getTexturedBackgroundColor()
   {
      if (settings.isDarkmodeOn())
         return shadyBlue;
      return texturedBackgroundColor;
   }

   Color getTexturedBackgroundColorLight()
   {
      if (settings.isDarkmodeOn())
         return shadyBlue;
      return texturedBackgroundColorLight;
   }

   public Color getWhite()
   {
      if (settings.isDarkmodeOn())
         return mediumSilverBlue;
      return white;
   }

   Color getLightYellow()
   {
      if (settings.isDarkmodeOn())
         return mediumSilverBlue;
      return lightYellow;
   }

   Color getDarkRed()
   {
      if (settings.isDarkmodeOn())
         return shadyBlue;
      return darkRed;
   }

   Color getRose()
   {
      if (settings.isDarkmodeOn())
         return texturedBackgroundColor;
      return rose;
   }

   Color getSunflowerYellow()
   {
      if (settings.isDarkmodeOn())
         return mediumBlue;
      return sunflowerYellow;
   }

   Color getGray()
   {
      if (settings.isDarkmodeOn())
         return mediumSilverBlue;
      return Color.GRAY;
   }

   Color getBlack()
   {
      if (settings.isDarkmodeOn())
         return white;
      return Color.BLACK;
   }
}
