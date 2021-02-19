package vokabeltrainer.panels.trainer;

import vokabeltrainer.types.Expression;

public class NikudResult
{
   private boolean okay = true;
   private boolean dictionaryEmpty;
   private boolean answerEmpty;
   private Expression expression;

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

}
