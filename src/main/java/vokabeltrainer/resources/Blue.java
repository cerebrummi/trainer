package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

import vokabeltrainer.ApplicationImages;

public class Blue
{
   private static List<BufferedImage> blueImagesList = new ArrayList<>();

   public static void read() throws Exception
   {
      File directoryBlueImages = new File(
            Blue.class.getResource("blue").getFile());
      String[] blueImages = directoryBlueImages.list();

      for (String blueImage : java.util.Objects.requireNonNull(blueImages))
      {

         blueImagesList.add(ImageIO
               .read(Blue.class.getResourceAsStream("blue/" + blueImage)));

      }
      ApplicationImages.setBlueImages(blueImagesList);
   }

   public static void readZip() throws Exception
   {
      CodeSource src = Blue.class.getProtectionDomain().getCodeSource();
      if (src != null)
      {
         URL jar = src.getLocation();
         ZipFile zipFile = new ZipFile(jar.getFile());
         Iterator<? extends ZipEntry> iterator = zipFile.entries().asIterator();
         while (iterator.hasNext())
         {
            ZipEntry ze = iterator.next();
            String name = ze.getName();
                  
            if (name.startsWith("vokabeltrainer/resources/blue/"))
            {
               blueImagesList.add(ImageIO.read(zipFile.getInputStream(ze)));
            }
         }
      }
      else
      {
         throw new IOException("can not find code source for blue images");
      }
      ApplicationImages.setBlueImages(blueImagesList);
   }
}
