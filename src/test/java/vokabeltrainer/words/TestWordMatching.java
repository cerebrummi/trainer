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
   String[] wordTest4Array = {"HAEI", "TET", "JOD", "SSAMECH", null, "WET", "JOD", "NUN", "WAW", null, "BET"};
   List<String> wordTest4List = new LinkedList<String>(Arrays.asList(wordTest4Array));
   String[] wordTest5Array = {"HAEI", "TET", null, "SSAMECH", null, "WET", "JOD", "NUN", "WAW", null, "BET"};
   List<String> wordTest5List = new LinkedList<String>(Arrays.asList(wordTest5Array));
   String[] wordTest6Array = {"HAEI", "TET", null, "SSAMECH", null, "WET", null, "NUN", "WAW", null, "BET"};
   List<String> wordTest6List = new LinkedList<String>(Arrays.asList(wordTest6Array));
   
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
   }
   
   @Test
   public void testMatchHebrew_PartlyFalse_Alignment()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest4);
      List<String> wordTesting = result.getDataTest();
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
   }
}
