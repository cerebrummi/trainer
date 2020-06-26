package vokabeltrainer.panels.trainer.dialog;

import java.util.Set;

import vokabeltrainer.types.Expression;

public interface StartTrainingControllerConnector
{
   public Set<Expression> getOldExpressionsDToH();
   public Set<Expression> getOldExpressionsHToD();
}
