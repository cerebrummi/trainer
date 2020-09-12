package vokabeltrainer.types.grammatical;

public enum Binjan implements GrammaticalEnum
{
   UNKOWN(
         "unbekannt"),
   PAAL(
         "pa'al"),
   PAAL_HOHL(
         "pa'al hohl"),
   PAAL_SCHWACH(
         "pa'al schwach"),
   PIEL(
         "pi'el"),
   HIFIL(
         "hif'il"),
   HITPAEL(
         "hitpa'el"),
   HUFAL(
         "huf'al"),
   PUAL(
         "pu'al"),
   NIFAL(
         "nif'al"),
   NA(
         "nicht anwendbar");

   String description;

   Binjan(String description)
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
      case HIFIL:
      case HITPAEL:
      case HUFAL:
      case NIFAL:
      case PAAL:
      case PAAL_HOHL:
      case PAAL_SCHWACH:
      case PIEL:
      case PUAL:
         return description;
      case UNKOWN:
         return "Binjan " + description;
      case NA:
      default:
         return "";
      }
   }

   @Override
   public Binjan fromEnumName(String name)
   {
      return Binjan.valueOf(name);  
   }
}
