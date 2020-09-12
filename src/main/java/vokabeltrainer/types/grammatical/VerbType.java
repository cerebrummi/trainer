package vokabeltrainer.types.grammatical;

public enum VerbType
{
   UNKOWN("unbekannt"),
   REGULAR("regulär"),
   IRREGULAR("irregulär"),
   AUXILIARY("Hilfsverb"),
   NA("nicht anwendbar");
   
   private String description;
   
   VerbType(String description)
   {
      this.description = description;
   }
   
   public String toString()
   {
      return description;
   }
}
