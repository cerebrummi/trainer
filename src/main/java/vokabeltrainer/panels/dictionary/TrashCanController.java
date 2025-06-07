package vokabeltrainer.panels.dictionary;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingWorker;

import vokabeltrainer.common.Data;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;

public class TrashCanController implements TrashCanControllerConnector
{
   private TrashCanDialog trashCanDialog;
   private DictionaryControllerConnector connector;

   public TrashCanController(DictionaryControllerConnector connector,
         Direction initialLanguage)
   {
      this.connector = connector;
      trashCanDialog = new TrashCanDialog(this);
      trashCanDialog.doShowTable(loadTableModel());
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
   public void restoreSelectedExpressions(List<Expression> selectedExpressions)
   {
      if (!selectedExpressions.isEmpty())
      {
         trashCanDialog.setRestore(true);
         Data.restoreExpressions(selectedExpressions);
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(
               loadTableModel());
         trashCanDialog.tableValidateRepaint();
         save();
      }
   }

   @Override
   public void selectAllExpressionsInTable()
   {
      if (trashCanDialog.isTableNotNull())
      {
         trashCanDialog.selectAllExpressionsInTable();
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(
               loadTableModel());
         trashCanDialog.tableValidateRepaint();
      }
   }

   @Override
   public void unselectAllExpressionsInTable()
   {
      if (trashCanDialog.isTableNotNull())
      {
         trashCanDialog.unselectAllExpressionsInTable();
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(
               loadTableModel());
         trashCanDialog.tableValidateRepaint();
      }
   }

   @Override
   public void switchLanguage()
   {
      trashCanDialog.clearTable();
      trashCanDialog
            .doShowTable(loadTableModel());
      trashCanDialog.tableValidateRepaint();
   }

   @Override
   public void save()
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            new SaveExpressions().save(false);
            return null;
         }
      }.execute();
   }

   @Override
   public void fireTableCellUpdated(JTable table, int selectedRow, int i)
   {
      ((ExpressionTableModel) table.getModel())
      .fireTableCellUpdated(table.getSelectedRow(), 0);
   }
}
