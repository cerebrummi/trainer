package vokabeltrainer.table.list.editor;

import vokabeltrainer.common.Common;

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


}
