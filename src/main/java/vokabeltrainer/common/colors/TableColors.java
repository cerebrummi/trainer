package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public interface TableColors {
    static Color getRow1() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.lightBlue;
    }

    static Color getRow2() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.lightGold;
        return ApplicationColors.veryLightGold;
    }
}
