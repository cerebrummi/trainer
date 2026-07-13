package vokabeltrainer.common;

import java.awt.Color;

public interface ApplicationColors {
    Color lightBlue = new Color(215, 231, 247);
    Color lightGrayBlue = new Color(150, 165, 180);
    Color mediumBlue = new Color(164, 190, 217);
    Color mediumSilverBlue = new Color(162, 180, 202);
    Color darkSilverBlue = new Color(62, 80, 102);
    Color shadyBlue = new Color(44, 51, 73);
    Color shadyBlueLight = new Color(70, 85, 130);
    Color gold = new Color(169, 136, 103);
    Color darkGold = new Color(143, 101, 58);
    Color lightGold = new Color(209, 191, 173);
    Color lightGrayGold = new Color(203, 191, 180);
    Color mediumGold = new Color(228, 213, 197);
    Color backgroundGold = new Color(223, 210, 198);
    Color veryLightGold = new Color(247, 240, 232);
    Color green = new Color(181, 192, 81);
    Color brightGreen = Color.GREEN;
    Color texturedBackgroundColor = new Color(225, 216, 211);
    Color texturedBackgroundColorLight = new Color(230, 221, 217);
    Color white = new Color(255, 255, 255);
    Color lightYellow = new Color(255, 255, 235);
    Color darkRed = new Color(216, 0, 0);
    Color rose = new Color(247, 215, 215);
    Color sunflowerYellow = new Color(255, 220, 35);
    Color darkGreen = new Color(110, 110, 70);
    Color transparent = new Color(0, 0, 0, 0);
    Color slategray = new Color(81, 81, 91);
    Color black = Color.BLACK;
    Color red = Color.RED;

    static Color getLightBlue() {
        if (Settings.isDarkmodeOn())
            return slategray;
        return lightBlue;
    }

    static Color getLightGrayBlue() {
        if (Settings.isDarkmodeOn())
            return shadyBlue;
        return lightGrayBlue;
    }

    static Color getMediumBlue() {
        if (Settings.isDarkmodeOn())
            return shadyBlue;
        return mediumBlue;
    }

    static Color getMediumSilverBlue() {
        if (Settings.isDarkmodeOn())
            return darkGreen;
        return mediumSilverBlue;
    }

    static Color getShadyBlue() {
        if (Settings.isDarkmodeOn())
            return white;
        return shadyBlue;
    }

    static Color getGold() {
        if (Settings.isDarkmodeOn())
            return darkGreen;
        return gold;
    }

    static Color getDarkGold() {
        if (Settings.isDarkmodeOn())
            return white;
        return darkGold;
    }

    static Color getLightGold() {
        if (Settings.isDarkmodeOn())
            return mediumSilverBlue;
        return lightGold;
    }

    static Color getLightGrayGold() {
        if (Settings.isDarkmodeOn())
            return shadyBlue;
        return lightGrayGold;
    }

    static Color getMediumGold() {
        if (Settings.isDarkmodeOn())
            return mediumBlue;
        return mediumGold;
    }

    static Color getBackgroundGold() {
        if (Settings.isDarkmodeOn())
            return shadyBlue;
        return backgroundGold;
    }

    static Color getVeryLightGold() {
        if (Settings.isDarkmodeOn())
            return darkGreen; // list background color B
        return veryLightGold;
    }

    static Color getGreen() {
        if (Settings.isDarkmodeOn())
            return darkGreen;
        return green;
    }

    static Color getSelectionGreen() {
        if (Settings.isDarkmodeOn())
            return sunflowerYellow;
        return green;
    }

    static Color getTransparent() {
        return new Color(0, 0, 0, 0);
    }

    static Color getTexturedBackgroundColor() {
        if (Settings.isDarkmodeOn())
            return shadyBlue;
        return texturedBackgroundColor;
    }

    static Color getTexturedBackgroundColorLight() {
        if (Settings.isDarkmodeOn())
            return shadyBlue;
        return texturedBackgroundColorLight;
    }

    static Color getWhite() {
        if (Settings.isDarkmodeOn())
            return mediumSilverBlue;
        return white;
    }

    static Color getLightYellow() {
        if (Settings.isDarkmodeOn())
            return mediumSilverBlue;
        return lightYellow;
    }

    static Color getDarkRed() {
        if (Settings.isDarkmodeOn())
            return shadyBlue;
        return darkRed;
    }

    static Color getRose() {
        if (Settings.isDarkmodeOn())
            return texturedBackgroundColor;
        return rose;
    }

    static Color getSunflowerYellow() {
        if (Settings.isDarkmodeOn())
            return mediumBlue;
        return sunflowerYellow;
    }

    static Color getGray() {
        if (Settings.isDarkmodeOn())
            return mediumSilverBlue;
        return Color.GRAY;
    }

    static Color getBlack() {
        if (Settings.isDarkmodeOn())
            return white;
        return Color.BLACK;
    }
}
