package vokabeltrainer.common;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.panels.MainView;

public class MainController implements MainControllerConnector
{
   private MainView mainView;

   public MainController(Common common)
   {
      mainView = new MainView(common);
   }

   public MainView getMainView()
   {
      return mainView;
   }

}
