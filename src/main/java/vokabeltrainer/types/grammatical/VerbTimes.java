package vokabeltrainer.types.grammatical;

import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.LLType;

public enum VerbTimes
      implements
      GrammaticalEnum
{
   PLEASE_CHOOSE(
         Translation.BITTE_WAEHLEN, LLType.ALL),
   VERBTIMES_UNKNOWN(
         Translation.UNBEKANNT, LLType.ALL),
   ROOT(
         Translation.WURZEL, LLType.HEBREW_ONLY),
   CONSTRUCTION(
         Translation.KONSTRUKTION, LLType.HEBREW_ONLY),
   INFINITIVE(
         Translation.INFINITIV, LLType.ALL),
   PAST(
         Translation.VERGANGENHEIT, LLType.ALL_BUT_GERMAN),
   PRAETERITUM(
         Translation.PRAETERITUM, LLType.GERMAN_ONLY),
   PERFEKT(
         Translation.PERFEKT, LLType.GERMAN_ONLY),
   PLUSQUANPERFEKT(
         Translation.PLUSQUAMPERFEKT, LLType.GERMAN_ONLY),
   PRESENT(
         Translation.GEGENWART, LLType.ALL),
   FUTURE(
         Translation.ZUKUNFT, LLType.ALL_BUT_GERMAN),
   FUTUR1(
         Translation.FUTUR1, LLType.GERMAN_ONLY),
   FUTUR2(
         Translation.FUTUR2, LLType.GERMAN_ONLY),
   SUPINUM(Translation.SUPINUM, LLType.SWEDISH_ONLY),
   IMPERARTIVE(
         Translation.BEFEHLSFORM, LLType.ALL),
   VERBTIMES_NA(
         Translation.NICHT_ANWENDBAR, LLType.ALL);

   private Translation description;
   private LLType[] llType;
   private static Translator translator;

   VerbTimes(Translation description, LLType[] llType)
   {
      this.description = description;
      this.llType = llType;
   }
   
   public static void setTranslator(Translator translator)
   {
      VerbTimes.translator = translator;
   }

   @Override
   public String toString()
   {
      return translator.realisticTranslate(description);
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
      case SUPINUM:
      case PRAETERITUM:
      case PERFEKT:
      case PLUSQUANPERFEKT:
      case FUTUR1:
      case FUTUR2:
      case CONSTRUCTION:
         return translator.realisticTranslate(description);
      case VERBTIMES_UNKNOWN:
         return translator.realisticTranslate(Translation.ZEITFORM) + " "
               + translator.realisticTranslate(description);
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
      case SUPINUM:
      case PRAETERITUM:
      case PERFEKT:
      case PLUSQUANPERFEKT:
      case FUTUR1:
      case FUTUR2:
      case CONSTRUCTION:
         return translator.realisticTranslate(description);
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

   public static VerbTimes[] values(Expression expression)
   {
      LLType learningLanguageType = expression.getLL().getLltype();
      return values(learningLanguageType);
   }

   public static VerbTimes[] values(LLType learningLanguageType)
   {
      List<VerbTimes> list = new ArrayList<>();
      for (VerbTimes v : VerbTimes.values())
      {
         innerloop: for (LLType l : v.llType)
         {
            if (l == learningLanguageType)
            {
               list.add(v);
               break innerloop;
            }
         }
      }
      return list.toArray(new VerbTimes[0]);
   }
}
