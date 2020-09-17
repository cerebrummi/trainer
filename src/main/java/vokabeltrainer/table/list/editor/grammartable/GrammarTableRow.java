package vokabeltrainer.table.list.editor.grammartable;

import vokabeltrainer.types.grammatical.GrammaticalEnum;

public class GrammarTableRow
{
   private GrammaticalEnum grammaticalEnum;

   public GrammarTableRow(GrammaticalEnum grammaticalEnum)
   {
      this.grammaticalEnum = grammaticalEnum;
   }

   public GrammaticalEnum getGrammaticalEnum()
   {
      return grammaticalEnum;
   }
}
