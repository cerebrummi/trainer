package vokabeltrainer.common.colors;

import java.awt.Color;

import vokabeltrainer.common.ColorBase;
import vokabeltrainer.common.main.Settings;

public interface TrainerColors {
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

    static Color getInfoTextForeground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlue;
        return ColorBase.darkGold;
    }

    static Color getPanelBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlueLight;
        return ColorBase.backgroundGold;
    }

    static Color getBackground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.mediumSilverBlue;
    }

    static Color getButton() {
        if (Settings.isDarkmodeOn())
            return ColorBase.mediumSilverBlue;
        return ColorBase.backgroundGold;
    }

    static Color getButtonForeground() {
        if (Settings.isDarkmodeOn())
            return ColorBase.shadyBlue;
        return ColorBase.darkGold;
    }

    static Color getPanelBackgroundDark() {
        if (Settings.isDarkmodeOn())
            return ColorBase.slategray;
        return ColorBase.backgroundGold;
    }

    static Color getTransparent() {
        return ColorBase.transparent;
    }
}
