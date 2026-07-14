package vokabeltrainer.table.list.editor;

import javax.swing.SwingWorker;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.ImageData;
import vokabeltrainer.common.main.View;
import vokabeltrainer.table.list.editor.images.ImageItem;
import vokabeltrainer.types.Expression;

public class PictureExpressionEditorController
      implements NikudExpressionEditorControllerConnector
{
   private PictureExpressionEditorView pictureExpressionEditorDialog;

   public PictureExpressionEditorController(Common common, View view)
   {
      pictureExpressionEditorDialog = new PictureExpressionEditorView(common, view, this);
   }

   public PictureExpressionEditorView getPictureExpressionEditorDialog()
   {
      return pictureExpressionEditorDialog;
   }

   @Override
   public void openPictureView(Common common, View view, Expression expression)
   {
      // nothing
   }

   @Override
   public void saveImage(Common common, View view, Expression expression, ImageItem item)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            ImageData.saveImage(common, view, item.getImage(), expression.getUuid(),
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
