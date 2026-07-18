package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;
import vokabeltrainer.common.main.Settings;

public interface InputColors {

    static Color getEditorBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.mediumSilverBlue;
    }

    static Color getTextEditorBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.lightGold;
        return ColorBase.veryLightGold;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlueLight;
        return ColorBase.backgroundGold;
    }

    static Color getButtonBorder() {
        if (Settings.isDarkmodeOn())
            return ColorBase.green;
        return ColorBase.green;
    }

    static Color getButton() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.backgroundGold;
    }

    static Color getButton2() {
        if (Settings.isDarkmodeOn())
            return ColorBase.lightGold;
        return ColorBase.backgroundGold;
    }

    static Color getTransparent() {
        return new Color(0, 0, 0, 0);
    }

    static Color getTextBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.backgroundGold;
    }

    static Color getTextForeground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.white;
        return ColorBase.darkGold;
    }

    static Color getInfoTextForeground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlue;
        return ColorBase.darkGold;
    }
}
