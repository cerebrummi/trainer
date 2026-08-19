package vokabeltrainer.common.colors;

import java.awt.Color;

public class DarkMode extends AppColors
{
   public Alefbet alephbet = new Alefbet();
   public Dictionary dictionary = new Dictionary();
   public Input input = new Input();
   public Main main = new Main();
   public Settings settings = new Settings();
   public Start start = new Start();
   public Statistics statistics = new Statistics();
   public Success success = new Success();
   public Table table = new Table();
   public Trainer trainer = new Trainer();
   
   
   @Override
   public Color getLightBlue()
   {
      return lightBlue;
   }

   @Override
   public Color getLightGrayBlue()
   {
      return lightGrayBlue;
   }

   @Override
   public Color getMediumBlue()
   {
      return mediumBlue;
   }

   @Override
   public Color getMediumSilverBlue()
   {
      return mediumSilverBlue;
   }

   @Override
   public Color getShadyBlue() // getWhite is already taken
   {
      return white;
   }

   @Override
   public Color getGold()
   {
      return gold; // defining color of theme
   }

   @Override
   public Color getDarkGold()
   {
      return white;
   }

   @Override
   public Color getLightGold()
   {
      return lightGold;
   }

   @Override
   public Color getLightGrayGold()
   {
      return lightGrayGold;
   }

   @Override
   public Color getMediumGold()
   {
      return mediumGold;
   }

   @Override
   public Color getBackgroundGold()
   {
      return backgroundGold;
   }

   @Override
   public Color getVeryLightGold()
   {
      return veryLightGold;
   }

   @Override
   public Color getGreen()
   {
      return green;
   }
   
   @Override
   public Color getBrightGreen()
   {
      return brightGreen;
   }

   @Override
   public Color getSelectionGreen()
   {
      return green;
   }

   @Override
   public Color getTransparent()
   {
      return transparent;
   }

   @Override
   public Color getTexturedBackgroundColor()
   {
      return texturedBackgroundColor;
   }

   @Override
   public Color getTexturedBackgroundColorLight()
   {
      return texturedBackgroundColorLight;
   }

   @Override
   public Color getWhite()
   {
      return white;
   }

   @Override
   public Color getLightYellow()
   {
      return lightYellow;
   }

   @Override
   public Color getDarkRed()
   {
      return darkRed;
   }

   @Override
   public Color getRose()
   {
      return rose;
   }

   @Override
   public Color getSunflowerYellow()
   {
      return sunflowerYellow;
   }

   @Override
   public Color getGray()
   {
      return silvergray;
   }

   @Override
   public Color getBlack()
   {
      return black;
   }
   
   class Alefbet extends AppColors.Alefbet
   {

      @Override
      public Color getPanelBackground()
      {
         return shadyBlueLight;
      }

      @Override
      public Color getButton()
      {
         return mediumSilverBlue;
      }
      
      @Override
      public Color getButtonForeground()
      {
         return shadyBlue;
      }
      
      @Override
      public Color getButton2()
      {
         return white;
      }
      
      @Override
      public Color getKeyboardBackground()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getTextForeground()
      {
         return white;
      }

      @Override
      public Color getTextBackground()
      {
         return mediumSilverBlue;
      }
      
   }

   class Dictionary extends AppColors.Dictionary
   {

      @Override
      public Color getInfoTextForeground()
      {
         return shadyBlue;
      }

      @Override
      public Color getPanelBackground()
      {
         return shadyBlueLight;
      }

      @Override
      public Color getBackground()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getButton()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getButtonForeground()
      {
         return shadyBlue;
      }

      @Override
      public Color getLightGrayGold()
      {
         return lightGrayGold;
      }
      
   }
   
   class Input extends AppColors.Input
   {

      @Override
      public Color getEditorBackground()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getTextEditorBackground()
      {
         return lightGold;
      }

      @Override
      public Color getPanelBackground()
      {
         return shadyBlueLight;
      }

      @Override
      public Color getButtonBorder()
      {
         return green;
      }

      @Override
      public Color getButton()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getButton2()
      {
         return lightGold;
      }

      @Override
      public Color getTransparent()
      {
         return transparent;
      }

      @Override
      public Color getTextBackground()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getTextForeground()
      {
         return white;
      }

      @Override
      public Color getInfoTextForeground()
      {
         return shadyBlue;
      }
      
   }
   
   class Main extends AppColors.Main
   {

      @Override
      public Color getToolbarBackground()
      {
         return shadyBlueLight;
      }

      @Override
      public Color getPanelBackground()
      {
         return shadyBlueLight;
      }

      @Override
      public Color getButtonBackground()
      {
         return mediumSilverBlue;
      }
      
   }
   
   class Settings extends AppColors.Settings
   {

      @Override
      public Color getTextForeground()
      {
         return white;
      }
      
   }
   
   class Start extends AppColors.Start
   {

      @Override
      public Color getDatabase_Header()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getPanelBackground()
      {
         return shadyBlueLight;
      }

      @Override
      public Color getDatabase_HeaderText()
      {
         return white;
      }

      @Override
      public Color getTransparent()
      {
         return transparent;
      }

      @Override
      public Color getDatabase_Item()
      {
         return gold;
      }

      @Override
      public Color getDatabase_Tipp()
      {
         return gold;
      }
      
   }
   
   class Statistics extends AppColors.Statistics
   {

      @Override
      public Color getTextForeground()
      {
         return white;
      }

      @Override
      public Color getTextBackground()
      {
         return lightBlue;
      }

      @Override
      public Color getToday()
      {
         return green;
      }

      @Override
      public Color getToLate()
      {
         return rose;
      }

      @Override
      public Color getFuture()
      {
         return lightBlue;
      }

      @Override
      public Color getPanelBackground()
      {
         return shadyBlueLight;
      }

      @Override
      public Color getTableBackground()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getTableCellBackground()
      {
         return mediumBlue;
      }

      @Override
      public Color getTableCellHighlightBackground()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getSelectedBackground()
      {
         return slategray;
      }

      @Override
      public Color getTextForegroundInvers()
      {
         return black;
      }
      
   }
   
   class Success extends AppColors.Success
   {

      @Override
      public Color getTextForeground()
      {
         return white;
      }

      @Override
      public Color getPanelBackground()
      {
         return shadyBlueLight;
      }

      @Override
      public Color getPanelBackgroundLight()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getTableBackground()
      {
         return slategray;
      }
      
   }
   
   class Table extends AppColors.Table
   {

      @Override
      public Color getRow1()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getRow2()
      {
         return lightGold;
      }
      
   }
   
   class Trainer extends AppColors.Trainer
   {

      @Override
      public Color getTextForeground()
      {
         return white;
      }
      
      @Override
      public Color getTextBackground()
      {
         return lightBlue;
      }

      @Override
      public Color getInfoTextForeground()
      {
         return shadyBlue;
      }

      @Override
      public Color getPanelBackground()
      {
         return shadyBlueLight;
      }

      @Override
      public Color getBackground()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getButton()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getButtonForeground()
      {
         return shadyBlue;
      }

      @Override
      public Color getPanelBackgroundDark()
      {
         return slategray;
      }

      @Override
      public Color getTransparent()
      {
         return transparent;
      }
      
   }
}
