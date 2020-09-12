package vokabeltrainer.types.grammatical;

public enum Gender
{
   UNKOWN("unbekannt"),
   FEMALE("weiblich"),
   MALE("männlich"),
   BOTH("weiblich und männlich"),
   NA("nicht anwendbar");

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
}
