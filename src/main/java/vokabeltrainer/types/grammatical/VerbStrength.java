package vokabeltrainer.types.grammatical;

public enum VerbStrength implements GrammaticalEnum
{
   VERBSTENGTH_UNKNOWN(
         "unbekannt"),
   WEAK(
         "schwach"),
   STRONG(
         "stark"),
   VERBSTENGTH_NA(
         "nicht anwendbar");

   private String description;

   VerbStrength(String description)
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
      case STRONG:
      case WEAK:
         return description;
      case VERBSTENGTH_UNKNOWN:
         return "Verbstärke " + description;
      case VERBSTENGTH_NA:
      default:
         return "";
      }
   }
   
   @Override
   public Enum<?> fromEnumName(String name)
   {
      return VerbStrength.valueOf(name);
   }
}
