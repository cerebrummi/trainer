package vokabeltrainer;

import java.awt.ComponentOrientation;

import javax.swing.JButton;

public class DataButton extends JButton
{
   private static final long serialVersionUID = 578075610960102010L;
   
   private String data;

   public DataButton(String caption, String data)
   {
      super(caption);
      this.data = data;
      this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
   }

   public String getData()
   {
      return data;
   }
}
