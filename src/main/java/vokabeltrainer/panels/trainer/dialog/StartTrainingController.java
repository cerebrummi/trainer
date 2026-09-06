package vokabeltrainer.panels.trainer.dialog;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;

public class StartTrainingController implements StartTrainingControllerConnector
{
   private StartTrainingView startTrainingView;

   public StartTrainingController(App app, Common common, Model model, View view)
   {
      this.startTrainingView = new StartTrainingView(app, common, model, view, this);
   }

   public StartTrainingView getStartTrainingView()
   {
      return startTrainingView;
   }
}
