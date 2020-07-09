package vokabeltrainer.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

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
      CodeSource src = Blue.class.getProtectionDomain().getCodeSource();
      if (src != null)
      {
         URL jar = src.getLocation();
         ZipFile zipFile = new ZipFile(jar.getFile());
         ZipInputStream zip = new ZipInputStream(jar.openStream());
         while (true)
         {
            ZipEntry ze = zip.getNextEntry();
            if (ze == null)
               break;
            String name = ze.getName();
            if (name.startsWith("vokabeltrainer/resources/gruen/"))
            {
               greenImagesList.add(ImageIO.read(zipFile.getInputStream(ze)));
            }
         }
      }
      else
      {
         throw new IOException("can not find code source for blue images");
      }
      ApplicationImages.setGreenImages(greenImagesList);
   }
}
