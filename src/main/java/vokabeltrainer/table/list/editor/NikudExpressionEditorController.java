package vokabeltrainer.table.list.editor;

public class NikudExpressionEditorController implements NikudExpressionEditorControllerConnector
{
   private NikudExpressionEditorView nikudExpressionEditorDialog;
   
   public NikudExpressionEditorController()
   {
      nikudExpressionEditorDialog = new NikudExpressionEditorView(this);
   }

   public NikudExpressionEditorView getNikudExpressionEditorDialog()
   {
      return nikudExpressionEditorDialog;
   }

}
