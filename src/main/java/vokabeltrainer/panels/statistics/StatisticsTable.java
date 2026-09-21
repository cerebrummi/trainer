package vokabeltrainer.panels.statistics;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;

public class StatisticsTable extends JTable
{
   private static final long serialVersionUID = 1467979172740860765L;

   public StatisticsTable(App app, Common common, StatisticsTableModel model)
   {
      super(model, new StatisticsTableColumnModel(app, common));
      this.setShowVerticalLines(false);
      setOpaque(true);
      setRowHeight(50);
      setShowHorizontalLines(true);
      setBackground(app.appColors.statistics.getSelectedBackground());
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(true);
      this.setCellSelectionEnabled(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      JTableHeader header = getTableHeader();
      header.setForeground(app.appColors.getLightBlue());
      header.setBackground(app.appColors.getMediumBlue());
   }
}
