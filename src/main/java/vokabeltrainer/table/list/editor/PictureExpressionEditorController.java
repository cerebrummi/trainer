package vokabeltrainer.table.list.editor;

import vokabeltrainer.types.Expression;

public class PictureExpressionEditorController
      implements NikudExpressionEditorControllerConnector
{
   private PictureExpressionEditorView pictureExpressionEditorDialog;

   public PictureExpressionEditorController()
   {
      pictureExpressionEditorDialog = new PictureExpressionEditorView(this);
   }

   public PictureExpressionEditorView getPictureExpressionEditorDialog()
   {
      return pictureExpressionEditorDialog;
   }

   @Override
   public void openPictureView(Expression expression)
   {
      // nothing
   }


}
