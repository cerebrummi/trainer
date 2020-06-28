package vokabeltrainer.panels.trainer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vokabeltrainer.types.Expression;

public class Result
{
   private boolean okay = true;
   private List<BufferedImage> letterFeedbackImages = new ArrayList<>();
   private int width;
   private int answerLettersSize;
   private int expressionLettersSize;
   private Expression expression;

   public Expression getExpression()
   {
      return expression;
   }

   public void setExpression(Expression expression)
   {
      this.expression = expression;
   }

   public boolean isOkay()
   {
      return okay;
   }

   public void setOkay(boolean okay)
   {
      this.okay = okay;
   }

   public List<BufferedImage> getLetterFeedbackImages()
   {
      return letterFeedbackImages;
   }
   
   public void reverseLetterFeedbackImage()
   {
      Collections.reverse(letterFeedbackImages);
   }

   public int getWidth()
   {
      return width;
   }

   public void addToWidth(int pixelWidth)
   {
      width += pixelWidth;
   }

   public int getAnswerLettersSize()
   {
      return answerLettersSize;
   }

   public void setAnswerLettersSize(int answerLettersSize)
   {
      this.answerLettersSize = answerLettersSize;
   }

   public int getExpressionLettersSize()
   {
      return expressionLettersSize;
   }

   public void setExpressionLettersSize(int expressionLettersSize)
   {
      this.expressionLettersSize = expressionLettersSize;
   }
}
