package vokabeltrainer.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.ApplicationSound;
import vokabeltrainer.ApplicationSpecialPanels;
import vokabeltrainer.Settings;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.panels.letterpicture.Card;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public final class Main
{
        private static final Card[] cards = { Card.BLANK, Card.PICTURE,
              Card.GERMAN, Card.HEBREW, Card.LETTER };
        private static final String path = "";
        private static Font headerFont;
        private static Font germanFont;
        private static Font germanBoldFont;
        private static Font hebrewFont;
        private static Font germanSmallFont;

        public static void main(String[] args)
        {
                try
                {
                        ApplicationImages.setLogo24(ImageIO.read(Settings.class
                              .getResourceAsStream(
                                    path + "Cerebrummi_24px.png")));
                        ApplicationImages.setLogo150(ImageIO.read(Settings.class
                              .getResourceAsStream(
                                    path + "Cerebrummi_150px.png")));
                        ApplicationImages.setTrashcan(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "trashcan-98470.png")));
                        ApplicationImages.setShredder(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "trash-97586.png")));
                        ApplicationImages.setCopy(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "copy.png")));
                        ApplicationImages.setClear(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "clear.png")));
                        ApplicationImages.setSelect(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "select.png")));
                        ApplicationImages.setDeleteWord(ImageIO
                              .read(Settings.class
                                    .getResourceAsStream(path + "delete.png")));
                        ApplicationImages.setNewWord(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "new.png")));
                        ApplicationImages.setSaveWord(ImageIO
                              .read(Settings.class
                                    .getResourceAsStream(path + "save.png")));
                        ApplicationImages.setRestore(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "restore.png")));
                        ApplicationImages.setSearch(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "search.png")));
                        ApplicationImages.setSend(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "send.png")));
                        ApplicationImages.setStop(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "stop.png")));
                        ApplicationImages.setStart(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "start.png")));
                        ApplicationImages.setOkaySave(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "okay-save.png")));
                        ApplicationImages.setEmpty(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "empty.png")));
                        ApplicationImages.setInfoCursor(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "info-cursor.png")));
                        ApplicationImages.setInfoButtonIcon(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "info-button-icon.png")));
                        ApplicationImages.setInfoIcon(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "info-icon.png")));
                        ApplicationImages.setArrow(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "arrow.png")));
                        ApplicationImages.setCancel(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "cancel.png")));
                        ApplicationImages.setDone(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "done.png")));
                        ApplicationImages.setEmptyList(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "empty-list.png")));
                        ApplicationImages.setTurn(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "turn.png")));
                        ApplicationImages.setLetterEmpty(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "letter_empty.png")));
                        ApplicationImages.setWork(ImageIO.read(Settings.class
                              .getResourceAsStream(path + "work.png")));
                        ApplicationSound.setShredderSound(Settings.class
                              .getResourceAsStream(
                                    path + "shredder-sound.byt"));
                }
                catch (IOException e)
                {
                        e.printStackTrace();
                        e.getMessage();
                }

                try
                {
                        germanFont = Font.createFont(Font.TRUETYPE_FONT,
                              Settings.class.getResourceAsStream(
                                    path + "Orkney Light.ttf"));
                }
                catch (FontFormatException | IOException e)
                {
                        germanFont = new Font("Verdana", Font.PLAIN, 16);
                }

                try
                {
                        germanSmallFont = Font.createFont(Font.TRUETYPE_FONT,
                              Settings.class.getResourceAsStream(
                                    path + "Orkney Light.ttf"));
                }
                catch (FontFormatException | IOException e)
                {
                        germanFont = new Font("Verdana", Font.PLAIN, 16);
                }

                try
                {
                        germanBoldFont = Font.createFont(Font.TRUETYPE_FONT,
                              Settings.class.getResourceAsStream(
                                    path + "Orkney Regular.ttf"));
                }
                catch (FontFormatException | IOException e)
                {
                        germanFont = new Font("Verdana", Font.BOLD, 16);
                }

                try
                {
                        headerFont = Font.createFont(Font.TRUETYPE_FONT,
                              Settings.class.getResourceAsStream(
                                    path + "Orkney Light.ttf"));
                }
                catch (FontFormatException | IOException e)
                {
                        headerFont = germanFont;
                }

                try
                {
                        hebrewFont = Font.createFont(Font.TRUETYPE_FONT,
                              Settings.class.getResourceAsStream(
                                    path + "Cardo-regular_104s.ttf"));
                }
                catch (FontFormatException | IOException e)
                {
                        hebrewFont = germanFont;
                }

                Data.initDataBase();

                try
                {
                        NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
                        UIManager.setLookAndFeel(nimbus);
                        UIManager.put("control",
                              Settings.getTexturedBackgroundColor());
                        UIManager.put("nimbusBlueGrey",
                              Settings.getLightGrayGold());
                        UIManager.put("nimbusBase", Settings.getDarkGold());
                        UIManager.put("textForeground", Color.BLACK);
                        UIManager.put("nimbusFocus", new Color(255, 220, 35));
                        UIManager.put("ToolBar:Button.contentMargins",
                              new Insets(5, 15, 5, 15));
                        UIManager.put("TextField.background",
                              Settings.getLightYellow());
                        UIManager.put("ComboBox.forceOpaque", false);
                        UIManager.put("TitledBorder.border",
                              new Insets(10, 10, 10, 10));
                        UIManager.put("TitledBorder.position",
                              TitledBorder.ABOVE_BOTTOM);
                        UIManager.put("TitledBorder.font", getGermanFont(16F));
                        UIManager.put("TitledBorder.titleColor", Color.GRAY);
                        UIManager.put("Table.opaque", false);
                        UIManager.put("List.opaque", false);
                        UIManager.put("Table.cellRenderer", false);
                        UIManager.put("OptionPane.buttonFont",
                              Main.getGermanFont(16F));

                }
                catch (UnsupportedLookAndFeelException e3)
                {
                        e3.printStackTrace();
                }

                try
                {
                        ApplicationImages.setImage(ImageIO.read(Settings.class
                              .getResourceAsStream(
                                    path + "book-2878724_1280.jpg")));

                        ApplicationImages.setTrashcanBackground(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "white-male-2064827_640.jpg")));

                        ApplicationImages.setTexturedBackground(ImageIO
                              .read(Settings.class.getResourceAsStream(
                                    path + "textured-background.jpg")));
                }
                catch (IOException e)
                {
                        // nothing
                }

                File directoryLetterPictures = new File(
                      Settings.class.getResource(path + "/buchstabenbilder")
                            .getFile());
                String[] letterPicturesImages = directoryLetterPictures.list();
                Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap = new HashMap<>();
                Map<HebrewLetter, BufferedImage> letterPicturesMap = new HashMap<>();

                for (String letterPicture : java.util.Objects
                      .requireNonNull(letterPicturesImages))
                {
                        try
                        {
                                String[] names = letterPicture
                                      .substring(0, letterPicture.length() - 4)
                                      .split("-");
                                BufferedImage picture = ImageIO
                                      .read(Settings.class.getResourceAsStream(
                                            path + "buchstabenbilder/"
                                                  + letterPicture));
                                letterPicturesPanelMap
                                      .put(HebrewLetter.valueOf(names[1]),
                                            new LetterPictureButtonPanel(
                                                  picture, names[0],
                                                  HebrewLetter
                                                        .valueOf(names[1]),
                                                  cards));
                                letterPicturesMap
                                      .put(HebrewLetter.valueOf(names[1]),
                                            picture);
                        }
                        catch (IOException e)
                        {
                                e.printStackTrace();
                        }
                }
                ApplicationSpecialPanels
                      .setLetterPicturesPanelMap(letterPicturesPanelMap);
                ApplicationImages.setLetterPicturesMap(letterPicturesMap);

                // images and sounds for training can be loaded later
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
                {
                        @Override protected Void doInBackground()
                              throws Exception
                        {
                                ApplicationImages.setStartImage(ImageIO
                                      .read(Settings.class.getResourceAsStream(
                                            path + "neutral.jpg")));
                                ApplicationImages.setErrorImage(ImageIO
                                      .read(Settings.class.getResourceAsStream(
                                            path + "error.jpg")));
                                ApplicationImages.setAnswerOkay(ImageIO
                                      .read(Settings.class.getResourceAsStream(
                                            path + "answer-okay.png")));
                                ApplicationImages.setAnswerNotOkay(ImageIO
                                      .read(Settings.class.getResourceAsStream(
                                            path + "answer-not-okay.png")));
                                ApplicationImages.setAnswerUndecided(ImageIO
                                      .read(Settings.class.getResourceAsStream(
                                            path + "answer-undecided.png")));
                                ApplicationImages.setReward(ImageIO
                                      .read(Settings.class.getResourceAsStream(
                                            path + "baerlohnung.png")));
                                ApplicationImages.setSoundOn(ImageIO
                                      .read(Settings.class.getResourceAsStream(
                                            path + "headphones-2104207.png")));
                                ApplicationImages.setSoundOff(ImageIO
                                      .read(Settings.class.getResourceAsStream(
                                            path
                                                  + "headphones-2104207-gray.png")));

                                File directoryGreenImages = new File(
                                      Settings.class.getResource(path + "gruen")
                                            .getFile());
                                String[] greenImages = directoryGreenImages
                                      .list();
                                List<BufferedImage> greenImagesList = new ArrayList<>();
                                for (String greenImage : java.util.Objects
                                      .requireNonNull(greenImages))
                                {
                                        try
                                        {
                                                greenImagesList.add(ImageIO
                                                      .read(Settings.class
                                                            .getResourceAsStream(
                                                                  path
                                                                        + "gruen/"
                                                                        + greenImage)));
                                        }
                                        catch (IOException e)
                                        {
                                                // nothing
                                        }
                                }
                                ApplicationImages
                                      .setGreenImages(greenImagesList);

                                File directoryBlueImages = new File(
                                      Settings.class.getResource(path + "blue")
                                            .getFile());
                                String[] blueImages = directoryBlueImages
                                      .list();
                                List<BufferedImage> blueImagesList = new ArrayList<>();
                                for (String blueImage : java.util.Objects
                                      .requireNonNull(blueImages))
                                {
                                        try
                                        {
                                                blueImagesList.add(ImageIO
                                                      .read(Settings.class
                                                            .getResourceAsStream(
                                                                  path + "blue/"
                                                                        + blueImage)));
                                        }
                                        catch (IOException e)
                                        {
                                                // nothing
                                        }
                                }
                                ApplicationImages.setBlueImages(blueImagesList);

                                ApplicationSound.setSplotchSound(Settings.class
                                      .getResourceAsStream(
                                            path + "splotch-sound.byt"));
                                ApplicationSound.setClappingSound(Settings.class
                                      .getResourceAsStream(
                                            path + "clapping-sound.byt"));
                                ApplicationSound.setWaveSound(Settings.class
                                      .getResourceAsStream(
                                            path + "wave-sound.byt"));

                                return null;
                        }
                };

                worker.execute();

                SwingUtilities.invokeLater(() -> {
                        javax.swing.JFrame window = new JFrame();
                        window.setResizable(false);
                        window.setIconImage(new ImageIcon(
                              vokabeltrainer.Settings.class
                                    .getResource("Cerebrummi.png")).getImage());
                        vokabeltrainer.common.Common.setjFrame(window);
                        window.setTitle("Cerebrummi©");
                        window.setFont(germanFont.deriveFont(14F));
                        ToolTipManager.sharedInstance().setDismissDelay(8000);
                        ToolTipManager.sharedInstance().setInitialDelay(1000);
                        vokabeltrainer.common.Common.setMainJPanel(
                              new vokabeltrainer.common.MainController()
                                    .getMainView());
                        window.getContentPane().add(vokabeltrainer.common.Common
                              .getMainJPanel());
                        window.setDefaultCloseOperation(
                              javax.swing.JFrame.EXIT_ON_CLOSE);
                        window.setSize(1300, 735);
                        window.setLocationRelativeTo(null);
                        window.setVisible(true);
                });
        }

        /*
         * ClappingSound: Attribution License: Attribution — You must give
         * appropriate credit, provide a link to the license, and indicate if changes
         * were made. You may do so in any reasonable manner, but not in any way that
         * suggests the licensor endorses you or your use.
         * https://freesound.org/people/ebcrosby/sounds/339483/
         */

        /*
         * WaveSound: Creative Commons 0 License.: No Copyright The person who
         * associated a work with this deed has dedicated the work to the public
         * domain by waiving all of his or her rights to the work worldwide under
         * copyright law, including all related and neighboring rights, to the extent
         * allowed by law. You can copy, modify, distribute and perform the work,
         * even for commercial purposes, all without asking permission.
         * https://freesound.org/people/florianreichelt/sounds/450755/
         */

        public static Font getGermanFont(float size)
        {
                if (size < 21)
                {
                        return germanSmallFont.deriveFont(size);
                }
                return germanFont.deriveFont(size);
        }

        public static Font getGermanBoldFont(float size)
        {
                return germanBoldFont.deriveFont(size);
        }

        public static Font getHebrewFont(float size)
        {
                return hebrewFont.deriveFont(size);
        }

        public static Font getHeaderFont(float size)
        {
                return headerFont.deriveFont(size);
        }
}
