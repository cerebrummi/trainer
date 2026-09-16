package vokabeltrainer.table.list.editor;

import javax.swing.SwingWorker;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.table.list.editor.images.ImageItem;
import vokabeltrainer.types.Expression;

public class PictureExpressionEditorController
      implements NikudExpressionEditorControllerConnector
{
   private PictureExpressionEditorView pictureExpressionEditorDialog;

   public PictureExpressionEditorController(App app, Common common, Model model, View view)
   {
      pictureExpressionEditorDialog = new PictureExpressionEditorView(app, common, model, view, this);
   }

   public PictureExpressionEditorView getPictureExpressionEditorDialog()
   {
      return pictureExpressionEditorDialog;
   }

   @Override
   public void openPictureView(App app, Common common, Model model, View view, Expression expression)
   {
      // nothing
   }

   @Override
   public void saveImage(App app, Common common, Model model, View view, Expression expression, ImageItem item)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            model.imageData.saveImage(app, common, view, item.getImage(), expression.getUuid(),
                  item.getImageFileName());
            return null;
         }

      }.execute();
   }

   @Override
   public void deleteImage(App app, Model model, Expression expression, ImageItem item)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            model.imageData.deleteImage(app, expression.getUuid(),
                  item.getImageFileName());
            return null;
         }

      }.execute();
   }
}
