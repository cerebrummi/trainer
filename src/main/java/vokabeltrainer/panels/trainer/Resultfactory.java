package vokabeltrainer.panels.trainer;

import java.util.List;

import vokabeltrainer.LetterFeedbackImage;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.types.Expression;

public class Resultfactory
{

   public static Result getResultDtoH(Expression expression, String answer)
   {
      Result result = new Result();
      result.setExpression(expression);

      List<HebrewLetter> answerLetters = HebrewLetter.findHebrewLetters(answer);
      List<HebrewLetter> expressionLetters = HebrewLetter
            .findHebrewLetters(expression.getHebrew());

      for (int i = 0; i < answerLetters.size()
            && i < expressionLetters.size(); i++)
      {
         if (answerLetters.get(i).equals(expressionLetters.get(i)))
         {
            result.getLetterFeedbackImages()
                  .add(LetterFeedbackImage.make(answerLetters.get(i), true));
         }
         else
         {
            result.setOkay(false);
            result.getLetterFeedbackImages()
                  .add(LetterFeedbackImage.make(answerLetters.get(i), false));
         }
         result.addToWidth(answerLetters.get(i).getPixelWidth());
      }

      if (answerLetters.size() - expressionLetters.size() > 0)
      {
         result.setOkay(false);
         for (int i = expressionLetters.size(); i < answerLetters.size(); i++)
         {
            result.getLetterFeedbackImages()
                  .add(LetterFeedbackImage.make(answerLetters.get(i), false));
            result.addToWidth(answerLetters.get(i).getPixelWidth());
         }
      }

      if (expressionLetters.size() - answerLetters.size() == 1)
      {
         if (!HebrewLetter.isQuestionmark(
               expressionLetters.get(expressionLetters.size() - 1)))
         {
            result.setOkay(false);
            result.getLetterFeedbackImages()
                  .add(LetterFeedbackImage.make(HebrewLetter.SPACE, false));
            result.addToWidth(HebrewLetter.SPACE.getPixelWidth());
         }
      }
      else if (expressionLetters.size() - answerLetters.size() > 0)
      {
         result.setOkay(false);
         for (int i = answerLetters.size(); i < expressionLetters.size(); i++)
         {
            result.getLetterFeedbackImages()
                  .add(LetterFeedbackImage.make(HebrewLetter.SPACE, false));
            result.addToWidth(HebrewLetter.SPACE.getPixelWidth());
         }
      }

      result.setAnswerLettersSize(answerLetters.size());
      result.setExpressionLettersSize(expressionLetters.size());
      result.reverseLetterFeedbackImage();

      return result;
   }

}
