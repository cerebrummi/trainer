package vokabeltrainer.table.list.editor.grammartable;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.Settings;
import vokabeltrainer.types.grammatical.GrammaticalEnum;

public class GrammarTable extends JTable
{
   private static final long serialVersionUID = 5464715782737391017L;

   public GrammarTable(GrammarTableModel model, int totalWidth)
   {
      super(model, new GrammarTableColumnModel(totalWidth));
      this.setShowVerticalLines(false);
      setOpaque(true);
      setRowHeight(30);
      setShowHorizontalLines(true);
      setBackground(Settings.getLightYellow());
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(true);
      this.setCellSelectionEnabled(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
      addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1
                  && row == table.getSelectedRow() && table.getSelectedColumn() == 0)
            {
               GrammarTableRow tableRow = ((GrammarTableRow) table
                     .getValueAt(table.getSelectedRow(), 0));
               
               GrammaticalEnum grammaticalEnum = tableRow.getGrammaticalEnum();

               grammaticalEnum.toggleSelected();

               ((GrammarTableModel) table.getModel())
                     .fireTableCellUpdated(table.getSelectedRow(), 0);
            }
         }
      });
   }
}
