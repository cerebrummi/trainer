package vokabeltrainer.common.main;

import java.awt.FontFormatException;
import java.io.IOException;

import vokabeltrainer.cmd.Mode;
import vokabeltrainer.common.colors.AppColors;
import vokabeltrainer.common.colors.DarkMode;
import vokabeltrainer.common.colors.GoldenMode;
import vokabeltrainer.resources.Fonts;
import vokabeltrainer.resources.Images;
import vokabeltrainer.resources.Sounds;

public class App
{
   public Settings settings;
   
   public AppSounds appSound;
   public AppColors appColors;
   public AppImages appImages;
   public AppFonts appFonts;

   
   public App (Mode mode)
   {
      this.settings = new Settings(this);
      
      UserPreferences userPreferences = new UserPreferences();
      userPreferences.read(settings, mode);
      
      Sounds sounds = new Sounds();
      sounds.read(settings);
      appSound = sounds.getAppSounds();
      
      readColorModes();
      
      Images images = new Images();
      try
      {
         images.read();
      }
      catch (IOException e)
      {
         System.exit(1);
      }
      appImages = images.getAppImages();
      
      Fonts fonts = new Fonts();
      try
      {
         fonts.read();
      }
      catch (FontFormatException | IOException e)
      {
         System.exit(2);
      }
      fonts.define();
      appFonts = fonts.getAppFonts();
      
      
   }
   
   public void readColorModes()
   {
      if(settings.isDarkmodeOn())
      {
         appColors = new DarkMode();
      }
      else
      {
         appColors = new GoldenMode();
      }
   }
}
