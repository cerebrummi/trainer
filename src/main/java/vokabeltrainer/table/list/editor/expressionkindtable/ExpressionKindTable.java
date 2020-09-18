package vokabeltrainer.table.list.editor.expressionkindtable;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.Settings;
import vokabeltrainer.panels.success.table.SuccessTableModel;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class ExpressionKindTable extends JTable
{
   private static final long serialVersionUID = 1518676670024526651L;

   public ExpressionKindTable(SuccessTableModel model, int totalWidth)
   {
      super(model, new ExpressionKindTableColumnModel(totalWidth));
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
                  && row == table.getSelectedRow())
            {
               ExpressionKindTableRow tableRow = ((ExpressionKindTableRow) table
                     .getValueAt(table.getSelectedRow(), 0));
               
               ExpressionKind expressionKind = tableRow.getExpressionKind();

               expressionKind.toggleSelected();

               ((ExpressionKindTableModel) table.getModel())
                     .fireTableCellUpdated(table.getSelectedRow(), 0);
            }
         }
      });
   }

}
