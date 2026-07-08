package vokabeltrainer.table;

import java.awt.Component;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.ImageData;
import vokabeltrainer.common.colors.TableColors;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.types.Expression;

public class ExpressionCellRenderer2 
      implements TableCellRenderer
{
   private JPanel content;
   private JLabel infos;

   public ExpressionCellRenderer2()
   {
      content = new JPanel();
      TotemLayout layout = new TotemLayout(content, 15);
      content.setLayout(layout);
      infos = new JLabel(new ImageIcon(ApplicationImages.getIcon_bulb()));
      infos.setSize(60, 60);
      infos.setBorder(BorderFactory.createEmptyBorder());
      
      content.add(infos);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      Expression expression = (Expression) value;
      UUID uuid = expression.getUuid();
      if(ImageData.isImageForExpressionAvailable(uuid))
      {
         infos.setIcon(new ImageIcon(ApplicationImages.getIcon_bulb_on()));
      }
      else
      {
    	  infos.setIcon(new ImageIcon(ApplicationImages.getIcon_bulb()));
      }
      
      if (isSelected)
      {
         content.setBorder(BorderFactory.createLineBorder(ApplicationColors.getSelectionGreen(), 3));
      }
      else
      {
         content.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
      }

      if (row % 2 == 1)
      {
         content.setBackground(TableColors.getRow1());
      }
      else
      {
         content.setBackground(TableColors.getRow2());
      }
      
      return content;
   }

   
}
