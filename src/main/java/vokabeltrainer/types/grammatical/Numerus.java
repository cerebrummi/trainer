package vokabeltrainer.types.grammatical;

public enum Numerus
{
   UNKNOWN("unbekannt"),
   SINGULAR("Singular"),
   DUAL("Dual"),
   PLURAL("Plural"),
   NA("nicht anwendbar");

   private String description;

   Numerus(String description)
   {
      this.description = description;
   }

   @Override
   public String toString()
   {
      return description;
   }
}
