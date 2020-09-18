package vokabeltrainer.types.grammatical;

public enum VerbConjugation implements GrammaticalEnum
{
   VERBCONJUGATION_UNKNOWN(
         "unbekannt"),
   INFINITIVE(
         "Infinitiv"),
   PAST(
         "Vergangenheit"),
   PAST_PARTICIPLE(
         "Vergangenheit-Partizip"),
   PRESENT(
         "Gegenwart"),
   FUTURE(
         "Zukunft"),
   IMPERARTIVE(
         "Befehlsform"),
   ACTION_NOUN(
         "Gerundium"),
   VERBCONJUGATION_NA(
         "nicht anwendbar");

   private String description;
   private boolean selected;
   private int sortNumber = 50;
   private GrammaticalParentEnum parent = GrammaticalParentEnum.VERB_CONJUGATION;

   VerbConjugation(String description)
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
      case ACTION_NOUN:
      case FUTURE:
      case IMPERARTIVE:
      case INFINITIVE:
      case PAST:
      case PAST_PARTICIPLE:
      case PRESENT:
         return description;
      case VERBCONJUGATION_UNKNOWN:
         return "Verbconjugation " + description;
      case VERBCONJUGATION_NA:
      default:
         return "";
      }
   }
   
   @Override
   public VerbConjugation fromEnumName(String name)
   {
      return VerbConjugation.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      switch (this)
      {
      case ACTION_NOUN:
      case FUTURE:
      case IMPERARTIVE:
      case INFINITIVE:
      case PAST:
      case PAST_PARTICIPLE:
      case PRESENT:
         return description;
      case VERBCONJUGATION_UNKNOWN:
      case VERBCONJUGATION_NA:
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
