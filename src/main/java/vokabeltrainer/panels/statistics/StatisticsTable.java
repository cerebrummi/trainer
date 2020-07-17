package vokabeltrainer.panels.statistics;

import java.awt.Color;
import javax.swing.JTable;

public class StatisticsTable extends JTable
{
   private static final long serialVersionUID = 1467979172740860765L;

   public StatisticsTable(StatisticsTableModel model)
   {
      super(model, new StatisticsTableColumnModel());

      putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
      setOpaque(false);
      setRowHeight(50);
      setShowHorizontalLines(true);
      setBackground(new Color(0, 0, 0, 0));
      this.setRowSelectionAllowed(false);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
   }

 

}
