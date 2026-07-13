package vokabeltrainer.common;

import vokabeltrainer.panels.MainView;

public class MainController implements MainControllerConnector
{
   private MainView mainView;

   public MainController(Initializer initializer)
   {
      mainView = new MainView(initializer);
   }

   public MainView getMainView()
   {
      return mainView;
   }

}
