package vokabeltrainer.types.grammatical;

public interface GrammaticalEnum
{
   public String toDescription();

   public GrammaticalEnum fromEnumName(String name);

   public String name();

   public String toInfo();

   public GrammaticalParentEnum getParent();

   public int getPrintOrderNumber();
   
   public GrammaticalEnum getUnkown();

   public enum GrammaticalParentEnum
   {
      GENDER(
            10,
            "Geschlecht"),
      NUMERUS(
            20,
            "Numerus"),
      GRAMMATICAL_PERSON(
            30,
            "grammatische Person"),
      BINJAN(
            40,
            "Binjan"),
      VERB_TIMES(
            50,
            "Zeitform");

      private int sortNumber;
      private String identifier;

      GrammaticalParentEnum(int sortNumber, String identifier)
      {
         this.sortNumber = sortNumber;
         this.identifier = identifier;
      }

      public int getSortNumber()
      {
         return sortNumber;
      }

      public String getIdentifier()
      {
         return identifier;
      }
      
      public GrammaticalEnum getUnkown()
      {
         switch(this)
         {
         case BINJAN:
            return Binjan.BINJAN_UNKNOWN;
         case GENDER:
            return Gender.GENDER_UNKNOWN;
         case GRAMMATICAL_PERSON:
            return GrammaticalPerson.GRAMMATICALPERSON_UNKNOWN;
         case NUMERUS:
            return Numerus.NUMERUS_UNKNOWN;
         case VERB_TIMES:
            return VerbTimes.VERBTIMES_UNKNOWN;
         }
         return null;
      }
   }
}
