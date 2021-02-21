package vokabeltrainer.panels.trainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.words.WordMatching;
import vokabeltrainer.words.WordMatchingResult;

public class Resultfactory
{
   private Resultfactory()
   {
      // nothing
   }

   public static Result getResultDtoHScentence(Expression expression,
         String answer)
   {
      String[] expressionArray = expression.getHebrew()
            .split(HebrewLetter.SPACE.getUnicode());

      String[] answerArray = answer.split(HebrewLetter.SPACE.getUnicode());

      if (expressionArray.length == 1 && answerArray.length == 1)
      {
         return getResultDtoH(expression, answer);
      }

      if (expressionArray.length == answerArray.length)
      {
         Result result = new Result();
         result.setExpression(expression);
         List<Result> resultList = new ArrayList<>(expressionArray.length);
         for (int i = expressionArray.length-1; i >=0 ; i--)
         {
            resultList.add(getResultDtoHString(expressionArray[i],
                  answerArray[i], new Result()));
         }

         result.setOkay(true);
         result.setAnswerEmpty(true);
         result.setDictionaryEmpty(true);
         for (Result singleResult : resultList)
         {
            result.setOkay(result.isOkay() && singleResult.isOkay());
            result.setAnswerEmpty(
                  result.isAnswerEmpty() && singleResult.isAnswerEmpty());
            result.setDictionaryEmpty(result.isDictionaryEmpty()
                  && singleResult.isDictionaryEmpty());
         }

         for (int i = 0; i < resultList.size(); i++)
         {
            if (i == 0)
            {
               result.addToWidth(resultList.get(i).getWidth());
               result.getLetterFeedbackImages()
                     .addAll(resultList.get(i).getLetterFeedbackImages());
               result.setAnswerLettersSize(result.getAnswerLettersSize()
                     + resultList.get(i).getAnswerLettersSize());
            }
            else
            {
               result.addToWidth(HebrewLetter.SPACE.getPixelWidth());
               result.addToWidth(resultList.get(i).getWidth());
               result.getLetterFeedbackImages().add(LetterFeedbackImage
                     .make(HebrewLetter.SPACE, HebrewLetter.SPACE, true));
               result.getLetterFeedbackImages()
                     .addAll(resultList.get(i).getLetterFeedbackImages());
               result.setAnswerLettersSize(result.getAnswerLettersSize() + 1);
               result.setAnswerLettersSize(result.getAnswerLettersSize()
                     + resultList.get(i).getAnswerLettersSize());
            }
         }

         return result;
      }

      return getResultDtoH(expression, answer);
   }

   private static Result getResultDtoH(Expression expression, String answer)
   {
      Result result = new Result();
      result.setExpression(expression);

      return getResultDtoHString(expression.getHebrew(), answer, result);
   }

   private static Result getResultDtoHString(String hebrew, String answer,
         Result result)
   {
      WordMatchingResult wordMatchingResult = WordMatching.matchHebrew(hebrew,
            answer);

      if (wordMatchingResult.isAnswerEmpty())
      {
         result.setAnswerEmpty(true);
         return result;
      }
      else if (wordMatchingResult.isDictionaryEmpty())
      {
         result.setDictionaryEmpty(true);
         return result;
      }

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
            result.getLetterFeedbackImages().add(LetterFeedbackImage
                  .make(expressionLetters.get(i), answerLetters.get(i), true));
         }
         else
         {
            result.getLetterFeedbackImages().add(LetterFeedbackImage
                  .make(expressionLetters.get(i), answerLetters.get(i), false));
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
