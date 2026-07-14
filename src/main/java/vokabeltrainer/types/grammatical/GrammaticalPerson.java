package vokabeltrainer.types.grammatical;

import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.LLType;

public enum GrammaticalPerson implements GrammaticalEnum
{
   PLEASE_CHOOSE(
         Translation.BITTE_WAEHLEN, LLType.ALL),
   GRAMMATICALPERSON_UNKNOWN(
         Translation.UNBEKANNT, LLType.ALL),
   ERSTE_PERSON(
         Translation._1_PERSON, LLType.ALL),
   ZWEITE_PERSON(
         Translation._2_PERSON, LLType.ALL),
   DRITTE_PERSON(
         Translation._3_PERSON, LLType.ALL),
   ALL_PERSON(Translation._1_2_3_PERSON, LLType.HEBREW_ONLY),
   GRAMMATICALPERSON_NA(
         Translation.NICHT_ANWENDBAR, LLType.ALL);

   private Translation description;
   private LLType[] llType;
   private static Translator translator;

   GrammaticalPerson(Translation description, LLType[] llType)
   {
      this.description = description;
      this.llType = llType;
   }

   public static void setTranslator(Translator translator)
   {
      GrammaticalPerson.translator = translator;
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
      case DRITTE_PERSON:
      case ZWEITE_PERSON:
      case ERSTE_PERSON:
      case ALL_PERSON:
         return translator.realisticTranslate(description);
      case GRAMMATICALPERSON_UNKNOWN:
         return translator.realisticTranslate(Translation.GRAMMATISCHE_PERSON)
               + " " + translator.realisticTranslate(description);
      case GRAMMATICALPERSON_NA:
      default:
         return "";
      }
   }

   @Override
   public GrammaticalPerson fromEnumName(String name)
   {
      return GrammaticalPerson.valueOf(name);
   }

   @Override
   public String toInfo()
   {
      switch (this)
      {
      case DRITTE_PERSON:
      case ZWEITE_PERSON:
      case ERSTE_PERSON:
      case ALL_PERSON:
         return translator.realisticTranslate(description);
      case GRAMMATICALPERSON_UNKNOWN:
      case GRAMMATICALPERSON_NA:
      default:
         return "";
      }
   }

   @Override
   public GrammaticalParentEnum getParent()
   {
      return GrammaticalParentEnum.GRAMMATICAL_PERSON;
   }

   @Override
   public int getPrintOrderNumber()
   {
      return getParent().getSortNumber();
   }

   @Override
   public GrammaticalEnum getUnkown()
   {
      return GrammaticalPerson.GRAMMATICALPERSON_UNKNOWN;
   }

   public static GrammaticalPerson[] values(Expression expression)
   {
      LLType learningLanguageType = expression.getLL().getLltype();
      return values(learningLanguageType);
   }

   public static GrammaticalPerson[] values(LLType learningLanguageType)
   {
      List<GrammaticalPerson> list = new ArrayList<>();
      for (GrammaticalPerson g : GrammaticalPerson.values())
      {
         innerloop: for (LLType l : g.llType)
         {
            if (l == learningLanguageType)
            {
               list.add(g);
               break innerloop;
            }
         }
      }
      return list.toArray(new GrammaticalPerson[0]);
   }
}
