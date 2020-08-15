package vokabeltrainer.editing;

import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

public enum SignLetter
{
   QUESTIONMARK(" 003f", "\u003f"),    // ?
   EXCLAMATIONMARK(" 0021", "\u0021"), // !
   POINT(" 002e", "\u002e"),           // .
   APOSTROPH(" 0027", "\u0027"),       // '
   BRAKET_OPEN(" 0028", "\u0028"),     // (
   BRAKET_CLOSE(" 0029", "\u0029"),    // )
   COMMA(" 002c", "\u002c"),           // ,
   HYPHEN(" 002d","\u002d");           // -
   
   private String code;
   private String unicode;
   
   private static SignLetter[] germanSigns = {QUESTIONMARK, EXCLAMATIONMARK,
         POINT, APOSTROPH, BRAKET_OPEN, BRAKET_CLOSE, HYPHEN};
   private static SignLetter[] germanSignsWithComma = {QUESTIONMARK, EXCLAMATIONMARK,
         POINT, APOSTROPH, BRAKET_OPEN, BRAKET_CLOSE, HYPHEN, COMMA};
   
   private static SignLetter[] hebrewSigns = { QUESTIONMARK, EXCLAMATIONMARK,
         POINT };
   private static SignLetter[] hebrewSignsWithComma = { QUESTIONMARK,
         EXCLAMATIONMARK, POINT, COMMA };

   SignLetter(String code, String unicode)
   {
      this.code = code;
      this.unicode = unicode;
   }
   
   public static String getPatternStringGerman()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : germanSigns)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }

   public static String getPatternStringGermanWithComma()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : germanSignsWithComma)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }
   
   public static String getPatternStringHebrew()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : hebrewSigns)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }

   public static String getPatternStringHebrewWithComma()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : hebrewSignsWithComma)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }

   public String getCode()
   {
      return code;
   }

   public String getUnicode()
   {
      return unicode;
   }

   static SignLetter getLetterFromCode(String code)
   {
      for (SignLetter letter : SignLetter.values())
      {
         if (StringUtils.containsIgnoreCase(letter.getCode(), code))
         {
            return letter;
         }
      }
      return null;
   }

}
