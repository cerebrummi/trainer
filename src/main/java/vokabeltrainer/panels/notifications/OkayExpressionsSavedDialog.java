package vokabeltrainer.panels.notifications;

import java.awt.Dialog;
import java.awt.Dimension;
import java.io.Serial;

import javax.swing.JDialog;

import vokabeltrainer.IconPanel;
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.View;

public class OkayExpressionsSavedDialog extends JDialog
{
   @Serial
   private static final long serialVersionUID = 3958358982334677951L;

   public OkayExpressionsSavedDialog(App app, View view)
   {
      super(view.getjFrame(), "", Dialog.ModalityType.MODELESS);

      setSize(200, 200);
      getContentPane().setPreferredSize(new Dimension(200, 200));
      IconPanel content = new IconPanel(app.appImages.getOkaySave(), 200,
            200);
      getContentPane().add(content);
      setUndecorated(true);
      setBackground(app.appColors.getTransparent());
      setAlwaysOnTop(true);
   }

}
