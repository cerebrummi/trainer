package vokabeltrainer.table;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.colors.TableColors;
import vokabeltrainer.table.list.ExpressionList;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;

public class ExpressionCellRenderer implements TableCellRenderer
{
   private ExpressionList list;
   private Direction language;

   public ExpressionCellRenderer(Direction language)
   {
      this.language = language;
      list = new ExpressionList(language);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      Expression expression = (Expression) value;
      if (Direction.OWN_TO_NEW.equals(language))
      {
         if (expression.getLL().isSwedish() || expression.getLL().isGerman())
         {
            list.setListData(expression.toSwedishArrayForTableEntry2());
         }
         else
         {
            list.setListData(expression.toHebrewArrayForTableEntry2());
         }
      }
      else
      {
         if (expression.getLL().isSwedish() || expression.getLL().isGerman())
         {
            list.setListData(expression.toSwedishArrayForTableEntry());
         }
         else
         {
            list.setListData(expression.toHebrewArrayForTableEntry());
         }
      }

      if (isSelected)
      {
         list.setBorder(BorderFactory
               .createLineBorder(ApplicationColors.brightGreen, 3));
      }
      else
      {
         list.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
      }

      if (row % 2 == 1)
      {
         list.setBackground(TableColors.getRow1());
      }
      else
      {
         list.setBackground(TableColors.getRow2());
      }

      list.setLock(expression.isDoNotChange());
      list.setWithEye(expression.isVisible());

      return list;
   }
}
