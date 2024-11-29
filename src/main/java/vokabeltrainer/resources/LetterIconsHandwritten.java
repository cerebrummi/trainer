package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.editing.NikudLetter;

public class LetterIconsHandwritten
{

   public static void readNikud() throws Exception
   {
      Map<NikudLetter, BufferedImage> letterIconsNikudHandwrittenMap = new HashMap<>();

      for (NikudLetter letter : NikudLetter.values())
      {
         if (!letter.isHandwritten())
         {
            continue;
         }
         BufferedImage image = ImageIO.read(LetterIconsHandwritten.class
               .getResourceAsStream("letterIconsHandwritten" + File.separator
                     + letter.name() + ".png"));
         if (image != null)
         {
            letterIconsNikudHandwrittenMap.put(letter, image);
         }
      }

      ApplicationImages
            .setLetterIconsNikudHandwrittenMap(letterIconsNikudHandwrittenMap);
   }
}
