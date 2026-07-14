package vokabeltrainer.table.list.editor;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.table.list.editor.images.ImageItem;
import vokabeltrainer.types.Expression;

public interface NikudExpressionEditorControllerConnector
{

   void openPictureView(Common common, View view, Expression expression);

   void saveImage(Common common, View view, Expression expression, ImageItem item);

   void deleteImage(Expression expression, ImageItem item);

}
