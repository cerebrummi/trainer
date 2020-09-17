package vokabeltrainer.table.list.editor.grammartable;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;


public class GrammarTableColumnModel extends DefaultTableColumnModel
{

   private static final long serialVersionUID = -5977419104447912009L;
   
   private GrammarTableCellRenderer renderer;
   
   public GrammarTableColumnModel(int totalWidth)
   {
      renderer = new GrammarTableCellRenderer();
      
      TableColumn column = new TableColumn();
      column.setHeaderValue("ausgewählt");
      column.setCellRenderer(renderer);
      column.setCellEditor(renderer);
      column.setPreferredWidth(37);
      addColumn(column);
      
      TableColumn column1 = new TableColumn();
      column1.setHeaderValue("Wortart");
      column1.setCellRenderer(renderer);
      column1.setCellEditor(renderer);
      column1.setPreferredWidth(totalWidth-37);
      addColumn(column1);
   }

}
