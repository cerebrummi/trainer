package vokabeltrainer.panels;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.resources.LetterIcons;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class ColorPanel extends JPanel
{
   /**
    * 
    */
   private static final long serialVersionUID = 5974748523983524775L;
   private JButton applyButton;
   private String message = Settings.getWindowTitle()
         + " bitte neu starten.\nFehler: ";
   
   public ColorPanel()
   {
      setLayout(new BullsEyeLayout(this));

      add(initChooseColormode());

      initController();
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

   private void initController()
   {
      applyButton.addActionListener(event -> {
         applyButton.setEnabled(false);
         Settings.toggleDarkmodeOn();

         Common.setUI();

         this.removeAll();
         this.invalidate();
         add(initChooseColormode());
         this.validate();
         this.repaint();
         
         Common.getjFrame().getContentPane().setBackground(ApplicationColors.getBackgroundGold());
         Common.getjFrame().getContentPane().validate();
         Common.getjFrame().getContentPane().repaint();
         
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

//         try
//         {
//            LetterIconsHandwritten.readNikud();
//         }
//         catch (Exception e1)
//         {
//            JOptionPane.showMessageDialog(null,
//                  message + "Buchstaben Nikud Handwritten Icons fehlen",
//                  "Nachricht", JOptionPane.CLOSED_OPTION);
//            System.exit(1);
//         }
         
         initController();
         applyButton.setEnabled(true);
      });
   }

}
