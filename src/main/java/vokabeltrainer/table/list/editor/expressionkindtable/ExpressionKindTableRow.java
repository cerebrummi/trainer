package vokabeltrainer.table.list.editor.expressionkindtable;

import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class ExpressionKindTableRow
{
   private ExpressionKind expressionKind;

   public ExpressionKindTableRow(ExpressionKind expressionKind)
   {
      this.expressionKind = expressionKind;
   }

   public ExpressionKind getExpressionKind()
   {
      return expressionKind;
   }

}
