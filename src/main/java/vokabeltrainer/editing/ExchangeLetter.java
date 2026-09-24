package vokabeltrainer.editing;

public enum ExchangeLetter
{
   SSIN("\uFB2B", " FB2B");

   private String unicode;
   private String code;

   ExchangeLetter(String unicode, String code)
   {
      this.unicode = unicode;
      this.code = code;
   }

   public String getUnicode()
   {
      return unicode;
   }

   public String getCode()
   {
      return code;
   }
}
