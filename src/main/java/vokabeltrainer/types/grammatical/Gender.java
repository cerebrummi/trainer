package vokabeltrainer.types.grammatical;

public enum Gender implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         "bitte wählen"),
   GENDER_UNKNOWN(
         "unbekannt"),
   FEMALE(
         "weiblich"),
   MALE(
         "männlich"),
   BOTH_FEMALE_MALE(
         "weiblich und männlich"),
   GENDER_NA(
         "nicht anwendbar");

   private String description;
   private boolean selected;
   private GrammaticalParentEnum parent = GrammaticalParentEnum.GENDER;

   Gender(String description)
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
      case BOTH_FEMALE_MALE:
      case FEMALE:
      case MALE:
         return description;
      case GENDER_UNKNOWN:
         return "Geschlecht " + description;
      case GENDER_NA:
      default:
         return "";
      }
   }
   
   public String toInfo()
   {
      switch (this)
      {
      case BOTH_FEMALE_MALE:
      case FEMALE:
      case MALE:
         return description;
      case GENDER_UNKNOWN:
      case GENDER_NA:
      default:
         return "";
      }
   }
   
   @Override
   public Gender fromEnumName(String name)
   {
      return Gender.valueOf(name);
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
   
   @Override
   public int getPrintOrderNumber()
   {
      return parent.getSortNumber();
   }
}
