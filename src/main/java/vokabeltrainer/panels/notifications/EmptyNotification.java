package vokabeltrainer.panels.notifications;

import javax.swing.JDialog;
import javax.swing.Timer;

import vokabeltrainer.common.main.View;

public class EmptyNotification
{

   private EmptyNotification()
   {
      // nothing
   }

   public static void display(View view)
   {
      JDialog dialog = new EmptyDialog(view);
      dialog.setLocationRelativeTo(view.getjFrame());
      dialog.setVisible(true);

      new Timer(1000, _ -> {
         dialog.setVisible(false);
      }).start();
   }
}
