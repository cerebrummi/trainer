package vokabeltrainer.panels.notifications;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;

import javax.swing.JDialog;

import vokabeltrainer.IconPanel;
import vokabeltrainer.common.main.AppImages;
import vokabeltrainer.common.main.View;

public class EmptyDialog extends JDialog
{
   private static final long serialVersionUID = 1134423942957856982L;

   public EmptyDialog(View view)
   {
      super(view.getjFrame(), "", Dialog.ModalityType.MODELESS);

      setSize(200, 200);
      getContentPane().setPreferredSize(new Dimension(200, 200));
      IconPanel content = new IconPanel(AppImages.getEmpty(), 200, 200);
      getContentPane().add(content);
      setUndecorated(true);
      this.setBackground(new Color(0, 0, 0, 0));
   }

}