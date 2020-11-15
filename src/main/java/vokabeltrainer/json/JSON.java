package vokabeltrainer.json;

import java.util.Collection;
import java.util.List;

public class JSON
{
   private JSON()
   {
   }

   public static JSONObjectBuilder createObjectBuilder()
   {
      return new JSONObjectBuilder();
   }

   public static JSONArrayBuilder createArrayBuilder(String key, List<String> list)
   {
      return new JSONArrayBuilder(key, list);
   }
   
   public static JSONArrayBuilder createArrayBuilder(String key, Collection<? extends JSONObjectProducer> collection)
   {
      return new JSONArrayBuilder(key, collection);
   }
}
