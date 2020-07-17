package vokabeltrainer.panels.statistics;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class StatisticsTableColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = 1858850934582009695L;
   
   private StatisticsTableCellRenderer renderer;

   public StatisticsTableColumnModel()
   {
      renderer = new StatisticsTableCellRenderer();
      
      TableColumn column = new TableColumn();
      column.setHeaderValue("Datum");
      column.setCellRenderer(renderer);
      column.setPreferredWidth(200);
      addColumn(column);
      
      TableColumn column1 = new TableColumn();
      column1.setHeaderValue("Hebräisch >> Deutsch");
      column1.setCellRenderer(renderer);
      column1.setPreferredWidth(100);
      addColumn(column1);
      
      TableColumn column2 = new TableColumn();
      column2.setHeaderValue("Deutsch >> Hebräisch");
      column2.setCellRenderer(renderer);
      column2.setPreferredWidth(100);
      addColumn(column2);
      
      TableColumn column3 = new TableColumn();
      column3.setHeaderValue("aus dem Training ...");
      column3.setCellRenderer(renderer);
      column3.setPreferredWidth(200);
      addColumn(column3);
   }

   public StatisticsTableCellRenderer getRenderer()
   {
      return renderer;
   }

}
