package vokabeltrainer.panels.dialogs;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;

import javax.swing.JDialog;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.IconPanel;
import vokabeltrainer.common.Common;

public class EmptyDialog extends JDialog
{
   private static final long serialVersionUID = 1134423942957856982L;

   public EmptyDialog()
   {
      super(Common.getjFrame(), "", Dialog.ModalityType.MODELESS);

      setSize(200, 200);
      getContentPane().setPreferredSize(new Dimension(121, 100));
      IconPanel content = new IconPanel(ApplicationImages.getEmpty(), 200, 200);
      getContentPane().add(content);
      setUndecorated(true);
      this.setBackground(new Color(0, 0, 0, 0));
   }

}