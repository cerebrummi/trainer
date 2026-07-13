package vokabeltrainer.resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import vokabeltrainer.common.ApplicationFonts;

public interface Fonts {

    static void read() throws FontFormatException, IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Windows accepts the forward slash and Linux, too.
        ApplicationFonts.hebrewFont = Font.createFont(Font.TRUETYPE_FONT, classloader.getResourceAsStream("_1_fonts/Cardo-regular_104s.ttf"));
        ApplicationFonts.germanFont = Font.createFont(Font.TRUETYPE_FONT, classloader.getResourceAsStream("_1_fonts/Orkney Light.ttf"));
        ApplicationFonts.germanBoldFont = Font.createFont(Font.TRUETYPE_FONT, classloader.getResourceAsStream("_1_fonts/Orkney Medium.ttf"));
        ApplicationFonts.hebrewHandwrittenFont = Font.createFont(Font.TRUETYPE_FONT, classloader.getResourceAsStream("_1_fonts/AdaAdama.ttf"));
    }

    static void define() {
        ApplicationFonts.buttonFont = ApplicationFonts.germanFont.deriveFont(16F);
        ApplicationFonts.toolbarButtonFont = ApplicationFonts.germanFont.deriveFont(26F);
        ApplicationFonts.secondaryToolbarButtonFont = ApplicationFonts.germanFont.deriveFont(18F);
        ApplicationFonts.radioButtonFont = ApplicationFonts.germanFont.deriveFont(12F);
        ApplicationFonts.comboBoxFont = ApplicationFonts.germanFont.deriveFont(14F);
    }
}
