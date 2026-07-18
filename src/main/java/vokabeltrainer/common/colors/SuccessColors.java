package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;
import vokabeltrainer.common.main.Settings;

public interface SuccessColors {
    static Color getTextForeground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.white;
        return ColorBase.darkGold;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlueLight;
        return ColorBase.backgroundGold;
    }

    static Color getPanelBackgroundLight() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.mediumSilverBlue;
    }

    static Color getTableBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.slategray;
        return ColorBase.white;
    }
}
