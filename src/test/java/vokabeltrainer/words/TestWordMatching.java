package vokabeltrainer.words;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import vokabeltrainer.editing.HebrewLetter;

public class TestWordMatching
{
   String wordDic = "בּאוניברסיטה";
   String wordTest1 = "בּאוניברסיטה";
   String wordTest2 = "גדפ";
   String wordTest3 = "בּוניברסיטה";
   String wordTest4 = "בּוניבסיטה"; // 2 letters missing
   String wordTest5 = "בּוניבסטה"; // 3 letters missing
   String wordTest6 = "בּונבסטה"; // 4 letters missing
   String wordTest7 = "בּונבסטהעפש"; // 4 letters missing plus 3 letters stupid
   String wordTest8 = "בּונבסטהעפשצק"; // 4 letters missing plus 5 letters
                                       // stupid
   String wordTest9 = "אובּאוניברסיטה"; // 2 letters added at the beginning =>
                                        // right shift case
   String wordTest10 = "אובּאוניבריטה"; // 2 letters added at the beginning =>
                                        // right shift case, and 1 letter
                                        // missing inside
   String wordTest11 = "מסרדם"; // example from real life, wrong spelling
   String wordDic11 = "משׂרדים"; // correct spelling of 11
   String wordTest12 = "פּקדה"; // example from real life, wrong spelling
   String wordDic12 = "פּקידה"; // correct spelling of 12
   String wordTest13 = "חברים"; // correct spelling test
   String wordTest14 = "בּקר טוב"; // wrong spelling
   String wordDic14 = "בּוקר טוב"; // correct spelling of 14
   String wordTest15 = "נימות"; // wrong spelling
   String wordDic15 = "נעימות"; // correct spelling
   String wordTest15b = "נ ימות"; // corrected spelling
   String wordDic16 = "נעימות"; // correct spelling
   String wordTest16b = "נא מ ת"; // corrected spelling
   String wordTest16 = "נאמת"; // wrong spelling

   String wordTest17 = "מא נישמה"; // wrong spelling
   String wordDic17 = "מה נשמע"; // correct spelling
   String wordDic17b = "מה נ שמע"; // dic corrected spelling
   String wordTest17b = "מא נישמה"; // test corrected spelling

   String wordTest18 = "בּוקרטוב"; // wrong spelling
   String wordDic18 = "בּוקר טוב"; // correct spelling

   String wordTest19 = "בּמשׂראד"; // wrong spelling
   String wordDic19 = "בּמשׂרד"; // correct spelling

   String[] wordTest4Array = { "HAEI", "TET", "JOD", "SSAMECH", "NEWSPACE",
         "WET", "JOD", "NUN", "WAW", "NEWSPACE", "BET" };
   List<String> wordTest4List = new LinkedList<String>(
         Arrays.asList(wordTest4Array));
   String[] wordTest5Array = { "HAEI", "TET", "NEWSPACE", "SSAMECH", "NEWSPACE",
         "WET", "JOD", "NUN", "WAW", "NEWSPACE", "BET" };
   List<String> wordTest5List = new LinkedList<String>(
         Arrays.asList(wordTest5Array));
   String[] wordTest6Array = { "HAEI", "TET", "NEWSPACE", "SSAMECH", "NEWSPACE",
         "WET", "NEWSPACE", "NUN", "WAW", "NEWSPACE", "BET" };
   List<String> wordTest6List = new LinkedList<String>(
         Arrays.asList(wordTest6Array));
   String[] wordTest7Array = { "SCHIN", "FAEI", "AIN", "HAEI", "TET", "SSAMECH",
         "WET", "NUN", "WAW", "NEWSPACE", "BET" };
   List<String> wordTest7List = new LinkedList<String>(
         Arrays.asList(wordTest7Array));
   String[] wordTest8Array = { "KUF", "ZADI", "SCHIN", "FAEI", "AIN", "HAEI",
         "TET", "SSAMECH", "WET", "NUN", "WAW", "NEWSPACE", "BET" };
   List<String> wordTest8List = new LinkedList<String>(
         Arrays.asList(wordTest8Array));

