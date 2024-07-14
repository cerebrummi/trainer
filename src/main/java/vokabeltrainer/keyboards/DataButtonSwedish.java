package vokabeltrainer.keyboards;

import java.awt.ComponentOrientation;
import vokabeltrainer.common.ApplicationFonts;

public class DataButtonSwedish extends DataButton
{
   private static final long serialVersionUID = 578075610960102010L;
   
   private String data;
   
   public DataButtonSwedish(String caption, String data)
   {
      super(caption, data, ComponentOrientation.LEFT_TO_RIGHT);
      this.data = data;
      this.setFont(ApplicationFonts.getButtonFont());
   }

   public String getData()
   {
      return data;
   }
}
