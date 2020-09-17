package vokabeltrainer.table.list.editor.grammartable;

import vokabeltrainer.types.grammatical.GrammaticalEnum;

public class GrammarTableRow
{
   private GrammaticalEnum grammaticalEnum;

   public GrammarTableRow(GrammaticalEnum expression)
   {
      this.grammaticalEnum = expression;
   }

   public GrammaticalEnum getGrammaticalEnum()
   {
      return grammaticalEnum;
   }
}
