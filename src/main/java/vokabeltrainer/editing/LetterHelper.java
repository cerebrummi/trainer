package vokabeltrainer.editing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LetterHelper
{
   private static Map<String, Letter> codeMap;
   static
   {
      codeMap = new HashMap<>();
      for (Letter sign : SignLetter.values())
      {
         codeMap.put(sign.getCode().toLowerCase(), sign);
         codeMap.put(sign.getCode().toUpperCase(), sign);
      }
      for (Letter german : GermanLetter.values())
      {
         codeMap.put(german.getCode().toLowerCase(), german);
         codeMap.put(german.getCode().toUpperCase(), german);
      }
      for (Letter hebrew : HebrewLetter.values())
      {
         codeMap.put(hebrew.getCode().toLowerCase(), hebrew);
         codeMap.put(hebrew.getCode().toUpperCase(), hebrew);
      }
      for (Letter nikud : NikudLetter.values())
      {
         codeMap.put(nikud.getCode().toLowerCase(), nikud);
         codeMap.put(nikud.getCode().toUpperCase(), nikud);
      }
      for (Letter number : NumberLetter.values())
      {
         codeMap.put(number.getCode().toLowerCase(), number);
         codeMap.put(number.getCode().toUpperCase(), number);
      }
   }

   private LetterHelper()
   {
      // nothing
   }

   public static List<String> findLetterCodes(String word)
   {
      List<String> letterCodes = new LinkedList<>();
      if (word == null)
      {
         return letterCodes;
      }
      for (int i = 0, c = 0; i < word.length() && c < word.length();)
      {
         try
         {
            String code = String.format(" %04x", (int) word.charAt(c));
            if (i == 0 && (code.equalsIgnoreCase(" 05BC")
                  || code.equalsIgnoreCase(" 05c2")))
            {
               // i is the number of letterCodes, since no new letterCode is
               // added i is not advanced
               c++;
               continue; // wrong spelling, dagesch and ssin dot can not be in
                         // the beginning of a word, this is cut out
            }

            if (code.equalsIgnoreCase(" 05BC")) // dagesch
            {
               // i is the number of letterCodes, since no new letterCode is
               // added i is not advanced
               letterCodes.set(i - 1, letterCodes.get(i - 1) + code); // dagesch
                                                                      // is
                                                                      // added
                                                                      // to
                                                                      // letter
                                                                      // before
               c++;
            }
            else if (code.equalsIgnoreCase(" 05c2")) // ssin dot
            {
               // i is the number of letterCodes, since no new letterCode is
               // added i is not advanced
               letterCodes.set(i - 1, " Fb2B"); // letter before is a ssin
               c++;
            }
            else
            {
               letterCodes.add(code);
               i++;
               c++;
            }
         }
         catch (Exception e)
         {
            c++;
         }
      }
      return letterCodes;
   }

   public static List<String> findNikudLetterCodes(String word)
   {
      List<String> letterCodes = new LinkedList<>();
      if (word == null)
      {
         return letterCodes;
      }
      for (int c = 0; c < word.length(); c++)
      {
         try
         {
            String code = String.format(" %04x", (int) word.charAt(c));

            letterCodes.add(code);
         }
         catch (Exception e)
         {
            // nothing
         }
      }
      return letterCodes;
   }

   public static String makeWordFromCodes(List<String> codes)
   {
      StringBuilder builder = new StringBuilder();
      for (String code : codes)
      {
         if (codeMap.get(code) != null)
         {
            builder.append(codeMap.get(code).getUnicode());
         }
      }
      return builder.toString();
   }

}
