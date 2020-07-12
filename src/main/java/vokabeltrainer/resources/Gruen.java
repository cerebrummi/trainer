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

public class Gruen
{
   private static List<BufferedImage> greenImagesList = new ArrayList<>();

   public static void read() throws Exception
   {
      File directoryGreenImages = new File(
            Gruen.class.getResource("gruen").getFile());
      String[] greenImages = directoryGreenImages.list();

      for (String greenImage : java.util.Objects.requireNonNull(greenImages))
      {
         greenImagesList.add(ImageIO
               .read(Gruen.class.getResourceAsStream("gruen/" + greenImage)));
      }
      ApplicationImages.setGreenImages(greenImagesList);
   }
   
   public static void readZip() throws Exception
   {
      CodeSource src = Gruen.class.getProtectionDomain().getCodeSource();
      if (src != null)
      {
         URL jar = src.getLocation();
         ZipFile zipFile = new ZipFile(jar.getFile());
         Iterator<? extends ZipEntry> iterator = zipFile.entries().asIterator();
         while (iterator.hasNext())
         {
            ZipEntry ze = iterator.next();
            String name = ze.getName();
            
            if (name.startsWith("vokabeltrainer/resources/gruen/"))
            {
               BufferedImage image = ImageIO.read(zipFile.getInputStream(ze));
               if(image != null)
               {
                  greenImagesList.add(image);
               }
            }
         }
      }
      else
      {
         throw new IOException("can not find code source for green images");
      }
      ApplicationImages.setGreenImages(greenImagesList);
   }
}
