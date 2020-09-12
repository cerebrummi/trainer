package vokabeltrainer.types.grammatical;

public enum Gender implements GrammaticalEnum
{
   UNKOWN(
         "unbekannt"),
   FEMALE(
         "weiblich"),
   MALE(
         "männlich"),
   BOTH(
         "weiblich und männlich"),
   NA(
         "nicht anwendbar");

   private String description;

   Gender(String description)
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
      case BOTH:
      case FEMALE:
      case MALE:
         return description;
      case UNKOWN:
         return "Geschlecht " + description;
      case NA:
      default:
         return "";
      }
   }
   
   @Override
   public Enum<?> fromEnumName(String name)
   {
      return Gender.valueOf(name);
   }
}
