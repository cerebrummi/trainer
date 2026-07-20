package vokabeltrainer.keyboards;

import java.awt.ComponentOrientation;

import vokabeltrainer.common.main.App;

public class DataButtonSwedish extends DataButton
{
   private static final long serialVersionUID = 578075610960102010L;

   private String data;

   public DataButtonSwedish(App app, String caption, String data)
   {
      super(app, caption, data, ComponentOrientation.LEFT_TO_RIGHT);
      this.data = data;
      this.setFont(app.appFonts.buttonFont);
   }

   public String getData()
   {
      return data;
   }
}
