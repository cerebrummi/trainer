package vokabeltrainer.panels.trainer.dialog.table;

import java.util.List;

import vokabeltrainer.Command;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.ExpressionKind;

public class TrainingTableRow
{
   private String field;
   private int totalWords;
   private int notStudiedWords;
   private int amountOfNewWords;
   private boolean fieldDone;
   private Command fieldOfTraining;
   private String chapter;
   private ExpressionKind kind;
   private List<Expression> expressionList;

   @Override
   public String toString()
   {
      return "TrainingTableRow [field=" + field + ", totalWords=" + totalWords
            + ", notStudiedWords=" + notStudiedWords + ", amountOfNewWords="
            + amountOfNewWords + ", fieldDone=" + fieldDone
            + ", fieldOfTraining=" + fieldOfTraining + ", chapter=" + chapter
            + ", kind=" + kind + ", expressionList=" + expressionList + "]";
   }

   public String getField()
   {
      return field;
   }

   public void setField(String field)
   {
      this.field = field;
   }

   public int getTotalWords()
   {
      return totalWords;
   }

   public void setTotalWords(int totalWords)
   {
      this.totalWords = totalWords;
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

   public List<Expression> getExpressionList()
   {
      return expressionList;
   }

   public void setExpressionList(List<Expression> listSelected)
   {
      this.expressionList = listSelected;
   }
}
