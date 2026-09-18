package vokabeltrainer.panels;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class ColorPanel extends JPanel
{
   private static final long serialVersionUID = 5974748523983524775L;
   
   private JButton applyButton;

   public ColorPanel(App app, Common common, View view)
   {
      setLayout(new BullsEyeLayout(this));

      add(initChooseColormode(app));

      initController(app, common, view);
   }

   private Component initChooseColormode(App app)
   {
      this.setBackground(app.appColors.getBackgroundGold());
      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 15);
      horizontal.setLayout(horizontalLayout);
      horizontal.setOpaque(false);
      horizontal.setBackground(app.appColors.getTransparent());

      applyButton = new JButton(new ImageIcon(app.appImages.getDarkmode()));

      horizontal.add(applyButton);

      return horizontal;
   }

   private void initController(App app, Common common, View view)
   {
      applyButton.addActionListener(_ -> {
         applyButton.setEnabled(false);
         
         app.settings.toggleDarkmodeOn();
         app.readColorModes();
         view.setUI(app, common);

         this.removeAll();
         this.invalidate();
         add(initChooseColormode(app));
         this.validate();
         this.repaint();

         view.getjFrame().getContentPane()
               .setBackground(app.appColors.getBackgroundGold());
         view.getjFrame().getContentPane().validate();
         view.getjFrame().getContentPane().repaint();

         initController(app, common, view);
         applyButton.setEnabled(true);
      });
   }

}
