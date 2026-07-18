package vokabeltrainer;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import vokabeltrainer.common.main.App;

public class InfoCheckBox extends JCheckBox
{
   private static final long serialVersionUID = 7704010484130304775L;

   public InfoCheckBox(App app)
   {
      setOpaque(false);
      setBackground(app.appColors.getTransparent());
      this.setBorderPainted(true);
   }

   public InfoCheckBox(App app, String borderTitle)
   {
      this(app);
      this.setBorder(BorderFactory.createTitledBorder(borderTitle));
   }

}
