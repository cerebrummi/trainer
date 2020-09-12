package vokabeltrainer.types.grammatical;

public enum GrammaticalPerson
{
   UNKOWN("unbekannt"),
   ERSTE_PERSON("1. Person"),
   ZWEITE_PERSON("2. Person"),
   DRITTE_PERSON("3. Person"),
   NA("nicht anwendbar");
   
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
}
