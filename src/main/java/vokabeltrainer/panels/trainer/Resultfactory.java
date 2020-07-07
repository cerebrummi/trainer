package vokabeltrainer.panels.trainer;

import java.util.Collections;
import java.util.List;

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

      WordMatchingResult wordMatchingResult = WordMatching
            .matchHebrew(expression.getHebrew(), answer);

      List<HebrewLetter> answerLetters = wordMatchingResult.getHebrewTest();
      List<HebrewLetter> expressionLetters = wordMatchingResult
            .getHebrewDictionary();

      Collections.reverse(answerLetters);
      Collections.reverse(expressionLetters);

      result.setOkay(wordMatchingResult.isOkay());

      for (int i = 0; i < answerLetters.size(); i++)
      {
         if (answerLetters.get(i).equals(expressionLetters.get(i)))
         {
            result.getLetterFeedbackImages().add(LetterFeedbackImage.make(
                  expressionLetters.get(i), answerLetters.get(i), true));
         }
         else
         {
            result.getLetterFeedbackImages().add(LetterFeedbackImage.make(
                  expressionLetters.get(i), answerLetters.get(i), false));
         }
         result.addToWidth(Math.max(expressionLetters.get(i).getPixelWidth(),
               answerLetters.get(i).getPixelWidth()));
      }

      result.setAnswerLettersSize(answerLetters.size());
      result.setExpressionLettersSize(expressionLetters.size());
      result.reverseLetterFeedbackImage();

      return result;
   }

}
