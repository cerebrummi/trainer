package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public enum NumberLetter
{
   ZERO(" 0030"),
   ONE(" 0031"),
   TWO(" 0032"),
   THREE(" 0033"),
   FOUR(" 0034"),
   FIVE(" 0035"),
   SIX(" 0036"),
   SEVEN(" 0037"),
   EIGHT(" 0038"),
   NINE(" 0039");
   
   private String code;
   
   NumberLetter(String code)
   {
      this.code = code;
   }


   public static String getPatternString(boolean withComma)
   {
      StringJoiner joiner = new StringJoiner(",");
      for(NumberLetter letter : NumberLetter.values())
      {
         joiner.add(letter.code);
      }
      if(withComma)
      {
         joiner.add(" 002C"); // .
      }
      
      return joiner.toString();
   }
   
   public static List<String> findNumberLetters(String numberWord)
   {
      List<String> letters = new ArrayList<>();
      for (int i = 0; i < numberWord.length(); i++)
      {
         try
         {
            letters.add(String.format(" %04x", (int) numberWord.charAt(i)));
         }
         catch (Exception e)
         {
            
         }
      }
      return letters;
   }
}
