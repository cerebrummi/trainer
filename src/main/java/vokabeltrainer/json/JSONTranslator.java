package vokabeltrainer.json;

public class JSONTranslator
{

   private JSONTranslator()
   {

   }

   public static String getCommaReplacement()
   {
      return "u\00A9";
   }

   public static String getColonReplacement()
   {
      return "u\00AE";
   }
}
