package vokabeltrainer.panels.statistics;

import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.main.App;

public class StatisticsTableCellRenderer
      implements TableCellRenderer, TableCellEditor
{
   private JLabel date;
   private JLabel expressionsDtoH;
   private JLabel expressionsHtoD;
   private App app;

   public StatisticsTableCellRenderer(App app)
   {
      this.app = app;
      Font font = app.appFonts.germanFont.deriveFont(20F);
      date = new JLabel();
      date.setFont(font);
      date.setOpaque(true);
      expressionsDtoH = new JLabel();
      expressionsDtoH.setFont(font);
      expressionsDtoH.setOpaque(true);
      expressionsDtoH.setHorizontalAlignment(SwingConstants.CENTER);
      expressionsHtoD = new JLabel();
      expressionsHtoD.setFont(font);
      expressionsHtoD.setOpaque(true);
      expressionsHtoD.setHorizontalAlignment(SwingConstants.CENTER);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {

      StatisticsTableRow renderedRow = (StatisticsTableRow) value;
      LocalDate now = LocalDate.now();

      if (column == 0)
      {
         date.setText(renderedRow.getDate());
         if (now.isBefore(renderedRow.getLocalDate()))
         {
            date.setBackground(app.appColors.statistics.getFuture());
         }
         else if (now.isAfter(renderedRow.getLocalDate()))
         {
            date.setBackground(app.appColors.statistics.getToLate());
         }
         else
         {
            date.setBackground(app.appColors.statistics.getToday());
         }
         return date;
      }

      if (column == 1)
      {
         if (isSelected)
         {
            expressionsHtoD
                  .setBackground(app.appColors.statistics.getSelectedBackground());
            expressionsHtoD.setForeground(app.appColors.statistics.getTextForeground());
         }
         else
         {
            expressionsHtoD
                  .setBackground(app.appColors.statistics.getTableCellBackground());
            expressionsHtoD
                  .setForeground(app.appColors.statistics.getTextForegroundInvers());
         }
         expressionsHtoD
               .setText(String.valueOf(renderedRow.getExpressionsHtoDSize()));
         return expressionsHtoD;
      }

      // column == 2
      if (isSelected)
      {
         expressionsDtoH
               .setBackground(app.appColors.statistics.getSelectedBackground());
         expressionsDtoH.setForeground(app.appColors.statistics.getTextForeground());
      }
      else
      {
         expressionsDtoH
               .setBackground(app.appColors.statistics.getTableCellBackground());
         expressionsDtoH
               .setForeground(app.appColors.statistics.getTextForegroundInvers());
      }
      expressionsDtoH
            .setText(String.valueOf(renderedRow.getExpressionsDtoHSize()));
      return expressionsDtoH;

   }

   @Override
   public Object getCellEditorValue()
   {
      return null;
   }

   @Override
   public boolean isCellEditable(EventObject anEvent)
   {
      return false;
   }

   @Override
   public boolean shouldSelectCell(EventObject anEvent)
   {
      return false;
   }

   @Override
   public boolean stopCellEditing()
   {
      return false;
   }

   @Override
   public void cancelCellEditing()
   {

   }

   @Override
   public void addCellEditorListener(CellEditorListener l)
   {

   }

   @Override
   public void removeCellEditorListener(CellEditorListener l)
   {

   }

   @Override
   public Component getTableCellEditorComponent(JTable table, Object value,
         boolean isSelected, int row, int column)
   {
      return null;
   }

}
