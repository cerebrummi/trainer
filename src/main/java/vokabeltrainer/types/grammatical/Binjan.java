package vokabeltrainer.types.grammatical;

public enum Binjan
      implements
      GrammaticalEnum
{
   PLEASE_CHOOSE(
         "bitte wählen"),
   BINJAN_UNKNOWN(
         "unbekannt"),
   PAAL(
         "pa'al"),
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
   BINJAN_NA(
         "nicht anwendbar");

   private String description;

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
      case PIEL:
      case PUAL:
         return description;
      case BINJAN_UNKNOWN:
         return "Binjan " + description;
      case BINJAN_NA:
      default:
         return "";
      }
   }

   @Override
   public Binjan fromEnumName(String name)
   {
      return Binjan.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      switch (this)
      {
      case HIFIL:
      case HITPAEL:
      case HUFAL:
      case NIFAL:
      case PAAL:
      case PIEL:
      case PUAL:
         return description;
      case BINJAN_UNKNOWN:
      case BINJAN_NA:
      default:
         return "";
      }
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.BINJAN;
   }

   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return Binjan.BINJAN_UNKNOWN;
   }
}
