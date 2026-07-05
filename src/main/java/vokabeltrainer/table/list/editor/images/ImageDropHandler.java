package vokabeltrainer.table.list.editor.images;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

public class ImageDropHandler extends TransferHandler
{
   private static final long serialVersionUID = 4233594459693020422L;

   UUID expressionUUID;
   DefaultListModel<ImageItem> model;
   
   public ImageDropHandler(UUID expressionUUID, DefaultListModel<ImageItem> model)
   {
      this.expressionUUID = expressionUUID;
      this.model = model;
   }

   @Override
   public boolean canImport(TransferSupport support)
   {
      return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
   }

   @Override
   public boolean importData(TransferSupport support)
   {
      try
      {
         @SuppressWarnings("unchecked")
         List<File> files = (List<File>) support.getTransferable()
               .getTransferData(DataFlavor.javaFileListFlavor);

         for (File file : files)
         {
            addImage(file);
         }

         return true;

      }
      catch (Exception ex)
      {
         return false;
      }
   }

   void addImage(File file)
   {
      try
      {
         BufferedImage image = ImageIO.read(file);

         if (image == null)
         {
            JOptionPane.showMessageDialog(null, this, 
                  "Keine lesbare Bilddatei.", 1);
            return;
         }

         ImageIcon thumbnail = createThumbnail(image, 180, 180);

         ImageItem item = new ImageItem(expressionUUID, file.getName(),
               file.toPath(), thumbnail, image);

         model.addElement(item);

      }
      catch (Exception ex)
      {
         JOptionPane.showMessageDialog(null, this, 
               "Bild konnte nicht geladen werden:\n" + ex.getMessage(),1);
      }
   }

   public static ImageIcon createThumbnail(BufferedImage image, int maxWidth,
         int maxHeight)
   {
      double scale = Math.min((double) maxWidth / image.getWidth(),
            (double) maxHeight / image.getHeight());

      int width = (int) (image.getWidth() * scale);
      int height = (int) (image.getHeight() * scale);

      Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      return new ImageIcon(scaled);
   }
}