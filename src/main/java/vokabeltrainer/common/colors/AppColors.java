package vokabeltrainer.common.colors;

import java.awt.Color;

public abstract class AppColors implements ColorBase
{
   public Alefbet alefbet;
   public Dictionary dictionary;
   public Input input;
   public Main main;
   public Settings settings;
   public Start start;
   public Statistics statistics;
   public Success success;
   public Table table;
   public Trainer trainer;
   
   public abstract Color getThemeDefinitionColor();
   
   public abstract Color getBoxForeground(); // for UI
   
   public Color getTransparent()
   {
      return ColorBase.transparent; // do not change
   }
   
   public abstract Color getBackground();
   
   public abstract Color getLightBlue();

   public abstract Color getLightGrayBlue();

   public abstract Color getMediumBlue();

   public abstract Color getMediumSilverBlue();   

   public abstract Color getGold();

   public abstract Color getDarkGold();

   public abstract Color getLightGold();

   public abstract Color getMediumGold();

   public abstract Color getVeryLightGold();
   
   public abstract Color getBrightGreen();

   public abstract Color getMediumGreen();

   public abstract Color getBeigeGold();

   public abstract Color getBeigeGoldLight();

   public abstract Color getWhite();

   public abstract Color getLightYellow();
   
   public abstract Color getRed();

   public abstract Color getRose();

   public abstract Color getSunflowerYellow();
   
   public abstract Color getGray();

   public abstract Color getBlack();
   
   public abstract class Alefbet
   {
      public abstract Color getPanelBackground();
      public abstract Color getButton();
      public abstract Color getButtonForeground();
      public abstract Color getButton2();
      public abstract Color getKeyboardBackground();
      public abstract Color getTextForeground();
      public abstract Color getTextBackground();
   }
   
   public abstract class Dictionary
   {
      public abstract Color getInfoTextForeground();
      public abstract Color getPanelBackground();
      public abstract Color getBackground();
      public abstract Color getButton();
      public abstract Color getButtonForeground();
      public abstract Color getLabelBackground();
      public abstract Color getWasteBinBorder();
      public abstract Color getShredderButtonBorder();
   }
   
   public abstract class Input
   {
      public abstract Color getEditorBackground();
      public abstract Color getTextEditorBackground();
      public abstract Color getBoxBackground();
      public abstract Color getButtonBorder();
      public abstract Color getButton();
      public abstract Color getButton2();
      public abstract Color getTransparent();
      public abstract Color getTextBackground();
      public abstract Color getTextForeground();
      public abstract Color getInfoTextForeground();
      public abstract Color getInfoButtonBackground();
   }
   
   public abstract class Main
   {
      public abstract Color getToolbarBackground();
      public abstract Color getPanelBackground();
      public abstract Color getButtonBackground();
   }
   
   public abstract class Settings
   {
      public abstract Color getTextForeground();
   }
   
   public abstract class Start
   {
      public abstract Color getDatabase_Header();
      public abstract Color getPanelBackground();
      public abstract Color getDatabase_HeaderText();
      public abstract Color getDatabase_Item();
      public abstract Color getDatabase_Tipp();
   }
   
   public abstract class Statistics
   {
      public abstract Color getTextForeground();
      public abstract Color getTextBackground();
      public abstract Color getToday();
      public abstract Color getToLate();
      public abstract Color getFuture();
      public abstract Color getPanelBackground();
      public abstract Color getTableBackground();
      public abstract Color getTableCellBackground();
      public abstract Color getTableCellHighlightBackground();
      public abstract Color getSelectedBackground();
      public abstract Color getTextForegroundInvers();
   }
   
   public abstract class Success
   {
      public abstract Color getTextForeground();
      public abstract Color getPanelBackground();
      public abstract Color getPanelBackgroundLight();
      public abstract Color getTableBackground();
   }
   
   public abstract class Table
   {
      public abstract Color getRow1();
      public abstract Color getRow2();
   }
   
   public abstract class Trainer
   {
      public abstract Color getTextForeground();
      public abstract Color getTextBackground();
      public abstract Color getInfoTextForeground();
      public abstract Color getPanelBackground();
      public abstract Color getBackground();
      public abstract Color getButton();
      public abstract Color getButtonForeground();
      public abstract Color getPanelBackgroundDark();
   }
}
