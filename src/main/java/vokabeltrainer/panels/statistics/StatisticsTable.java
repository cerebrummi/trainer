package vokabeltrainer.panels.statistics;

import javax.swing.JTable;
import vokabeltrainer.Settings;

public class StatisticsTable extends JTable
{
   private static final long serialVersionUID = 1467979172740860765L;

   public StatisticsTable(StatisticsTableModel model)
   {
      super(model, new StatisticsTableColumnModel());
      this.setShowVerticalLines(false);
      setOpaque(false);
      setRowHeight(50);
      setShowHorizontalLines(true);
      setBackground(Settings.getTransparent());
      this.setRowSelectionAllowed(false);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
   }
}
