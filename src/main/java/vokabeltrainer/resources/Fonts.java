package vokabeltrainer.resources;

import java.awt.Font;
import java.io.File;

import vokabeltrainer.common.ApplicationFonts;

public class Fonts
{

   public static void read() throws Exception
   {
      ApplicationFonts.setHebrewFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                  "_1_fonts" + File.separator + "Cardo-regular_104s.ttf")));

      ApplicationFonts.setGermanFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                  "_1_fonts" + File.separator + "Orkney Light.ttf")));

      ApplicationFonts.setGermanBoldFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                  "_1_fonts" + File.separator + "Orkney Medium.ttf")));

      ApplicationFonts.setHebrewHandwrittenFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                  "_1_fonts" + File.separator + "AdaAdama.ttf")));
   }

   public static void define() throws Exception
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
