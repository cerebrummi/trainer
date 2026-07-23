package vokabeltrainer.common;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.panels.MainView;

public class MainController implements MainControllerConnector
{
   private MainView mainView;

   public MainController(App app, Common common)
   {
      mainView = new MainView(app, common);
   }

   public MainView getMainView()
   {
      return mainView;
   }

}
