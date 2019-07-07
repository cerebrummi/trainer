package vokabeltrainer.table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.types.Expression;

public class SelectionCellRendererEditor
      implements TableCellRenderer, TableCellEditor
{
   JCheckBox box;
   JCheckBox renderBox;
   Expression expression;

   public SelectionCellRendererEditor()
   {
      box = new JCheckBox();
      renderBox = new JCheckBox();
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      Expression expression = (Expression) value;
      renderBox.setSelected(expression.isSelected());
      return renderBox;
   }

   @Override
   public Object getCellEditorValue()
   {
      expression.setSelected(box.isSelected());
      return expression;
   }

   @Override
   public boolean isCellEditable(EventObject anEvent)
   {
      return true;
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
      expression = (Expression) value;
      box.setSelected(expression.isSelected());
      return box;
   }

}
