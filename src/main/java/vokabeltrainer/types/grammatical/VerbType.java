package vokabeltrainer.types.grammatical;

public enum VerbType implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         "bitte wählen"),
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
   private boolean selected;
   private GrammaticalParentEnum parent = GrammaticalParentEnum.VERB_TYPE;

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
   public VerbType fromEnumName(String name)
   {
      return VerbType.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      switch (this)
      {
      case AUXILIARY:
      case IRREGULAR:
      case REGULAR:
         return description;
      case VERBTYPE_UNKNOWN:
      case VERBTYPE_NA:
      default:
         return "";
      }
   }

   @Override
   public void toggleSelected()
   {
      selected = !selected;  
   }
   
   @Override
   public boolean isSelected()
   {
      return selected;
   }

   @Override
   public void setSelected(boolean selected)
   {
      this.selected = selected;
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return parent;
   }
}
