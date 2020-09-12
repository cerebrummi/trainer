package vokabeltrainer.types.grammatical;

public enum Numerus implements GrammaticalEnum
{
   UNKNOWN(
         "unbekannt"),
   SINGULAR(
         "Singular"),
   DUAL(
         "Dual"),
   PLURAL(
         "Plural"),
   NA(
         "nicht anwendbar");

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

   public String toDescription()
   {
      switch (this)
      {
      case DUAL:
      case PLURAL:
      case SINGULAR:
         return description;
      case UNKNOWN:
         return "Numerus " + description;
      case NA:
      default:
         return "";
      }
   }
   
   @Override
   public Enum<?> fromEnumName(String name)
   {
      return Numerus.valueOf(name);
   }
}
