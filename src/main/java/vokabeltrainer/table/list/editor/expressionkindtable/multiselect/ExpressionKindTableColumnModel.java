package vokabeltrainer.table.list.editor.expressionkindtable.multiselect;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import vokabeltrainer.common.main.App;

import java.io.Serial;

public class ExpressionKindTableColumnModel extends DefaultTableColumnModel
{
   @Serial
   private static final long serialVersionUID = -2011627608532876165L;

   private ExpressionKindTableCellRenderer renderer;

   public ExpressionKindTableColumnModel(App app, int totalWidth)
   {
      renderer = new ExpressionKindTableCellRenderer(app);

      TableColumn column = new TableColumn();
      column.setHeaderValue("Wahl");
      column.setCellRenderer(renderer);
      column.setCellEditor(renderer);
      column.setPreferredWidth(43);
      addColumn(column);

      TableColumn column1 = new TableColumn();
      column1.setHeaderValue("Wortart");
      column1.setCellRenderer(renderer);
      column1.setCellEditor(renderer);
      column1.setPreferredWidth(totalWidth - 43);
      addColumn(column1);
   }
}
