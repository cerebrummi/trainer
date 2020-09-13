package vokabeltrainer.types.grammatical;

public enum VerbType implements GrammaticalEnum
{
   VERBTYPE_UNKNOWN(
         "unbekannt"),
   REGULAR(
         "regulär"),
   IRREGULAR(
         "irregulär"),
   AUXILIARY(
         "Hilfsverb"),
   VERBTYPE_NA(
         "nicht anwendbar");

   private String description;

   VerbType(String description)
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
      case AUXILIARY:
      case IRREGULAR:
      case REGULAR:
         return description;
      case VERBTYPE_UNKNOWN:
         return "Verbtyp " + description;
      case VERBTYPE_NA:
      default:
         return "";
      }
   }
   
   @Override
   public Enum<?> fromEnumName(String name)
   {
      return VerbType.valueOf(name);
   }
}
