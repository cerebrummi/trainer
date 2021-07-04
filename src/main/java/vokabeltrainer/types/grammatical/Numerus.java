package vokabeltrainer.types.grammatical;

public enum Numerus implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         "bitte wählen"),
   NUMERUS_UNKNOWN(
         "unbekannt"),
   SINGULAR(
         "Singular"),
   DUAL(
         "Dual"),
   PLURAL(
         "Plural"),
   NUMERUS_NA(
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
      case NUMERUS_UNKNOWN:
         return "Numerus " + description;
      case NUMERUS_NA:
      default:
         return "";
      }
   }
   
   public String toInfo()
   {
      switch (this)
      {
      case DUAL:
      case PLURAL:
      case SINGULAR:
         return description;
      case NUMERUS_UNKNOWN:
      case NUMERUS_NA:
      default:
         return "";
      }
   }
   
   @Override
   public Numerus fromEnumName(String name)
   {
      return Numerus.valueOf(name);
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.NUMERUS;
   }
   
   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return Numerus.NUMERUS_UNKNOWN;
   }
}
