package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public interface AlefbetColors {

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlueLight;
        return ApplicationColors.backgroundGold;
    }

    static Color getButton() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.backgroundGold;
    }

    static Color getButtonForeground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlue;
        return ApplicationColors.darkGold;
    }

    static Color getButton2() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.white;
        return ApplicationColors.backgroundGold;
    }

    static Color getKeyboardBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.backgroundGold;
    }

    static Color getTextForeground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.white;
        return ApplicationColors.darkGold;
    }

    static Color getTextBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.white;
    }
}
