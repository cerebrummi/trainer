package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.table.list.editor.ExpressionEditorController;
import vokabeltrainer.table.list.editor.ExpressionEditorView;
import vokabeltrainer.table.list.editor.NikudExpressionEditorController;
import vokabeltrainer.table.list.editor.NikudExpressionEditorView;
import vokabeltrainer.types.Expression;

public class EnterAction extends AbstractAction
{
   private ExpressionTable table;
   private ExpressionEditorView editorIvrit;
   private NikudExpressionEditorView editorPunktation;
   private TableConnector connector;

   public EnterAction(ExpressionTable table,
         TableConnector connector)
   {
      this.table = table;
      this.connector = connector;
      editorIvrit = new ExpressionEditorController().getExpressionEditorDialog();
      editorPunktation = new NikudExpressionEditorController().getNikudExpressionEditorDialog();
   }

   private static final long serialVersionUID = 719272853628204094L;

   @Override
   public void actionPerformed(ActionEvent e)
   {
      int selectedRow = table.getSelectedRow();
      if (selectedRow >= 0)
      {
         Expression expression = (Expression) table.getValueAt(selectedRow, 0);
         if(expression.isIvrit())
         {
            showEditorIvrit(expression);
         }
         else
         {
            showEditorPunktation(expression);
         }
      }
   }

   private void showEditorPunktation(Expression expression)
   {
      editorPunktation.setFrozen(expression.isDoNotChange());
      editorPunktation.setExpression(expression, false);
      editorPunktation.setLocationRelativeTo(null);
      editorPunktation.setVisible(true);
      if (editorPunktation.isSave())
      {
         connector.save();
      }
      editorPunktation.dispose();
   }

   private void showEditorIvrit(Expression expression)
   {
      editorIvrit.setFrozen(expression.isDoNotChange());
      editorIvrit.setExpression(expression, false);
      editorIvrit.setLocationRelativeTo(null);
      editorIvrit.setVisible(true);
      if (editorIvrit.isSave())
      {
         connector.save();
      }
      editorIvrit.dispose();
   }
}
