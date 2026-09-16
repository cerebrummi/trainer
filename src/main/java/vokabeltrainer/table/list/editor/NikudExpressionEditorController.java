package vokabeltrainer.table.list.editor;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.table.EnterAction;
import vokabeltrainer.table.list.editor.images.ImageItem;
import vokabeltrainer.types.Expression;

public class NikudExpressionEditorController
      implements NikudExpressionEditorControllerConnector
{
   private LanguageExpressionEditorView nikudExpressionEditorDialog;

   public NikudExpressionEditorController(App app, Common common, Model model, View view)
   {
      nikudExpressionEditorDialog = new LanguageExpressionEditorView(app, common, model, view, this);
   }

   public LanguageExpressionEditorView getNikudExpressionEditorDialog()
   {
      return nikudExpressionEditorDialog;
   }

   @Override
   public void openPictureView(App app, Common common, Model model, View view, Expression expression)
   {
      new EnterAction(app, common, model, view).showEditorPicture(app, common, model, view, expression, false);
   }

   @Override
   public void saveImage(App app, Common common, Model model, View view, Expression expression, ImageItem item)
   {
      // nothing
   }

   @Override
   public void deleteImage(App app, Model model, Expression expression, ImageItem item)
   {
      // nothing
   }
}
