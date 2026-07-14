package vokabeltrainer.panels.trainer.dialog;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;

public class StartTrainingController implements StartTrainingControllerConnector
{
   private StartTrainingView startTrainingView;

   public StartTrainingController(Common common, View view)
   {
      this.startTrainingView = new StartTrainingView(common, view, this);
   }

   public StartTrainingView getStartTrainingView()
   {
      return startTrainingView;
   }
}
