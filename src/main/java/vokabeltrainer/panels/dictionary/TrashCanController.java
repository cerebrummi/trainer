package vokabeltrainer.panels.dictionary;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingWorker;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Data;
import vokabeltrainer.common.main.SaveExpressions;
import vokabeltrainer.common.main.View;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;

public class TrashCanController implements TrashCanControllerConnector
{
   private TrashCanDialog trashCanDialog;
   private DictionaryControllerConnector connector;

   public TrashCanController(Common common, View view, DictionaryControllerConnector connector,
         Direction initialLanguage)
   {
      this.connector = connector;
      trashCanDialog = new TrashCanDialog(common, view, this);
      trashCanDialog.doShowTable(common, view, loadTableModel());
      trashCanDialog.pack();
   }

   @Override
   public DictionaryControllerConnector getDictionaryControllerConnector()
   {
      return connector;
   }

   public TrashCanDialog getTrashCanDialog()
   {
      return trashCanDialog;
   }

   @Override
   public ExpressionTableModel loadTableModel()
   {
      return Data.findTranslationsDeletedWords();
   }

   @Override
   public void restoreSelectedExpressions(Common common, View view, List<Expression> selectedExpressions)
   {
      if (!selectedExpressions.isEmpty())
      {
         trashCanDialog.setRestore(true);
         Data.restoreExpressions(selectedExpressions);
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(common, view, loadTableModel());
         trashCanDialog.tableValidateRepaint();
         save(common, view);
      }
   }

   @Override
   public void selectAllExpressionsInTable(Common common, View view)
   {
      if (trashCanDialog.isTableNotNull())
      {
         trashCanDialog.selectAllExpressionsInTable();
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(common, view, loadTableModel());
         trashCanDialog.tableValidateRepaint();
      }
   }

   @Override
   public void unselectAllExpressionsInTable(Common common, View view)
   {
      if (trashCanDialog.isTableNotNull())
      {
         trashCanDialog.unselectAllExpressionsInTable();
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(common, view, loadTableModel());
         trashCanDialog.tableValidateRepaint();
      }
   }

   @Override
   public void switchLanguage(Common common, View view)
   {
      trashCanDialog.clearTable();
      trashCanDialog.doShowTable(common, view, loadTableModel());
      trashCanDialog.tableValidateRepaint();
   }

   @Override
   public void save(Common common, View view)
   {
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

   @Override
   public void fireTableCellUpdated(Common common, View view, JTable table, int selectedRow, int i)
   {
      ((ExpressionTableModel) table.getModel())
            .fireTableCellUpdated(table.getSelectedRow(), 0);
   }
}
