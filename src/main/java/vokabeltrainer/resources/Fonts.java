package vokabeltrainer.resources;

import java.awt.Font;

import vokabeltrainer.common.Main;

public class Fonts
{

   public static void read() throws Exception
   {
      Main.setHebrewFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                  "_1_fonts/Cardo-regular_104s.ttf")));
      
      Main.setGermanFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                 "_1_fonts/Orkney Light.ttf")));
      
      Main.setGermanBoldFont(
            Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(
                  "_1_fonts/Orkney Medium.ttf")));
   }

}
