package vokabeltrainer.panels.list.table;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;

import vokabeltrainer.types.DatabaseDescription;

public class DatabaseTableMultiselect extends JTable
{
   private static final long serialVersionUID = 1518676670024526651L;

   private MouseListener mouseListener;

   public DatabaseTableMultiselect(DatabaseTableModel model, int totalWidth)
   {
      super(model, new DatabaseTableColumnModel(totalWidth));
      this.setShowVerticalLines(false);
      setOpaque(true);
      setRowHeight(40);
      setShowHorizontalLines(false);
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.setBorder(BorderFactory.createEmptyBorder());
      this.setTableHeader(null);

      mouseListener = getMultiselectMouseListener();
      addMouseListener(mouseListener);
   }

   private MouseAdapter getMultiselectMouseListener()
   {
      return new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            if (mouseEvent.getClickCount() == 2 && row != -1
                  && row == table.getSelectedRow())
            {
               DatabaseTableRow tableRow = ((DatabaseTableRow) table
                     .getValueAt(row, 0));

               DatabaseDescription databaseDescription = tableRow
                     .getDescription();

               databaseDescription.toggleSelected();

               ((DatabaseTableModel) table.getModel()).fireTableCellUpdated(row,
                     0);

               new SwingWorker<Void, Void>()
               {
                  @Override
                  protected Void doInBackground() throws Exception
                  {
                     if (row == 0)
                     {
                        // nothing
                     }
                     else
                     {
                        getModel().getSelectedRows();
                     }
                     return null;
                  }
               }.execute();
            }
         }
      };
   }

   @Override
   public DatabaseTableModel getModel()
   {
      return (DatabaseTableModel) super.getModel();
   }

   public int getScrollValue()
   {
      for (int i = 0; i < this.getRowCount(); i++)
      {
         if (((DatabaseTableRow) getValueAt(i, 0)).getDescription()
               .isSelected())
         {
            return i * this.getRowHeight();
         }
      }

      return 0;
   }

   public int getMaxScrollValue()
   {
      return this.getRowCount() * this.getRowHeight();
   }

   @Override
   public Class<?> getColumnClass(int column)
   {
      return JLabel.class;
   }

}
