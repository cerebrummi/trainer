package vokabeltrainer.types.grammatical;

public enum Numerus implements GrammaticalEnum
{
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
   private boolean selected;

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
}
