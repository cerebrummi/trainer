package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import vokabeltrainer.types.Expression;

public class LetterHelper
{
   private static Map<String, Letter> codeMap;
   static
   {
      codeMap = new HashMap<>();
      for (Letter german : GermanLetter.values())
      {
         codeMap.put(german.getCode().toLowerCase(), german);
         codeMap.put(german.getCode().toUpperCase(), german);
      }
      for (Letter nikud : NikudLetter.values())
      {
         codeMap.put(nikud.getCode().toLowerCase(), nikud);
         codeMap.put(nikud.getCode().toUpperCase(), nikud);
      }
      for (Letter swedish : SwedishLetter.values())
      {
         codeMap.put(swedish.getCode().toLowerCase(), swedish);
         codeMap.put(swedish.getCode().toUpperCase(), swedish);
      }
      for (Letter sign : SignLetter.values())
      {
         codeMap.put(sign.getCode().toLowerCase(), sign);
         codeMap.put(sign.getCode().toUpperCase(), sign);
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

   public static Letter getLetterFromCode(String code, LetterType type)
   {
      if (LetterType.GERMAN == type && codeMap.containsKey(code))
      {
         return codeMap.get(code);
      }
      if (LetterType.SWEDISH == type && codeMap.containsKey(code))
      {
         return codeMap.get(code);
      }
      if (LetterType.HEBREW == type && codeMap.containsKey(code))
      {
         return codeMap.get(code);
      }
      if (LetterType.NUMBER == type && codeMap.containsKey(code))
      {
         return codeMap.get(code);
      }
      if (LetterType.SIGN == type && codeMap.containsKey(code))
      {
         return codeMap.get(code);
      }

      return null;
   }

   public static List<SwedishLetter> findSwedishLetters(String swedishWord)
   {
      List<String> letterCodes = LetterHelper.findLetterCodes(swedishWord,
            LetterType.SWEDISH);
      List<SwedishLetter> swedishLetters = new ArrayList<>();
      for (String code : letterCodes)
      {
         Letter swedishLetter = LetterHelper.getLetterFromCode(code,
               LetterType.SWEDISH);
         if (swedishLetter != null && swedishLetter instanceof SwedishLetter)
         {
            swedishLetters.add((SwedishLetter) swedishLetter);
         }
      }
      return swedishLetters;
   }
   
   public static List<NikudLetter> findNikudLetters(String hebrewWord)
   {
      List<String> letterCodes = LetterHelper.findLetterCodes(hebrewWord,
            LetterType.HEBREW);
      List<NikudLetter> hebrewLetters = new ArrayList<>();
      for (String code : letterCodes)
      {
         Letter hebrewLetter = LetterHelper.getLetterFromCode(code,
               LetterType.HEBREW);
         if (hebrewLetter != null && hebrewLetter instanceof NikudLetter)
         {
            hebrewLetters.add((NikudLetter) hebrewLetter);
         }
      }
      return hebrewLetters;
   }

   public static LetterType findLetterTypeLanguages(String text)
   {
      List<String> codelist = findLetterCodes(text, LetterType.NONE);
      
      for (String code : codelist)
      {
         if(getLetterFromCode(code + "_de", LetterType.GERMAN) != null)
         {
            return LetterType.GERMAN;
         }
         if(getLetterFromCode(code + "_se", LetterType.SWEDISH) != null)
         {
            return LetterType.SWEDISH;
         }
         if(getLetterFromCode(code + "_il", LetterType.HEBREW) != null)
         {
            return LetterType.HEBREW;
         }
      }
      
      return LetterType.NONE;
   }
   
   public static List<String> findLetterCodes(String word, LetterType type)
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
            String code = String.format(" %04x", (int) word.charAt(c))
                  + type.getRealm();

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

   public static LinkedList<LetterForAnalysis> findLetterForAnalysisList(
         String word, LetterType letterType)
   {
      LinkedList<LetterForAnalysis> analysisList = new LinkedList<>();

      List<String> codeList = findLetterCodes(word, letterType);
      LetterForAnalysis currentLetterForAnalysis;
      if (LetterType.HEBREW == letterType)
      {
         currentLetterForAnalysis = new LetterForAnalysis(
               NikudLetter.SPACE);
      }
      else if(LetterType.SWEDISH == letterType)
      {
         currentLetterForAnalysis = new LetterForAnalysis(
               SwedishLetter.SPACE);
      }
      else
      {
         currentLetterForAnalysis = new LetterForAnalysis(
               GermanLetter.SPACE);
      }

      for (int i = 0; i < codeList.size(); i++)
      {
         Letter letter = codeMap.get(codeList.get(i));
         if (letter != null && LetterType.HEBREW == letter.isType())
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
               break;
            }
         }
         else if (letter != null && LetterType.SWEDISH == letter.isType())
         {
            SwedishLetter swedishLetter = (SwedishLetter) letter;
            currentLetterForAnalysis = new LetterForAnalysis(swedishLetter);
            analysisList.add(currentLetterForAnalysis);
         }
         else if (letter != null && LetterType.GERMAN == letter.isType())
         {
            GermanLetter germanLetter = (GermanLetter) letter;
            currentLetterForAnalysis = new LetterForAnalysis(germanLetter);
            analysisList.add(currentLetterForAnalysis);
         }
      }

      return analysisList;
   }

   public static boolean areLettersEqual(LetterForAnalysis one,
         LetterForAnalysis two)
   {
      if(one.isNikud() && two.isSwedish())
      {
         return false;
      }
      if(one.isSwedish() && two.isNikud())
      {
         return false;
      }
      if(one.isNikud() && two.isNikud())
      {
         if (one.getContent() != two.getContent())
         {
            return false;
         }
      }
      if(one.isSwedish() && two.isSwedish())
      {
         if (one.getSwedishContent() != two.getSwedishContent())
         {
            return false;
         }
      }

      return true;
   }

   public static String turnExchangeSsinIntoNikudSsin(String hebrew)
   {
      List<String> nikudCodeList = new ArrayList<>();
      List<String> hebrewCodeList = LetterHelper.findLetterCodes(hebrew,
            LetterType.HEBREW);
      for (String hebrewCode : hebrewCodeList)
      {
         if (ExchangeLetter.SSIN.getCode().equalsIgnoreCase(hebrewCode))
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

   public static List<List<LetterForAnalysis>> findListofNikudLetterForAnalysisListsHebrewSearchwords(
         Expression expression)
   {
      return expression.getSearchwordsHebrew().stream()
            .map(word -> findLetterForAnalysisList(word, LetterType.HEBREW))
            .collect(Collectors.toList());
   }

   public static String findHebrewWithoutPunctation(String hebrew)
   {
      LinkedList<LetterForAnalysis> list = LetterHelper
            .findLetterForAnalysisList(hebrew, LetterType.HEBREW);

      StringBuilder result = new StringBuilder();

      for (LetterForAnalysis letter : list)
      {
         result.append(letter.getContent().getUnicode());
      }

      return result.toString();
   }

}
