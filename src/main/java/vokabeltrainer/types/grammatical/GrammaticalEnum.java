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
}
