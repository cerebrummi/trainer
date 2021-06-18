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
   
   public enum GrammaticalParentEnum
   {
      GENDER(10),
      NUMERUS(20),
      GRAMMATICAL_PERSON(30),
      BINJAN(40),
      VERB_TIMES(50);
      
      private int sortNumber;
      
      GrammaticalParentEnum(int sortNumber)
      {
         this.sortNumber = sortNumber;
      }

      public int getSortNumber()
      {
         return sortNumber;
      }
   }
}
