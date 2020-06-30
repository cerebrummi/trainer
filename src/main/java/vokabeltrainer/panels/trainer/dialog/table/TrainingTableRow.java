package vokabeltrainer.panels.trainer.dialog.table;

import java.util.List;
import java.util.Set;

import vokabeltrainer.Command;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.ExpressionKind;

public class TrainingTableRow
{
   private String field;
   private int toBeRepeatedWords;
   private int notStudiedWords;
   private int amountOfNewWords;
   private boolean fieldDone;
   private Command fieldOfTraining;
   private String chapter;
   private ExpressionKind kind;
   private List<Expression> expressionListNewWords;
   private Set<Expression> expressionListOldWords;
   private boolean started;

   public String getField()
   {
      return field;
   }

   public void setField(String field)
   {
      this.field = field;
   }

   public int getToBeRepeatedWords()
   {
      return toBeRepeatedWords;
   }

   public void setToBeRepeatedWords(int totalWords)
   {
      this.toBeRepeatedWords = totalWords;
   }

   public int getNotStudiedWords()
   {
      return notStudiedWords;
   }

   public void setNotStudiedWords(int notStudiedWords)
   {
      this.notStudiedWords = notStudiedWords;
   }

   public int getAmountOfNewWords()
   {
      return amountOfNewWords;
   }

   public void setAmountOfNewWords(int amountOfNewWords)
   {
      this.amountOfNewWords = amountOfNewWords;
   }

   public boolean isFieldDone()
   {
      return fieldDone;
   }

   public void setFieldDone(boolean fieldDone)
   {
      this.fieldDone = fieldDone;
   }

   public Command getFieldOfTraining()
   {
      return fieldOfTraining;
   }

   public void setFieldOfTraining(Command fieldOfTraining)
   {
      this.fieldOfTraining = fieldOfTraining;
   }

   public String getChapter()
   {
      return chapter;
   }

   public void setChapter(String chapter)
   {
      this.chapter = chapter;
   }

   public ExpressionKind getKind()
   {
      return kind;
   }

   public void setKind(ExpressionKind kind)
   {
      this.kind = kind;
   }

   public List<Expression> getExpressionListNewWords()
   {
      return expressionListNewWords;
   }

   public void setExpressionListNewWords(List<Expression> listSelected)
   {
      this.expressionListNewWords = listSelected;
   }

   public Set<Expression> getExpressionListOldWords()
   {
      return expressionListOldWords;
   }

   public void setExpressionListOldWords(Set<Expression> expressionListOldWords)
   {
      this.expressionListOldWords = expressionListOldWords;
   }

   public boolean isStarted()
   {
      return started;
   }

   public void setStarted(boolean started)
   {
      this.started = started;
   }
}
