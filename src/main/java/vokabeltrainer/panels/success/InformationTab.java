package vokabeltrainer.panels.success;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serial;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class InformationTab extends JPanel
{
   @Serial
   private static final long serialVersionUID = -700729868879651952L;
   private Translator translator;
   private JCheckBox morgenCheck;
   private JCheckBox tage_2_Check;
   private JCheckBox tage_5_Check;
   private JCheckBox tage_11_Check;
   private JCheckBox tage_19_Check;
   private JCheckBox monate_1_Check;
   private JCheckBox monate_2_Check;
   private JCheckBox monate_5_Check;
   private App app;

   public InformationTab(App app, Common common)
   {
      this.app = app;
      translator = common.getTranslator();
      
      setLayout(new TrainLayout(this));
      this.setOpaque(false);
      this.setBackground(app.appColors.getTransparent());

      JPanel leftSide = new JPanel();
      leftSide.setLayout(new TotemLayout(leftSide));
      leftSide.setOpaque(true);
      leftSide.setBackground(app.appColors.getLightGold());

      JPanel fillerBottom = new JPanel(new FlowLayout());
      fillerBottom.setMinimumSize(new Dimension(355, 100));
      fillerBottom.setMaximumSize(new Dimension(355, 700));
      fillerBottom.setOpaque(false);
      fillerBottom.setBackground(app.appColors.getTransparent());

      JPanel boxWrapper = new JPanel(new FlowLayout());
      boxWrapper.setOpaque(false);
      boxWrapper.setBackground(app.appColors.getTransparent());
      boxWrapper.setPreferredSize(new Dimension(355, 100));
      boxWrapper.setMinimumSize(new Dimension(355, 100));
      boxWrapper.setMaximumSize(new Dimension(355, 300));
      JLabel box = new JLabel(
            "<html>" + translator.realisticTranslate(app, Translation.KARTEIKASTEN)
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

      JPanel checkWrapper = new JPanel();
      TotemLayout checkLayout = new TotemLayout(checkWrapper);
      checkWrapper.setLayout(checkLayout);
      checkWrapper.setMinimumSize(new Dimension(355, 1000));
      checkWrapper.setMaximumSize(new Dimension(355, 1000));
      checkWrapper.setPreferredSize(new Dimension(355, 1000));
      checkWrapper.setBackground(app.appColors.getDarkGold());
      checkWrapper.setOpaque(true);

      Font checkBoxFont = app.appFonts.germanFont.deriveFont(20F);

      JCheckBox heuteCheck = new JCheckBox(
            translator.realisticTranslate(app, Translation.HEUTE));
      heuteCheck.setFont(checkBoxFont);
      heuteCheck.setForeground(app.appColors.success.getTableBackground());
      heuteCheck.setSelected(true);
      heuteCheck.setEnabled(false);
      morgenCheck = new JCheckBox(
            translator.realisticTranslate(app, Translation.MORGEN));
      morgenCheck.setFont(checkBoxFont);
      morgenCheck.setForeground(app.appColors.success.getTableBackground());
      morgenCheck.setSelected(app.settings.isRepetition_one_day());
      tage_2_Check = new JCheckBox(
            translator.realisticTranslate(app, Translation._2_TAGE));
      tage_2_Check.setFont(checkBoxFont);
      tage_2_Check.setForeground(app.appColors.success.getTableBackground());
      tage_2_Check.setSelected(app.settings.isRepetition_two_days());
      tage_5_Check = new JCheckBox(
            translator.realisticTranslate(app, Translation._5_TAGE));
      tage_5_Check.setFont(checkBoxFont);
      tage_5_Check.setForeground(app.appColors.success.getTableBackground());
      tage_5_Check.setSelected(app.settings.isRepetition_five_days());
      tage_11_Check = new JCheckBox(
            translator.realisticTranslate(app, Translation._11_TAGE));
      tage_11_Check.setFont(checkBoxFont);
      tage_11_Check.setForeground(app.appColors.success.getTableBackground());
      tage_11_Check.setSelected(app.settings.isRepetition_eleven_days());
      tage_19_Check = new JCheckBox(
            translator.realisticTranslate(app, Translation._19_TAGE));
      tage_19_Check.setFont(checkBoxFont);
      tage_19_Check.setForeground(app.appColors.success.getTableBackground());
      tage_19_Check.setSelected(app.settings.isRepetition_nineteen_days());
      monate_1_Check = new JCheckBox(
            translator.realisticTranslate(app, Translation._1_MONAT));
      monate_1_Check.setFont(checkBoxFont);
      monate_1_Check.setForeground(app.appColors.success.getTableBackground());
      monate_1_Check.setSelected(app.settings.isRepetition_one_month());
      monate_2_Check = new JCheckBox(
            translator.realisticTranslate(app, Translation._2_MONATE));
      monate_2_Check.setFont(checkBoxFont);
      monate_2_Check.setForeground(app.appColors.success.getTableBackground());
      monate_2_Check.setSelected(app.settings.isRepetition_two_months());
      monate_5_Check = new JCheckBox(
            translator.realisticTranslate(app, Translation._5_MONATE));
      monate_5_Check.setFont(checkBoxFont);
      monate_5_Check.setForeground(app.appColors.success.getTableBackground());
      monate_5_Check.setSelected(app.settings.isRepetition_five_months());
      JCheckBox done_Check = new JCheckBox(
            translator.realisticTranslate(app, Translation.FERTIG));
      done_Check.setFont(checkBoxFont);
      done_Check.setForeground(app.appColors.success.getTableBackground());
      done_Check.setSelected(true);
      done_Check.setEnabled(false);

      checkWrapper.add(heuteCheck);
      checkWrapper.add(morgenCheck);
      checkWrapper.add(tage_2_Check);
      checkWrapper.add(tage_5_Check);
      checkWrapper.add(tage_11_Check);
      checkWrapper.add(tage_19_Check);
      checkWrapper.add(monate_1_Check);
      checkWrapper.add(monate_2_Check);
      checkWrapper.add(monate_5_Check);
      checkWrapper.add(done_Check);

      JPanel fillerRight = new JPanel(new FlowLayout());
      fillerRight.setMinimumSize(new Dimension(600, 500));
      fillerRight.setMaximumSize(new Dimension(900, 850));
      fillerRight.setOpaque(false);
      fillerRight.setBackground(app.appColors.getTransparent());

      leftSide.add(boxWrapper);
      leftSide.add(checkWrapper);
      leftSide.add(fillerBottom);

      add(leftSide);
      add(fillerRight);

      initController(app);
   }

   private void initController(App app)
   {
      morgenCheck.addActionListener(_ -> {
         app.settings.setRepetition_one_day(morgenCheck.isSelected());
      });
      tage_2_Check.addActionListener(_ -> {
         app.settings.setRepetition_two_days(tage_2_Check.isSelected());
      });
      tage_5_Check.addActionListener(_ -> {
         app.settings.setRepetition_five_days(tage_5_Check.isSelected());
      });
      tage_11_Check.addActionListener(_ -> {
         app.settings.setRepetition_eleven_days(tage_11_Check.isSelected());
      });
      tage_19_Check.addActionListener(_ -> {
         app.settings.setRepetition_nineteen_days(tage_19_Check.isSelected());
      });
      monate_1_Check.addActionListener(_ -> {
         app.settings.setRepetition_one_month(monate_1_Check.isSelected());
      });
      monate_2_Check.addActionListener(_ -> {
         app.settings.setRepetition_two_months(monate_2_Check.isSelected());
      });
      monate_5_Check.addActionListener(_ -> {
         app.settings.setRepetition_five_months(monate_5_Check.isSelected());
      });
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (app.appImages.getImage() != null)
      {
         int x = this.getParent().getWidth() / 2 - 1280 / 2;
         int y = this.getParent().getHeight() / 2 - 853 / 2;
         g.drawImage(app.appImages.getDreidel(), x, y, this);
      }
   }

}
