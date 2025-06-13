package vokabeltrainer.editing;

import java.util.StringJoiner;

public enum SignLetter implements Letter
{
   TAB(" 0009_","\u0009"),
   QUESTION_MARK(" 003F_**", "\u003F"),            // ?
   EXCLAMATION_MARK(" 0021_**", "\u0021"),         // !
   FULL_STOP(" 002E_**", "\u002E"),                // .
   APOSTROPHE(" 0027_**", "\u0027"),               // '
   LEFT_PARENTHESIS(" 0028_**", "\u0028"),         // (
   RIGHT_PARENTHESIS(" 0029_**", "\u0029"),        // )
   COMMA(" 002c_**", "\u002c"),                    // ,
   HYPHEN_MINUS(" 002d_**", "\u002d"),             // -
   QUOTATION_MARK(" 0022_**", "\u005c\u0022"),     // "
   SECTION_SIGN(" 00A7_**", "\u00A7"),             // �
   DOLLAR_SIGN(" 0024_**", "\u0024"),              // $
   EURO_SIGN(" 20AC_**", "\u20AC"),                // �
   PERCENT_SIGN(" 0025_**", "\u0025"),             // %
   SOLIDUS(" 002F_**", "\u002F"),                  // /
   LEFT_SQUARE_BRACKET(" 005B_**", "\u005B"),      // [
   RIGHT_SQUARE_BRACKET(" 005D_**", "\u005D"),     // ]
   LEFT_CURLY_BRACKET(" 007B_**", "\u007B"),       // {
   RIGHT_CURLY_BRACKET(" 007D_**", "\u007D"),      // }
   EQUALS_SIGN(" 003D_**", "\u003D"),              // =
   REVERSE_SOLIDUS(" 005C_**", "\\u005C"),         // \ causes problems, do not allow anywhere
   ASTERISK(" 002A_**", "\u002A"),                 // *
   PLUS_SIGN(" 002B_**", "\u002B"),                // +
   NUMBER_SIGN(" 0023_**", "\u0023"),              // #
   SEMICOLON(" 003B_**", "\u003B"),                // ;
   COLON(" 003A_**", "\u003A"),                    // :
   LOW_LINE(" 005F_**", "\u005F"),                 // _
   DEGREE_SIGN(" 00B0_**", "\u00B0"),               // °
   CIRCUMFLEX_ACCENT(" 005E_**", "\u005E"),         // ^ does not work properly, known Swing bug
   ACUTE_ACCENT(" 00B4_**", "\u00B4"),              // ´ does not work properly, known Swing bug
   GRAVE_ACCENT(" 0060_**", "\u0060"),              // ` does not work properly, known Swing bug
   COMMERCIAL_AT(" 0040_**", "\u0040"),             // @
   MICRO_SIGN(" 00B5_**", "\u00B5"),                // �
   LESS_THAN_SIGN(" 003C_**", "\u003C"),            // <
   GREATER_THAN_SIGN(" 003E_**", "\u003E"),         // >
   VERTICAL_LINE(" 007C_**", "\u007C"),             // |
   AMPERSAND(" 0026_**","\u0026");                  // &

   private String code;
   private String unicode;

   private static SignLetter[] germanSigns = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP, APOSTROPHE, LEFT_PARENTHESIS, RIGHT_PARENTHESIS,
         HYPHEN_MINUS, QUOTATION_MARK, SECTION_SIGN, DOLLAR_SIGN, EURO_SIGN,
         PERCENT_SIGN, SOLIDUS, LEFT_SQUARE_BRACKET, RIGHT_SQUARE_BRACKET,
         LEFT_CURLY_BRACKET, RIGHT_CURLY_BRACKET, EQUALS_SIGN, ASTERISK,
         PLUS_SIGN, NUMBER_SIGN, SEMICOLON, COLON, LOW_LINE, DEGREE_SIGN,
         COMMERCIAL_AT, MICRO_SIGN,
         LESS_THAN_SIGN, GREATER_THAN_SIGN, VERTICAL_LINE, AMPERSAND,
         CIRCUMFLEX_ACCENT, ACUTE_ACCENT, GRAVE_ACCENT};
   
   private static SignLetter[] germanSignsWithComma = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP, APOSTROPHE, LEFT_PARENTHESIS, RIGHT_PARENTHESIS,
         HYPHEN_MINUS, QUOTATION_MARK, SECTION_SIGN, DOLLAR_SIGN, EURO_SIGN,
         PERCENT_SIGN, SOLIDUS, LEFT_SQUARE_BRACKET, RIGHT_SQUARE_BRACKET,
         LEFT_CURLY_BRACKET, RIGHT_CURLY_BRACKET, EQUALS_SIGN, ASTERISK,
         PLUS_SIGN, NUMBER_SIGN, SEMICOLON, COLON, LOW_LINE, DEGREE_SIGN,
         COMMERCIAL_AT, MICRO_SIGN,
         LESS_THAN_SIGN, GREATER_THAN_SIGN, VERTICAL_LINE, AMPERSAND, COMMA };

   private static SignLetter[] hebrewSigns = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP };
   private static SignLetter[] hebrewSignsWithComma = { QUESTION_MARK,
         EXCLAMATION_MARK, FULL_STOP, COMMA };
   
   private static SignLetter[] nikudSigns = { QUESTION_MARK, EXCLAMATION_MARK,
         FULL_STOP };
   private static SignLetter[] nikudSignsWithComma = { QUESTION_MARK,
         EXCLAMATION_MARK, FULL_STOP, COMMA };

   private static SignLetter[] extraInformationSigns = { QUESTION_MARK,
         EXCLAMATION_MARK, FULL_STOP, APOSTROPHE, LEFT_PARENTHESIS,
         RIGHT_PARENTHESIS, HYPHEN_MINUS, COMMA, QUOTATION_MARK, SECTION_SIGN,
         DOLLAR_SIGN, EURO_SIGN, PERCENT_SIGN, SOLIDUS, LEFT_SQUARE_BRACKET,
         RIGHT_SQUARE_BRACKET, LEFT_CURLY_BRACKET, RIGHT_CURLY_BRACKET,
         EQUALS_SIGN, ASTERISK, PLUS_SIGN, NUMBER_SIGN, 
         SEMICOLON, COLON, LOW_LINE, DEGREE_SIGN, COMMERCIAL_AT, MICRO_SIGN, LESS_THAN_SIGN,
         GREATER_THAN_SIGN, VERTICAL_LINE, AMPERSAND };
   
   private static SignLetter[] forFileNames = {HYPHEN_MINUS, LOW_LINE};
   
   private static SignLetter[] forbiddenSigns = {TAB, REVERSE_SOLIDUS};

   SignLetter(String code, String unicode)
   {
      this.code = code.toUpperCase();
      this.unicode = unicode.toUpperCase();
   }
   
   public static String getInternationalExclusionPattern()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : forbiddenSigns)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
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
   
   public static String getPatternStringNikud()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : nikudSigns)
      {
         joiner.add(letter.getCode());
      }
      return joiner.toString();
   }
   
   public static String getPatternStringNikudWithComma()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : nikudSignsWithComma)
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
   
   public static String getPatternStringForFileNames()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (SignLetter letter : forFileNames)
      {
         joiner.add(letter.getCode());
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
      return LetterType.SIGN;
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
