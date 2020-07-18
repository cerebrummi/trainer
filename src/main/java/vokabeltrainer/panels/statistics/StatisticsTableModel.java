package vokabeltrainer.panels.statistics;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class StatisticsTableModel<V> extends DefaultTableModel
{
   private static final long serialVersionUID = -2421475557884629587L;

   private Vector<Vector<StatisticsTableRow>> data;

   public StatisticsTableModel(Vector<Vector<StatisticsTableRow>> data)
   {
      super(data, data.size());
      this.data = data;
   }

   public void deleteRow(int row)
   {
      data.remove(row);
      this.fireTableRowsDeleted(row, row);
   }
}
