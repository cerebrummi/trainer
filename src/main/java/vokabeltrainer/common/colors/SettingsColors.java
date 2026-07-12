package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public interface SettingsColors {
    static Color getTextForeground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.white;
        return ApplicationColors.darkGold;
    }
}
