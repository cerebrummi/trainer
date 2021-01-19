package vokabeltrainer.panels.success;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class InformationTab extends JPanel
{
   private static final long serialVersionUID = -700729868879651952L;

   public InformationTab()
   {
      setLayout(new TrainLayout(this));
      this.setOpaque(false);
      this.setBackground(Settings.getTransparent());
      
      JPanel leftSide = new JPanel();
      leftSide.setLayout(new TotemLayout(leftSide));
      leftSide.setOpaque(true);
      leftSide.setBackground(Settings.getLightGold());
      
      JPanel fillerBottom = new JPanel(new FlowLayout());
      fillerBottom.setMinimumSize(new Dimension(355, 100));
      fillerBottom.setMaximumSize(new Dimension(355, 700));
      fillerBottom.setOpaque(false);
      fillerBottom.setBackground(Settings.getTransparent());
      
      JPanel boxWrapper = new JPanel(new FlowLayout());
      boxWrapper.setOpaque(false);
      boxWrapper.setBackground(Settings.getTransparent());
      boxWrapper.setPreferredSize(new Dimension(355, 100));
      boxWrapper.setMinimumSize(new Dimension(355, 100));
      boxWrapper.setMaximumSize(new Dimension(355, 300));
      JLabel box = new JLabel("<html>Karteikasten</html>");
      box.setMinimumSize(new Dimension(355, 100));
      box.setMaximumSize(new Dimension(355, 100));
      box.setPreferredSize(new Dimension(355, 100));
      box.setSize(new Dimension(355, 100));
      box.setForeground(Settings.getWhite());
      box.setBackground(Settings.getTransparent());
      box.setFont(Main.getHebrewFont(30F));
      box.setHorizontalAlignment(SwingConstants.CENTER);
      boxWrapper.add(box);
      
      JPanel fillerRight = new JPanel(new FlowLayout());
      fillerRight.setMinimumSize(new Dimension(600, 500));
      fillerRight.setMaximumSize(new Dimension(900, 850));
      fillerRight.setOpaque(false);
      fillerRight.setBackground(Settings.getTransparent());
      
      leftSide.add(boxWrapper);
      leftSide.add(fillerBottom);
      
      add(leftSide);
      add(fillerRight);
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (ApplicationImages.getImage() != null)
      {
         int x = this.getParent().getWidth() / 2 - 1280 / 2;
         int y = this.getParent().getHeight() / 2 - 853 / 2;
         g.drawImage(ApplicationImages.getDreidel(), x, y, this);
      }
   }

}
