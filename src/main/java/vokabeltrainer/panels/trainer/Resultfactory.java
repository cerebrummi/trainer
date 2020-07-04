package vokabeltrainer.panels.trainer;

import java.util.List;

import vokabeltrainer.LetterFeedbackImage;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.words.WordMatching;
import vokabeltrainer.words.WordMatchingResult;

public class Resultfactory
{

   public static Result getResultDtoH(Expression expression, String answer)
   {
      Result result = new Result();
      result.setExpression(expression);

      WordMatchingResult wordMatchingResult = WordMatching.matchHebrew(answer, expression.getHebrew());
      
      List<HebrewLetter> answerLetters = wordMatchingResult.getHebrewDictionary();
      List<HebrewLetter> expressionLetters = wordMatchingResult.getHebrewTest();
      result.setOkay(wordMatchingResult.isOkay());

      for (int i = 0; i < answerLetters.size()
            && i < expressionLetters.size(); i++)
      {
         if (answerLetters.get(i).equals(expressionLetters.get(i)) || HebrewLetter.isQuestionmark(answerLetters.get(i)))
         {
            result.getLetterFeedbackImages()
                  .add(LetterFeedbackImage.make(answerLetters.get(i), true));
         }
         else
         {
            result.getLetterFeedbackImages()
                  .add(LetterFeedbackImage.make(answerLetters.get(i), false));
         }
         result.addToWidth(answerLetters.get(i).getPixelWidth());
      }

      result.setAnswerLettersSize(answerLetters.size());
      result.setExpressionLettersSize(expressionLetters.size());

      return result;
   }

}
