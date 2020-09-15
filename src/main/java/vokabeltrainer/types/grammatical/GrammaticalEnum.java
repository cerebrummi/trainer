package vokabeltrainer.types.grammatical;

public interface GrammaticalEnum
{
   public String toDescription();
   public GrammaticalEnum fromEnumName(String name);
   public String name();
   public String toInfo();
}
