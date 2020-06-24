package vokabeltrainer.table.list.editor;

public class ExpressionEditorController
{
   private ExpressionEditorDialog expressionEditorDialog;
   
   public ExpressionEditorController()
   {
     this.expressionEditorDialog = new ExpressionEditorDialog();
   }

   public ExpressionEditorDialog getExpressionEditorDialog()
   {
      return expressionEditorDialog;
   }

}
