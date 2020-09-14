package vokabeltrainer.table.list.editor;

import vokabeltrainer.types.grammatical.ExpressionKind;

public class AntiFocusExpressionKindField extends AntiFocusTextField
{
   private static final long serialVersionUID = 4739033363754099293L;

   private ExpressionKind expressionKind;
   
   public AntiFocusExpressionKindField(ExpressionKind expressionKind)
   {
      super(expressionKind.toString());
      this.expressionKind = expressionKind;
   }

   public ExpressionKind getExpressionKind()
   {
      return expressionKind;
   }

   public void setExpressionKind(ExpressionKind expressionKind)
   {
      this.expressionKind = expressionKind;
   }

}
