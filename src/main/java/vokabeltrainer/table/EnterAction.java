package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.ImageData;
import vokabeltrainer.common.main.View;
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
   private Common common;
   private View view;

   public EnterAction(Common common, View view)
   {
      this.common = common;
      this.view = view;
      PictureExpressionEditorController pictureController = new PictureExpressionEditorController(common, view);
      editorPicture = pictureController.getPictureExpressionEditorDialog();
   }

   public EnterAction(Common common, View view, ExpressionTable table, TableConnector connector)
   {
      this.common = common;
      this.view = view;
      this.table = table;
      this.connector = connector;
      NikudExpressionEditorController controller = new NikudExpressionEditorController(common, view);
      editorPunktation = controller.getNikudExpressionEditorDialog();
      PictureExpressionEditorController pictureController = new PictureExpressionEditorController(common, view);
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

         if (table.getSelectedColumn() == 0)
         {
            showEditorPunktation(common, view, expression);
         }
         else
         {
            showEditorPicture(common, view, expression, false);
         }

      }
   }

   private void showEditorPunktation(Common common, View view, Expression expression)
   {
      editorPunktation.setFrozen(expression.isDoNotChange());
      editorPunktation.setExpression(common, view, expression, false);
      editorPunktation.setLocationRelativeTo(view.getjFrame());
      editorPunktation.setVisible(true);
      // editor is open
      if (editorPunktation.isSave())
      {
         connector.save(common, view);
      }
      editorPunktation.dispose();
   }

   public void showEditorPicture(Common common, View view, Expression expression, boolean dropped)
   {
      editorPicture.setExpression(common, view, expression);
      if (ImageData.isImageForExpressionAvailable(expression.getUuid()))
      {
         editorPicture.setImages(ImageData.loadImages(expression.getUuid()));
      }
      editorPicture.revalidate();
      editorPicture.repaint();
      editorPicture.setLocationRelativeTo(view.getjFrame());
      editorPicture.setVisible(true);
      // editor is open
      editorPicture.dispose();
   }
}
