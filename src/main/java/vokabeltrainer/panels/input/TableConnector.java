package vokabeltrainer.panels.input;

import javax.swing.JTable;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;

public interface TableConnector
{
   public void save(Common common, View view);

   public void fireTableCellUpdated(Common common, View view, JTable table, int selectedRow, int i);
}
