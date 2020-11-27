package vokabeltrainer.table.list.editor.expressionkindtable.multiselect;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Vector;

public class ExpressionKindTableRowComparator implements Comparator<Vector<ExpressionKindTableRow>>
{

   @Override
   public int compare(Vector<ExpressionKindTableRow> o1, Vector<ExpressionKindTableRow> o2)
   {
      Collator coll = Collator.getInstance(Locale.GERMAN);
      coll.setStrength(Collator.PRIMARY);
      return coll.compare(o1.get(0).getExpressionKind().toString(),
            o2.get(0).getExpressionKind().toString());
   }

}
