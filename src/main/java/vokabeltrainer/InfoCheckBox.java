package vokabeltrainer;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import vokabeltrainer.common.ApplicationColors;

public class InfoCheckBox extends JCheckBox
{
   private static final long serialVersionUID = 7704010484130304775L;
   
   public InfoCheckBox(String borderTitle)
   {
      setOpaque(false);
      setBackground(ApplicationColors.getTransparent());
      this.setBorder(BorderFactory.createTitledBorder(borderTitle));
      this.setBorderPainted(true);
   }
}
