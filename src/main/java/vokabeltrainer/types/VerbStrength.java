package vokabeltrainer.types;

public enum VerbStrength
{
   NA("nicht anwendbar"),
   UNKOWN("unbekannt"),
   WEAK("schwach"),
   STRONG("stark");
   
   private String name;
   
   VerbStrength(String name)
   {
      this.name = name;
   }
   
   public String toString()
   {
      return name;
   }
}
