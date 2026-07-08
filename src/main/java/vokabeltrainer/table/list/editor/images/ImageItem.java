package vokabeltrainer.table.list.editor.images;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.UUID;

import javax.swing.ImageIcon;

public class ImageItem
{
   private final UUID expressionUuid;
   private final String originalFileName;
   private final Path imagePath;
   private ImageIcon thumbnail;
   private boolean checked;
   private BufferedImage image;

   public ImageItem(UUID expressionUuid, String originalFileName,
         Path imagePath, ImageIcon thumbnail, BufferedImage image)
   {
      this.expressionUuid = expressionUuid;
      this.originalFileName = originalFileName;
      this.imagePath = imagePath;
      this.thumbnail = thumbnail;
      this.image = image;
   }

   public ImageItem(UUID expressionUuid, Path file, BufferedImage image)
   {
      this(expressionUuid, file.getFileName().toString(), file,
            ImageDropHandler.createThumbnail(image), image);
      this.checked = true;
   }

   public UUID getExpressionUuid()
   {
      return expressionUuid;
   }

   public String getImageFileName()
   {
      return originalFileName;
   }

   public Path getImagePath()
   {
      return imagePath;
   }

   public ImageIcon getThumbnail()
   {
      return thumbnail;
   }

   public boolean isChecked()
   {
      return checked;
   }

   public void setChecked(boolean checked)
   {
      this.checked = checked;
   }

   public void toggle()
   {
      this.checked = !this.checked;
   }

   public BufferedImage getImage()
   {
      return image;
   }

   public void setImage(BufferedImage image)
   {
      this.image = image;
      this.thumbnail = ImageDropHandler.createThumbnail(image);
   }

   @Override
   public String toString()
   {
      return originalFileName;
   }
}