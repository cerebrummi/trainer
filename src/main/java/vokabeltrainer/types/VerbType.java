package vokabeltrainer.types;

public enum VerbType
{
   NA("nicht anwendbar"),
   UNKOWN("unbekannt"),
   REGULAR("regulär"),
   IRREGULAR("irregulär"),
   AUXILIARY("Hilfsverb");
   
   private String name;
   
   VerbType(String name)
   {
      this.name = name;
   }
   
   public String toString()
   {
      return name;
   }
}
