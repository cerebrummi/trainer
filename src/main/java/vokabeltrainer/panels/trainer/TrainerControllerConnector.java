package vokabeltrainer.panels.trainer;

import java.util.List;
import java.util.Set;

import vokabeltrainer.Command;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public interface TrainerControllerConnector
{
   public Language getLanguageDirection();
   public Command getFieldOfTraining();
   public List<Expression> getNewExpressions();
   public List<Expression> getOldExpressions();
}
