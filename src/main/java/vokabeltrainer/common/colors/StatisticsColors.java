package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public interface StatisticsColors {
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

    static Color getToday() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.green;
        return ApplicationColors.green;
    }

    static Color getToLate() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.rose;
        return ApplicationColors.rose;
    }

    static Color getFuture() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.lightBlue;
        return ApplicationColors.lightBlue;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlueLight;
        return ApplicationColors.backgroundGold;
    }

    static Color getTableBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.veryLightGold;
    }

    static Color getTableCellBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumBlue;
        return ApplicationColors.lightGold;
    }

    static Color getTableCellHighlightBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.veryLightGold;
    }

    static Color getSelectedBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.slategray;
        return ApplicationColors.backgroundGold;
    }

    static Color getTextForegroundInvers() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.black;
        return ApplicationColors.white;
    }
}
