package vokabeltrainer.panels.dialogs;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;

import javax.swing.JDialog;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.IconPanel;
import vokabeltrainer.common.Common;

public class OkaySaveExpressionsDialog extends JDialog
{
   private static final long serialVersionUID = 3958358982334677951L;

   public OkaySaveExpressionsDialog()
   {
      super(Common.getjFrame(), "", Dialog.ModalityType.MODELESS);

      setSize(200, 200);
      getContentPane().setPreferredSize(new Dimension(121, 100));
      IconPanel content = new IconPanel(ApplicationImages.getOkaySave(), 200,
            200);
      getContentPane().add(content);
      setUndecorated(true);
      setBackground(new Color(0, 0, 0, 0));
      setAlwaysOnTop(true);
   }

}
