package vokabeltrainer.panels.trainer;

import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.editing.LetterType;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.words.WordLetterMatching;
import vokabeltrainer.words.WordLetterMatchingResult;
import vokabeltrainer.words.WordMatching;
import vokabeltrainer.words.WordMatchingResult;

public class NikudResultFactory
{

   private NikudResultFactory()
   {
      
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
         for (int i = expressionArray.length-1; i >=0 ; i--)
         {
            resultList.add(getResultDtoNikudString(expressionArray[i],
                  answerArray[i], new NikudResult()));
         }
         
         result.setOkay(true);
         result.setAnswerEmpty(true);
         result.setDictionaryEmpty(true);
         
         for (NikudResult singleResult : resultList)
         {
            result.setOkay(result.isOkay() && singleResult.isOkay());
            result.setAnswerEmpty(
                  result.isAnswerEmpty() && singleResult.isAnswerEmpty());
            result.setDictionaryEmpty(result.isDictionaryEmpty()
                  && singleResult.isDictionaryEmpty());
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
   
   private static NikudResult getResultDtoNikudString(String dictionary, String answer,
         NikudResult result)
   {
      // make List<LetterOfAnalysis> from String TODO
      
      
      
      
      WordLetterMatchingResult matchingResult = WordLetterMatching.matchLetter(dictionaryLoA, answerLoA, LetterType.NIKUD);
      
      
      
      
      
      
      
      
      
      return result;
   }
}
