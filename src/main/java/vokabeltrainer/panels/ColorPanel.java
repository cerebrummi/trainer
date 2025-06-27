package vokabeltrainer.panels;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class ColorPanel extends JPanel
{
   /**
    * 
    */
   private static final long serialVersionUID = 5974748523983524775L;
   private JButton applyButton;

   public ColorPanel()
   {
      setLayout(new BullsEyeLayout(this));

      add(initChooseColormode());

      initController();
   }

   private Component initChooseColormode()
   {
      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 15);
      horizontal.setLayout(horizontalLayout);

      applyButton = new JButton(new ImageIcon(ApplicationImages.getDarkmode()));

      horizontal.add(applyButton);

      return horizontal;
   }

   private void initController()
   {
      applyButton.addActionListener(event -> {
         Settings.toggleDarkmodeOn();

         Common.setUI();

         this.removeAll();
         this.invalidate();
         add(initChooseColormode());
         applyButton.setEnabled(false);
         
         this.validate();
         this.repaint();
      });
   }

}
