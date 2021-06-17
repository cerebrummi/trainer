package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LetterHelper
{
   private static Map<String, Letter> nikudCodeMap;
   static
   {
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

   public static List<NikudLetter> findNikudLetters(String hebrewWord)
   {
      List<String> letterCodes = LetterHelper.findLetterCodes(hebrewWord);
      List<NikudLetter> hebrewLetters = new ArrayList<>();
      for (String code : letterCodes)
      {
         NikudLetter hebrewLetter = NikudLetter.getLetterFromCode(code);
         if (hebrewLetter != null)
         {
            hebrewLetters.add(hebrewLetter);
         }
      }
      return hebrewLetters;
   }
   
   public static List<String> findLetterCodes(String word)
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

      List<String> codeList = findLetterCodes(word);

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
      return LetterHelper.makeWordFromCodes(nikudCodeList);
   }

}
