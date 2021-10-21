package vokabeltrainer;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import vokabeltrainer.types.Chapter;

public class ChapterDatabaseComparator implements Comparator<Chapter>
{

   @Override
   public int compare(Chapter o1, Chapter o2)
   {
      Collator coll = Collator.getInstance(Locale.GERMAN);
      coll.setStrength(Collator.PRIMARY);
      
      if(o1.getName().equals(o2.getName()))
      {
         return coll.compare(o1.getDatabaseName(), o2.getDatabaseName());
      }
      
      return coll.compare(o1.getName(), o2.getName());
   }
}

