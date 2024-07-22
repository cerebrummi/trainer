package vokabeltrainer.panels.trainer;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.LetterType;
import vokabeltrainer.editing.SwedishLetter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.words.WordLetterMatching;
import vokabeltrainer.words.WordLetterMatchingResult;

public class SwedishResultFactory
{
   private SwedishResultFactory()
   {
      // nothing
   }
   
   public static BestResult getBestResultPossible(Expression expression,
         String answer, Font swedishFont)

   {
      BestResult bestResult = new BestResult(Selection.SWEDISH);
      bestResult.setResultSwedish(getResultDtoSwedishSentence(expression,
            answer, swedishFont));
      return bestResult;
   }

   public static Result getResultDtoSwedishSentence(Expression expression,
         String answer, Font swedishFont)
   {
      String[] expressionArray = expression.getLL()
            .getSwedish()
            .split(SwedishLetter.SPACE.getUnicode());

      String[] answerArray = answer.split(SwedishLetter.SPACE.getUnicode());

      if (expressionArray.length == 1 && answerArray.length == 1)
      {
         return getResultDtoSwedish(expression, answer, swedishFont);
      }

      if (expressionArray.length == answerArray.length)
      {
         Result result = new Result();
         result.setExpression(expression);
         List<Result> resultList = new ArrayList<>(expressionArray.length);
         for (int i = 0; i < expressionArray.length; i++)
         {
            resultList.add(getResultDtoSwedishString(expressionArray[i],
                  answerArray[i], new Result(), swedishFont));
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
               result.addAnswerSpace(new LetterForAnalysis(SwedishLetter.SPACE));
               result.addDictionarySpace(new LetterForAnalysis(SwedishLetter.SPACE));
            }
            result.addFeedbackImageList(singleResult.getFeedbackImageList());
            result.addAnswer(singleResult.getAnswer()); // for unit testing
            result.addDictionary(singleResult.getDictionary()); // for unit testing
            index++;
         }

         return result;
      }

      return getResultDtoSwedish(expression, answer, swedishFont);
   }

   private static Result getResultDtoSwedish(Expression expression, String answer,
         Font swedishFont)
   {
      Result result = new Result();
      result.setExpression(expression);

      return getResultDtoSwedishString(
            expression.getLL().getSwedish(),
            answer, result, swedishFont);
   }

   private static Result getResultDtoSwedishString(String dictionary,
         String answer, Result result, Font swedishFont)
   {
      WordLetterMatchingResult matchingResult = WordLetterMatching.matchLetters(
            LetterHelper.findLetterForAnalysisList(dictionary, LetterType.SWEDISH),
            LetterHelper.findLetterForAnalysisList(answer, LetterType.SWEDISH),
            LetterType.SWEDISH);

      result.setAnswerEmpty(matchingResult.isAnswerEmpty());
      result.setDictionaryEmpty(matchingResult.isDictionaryEmpty());
      result.setOkay(matchingResult.isOkay());
      result.setSimilarity(matchingResult.getSimilarity());

      List<LetterForAnalysis> dictionaryList = matchingResult.getDictionary();
      Collections.reverse(dictionaryList);
      List<LetterForAnalysis> answerList = matchingResult.getAnswer();
      Collections.reverse(answerList);

      result.setDictionary(matchingResult.getDictionary()); // for unit testing
      result.setAnswer(matchingResult.getAnswer()); // for unit testing

      List<BufferedImage> feedbackImageList = new ArrayList<BufferedImage>();
      for (int i = 0; i < dictionaryList.size() && i < answerList.size(); i++)
      {
         boolean letterresult = LetterHelper
               .areLettersEqual(dictionaryList.get(i), answerList.get(i));

         feedbackImageList.add(LetterFeedbackImage.make(dictionaryList.get(i),
               answerList.get(i), letterresult, swedishFont));
         result.setOkay(result.isOkay() && letterresult);
      }
      result.setFeedbackImageList(feedbackImageList);
      return result;
   }
}
