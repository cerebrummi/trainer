package vokabeltrainer.common.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.prefs.Preferences;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import vokabeltrainer.common.CerebrummiNodes;
import vokabeltrainer.common.Settings;
import vokabeltrainer.table.list.editor.images.ImageItem;

public final class ImageData
{

   private static final AtomicBoolean databaseInUse = new AtomicBoolean(false);
   private static volatile UUID uuidDataBaseLock;
   private static ImageDataBase database;

   private ImageData()
   {
      // nothing
   }

   static void initImageDataBase(Common common, View view)
   {
      database = new ImageDataBase(common, view);
   }

   static boolean lockDataBase(UUID uuid)
   {
      if (databaseInUse.get())
      {
         return false;
      }

      uuidDataBaseLock = uuid;
      databaseInUse.set(true);
      return true;
   }

   static boolean unlockDataBase(UUID uuid)
   {
      if (uuidDataBaseLock.equals(uuid))
      {
         databaseInUse.set(false);
         return true;
      }
      return false;
   }

   private static void checkDataBaseInUseAndWait()
   {
      while (databaseInUse.get())
      {
         try
         {
            Thread.sleep(100);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
   }

   private static ImageDataBase getDataBaseAtomic()
   {
      checkDataBaseInUseAndWait();
      return database;
   }

   public static boolean isImageForExpressionAvailable(UUID uuid)
   {
      if (uuid == null)
      {
         return false;
      }
      return getDataBaseAtomic().isImageForExpressionAvailable(uuid);
   }

   public static void saveImage(Common common, View view, BufferedImage image, UUID uuid,
         String imageName)
   {
      if (uuid == null)
      {
         return;
      }
      getDataBaseAtomic().saveImage(common, view, image, uuid, imageName);
   }

   public static ArrayList<ImageItem> loadImages(UUID uuid)
   {
      if (uuid == null)
      {
         return null;
      }
      return getDataBaseAtomic().loadImages(uuid);
   }

   public static void deleteImage(UUID uuid, String imageName)
   {
      if (uuid == null)
      {
         return;
      }
      getDataBaseAtomic().deleteImage(uuid, imageName);
   }

   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // ################### ImageDataBase #######################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################

   private static class ImageDataBase
   {
      private final ConcurrentMap<UUID, ArrayList<String>> imageNameMap = new ConcurrentHashMap<>(
            findNumberOfAllVocabulary() + 100);

      ImageDataBase(Common common, View view)
      {
         if (!checkDirectory(common, view))
         {
            return;
         }
         moveImagesFromPreviousVersion(common, view);
         readImagesAvailable();
      }

      private void readImagesAvailable()
      {
         try (Stream<Path> s = Files.list(Paths.get(Settings.getImagePath())))
         {
            s.filter(Files::isDirectory).forEach(dirPath -> {
               try (Stream<Path> walk = Files.walk(dirPath))
               {
                  walk.filter(Files::isRegularFile).forEach(
                        filePath -> addToImageNameMap(filePath, dirPath));
               }
               catch (IOException e)
               {
                  // nothing
               }
            });
         }
         catch (IOException e)
         {
            // nothing
         }
      }

      private void moveImagesFromPreviousVersion(Common common, View view)
      {
         try (Stream<Path> s = Files.walk(Paths.get(Settings.getImagePath())))
         {
            s.filter(Files::isRegularFile).forEach(filePath -> {

               String fileName = filePath.getFileName().toString();
               UUID uuid = getUuidFromOldImageFile(fileName);

               if (Data.isExistUuid(uuid))
               {
                  checkDirectory(common, view, uuid);

                  try
                  {
                     Files.move(
                           Paths.get(Settings.getImagePath(), File.separator,
                                 fileName),
                           Paths.get(Settings.getImagePath(), File.separator,
                                 uuid.toString(), File.separator,
                                 "ex_" + fileName),
                           StandardCopyOption.REPLACE_EXISTING);
                     addToImageNameMap(fileName, uuid);
                  }
                  catch (Exception e)
                  {
                     // nothing
                  }
               }
            });
         }
         catch (Exception e)
         {
            // nothing
         }
      }

      private void addToImageNameMap(Path file, Path uuidPath)
      {
         String fileName = file.getFileName().toString();

         if (!checkImageType(fileName))
         {
            return;
         }

         UUID uuid = UUID.fromString(uuidPath.getFileName().toString());

         addToImageNameMap(fileName, uuid);
      }

      private void addToImageNameMap(String fileName, UUID uuid)
      {
         if (imageNameMap.containsKey(uuid) && imageNameMap.get(uuid) != null)
         {
            imageNameMap.get(uuid).add(fileName);
         }
         else
         {
            ArrayList<String> listNames = new ArrayList<>();
            listNames.add(fileName);
            imageNameMap.put(uuid, listNames);
         }
      }

      private boolean checkImageType(String image)
      {
         image = image.toLowerCase();
         int length = image.length();
         char dot3 = image.charAt(length - 3);
         char dot4 = image.charAt(length - 4);
         if (dot3 == '.' || dot4 == '.')
         {
            return true;
         }
         return false;
      }

      private UUID getUuidFromOldImageFile(String image)
      {
         return UUID.fromString(image.substring(0, image.lastIndexOf('.')));
      }

      // #########################################################

      private ArrayList<ImageItem> loadImages(UUID uuid)
      {
         ArrayList<ImageItem> imageList = new ArrayList<>();
         ArrayList<String> nameList = imageNameMap.get(uuid);

         if (nameList == null)
         {
            return imageList;
         }

         try (Stream<Path> s = Files.walk(
               Paths.get(Settings.getImagePath() + File.separator + uuid)))
         {
            s.filter(Files::isRegularFile).forEach(file -> {
               ImageItem item = loadImageOriginal(file, uuid);
               if (item != null)
               {
                  imageList.add(item);
               }
            });
         }
         catch (Exception e)
         {
            // nothing
         }

         return imageList;
      }

      private ImageItem loadImageOriginal(Path file, UUID uuid)
      {
         try (FileInputStream in = new FileInputStream(file.toFile()))
         {
            BufferedImage image = ImageIO.read(in);

            if (image == null)
            {
               return null;
            }

            return new ImageItem(uuid, file, image);
         }
         catch (Exception e)
         {
            // nothing
         }
         return null;
      }

      private void deleteImage(UUID uuid, String imageFile)
      {
         try
         {
            Files.deleteIfExists(
                  Paths.get(Settings.getImagePath() + File.separator
                        + uuid.toString() + File.separator + imageFile));
            imageNameMap.get(uuid).remove(imageFile);

         }
         catch (Exception e)
         {
            // nothing
            e.printStackTrace();
         }
      }

      private boolean isImageForExpressionAvailable(UUID uuid)
      {
         return imageNameMap.containsKey(uuid)
               && !imageNameMap.get(uuid).isEmpty() ? true : false;
      }

      private void saveImage(Common common, View view, BufferedImage image, UUID uuid, String imageName)
      {
         if (!checkDirectory(common, view))
         {
            return;
         }

         if (!checkDirectory(common, view, uuid))
         {
            return;
         }

         try
         {
            Path target = Path
                  .of(Settings.getImagePath(), File.separator, uuid.toString())
                  .resolve(imageName);

            String format = getFormat(imageName);

            boolean success = ImageIO.write(image, format, target.toFile());
            if (success)
            {
               addToImageNameMap(imageName, uuid);
            }
         }
         catch (IOException e)
         {
            // nothing
         }
      }

      private static String getFormat(String fileName)
      {
         int dot = fileName.lastIndexOf('.');

         if (dot < 0 || dot == fileName.length() - 1)
         {
            return "png"; // Standard
         }

         return fileName.substring(dot + 1).toLowerCase();
      }

      private boolean checkDirectory(Common common, View view)
      {
         File customDir = new File(Settings.getImagePath());
         if (!customDir.exists())
         {
            if (!common.getDirectoryHelper().makeDirectory(common, view, customDir))
            {
               return false;
            }
         }
         return true;
      }

      private boolean checkDirectory(Common common, View view, UUID uuid)
      {
         File customDir = new File(
               Settings.getImagePath() + File.separator + uuid);

         if (!customDir.exists())
         {
            if (!common.getDirectoryHelper().makeDirectory(common, view, customDir))
            {
               return false;
            }
         }
         return true;
      }

      private int findNumberOfAllVocabulary()
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         int numberOfVocabulary = preferences
               .getInt(CerebrummiNodes.getExpressionNode(), 0);
         if (numberOfVocabulary > 30000)
         {
            numberOfVocabulary = 30000;
         }
         return numberOfVocabulary;
      }
   }
}
