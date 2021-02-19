package vokabeltrainer.words;

import java.util.List;

import vokabeltrainer.editing.LetterForAnalysis;

public class WordLetterMatchingResult
{
   private boolean dictionaryEmpty;
   private boolean answerEmpty;
   
   private boolean completelyFalse;
   private boolean partlyFalse;
   private boolean okay;
   
   private int deltaCol;
   
   private List<LetterForAnalysis> dictionary;
   private List<LetterForAnalysis> answer;

   public boolean isDictionaryEmpty()
   {
      return dictionaryEmpty;
   }

   public void setDictionaryEmpty(boolean dictionaryEmpty)
   {
      this.dictionaryEmpty = dictionaryEmpty;
   }

   public boolean isAnswerEmpty()
   {
      return answerEmpty;
   }

   public void setAnswerEmpty(boolean answerEmpty)
   {
      this.answerEmpty = answerEmpty;
   }

   public boolean isCompletelyFalse()
   {
      return completelyFalse;
   }

   public void setCompletelyFalse(boolean completelyFalse)
   {
      this.completelyFalse = completelyFalse;
   }

   public boolean isPartlyFalse()
   {
      return partlyFalse;
   }

   public void setPartlyFalse(boolean partlyFalse)
   {
      this.partlyFalse = partlyFalse;
   }

   public boolean isOkay()
   {
      return okay;
   }

   public void setOkay(boolean okay)
   {
      this.okay = okay;
   }

   public int getDeltaCol()
   {
      return deltaCol;
   }

   public void setDeltaCol(int deltaCol)
   {
      this.deltaCol = deltaCol;
   }

   public List<LetterForAnalysis> getDictionary()
   {
      return dictionary;
   }

   public void setDictionary(List<LetterForAnalysis> dictionary)
   {
      this.dictionary = dictionary;
   }

   public List<LetterForAnalysis> getAnswer()
   {
      return answer;
   }

   public void setAnswer(List<LetterForAnalysis> answer)
   {
      this.answer = answer;
   }
}
