package vokabeltrainer.panels.trainer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serial;

import javax.swing.JPanel;

import vokabeltrainer.common.main.App;

public class ImagePanelGreen extends JPanel
{
   @Serial
   private static final long serialVersionUID = 5685334885200762846L;
   private static BufferedImage currentImage;

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      int x = this.getParent().getWidth() / 2 - 501 / 2;
      int y = this.getParent().getHeight() / 2 - 210 / 2;
      g.drawImage(currentImage, x, y, this);
   }

   public static void setNextImage(App app)
   {
      currentImage = app.appImages.getRandomGreenImage();
   }
}
