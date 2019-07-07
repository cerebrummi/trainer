package vokabeltrainer.panels.trainer;

import java.awt.Graphics;

import javax.swing.JPanel;

import vokabeltrainer.ApplicationImages;

public class ImagePanelGreen extends JPanel
{
   private static final long serialVersionUID = 5685334885200762846L;

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      int x = this.getParent().getWidth() / 2 - 501 / 2;
      int y = this.getParent().getHeight() / 2 - 210 / 2;
      g.drawImage(ApplicationImages.getRandomGreenImage(), x, y, this);
   }

}
