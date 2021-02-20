package vokabeltrainer.panels.trainer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.types.Expression;

public class NikudResult
{
   private boolean okay = true;
   private boolean dictionaryEmpty;
   private boolean answerEmpty;
   private Expression expression;
   private List<BufferedImage> feedbackImageList = new ArrayList<BufferedImage>();

   public boolean isOkay()
   {
      return okay;
   }

   public void setOkay(boolean okay)
   {
      this.okay = okay;
   }

   public boolean isDictionaryEmpty()
   {
      return dictionaryEmpty;
   }

   public void setDictionaryEmpty(boolean dictionaryEmpty)
   {
      this.dictionaryEmpty = dictionaryEmpty;
   }

   public boolean isAnswerEmpty()
   {
      return answerEmpty;
   }

   public void setAnswerEmpty(boolean answerEmpty)
   {
      this.answerEmpty = answerEmpty;
   }

   public Expression getExpression()
   {
      return expression;
   }

   public void setExpression(Expression expression)
   {
      this.expression = expression;
   }

   public List<BufferedImage> getFeedbackImageList()
   {
      return feedbackImageList;
   }

   public void setFeedbackImageList(List<BufferedImage> feedbackImageList)
   {
      this.feedbackImageList = feedbackImageList;
   }
   
   public void addFeedbackImageList(List<BufferedImage> list)
   {
      this.feedbackImageList.addAll(list);
   }
   
   public void addFeedbackImage(BufferedImage image)
   {
      this.feedbackImageList.add(image);
   }
}
