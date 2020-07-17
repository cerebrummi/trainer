package vokabeltrainer.panels.statistics;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.Main;

public class StatisticsTableCellRenderer implements TableCellRenderer
{
   private JLabel date;
   private JLabel expressionsDtoH;
   private JLabel expressionsHtoD;

   public StatisticsTableCellRenderer()
   {
      Font font = Main.getGermanFont(20F);
      date = new JLabel();
      date.setFont(font);
      expressionsDtoH = new JLabel();
      expressionsDtoH.setFont(font);
      expressionsHtoD = new JLabel();
      expressionsHtoD.setFont(font);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      StatisticsTableRow renderedRow = (StatisticsTableRow) value;

      if (column == 0)
      {
         date.setText(renderedRow.getDate());
         return date;
      }

      if (column == 1)
      {
         expressionsHtoD
               .setText(String.valueOf(renderedRow.getExpressionsHtoDSize()));
         return expressionsHtoD;
      }

      if (column == 2)
      {
         expressionsDtoH
               .setText(String.valueOf(renderedRow.getExpressionsDtoHSize()));
         return expressionsDtoH;
      }

      if (column == 3)
      {
         return renderedRow.getTakeOutButton();
      }
      return null;
   }

}
