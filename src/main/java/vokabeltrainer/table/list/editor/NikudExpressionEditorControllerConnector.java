package vokabeltrainer.table.list.editor;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.table.list.editor.images.ImageItem;
import vokabeltrainer.types.Expression;

public interface NikudExpressionEditorControllerConnector
{

   void openPictureView(App app, Common common, Model model, View view, Expression expression);

   void saveImage(App app, Common common, Model model, View view, Expression expression, ImageItem item);

   void deleteImage(App app, Model model, Expression expression, ImageItem item);

}
