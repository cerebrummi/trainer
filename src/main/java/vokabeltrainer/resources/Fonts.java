package vokabeltrainer.resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import vokabeltrainer.common.ApplicationFonts;

public class Fonts
{

   public static void read() throws FontFormatException, IOException
   {
      ApplicationFonts
            .setHebrewFont(Font.createFont(Font.TRUETYPE_FONT, Fonts.class
                  .getResourceAsStream("_1_fonts/Cardo-regular_104s.ttf"))); // Windows
                                                                             // accepts
                                                                             // the
                                                                             // forward
                                                                             // slash
                                                                             // and
                                                                             // Linux,
                                                                             // too.

      ApplicationFonts.setGermanFont(Font.createFont(Font.TRUETYPE_FONT,
            Fonts.class.getResourceAsStream("_1_fonts/Orkney Light.ttf")));

      ApplicationFonts.setGermanBoldFont(Font.createFont(Font.TRUETYPE_FONT,
            Fonts.class.getResourceAsStream("_1_fonts/Orkney Medium.ttf")));

      ApplicationFonts
            .setHebrewHandwrittenFont(Font.createFont(Font.TRUETYPE_FONT,
                  Fonts.class.getResourceAsStream("_1_fonts/AdaAdama.ttf")));
   }

   public static void define()
   {
      ApplicationFonts
            .setButtonFont(ApplicationFonts.getGermanFont().deriveFont(16F));
      ApplicationFonts.setToolbarButtonFont(
            ApplicationFonts.getGermanFont().deriveFont(26F));
      ApplicationFonts.setSecondaryToolbarButtonFont(
            ApplicationFonts.getGermanFont().deriveFont(18F));
      ApplicationFonts.setRadioButtonFont(
            ApplicationFonts.getGermanFont().deriveFont(12F));
      ApplicationFonts
            .setComboBoxFont(ApplicationFonts.getGermanFont().deriveFont(14F));
   }
}
