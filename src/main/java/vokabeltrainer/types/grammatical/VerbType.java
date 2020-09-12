package vokabeltrainer.types.grammatical;

public enum VerbType implements GrammaticalEnum
{
   UNKOWN(
         "unbekannt"),
   REGULAR(
         "regulär"),
   IRREGULAR(
         "irregulär"),
   AUXILIARY(
         "Hilfsverb"),
   NA(
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
      case UNKOWN:
         return "Verbtyp " + description;
      case NA:
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
