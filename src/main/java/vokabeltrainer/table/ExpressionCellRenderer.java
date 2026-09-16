package vokabeltrainer.table;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.table.list.ExpressionList;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;

public class ExpressionCellRenderer implements TableCellRenderer
{
   private ExpressionList list;
   private Direction language;
   private App app;
   private Common common;

   public ExpressionCellRenderer(App app, Common common, Direction language)
   {
      this.app = app;
      this.common = common;
      this.language = language;
      list = new ExpressionList(app, language);
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
            list.setListData(expression.toSwedishArrayForTableEntry2(app, common));
         }
         else
         {
            list.setListData(expression.toHebrewArrayForTableEntry2(app, common));
         }
      }
      else
      {
         if (expression.getLL().isSwedish() || expression.getLL().isGerman())
         {
            list.setListData(expression.toSwedishArrayForTableEntry(app, common));
         }
         else
         {
            list.setListData(expression.toHebrewArrayForTableEntry(app, common));
         }
      }

      if (isSelected)
      {
         list.setBorder(BorderFactory
               .createLineBorder(app.appColors.getSelectionGreen(), 3));
      }
      else
      {
         list.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
      }

      if (row % 2 == 1)
      {
         list.setBackground(app.appColors.table.getRow1());
      }
      else
      {
         list.setBackground(app.appColors.table.getRow2());
      }

      list.setLock(expression.isDoNotChange());
      list.setWithEye(expression.isVisible());

      return list;
   }
}
