package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.editing.NikudLetter;

public class LetterIcons
{

   public static void read() throws Exception
   {
      Map<HebrewLetter, BufferedImage> letterIconsMap = new HashMap<>();
      
      for(HebrewLetter letter : HebrewLetter.values())
      {
         if(letter == HebrewLetter.NEWSPACE || letter == HebrewLetter.SPACE)
         {
            continue;
         }
         BufferedImage image = ImageIO.read(
               LetterIcons.class.getResourceAsStream("letterIcons/"+letter.name()+".png"));
         if(image != null)
         {
            letterIconsMap.put(letter, image);
         }
      }
      
      ApplicationImages.setLetterIconsMap(letterIconsMap);
   }
   
   
   public static void readNikud() throws Exception
   {
      Map<NikudLetter, BufferedImage> letterIconsNikudMap = new HashMap<>();
      
      for(NikudLetter letter : NikudLetter.values())
      {
         if(letter == NikudLetter.SPACE)
         {
            continue;
         }
         BufferedImage image = ImageIO.read(
               LetterIcons.class.getResourceAsStream("letterIcons/"+letter.name()+".png"));
         if(image != null)
         {
            letterIconsNikudMap.put(letter, image);
         }
      }
      
      ApplicationImages.setLetterIconsNikudMap(letterIconsNikudMap);
   }
}
