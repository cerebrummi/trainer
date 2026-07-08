package vokabeltrainer.table.list.editor;

import vokabeltrainer.common.Common;
import vokabeltrainer.table.EnterAction;
import vokabeltrainer.types.Expression;

public class NikudExpressionEditorController
      implements NikudExpressionEditorControllerConnector
{
   private LanguageExpressionEditorView nikudExpressionEditorDialog;

   public NikudExpressionEditorController()
   {
      nikudExpressionEditorDialog = new LanguageExpressionEditorView(this);
      Common.setLanguageExpressionEditor(nikudExpressionEditorDialog);
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
}
