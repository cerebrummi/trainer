package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.copepod.tonion.TrainLayout;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.common.Main;

public class StartPanel extends JPanel
{
   private static final long serialVersionUID = -4928761869820144146L;
   
   public StartPanel()
   {
      setLayout(new BorderLayout());

      JPanel center = new JPanel(new FlowLayout());
      center.setOpaque(false);
      JPanel titlePanel = new JPanel();
      titlePanel.setLayout(new BoxLayout(titlePanel, 1));
      titlePanel.setOpaque(false);

      JPanel nameWrapper = new JPanel(new FlowLayout());
      nameWrapper.setOpaque(false);
      nameWrapper.setPreferredSize(new Dimension(580, 130));
      JLabel name = new JLabel("<html>Cerebrummi</html>");
      name.setPreferredSize(new Dimension(580, 120));
      name.setForeground(Color.WHITE);
      name.setFont(Main.getHebrewFont(100F));
      nameWrapper.add(name);

      JPanel trainerWrapper = new JPanel(new FlowLayout());
      trainerWrapper.setOpaque(false);
      trainerWrapper.setPreferredSize(new Dimension(400, 80));
      JLabel trainer = new JLabel("<html>Vokabeltrainer</html>");
      trainer.setPreferredSize(new Dimension(355, 70));
      trainer.setForeground(Color.WHITE);
      trainer.setFont(Main.getHebrewFont(55F));
      trainerWrapper.add(trainer);

      titlePanel.add(nameWrapper);
      titlePanel.add(trainerWrapper);

      center.add(titlePanel);
      add(center, BorderLayout.NORTH);

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setLayout(new TrainLayout(horizontal));

      JPanel filler = new JPanel();
      filler.setOpaque(false);
      filler.setMinimumSize(new Dimension(600, 250));
      filler.setMaximumSize(new Dimension(800, 250));

      JLabel schalom = new JLabel(
            new ImageIcon(ApplicationImages.getLogo150()));

      schalom.setMinimumSize(new Dimension(400, 250));
      schalom.setMaximumSize(new Dimension(600, 250));

      horizontal.add(filler);
      horizontal.add(schalom);

      add(horizontal, BorderLayout.SOUTH);
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (ApplicationImages.getImage() != null)
      {
         int x = this.getParent().getWidth() / 2 - 1280 / 2;
         int y = this.getParent().getHeight() / 2 - 859 / 2;
         g.drawImage(ApplicationImages.getImage(), x, y, this);
      }
   }
}
