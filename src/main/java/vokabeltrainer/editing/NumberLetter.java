package vokabeltrainer.editing;

import java.util.StringJoiner;

public enum NumberLetter implements Letter
{
   ZERO(" 0030_##","\u0030"),
   ONE(" 0031_##","\u0031"),
   TWO(" 0032_##","\u0032"),
   THREE(" 0033_##","\u0033"),
   FOUR(" 0034_##","\u0034"),
   FIVE(" 0035_##","\u0035"),
   SIX(" 0036_##","\u0036"),
   SEVEN(" 0037_##","\u0037"),
   EIGHT(" 0038_##","\u0038"),
   NINE(" 0039_##","\u0039");
   
   private String code;
   private String unicode;

   NumberLetter(String code, String unicode)
   {
      this.code = code.toUpperCase();
      this.unicode = unicode.toUpperCase();
   }

   public static String getPatternString()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (NumberLetter letter : NumberLetter.values())
      {
         joiner.add(letter.code);
      }

      return joiner.toString();
   }

   @Override
   public String getCode()
   {
      return code;
   }

   @Override
   public String getUnicode()
   {
      return unicode;
   }

   @Override
   public boolean isNewspace()
   {
      return false;
   }

   @Override
   public boolean isSpace()
   {
      return false;
   }

   @Override
   public LetterType isType()
   {
      return LetterType.NUMBER;
   }

   @Override
   public Letter getNewspace()
   {
      return null;
   }

   @Override
   public int getPixelWidth()
   {
      return -1;
   }

   @Override
   public String getTranscript()
   {
      return name().toLowerCase();
   }
}
