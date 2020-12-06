package vokabeltrainer.panels.settings.table.multiselect;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class DatabaseTableColumnModel extends DefaultTableColumnModel
{

   private static final long serialVersionUID = -126413736438939824L;

private DatabaseTableCellRenderer renderer;
   
   public DatabaseTableColumnModel(int totalWidth)
   {
      renderer = new DatabaseTableCellRenderer();
      
      TableColumn column = new TableColumn();
      column.setHeaderValue("Wahl");
      column.setCellRenderer(renderer);
      column.setCellEditor(renderer);
      column.setPreferredWidth(43);
      addColumn(column);
      
      TableColumn column1 = new TableColumn();
      column1.setHeaderValue("Datenbank");
      column1.setCellRenderer(renderer);
      column1.setCellEditor(renderer);
      column1.setPreferredWidth(totalWidth-43);
      addColumn(column1);
   }

}
