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
      StringJoiner joiner = new StringJoiner(", ");
      for (ExpressionKind kind : definitions.keySet())
      {
         joiner.add(definitions.get(kind).getGrammaticalEnum(Gender.class)
               .toDescription());
      }
      return joiner.toString();
   }

   public String getNumerusDescriptions()
   {
      StringJoiner joiner = new StringJoiner(", ");
      for (ExpressionKind kind : definitions.keySet())
      {
         joiner.add(definitions.get(kind).getGrammaticalEnum(Numerus.class)
               .toDescription());
      }
      return joiner.toString();
   }

   public String getBinjanDescriptions()
   {
      StringJoiner joiner = new StringJoiner(", ");
      for (ExpressionKind kind : definitions.keySet())
      {
         joiner.add(definitions.get(kind).getGrammaticalEnum(Binjan.class)
               .toDescription());
      }
      return joiner.toString();
   }

   public String getExpressionKindDescriptions()
   {
      StringJoiner joiner = new StringJoiner(", ");
      for (ExpressionKind kind : definitions.keySet())
      {
         joiner.add(kind.toDescription());
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

   public String getNumerusInfos()
   {
      StringJoiner joiner = new StringJoiner(", ");
      for (ExpressionKind kind : definitions.keySet())
      {
         joiner.add(definitions.get(kind).getGrammaticalEnum(Numerus.class)
               .toInfo());
      }
      return joiner.toString();
   }

   public String getGenderInfos()
   {
      StringJoiner joiner = new StringJoiner(", ");
      for (ExpressionKind kind : definitions.keySet())
      {
         joiner.add(
               definitions.get(kind).getGrammaticalEnum(Gender.class).toInfo());
      }
      return joiner.toString();
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
