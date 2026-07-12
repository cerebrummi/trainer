package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public interface TrainerColors {
    static Color getTextForeground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.white;
        return ApplicationColors.darkGold;
    }

    static Color getTextBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.lightBlue;
        return ApplicationColors.white;
    }

    static Color getInfoTextForeground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlue;
        return ApplicationColors.darkGold;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlueLight;
        return ApplicationColors.backgroundGold;
    }

    static Color getBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.mediumSilverBlue;
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

    static Color getPanelBackgroundDark() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.slategray;
        return ApplicationColors.backgroundGold;
    }

    static Color getTransparent() {
        return ApplicationColors.transparent;
    }
}
