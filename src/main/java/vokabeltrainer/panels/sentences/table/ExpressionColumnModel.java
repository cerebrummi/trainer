package vokabeltrainer.panels.sentences.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ExpressionColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = 2275832088905828369L;

   public ExpressionColumnModel()
   {
      TableColumn column = new TableColumn();
      column.setHeaderRenderer(null);
      ExpressionCellRenderer rendererEditor = new ExpressionCellRenderer();
      column.setCellRenderer(rendererEditor);
      column.setCellEditor(rendererEditor);
      addColumn(column);
   }

}
