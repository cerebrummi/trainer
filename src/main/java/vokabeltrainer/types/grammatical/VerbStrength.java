package vokabeltrainer.types.grammatical;

public enum VerbStrength implements GrammaticalEnum
{
   UNKOWN(
         "unbekannt"),
   WEAK(
         "schwach"),
   STRONG(
         "stark"),
   NA(
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
      case UNKOWN:
         return "Verbstärke " + description;
      case NA:
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
