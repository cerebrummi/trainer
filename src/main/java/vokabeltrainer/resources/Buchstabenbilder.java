package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.ApplicationSpecialPanels;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.editing.NikudLetterDistinction;
import vokabeltrainer.panels.letterpicture.Card;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public class Buchstabenbilder
{
   private static final Card[] cards = { Card.BLANK, Card.PICTURE, Card.GERMAN,
         Card.HEBREW, Card.LETTER };

   private static Map<NikudLetter, BufferedImage> letterPicturesMap = new HashMap<>();
   private static Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap = new HashMap<>();

   public static void read() throws Exception
   {

      for (NikudLetter letter : NikudLetter.values())
      {
         if (NikudLetterDistinction.LETTER != letter.getDistinction()
               || NikudLetter.NEWSPACE == letter || NikudLetter.MAQAF == letter
               || NikudLetter.PASEQ == letter || NikudLetter.SOF_PASUQ == letter
               || NikudLetter.HAFUKAH == letter
               || NikudLetter.JIDDISH_DOUBLE_WAW == letter
               || NikudLetter.JIDDISH_WAW_JOD == letter
               || NikudLetter.JIDDISH_DOUBLE_JOD == letter)
         {
            continue;
         }
         BufferedImage picture = ImageIO
               .read(Buchstabenbilder.class.getResourceAsStream(
                     "buchstabenbilder/" + letter.name() + ".png"));

         letterPicturesPanelMap.put(letter,
               new LetterPictureButtonPanel(picture, letter, cards));
         letterPicturesMap.put(letter, picture);
      }
      ApplicationSpecialPanels
            .setLetterPicturesPanelMap(letterPicturesPanelMap);
      ApplicationImages.setLetterPicturesMap(letterPicturesMap);
   }

}
