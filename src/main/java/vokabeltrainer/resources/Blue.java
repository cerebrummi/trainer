package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import vokabeltrainer.common.ApplicationImages;

public class Blue
{
   private static List<BufferedImage> blueImagesList = new ArrayList<>();

   public static void read() throws IOException
   {

      for (int i = 0; i < 32; i++)
      {

         blueImagesList.add(ImageIO.read(
               Blue.class.getResourceAsStream("blue/blue_" + i + ".jpg")));

      }
      ApplicationImages.setBlueImages(blueImagesList);
   }

}
