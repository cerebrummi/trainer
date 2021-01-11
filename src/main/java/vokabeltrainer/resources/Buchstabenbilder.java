package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
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

   private static Map<HebrewLetter, BufferedImage> letterPicturesMap = new HashMap<>();
   private static Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap = new HashMap<>();

   public static void read() throws Exception
   {
      
      for(HebrewLetter letter : HebrewLetter.values())
      {
         if(HebrewLetter.NEWSPACE == letter)
         {
            continue;
         }
         BufferedImage picture = ImageIO.read(Buchstabenbilder.class
               .getResourceAsStream("buchstabenbilder/" + letter.name() + ".png"));
         
         letterPicturesPanelMap.put(letter,
               new LetterPictureButtonPanel(picture, letter, cards));
         letterPicturesMap.put(letter, picture);
      }
      ApplicationSpecialPanels
            .setLetterPicturesPanelMap(letterPicturesPanelMap);
      ApplicationImages.setLetterPicturesMap(letterPicturesMap);
   }

}
