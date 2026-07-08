package vokabeltrainer.panels.list.table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.types.DatabaseDescription;

public class DatabaseTableCellRenderer implements TableCellRenderer, TableCellEditor
{

   private JLabel selected;
   private JLabel empty;
   private JLabel database;

   public DatabaseTableCellRenderer() // multiple selection possible, except unknown selection row 0
   {
      selected = new JLabel(new ImageIcon(ApplicationImages.getLogoFolder()));
      selected.setBackground(ApplicationColors.getTransparent());
      selected.setOpaque(false);
      
      empty = new JLabel();
      empty.setBackground(ApplicationColors.getTransparent());
      empty.setOpaque(false);

      database = new JLabel();
      database.setFont(ApplicationFonts.getGermanFont(14F));
      database.setBackground(ApplicationColors.getTransparent());
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
      DatabaseDescription databaseDescription = ((DatabaseTableRow) value).getDescription();
      
      if (column == 0)
      {
         if (databaseDescription.isSelected())
         {
            return selected;
         }
         else
         {
            return empty;
         }
      }

      this.database.setText(databaseDescription.getDatabaseName());
      return this.database;
   }

}
