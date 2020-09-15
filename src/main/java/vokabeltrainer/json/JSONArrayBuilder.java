package vokabeltrainer.json;

import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

public class JSONArrayBuilder
{
   private StringJoiner json;

   public JSONArrayBuilder(String key, List<String> list)
   {
      json = new StringJoiner(",");
      for (String value : list)
      {
         json.add(new StringBuilder().append("{\"").append(key).append("\":\"")
               .append(value).append("\"}"));
      }
   }

   public JSONArrayBuilder(String key, Collection<? extends JSONObjectProducer> set)
   {
      json = new StringJoiner(",");
      for (JSONObjectProducer producer : set)
      {
         json.add(new StringBuilder().append("{\"").append(key).append("\":\"")
               .append(producer.getJSONObject().getJSON()).append("\"}"));
      }
   }

   public String build()
   {
      return new StringBuilder().append("[").append(json).append("]")
            .toString();
   }

}
