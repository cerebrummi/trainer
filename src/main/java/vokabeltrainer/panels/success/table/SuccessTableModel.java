package vokabeltrainer.panels.success.table;

import java.awt.Component;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class SuccessTableModel extends DefaultTableModel
{
   private static final long serialVersionUID = 5839657636851611176L;

   @Override
   public Class<?> getColumnClass(int columnIndex)
   {
      Class<?> clazz = String.class;
      switch (columnIndex)
      {
      case 0:
         clazz = Boolean.class;
         break;
      case 1:
         clazz = Component.class;
         break;
      }
      return clazz;
   }

   @Override
   public boolean isCellEditable(int row, int column)
   {
      return column == 0;
   }

   @Override
   public void setValueAt(Object value, int row, int column)
   {
      if (value instanceof Boolean && column == 0)
      {
         @SuppressWarnings("unchecked")
         Vector<SuccessTableRow> rowData = (Vector<SuccessTableRow>) getDataVector().get(row);
         SuccessTableRow rowTable = rowData.firstElement();
         rowTable.getExpression().setSelected((Boolean)value);
         fireTableCellUpdated(row, column);
      }
   }
}
