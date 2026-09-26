package vokabeltrainer.panels.success;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.Serial;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class InformationTabDtoH extends JPanel
{
   @Serial
   private static final long serialVersionUID = -4006431590073534430L;
   private Translator translator;
   private App app;

   public InformationTabDtoH(App app, Common common)
   {
      this.app = app;
      translator = common.getTranslator();
      this.setLayout(new TrainLayout(this));
      setOpaque(false);
      setBackground(app.appColors.getTransparent());

      JPanel leftSide = new JPanel();
      leftSide.setLayout(new TotemLayout(leftSide));
      leftSide.setOpaque(true);
      leftSide.setBackground(app.appColors.getLightGold());

      JPanel fillerBottom = new JPanel(new FlowLayout());
      fillerBottom.setMinimumSize(new Dimension(200, 100));
      fillerBottom.setMaximumSize(new Dimension(400, 700));
      fillerBottom.setOpaque(false);
      fillerBottom.setBackground(app.appColors.getTransparent());

      JPanel boxWrapper = new JPanel(new FlowLayout());
      boxWrapper.setOpaque(false);
      boxWrapper.setBackground(app.appColors.getTransparent());
      boxWrapper.setPreferredSize(new Dimension(355, 100));
      JLabel box = new JLabel("<html>"
            + translator.realisticTranslate(app, Translation.DEUTSCH) + " >> "
            + translator.realisticTranslate(app, Translation.HEBRAEISCH)
            + "</html>");
      box.setMinimumSize(new Dimension(355, 100));
      box.setMaximumSize(new Dimension(355, 100));
      box.setPreferredSize(new Dimension(355, 100));
      box.setSize(new Dimension(355, 100));
      box.setForeground(app.appColors.getWhite());
      box.setBackground(app.appColors.getTransparent());
      box.setFont(app.appFonts.hebrewFont.deriveFont(30F));
      box.setHorizontalAlignment(SwingConstants.CENTER);
      boxWrapper.add(box);

      JPanel fillerRight = new JPanel(new FlowLayout());
      fillerRight.setMinimumSize(new Dimension(600, 500));
      fillerRight.setMaximumSize(new Dimension(900, 850));
      fillerRight.setOpaque(false);
      fillerRight.setBackground(app.appColors.getTransparent());

      leftSide.add(boxWrapper);
      leftSide.add(fillerBottom);

      add(leftSide);
      add(fillerRight);
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (app.appImages.getHebrewLetters() != null)
      {
         int x = this.getParent().getWidth() / 2 - 1280 / 2;
         int y = this.getParent().getHeight() / 2 - 853 / 2;
         g.drawImage(app.appImages.getHebrewLetters(), x, y, this);
      }
   }

}
