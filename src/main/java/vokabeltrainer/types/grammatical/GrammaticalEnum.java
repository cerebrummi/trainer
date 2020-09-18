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
   public int getSortNumber();
   public GrammaticalParentEnum getParent();
   
   public enum GrammaticalParentEnum
   {
      BINJAN,
      GENDER,
      GRAMMATICAL_PERSON,
      NUMERUS,
      VERB_CONJUGATION,
      VERB_STRENGTH,
      VERB_TYPE;
   }
}
