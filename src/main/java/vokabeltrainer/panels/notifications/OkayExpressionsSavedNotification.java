package vokabeltrainer.panels.notifications;

import javax.swing.JDialog;
import javax.swing.Timer;

import vokabeltrainer.common.Common;

public class OkayExpressionsSavedNotification
{

   private OkayExpressionsSavedNotification()
   {
      // nothing
   }

   public static void display()
   {
      JDialog dialog = new OkayExpressionsSavedDialog();
      dialog.setLocationRelativeTo(Common.getjFrame());
      dialog.setVisible(true);

      new Timer(1000, _ -> {
         dialog.setVisible(false);
      }).start();
   }

}
