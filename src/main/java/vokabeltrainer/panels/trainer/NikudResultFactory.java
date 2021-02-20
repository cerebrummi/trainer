package vokabeltrainer.panels.trainer;

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

   public static NikudResult getResultDtoNikudSentence(Expression expression,
         String answer)
   {

      String[] expressionArray = expression.getHebrew()
            .split(NikudLetter.SPACE.getUnicode());

      String[] answerArray = answer.split(NikudLetter.SPACE.getUnicode());

      if (expressionArray.length == 1 && answerArray.length == 1)
      {
         return getResultDtoNikud(expression, answer);
      }

      if (expressionArray.length == answerArray.length)
      {
         NikudResult result = new NikudResult();
         result.setExpression(expression);
         List<NikudResult> resultList = new ArrayList<>(expressionArray.length);
         for (int i = expressionArray.length - 1; i >= 0; i--)
         {
            resultList.add(getResultDtoNikudString(expressionArray[i],
                  answerArray[i], new NikudResult()));
         }

         result.setOkay(true);
         result.setAnswerEmpty(true);
         result.setDictionaryEmpty(true);
         int index = 0;

         for (NikudResult singleResult : resultList)
         {
            result.setOkay(result.isOkay() && singleResult.isOkay());
            result.setAnswerEmpty(
                  result.isAnswerEmpty() && singleResult.isAnswerEmpty());
            result.setDictionaryEmpty(result.isDictionaryEmpty()
                  && singleResult.isDictionaryEmpty());
            if(index > 0)
            {
               result.addFeedbackImage(LetterFeedbackImage.makeNikudSpace());
            }
            result.addFeedbackImageList(singleResult.getFeedbackImageList());
            index++;
         }

         return result;
      }

      return getResultDtoNikud(expression, answer);
   }

   private static NikudResult getResultDtoNikud(Expression expression,
         String answer)
   {
      NikudResult result = new NikudResult();
      result.setExpression(expression);

      return getResultDtoNikudString(expression.getHebrew(), answer, result);
   }

   private static NikudResult getResultDtoNikudString(String dictionary,
         String answer, NikudResult result)
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
         feedbackImageList
               .add(LetterFeedbackImage.makeNikud(dictionaryList.get(i),
                     answerList.get(i), LetterHelper.areLettersEqual(
                           dictionaryList.get(i), answerList.get(i))));
      }
      result.setFeedbackImageList(feedbackImageList);
      return result;
   }
}
