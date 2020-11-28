package vokabeltrainer.table.list.editor.expressionkindtable.multiselect;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import vokabeltrainer.table.list.editor.ExpressionEditorViewConnector;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class ExpressionKindTableMultiselect extends JTable
{
   private static final long serialVersionUID = 1518676670024526651L;

   public ExpressionKindTableMultiselect(ExpressionKindTableModel model,
         int totalWidth, ExpressionEditorViewConnector connector)
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

               new SwingWorker<Void, Void>()
               {
                  @Override
                  protected Void doInBackground() throws Exception
                  {
                     if (row == 0)
                     {
                        connector.showGrammaticalEnums(Collections.emptySet());
                     }
                     else
                     {
                        connector.showGrammaticalEnums(
                              ExpressionKind.getSetOfGrammaticalParentEnums(
                                    getModel().getSelectedRows()));
                     }
                     return null;
                  }
               }.execute();
            }
         }
      });
   }

   @Override
   public ExpressionKindTableModel getModel()
   {
      return (ExpressionKindTableModel) super.getModel();
   }

   public int getScrollValue()
   {
      for (int i = 0; i < this.getRowCount(); i++)
      {
         if (((ExpressionKindTableRow) getValueAt(i, 0)).getExpressionKind()
               .isSelected())
         {
            return i * this.getRowHeight();
         }
      }

      return 0;
   }

   public int getMaxScrollValue()
   {
      // TODO Auto-generated method stub
      return this.getRowCount() * this.getRowHeight();
   }

}
