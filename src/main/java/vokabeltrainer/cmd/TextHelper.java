package vokabeltrainer.cmd;

public class TextHelper
{
   private TextHelper()
   {
      // nothing
   }

   static public String cleanText(String text)
   {
      return text.replaceAll("\\t", " ").replaceAll("\\n", " ")
            .replaceAll("\\r", " ").strip();
   }
}
