package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.List;

public class LetterHelper
{
   private LetterHelper()
   {
      // nothing
   } 
   
   public static List<String> findLetterCodes(String word)
   {
      List<String> letterCodes = new ArrayList<>();
      if(word == null)
      {
         return letterCodes;
      }
      for (int i = 0, c = 0; i < word.length()
            && c < word.length();)
      {
         try
         {
            String code = String.format(" %04x", (int) word.charAt(c));
            if (i == 0 && (code.equalsIgnoreCase(" 05BC")
                  || code.equalsIgnoreCase(" 05c2")))
            {
               i++;
               c++;
               continue; // wrong spelling, dagesch and ssin dot can not be in
                         // the beginning of a word
            }

            if (code.equalsIgnoreCase(" 05BC")) // dagesch
            {
               // i is the number of letterCodes, since no new letterCode is added i is not advanced
               letterCodes.set(i - 1, letterCodes.get(i - 1) + code); // dagesch is added to letter before
               c++;
            }
            else if (code.equalsIgnoreCase(" 05c2")) // ssin dot
            {
               // i is the number of letterCodes, since no new letterCode is added i is not advanced
               letterCodes.set(i - 1, " Fb2B"); // letter before is a ssin
               c++;
            }
            else if (code.equalsIgnoreCase(" 0022")) // quotationmark
            {
               // i is the number of letterCodes, since no new letterCode is added i is not advanced
               letterCodes.set(i - 1, letterCodes.get(i - 1) + code); // letter before is a backslash
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
}
