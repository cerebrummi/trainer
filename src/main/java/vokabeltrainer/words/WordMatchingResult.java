package vokabeltrainer.words;

import java.util.List;

import vokabeltrainer.editing.HebrewLetter;

public class WordMatchingResult
{
   private boolean completelyFalse;
   private boolean partlyFalse;
   private boolean okay;
   private int deltaCol;
   private List<HebrewLetter> hebrewDictionary;
   private List<HebrewLetter> hebrewTest;
   private List<String>  dataDic;
   private List<String>  dataTest;

   public boolean isCompletelyFalse()
   {
      return completelyFalse;
   }

   public void setCompletelyFalse(boolean completelyFalse)
   {
      this.completelyFalse = completelyFalse;
   }

   public boolean isOkay()
   {
      return okay;
   }

   public void setOkay(boolean okay)
   {
      this.okay = okay;
   }

   public boolean isPartlyFalse()
   {
      return partlyFalse;
   }

   public void setPartlyFalse(boolean partlyFalse)
   {
      this.partlyFalse = partlyFalse;
   }

   public int getDeltaCol()
   {
      return deltaCol;
   }

   public void setDeltaCol(int deltaCol)
   {
      this.deltaCol = deltaCol;
   }

   public List<HebrewLetter> getHebrewDictionary()
   {
      return hebrewDictionary;
   }

   public void setHebrewDictionary(List<HebrewLetter> hebrewDictionary)
   {
      this.hebrewDictionary = hebrewDictionary;
   }

   public List<HebrewLetter> getHebrewTest()
   {
      return hebrewTest;
   }

   public void setHebrewTest(List<HebrewLetter> hebrewTest)
   {
      this.hebrewTest = hebrewTest;
   }

   public List<String> getDataDic()
   {
      return dataDic;
   }

   public void setDataDic(List<String> dataDic)
   {
      this.dataDic = dataDic;
   }

   public List<String> getDataTest()
   {
      return dataTest;
   }

   public void setDataTest(List<String> dataTest)
   {
      this.dataTest = dataTest;
   }

   



}
