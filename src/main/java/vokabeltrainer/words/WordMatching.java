package vokabeltrainer.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import vokabeltrainer.editing.HebrewLetter;

public class WordMatching
{
   public static WordMatchingResult matchHebrew(String worddictionary,
         String wordtest)
   {
      if (worddictionary.trim().isEmpty())
      {
         return null;
      }

      WordMatchingResult result = new WordMatchingResult();

      List<HebrewLetter> lettersDic = HebrewLetter
            .findHebrewLetters(worddictionary);
      Collections.reverse(lettersDic);

      List<HebrewLetter> lettersTest = HebrewLetter.findHebrewLetters(wordtest);

      int sizeDic = lettersDic.size();
      int sizeTest = lettersTest.size();
      int numberOfCols = sizeDic + 2 * sizeTest;
      int numberOfRows = numberOfCols + 1 - sizeTest + 1;

      String[][] data = new String[numberOfRows][numberOfCols];

      // put the correct word into the first row of the datagrid
      for (int i = sizeTest, l = 0; l < lettersDic.size(); i++, l++)
      {
         data[0][i] = lettersDic.get(l).name();
      }

      // put test word into the data grid
      for (int row = 1, deltaCol = 1; row < numberOfRows; row++, deltaCol++)
      {
         int col = numberOfCols - deltaCol;
         for (int l = 0; l < sizeTest; l++)
         {
            data[row][col] = lettersTest.get(l).name();
            col--;
         }
      }

      // System.out.println(Arrays.deepToString(data));

      int rowMax = 0;
      int rowSameMaxValue = 0;
      int rowDiffValueAtSameMax = 0;
      // compare words
      for (int row = 1; row < numberOfRows; row++)
      {
         int rowSameValue = 0;
         int rowDiffValue = 0;
         for (int col = 0; col < numberOfCols; col++)
         {
            rowSameValue += evaluateSame(data[0][col], data[row][col]);
            rowDiffValue += evaluateDifference(data[0][col], data[row][col]);
         }
         if (rowSameValue > rowSameMaxValue)
         {
            rowSameMaxValue = rowSameValue;
            rowDiffValueAtSameMax = rowDiffValue;
            rowMax = row;
         }
      }

      if (rowSameMaxValue == 0)
      {
         result.setCompletelyFalse(true);
      }
      else if (sizeDic == rowSameMaxValue && rowDiffValueAtSameMax == 0)
      {
         result.setOkay(true);
      }
      else
      {
         result.setPartlyFalse(true);
      }

      Collections.reverse(lettersDic);

      List<String> dataDic = new LinkedList<>();
      List<String> dataTest = new LinkedList<>();

      for (String letter : data[0])
      {
         dataDic.add(letter);
      }

      for (String letter : data[rowMax])
      {
         dataTest.add(letter);
      }

      int deltaCol = findDeltaColumns(dataTest, sizeTest);
      result.setDeltaCol(deltaCol);
      if (deltaCol > 0) // moved to the left
      {
         cutOfUnnecessaryDataToTheRight(dataDic, dataTest);

         moveBeginningLettersOfdataTestToTheRightIfPossible(dataDic, dataTest,
               deltaCol);

         lookForNullAndMoveLettersToTheLeftIfPossible(dataDic, dataTest);

         cutOfUnnecessaryDataToTheLeft(dataDic, dataTest);
      }
      else if (deltaCol < 0 || result.isPartlyFalse()) // moved to the right or not moved
      {
         cutOfUnnecessaryDataToTheRight(dataDic, dataTest);

         lookForNullAndMoveLettersToTheLeftIfPossible(dataDic, dataTest);

         cutOfUnnecessaryDataToTheLeft(dataDic, dataTest);
      }
      else // not moved and either okay or completely false
      {
         cutOfUnnecessaryDataToTheRight(dataDic, dataTest);
         cutOfUnnecessaryDataToTheLeft(dataDic, dataTest);
      }

      result.setDataDic(dataDic); // for testing purposes
      result.setDataTest(dataTest); // for testing purposes

      transferToHebrewLetters(result, dataDic, dataTest);

      return result;
   }

