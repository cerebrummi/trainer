package vokabeltrainer.types.grammatical;

public enum VerbTimes implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         "bitte wählen"),
   VERBTIMES_UNKNOWN(
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
   VERBTIMES_NA(
         "nicht anwendbar");

   private String description;
   private boolean selected;
   private GrammaticalParentEnum parent = GrammaticalParentEnum.VERB_TIMES;

   VerbTimes(String description)
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
      case VERBTIMES_UNKNOWN:
         return "Verbconjugation " + description;
      case VERBTIMES_NA:
      default:
         return "";
      }
   }
   
   @Override
   public VerbTimes fromEnumName(String name)
   {
      return VerbTimes.valueOf(name);
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
      case VERBTIMES_UNKNOWN:
      case VERBTIMES_NA:
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
}
