package vokabeltrainer.types.grammatical;

public interface GrammaticalEnum
{
   public String toDescription();

   public GrammaticalEnum fromEnumName(String name);

   public String name();

   public String toInfo();

   public void toggleSelected();

   public boolean isSelected();

   public void setSelected(boolean selected);

   public GrammaticalParentEnum getParent();

   public int getPrintOrderNumber();

   public enum GrammaticalParentEnum
   {
      GENDER(
            10,
            "Geschlecht",
            Gender.GENDER_UNKNOWN),
      NUMERUS(
            20,
            "Numerus",
            Numerus.NUMERUS_UNKNOWN),
      GRAMMATICAL_PERSON(
            30,
            "grammatische Person",
            GrammaticalPerson.GRAMMATICALPERSON_UNKNOWN),
      BINJAN(
            40,
            "Binjan",
            Binjan.BINJAN_UNKNOWN),
      VERB_TIMES(
            50,
            "Zeit",
            VerbTimes.VERBTIMES_UNKNOWN);

      private int sortNumber;
      private String identifier;
      private GrammaticalEnum unkown;

      GrammaticalParentEnum(int sortNumber, String identifier,
            GrammaticalEnum unkown)
      {
         this.sortNumber = sortNumber;
         this.identifier = identifier;
         this.unkown = unkown;
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
         return unkown;
      }
   }
}
