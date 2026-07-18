package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;
import vokabeltrainer.common.main.Settings;

public interface SettingsColors {
    static Color getTextForeground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.white;
        return ColorBase.darkGold;
    }
}
