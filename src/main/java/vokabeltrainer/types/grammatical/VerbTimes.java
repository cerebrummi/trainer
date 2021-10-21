package vokabeltrainer.types.grammatical;

public enum VerbTimes
      implements
      GrammaticalEnum
{
   PLEASE_CHOOSE(
         "bitte wählen"),
   VERBTIMES_UNKNOWN(
         "unbekannt"),
   ROOT(
         "Wurzel"),
   INFINITIVE(
         "Infinitiv"),
   PAST(
         "Vergangenheit"),
   PRESENT(
         "Gegenwart"),
   FUTURE(
         "Zukunft"),
   IMPERARTIVE(
         "Befehlsform"),
   VERBTIMES_NA(
         "nicht anwendbar");

   private String description;

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
      case FUTURE:
      case IMPERARTIVE:
      case INFINITIVE:
      case PAST:
      case PRESENT:
      case ROOT:
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
      case FUTURE:
      case IMPERARTIVE:
      case INFINITIVE:
      case PAST:
      case PRESENT:
      case ROOT:
         return description;
      case VERBTIMES_UNKNOWN:
      case VERBTIMES_NA:
      default:
         return "";
      }
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.VERB_TIMES;
   }

   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return VerbTimes.VERBTIMES_UNKNOWN;
   }
}
