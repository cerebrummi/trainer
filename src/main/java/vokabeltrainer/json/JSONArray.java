package vokabeltrainer.json;

import java.util.List;

public class JSONArray
{
   public void read(String json, List<String> result)
   {
      json = json.substring(1, json.length() - 2);
      String[] keyValueArray = json.split(JSONTranslator.getCommaReplacement());
      for (String keyValue : keyValueArray)
      {
         String value = keyValue.split(JSONTranslator.getColonReplacement())[1];
         result.add(value.substring(1, value.length() - 2));
      }
   }
}
