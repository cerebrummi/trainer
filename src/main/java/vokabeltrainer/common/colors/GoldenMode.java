package vokabeltrainer.common.colors;

import java.awt.Color;

public class GoldenMode extends AppColors
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
      return slategray;
   }

   @Override
   public Color getLightGrayBlue()
   {
      return shadyBlue;
   }

   @Override
   public Color getMediumBlue()
   {
      return shadyBlue;
   }

   @Override
   public Color getMediumSilverBlue()
   {
      return darkGreen;
   }

   @Override
   public Color getShadyBlue()
   {
      return shadyBlue; // defining color of theme
   }

   @Override
   public Color getGold()
   {
      return darkGreen;
   }

   @Override
   public Color getDarkGold()
   {
      return darkGold;
   }

   @Override
   public Color getLightGold()
   {
      return mediumSilverBlue;
   }

   @Override
   public Color getLightGrayGold()
   {
      return shadyBlue;
   }

   @Override
   public Color getMediumGold()
   {
      return mediumBlue;
   }

   @Override
   public Color getBackgroundGold()
   {
      return shadyBlue;
      
   }

   @Override
   public Color getVeryLightGold()
   {
      return darkGreen; // list background color B
   }

   @Override
   public Color getGreen()
   {
      return darkGreen;
   }
   
   @Override
   public Color getBrightGreen()
   {
      return brightGreen;
   }

   @Override
   public Color getSelectionGreen()
   {
      return sunflowerYellow;
   }

   @Override
   public Color getTransparent()
   {
      return transparent;
   }

   @Override
   public Color getTexturedBackgroundColor()
   {
      return shadyBlue;
   }

   @Override
   public Color getTexturedBackgroundColorLight()
   {
      return shadyBlue;
   }

   @Override
   public Color getWhite()
   {
      return mediumSilverBlue;
   }

   @Override
   public Color getLightYellow()
   {
      return mediumSilverBlue;
   }

   @Override
   public Color getDarkRed()
   {
      return shadyBlue;
   }

   @Override
   public Color getRose()
   {
      return texturedBackgroundColor;
   }

   @Override
   public Color getSunflowerYellow()
   {
      return mediumBlue;
   }

   @Override
   public Color getGray()
   {
      return mediumSilverBlue;
   }

   @Override
   public Color getBlack()
   {
      return white;
   }

   class Alefbet extends AppColors.Alefbet
   {

      @Override
      public Color getPanelBackground()
      {
         return backgroundGold;
      }

      @Override
      public Color getButton()
      {
         return backgroundGold;
      }
      
      @Override
      public Color getButtonForeground()
      {
         return darkGold;
      }
      
      @Override
      public Color getButton2()
      {
         return backgroundGold;
      }
      
      @Override
      public Color getKeyboardBackground()
      {
         return backgroundGold;
      }

      @Override
      public Color getTextForeground()
      {
         return darkGold;
      }

      @Override
      public Color getTextBackground()
      {
         return white;
      }
      
   }
   
   class Dictionary extends AppColors.Dictionary
   {

      @Override
      public Color getInfoTextForeground()
      {
         return darkGold;
      }

      @Override
      public Color getPanelBackground()
      {
         return backgroundGold;
      }

      @Override
      public Color getBackground()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getButton()
      {
         return backgroundGold;
      }

      @Override
      public Color getButtonForeground()
      {
         return darkGold;
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
         return veryLightGold;
      }

      @Override
      public Color getPanelBackground()
      {
         return backgroundGold;
      }

      @Override
      public Color getButtonBorder()
      {
         return green;
      }

      @Override
      public Color getButton()
      {
         return backgroundGold;
      }

      @Override
      public Color getButton2()
      {
         return backgroundGold;
      }

      @Override
      public Color getTransparent()
      {
         return transparent;
      }

      @Override
      public Color getTextBackground()
      {
         return backgroundGold;
      }

      @Override
      public Color getTextForeground()
      {
         return darkGold;
      }

      @Override
      public Color getInfoTextForeground()
      {
         return darkGold;
      }
      
   }
   
   class Main extends AppColors.Main
   {

      @Override
      public Color getToolbarBackground()
      {
         return backgroundGold;
      }

      @Override
      public Color getPanelBackground()
      {
         return backgroundGold;
      }

      @Override
      public Color getButtonBackground()
      {
         return backgroundGold;
      }
      
   }
   
   class Settings extends AppColors.Settings
   {

      @Override
      public Color getTextForeground()
      {
         return darkGold;
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
         return backgroundGold;
      }

      @Override
      public Color getDatabase_HeaderText()
      {
         return darkGold;
      }

      @Override
      public Color getTransparent()
      {
         return transparent;
      }

      @Override
      public Color getDatabase_Item()
      {
         return lightBlue;
      }

      @Override
      public Color getDatabase_Tipp()
      {
         return shadyBlue;
      }
      
   }
   
   class Statistics extends AppColors.Statistics
   {

      @Override
      public Color getTextForeground()
      {
         return darkGold;
      }

      @Override
      public Color getTextBackground()
      {
         return white;
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
         return backgroundGold;
      }

      @Override
      public Color getTableBackground()
      {
         return veryLightGold;
      }

      @Override
      public Color getTableCellBackground()
      {
         return lightGold;
      }

      @Override
      public Color getTableCellHighlightBackground()
      {
         return veryLightGold;
      }

      @Override
      public Color getSelectedBackground()
      {
         return backgroundGold;
      }

      @Override
      public Color getTextForegroundInvers()
      {
         return white;
      }
      
   }
   
   class Success extends AppColors.Success
   {

      @Override
      public Color getTextForeground()
      {
         return darkGold;
      }

      @Override
      public Color getPanelBackground()
      {
         return darkGold;
      }

      @Override
      public Color getPanelBackgroundLight()
      {
         return darkGold;
      }

      @Override
      public Color getTableBackground()
      {
         return white;
      }
      
   }
   
   class Table extends AppColors.Table
   {

      @Override
      public Color getRow1()
      {
         return lightBlue;
      }

      @Override
      public Color getRow2()
      {
         return veryLightGold;
      }
      
   }
   
   class Trainer extends AppColors.Trainer
   {

      @Override
      public Color getTextForeground()
      {
         return darkGold;
      }
      
      @Override
      public Color getTextBackground()
      {
         return white;
      }

      @Override
      public Color getInfoTextForeground()
      {
         return darkGold;
      }

      @Override
      public Color getPanelBackground()
      {
         return darkGold;
      }

      @Override
      public Color getBackground()
      {
         return mediumSilverBlue;
      }

      @Override
      public Color getButton()
      {
         return backgroundGold;
      }

      @Override
      public Color getButtonForeground()
      {
         return darkGold;
      }

      @Override
      public Color getPanelBackgroundDark()
      {
         return backgroundGold;
      }

      @Override
      public Color getTransparent()
      {
         return transparent;
      }
      
   }

   @Override
   public Color getRed()
   {
      return red;
   }   
}
