package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
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
   private App app;
   private Common common;
   private Model model;
   private View view;

   public EnterAction(App app, Common common, Model model, View view)
   {
      this.app = app;
      this.common = common;
      this.model = model;
      this.view = view;
      PictureExpressionEditorController pictureController = new PictureExpressionEditorController(common, view);
      editorPicture = pictureController.getPictureExpressionEditorDialog();
   }

   public EnterAction(App app, Common common, Model model, View view, ExpressionTable table, TableConnector connector)
   {
      this.app = app;
      this.common = common;
      this.model = model;
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
            showEditorPunktation(app, common, model, view, expression);
         }
         else
         {
            showEditorPicture(app, common, model, view, expression, false);
         }

      }
   }

   private void showEditorPunktation(App app, Common common, Model model, View view, Expression expression)
   {
      editorPunktation.setFrozen(expression.isDoNotChange());
      editorPunktation.setExpression(common, view, expression, false);
      editorPunktation.setLocationRelativeTo(view.getjFrame());
      editorPunktation.setVisible(true);
      // editor is open
      if (editorPunktation.isSave())
      {
         connector.save(app, common, model, view);
      }
      editorPunktation.dispose();
   }

   public void showEditorPicture(App app, Common common, Model model, View view, Expression expression, boolean dropped)
   {
      editorPicture.setExpression(common, view, expression);
      if (model.imageData.isImageForExpressionAvailable(expression.getUuid()))
      {
         editorPicture.setImages(model.imageData.loadImages(app, expression.getUuid()));
      }
      editorPicture.revalidate();
      editorPicture.repaint();
      editorPicture.setLocationRelativeTo(view.getjFrame());
      editorPicture.setVisible(true);
      // editor is open
      editorPicture.dispose();
   }
}
