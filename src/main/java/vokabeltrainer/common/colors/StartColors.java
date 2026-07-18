package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;
import vokabeltrainer.common.main.Settings;

public interface StartColors {
    static Color getDatabase_Header() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.mediumSilverBlue;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlueLight;
        return ColorBase.backgroundGold;
    }

    static Color getDatabase_HeaderText() {
        if (Settings.isDarkmodeOn())
            return ColorBase.white;
        return ColorBase.darkGold;
    }

    static Color getTransparent() {
        return ColorBase.transparent;
    }

    static Color getDatabase_Item() {
        if (Settings.isDarkmodeOn())
            return ColorBase.gold;
        return ColorBase.lightBlue;
    }

    static Color getDatabase_Tipp() {
        if (Settings.isDarkmodeOn())
            return ColorBase.gold;
        return ColorBase.shadyBlue;
    }
}
