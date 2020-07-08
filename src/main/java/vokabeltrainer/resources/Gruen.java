package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import vokabeltrainer.ApplicationImages;

public class Gruen
{
   public static void read() throws Exception
   {
      File directoryGreenImages = new File(
            Gruen.class.getResource("gruen").getFile());
      String[] greenImages = directoryGreenImages.list();
      List<BufferedImage> greenImagesList = new ArrayList<>();
      for (String greenImage : java.util.Objects.requireNonNull(greenImages))
      {
         greenImagesList.add(ImageIO
               .read(Gruen.class.getResourceAsStream("gruen/" + greenImage)));
      }
      ApplicationImages.setGreenImages(greenImagesList);
   }
}
