package vokabeltrainer.json;

import java.util.HashMap;
import java.util.Map;

public class JSONObject
{
   private String json = "";
   private Map<String, String> attributeMap = new HashMap<>();

   public JSONObject()
   {

   }

   public JSONObject(String json)
   {
      json = json.substring(1, json.length() - 2);
      String[] attributes = json.split(",");
      for (String attribute : attributes)
      {
         String key = attribute.split(":")[0];
         String value = attribute.split(":")[1];
         attributeMap.put(key.substring(1, key.length() - 2),
               value.substring(1, value.length() - 2));
      }
   }
   
   public String getAttribute(String attribute)
   {
      return attributeMap.get(attribute);
   }

   public void addJSON(String json)
   {
      this.json = json;
   }

   public String getJSON()
   {
      return json;
   }
}
