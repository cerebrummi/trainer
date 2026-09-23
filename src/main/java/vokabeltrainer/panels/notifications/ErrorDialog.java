package vokabeltrainer.panels.notifications;

import java.awt.Color;
import java.awt.Dialog;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.tonionlayout.TotemLayout;

public class ErrorDialog extends JDialog
{
   private static final long serialVersionUID = -2919642120086961576L;

   public ErrorDialog(App app, Common common, View view, String textA, String textB, String middle, String textD)
   {
      super(view.getjFrame(), "", Dialog.ModalityType.MODELESS);

      setSize(400, 200);
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical);
      vertical.setLayout(verticalLayout);
      vertical.setBackground(app.appColors.getWhite());
      vertical.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(app.appColors.dictionary.getShredderButtonBorder()),
            common.getTranslator()
                  .realisticTranslate(app, Translation.FEHLERMELDUNG)));

      JLabel rowA = new JLabel(textA);
      rowA.setFont(app.appFonts.germanFont.deriveFont(24F));
      JLabel rowB = new JLabel(textB);
      rowB.setFont(app.appFonts.germanFont.deriveFont(20F));
      JLabel rowC = new JLabel(middle);
      rowC.setFont(app.appFonts.germanFont.deriveFont(20F));
      JLabel rowD = new JLabel(textD);
      rowD.setFont(app.appFonts.germanFont.deriveFont(16F));

      vertical.add(rowA);
      vertical.add(new JPanel());
      vertical.add(rowB);
      vertical.add(rowC);
      vertical.add(new JPanel());
      vertical.add(rowD);

      getContentPane().add(vertical);
      setUndecorated(true);
      this.setBackground(new Color(0, 0, 0, 0));
   }

}
