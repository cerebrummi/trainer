package vokabeltrainer.panels.trainer;

import java.util.List;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.FieldOfTraining;
import vokabeltrainer.types.LanguageDirection;

public interface TrainerControllerConnector
{
   public LanguageDirection getLanguageDirection();

   public FieldOfTraining getFieldOfTraining();

   public List<Expression> getNewExpressions();

   public List<Expression> getOldExpressions();

   public void setAdditionalInfo();

   public void setNextTest();

   public void send(Common common, View view);

   public void stopTraining(Common common, View view, boolean finished);

   public int getNewWordsToLearn();

   public int getOldWordsToRepeat();

   public Expression getCurrentExpression();

   public void removeFirstExpressionToBeTested();

   public List<Expression> getExpressionsToBeTested();

   public void resultHtoDOkay(Common common, View view);

   public void resultHtoDUndecided(Common common, View view);

   public void resultHtoDFalse(Common common, View view);

   public void toggleSound();

   public void toggleLetterPictures();

   public void setGrammarInfo();
}