   private static void transferToHebrewLetters(WordMatchingResult result,
         List<String> dataDic, List<String> dataTest)
   {
      // transfer back into hebrew letter enums
      List<HebrewLetter> hebrewWordFromDictionary = new ArrayList<>();
      for (String letter : dataDic)
      {
         if (letter != null)
         {
            hebrewWordFromDictionary.add(HebrewLetter.valueOf(letter));
         }
         else
         {
            hebrewWordFromDictionary.add(HebrewLetter.SPACE);
         }
      }
      result.setHebrewDictionary(hebrewWordFromDictionary);

      List<HebrewLetter> hebrewWordFromTest = new ArrayList<>();
      for (String letter : dataTest)
      {
         if (letter != null)
         {
            hebrewWordFromTest.add(HebrewLetter.valueOf(letter));
         }
         else
         {
            hebrewWordFromTest.add(HebrewLetter.SPACE);
         }
      }
      result.setHebrewTest(hebrewWordFromTest);
   }

   private static int readIndexOfNextLetterToTheRight(List<String> dataTest,
         int i)
   {
      for (int index = i; index < dataTest.size(); index++)
      {
         if (dataTest.get(index) != null)
         {
            return index;
         }
      }
      return -1;
   }

   private static void cutOfUnnecessaryDataToTheLeft(List<String> dataDic,
         List<String> dataTest)
   {
      while (true)
      {
         if (dataDic.get(0) == null && dataTest.get(0) == null)
         {
            dataDic.remove(0);
            dataTest.remove(0);
         }
         else
         {
            break;
         }
      }
   }

   private static void cutOfUnnecessaryDataToTheRight(List<String> dataDic,
         List<String> dataTest)
   {
      while (true)
      {
         if (dataDic.get(dataDic.size() - 1) == null
               && dataTest.get(dataTest.size() - 1) == null)
         {
            dataDic.remove(dataDic.size() - 1);
            dataTest.remove(dataTest.size() - 1);
         }
         else
         {
            break;
         }
      }
   }

   private static void moveBeginningLettersOfdataTestToTheRightIfPossible(
         List<String> dataDic, List<String> dataTest, int deltaCol)
   {
      for (int d = dataDic.size() - 1, t = dataTest.size() - 1
            - deltaCol;; d--, t--)
      {
         if (WordMatching.evaluateSame(dataDic.get(d), dataTest.get(t)) == 1)
         {
            dataTest.remove(t);
            dataTest.add(d, dataDic.get(d));
         }
         else
         {
            break;
         }
      }
   }

   private static int findDeltaColumns(List<String> dataTest, int sizeTest)
   {
      int deltaTest = 0;
      for (int i = dataTest.size() - 1; i > -1; i--)
      {
         if (dataTest.get(i) == null)
         {
            deltaTest++;
         }
         else
         {
            break;
         }
      }

      return deltaTest - sizeTest;
   }

   private static void lookForNullAndMoveLettersToTheLeftIfPossible(
         List<String> dataDic, List<String> dataTest)
   {
      for (int i = 0; i < dataTest.size(); i++)
      {
         if (dataTest.get(i) == null && dataDic.get(i) != null)
         {
            int index = readIndexOfNextLetterToTheRight(dataTest, i);
            if (index > 0 && WordMatching.evaluateSame(dataDic.get(i),
                  dataTest.get(index)) == 1)
            {
               String letterToBeMoved = dataTest.remove(index);
               dataTest.add(i, letterToBeMoved);
            }
         }
      }
   }

   private static int evaluateSame(String stringDic, String stringTest)
   {
      if (stringDic == null && stringTest == null)
      {
         return 0;
      }
      if (stringDic == null)
      {
         return 0;
      }
      if (stringDic.equals("QUESTIONMARK") && stringTest == null)
      {
         return 1;
      }
      if (stringTest == null)
      {
         return 0;
      }
      if (stringDic.equalsIgnoreCase(stringTest))
      {
         return 1;
      }
      return 0;
   }

   private static int evaluateDifference(String stringDic, String stringTest)
   {
      if (stringDic == null && stringTest == null)
      {
         return 0;
      }
      if (stringDic == null)
      {
         return 1;
      }
      if (stringDic.equals("QUESTIONMARK") && stringTest == null)
      {
         return 0;
      }
      if (stringTest == null)
      {
         return 1;
      }
      if (stringDic.equalsIgnoreCase(stringTest))
      {
         return 0;
      }
      return 1;
   }
}
