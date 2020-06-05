package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public enum GermanLetter
{
   A(" 0041"),
   B(" 0042"),
   C(" 0043"),
   D(" 0044"),
   E(" 0045"),
   F(" 0046"),
   G(" 0047"),
   H(" 0048"),
   I(" 0049"),
   J(" 004a"),
   K(" 004b"),
   L(" 004c"),
   M(" 004d"),
   N(" 004e"),
   O(" 004f"),
   P(" 0050"),
   Q(" 0051"),
   R(" 0052"),
   S(" 0053"),
   T(" 0054"),
   U(" 0055"),
   V(" 0056"),
   W(" 0057"),
   X(" 0058"),
   Y(" 0059"),
   Z(" 005a"),
   UE(" 00dc"),
   AE(" 00c4"),
   OE(" 00d6"),
   a(" 0061"),
   b(" 0062"),
   c(" 0063"),
   d(" 0064"),
   e(" 0065"),
   f(" 0066"),
   g(" 0067"),
   h(" 0068"),
   i(" 0069"),
   j(" 006a"),
   k(" 006b"),
   l(" 006c"),
   m(" 006d"),
   n(" 006e"),
   o(" 006f"),
   p(" 0070"),
   q(" 0071"),
   r(" 0072"),
   s(" 0073"),
   t(" 0074"),
   u(" 0075"),
   v(" 0076"),
   w(" 0077"),
   x(" 0078"),
   y(" 0079"),
   z(" 007a"),
   ue(" 00fc"),
   ae(" 00e4"),
   oe(" 00f6"),
   sz(" 00DF");
   
   private String code;
   
   GermanLetter(String code)
   {
      this.code = code;
   }
   
   public static String getPatternString(boolean withComma)
   {
      StringJoiner joiner = new StringJoiner(",");
      for(GermanLetter letter : GermanLetter.values())
      {
         joiner.add(letter.code);
      }
      joiner.add(" 003F"); // ?
      joiner.add(" 0021"); // !
      joiner.add(" 002E"); // .
      joiner.add(" 0020"); // space
      joiner.add(" 0027"); // '
      joiner.add(" 0028"); // (
      joiner.add(" 0029"); // )
      if(withComma)
      {
         joiner.add(" 002C"); // .
      }
      
      return joiner.toString();
   }
   
   public static List<String> findLetters(String germanWord)
   {
      List<String> letters = new ArrayList<>();
      for (int i = 0; i < germanWord.length(); i++)
      {
         try
         {
            letters.add(String.format(" %04x", (int) germanWord.charAt(i)));
         }
         catch (Exception e)
         {
            
         }
      }
      return letters;
   }
}
