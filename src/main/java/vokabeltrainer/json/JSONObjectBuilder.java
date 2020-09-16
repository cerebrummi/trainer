package vokabeltrainer.json;

import java.util.StringJoiner;

public class JSONObjectBuilder
{
   private StringJoiner json = new StringJoiner(
         JSONTranslator.getCommaReplacement());

   public JSONObjectBuilder add(String key, CharSequence value)
   {
      json.add(new StringBuilder().append("\"").append(key).append("\"")
            .append(JSONTranslator.getColonReplacement()).append("\"")
            .append(value).append("\""));
      return this;
   }

   public JSONObjectBuilder add(String key, String value)
   {
      json.add(new StringBuilder().append("\"").append(key).append("\"")
            .append(JSONTranslator.getColonReplacement()).append("\"")
            .append(value).append("\""));
      return this;
   }

   public JSONObjectBuilder add(String key, int value)
   {
      json.add(new StringBuilder().append("\"").append(key).append("\"")
            .append(JSONTranslator.getColonReplacement()).append("\"")
            .append(value).append("\""));
      return this;
   }

   public String build()
   {
      return new StringBuilder().append("{").append(json).append("}")
            .toString();
   }

}
