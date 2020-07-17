package vokabeltrainer.panels.statistics;

import javax.swing.table.DefaultTableModel;

public class StatisticsTableModel extends DefaultTableModel
{
   private static final long serialVersionUID = -2421475557884629587L;

   private StatisticsTableRow[][] data;
   private final static String[][] COLUMNNAMES = { { "" }, { "" }, { "" },
         { "" } };

   public StatisticsTableModel(StatisticsTableRow[][] data)
   {
      super(data, COLUMNNAMES);
      this.data = data;
   }

   public StatisticsTableRow[][] getData()
   {
      return data;
   }
}
