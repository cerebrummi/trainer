package vokabeltrainer.table.list.editor;

public class ExpressionEditorController implements ExpressionEditorControllerConnector
{
   private ExpressionEditorView expressionEditorDialog;
   
   public ExpressionEditorController()
   {
     this.expressionEditorDialog = new ExpressionEditorView(this);
   }

   public ExpressionEditorView getExpressionEditorDialog()
   {
      return expressionEditorDialog;
   }

}
