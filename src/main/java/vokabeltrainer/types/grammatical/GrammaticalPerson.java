package vokabeltrainer.types.grammatical;

public enum GrammaticalPerson implements GrammaticalEnum
{
   UNKOWN(
         "unbekannt"),
   ERSTE_PERSON(
         "1. Person"),
   ZWEITE_PERSON(
         "2. Person"),
   DRITTE_PERSON(
         "3. Person"),
   NA(
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
      case UNKOWN:
         return "Grammatische-Person " + description;
      case NA:
      default:
         return "";
      }
   }
   
   @Override
   public Enum<?> fromEnumName(String name)
   {
      return GrammaticalPerson.valueOf(name);
   }
}
