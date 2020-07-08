package vokabeltrainer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import vokabeltrainer.common.Main;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.panels.letterpicture.Card;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public class Resources
{
   private static final Card[] cards = { Card.BLANK, Card.PICTURE, Card.GERMAN,
         Card.HEBREW, Card.LETTER };

   public static void read() throws Exception
   {
      Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap = new HashMap<>();
      Map<HebrewLetter, BufferedImage> letterPicturesMap = new HashMap<>();  
      List<BufferedImage> greenImagesList = new ArrayList<>();
      List<BufferedImage> blueImagesList = new ArrayList<>();

      ZipFile zipFile = new ZipFile(Resources.class.getClassLoader()
            .getResource("vokabeltrainer.jar").toURI().toURL().getPath());

      ZipInputStream zip = new ZipInputStream(Resources.class.getClassLoader()
            .getResource("vokabeltrainer.jar").toURI().toURL().openStream());
      while (true)
      {
         ZipEntry e = zip.getNextEntry();
         if (e == null)
            break;
         String name = e.getName();

         if (name.startsWith("_2_images"))
         {
            readImages(zipFile, e, name);
         }
         else if (name.startsWith("_1_fonts"))
         {
            readFonts(zipFile, e, name);
         }
         else if (name.startsWith("buchstabenbilder"))
         {
            String[] names = name.substring(16).substring(1, name.length() - 20)
                  .split("-");
            BufferedImage picture = ImageIO.read(zipFile.getInputStream(e));
            letterPicturesPanelMap.put(HebrewLetter.valueOf(names[1]),
                  new LetterPictureButtonPanel(picture, names[0],
                        HebrewLetter.valueOf(names[1]), cards));
            letterPicturesMap.put(HebrewLetter.valueOf(names[1]), picture);
         }
         else if (name.startsWith("blue"))
         {
            blueImagesList.add(ImageIO.read(zipFile.getInputStream(e)));
         }
         else if (name.startsWith("gruen"))
         {
            greenImagesList.add(ImageIO.read(zipFile.getInputStream(e)));
         }
         else if (name.startsWith("sounds"))
         {
            readSounds(zipFile, e, name);
         }
         zip.closeEntry();
      }
      zip.close();

      ApplicationImages.setBlueImages(blueImagesList);
      ApplicationImages.setGreenImages(greenImagesList);
      ApplicationSpecialPanels
            .setLetterPicturesPanelMap(letterPicturesPanelMap);
      ApplicationImages.setLetterPicturesMap(letterPicturesMap);
   }

   private static void readSounds(ZipFile zipFile, ZipEntry e, String name)
         throws IOException
   {
      switch (name)
      {
      case "sounds/clapping-sound.byt":
         ApplicationSound.setClappingSound(zipFile.getInputStream(e));
         break;
      case "sounds/shredder-sound.byt":
         ApplicationSound.setShredderSound(zipFile.getInputStream(e));
         break;
      case "sounds/splotch-sound.byt":
         ApplicationSound.setSplotchSound(zipFile.getInputStream(e));
         break;
      case "sounds/wave-sound.byt":
         ApplicationSound.setWaveSound(zipFile.getInputStream(e));
         break;
      }
   }

   private static void readFonts(ZipFile zipFile, ZipEntry e, String name)
         throws FontFormatException, IOException
   {
      switch (name)
      {
      case "_1_fonts/Cardo-regular_104s.ttf":
         Main.setHebrewFont(
               Font.createFont(Font.TRUETYPE_FONT, zipFile.getInputStream(e)));
         break;
      case "_1_fonts/Orkney Bold.ttf":
         break;
      case "_1_fonts/Orkney Light.ttf":
         Main.setGermanFont(
               Font.createFont(Font.TRUETYPE_FONT, zipFile.getInputStream(e)));
         break;
      case "_1_fonts/Orkney Medium.ttf":
         Main.setGermanBoldFont(
               Font.createFont(Font.TRUETYPE_FONT, zipFile.getInputStream(e)));
         break;
      case "_1_fonts/Orkney Regular.ttf":
         break;
      }
   }

   private static void readImages(ZipFile zipFile, ZipEntry e, String name)
         throws IOException
   {
      switch (name)
      {
      case "_2_images/answer-not-okay.png":
         ApplicationImages
               .setAnswerNotOkay(ImageIO.read(zipFile.getInputStream(e)));
         break;

      case "_2_images/answer-okay.png":
         ApplicationImages
               .setAnswerOkay(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/answer-undecided.png":
         ApplicationImages
               .setAnswerUndecided(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/arrow.png":
         ApplicationImages.setArrow(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/baerlohnung.png":
         ApplicationImages.setReward(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/book-2878724_1280.jpg":
         ApplicationImages.setImage(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/cancel.png":
         ApplicationImages.setCancel(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/Cerebrummi.png":
         ApplicationImages.setLogo(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/Cerebrummi_150px.png":
         ApplicationImages.setLogo150(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/Cerebrummi_24px.png":
         ApplicationImages.setLogo24(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/clear.png":
         ApplicationImages.setClear(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/copy.png":
         ApplicationImages.setCopy(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/delete.png":
         ApplicationImages
               .setDeleteWord(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/done.png":
         ApplicationImages.setDone(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/empty.png":
         ApplicationImages.setEmpty(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/empty-list.png":
         ApplicationImages
               .setEmptyList(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/error.jpg":
         ApplicationImages
               .setErrorImage(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/headphones-2104207.png":
         ApplicationImages.setSoundOn(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/headphones-2104207-gray.png":
         ApplicationImages.setSoundOff(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/hebrew-letters-2730159_1280.jpg":
         break;
      case "_2_images/icon.png":
         break;
      case "_2_images/info-button-icon.png":
         ApplicationImages
               .setInfoButtonIcon(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/info-cursor.png":
         ApplicationImages
               .setInfoCursor(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/info-icon.png":
         ApplicationImages.setInfoIcon(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/jewish-1159704_1280.jpg":
         break;
      case "_2_images/letter_empty.png":
         ApplicationImages
               .setLetterEmpty(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/neutral.jpg":
         ApplicationImages
               .setStartImage(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/new.png":
         ApplicationImages.setNewWord(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/okay-save.png":
         ApplicationImages.setOkaySave(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/restore.png":
         ApplicationImages.setRestore(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/robert-zunikoff-483401-unsplash.jpg":
         break;
      case "_2_images/save.png":
         ApplicationImages.setSaveWord(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/scroll-1410168_1280.jpg":
         break;
      case "_2_images/search.png":
         ApplicationImages.setSearch(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/select.png":
         ApplicationImages.setSelect(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/send.png":
         ApplicationImages.setSend(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/start.png":
         ApplicationImages.setStart(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/stop.png":
         ApplicationImages.setStop(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/textured-background.jpg":
         ApplicationImages
               .setTexturedBackground(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/trash-97586.png":
         ApplicationImages.setShredder(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/trashcan-98470.png":
         ApplicationImages.setTrashcan(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/turn.png":
         ApplicationImages.setTurn(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/white-male-2064827_640.jpg":
         ApplicationImages
               .setTrashcanBackground(ImageIO.read(zipFile.getInputStream(e)));
         break;
      case "_2_images/work.png":
         ApplicationImages.setWork(ImageIO.read(zipFile.getInputStream(e)));
      }
   }

}
