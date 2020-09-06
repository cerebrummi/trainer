package vokabeltrainer.editing;

import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

public enum SignLetter
{
   QUESTION_MARK(
         " 003f", "\u003f"
   ), // ?
   EXCLAMATION_MARK(
         " 0021", "\u0021"
   ), // !
   FULL_STOP(
         " 002e", "\u002e"
   ), // .
   APOSTROPHE(
         " 0027", "\u0027"
   ), // '
   LEFT_PARENTHESIS(
         " 0028", "\u0028"
   ), // (
   RIGHT_PARENTHESIS(
         " 0029", "\u0029"
   ), // )
   COMMA(
         " 002c", "\u002c"
   ), // ,
   HYPHEN_MINUS(
         " 002d", "\u002d"
   ), // -
   QUOTATION_MARK(
         " 0022", "\u005c\u0022"
   ), // "
   SECTION_SIGN(
         " 00A7", "\u00A7"
   ), // §
   DOLLAR_SIGN(
         " 0024", "\u0024"
   ), // $
   EURO_SIGN(
         " 20AC", "\u20AC"
   ), // €
   PERCENT_SIGN(
         " 0025", "\u0025"
   ), // %
   SOLIDUS(
         " 002F", "\u002F"
   ), // /
   LEFT_SQUARE_BRACKET(
         " 005B", "\u005B"
   ), // [
   RIGHT_SQUARE_BRACKET(
         " 005D", "\u005D"
   ), // ]
   LEFT_CURLY_BRACKET(
         " 007B", "\u007B"
   ), // {
   RIGHT_CURLY_BRACKET(
         " 007D", "\u007D"
   ), // }
   EQUALS_SIGN(
         " 003D", "\u003D"
   ), // =
   REVERSE_SOLIDUS(
         " 005C", "\\u005C"
   ), // \
   ASTERISK(
         " 002A", "\u002A"
   ), // *
   PLUS_SIGN(
         " 002B", "\u002B"
   ), // +
   NUMBER_SIGN(
         " 0023", "\u0023"
   ), // #
   SEMICOLON(
         " 003B", "\u003B"
   ), // ;
   COLON(
         " 003A", "\u003A"
   ), // :
   LOW_LINE(
         " 005F", "\u005F"
   ); // _

   private String code;
   private String unicode;

   private static SignLetter[] germanSigns = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP, APOSTROPHE, LEFT_PARENTHESIS, RIGHT_PARENTHESIS,
         HYPHEN_MINUS };
   private static SignLetter[] germanSignsWithComma = { QUESTION_MARK,
         EXCLAMATION_MARK, FULL_STOP, APOSTROPHE, LEFT_PARENTHESIS,
         RIGHT_PARENTHESIS, HYPHEN_MINUS, COMMA };

   private static SignLetter[] hebrewSigns = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP };
   private static SignLetter[] hebrewSignsWithComma = { QUESTION_MARK,
         EXCLAMATION_MARK, FULL_STOP, COMMA };

   private static SignLetter[] extraInformationSigns = { QUESTION_MARK,
         EXCLAMATION_MARK, FULL_STOP, APOSTROPHE, LEFT_PARENTHESIS,
         RIGHT_PARENTHESIS, HYPHEN_MINUS, COMMA, QUOTATION_MARK, SECTION_SIGN,
         DOLLAR_SIGN, EURO_SIGN, PERCENT_SIGN, SOLIDUS, LEFT_SQUARE_BRACKET,
         RIGHT_SQUARE_BRACKET, LEFT_CURLY_BRACKET, RIGHT_CURLY_BRACKET,
         EQUALS_SIGN, REVERSE_SOLIDUS, ASTERISK, PLUS_SIGN, NUMBER_SIGN,
         SEMICOLON, COLON, LOW_LINE };

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

   public static String getPatternStringExtraInformation()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : extraInformationSigns)
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
