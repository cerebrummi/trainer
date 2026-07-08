package vokabeltrainer.table.list.editor;

import javax.swing.SwingWorker;

import vokabeltrainer.common.ImageData;
import vokabeltrainer.table.list.editor.images.ImageItem;
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

   @Override
   public void saveImage(Expression expression, ImageItem item)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            ImageData.saveImage(item.getImage(), expression.getUuid(),
                  item.getImageFileName());
            return null;
         }

      }.execute();
   }

   @Override
   public void deleteImage(Expression expression, ImageItem item)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            ImageData.deleteImage(expression.getUuid(),
                  item.getImageFileName());
            return null;
         }

      }.execute();
   }
}
