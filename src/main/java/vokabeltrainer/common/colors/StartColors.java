package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Settings;

public interface StartColors {
    static Color getDatabase_Header() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.mediumSilverBlue;
        return ApplicationColors.mediumSilverBlue;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.shadyBlueLight;
        return ApplicationColors.backgroundGold;
    }

    static Color getDatabase_HeaderText() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.white;
        return ApplicationColors.darkGold;
    }

    static Color getTransparent() {
        return ApplicationColors.transparent;
    }

    static Color getDatabase_Item() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.gold;
        return ApplicationColors.lightBlue;
    }

    static Color getDatabase_Tipp() {
        if (Settings.isDarkmodeOn())
            return ApplicationColors.gold;
        return ApplicationColors.shadyBlue;
    }
}
