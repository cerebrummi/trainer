package vokabeltrainer;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class ExpressionComparator implements Comparator<Expression>
{

   private Language language;
   private boolean sortForDate;

   public ExpressionComparator(Language language, boolean sortForDate)
   {
      this.language = language;
      this.sortForDate = sortForDate;
   }
   
   public ExpressionComparator(Language language)
   {
      this.language = language;
      this.sortForDate = false;
   }

   @Override
   public int compare(Expression o1, Expression o2)
   {
      if(sortForDate)
      {
         return o1.getLastModified().compareTo(o2.getLastModified());
      }
      else if (Language.GERMAN.equals(language))
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
