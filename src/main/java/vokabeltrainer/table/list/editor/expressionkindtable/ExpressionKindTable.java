package vokabeltrainer.table.list.editor.expressionkindtable;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class ExpressionKindTable extends JTable
{
   private static final long serialVersionUID = 1518676670024526651L;

   public ExpressionKindTable(ExpressionKindTableModel model, int totalWidth)
   {
      super(model, new ExpressionKindTableColumnModel(totalWidth));
      this.setShowVerticalLines(false);
      setOpaque(true);
      setRowHeight(30);
      setShowHorizontalLines(false);
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.setBorder(BorderFactory.createEmptyBorder());
      this.setTableHeader(null);
      
      addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1
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

   @Override
   public ExpressionKindTableModel getModel()
   {
      return (ExpressionKindTableModel) super.getModel();
   }
   
}
