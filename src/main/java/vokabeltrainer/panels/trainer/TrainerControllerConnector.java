package vokabeltrainer.panels.trainer;

import java.util.List;
import vokabeltrainer.Command;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public interface TrainerControllerConnector
{
   public Language getLanguageDirection();
   public Command getFieldOfTraining();
   public List<Expression> getNewExpressions();
   public List<Expression> getOldExpressions();
   public void setAdditionalInfo();
   public void setTranscription();
   public void setNextTest();
   public void send();
   public void stopTraining(boolean finished);
   public int getNewWordsToLearn();
   public int getOldWordsToRepeat();
   public Expression getCurrentExpression();
   public void removeFirstExpressionToBeTested();
   public List<Expression> getExpressionsToBeTested();
   public void resultHtoDOkay();
   public void resultHtoDUndecided();
   public void resultHtoDFalse();
   public void toggleSound();
}
