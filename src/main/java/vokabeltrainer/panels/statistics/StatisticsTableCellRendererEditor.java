package vokabeltrainer.panels.statistics;

import java.awt.Component;
import java.awt.Font;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.Main;

public class StatisticsTableCellRendererEditor implements TableCellRenderer, TableCellEditor
{
   private JLabel date;
   private JLabel expressionsDtoH;
   private JLabel expressionsHtoD;
   private StatisticsTableRow valueEditor;

   public StatisticsTableCellRendererEditor()
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
      if(value instanceof StatisticsTableRow)
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

      }
      
      return null;
   }

   @Override
   public Object getCellEditorValue()
   {
      return this.valueEditor;
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
      return true;
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
      if(value instanceof StatisticsTableRow)
      {
         valueEditor = (StatisticsTableRow) value;
         
         if (column == 0)
         {
            date.setText(valueEditor.getDate());
            return date;
         }

         if (column == 1)
         {
            expressionsHtoD
                  .setText(String.valueOf(valueEditor.getExpressionsHtoDSize()));
            return expressionsHtoD;
         }

         if (column == 2)
         {
            expressionsDtoH
                  .setText(String.valueOf(valueEditor.getExpressionsDtoHSize()));
            return expressionsDtoH;
         }
      }
      return null;
   }

}
