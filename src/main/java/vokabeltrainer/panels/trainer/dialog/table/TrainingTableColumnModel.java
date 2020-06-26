package vokabeltrainer.panels.trainer.dialog.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class TrainingTableColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = -1884221408045680993L;

   private TrainingTableCellRendererEditor rendererEditor;
   
   public TrainingTableColumnModel()
   {
      rendererEditor = new TrainingTableCellRendererEditor();
    
      TableColumn column = new TableColumn();
      column.setHeaderValue("Bereich");
      column.setCellRenderer(rendererEditor);
      column.setCellEditor(rendererEditor);
      column.setPreferredWidth(300);
      addColumn(column);

      TableColumn column1 = new TableColumn();
      column1.setHeaderValue("Wörter wiederholen");
      column1.setCellRenderer(rendererEditor);
      column1.setCellEditor(rendererEditor);
      column1.setPreferredWidth(150);
      addColumn(column1);

      TableColumn column2 = new TableColumn();
      column2.setHeaderValue("ungelernte Wörter");
      column2.setCellRenderer(rendererEditor);
      column2.setCellEditor(rendererEditor);
      addColumn(column2);

      TableColumn column3 = new TableColumn();
      column3.setHeaderValue("neu lernen");
      column3.setCellRenderer(rendererEditor);
      column3.setCellEditor(rendererEditor);
      addColumn(column3);

      TableColumn column4 = new TableColumn();
      column4.setHeaderValue("leer / fertig");
      column4.setCellRenderer(rendererEditor);
      column4.setCellEditor(rendererEditor);
      column4.setPreferredWidth(50);
      addColumn(column4);
   }

   public TrainingTableCellRendererEditor getRendererEditor()
   {
      return rendererEditor;
   }
}
