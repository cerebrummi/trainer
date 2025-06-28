package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.NikudLetter;

public class LetterIcons
{

   public static void readNikud() throws Exception
   {
      Map<NikudLetter, BufferedImage> letterIconsNikudMap = new HashMap<>();
      String type = "";
      if (Settings.isDarkmodeOn())
      {
         type = "_white";
      }

      for (NikudLetter letter : NikudLetter.values())
      {
         if (letter == NikudLetter.NEWSPACE || letter == NikudLetter.SPACE)
         {
            continue;
         }
         BufferedImage image = ImageIO
               .read(LetterIcons.class.getResourceAsStream(
                     "letterIcons" + type + "/" + letter.name() + ".png"));
         if (image != null)
         {
            letterIconsNikudMap.put(letter, image);
         }
      }

      ApplicationImages.setLetterIconsNikudMap(letterIconsNikudMap);
   }
}
