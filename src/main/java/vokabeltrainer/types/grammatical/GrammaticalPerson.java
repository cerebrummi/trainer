package vokabeltrainer.types.grammatical;

public enum GrammaticalPerson implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         "bitte wählen"),
   GRAMMATICALPERSON_UNKNOWN(
         "unbekannt"),
   ERSTE_PERSON(
         "1. Person"),
   ZWEITE_PERSON(
         "2. Person"),
   DRITTE_PERSON(
         "3. Person"),
   GRAMMATICALPERSON_NA(
         "nicht anwendbar");

   private String description;
   private boolean selected;
   private GrammaticalParentEnum parent = GrammaticalParentEnum.GRAMMATICAL_PERSON;

   GrammaticalPerson(String description)
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
      case DRITTE_PERSON:
      case ZWEITE_PERSON:
      case ERSTE_PERSON:
         return description;
      case GRAMMATICALPERSON_UNKNOWN:
         return "Grammatische-Person " + description;
      case GRAMMATICALPERSON_NA:
      default:
         return "";
      }
   }
   
   @Override
   public GrammaticalPerson fromEnumName(String name)
   {
      return GrammaticalPerson.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      switch (this)
      {
      case DRITTE_PERSON:
      case ZWEITE_PERSON:
      case ERSTE_PERSON:
         return description;
      case GRAMMATICALPERSON_UNKNOWN:
      case GRAMMATICALPERSON_NA:
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
   
   @Override
   public int getPrintOrderNumber()
   {
      return parent.getSortNumber();
   }
}
