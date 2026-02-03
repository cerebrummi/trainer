package vokabeltrainer.table.list.editor;

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


}
