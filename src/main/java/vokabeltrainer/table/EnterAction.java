package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.table.list.editor.NikudExpressionEditorController;
import vokabeltrainer.table.list.editor.NikudExpressionEditorView;
import vokabeltrainer.types.Expression;

public class EnterAction extends AbstractAction
{
   private ExpressionTable table;
   private NikudExpressionEditorView editorPunktation;
   private TableConnector connector;

   public EnterAction(ExpressionTable table,
         TableConnector connector)
   {
      this.table = table;
      this.connector = connector;
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
         showEditorPunktation(expression);
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
}
