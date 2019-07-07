package vokabeltrainer.panels.list;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;

public class StringListCellRenderer extends DefaultListCellRenderer
{
   private static final long serialVersionUID = 6061565751599958392L;

   private JLabel label;

   public StringListCellRenderer()
   {
      label = new JLabel();
      label.setBackground(Settings.getLightGrayGold());
      label.setFont(Main.getGermanFont(16F));
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends Object> list,
         Object value, int index, boolean isSelected, boolean cellHasFocus)
   {
      label.setText((String) value);
      label.setBorder(BorderFactory.createEmptyBorder());
      if(list.getSelectedIndex() == index)
      {        
         label.setOpaque(true);
      }
      else
      {
         label.setOpaque(false);
      }
      return label;
   }
}
