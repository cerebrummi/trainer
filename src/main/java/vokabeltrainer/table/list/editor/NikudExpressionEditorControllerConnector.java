package vokabeltrainer.table.list.editor;

import vokabeltrainer.table.list.editor.images.ImageItem;
import vokabeltrainer.types.Expression;

public interface NikudExpressionEditorControllerConnector
{

   void openPictureView(Expression expression);

   void saveImage(Expression expression, ImageItem item);

   void deleteImage(Expression expression, ImageItem item);

}
