package vokabeltrainer.panels.list;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.types.DatabaseDescription;

public class DatabaseListCellRenderer extends DefaultListCellRenderer
{

   /**
    * 
    */
   private static final long serialVersionUID = -2525657515688753079L;

   private JLabel label;
   
   public DatabaseListCellRenderer()
   {
      label = new JLabel();
      label.setOpaque(true);
      label.setBackground(ApplicationColors.getLightBlue());
      label.setFont(ApplicationFonts.getInternationalFont(20.0F));
      label.setIcon(new ImageIcon(ApplicationImages.getLogoFolder()));
      label.setBorder(BorderFactory.createEmptyBorder());
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends Object> list,
         Object value, int index, boolean isSelected, boolean cellHasFocus)
   {
      if (value != null)
      {
         DatabaseDescription data = (DatabaseDescription) value;
         label
               .setText(data.getDatabaseName());
      }
      else
      {
         label.setText("");
      }

      
      return label;
   }
}
