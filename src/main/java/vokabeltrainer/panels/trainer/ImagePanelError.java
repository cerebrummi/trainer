package vokabeltrainer.panels.trainer;

import java.awt.Graphics;

import javax.swing.JPanel;

import vokabeltrainer.common.main.App;

public class ImagePanelError extends JPanel
{
   private static final long serialVersionUID = 6703471281100144482L;
   
   private App app;
   
   public ImagePanelError(App app)
   {
      this.app = app;
   }
   
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      int x = this.getParent().getWidth() / 2 - 501 / 2;
      int y = this.getParent().getHeight() / 2 - 210 / 2;
      g.drawImage(app.appImages.getErrorImage(), x, y, this);
   }

}
