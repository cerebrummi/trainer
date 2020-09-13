package vokabeltrainer.types.grammatical;

public enum Gender implements GrammaticalEnum
{
   GENDER_UNKNOWN(
         "unbekannt"),
   FEMALE(
         "weiblich"),
   MALE(
         "männlich"),
   BOTH_FEMALE_MALE(
         "weiblich und männlich"),
   GENDER_NA(
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
      case BOTH_FEMALE_MALE:
      case FEMALE:
      case MALE:
         return description;
      case GENDER_UNKNOWN:
         return "Geschlecht " + description;
      case GENDER_NA:
      default:
         return "";
      }
   }
   
   public String toInfo()
   {
      switch (this)
      {
      case BOTH_FEMALE_MALE:
      case FEMALE:
      case MALE:
         return description;
      case GENDER_UNKNOWN:
      case GENDER_NA:
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
