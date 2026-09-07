package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import vokabeltrainer.common.main.App;

public class Gruen
{
   private static List<BufferedImage> greenImagesList = new ArrayList<>();

   public static void read(App app) throws IOException
   {

      for (int i = 0; i < 72; i++)
      {
         greenImagesList.add(ImageIO.read(
               Gruen.class.getResourceAsStream("gruen/green_" + i + ".jpg")));
      }
      app.appImages.setGreenImages(greenImagesList);
   }
}
