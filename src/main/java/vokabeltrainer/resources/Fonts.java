package vokabeltrainer.resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import vokabeltrainer.common.main.AppFonts;

public class Fonts
{
   private AppFonts appFonts = new AppFonts();

   public void read() throws FontFormatException, IOException
   {
      // Windows accepts the forward slash and Linux, too.
      this.appFonts.hebrewFont = Font.createFont(Font.TRUETYPE_FONT,
            Fonts.class.getResourceAsStream("_1_fonts/Cardo-regular_104s.ttf"));
      this.appFonts.germanFont = Font.createFont(Font.TRUETYPE_FONT,
            Fonts.class.getResourceAsStream("_1_fonts/Orkney Light.ttf"));
      this.appFonts.germanBoldFont = Font.createFont(Font.TRUETYPE_FONT,
            Fonts.class.getResourceAsStream("_1_fonts/Orkney Medium.ttf"));
      this.appFonts.hebrewHandwrittenFont = Font.createFont(
            Font.TRUETYPE_FONT,
            Fonts.class.getResourceAsStream("_1_fonts/AdaAdama.ttf"));
   }

   public void define()
   {
      this.appFonts.buttonFont = this.appFonts.germanFont.deriveFont(16F);
      this.appFonts.toolbarButtonFont = this.appFonts.germanFont
            .deriveFont(26F);
      this.appFonts.secondaryToolbarButtonFont = this.appFonts.germanFont
            .deriveFont(18F);
      this.appFonts.radioButtonFont = this.appFonts.germanFont
            .deriveFont(12F);
      this.appFonts.comboBoxFont = this.appFonts.germanFont
            .deriveFont(14F);
   }

   public AppFonts getAppFonts()
   {
      return appFonts;
   }
}
