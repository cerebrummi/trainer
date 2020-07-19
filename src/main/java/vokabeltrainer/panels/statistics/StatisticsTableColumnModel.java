package vokabeltrainer.panels.statistics;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class StatisticsTableColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = 1858850934582009695L;
   
   private StatisticsTableCellRendererEditor renderer;

   public StatisticsTableColumnModel()
   {
      renderer = new StatisticsTableCellRendererEditor();
      
      TableColumn column = new TableColumn();
      column.setHeaderValue("Datum");
      column.setCellRenderer(renderer);
      column.setPreferredWidth(250);
      addColumn(column);
      
      TableColumn column1 = new TableColumn();
      column1.setHeaderValue("Hebräisch >> Deutsch");
      column1.setCellRenderer(renderer);
      column1.setPreferredWidth(150);
      addColumn(column1);
      
      TableColumn column2 = new TableColumn();
      column2.setHeaderValue("Deutsch >> Hebräisch");
      column2.setCellRenderer(renderer);
      column2.setPreferredWidth(150);
      addColumn(column2);
   }

   public StatisticsTableCellRendererEditor getRenderer()
   {
      return renderer;
   }

}
