package vokabeltrainer.panels.trainer;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.LetterType;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.words.WordLetterMatching;
import vokabeltrainer.words.WordLetterMatchingResult;

public class NikudResultFactory
{

   private NikudResultFactory()
   {
      // nothing
   }

   public static Result getResultDtoNikudSentence(Expression expression,
         String answer, Font hebrewFont)
   {

      String[] expressionArray = expression.getHebrew()
            .split(NikudLetter.SPACE.getUnicode());

      String[] answerArray = answer.split(NikudLetter.SPACE.getUnicode());

      if (expressionArray.length == 1 && answerArray.length == 1)
      {
         return getResultDtoNikud(expression, answer, hebrewFont);
      }

      if (expressionArray.length == answerArray.length)
      {
         Result result = new Result();
         result.setExpression(expression);
         List<Result> resultList = new ArrayList<>(expressionArray.length);
         for (int i = expressionArray.length - 1; i >= 0; i--)
         {
            resultList.add(getResultDtoNikudString(expressionArray[i],
                  answerArray[i], new Result(), hebrewFont));
         }

         result.setOkay(true);
         result.setAnswerEmpty(true);
         result.setDictionaryEmpty(true);
         int index = 0;

         for (Result singleResult : resultList)
         {
            result.setOkay(result.isOkay() && singleResult.isOkay());
            result.setAnswerEmpty(
                  result.isAnswerEmpty() && singleResult.isAnswerEmpty());
            result.setDictionaryEmpty(result.isDictionaryEmpty()
                  && singleResult.isDictionaryEmpty());
            if (index > 0)
            {
               result.addFeedbackImage(LetterFeedbackImage.makeSpace());
            }
            result.addFeedbackImageList(singleResult.getFeedbackImageList());
            index++;
         }

         return result;
      }

      return getResultDtoNikud(expression, answer, hebrewFont);
   }

   private static Result getResultDtoNikud(Expression expression,
         String answer, Font hebrewFont)
   {
      Result result = new Result();
      result.setExpression(expression);

      return getResultDtoNikudString(expression.getHebrew(), answer, result, hebrewFont);
   }

   private static Result getResultDtoNikudString(String dictionary,
         String answer, Result result, Font hebrewFont)
   {

      WordLetterMatchingResult matchingResult = WordLetterMatching.matchLetter(
            LetterHelper.findNikudLetterForAnalysisList(dictionary),
            LetterHelper.findNikudLetterForAnalysisList(answer),
            LetterType.NIKUD);

      result.setOkay(matchingResult.isOkay());

      List<LetterForAnalysis> dictionaryList = matchingResult.getDictionary();
      List<LetterForAnalysis> answerList = matchingResult.getAnswer();

      List<BufferedImage> feedbackImageList = new ArrayList<BufferedImage>();
      for (int i = 0; i < dictionaryList.size() && i < answerList.size(); i++)
      {
         boolean letterresult = LetterHelper.areLettersEqual(
               dictionaryList.get(i), answerList.get(i));
         
         feedbackImageList
               .add(LetterFeedbackImage.make(dictionaryList.get(i),
                     answerList.get(i), letterresult, hebrewFont));
         result.setOkay(result.isOkay() && letterresult);
      }
      result.setFeedbackImageList(feedbackImageList);
      return result;
   }
}