   String[] wordDic9Array = { "HAEI", "TET", "JOD", "SSAMECH", "RESCH", "WET",
         "JOD", "NUN", "WAW", "ALEF", "BET", "NEWSPACE", "NEWSPACE" };
   List<String> wordDic9List = new LinkedList<String>(
         Arrays.asList(wordDic9Array));
   String[] wordTest9Array = { "HAEI", "TET", "JOD", "SSAMECH", "RESCH", "WET",
         "JOD", "NUN", "WAW", "ALEF", "BET", "WAW", "ALEF" };
   List<String> wordTest9List = new LinkedList<String>(
         Arrays.asList(wordTest9Array));

   String[] wordTest10Array = { "HAEI", "TET", "JOD", "NEWSPACE", "RESCH",
         "WET", "JOD", "NUN", "WAW", "ALEF", "BET", "WAW", "ALEF" };
   List<String> wordTest10List = new LinkedList<String>(
         Arrays.asList(wordTest10Array));

   String[] wordTest11Array = { "MEMSSOFIT", "NEWSPACE", "DALET", "RESCH",
         "SSAMECH", "MEM" };
   List<String> wordTest11List = new LinkedList<String>(
         Arrays.asList(wordTest11Array));

   String[] wordTest12Array = { "HAEI", "DALET", "NEWSPACE", "KUF", "PAEI" };
   List<String> wordTest12List = new LinkedList<String>(
         Arrays.asList(wordTest12Array));

   String[] wordTest13Array = { "MEMSSOFIT", "JOD", "RESCH", "WET", "CHET" };
   List<String> wordTest13List = new LinkedList<String>(
         Arrays.asList(wordTest13Array));

   String[] wordTest14Array = { "WET", "WAW", "TET", "SPACE", "RESCH", "KUF",
         "NEWSPACE", "BET" };
   List<String> wordTest14List = new LinkedList<String>(
         Arrays.asList(wordTest14Array));

   String[] wordTest15Array = { "TAW", "WAW", "MEM", "JOD", "NEWSPACE", "NUN" };
   List<String> wordTest15List = new LinkedList<String>(
         Arrays.asList(wordTest15Array));

   String[] wordTest15bArray = { "TAW", "WAW", "MEM", "JOD", "NEWSPACE",
         "NUN" };
   List<String> wordTest15bList = new LinkedList<String>(
         Arrays.asList(wordTest15bArray));

   String[] wordTest16bArray = { "TAW", "NEWSPACE", "MEM", "NEWSPACE", "ALEF",
         "NUN" };
   List<String> wordTest16bList = new LinkedList<String>(
         Arrays.asList(wordTest16bArray));

   String[] wordTest17bArray = { "HAEI", "MEM", "SCHIN", "JOD", "NUN", "SPACE",
         "ALEF", "MEM" };
   List<String> wordTest17bList = new LinkedList<String>(
         Arrays.asList(wordTest17bArray));
   String[] wordDic17bArray = { "AIN", "MEM", "SCHIN", "NEWSPACE", "NUN",
         "SPACE", "HAEI", "MEM" };
   List<String> wordDic17bList = new LinkedList<String>(
         Arrays.asList(wordDic17bArray));

   String[] wordTest18bArray = { "WET", "WAW", "TET", "NEWSPACE", "RESCH",
         "KUF", "WAW", "BET" };
   List<String> wordTest18bList = new LinkedList<>(
         Arrays.asList(wordTest18bArray));
   
   String[] wordDic19bArray = { "DALET", "NEWSPACE", "RESCH",
         "SSIN", "MEM", "BET" };
   List<String> wordDic19bList = new LinkedList<>(
         Arrays.asList(wordDic19bArray));

