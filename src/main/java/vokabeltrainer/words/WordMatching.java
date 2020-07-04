package vokabeltrainer.words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import vokabeltrainer.editing.HebrewLetter;

public class WordMatching
{
   public static WordMatchingResult matchHebrew(String worddictionary,
         String wordtest)
   {
      if (worddictionary.isBlank())
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

//      System.out.println(Arrays.deepToString(data));

      int rowMax = 0;
      int rowMaxValue = 0;
      // compare words
      for (int row = 1; row < numberOfRows; row++)
      {
         int rowValue = 0;
         for (int col = 0; col < numberOfCols; col++)
         {
            rowValue += evaluateSame(data[0][col], data[row][col]);
         }
         if (rowValue > rowMaxValue)
         {
            rowMaxValue = rowValue;
            rowMax = row;
         }
      }

      if (rowMaxValue == 0)
      {
         result.setCompletelyFalse(true);
         return result;
      }

      if (sizeDic == rowMaxValue)
      {
         result.setOkay(true);
         return result;
      }

      result.setPartlyFalse(true);

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
//      if (deltaCol > 0) // moved to the left
//      {
//         cutOfUnnecessaryDataToTheRightFORleftShift(dataDic, dataTest,
//               numberOfCols, sizeTest);
//
//         moveBeginningLettersOfdataTestToTheRightIfPossible(dataDic, dataTest,
//               deltaCol);
//
//         moveEndingLettersOfdataTestToTheLeftIfPossible(dataDic, dataTest,
//               deltaCol, sizeDic, sizeTest);
//
//         cutOfUnnecessaryDataToTheLeft(dataDic);
//         cutOfUnnecessaryDataToTheLeft(dataTest);
//
//         lookForDoubleNullAndMoveLettersToTheLeftIfPossible(dataDic, dataTest);
//      }
//      else if (deltaCol < 0) // moved to the right
//      {
//         cutOfUnnecessaryDataToTheRightFORrightShift(dataDic, dataTest,
//               numberOfCols, sizeTest, deltaCol);

//         moveEndingLettersOfdataTestToTheLeftIfPossible(dataDic, dataTest,
//               deltaCol, sizeDic, sizeTest);

//         cutOfUnnecessaryDataToTheLeft(dataDic);
//         cutOfUnnecessaryDataToTheLeft(dataTest);
//      }

      result.setDataDic(dataDic); // for testing purposes
      result.setDataTest(dataTest); // for testing purposes

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

      return result;
   }

   private static void lookForDoubleNullAndMoveLettersToTheLeftIfPossible(
         List<String> dataDic, List<String> dataTest)
   {
      for (int t1 = 0, t2 = 1; t1 < dataTest.size(); t1++, t2++)
      {
         if (dataTest.get(t1) == null && dataTest.get(t2) == null)
         {
            int index = readIndexOfNextLetterToTheRight(dataTest, t2);
            if (index > 0
                  && dataTest.get(index).equalsIgnoreCase(dataDic.get(t2)))
            {
               String letterToBeMoved = dataTest.remove(index);
               dataTest.add(t2, letterToBeMoved);
            }
         }
      }
   }

   private static int readIndexOfNextLetterToTheRight(List<String> dataTest,
         int t2)
   {
      for (int index = t2; index < dataTest.size(); index++)
      {
         if (dataTest.get(index) != null)
         {
            return index;
         }
      }
      return -1;
   }

   private static void moveEndingLettersOfdataTestToTheLeftIfPossible(
         List<String> dataDic, List<String> dataTest, int deltaCol, int sizeDic,
         int sizeTest)
   {
      int deltaColLeft = sizeDic - (sizeTest + deltaCol);

      for (int d = sizeTest, t = sizeTest + deltaColLeft;; d++, t++)
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

   private static void cutOfUnnecessaryDataToTheLeft(List<String> dataDic)
   {
      while (true)
      {
         if (dataDic.get(0) == null)
         {
            dataDic.remove(0);
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

   private static void cutOfUnnecessaryDataToTheRightFORleftShift(
         List<String> dataDic, List<String> dataTest, int numberOfCol,
         int sizeTest)
   {
      for (int i = numberOfCol - 1; i > numberOfCol - sizeTest - 1; i--)
      {
         dataDic.remove(i);
         dataTest.remove(i);
      }
   }

   private static void cutOfUnnecessaryDataToTheRightFORrightShift(
         List<String> dataDic, List<String> dataTest, int numberOfCol,
         int sizeTest, int deltaCol)
   {
      for (int i = numberOfCol - 1; i > numberOfCol - sizeTest - 1
            + deltaCol; i--)
      {
         dataDic.remove(i);
         dataTest.remove(i);
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

   private static int evaluateSame(String string, String string2)
   {
      if (string == null && string2 == null)
      {
         return 0;
      }
      if (string == null || string2 == null)
      {
         return 0;
      }
      if (string.equalsIgnoreCase(string2))
      {
         return 1;
      }
      return 0;
   }
}
