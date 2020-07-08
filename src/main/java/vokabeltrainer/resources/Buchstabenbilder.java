package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

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

   public static void read() throws Exception
   {
      File directoryLetterPictures = new File(
            Buchstabenbilder.class.getResource("buchstabenbilder").getFile());
      String[] letterPicturesImages = directoryLetterPictures.list();
      Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap = new HashMap<>();
      Map<HebrewLetter, BufferedImage> letterPicturesMap = new HashMap<>();

      for (String letterPicture : java.util.Objects
            .requireNonNull(letterPicturesImages))
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
}
