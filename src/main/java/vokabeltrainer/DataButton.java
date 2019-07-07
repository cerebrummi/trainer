package vokabeltrainer;

import javax.swing.JButton;

public class DataButton extends JButton
{
   private static final long serialVersionUID = 578075610960102010L;
   
   private String data;

   public DataButton(String caption, String data)
   {
      super(caption);
      this.data = data;
   }

   public String getData()
   {
      return data;
   }

}
