package vokabeltrainer.panels.success;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class InformationTabDtoH extends JPanel
{
   private static final long serialVersionUID = -4006431590073534430L;

   public InformationTabDtoH()
   {
      this.setLayout(new BorderLayout());
      setOpaque(false);
      setBackground(Settings.getTransparent());

      JPanel eyePanel = new JPanel();
      eyePanel.setOpaque(false);
      eyePanel.setBackground(Settings.getTransparent());
      eyePanel.setLayout(new BullsEyeLayout(eyePanel));

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));
      horizontal.setOpaque(false);
      horizontal.setBackground(Settings.getTransparent());

      JPanel leftSide = new JPanel();
      leftSide.setLayout(new TotemLayout(leftSide));
      leftSide.setOpaque(true);
      leftSide.setBackground(Settings.getLightGold());

      JPanel fillerTop = new JPanel(new FlowLayout());
      fillerTop.setMinimumSize(new Dimension(200, 10));
      fillerTop.setMaximumSize(new Dimension(400, 10));
      fillerTop.setOpaque(false);
      fillerTop.setBackground(Settings.getTransparent());

      JPanel boxWrapper = new JPanel(new FlowLayout());
      boxWrapper.setOpaque(false);
      boxWrapper.setBackground(Settings.getTransparent());
      boxWrapper.setPreferredSize(new Dimension(400, 100));
      JLabel box = new JLabel("<html>Deutsch >> Hebräisch</html>");
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
      fillerRight.setMaximumSize(new Dimension(900, 700));
      fillerRight.setOpaque(false);
      fillerRight.setBackground(Settings.getTransparent());

      leftSide.add(fillerTop);
      leftSide.add(boxWrapper);

      horizontal.add(leftSide);
      horizontal.add(fillerRight);

      eyePanel.add(horizontal);

      add(eyePanel, BorderLayout.CENTER);
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (ApplicationImages.getImage() != null)
      {
         int x = this.getParent().getWidth() / 2 - 1280 / 2;
         int y = this.getParent().getHeight() / 2 - 853 / 2;
         g.drawImage(ApplicationImages.getHebrewLetters(), x, y, this);
      }
   }

}
