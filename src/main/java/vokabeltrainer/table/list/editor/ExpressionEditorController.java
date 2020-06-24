package vokabeltrainer.table.list.editor;

public class ExpressionEditorController implements ExpressionEditorControllerConnector
{
   private ExpressionEditorDialog expressionEditorDialog;
   
   public ExpressionEditorController()
   {
     this.expressionEditorDialog = new ExpressionEditorDialog(this);
   }

   public ExpressionEditorDialog getExpressionEditorDialog()
   {
      return expressionEditorDialog;
   }

}
