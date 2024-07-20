package vokabeltrainer.types.grammatical;

import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.LLType;

public enum Gender implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         Translation.BITTE_WAEHLEN, LLType.ALL),
   GENDER_UNKNOWN(
         Translation.UNBEKANNT, LLType.ALL),
   FEMALE(
         Translation.FEMININ, LLType.HEBREW_ONLY),
   MALE(
         Translation.MASKULIN, LLType.HEBREW_ONLY),
   BOTH_FEMALE_MALE(
         Translation.FEMININ_UND_MASKULIN, LLType.HEBREW_ONLY),
   EN(Translation.EN, LLType.SWEDISH_ONLY),
   ETT(Translation.ETT, LLType.SWEDISH_ONLY),
   GENDER_NA(
         Translation.NICHT_ANWENDBAR, LLType.ALL);

   private Translation description;
   private LLType[] llType;

   Gender(Translation description, LLType[] llType)
   {
      this.description = description;
      this.llType = llType;
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
      case BOTH_FEMALE_MALE:
      case FEMALE:
      case MALE:
      case EN:
      case ETT:
         return translator.realisticTranslate(description);
      case GENDER_UNKNOWN:
         return translator.realisticTranslate(Translation.GESCHLECHT)
               + " " + translator.realisticTranslate(description);
      case GENDER_NA:
      default:
         return "";
      }
   }
   
   public String toInfo()
   {
      Translator translator = Common.getTranslator();
      switch (this)
      {
      case BOTH_FEMALE_MALE:
      case FEMALE:
      case MALE:
      case EN:
      case ETT:
         return translator.realisticTranslate(description);
      case GENDER_UNKNOWN:
      case GENDER_NA:
      default:
         return "";
      }
   }
   
   @Override
   public Gender fromEnumName(String name)
   {
      return Gender.valueOf(name);
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.GENDER;
   }
   
   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return Gender.GENDER_UNKNOWN;
   }
   
   public static Gender[] values(Expression expression)
   {
      LLType learningLanguageType = expression.getLL().getLltype();
      return values(learningLanguageType);  
   }

   public static Gender[] values(LLType learningLanguageType)
   {
      List<Gender> list = new ArrayList<>();
      for(Gender g: Gender.values())
      {
         innerloop:
            for(LLType l : g.llType)
            {
               if(l == learningLanguageType)
               {
                  list.add(g);
                  break innerloop;
               }
            }
      }
      return list.toArray(new Gender[0]);
   }
}
