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
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.ApplicationSpecialPanels;
import vokabeltrainer.Settings;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public final class Main
{
   private static Font headerFont;
   private static Font germanFont;
   private static Font germanBoldFont;
   private static Font hebrewFont;
   private static Font germanSmallFont;

   public static void main(String[] args)
   {
      try
      {
         ApplicationImages.setLogo24(ImageIO.read(
               Settings.class.getResourceAsStream("Cerebrummi_24px.png")));
         ApplicationImages.setLogo150(ImageIO.read(
               Settings.class.getResourceAsStream("Cerebrummi_150px.png")));
         ApplicationImages.setTrashcan(ImageIO
               .read(Settings.class.getResourceAsStream("trashcan-98470.png")));
         ApplicationImages.setShredder(ImageIO
               .read(Settings.class.getResourceAsStream("trash-97586.png")));
         ApplicationImages.setCopy(
               ImageIO.read(Settings.class.getResourceAsStream("copy.png")));
         ApplicationImages.setClear(
               ImageIO.read(Settings.class.getResourceAsStream("clear.png")));
         ApplicationImages.setSelect(
               ImageIO.read(Settings.class.getResourceAsStream("select.png")));
         ApplicationImages.setDeleteWord(
               ImageIO.read(Settings.class.getResourceAsStream("delete.png")));
         ApplicationImages.setNewWord(
               ImageIO.read(Settings.class.getResourceAsStream("new.png")));
         ApplicationImages.setSaveWord(
               ImageIO.read(Settings.class.getResourceAsStream("save.png")));
         ApplicationImages.setRestore(
               ImageIO.read(Settings.class.getResourceAsStream("restore.png")));
         ApplicationImages.setSearch(
               ImageIO.read(Settings.class.getResourceAsStream("search.png")));
         ApplicationImages.setSend(
               ImageIO.read(Settings.class.getResourceAsStream("send.png")));
         ApplicationImages.setStop(
               ImageIO.read(Settings.class.getResourceAsStream("stop.png")));
         ApplicationImages.setStart(
               ImageIO.read(Settings.class.getResourceAsStream("start.png")));
         ApplicationImages.setOkaySave(ImageIO
               .read(Settings.class.getResourceAsStream("okay-save.png")));
         ApplicationImages.setEmpty(
               ImageIO.read(Settings.class.getResourceAsStream("empty.png")));
         ApplicationImages.setInfoCursor(ImageIO
               .read(Settings.class.getResourceAsStream("info-cursor.png")));
         ApplicationImages.setInfoButtonIcon(ImageIO.read(
               Settings.class.getResourceAsStream("info-button-icon.png")));
         ApplicationImages.setInfoIcon(ImageIO
               .read(Settings.class.getResourceAsStream("info-icon.png")));
         ApplicationImages.setArrow(
               ImageIO.read(Settings.class.getResourceAsStream("arrow.png")));
         ApplicationImages.setCancel(
               ImageIO.read(Settings.class.getResourceAsStream("cancel.png")));
         ApplicationImages.setDone(
               ImageIO.read(Settings.class.getResourceAsStream("done.png")));
         ApplicationImages.setEmptyList(ImageIO
               .read(Settings.class.getResourceAsStream("empty-list.png")));
         ApplicationImages.setTurn(
               ImageIO.read(Settings.class.getResourceAsStream("turn.png")));
         ApplicationImages.setAnswerOkay(
               ImageIO.read(Settings.class.getResourceAsStream("answer-okay.png")));
         ApplicationImages.setAnswerNotOkay(
               ImageIO.read(Settings.class.getResourceAsStream("answer-not-okay.png")));
         ApplicationImages.setAnswerUndecided(
               ImageIO.read(Settings.class.getResourceAsStream("answer-undecided.png")));
         ApplicationImages.setReward(
               ImageIO.read(Settings.class.getResourceAsStream("baerlohnung.png")));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      try
      {
         germanFont = Font.createFont(Font.TRUETYPE_FONT,
               Settings.class.getResourceAsStream("Orkney Light.ttf"));
      }
      catch (FontFormatException | IOException e)
      {
         germanFont = new Font("Verdana", Font.PLAIN, 16);
      }
      
      try
      {
         germanSmallFont = Font.createFont(Font.TRUETYPE_FONT,
               Settings.class.getResourceAsStream("Orkney Light.ttf"));
      }
      catch (FontFormatException | IOException e)
      {
         germanFont = new Font("Verdana", Font.PLAIN, 16);
      }

      try
      {
         germanBoldFont = Font.createFont(Font.TRUETYPE_FONT,
               Settings.class.getResourceAsStream("Orkney Regular.ttf"));
      }
      catch (FontFormatException | IOException e)
      {
         germanFont = new Font("Verdana", Font.BOLD, 16);
      }
      
      try
      {
         headerFont = Font.createFont(Font.TRUETYPE_FONT,
               Settings.class.getResourceAsStream("Orkney Light.ttf"));
      }
      catch (FontFormatException | IOException e)
      {
         headerFont = germanFont;
      }

      try
      {
         hebrewFont = Font.createFont(Font.TRUETYPE_FONT,
               Settings.class.getResourceAsStream("Cardo-regular_104s.ttf"));
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
         UIManager.put("control", Settings.getTexturedBackgroundColor());
         UIManager.put("nimbusBlueGrey", Settings.getLightGrayGold());
         UIManager.put("nimbusBase", Settings.getDarkGold());
         UIManager.put("textForeground", Color.BLACK);
         UIManager.put("nimbusFocus", new Color(255, 220, 35));
         UIManager.put("ToolBar:Button.contentMargins",
               new Insets(5, 15, 5, 15));
         UIManager.put("TextField.background", Settings.getLightYellow());
         UIManager.put("ComboBox.forceOpaque", false);
         UIManager.put("TitledBorder.border", new Insets(10, 10, 10, 10));
         UIManager.put("TitledBorder.position", TitledBorder.ABOVE_BOTTOM);
         UIManager.put("TitledBorder.font", getGermanFont(16F));
         UIManager.put("TitledBorder.titleColor", Color.GRAY);
         UIManager.put("Table.opaque", false);
         UIManager.put("List.opaque", false);
         UIManager.put("Table.cellRenderer", false);
         UIManager.put("OptionPane.buttonFont", Main.getGermanFont(16F));

      }
      catch (UnsupportedLookAndFeelException e3)
      {
         e3.printStackTrace();
      }

      try
      {
         ApplicationImages.setImage(ImageIO.read(
               Settings.class.getResourceAsStream("book-2878724_1280.jpg")));

         ApplicationImages.setTrashcanBackground(ImageIO.read(Settings.class
               .getResourceAsStream("white-male-2064827_640.jpg")));

         ApplicationImages.setTexturedBackground(ImageIO.read(
               Settings.class.getResourceAsStream("textured-background.jpg")));
      }
      catch (IOException e)
      {

      }

      File directoryGreenImages = new File(
            Settings.class.getResource("gruen").getFile());
      String[] greenImages = directoryGreenImages.list();
      List<BufferedImage> greenImagesList = new ArrayList<>();
      for (String greenImage : greenImages)
      {
         try
         {
            greenImagesList.add(ImageIO.read(
                  Settings.class.getResourceAsStream("gruen/" + greenImage)));
         }
         catch (IOException e)
         {

         }
      }
      ApplicationImages.setGreenImages(greenImagesList);
      
      File directoryBlueImages = new File(
            Settings.class.getResource("blue").getFile());
      String[] blueImages = directoryBlueImages.list();
      List<BufferedImage> blueImagesList = new ArrayList<>();
      for (String blueImage : blueImages)
      {
         try
         {
            blueImagesList.add(ImageIO.read(
                  Settings.class.getResourceAsStream("blue/" + blueImage)));
         }
         catch (IOException e)
         {

         }
      }
      ApplicationImages.setBlueImages(blueImagesList);

      File directoryLetterPictures = new File(
            Settings.class.getResource("buchstabenbilder").getFile());
      String[] letterPicturesImages = directoryLetterPictures.list();
      Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap = new HashMap<>();
      Map<HebrewLetter, BufferedImage> letterPicturesMap = new HashMap<>();
      for (String letterPicture : letterPicturesImages)
      {
         try
         {
            String[] names = letterPicture
                  .substring(0, letterPicture.length() - 4).split("-");
            BufferedImage picture = ImageIO.read(Settings.class
                  .getResourceAsStream("buchstabenbilder/" + letterPicture));
            letterPicturesPanelMap.put(HebrewLetter.valueOf(names[1]),
                  new LetterPictureButtonPanel(picture, names[0],
                        HebrewLetter.valueOf(names[1])));
            letterPicturesMap.put(HebrewLetter.valueOf(names[1]), picture);
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }
      ApplicationSpecialPanels
            .setLetterPicturesPanelMap(letterPicturesPanelMap);
      ApplicationImages.setLetterPicturesMap(letterPicturesMap);

      try
      {
         ApplicationImages.setStartImage(
               ImageIO.read(Settings.class.getResourceAsStream("neutral.jpg")));

         ApplicationImages.setErrorImage(ImageIO.read(Settings.class
               .getResourceAsStream("error.jpg")));
      }
      catch (IOException e)
      {

      }

      SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            JFrame window = new JFrame();
            window.setResizable(false);
            window.setIconImage(
                  new ImageIcon(Settings.class.getResource("Cerebrummi.png"))
                        .getImage());
            Common.setjFrame(window);
            window.setTitle("Cerebrummi©");
            window.setFont(germanFont.deriveFont(14F));
            ToolTipManager.sharedInstance().setDismissDelay(8000);
            ToolTipManager.sharedInstance().setInitialDelay(1000);
            Common.setMainJPanel(new MainController().getMainView());
            window.getContentPane().add(Common.getMainJPanel());
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(1280, 700);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
         }
      });
   }

   public static Font getGermanFont(float size)
   {
      if(size < 21)
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
