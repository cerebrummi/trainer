package vokabeltrainer.panels.trainer;

import java.util.List;
import vokabeltrainer.Command;
import vokabeltrainer.panels.TrainerView;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class TrainerController implements TrainerControllerConnector
{
   private TrainerView trainerView;
   private Language languageDirection;
   private Command fieldOfTraining;
   private List<Expression> newExpressions;
   private List<Expression> oldExpressions;

   public TrainerController(Language languageDirection, Command fieldOfTraining,
         List<Expression> newExpressions, List<Expression> oldExpressions)
   {
      this.languageDirection = languageDirection;
      this.fieldOfTraining = fieldOfTraining;
      this.newExpressions = newExpressions;
      this.oldExpressions = oldExpressions;

      trainerView = new TrainerView(this);
      trainerView.init();
   }

   public TrainerView getTrainerView()
   {
      return trainerView;
   }

   public Language getLanguageDirection()
   {
      return languageDirection;
   }

   public Command getFieldOfTraining()
   {
      return fieldOfTraining;
   }

   public List<Expression> getNewExpressions()
   {
      return newExpressions;
   }

   public List<Expression> getOldExpressions()
   {
      return oldExpressions;
   }
}
