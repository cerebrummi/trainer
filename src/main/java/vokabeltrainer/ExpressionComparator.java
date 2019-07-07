package vokabeltrainer;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class ExpressionComparator implements Comparator<Expression>
{

   Language language;

   public ExpressionComparator(Language language)
   {
      this.language = language;
   }

   @Override
   public int compare(Expression o1, Expression o2)
   {
      if (Language.GERMAN.equals(language))
      {
         Collator coll = Collator.getInstance(Locale.GERMAN);
         coll.setStrength(Collator.PRIMARY);
         return coll.compare(o1.getGerman(), o2.getGerman());
      }
      else
      {
         return o1.getHebrew().compareTo(o2.getHebrew());
      }
   }

}
