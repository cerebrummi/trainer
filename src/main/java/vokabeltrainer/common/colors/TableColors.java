package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;
import vokabeltrainer.common.main.Settings;

public interface TableColors {
    static Color getRow1() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.lightBlue;
    }

    static Color getRow2() {
        if (Settings.isDarkmodeOn())
            return ColorBase.lightGold;
        return ColorBase.veryLightGold;
    }
}
