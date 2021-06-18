package vokabeltrainer.types.grammatical.expressionkind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;

public class Definition
{
   private ExpressionKind expressionKind;
   protected Map<Class<? extends GrammaticalEnum>, GrammaticalEnum> grammaticalEnumMap = new HashMap<>();

   public Definition(ExpressionKind expressionKind)
   {
      this.expressionKind = expressionKind;
      fillGrammaticalEnumMap();
   }

   private void fillGrammaticalEnumMap()
   {
      for (GrammaticalEnum ge : DefinitionHelper
            .getDefinitionsNA(expressionKind))
      {
         grammaticalEnumMap.put(ge.getClass(), ge);
      }

      for (GrammaticalEnum ge : DefinitionHelper
            .getDefinitionsUNKNOWN(expressionKind))
      {
         grammaticalEnumMap.put(ge.getClass(), ge);
      }
   }

   public ExpressionKind getExpressionKind()
   {
      return expressionKind;
   }

   public void setExpressionKind(ExpressionKind expressionKind)
   {
      this.expressionKind = expressionKind;
   }

   public void setGrammaticalEnum(Class<? extends GrammaticalEnum> clazz,
         String value)
   {
      GrammaticalEnum e = (GrammaticalEnum) grammaticalEnumMap.get(clazz);
      grammaticalEnumMap.put(clazz, e.fromEnumName(value));
   }

   public void setGrammaticalEnum(GrammaticalEnum e)
   {
      grammaticalEnumMap.put(e.getClass(), e);
   }

   public GrammaticalEnum getGrammaticalEnum(
         Class<? extends GrammaticalEnum> clazz)
   {
      return grammaticalEnumMap.get(clazz);
   }

   public List<Class<? extends GrammaticalEnum>> getSortedGrammaticalEnumKeys()
   {
      List<Class<? extends GrammaticalEnum>> keyList = new ArrayList<>();
      keyList.addAll(grammaticalEnumMap.keySet());
      Collections.sort(keyList,
            new Comparator<Class<? extends GrammaticalEnum>>()
            {

               @Override
               public int compare(Class<? extends GrammaticalEnum> o1,
                     Class<? extends GrammaticalEnum> o2)
               {
                  return String.valueOf(o1)
                        .compareToIgnoreCase(String.valueOf(o2));
               }

            });
      return keyList;
   }

   public Collection<GrammaticalEnum> getGrammaticalEnumValues()
   {
      return grammaticalEnumMap.values();
   }
   
   public String addGrammaticalEnumsForCopy(String tag)
   {
      StringJoiner joiner = new StringJoiner(tag);
      for (Entry<Class<? extends GrammaticalEnum>, GrammaticalEnum> entry : grammaticalEnumMap
            .entrySet())
      {
         if(!entry.getValue().toInfo().isEmpty())
         {
            joiner.add(entry.getKey().getCanonicalName()+": "+entry.getValue().toInfo());
         }       
      }
      return joiner.toString();
   }

   public String getGrammaticalEnumsForSaving()
   {
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(grammaticalEnumMap.get(Gender.class).name());
      joiner.add(grammaticalEnumMap.get(Numerus.class).name());
      joiner.add(grammaticalEnumMap.get(GrammaticalPerson.class).name());
      joiner.add(grammaticalEnumMap.get(Binjan.class).name());
      joiner.add(grammaticalEnumMap.get(VerbTimes.class).name());
      return joiner.toString();
   }

}
