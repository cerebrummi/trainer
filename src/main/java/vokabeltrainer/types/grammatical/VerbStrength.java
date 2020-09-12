package vokabeltrainer.types.grammatical;

public enum VerbStrength
{
   UNKOWN("unbekannt"),
   WEAK("schwach"),
   STRONG("stark"),
   NA("nicht anwendbar");
   
   private String description;
   
   VerbStrength(String description)
   {
      this.description = description;
   }
   
   public String toString()
   {
      return description;
   }
}
