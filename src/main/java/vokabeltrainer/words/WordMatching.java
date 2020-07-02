package vokabeltrainer.words;

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

      int rowMax = 0;
      int rowMaxValue = 0;
      // vergleiche Wörter
      for (int row = 1; row < numberOfRows; row++)
      {
         int rowValue = 0;
         for (int col = 0; col < numberOfCols; col++)
         {
            rowValue += evaluate(data[0][col], data[row][col]);
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
      
      /*Collections.reverse(lettersDic);

      List<String> dataDic = new LinkedList<>();
      List<String> dataTest = new LinkedList<>();

      for (String letter : data[0])
      {
         dataDic.add(letter);
      }

      for (String letter : data[rowMax])
      {
         dataTest.add(letter);
      }*/

      return result;
   }

   private static int evaluate(String string, String string2)
   {
      if (string == null && string2 == null)
      {
         return 0;
      }
      if (string == null || string2 == null)
      {
         return -1;
      }
      if (string.equalsIgnoreCase(string2))
      {
         return 1;
      }
      return -1;
   }
}
