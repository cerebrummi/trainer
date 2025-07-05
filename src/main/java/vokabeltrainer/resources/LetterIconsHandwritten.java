package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.NikudLetter;

public class LetterIconsHandwritten
{

   public static void readNikud() throws Exception
   {
      Map<NikudLetter, BufferedImage> letterIconsNikudHandwrittenMap = new HashMap<>();
      String type = "";
      if (Settings.isDarkmodeOn())
      {
         type = "_white";
      }

      for (NikudLetter letter : NikudLetter.values())
      {
         if (!letter.isHandwritten())
         {
            continue;
         }
         BufferedImage image = ImageIO.read(LetterIconsHandwritten.class
               .getResourceAsStream("letterIconsHandwritten" + type + "/"
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
