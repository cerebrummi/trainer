package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public interface MainColors {
    static Color getToolbarBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlueLight;
        return ApplicationColors.backgroundGold;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlueLight;
        return ApplicationColors.backgroundGold;
    }

    static Color getButtonBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.backgroundGold;
    }
}
