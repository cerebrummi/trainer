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
   private boolean selected;
   private int sortNumber = 60;
   private GrammaticalParentEnum parent = GrammaticalParentEnum.VERB_STRENGTH;

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

   @Override
   public void toggleSelected()
   {
      selected = !selected;  
   }
   
   public boolean isSelected()
   {
      return selected;
   }

   public void setSelected(boolean selected)
   {
      this.selected = selected;
   }

   public int getSortNumber()
   {
      return sortNumber;
   }

   public GrammaticalParentEnum getParent()
   {
      return parent;
   }
}
