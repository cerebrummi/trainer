package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.ApplicationSpecialPanels;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.panels.letterpicture.Card;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public class Buchstabenbilder
{
   private static final Card[] cards = { Card.BLANK, Card.PICTURE, Card.GERMAN,
         Card.HEBREW, Card.LETTER, Card.LETTER_HANDWRITTEN };

   private static Map<NikudLetter, BufferedImage> letterPicturesMap = new HashMap<>();
   private static Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap = new HashMap<>();

   public static void read() throws IOException
   {

      for (NikudLetter letter : NikudLetter.values())
      {
         if (NikudLetter.SPACE == letter || NikudLetter.GERESCH == letter
               || NikudLetter.GERSCHAYIM == letter)
         {
            // !continue
         }
         else if (!letter.isHandwritten())
         {
            continue;
         }
         BufferedImage picture = ImageIO
               .read(Buchstabenbilder.class.getResourceAsStream(
                     "buchstabenbilder/" + letter.name() + ".png"));

         if (!(NikudLetter.SPACE == letter || NikudLetter.GERESCH == letter
               || NikudLetter.GERSCHAYIM == letter))
         {
            letterPicturesPanelMap.put(letter,
                  new LetterPictureButtonPanel(picture, letter, cards));
         }

         letterPicturesMap.put(letter, picture);
      }

      ApplicationSpecialPanels
            .setLetterPicturesPanelMap(letterPicturesPanelMap);

      ApplicationImages.setLetterPicturesMap(letterPicturesMap);
   }

   public static void reRead() throws Exception
   {
      ApplicationSpecialPanels.getLetterPicturesPanelMap().clear();

      for (NikudLetter letter : NikudLetter.values())
      {
         if (NikudLetter.SPACE == letter || NikudLetter.GERESCH == letter
               || NikudLetter.GERSCHAYIM == letter)
         {
            // !continue
         }
         else if (!letter.isHandwritten())
         {
            continue;
         }
         BufferedImage picture = letterPicturesMap.get(letter);

         if (!(NikudLetter.SPACE == letter || NikudLetter.GERESCH == letter
               || NikudLetter.GERSCHAYIM == letter))
         {
            letterPicturesPanelMap.put(letter,
                  new LetterPictureButtonPanel(picture, letter, cards));
         }
      }

      ApplicationSpecialPanels
            .setLetterPicturesPanelMap(letterPicturesPanelMap);
   }
}
