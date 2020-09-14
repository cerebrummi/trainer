package vokabeltrainer.types.grammatical.expressionkind;

import java.awt.Component;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import vokabeltrainer.json.JSONObject;
import vokabeltrainer.types.grammatical.ExpressionKind;
import vokabeltrainer.types.grammatical.GrammaticalEnum;

public class Definitions
{
   private Set<ExpressionKind> expressionKinds = new HashSet<>();
   private Map<ExpressionKind, Definition> definitions = new HashMap<>();
   
   public Definitions()
   {
      expressionKinds.add(ExpressionKind.EXPRESSIONKIND_UNKNOWN);
      definitions.put(ExpressionKind.EXPRESSIONKIND_UNKNOWN,
            new Definition(ExpressionKind.EXPRESSIONKIND_UNKNOWN));
   }

   public boolean addExpressionKind(ExpressionKind expressionKind)
   {
      if(expressionKinds.add(expressionKind))
      {
         definitions.put(expressionKind, new Definition(expressionKind));
         return true;
      }     
      return false;
   }

   public String getGenderDescriptions()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getNumerusDescriptions()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getBinjanDescriptions()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getExpressionKindDescriptions()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getNumerusInfos()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getGenderInfos()
   {
      // TODO Auto-generated method stub
      return null;
   }
   
   public String addGrammaticalEnumsForPrint(String tag)
   {
      StringJoiner joiner = new StringJoiner(tag);
      for (Class<? extends GrammaticalEnum> clazz : definition
            .getSortedGrammaticalEnumKeys())
      {
         joiner.add(definition.getGrammaticalEnum(clazz).name());
      }
      return joiner.toString();
   }

   public JSONObject getJSONObject()
   {
      // TODO Auto-generated method stub
      return null;
   }
   
   public String addGrammaticalEnumsForCopy(String tag)
   {
      StringJoiner joiner = new StringJoiner(tag);
      for (GrammaticalEnum e : definition.getGrammaticalEnumValues())
      {
         joiner.add(e.toDescription());
      }
      return joiner.toString();
   }

   public List<DefinitionTab> getDefinitionTabs()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Set<ExpressionKind> getExpressionKindSet()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
