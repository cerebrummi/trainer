package vokabeltrainer.panels.trainer.dialog;

import java.util.Set;

import vokabeltrainer.common.Data;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class StartTrainingController implements StartTrainingControllerConnector
{
   private StartTrainingView startTrainingView;
   
   public StartTrainingController()
   {
      this.startTrainingView = new StartTrainingView(this);
   }

   public StartTrainingView getStartTrainingView()
   {
      return startTrainingView;
   }

   public Set<Expression> getOldExpressionsDToH()
   {
      return Data.findOldExpressionsToBeTested(Language.GERMAN);
   }
   
   public Set<Expression> getOldExpressionsHToD()
   {
      return Data.findOldExpressionsToBeTested(Language.HEBREW);
   }
}
