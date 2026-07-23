package vokabeltrainer.panels.dictionary;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingWorker;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.SaveExpressions;
import vokabeltrainer.common.main.View;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;

public class TrashCanController implements TrashCanControllerConnector
{
   private TrashCanDialog trashCanDialog;
   private DictionaryControllerConnector connector;

   public TrashCanController(Common common, Model model, View view, DictionaryControllerConnector connector,
         Direction initialLanguage)
   {
      this.connector = connector;
      trashCanDialog = new TrashCanDialog(common, view, this);
      trashCanDialog.doShowTable(common, view, loadTableModel(model));
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
   public ExpressionTableModel loadTableModel(Model model)
   {
      return model.data.findTranslationsDeletedWords();
   }

   @Override
   public void restoreSelectedExpressions(App app, Common common, Model model, View view, List<Expression> selectedExpressions)
   {
      if (!selectedExpressions.isEmpty())
      {
         trashCanDialog.setRestore(true);
         model.data.restoreExpressions(selectedExpressions);
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(common, view, loadTableModel(model));
         trashCanDialog.tableValidateRepaint();
         save(app, common, model, view);
      }
   }

   @Override
   public void selectAllExpressionsInTable(Common common, Model model, View view)
   {
      if (trashCanDialog.isTableNotNull())
      {
         trashCanDialog.selectAllExpressionsInTable();
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(common, view, loadTableModel(model));
         trashCanDialog.tableValidateRepaint();
      }
   }

   @Override
   public void unselectAllExpressionsInTable(Common common, Model model, View view)
   {
      if (trashCanDialog.isTableNotNull())
      {
         trashCanDialog.unselectAllExpressionsInTable();
         trashCanDialog.clearTable();
         trashCanDialog.doShowTable(common, view, loadTableModel(model));
         trashCanDialog.tableValidateRepaint();
      }
   }

   @Override
   public void switchLanguage(Common common, Model model, View view)
   {
      trashCanDialog.clearTable();
      trashCanDialog.doShowTable(common, view, loadTableModel(model));
      trashCanDialog.tableValidateRepaint();
   }

   @Override
   public void save(App app, Common common, Model model, View view)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            new SaveExpressions(app, model).save(common, view);
            return null;
         }
      }.execute();
   }

   @Override
   public void fireTableCellUpdated(App app, Common common, Model model, View view, JTable table, int selectedRow, int i)
   {
      ((ExpressionTableModel) table.getModel())
            .fireTableCellUpdated(table.getSelectedRow(), 0);
   }
}
