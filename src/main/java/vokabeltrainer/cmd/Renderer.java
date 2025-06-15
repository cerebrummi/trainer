package vokabeltrainer.cmd;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import vokabeltrainer.common.ApplicationColors;

public class Renderer extends DefaultListCellRenderer
{
   /**
    * 
    */
   private static final long serialVersionUID = 2183657576759647252L;

   @Override
   public void setOpaque(boolean makeBackGroundVisible)
   {
      super.setOpaque(true); // THIS DOES THE TRICK
   }

   @Override
   public Component getListCellRendererComponent(JList<?> list, Object value,
         int index, boolean isSelected, boolean cellHasFocus)
   {
      setText((String) value);
      setBackground(ApplicationColors.getLightYellow());
      return this;
   }
}