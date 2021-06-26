package vokabeltrainer.types.grammatical;

public interface GrammaticalEnum
{
   public String toDescription();
   public GrammaticalEnum fromEnumName(String name);
   public String name();
   public String toInfo();
   public void toggleSelected();
   public boolean isSelected();
   public void setSelected(boolean selected);
   public GrammaticalParentEnum getParent();
   public int getPrintOrderNumber();
   
   public enum GrammaticalParentEnum
   {
      GENDER(10, "Geschlecht"),
      NUMERUS(20, "Numerus"),
      GRAMMATICAL_PERSON(30, "grammatische Person"),
      BINJAN(40, "Binjan"),
      VERB_TIMES(50, "Zeit");
      
      private int sortNumber;
      private String identifier;
      
      GrammaticalParentEnum(int sortNumber, String identifier)
      {
         this.sortNumber = sortNumber;
         this.identifier = identifier;
      }

      public int getSortNumber()
      {
         return sortNumber;
      }

      public String getIdentifier()
      {
         return identifier;
      }
   }
}
