package vokabeltrainer.panels.trainer;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.editing.GermanLetter;
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.LetterType;
import vokabeltrainer.types.Expression;
import vokabeltrainer.words.WordLetterMatching;
import vokabeltrainer.words.WordLetterMatchingResult;

public class GermanResultFactory
{
   private GermanResultFactory()
   {
      // nothing
   }
   
   public static BestResult getBestResultPossible(Expression expression,
         String answer, Font germanFont)

   {
      BestResult bestResult = new BestResult(Selection.GERMAN);
      bestResult.setResultGerman(getResultDtoGermanSentence(expression,
            answer, germanFont));
      return bestResult;
   }

   public static Result getResultDtoGermanSentence(Expression expression,
         String answer, Font germanFont)
   {
      String[] expressionArray = expression.getLL()
            .getGerman()
            .split(GermanLetter.SPACE.getUnicode());

      String[] answerArray = answer.split(GermanLetter.SPACE.getUnicode());

      if (expressionArray.length == 1 && answerArray.length == 1)
      {
         return getResultDtoGerman(expression, answer, germanFont);
      }

      if (expressionArray.length == answerArray.length)
      {
         Result result = new Result();
         result.setExpression(expression);
         List<Result> resultList = new ArrayList<>(expressionArray.length);
         for (int i = 0; i < expressionArray.length; i++)
         {
            resultList.add(getResultDtoGermanString(expressionArray[i],
                  answerArray[i], new Result(), germanFont));
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
               result.addAnswerSpace(new LetterForAnalysis(GermanLetter.SPACE));
               result.addDictionarySpace(new LetterForAnalysis(GermanLetter.SPACE));
            }
            result.addFeedbackImageList(singleResult.getFeedbackImageList());
            result.addAnswer(singleResult.getAnswer()); // for unit testing
            result.addDictionary(singleResult.getDictionary()); // for unit testing
            index++;
         }

         return result;
      }

      return getResultDtoGerman(expression, answer, germanFont);
   }

   private static Result getResultDtoGerman(Expression expression, String answer,
         Font germanFont)
   {
      Result result = new Result();
      result.setExpression(expression);

      return getResultDtoGermanString(
            expression.getLL().getGerman(),
            answer, result, germanFont);
   }

   private static Result getResultDtoGermanString(String dictionary,
         String answer, Result result, Font germanFont)
   {
      WordLetterMatchingResult matchingResult = WordLetterMatching.matchLetters(
            LetterHelper.findLetterForAnalysisList(dictionary, LetterType.GERMAN),
            LetterHelper.findLetterForAnalysisList(answer, LetterType.GERMAN),
            LetterType.GERMAN);

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
               answerList.get(i), letterresult, germanFont));
         result.setOkay(result.isOkay() && letterresult);
      }
      result.setFeedbackImageList(feedbackImageList);
      return result;
   }
}
