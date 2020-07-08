package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import vokabeltrainer.ApplicationImages;

public class Blue
{
   public static void read() throws Exception
   {
      File directoryBlueImages = new File(
            Blue.class.getResource("blue").getFile());
      String[] blueImages = directoryBlueImages.list();
      List<BufferedImage> blueImagesList = new ArrayList<>();
      for (String blueImage : java.util.Objects.requireNonNull(blueImages))
      {
         blueImagesList.add(ImageIO
               .read(Blue.class.getResourceAsStream("blue/" + blueImage)));
      }
      ApplicationImages.setBlueImages(blueImagesList);
   }
}
