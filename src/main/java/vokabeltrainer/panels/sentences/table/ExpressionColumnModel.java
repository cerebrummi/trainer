package vokabeltrainer.panels.sentences.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import vokabeltrainer.common.main.App;

import java.io.Serial;

public class ExpressionColumnModel extends DefaultTableColumnModel
{
   @Serial
   private static final long serialVersionUID = 2275832088905828369L;

   public ExpressionColumnModel(App app)
   {
      TableColumn column = new TableColumn();
      column.setHeaderRenderer(null);
      ExpressionCellRenderer rendererEditor = new ExpressionCellRenderer(app);
      column.setCellRenderer(rendererEditor);
      column.setCellEditor(rendererEditor);
      addColumn(column);
   }

}
