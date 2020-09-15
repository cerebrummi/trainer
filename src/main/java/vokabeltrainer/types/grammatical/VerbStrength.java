package vokabeltrainer.types.grammatical;

public enum VerbStrength implements GrammaticalEnum
{
   VERBSTRENGTH_UNKNOWN(
         "unbekannt"),
   WEAK(
         "schwach"),
   STRONG(
         "stark"),
   VERBSTRENGTH_NA(
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
      case VERBSTRENGTH_UNKNOWN:
         return "Verbstärke " + description;
      case VERBSTRENGTH_NA:
      default:
         return "";
      }
   }
   
   @Override
   public VerbStrength fromEnumName(String name)
   {
      return VerbStrength.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      switch (this)
      {
      case STRONG:
      case WEAK:
         return description;
      case VERBSTRENGTH_UNKNOWN:
      case VERBSTRENGTH_NA:
      default:
         return "";
      }
   }
}
