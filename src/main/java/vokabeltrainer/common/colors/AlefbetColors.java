package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;
import vokabeltrainer.common.main.Settings;

public interface AlefbetColors {

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlueLight;
        return ColorBase.backgroundGold;
    }

    static Color getButton() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.backgroundGold;
    }

    static Color getButtonForeground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlue;
        return ColorBase.darkGold;
    }

    static Color getButton2() {
        if (Settings.isDarkmodeOn())
            return ColorBase.white;
        return ColorBase.backgroundGold;
    }

    static Color getKeyboardBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.backgroundGold;
    }

    static Color getTextForeground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.white;
        return ColorBase.darkGold;
    }

    static Color getTextBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.white;
    }
}
