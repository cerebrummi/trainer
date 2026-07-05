package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.table.list.editor.NikudExpressionEditorController;
import vokabeltrainer.table.list.editor.PictureExpressionEditorController;
import vokabeltrainer.table.list.editor.PictureExpressionEditorView;
import vokabeltrainer.table.list.editor.LanguageExpressionEditorView;
import vokabeltrainer.types.Expression;

public class EnterAction extends AbstractAction
{
   private ExpressionTable table;
   private LanguageExpressionEditorView editorPunktation;
   private PictureExpressionEditorView editorPicture;
   private TableConnector connector;

   public EnterAction(ExpressionTable table, TableConnector connector)
   {
      this.table = table;
      this.connector = connector;
      NikudExpressionEditorController controller = new NikudExpressionEditorController();
      editorPunktation = controller.getNikudExpressionEditorDialog();
      PictureExpressionEditorController pictureController = new PictureExpressionEditorController();
      editorPicture = pictureController.getPictureExpressionEditorDialog();
   }

   private static final long serialVersionUID = 719272853628204094L;

   @Override
   public void actionPerformed(ActionEvent e)
   {
      int selectedRow = table.getSelectedRow();
      
      if (selectedRow >= 0)
      {
         Expression expression = (Expression) table.getValueAt(selectedRow, 0);
         
         if(table.getSelectedColumn() == 0)
         {
        	 showEditorPunktation(expression);
         }
         else
         {
        	 showEditorPicture(expression);
         }
         
      }
   }

   private void showEditorPunktation(Expression expression)
   {
      editorPunktation.setFrozen(expression.isDoNotChange());
      editorPunktation.setExpression(expression, false);
      editorPunktation.setLocationRelativeTo(Common.getjFrame());
      editorPunktation.setVisible(true);
      // editor is open
      if (editorPunktation.isSave())
      {
         connector.save();
      }
      editorPunktation.dispose();
   }
   
   private void showEditorPicture(Expression expression)
   {
      editorPicture.setExpression(expression);
      editorPicture.revalidate();
      editorPicture.repaint();
      editorPicture.setLocationRelativeTo(Common.getjFrame());
      editorPicture.setVisible(true);
      // editor is open
      editorPicture.dispose();
   }
}
