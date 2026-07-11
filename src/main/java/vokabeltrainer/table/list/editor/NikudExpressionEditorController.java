package vokabeltrainer.table.list.editor;

import vokabeltrainer.table.EnterAction;
import vokabeltrainer.table.list.editor.images.ImageItem;
import vokabeltrainer.types.Expression;

public class NikudExpressionEditorController
      implements NikudExpressionEditorControllerConnector
{
   private LanguageExpressionEditorView nikudExpressionEditorDialog;

   public NikudExpressionEditorController()
   {
      nikudExpressionEditorDialog = new LanguageExpressionEditorView(this);
   }

   public LanguageExpressionEditorView getNikudExpressionEditorDialog()
   {
      return nikudExpressionEditorDialog;
   }

   @Override
   public void openPictureView(Expression expression)
   {
      new EnterAction().showEditorPicture(expression, false);
   }

   @Override
   public void saveImage(Expression expression, ImageItem item)
   {
      // nothing
   }

   @Override
   public void deleteImage(Expression expression, ImageItem item)
   {
      // nothing
   }
}
