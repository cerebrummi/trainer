package vokabeltrainer.panels.start.table.singleselect;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;

import vokabeltrainer.common.colors.StartColors;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Data;
import vokabeltrainer.common.main.SaveExpressions;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.settings.InputDatabaseNameDialog;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class DatabaseTableCopy extends JTable
{
   private static final long serialVersionUID = 4815287371476856952L;
   private MouseListener mouseListener;

   private Translator translator;

   public DatabaseTableCopy(Common common, View view, DatabaseTableCopyModel model, int totalWidth)
   {
      super(model, new DatabaseTableCopyColumnModel(common, totalWidth));
      translator = common.getTranslator();
      this.setShowVerticalLines(false);
      setOpaque(true);
      setBackground(StartColors.getDatabase_Item());
      setRowHeight(30);
      setShowHorizontalLines(true);
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(false);
      this.setCellSelectionEnabled(false);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.setBorder(BorderFactory.createEmptyBorder());
      this.setTableHeader(null);

      mouseListener = getSingleselectMouseListener(common, view);
      addMouseListener(mouseListener);
   }

   private MouseAdapter getSingleselectMouseListener(Common common, View view)
   {
      return new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1
                  && row == table.getSelectedRow())
            {
               DatabaseTableCopyRow tableRow = ((DatabaseTableCopyRow) table
                     .getValueAt(table.getSelectedRow(), 0));

               InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(common, view,
                     translator.realisticTranslate(
                           Translation.EXPORT_INTERNE_DATENBANK));
               dialog.setVisible(true);

               final String databaseName;
               final boolean overwriteDatabaseNames;

               if (!dialog.isStartImportOrExport())
               {
                  dialog.dispose();
                  return;
               }
               else
               {
                  databaseName = dialog.getDatabaseName();
                  overwriteDatabaseNames = dialog.isOverwrite();
                  dialog.dispose();
                  Data.copyInternalDatabase(common,
                        tableRow.getDatabaseItem().getDatabase(),
                        overwriteDatabaseNames, databaseName);
                  new SwingWorker<Void, Void>()
                  {

                     @Override
                     protected Void doInBackground() throws Exception
                     {
                        new SaveExpressions().save(common, view);
                        return null;
                     }

                  }.execute();
               }
            }
         }
      };
   }

   @Override
   public DatabaseTableCopyModel getModel()
   {
      return (DatabaseTableCopyModel) super.getModel();
   }

   @Override
   public Class<?> getColumnClass(int column)
   {
      return JLabel.class;
   }
}
