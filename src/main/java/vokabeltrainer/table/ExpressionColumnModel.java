package vokabeltrainer.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.types.Direction;

public class ExpressionColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = 2275832088905828369L;

   public ExpressionColumnModel(Common common, Direction language)
   {
      TableColumn column = new TableColumn();
      column.setHeaderRenderer(null);
      ExpressionCellRenderer rendererEditor = new ExpressionCellRenderer(common,
            language);
      column.setCellRenderer(rendererEditor);
      column.setMinWidth(column.getWidth() - 60);
      addColumn(column);

      TableColumn column2 = new TableColumn();
      column2.setHeaderRenderer(null);
      ExpressionCellRenderer2 rendererEditor2 = new ExpressionCellRenderer2();
      column2.setCellRenderer(rendererEditor2);
      column2.setMaxWidth(60);
      addColumn(column2);
   }

}