   @Test
   public void testMatchHebrew_Okay()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest1);
      assertTrue(result.isOkay());
      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest4);
      assertFalse(result2.isOkay());
   }

   @Test
   public void testMatchHebrew_False()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest2);
      assertTrue(result.isCompletelyFalse());
      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest4);
      assertFalse(result2.isCompletelyFalse());
   }

   @Test
   public void testMatchHebrew_PartlyFalse()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest3);
      assertTrue(result.isPartlyFalse());
      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest4);
      assertTrue(result2.isPartlyFalse());
   }

   @Test
   public void testMatchHebrew_PartlyFalse_deltaCol()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest3);
      assertTrue(result.getDeltaCol() == 1);
      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest7);
      assertTrue(result2.getDeltaCol() == 1);
      WordMatchingResult result3 = WordMatching.matchHebrew(wordDic, wordTest8);
      assertTrue(result3.getDeltaCol() == 1);
      WordMatchingResult result4 = WordMatching.matchHebrew(wordDic11,
            wordTest11);
      assertTrue(result4.getDeltaCol() == 0);
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest4);
      List<String> wordTesting = result.getDataTest();
      System.out.println(wordTest4List);
      System.out.println(wordTesting);
      assertTrue(wordTest4List.equals(wordTesting));

      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest5);
      List<String> wordTesting2 = result2.getDataTest();
      System.out.println(wordTesting2);
      assertTrue(wordTest5List.equals(wordTesting2));

      WordMatchingResult result3 = WordMatching.matchHebrew(wordDic, wordTest6);
      List<String> wordTesting3 = result3.getDataTest();
      System.out.println(wordTesting3);
      assertTrue(wordTest6List.equals(wordTesting3));

      WordMatchingResult result4 = WordMatching.matchHebrew(wordDic, wordTest7);
      List<String> wordTesting4 = result4.getDataTest();
      System.out.println(wordTesting4);
      assertTrue(wordTest7List.equals(wordTesting4));

      WordMatchingResult result5 = WordMatching.matchHebrew(wordDic, wordTest8);
      List<String> wordTesting5 = result5.getDataTest();
      System.out.println(wordTesting5);
      assertTrue(wordTest8List.equals(wordTesting5));

      WordMatchingResult result6 = WordMatching.matchHebrew(wordDic, wordTest9);
      List<String> wordDicing6 = result6.getDataDic();
      assertTrue(wordDic9List.equals(wordDicing6));
      List<String> wordTesting6 = result6.getDataTest();
      assertTrue(wordTest9List.equals(wordTesting6));
      assertTrue(result6.getDeltaCol() == -2);

      WordMatchingResult result7 = WordMatching.matchHebrew(wordDic,
            wordTest10);
      List<String> wordTesting7 = result7.getDataTest();
      assertTrue(wordTest10List.equals(wordTesting7));
      assertTrue(result7.getDeltaCol() == -2);

      WordMatchingResult result8 = WordMatching.matchHebrew(wordDic11,
            wordTest11);
      List<String> wordTesting8 = result8.getDataTest();
      System.out.println(result8.getDeltaCol());
      System.out.println(wordTesting8);
      assertTrue(wordTest11List.equals(wordTesting8));
      assertTrue(result8.getDeltaCol() == 0);

      WordMatchingResult result9 = WordMatching.matchHebrew(wordDic12,
            wordTest12);
      List<String> wordTesting9 = result9.getDataTest();
      System.out.println(result9.getDeltaCol());
      System.out.println(wordTesting9);
      assertTrue(wordTest12List.equals(wordTesting9));
      assertTrue(result9.getDeltaCol() == 0);

      WordMatchingResult result10 = WordMatching.matchHebrew(wordTest13,
            wordTest13);
      List<String> wordTesting10 = result10.getDataTest();
      System.out.println(result10.getDeltaCol());
      System.out.println(wordTesting10);
      assertTrue(wordTest13List.equals(wordTesting10));
      assertTrue(result10.getDeltaCol() == 0);

      WordMatchingResult result11 = WordMatching.matchHebrew(wordDic14,
            wordTest14);
      List<String> wordTesting11 = result11.getDataTest();
      System.out.println(result11.getDeltaCol());
      System.out.println(wordTesting11);
      assertTrue(wordTest14List.equals(wordTesting11));
      assertTrue(result11.getDeltaCol() == 1);

      WordMatchingResult result12 = WordMatching.matchHebrew(wordDic15,
            wordTest15);
      List<String> wordTesting12 = result12.getDataTest();
      System.out.println(result12.getDeltaCol());
      System.out.println(wordTesting12);
      assertTrue(wordTest15List.equals(wordTesting12));
      assertTrue(result12.getDeltaCol() == 1);

      WordMatchingResult result13 = WordMatching.matchHebrew(wordDic17,
            wordTest17);
      List<String> wordTesting13 = result13.getDataTest();
      List<String> wordDicing13 = result13.getDataDic();
      System.out.println(result13.getDeltaCol());
      System.out.println(wordDicing13);
      System.out.println(wordTesting13);
      assertTrue(result13.getDeltaCol() == 0);
      assertTrue(wordTest17bList.equals(wordTesting13));
      assertTrue(wordDic17bList.equals(wordDicing13));

      WordMatchingResult result14 = WordMatching.matchHebrew(wordDic18,
            wordTest18);
      List<String> wordTesting18 = result14.getDataTest();
      System.out.println(wordTest18bList);
      System.out.println(wordTesting18);
      assertTrue(wordTest18bList.equals(wordTesting18));
      
      WordMatchingResult result15 = WordMatching.matchHebrew(wordDic19,
            wordTest19);
      List<String> wordDicing19 = result15.getDataDic();
      System.out.println(wordDic19bList);
      System.out.println(wordDicing19);
      assertTrue(wordDic19bList.equals(wordDicing19));
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Result()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic15,
            wordTest15);
      assertFalse(result.getHebrewDictionary().equals(result.getHebrewTest()));
      List<HebrewLetter> comparison = transferToHebrewLetters(wordTest15bList);
      for (HebrewLetter letter : result.getHebrewTest())
      {
         System.out.println(letter.name());
      }
      System.out.println("----------");
      for (HebrewLetter letter : comparison)
      {
         System.out.println(letter.name());
      }
      assertTrue(result.getHebrewTest().equals(comparison));

      System.out.println("===========");

      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic16,
            wordTest16);
      assertFalse(
            result2.getHebrewDictionary().equals(result2.getHebrewTest()));
      List<HebrewLetter> comparison2 = transferToHebrewLetters(wordTest16bList);
      for (HebrewLetter letter : result2.getHebrewTest())
      {
         System.out.println(letter.name());
      }
      System.out.println("----------");
      for (HebrewLetter letter : comparison2)
      {
         System.out.println(letter.name());
      }
      assertTrue(result2.getHebrewTest().equals(comparison2));

      System.out.println("===========");

      WordMatchingResult result3 = WordMatching.matchHebrew(wordDic17,
            wordTest17);
      assertFalse(
            result3.getHebrewDictionary().equals(result3.getHebrewTest()));
      List<HebrewLetter> comparison3 = transferToHebrewLetters(wordDic17bList);
      List<HebrewLetter> comparison3b = HebrewLetter
            .findHebrewLetters(wordTest17b);
      Collections.reverse(comparison3b);
      System.out.println("-----should result Test-----");
      for (HebrewLetter letter : comparison3b)
      {
         System.out.println(letter.name());
      }
      System.out.println("-----result Test-----");
      for (HebrewLetter letter : result3.getHebrewTest())
      {
         System.out.println(letter.name());
      }
      System.out.println("-----should result Dic-----");
      for (HebrewLetter letter : comparison3)
      {
         System.out.println(letter.name());
      }
      System.out.println("-----result Dic-----");
      for (HebrewLetter letter : result3.getHebrewDictionary())
      {
         System.out.println(letter.name());
      }

      assertTrue(result3.getHebrewTest().equals(comparison3b));
      assertTrue(result3.getHebrewDictionary().equals(comparison3));
   }

   private List<HebrewLetter> transferToHebrewLetters(List<String> list)
   {
      // transfer back into hebrew letter enums
      List<HebrewLetter> hebrewWord = new ArrayList<>();
      for (String letter : list)
      {
         if (letter != null)
         {
            hebrewWord.add(HebrewLetter.valueOf(letter));
         }
         else
         {
            hebrewWord.add(HebrewLetter.NEWSPACE);
         }
      }
      return hebrewWord;
   }
}
