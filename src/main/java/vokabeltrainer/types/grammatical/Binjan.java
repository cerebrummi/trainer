package vokabeltrainer.types.grammatical;

import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.LLType;

public enum Binjan
      implements
      GrammaticalEnum
{
   PLEASE_CHOOSE(
         Translation.BITTE_WAEHLEN, LLType.ALL),
   BINJAN_UNKNOWN(
         Translation.UNBEKANNT, LLType.ALL),
   GRUPPE_1(Translation.GRUPPE_1, LLType.SWEDISH_ONLY),
   GRUPPE_2(Translation.GRUPPE_2, LLType.SWEDISH_ONLY),
   GRUPPE_3(Translation.GRUPPE_3, LLType.SWEDISH_ONLY),
   GRUPPE_4(Translation.GRUPPE_4, LLType.SWEDISH_ONLY),
   STARK(Translation.STARKES_VERB, LLType.GERMAN_ONLY),
   SCHWACH(Translation.SCHWACHES_VERB, LLType.GERMAN_ONLY),
   UNREGELMAESSIG(Translation.UNREGELMAESSIG_VERB, LLType.GERMAN_ONLY),
   PAAL(
         Translation.PA_AL___QAL, LLType.HEBREW_ONLY),
   NIFAL(
         Translation.NIF_AL, LLType.HEBREW_ONLY),
   HIFIL(
         Translation.HIF_IL, LLType.HEBREW_ONLY),
   HUFAL(
         Translation.HUF_AL___HOFAL, LLType.HEBREW_ONLY),
   PIEL(
         Translation.PI_EL, LLType.HEBREW_ONLY),
   PUAL(
         Translation.PU_AL, LLType.HEBREW_ONLY),
   HITPAEL(
         Translation.HITPA_EL, LLType.HEBREW_ONLY),
   BINJAN_NA(
         Translation.NICHT_ANWENDBAR, LLType.ALL);

   private Translation description;
   private LLType[] llType;

   Binjan(Translation description, LLType[] lltype)
   {
      this.description = description;
      this.llType = lltype;
   }

   @Override
   public String toString()
   {
      Translator translator = Common.getTranslator();
      return translator.realisticTranslate(description);
   }

   public String toDescription()
   {
      Translator translator = Common.getTranslator();
      switch (this)
      {
      case HIFIL:
      case HITPAEL:
      case HUFAL:
      case NIFAL:
      case PAAL:
      case PIEL:
      case PUAL:
      case GRUPPE_1:
      case GRUPPE_2:
      case GRUPPE_3:
      case GRUPPE_4:
      case STARK:
      case SCHWACH:
      case UNREGELMAESSIG:
         return translator.realisticTranslate(description);
      case BINJAN_UNKNOWN:
         return translator.realisticTranslate(Translation.BINJAN___STAMM) + " "
               + translator.realisticTranslate(description);
      case BINJAN_NA:
      default:
         return "";
      }
   }

   @Override
   public Binjan fromEnumName(String name)
   {
      return Binjan.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      Translator translator = Common.getTranslator();
      switch (this)
      {
      case HIFIL:
      case HITPAEL:
      case HUFAL:
      case NIFAL:
      case PAAL:
      case PIEL:
      case PUAL:
      case GRUPPE_1:
      case GRUPPE_2:
      case GRUPPE_3:
      case GRUPPE_4:
      case STARK:
      case SCHWACH:
      case UNREGELMAESSIG:
         return translator.realisticTranslate(description);
      case BINJAN_UNKNOWN:
      case BINJAN_NA:
      default:
         return "";
      }
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.BINJAN;
   }

   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return Binjan.BINJAN_UNKNOWN;
   }

   public static Binjan[] values(Expression expression)
   {
      LLType learningLanguageType = expression.getLL().getLltype();
      return values(learningLanguageType);
   }

   public static Binjan[] values(LLType learningLanguageType)
   {
      List<Binjan> list = new ArrayList<>();
      for (Binjan b : Binjan.values())
      {
         innerloop: for (LLType l : b.llType)
         {
            if (l == learningLanguageType)
            {
               list.add(b);
               break innerloop;
            }
         }
      }
      return list.toArray(new Binjan[0]);
   }
}
