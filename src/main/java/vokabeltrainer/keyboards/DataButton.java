package vokabeltrainer.keyboards;

import java.awt.ComponentOrientation;
import java.awt.Image;
import java.io.Serial;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import vokabeltrainer.common.main.App;

public class DataButton extends JButton
{
   @Serial
   private static final long serialVersionUID = 578075610960102010L;

   private String data;

   public DataButton(Image caption, String data)
   {
      super(new ImageIcon(caption));
      this.data = data;
   }

   public DataButton(App app, String caption, String data)
   {
      super(caption);
      this.data = data;
      this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      this.setFont(app.appFonts.hebrewFont.deriveFont(30F));
   }

   public DataButton(App app, String caption, String data,
         ComponentOrientation orientation)
   {
      super(caption);
      this.data = data;
      this.setComponentOrientation(orientation);
      this.setFont(app.appFonts.hebrewFont.deriveFont(30F));
   }

   public String getData()
   {
      return data;
   }
}
