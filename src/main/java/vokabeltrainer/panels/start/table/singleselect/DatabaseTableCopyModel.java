package vokabeltrainer.panels.start.table.singleselect;

import java.io.Serial;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DatabaseTableCopyModel extends DefaultTableModel
{
   @Serial
   private static final long serialVersionUID = 5442352055546967989L;

   private final Vector<Vector<DatabaseTableCopyRow>> data;

   public DatabaseTableCopyModel(Vector<Vector<DatabaseTableCopyRow>> data,
         Vector<String> columnNames)
   {
      super(data, columnNames);
      this.data = data;
   }

   public Vector<Vector<DatabaseTableCopyRow>> getData()
   {
      return data;
   }
}
