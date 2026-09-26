package vokabeltrainer.table.list.editor.expressionkindtable.singleselect;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import vokabeltrainer.common.main.App;

import java.io.Serial;

public class ExpressionKindTableColumnModel2 extends DefaultTableColumnModel
{
   @Serial
   private static final long serialVersionUID = -2011627608532876165L;

   private ExpressionKindTableCellRenderer2 renderer2;

   public ExpressionKindTableColumnModel2(App app, int totalWidth)
   {
      renderer2 = new ExpressionKindTableCellRenderer2(app);

      TableColumn column = new TableColumn();
      column.setHeaderValue("Wahl");
      column.setCellRenderer(renderer2);
      column.setCellEditor(renderer2);
      column.setPreferredWidth(43);
      column.setWidth(43);
      addColumn(column);

      TableColumn column1 = new TableColumn();
      column1.setHeaderValue("Wortart");
      column1.setCellRenderer(renderer2);
      column1.setCellEditor(renderer2);
      column1.setPreferredWidth(totalWidth - 43);
      addColumn(column1);
   }
}
