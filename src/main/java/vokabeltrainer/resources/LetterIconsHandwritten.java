package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import vokabeltrainer.common.main.App;
import vokabeltrainer.editing.NikudLetter;

public class LetterIconsHandwritten
{

   public static void readNikud(App app) throws IOException
   {
      Map<NikudLetter, BufferedImage> letterIconsNikudHandwrittenMap = new HashMap<>();
      String type = "";
      if (app.settings.isDarkmodeOn())
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

      app.appImages
            .setLetterIconsNikudHandwrittenMap(letterIconsNikudHandwrittenMap);
   }
}
