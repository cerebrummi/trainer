package vokabeltrainer;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.types.Chapter;

public class ChapterDatabaseComparator implements Comparator<Chapter>
{
   private App app;
   private Common common = null;
   
   public ChapterDatabaseComparator(App app, Common common)
   {
      this.app = app;
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

      if (o1.getDatabaseName(app, common).equals(o2.getDatabaseName(app, common)))
      {
         return coll.compare(o1.getName(), o2.getName());
      }

      return coll.compare(o1.getDatabaseName(app, common), o2.getDatabaseName(app, common));
   }
}
