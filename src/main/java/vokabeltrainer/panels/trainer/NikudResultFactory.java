package vokabeltrainer.panels.trainer;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.common.main.App;
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.LetterType;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.HebrewType;
import vokabeltrainer.words.WordLetterMatching;
import vokabeltrainer.words.WordLetterMatchingResult;

public class NikudResultFactory
{

   private NikudResultFactory()
   {
      // nothing
   }

   public static BestResult getBestResultPossible(App app, Expression expression,
         String answer, Font hebrewFont)

   {
      if (expression.getLL().isSimpleHebrew())
      {
         BestResult bestResult = new BestResult(Selection.SIMPLE);
         bestResult.setResultHebrew(getResultDtoNikudSentence(app, expression,
               answer, hebrewFont, HebrewType.SIMPLE));
         return bestResult;
      }

      BestResult bestResult = new BestResult(Selection.PLENE_DEFEKTIV);
      bestResult.setResultPlene(getResultDtoNikudSentence(app, expression, answer,
            hebrewFont, HebrewType.PLENE));
      bestResult.setResultDefektiv(getResultDtoNikudSentence(app, expression, answer,
            hebrewFont, HebrewType.DEFEKTIV));
      return bestResult;
   }

   public static Result getResultDtoNikudSentence(App app, Expression expression,
         String answer, Font hebrewFont, HebrewType selectionType)
   {
      String[] expressionArray = expression.getLL()
            .getHewbrewAccordingToType(selectionType)
            .split(NikudLetter.SPACE.getUnicode());

      String[] answerArray = answer.split(NikudLetter.SPACE.getUnicode());

      if (expressionArray.length == 1 && answerArray.length == 1)
      {
         return getResultDtoNikud(app, expression, answer, hebrewFont,
               selectionType);
      }

      if (expressionArray.length == answerArray.length)
      {
         Result result = new Result(selectionType);
         result.setExpression(expression);
         List<Result> resultList = new ArrayList<>(expressionArray.length);
         for (int i = expressionArray.length - 1; i >= 0; i--)
         {
            resultList.add(getResultDtoNikudString(app, expressionArray[i],
                  answerArray[i], new Result(selectionType)));
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
               result.addFeedbackImage(LetterFeedbackImage.makeSpace(app));
               result.addAnswerSpace(new LetterForAnalysis(NikudLetter.SPACE));
               result.addDictionarySpace(
                     new LetterForAnalysis(NikudLetter.SPACE));
            }
            result.addFeedbackImageList(singleResult.getFeedbackImageList());
            result.addAnswer(singleResult.getAnswer()); // for unit testing
            result.addDictionary(singleResult.getDictionary()); // for unit
                                                                // testing
            index++;
         }

         return result;
      }

      return getResultDtoNikud(app, expression, answer, hebrewFont, selectionType);
   }

   private static Result getResultDtoNikud(App app, Expression expression, String answer,
         Font hebrewFont, HebrewType selectionType)
   {
      Result result = new Result(selectionType);
      result.setExpression(expression);

      return getResultDtoNikudString(app, 
            expression.getLL().getHewbrewAccordingToType(selectionType), answer,
            result);
   }

   private static Result getResultDtoNikudString(App app, String dictionary,
         String answer, Result result)
   {

      WordLetterMatchingResult matchingResult = WordLetterMatching.matchLetters(
            LetterHelper.findLetterForAnalysisList(dictionary,
                  LetterType.HEBREW),
            LetterHelper.findLetterForAnalysisList(answer, LetterType.HEBREW),
            LetterType.HEBREW);

      result.setAnswerEmpty(matchingResult.isAnswerEmpty());
      result.setDictionaryEmpty(matchingResult.isDictionaryEmpty());
      result.setOkay(matchingResult.isOkay());
      result.setSimilarity(matchingResult.getSimilarity());

      List<LetterForAnalysis> dictionaryList = matchingResult.getDictionary();
      List<LetterForAnalysis> answerList = matchingResult.getAnswer();

      result.setDictionary(matchingResult.getDictionary()); // for unit testing
      result.setAnswer(matchingResult.getAnswer()); // for unit testing

      List<BufferedImage> feedbackImageList = new ArrayList<BufferedImage>();
      for (int i = 0; i < dictionaryList.size() && i < answerList.size(); i++)
      {
         boolean letterresult = LetterHelper
               .areLettersEqual(dictionaryList.get(i), answerList.get(i));

         feedbackImageList.add(LetterFeedbackImage.make(app, dictionaryList.get(i),
               answerList.get(i), letterresult));
         result.setOkay(result.isOkay() && letterresult);
      }
      result.setFeedbackImageList(feedbackImageList);
      return result;
   }
}
