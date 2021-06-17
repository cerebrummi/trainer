package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LetterHelper
{
   private static Map<String, Letter> codeMap;
   private static Map<String, Letter> nikudCodeMap;
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
      for (Letter number : NumberLetter.values())
      {
         codeMap.put(number.getCode().toLowerCase(), number);
         codeMap.put(number.getCode().toUpperCase(), number);
      }

      nikudCodeMap = new HashMap<>();
      for (Letter nikud : NikudLetter.values())
      {
         nikudCodeMap.put(nikud.getCode().toLowerCase(), nikud);
         nikudCodeMap.put(nikud.getCode().toUpperCase(), nikud);
      }
      for (Letter sign : SignLetter.values())
      {
         nikudCodeMap.put(sign.getCode().toLowerCase(), sign);
         nikudCodeMap.put(sign.getCode().toUpperCase(), sign);
      }
      for (Letter number : NumberLetter.values())
      {
         nikudCodeMap.put(number.getCode().toLowerCase(), number);
         nikudCodeMap.put(number.getCode().toUpperCase(), number);
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
   

   public static String makeNikudWordFromCodes(List<String> codes)
   {
      StringBuilder builder = new StringBuilder();
      for (String code : codes)
      {
         if (nikudCodeMap.get(code) != null)
         {
            builder.append(nikudCodeMap.get(code).getUnicode());
         }
      }
      return builder.toString();
   }

   public static LinkedList<LetterForAnalysis> findNikudLetterForAnalysisList(
         String word)
   {
      LinkedList<LetterForAnalysis> analysisList = new LinkedList<>();

      List<String> codeList = findNikudLetterCodes(word);

      LetterForAnalysis currentLetterForAnalysis = new LetterForAnalysis(
            NikudLetter.SPACE);

      for (int i = 0; i < codeList.size(); i++)
      {
         Letter letter = nikudCodeMap.get(codeList.get(i));
         if (LetterType.NIKUD == letter.isType())
         {
            NikudLetter nikudLetter = (NikudLetter) letter;
            switch (nikudLetter.getDistinction())
            {
            case LETTER:
               currentLetterForAnalysis = new LetterForAnalysis(nikudLetter);
               analysisList.add(currentLetterForAnalysis);
               break;
            case LOWER_PUNKTATION:
               currentLetterForAnalysis.addToLowerPunktation(nikudLetter);
               break;
            case MIDDLE_PUNKTATION:
               currentLetterForAnalysis.addDagesh(nikudLetter);
               break;
            case UPPER_PUNKTATION:
               currentLetterForAnalysis.addToUpperPunktation(nikudLetter);
            }
         }
      }

      return analysisList;
   }
   
   public static LinkedList<LetterForAnalysis> findHebrewLetterForAnalysisList(
         String word)
   {
      LinkedList<LetterForAnalysis> analysisList = new LinkedList<>();

      List<String> codeList = findLetterCodes(word);

      for (int i = 0; i < codeList.size(); i++)
      {
         Letter letter = codeMap.get(codeList.get(i));
         if (LetterType.HEBREW == letter.isType())
         {
            analysisList.add(new LetterForAnalysis((HebrewLetter) letter));
         }
      }

      return analysisList;
   }
   
   public static boolean areLettersEqual(LetterForAnalysis one, LetterForAnalysis two)
   {
      if(one.getContent() != two.getContent())
      {
         return false;
      }
      
      if(!one.getSetUpperPunktation().equals(two.getSetUpperPunktation()))
      {
         return false;
      }
      
      if(one.getDagesh() != two.getDagesh())
      {
         return false;
      }
      
      if(!one.getListLowerPunktation().equals(two.getListLowerPunktation()))
      {
         return false;
      }
      return true;
   }

   public static String turnExchangeSsinIntoNikudSsin(String hebrew)
   {
      List<String> nikudCodeList = new ArrayList<>();
      List<String> hebrewCodeList = LetterHelper.findLetterCodes(hebrew);
      for(String hebrewCode : hebrewCodeList)
      {
         if(ExchangeLetter.SSIN.getCode().equalsIgnoreCase(hebrewCode))
         {
            nikudCodeList.add(NikudLetter.SCHIN.getCode());
            nikudCodeList.add(NikudLetter.SIN_DOT.getCode());
         }
         else
         {
            nikudCodeList.add(hebrewCode);
         }
      }
      return LetterHelper.makeNikudWordFromCodes(nikudCodeList);
   }

}
