package vokabeltrainer.types.grammatical.expressionkind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;

public class Definitions
{
   private Set<ExpressionKind> expressionKinds = new HashSet<>();
   private Map<ExpressionKind, Definition> definitions = new HashMap<>();

   public Definitions(Map<ExpressionKind, Definition> definitions)
   {
      expressionKinds = definitions.keySet();
      this.definitions = definitions;
   }

   public Definitions()
   {

   }

   public boolean addExpressionKind(ExpressionKind expressionKind)
   {
      if (expressionKinds.add(expressionKind))
      {
         definitions.put(expressionKind, new Definition(expressionKind));
         return true;
      }
      return false;
   }

   public void setGrammaticalEnum(ExpressionKind expressionKind,
         Class<? extends GrammaticalEnum> clazz, String value)
   {
      definitions.get(expressionKind).setGrammaticalEnum(clazz, value);
   }

   public void setGrammaticalEnum(ExpressionKind expressionKind,
         GrammaticalEnum e)
   {
      definitions.get(expressionKind).setGrammaticalEnum(e);
   }

   public GrammaticalEnum getGrammaticalEnum(ExpressionKind expressionKind,
         Class<? extends GrammaticalEnum> clazz)
   {
      return definitions.get(expressionKind).grammaticalEnumMap.get(clazz);
   }

   public String getGenderDescriptions()
   {
      return grammaticalEnumToDescription(Gender.class).toString();
   }

   public String getNumerusDescriptions()
   {
      return grammaticalEnumToDescription(Numerus.class).toString();
   }

   public String getBinjanDescriptions()
   {
      return grammaticalEnumToDescription(Binjan.class).toString();
   }

   private StringJoiner grammaticalEnumToDescription(Class<? extends GrammaticalEnum> clazz)
   {
      Set<GrammaticalEnum> grammaticalEnum = new HashSet<>();
      for (ExpressionKind kind : definitions.keySet())
      {
         if (!definitions.get(kind).getGrammaticalEnum(clazz)
               .toDescription().isEmpty())
         {
            grammaticalEnum.add(definitions.get(kind).getGrammaticalEnum(clazz));
         }
      }
      
      StringJoiner joiner = new StringJoiner(", ");
      for(GrammaticalEnum grammaticalenum : grammaticalEnum)
      {
         joiner.add(grammaticalenum.toDescription());
      }
      return joiner;
   }

   public String getExpressionKindDescriptions()
   {
      StringJoiner joiner = new StringJoiner(", ");
      for (ExpressionKind kind : definitions.keySet())
      {
         if (!kind.toDescription().isEmpty())
         {
            joiner.add(kind.toDescription());
         }
      }
      return joiner.toString();
   }

   public String getExpressionKindsForSaving()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (ExpressionKind kind : definitions.keySet())
      {
         joiner.add(kind.name());
      }
      return joiner.toString();
   }

   public String getGrammaticalEnumsForSaving()
   {
      int counter = 0;
      Definition anyDefinition = new Definition(
            ExpressionKind.EXPRESSIONKIND_UNKNOWN);
      for (ExpressionKind kind : definitions.keySet())
      {
         if (counter > 0)
         {
            break;
         }
         anyDefinition = definitions.get(kind);
         counter++;
      }
      return anyDefinition.getGrammaticalEnumsForSaving();
   }

   public String getVerbConjugationInfos()
   {
      return grammaticalEnumToInfos(VerbTimes.class).toString();
   }
   
   public String getBinjanInfos()
   {
      return grammaticalEnumToInfos(Binjan.class).toString();
   }
   
   public String getNumerusInfos()
   {
      return grammaticalEnumToInfos(Numerus.class).toString();
   }

   public String getGenderInfos()
   {
      return grammaticalEnumToInfos(Gender.class).toString();
   }
   
   private StringJoiner grammaticalEnumToInfos(Class<? extends GrammaticalEnum> clazz)
   {
      Set<GrammaticalEnum> grammaticalEnum = new HashSet<>();
      for (ExpressionKind kind : definitions.keySet())
      {
         if (!definitions.get(kind).getGrammaticalEnum(clazz)
               .toInfo().isEmpty())
         {
            grammaticalEnum.add(definitions.get(kind).getGrammaticalEnum(clazz));
         }
      }
      
      StringJoiner joiner = new StringJoiner(", ");
      for(GrammaticalEnum grammaticalenum : grammaticalEnum)
      {
         joiner.add(grammaticalenum.toInfo());
      }
      return joiner;
   }

   public String addGrammaticalEnumsForCopy(String tag)
   {
      StringJoiner joiner = new StringJoiner(tag);
      for (Definition definition : definitions.values())
      {
         joiner.add(definition.addGrammaticalEnumsForCopy(tag));
      }
      return joiner.toString();
   }

   public Set<ExpressionKind> getExpressionKindSet()
   {
      return expressionKinds;
   }
}
