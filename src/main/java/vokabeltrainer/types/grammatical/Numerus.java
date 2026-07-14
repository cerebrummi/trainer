package vokabeltrainer.types.grammatical;

import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.LLType;


public enum Numerus implements GrammaticalEnum
{      
   PLEASE_CHOOSE(Translation.BITTE_WAEHLEN, LLType.ALL), 
   NUMERUS_UNKNOWN(Translation.UNBEKANNT, LLType.ALL), 
   SINGULAR(Translation.SINGULAR, LLType.ALL), 
   SINGULAR_SPECIFIC(Translation.SINGULAR_SPECIFIC, LLType.SWEDISH_ONLY),
   SINGULAR_INDEFINITE(Translation.SINGULAR_INDEFINITE, LLType.SWEDISH_ONLY),
   SINGULAR_PLURAL(Translation.SINGULAR_PLURAL, LLType.SWEDISH_ONLY),
   DUAL(Translation.DUAL, LLType.HEBREW_ONLY),
   PLURAL_SPECIFIC(Translation.PLURAL_SPECIFIC, LLType.SWEDISH_ONLY),
   PLURAL_INDEFINITE(Translation.PLURAL_INDEFINITE, LLType.SWEDISH_ONLY),
   PLURAL(Translation.PLURAL, LLType.ALL),
   BASE(Translation.GRUNDFORM, LLType.SWEDISH_ONLY), 
   T_FORM(Translation.T_FORM, LLType.SWEDISH_ONLY), 
   NUMERUS_NA(Translation.NICHT_ANWENDBAR, LLType.ALL);

   private Translation description;
   private LLType[] llType;
   private static Translator translator;

   Numerus(Translation description, LLType[] llType)
   {
      this.description = description;
      this.llType = llType;
   }
   
   public static void setTranslator(Translator translator)
   {
      Numerus.translator = translator;
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
      case DUAL:
      case PLURAL:
      case SINGULAR:
      case BASE:
      case T_FORM:
      case SINGULAR_SPECIFIC:
      case SINGULAR_INDEFINITE:
      case SINGULAR_PLURAL:
      case PLURAL_SPECIFIC:
      case PLURAL_INDEFINITE:
         return translator.realisticTranslate(description);
      case NUMERUS_UNKNOWN:
         return translator.realisticTranslate(Translation.NUMERUS) + " "
               + translator.realisticTranslate(description);
      case NUMERUS_NA:
      default:
         return "";
      }
   }

   public String toInfo()
   {
      switch (this)
      {
      case DUAL:
      case PLURAL:
      case SINGULAR:
      case BASE:
      case T_FORM:
      case SINGULAR_SPECIFIC:
      case SINGULAR_INDEFINITE:
      case SINGULAR_PLURAL:
      case PLURAL_SPECIFIC:
      case PLURAL_INDEFINITE:
         return translator.realisticTranslate(description);
      case NUMERUS_UNKNOWN:
      case NUMERUS_NA:
      default:
         return "";
      }
   }

   @Override
   public Numerus fromEnumName(String name)
   {
      return Numerus.valueOf(name);
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.NUMERUS;
   }

   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return Numerus.NUMERUS_UNKNOWN;
   }

   public static Numerus[] values(Expression expression)
   {
      LLType learningLanguageType = expression.getLL().getLltype();
      return values(learningLanguageType);
   }

   public static Numerus[] values(LLType learningLanguageType)
   {
      List<Numerus> list = new ArrayList<>();
      for (Numerus n : Numerus.values())
      {
         innerloop: for (LLType l : n.llType)
         {
            if (l == learningLanguageType)
            {
               list.add(n);
               break innerloop;
            }
         }
      }
      return list.toArray(new Numerus[0]);
   }
}
