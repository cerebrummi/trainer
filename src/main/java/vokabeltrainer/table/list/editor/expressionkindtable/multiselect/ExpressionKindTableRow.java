package vokabeltrainer.table.list.editor.expressionkindtable.multiselect;

import vokabeltrainer.types.grammatical.expressionkind.ExpressionKindItem;

public class ExpressionKindTableRow
{
   private ExpressionKindItem expressionKind;

   public ExpressionKindTableRow(ExpressionKindItem expressionKind)
   {
      this.expressionKind = expressionKind;
   }

   public ExpressionKindItem getExpressionKind()
   {
      return expressionKind;
   }

}
