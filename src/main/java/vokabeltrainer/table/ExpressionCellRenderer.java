package vokabeltrainer.table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.Settings;
import vokabeltrainer.table.list.ExpressionList;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class ExpressionCellRenderer
      implements TableCellRenderer, TableCellEditor
{
   Expression expression;
   ExpressionList list;
   Language language;

   public ExpressionCellRenderer(Language language)
   {
      this.language = language;
      list = new ExpressionList(language);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {

      if (row % 2 == 1)
      {
         list.setBackground(Settings.getLightBlue());
      }
      else
      {
         list.setBackground(Settings.getVeryLightGold());
      }
      Expression expression = (Expression) value;
      if (Language.GERMAN.equals(language))
      {
         list.setListData(expression.toGermanArray());
      }
      else
      {
         list.setListData(expression.toHebrewArray());
      }

      if (isSelected)
      {
         list.setBorder(BorderFactory.createLineBorder(Settings.getGreen(), 3));
      }
      else
      {
         list.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
      }

      return list;
   }

   @Override
   public Object getCellEditorValue()
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean isCellEditable(EventObject anEvent)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean shouldSelectCell(EventObject anEvent)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean stopCellEditing()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void cancelCellEditing()
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void addCellEditorListener(CellEditorListener l)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void removeCellEditorListener(CellEditorListener l)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public Component getTableCellEditorComponent(JTable table, Object value,
         boolean isSelected, int row, int column)
   {
      expression = (Expression) value;
      return null;
   }
}
