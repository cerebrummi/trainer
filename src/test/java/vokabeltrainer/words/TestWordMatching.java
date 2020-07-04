package vokabeltrainer.words;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

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

   String[] wordTest4Array = { "HAEI", "TET", "JOD", "SSAMECH", null, "WET",
         "JOD", "NUN", "WAW", null, "BET" };
   List<String> wordTest4List = new LinkedList<String>(
         Arrays.asList(wordTest4Array));
   String[] wordTest5Array = { "HAEI", "TET", null, "SSAMECH", null, "WET",
         "JOD", "NUN", "WAW", null, "BET" };
   List<String> wordTest5List = new LinkedList<String>(
         Arrays.asList(wordTest5Array));
   String[] wordTest6Array = { "HAEI", "TET", null, "SSAMECH", null, "WET",
         null, "NUN", "WAW", null, "BET" };
   List<String> wordTest6List = new LinkedList<String>(
         Arrays.asList(wordTest6Array));
   String[] wordTest7Array = { "SCHIN", "FAEI", "AIN", "HAEI", "TET", "SSAMECH",
         "WET", "NUN", "WAW", null, "BET" };
   List<String> wordTest7List = new LinkedList<String>(
         Arrays.asList(wordTest7Array));
   String[] wordTest8Array = { "KUF", "ZADI", "SCHIN", "FAEI", "AIN", "HAEI",
         "TET", "SSAMECH", "WET", "NUN", "WAW", null, "BET" };
   List<String> wordTest8List = new LinkedList<String>(
         Arrays.asList(wordTest8Array));
   
   
   String[] wordDic9Array = { "HAEI", "TET", "JOD", "SSAMECH", "RESCH", "WET",
         "JOD", "NUN", "WAW", "ALEF", "BET", "WAW", "ALEF", null, null };
   List<String> wordDic9List = new LinkedList<String>(
         Arrays.asList(wordDic9Array));
   String[] wordTest9Array = { "HAEI", "TET", "JOD", "SSAMECH", "RESCH", "WET",
         "JOD", "NUN", "WAW", "ALEF", "BET", "WAW", "ALEF" };
   List<String> wordTest9List = new LinkedList<String>(
         Arrays.asList(wordTest9Array));
   
//   @Test
   public void testMatchHebrew_Okay()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest1);
      assertTrue(result.isOkay());
      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest4);
      assertFalse(result2.isOkay());
   }

//   @Test
   public void testMatchHebrew_False()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest2);
      assertTrue(result.isCompletelyFalse());
      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest4);
      assertFalse(result2.isCompletelyFalse());
   }

//   @Test
   public void testMatchHebrew_PartlyFalse()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest3);
      assertTrue(result.isPartlyFalse());
      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest4);
      assertTrue(result2.isPartlyFalse());
   }

//   @Test
   public void testMatchHebrew_PartlyFalse_deltaCol()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest3);
      assertTrue(result.getDeltaCol() == 1);
      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest7);
      assertTrue(result2.getDeltaCol() == 1);
      WordMatchingResult result3 = WordMatching.matchHebrew(wordDic, wordTest8);
      assertTrue(result3.getDeltaCol() == 1);
   }

   @Test
   public void testMatchHebrew_PartlyFalse_Alignment()
   {
//      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest4);
//      List<String> wordTesting = result.getDataTest();
//      System.out.println(wordTesting);
//      assertTrue(wordTest4List.equals(wordTesting));
//
//      WordMatchingResult result2 = WordMatching.matchHebrew(wordDic, wordTest5);
//      List<String> wordTesting2 = result2.getDataTest();
//      System.out.println(wordTesting2);
//      assertTrue(wordTest5List.equals(wordTesting2));
//
//      WordMatchingResult result3 = WordMatching.matchHebrew(wordDic, wordTest6);
//      List<String> wordTesting3 = result3.getDataTest();
//      System.out.println(wordTesting3);
//      assertTrue(wordTest6List.equals(wordTesting3));
//
//      WordMatchingResult result4 = WordMatching.matchHebrew(wordDic, wordTest7);
//      List<String> wordTesting4 = result4.getDataTest();
//      System.out.println(wordTesting4);
//      assertTrue(wordTest7List.equals(wordTesting4));
//
//      WordMatchingResult result5 = WordMatching.matchHebrew(wordDic, wordTest8);
//      List<String> wordTesting5 = result5.getDataTest();
//      System.out.println(wordTesting5);
//      assertTrue(wordTest8List.equals(wordTesting5));
      
      WordMatchingResult result6 = WordMatching.matchHebrew(wordDic, wordTest9);
      List<String> wordDicing6 = result6.getDataDic();
      System.out.println(wordDicing6);
      List<String> wordTesting6 = result6.getDataTest();
      System.out.println(wordTesting6);
      System.out.println(result6.getDeltaCol());
   }
}
