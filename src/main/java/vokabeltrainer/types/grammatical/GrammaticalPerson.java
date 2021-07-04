package vokabeltrainer.types.grammatical;

public enum GrammaticalPerson implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         "bitte wählen"),
   GRAMMATICALPERSON_UNKNOWN(
         "unbekannt"),
   ERSTE_PERSON(
         "1. Person"),
   ZWEITE_PERSON(
         "2. Person"),
   DRITTE_PERSON(
         "3. Person"),
   GRAMMATICALPERSON_NA(
         "nicht anwendbar");

   private String description;

   GrammaticalPerson(String description)
   {
      this.description = description;
   }

   @Override
   public String toString()
   {
      return description;
   }

   public String toDescription()
   {
      switch (this)
      {
      case DRITTE_PERSON:
      case ZWEITE_PERSON:
      case ERSTE_PERSON:
         return description;
      case GRAMMATICALPERSON_UNKNOWN:
         return "Grammatische-Person " + description;
      case GRAMMATICALPERSON_NA:
      default:
         return "";
      }
   }
   
   @Override
   public GrammaticalPerson fromEnumName(String name)
   {
      return GrammaticalPerson.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      switch (this)
      {
      case DRITTE_PERSON:
      case ZWEITE_PERSON:
      case ERSTE_PERSON:
         return description;
      case GRAMMATICALPERSON_UNKNOWN:
      case GRAMMATICALPERSON_NA:
      default:
         return "";
      }
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.GRAMMATICAL_PERSON;
   }
   
   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return GrammaticalPerson.GRAMMATICALPERSON_UNKNOWN;
   }
}
