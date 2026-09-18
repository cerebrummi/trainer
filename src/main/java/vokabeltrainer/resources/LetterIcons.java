package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import vokabeltrainer.common.main.App;
import vokabeltrainer.editing.NikudLetter;

public class LetterIcons
{

   public static void readNikud(App app) throws IOException
   {
      Map<NikudLetter, BufferedImage> letterIconsNikudMap = new HashMap<>();
      String type = "";
      if (!app.settings.isDarkmodeOn())
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

      app.appImages.setLetterIconsNikudMap(letterIconsNikudMap);
   }
}
