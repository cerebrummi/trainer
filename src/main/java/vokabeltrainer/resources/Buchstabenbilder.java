package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.ApplicationSpecialPanels;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.panels.letterpicture.Card;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public class Buchstabenbilder
{
   private static final Card[] cards = { Card.BLANK, Card.PICTURE, Card.GERMAN,
         Card.HEBREW, Card.LETTER };

   private static Map<HebrewLetter, BufferedImage> letterPicturesMap = new HashMap<>();
   private static Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap = new HashMap<>();

   public static void read() throws Exception
   {
      File directoryLetterPictures = new File(
            Buchstabenbilder.class.getResource("buchstabenbilder").getFile());
      String[] letterPicturesImages = directoryLetterPictures.list();

      for (String letterPicture : letterPicturesImages)
      {
         String[] names = letterPicture.substring(0, letterPicture.length() - 4)
               .split("-");
         BufferedImage picture = ImageIO.read(Buchstabenbilder.class
               .getResourceAsStream("buchstabenbilder/" + letterPicture));
         
         letterPicturesPanelMap.put(HebrewLetter.valueOf(names[1]),
               new LetterPictureButtonPanel(picture, names[0],
                     HebrewLetter.valueOf(names[1]), cards));
         letterPicturesMap.put(HebrewLetter.valueOf(names[1]), picture);
      }
      ApplicationSpecialPanels
            .setLetterPicturesPanelMap(letterPicturesPanelMap);
      ApplicationImages.setLetterPicturesMap(letterPicturesMap);
   }

   public static void readZip() throws Exception
   {
      CodeSource src = Buchstabenbilder.class.getProtectionDomain()
            .getCodeSource();
      if (src != null)
      {
         URL jar = src.getLocation();
         ZipFile zipFile = new ZipFile(jar.getFile());
         Iterator<? extends ZipEntry> iterator = zipFile.entries().asIterator();
         while(iterator.hasNext())
         {
            ZipEntry ze = iterator.next();
            String name = ze.getName();

            if (name.length() > 42 && name
                  .startsWith("vokabeltrainer/resources/buchstabenbilder/"))
            {
               String[] names = name.substring(42)
                     .substring(0, name.length() - 46).split("-");
               BufferedImage picture = ImageIO.read(zipFile.getInputStream(ze));
               if (picture == null)
               {
                  throw new IOException("could not read buchstabenbild");
               }
               letterPicturesPanelMap.put(HebrewLetter.valueOf(names[1]),
                     new LetterPictureButtonPanel(picture, names[0],
                           HebrewLetter.valueOf(names[1]), cards));
               letterPicturesMap.put(HebrewLetter.valueOf(names[1]), picture);
            }
         }
      }
      else
      {
         throw new IOException(
               "can not find code source for buchstabenbilder images");
      }
      ApplicationSpecialPanels
            .setLetterPicturesPanelMap(letterPicturesPanelMap);
      ApplicationImages.setLetterPicturesMap(letterPicturesMap);
   }
}
