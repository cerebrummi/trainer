package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;
import vokabeltrainer.common.main.Settings;

public interface DictionaryColors {

    public static Color getInfoTextForeground() {
        if (Settings.isDarkmodeOn()) {
            return ColorBase.shadyBlue;
        }
        return ColorBase.darkGold;
    }

    public static Color getPanelBackground() {
        if (Settings.isDarkmodeOn()) {
            return ColorBase.shadyBlueLight;
        }
        return ColorBase.backgroundGold;
    }

    public static Color getBackground() {
        if (Settings.isDarkmodeOn()) {
            return ColorBase.mediumSilverBlue;
        }
        return ColorBase.mediumSilverBlue;
    }

    public static Color getButton() {
        if (Settings.isDarkmodeOn()) {
            return ColorBase.mediumSilverBlue;
        }
        return ColorBase.backgroundGold;
    }

    public static Color getButtonForeground() {
        if (Settings.isDarkmodeOn()) {
            return ColorBase.shadyBlue;
        }
        return ColorBase.darkGold;
    }

    public static Color getLightGrayGold() {
        if (Settings.isDarkmodeOn()) {
            return ColorBase.lightGrayGold;
        }
        return ColorBase.lightGrayGold;
    }

}
