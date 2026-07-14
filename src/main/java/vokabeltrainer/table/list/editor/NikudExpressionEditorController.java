package vokabeltrainer.table.list.editor;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.table.EnterAction;
import vokabeltrainer.table.list.editor.images.ImageItem;
import vokabeltrainer.types.Expression;

public class NikudExpressionEditorController
      implements NikudExpressionEditorControllerConnector
{
   private LanguageExpressionEditorView nikudExpressionEditorDialog;

   public NikudExpressionEditorController(Common common, View view)
   {
      nikudExpressionEditorDialog = new LanguageExpressionEditorView(common, view, this);
   }

   public LanguageExpressionEditorView getNikudExpressionEditorDialog()
   {
      return nikudExpressionEditorDialog;
   }

   @Override
   public void openPictureView(Common common, View view, Expression expression)
   {
      new EnterAction(common, view).showEditorPicture(common, view, expression, false);
   }

   @Override
   public void saveImage(Common common, View view, Expression expression, ImageItem item)
   {
      // nothing
   }

   @Override
   public void deleteImage(Expression expression, ImageItem item)
   {
      // nothing
   }
}
