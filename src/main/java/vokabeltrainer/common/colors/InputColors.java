package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public interface InputColors {

    static Color getEditorBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.mediumSilverBlue;
    }

    static Color getTextEditorBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.lightGold;
        return ApplicationColors.veryLightGold;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlueLight;
        return ApplicationColors.backgroundGold;
    }

    static Color getButtonBorder() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.green;
        return ApplicationColors.green;
    }

    static Color getButton() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.backgroundGold;
    }

    static Color getButton2() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.lightGold;
        return ApplicationColors.backgroundGold;
    }

    static Color getTransparent() {
        return new Color(0, 0, 0, 0);
    }

    static Color getTextBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.backgroundGold;
    }

    static Color getTextForeground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.white;
        return ApplicationColors.darkGold;
    }

    static Color getInfoTextForeground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlue;
        return ApplicationColors.darkGold;
    }
}
