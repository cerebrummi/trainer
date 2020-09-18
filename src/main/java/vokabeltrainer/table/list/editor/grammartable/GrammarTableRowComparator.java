package vokabeltrainer.table.list.editor.grammartable;

import java.util.Comparator;
import java.util.Vector;

public class GrammarTableRowComparator
      implements Comparator<Vector<GrammarTableRow>>
{

   @Override
   public int compare(Vector<GrammarTableRow> o1, Vector<GrammarTableRow> o2)
   {
      if (o1.get(0).getGrammaticalEnum().getSortNumber() < o2.get(0)
            .getGrammaticalEnum().getSortNumber())
      {
         return 1;
      }
      if (o1.get(0).getGrammaticalEnum().getSortNumber() > o2.get(0)
            .getGrammaticalEnum().getSortNumber())
      {
         return 1;
      }
      return 0;
   }

}
