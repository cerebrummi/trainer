package vokabeltrainer.types.grammatical;

import java.util.HashSet;
import java.util.Set;

public class GrammaticalEnumHelper
{

   private GrammaticalEnumHelper()
   {
      
   }

   public static Set<Class<? extends GrammaticalEnum>> getEnums()
   {
      Set<Class<? extends GrammaticalEnum>> enums = new HashSet<>();
      enums.add(Binjan.class);
      enums.add(Gender.class);
      enums.add(GrammaticalPerson.class);
      enums.add(Numerus.class);
      enums.add(VerbTimes.class);
      return enums;
   }
}
