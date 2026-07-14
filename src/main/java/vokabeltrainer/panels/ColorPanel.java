package vokabeltrainer.panels;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Settings;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.resources.Buchstabenbilder;
import vokabeltrainer.resources.LetterIcons;
import vokabeltrainer.resources.LetterIconsHandwritten;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class ColorPanel extends JPanel
{
   private static final long serialVersionUID = 5974748523983524775L;
   
   private JButton applyButton;
   private String message = Settings.getWindowTitle()
         + " bitte neu starten.\nFehler: ";

   public ColorPanel(Common common, View view)
   {
      setLayout(new BullsEyeLayout(this));

      add(initChooseColormode());

      initController(common, view);
   }

   private Component initChooseColormode()
   {
      this.setBackground(ApplicationColors.getBackgroundGold());
      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 15);
      horizontal.setLayout(horizontalLayout);
      horizontal.setOpaque(false);
      horizontal.setBackground(ApplicationColors.getTransparent());

      applyButton = new JButton(new ImageIcon(ApplicationImages.getDarkmode()));

      horizontal.add(applyButton);

      return horizontal;
   }

   private void initController(Common common, View view)
   {
      applyButton.addActionListener(_ -> {
         applyButton.setEnabled(false);
         Settings.toggleDarkmodeOn();
         
         view.setUI();

         this.removeAll();
         this.invalidate();
         add(initChooseColormode());
         this.validate();
         this.repaint();

         view.getjFrame().getContentPane()
               .setBackground(ApplicationColors.getBackgroundGold());
         view.getjFrame().getContentPane().validate();
         view.getjFrame().getContentPane().repaint();

         try
         {
            LetterIcons.readNikud();
         }
         catch (Exception e1)
         {
            JOptionPane.showMessageDialog(null,
                  message + "Buchstaben Nikud Icons fehlen", "Nachricht",
                  JOptionPane.CLOSED_OPTION);
            System.exit(1);
         }

         try
         {
            LetterIconsHandwritten.readNikud();
         }
         catch (Exception e1)
         {
            JOptionPane.showMessageDialog(null,
                  message + "Buchstaben Nikud Handwritten Icons fehlen",
                  "Nachricht", JOptionPane.CLOSED_OPTION);
            System.exit(1);
         }

         try
         {
            Buchstabenbilder.reRead(common);
         }
         catch (Exception e)
         {
            JOptionPane.showMessageDialog(null,
                  message + "Buchstabenbilder fehlen", "Nachricht",
                  JOptionPane.CLOSED_OPTION);
            e.printStackTrace();
            System.exit(1);
         }

         initController(common, view);
         applyButton.setEnabled(true);
      });
   }

}
