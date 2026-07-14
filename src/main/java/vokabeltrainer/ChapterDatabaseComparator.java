package vokabeltrainer;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.types.Chapter;

public class ChapterDatabaseComparator implements Comparator<Chapter>
{
   private Common common = null;
   
   public ChapterDatabaseComparator(Common common)
   {
      this.common = common;
   }
   
   @Override
   public int compare(Chapter o1, Chapter o2)
   {
      return compareChapter(o1, o2);
   }

   public int compareChapter(Chapter o1, Chapter o2)
   {
      Collator coll = Collator.getInstance(Locale.GERMAN);
      coll.setStrength(Collator.PRIMARY);

      if (o1.getDatabaseName(common).equals(o2.getDatabaseName(common)))
      {
         return coll.compare(o1.getName(), o2.getName());
      }

      return coll.compare(o1.getDatabaseName(common), o2.getDatabaseName(common));
   }
}
