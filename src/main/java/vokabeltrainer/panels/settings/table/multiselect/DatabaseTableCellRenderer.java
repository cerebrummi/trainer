package vokabeltrainer.panels.settings.table.multiselect;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.types.DatabaseItem;

public class DatabaseTableCellRenderer implements TableCellRenderer, TableCellEditor
{
   private JLabel selected;
   private JLabel empty;
   private JLabel database;

   public DatabaseTableCellRenderer() // multiple selection possible, except no selection row 0
   {
      selected = new JLabel(new ImageIcon(ApplicationImages.getSelectDone()));
      selected.setBackground(Settings.getTransparent());
      selected.setOpaque(false);
      
      empty = new JLabel();
      empty.setBackground(Settings.getTransparent());
      empty.setOpaque(false);

      database = new JLabel();
      database.setFont(Main.getGermanFont(14F));
      database.setBackground(Settings.getTransparent());
      database.setOpaque(false);
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

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      DatabaseItem databaseItem = ((DatabaseTableRow) value).getDatabaseItem();
      
      if(row == 0 && databaseItem.isSelected())
      {
         for (int i = 1; i < table.getRowCount(); i++)
         {
            DatabaseTableRow rowValue = (DatabaseTableRow) table.getValueAt(i, 1);
            rowValue.getDatabaseItem().setSelected(false);
            table.setValueAt(rowValue, i, 1);
         }
      }
      else if(row >  0 && databaseItem.isSelected())
      {
         DatabaseTableRow rowValue = (DatabaseTableRow) table.getValueAt(0, 1);
         rowValue.getDatabaseItem().setSelected(false);
         table.setValueAt(rowValue, 0, 1);
      }
      
      if (column == 0)
      {
         if (databaseItem.isSelected())
         {
            return selected;
         }
         else
         {
            return empty;
         }
      }

      this.database.setText(databaseItem.getDatabase().getName());
      return this.database;
   }

}
