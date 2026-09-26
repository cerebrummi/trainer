package vokabeltrainer;

import java.awt.Graphics;
import java.io.Serial;

import javax.swing.JPanel;

import vokabeltrainer.common.main.App;

public class TrashCanBackgroundPanel extends JPanel
{
   @Serial
   private static final long serialVersionUID = -7918923007938243168L;
   
   private App app;
   
   public TrashCanBackgroundPanel(App app)
   {
      this.app = app;
   }
   
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (app.appImages.getTrashcanBackground() != null)
      {
         int x = this.getParent().getWidth() / 2 - 1000 / 2;
         int y = this.getParent().getHeight() / 2 - 620 / 2;
         g.drawImage(app.appImages.getTrashcanBackground(), x, y, this);
      }
   }
}
