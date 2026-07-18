package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;
import vokabeltrainer.common.main.Settings;

public interface StatisticsColors {
    static Color getTextForeground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.white;
        return ColorBase.darkGold;
    }

    static Color getTextBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.lightBlue;
        return ColorBase.white;
    }

    static Color getToday() {
        if (Settings.isDarkmodeOn())
            return ColorBase.green;
        return ColorBase.green;
    }

    static Color getToLate() {
        if (Settings.isDarkmodeOn())
            return ColorBase.rose;
        return ColorBase.rose;
    }

    static Color getFuture() {
        if (Settings.isDarkmodeOn())
            return ColorBase.lightBlue;
        return ColorBase.lightBlue;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlueLight;
        return ColorBase.backgroundGold;
    }

    static Color getTableBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.veryLightGold;
    }

    static Color getTableCellBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumBlue;
        return ColorBase.lightGold;
    }

    static Color getTableCellHighlightBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.veryLightGold;
    }

    static Color getSelectedBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.slategray;
        return ColorBase.backgroundGold;
    }

    static Color getTextForegroundInvers() {
        if (Settings.isDarkmodeOn())
            return ColorBase.black;
        return ColorBase.white;
    }
}
