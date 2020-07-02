package vokabeltrainer.words;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestWordMatching
{
   String wordDic = "בּאוניברסיטה";
   String wordTest1 = "בּאוניברסיטה";
   String wordTest2 = "גדפ";
   String wordTest3 = "בּוניברסיטה";
   
   @Test
   public void testMatchHebrew_Okay()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest1);
      assertTrue(result.isOkay());
   }
   
   @Test
   public void testMatchHebrew_False()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest2);
      assertTrue(result.isCompletelyFalse());
   }
   
   @Test
   public void testMatchHebrew_PartlyFalse()
   {
      WordMatchingResult result = WordMatching.matchHebrew(wordDic, wordTest3);
      assertTrue(result.isPartlyFalse());
   }
}
