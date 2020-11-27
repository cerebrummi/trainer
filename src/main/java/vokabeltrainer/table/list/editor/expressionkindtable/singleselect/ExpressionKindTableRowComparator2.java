package vokabeltrainer.table.list.editor.expressionkindtable.singleselect;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Vector;

public class ExpressionKindTableRowComparator2 implements Comparator<Vector<ExpressionKindTableRow2>>
{

   @Override
   public int compare(Vector<ExpressionKindTableRow2> o1, Vector<ExpressionKindTableRow2> o2)
   {
      Collator coll = Collator.getInstance(Locale.GERMAN);
      coll.setStrength(Collator.PRIMARY);
      return coll.compare(o1.get(0).getExpressionKind().toString(),
            o2.get(0).getExpressionKind().toString());
   }

}
