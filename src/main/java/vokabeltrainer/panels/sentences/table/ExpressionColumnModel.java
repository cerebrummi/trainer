package vokabeltrainer.panels.sentences.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import vokabeltrainer.types.LanguageDirection;

public class ExpressionColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = 2275832088905828369L;

   public ExpressionColumnModel(LanguageDirection language)
   {
      TableColumn column = new TableColumn();
      column.setHeaderRenderer(null);
      ExpressionCellRenderer rendererEditor = new ExpressionCellRenderer(
            language);
      column.setCellRenderer(rendererEditor);
      column.setCellEditor(rendererEditor);
      addColumn(column);
   }

}
