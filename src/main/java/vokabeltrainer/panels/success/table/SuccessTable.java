package vokabeltrainer.panels.success.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.Settings;

public class SuccessTable extends JTable
{
   private static final long serialVersionUID = 853537882592595897L;

   public SuccessTable(SuccessTableModel model)
   {
      super(model, new SuccessTableColumnModel());
      this.setShowVerticalLines(false);
      setOpaque(false);
      setRowHeight(50);
      setShowHorizontalLines(true);
      setBackground(Settings.getTransparent());
      this.setRowSelectionAllowed(false);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

}
