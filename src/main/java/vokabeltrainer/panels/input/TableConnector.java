package vokabeltrainer.panels.input;

import javax.swing.JTable;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;

public interface TableConnector
{
   public void save(App app, Common common, Model model, View view);

   public void fireTableCellUpdated(App app, Common common, Model model, View view, JTable table, int selectedRow, int i);
}
