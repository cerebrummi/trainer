package vokabeltrainer.panels.notifications;

import javax.swing.JDialog;
import javax.swing.Timer;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;

public class ErrorNotification
{
   private ErrorNotification()
   {

   }

   public static void display(App app, Common common, View view, String header, String top, String middle,
         String bottom)
   {
      JDialog dialog = new ErrorDialog(app, common, view, header, top, middle, bottom);
      dialog.setLocationRelativeTo(view.getjFrame());
      dialog.setVisible(true);

      new Timer(50000, _ -> {
         dialog.setVisible(false);
      }).start();
   }
}
