package vokabeltrainer.panels.trainer;

import java.util.List;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
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

   public void setNextTest(App app);

   public void send(App app, Common common, Model model, View view);

   public void stopTraining(App app, Common common, Model model, View view, boolean finished);

   public int getNewWordsToLearn();

   public int getOldWordsToRepeat();

   public Expression getCurrentExpression();

   public void removeFirstExpressionToBeTested();

   public List<Expression> getExpressionsToBeTested();

   public void resultHtoDOkay(App app, Common common, Model model, View view);

   public void resultHtoDUndecided(App app, Common common, Model model, View view);

   public void resultHtoDFalse(App app, Common common, Model model, View view);

   public void toggleSound(App app);

   public void toggleLetterPictures(App app);

   public void setGrammarInfo();
}
