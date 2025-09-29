package vokabeltrainer.panels.success.table;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import vokabeltrainer.common.colors.SuccessColors;
import vokabeltrainer.types.Expression;

public class SuccessTable extends JTable
{
   private static final long serialVersionUID = 853537882592595897L;

   public SuccessTable(SuccessTableModel model)
   {
      super(model, new SuccessTableColumnModel());
      this.setShowVerticalLines(true);
      setOpaque(true);
      setRowHeight(75);
      setShowHorizontalLines(true);
      setBackground(SuccessColors.getTableBackground());
      this.setRowSelectionAllowed(false);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer(); 
      headerRenderer.setForeground(SuccessColors.getTextForeground());
      headerRenderer.setBackground(SuccessColors.getPanelBackgroundLight());
      this.getTableHeader().setDefaultRenderer(headerRenderer);
      
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
               SuccessTableRow tableRow = ((SuccessTableRow) table
                     .getValueAt(table.getSelectedRow(), 0));
               
               Expression expression = tableRow.getExpression();

               expression.toggleSelected();

               ((SuccessTableModel) table.getModel())
                     .fireTableCellUpdated(table.getSelectedRow(), 0);
            }
         }
      });
   }

}
