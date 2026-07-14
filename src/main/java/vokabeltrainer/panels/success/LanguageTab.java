package vokabeltrainer.panels.success;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.colors.SuccessColors;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Repetition;

public class LanguageTab extends JPanel
{
   private static final long serialVersionUID = 7350720885659255518L;

   private JPanel notStartedPanel;
   private JPanel nowPanel;
   private JPanel oneDayPanel;
   private JPanel twoDaysPanel;
   private JPanel fiveDaysPanel;
   private JPanel elevenDaysPanel;
   private JPanel nineteenDaysPanel;
   private JPanel oneMonthPanel;
   private JPanel twoMonthsPanel;
   private JPanel fiveMonthsPanel;
   private JPanel donePanel;

   private JTabbedPane register;

   private JPanel infoPanel;
   private Direction languageDirection;
   private Translator translator;

   public LanguageTab(Common common, JPanel infoPanel, Direction languageDirection)
   {
      translator = common.getTranslator();
      this.infoPanel = infoPanel;
      this.languageDirection = languageDirection;
      this.setLayout(new BorderLayout());
      this.setOpaque(true);
      this.setBackground(SuccessColors.getPanelBackground());
   }

   public void loadBoxes(Common common, View view)
   {
      this.removeAll();

      register = new JTabbedPane();
      register.setOpaque(true);
      register.setBackground(SuccessColors.getPanelBackground());
      register.setFont(ApplicationFonts.secondaryToolbarButtonFont);

      register.addTab(translator.realisticTranslate(Translation.RICHTUNG),
            infoPanel); // 0
      register.addTab(translator.realisticTranslate(Translation.VORRAT),
            initNotStarted()); // 1
      register.addTab(translator.realisticTranslate(Translation.HEUTE),
            initNow()); // 2
      register.addTab(translator.realisticTranslate(Translation.MORGEN),
            initOneDay()); // 3
      register.addTab(translator.realisticTranslate(Translation._2_TAGE),
            initTwoDays()); // 4
      register.addTab(translator.realisticTranslate(Translation._5_TAGE),
            initFiveDays()); // 5
      register.addTab(translator.realisticTranslate(Translation._11_TAGE),
            initElevenDays()); // 6
      register.addTab(translator.realisticTranslate(Translation._19_TAGE),
            initNineteenDays()); // 7
      register.addTab(translator.realisticTranslate(Translation._1_MONAT),
            initOneMonth()); // 8
      register.addTab(translator.realisticTranslate(Translation._2_MONATE),
            initTwoMonths()); // 9
      register.addTab(translator.realisticTranslate(Translation._5_MONATE),
            initFiveMonths()); // 10
      register.addTab(translator.realisticTranslate(Translation.FERTIG),
            initDone()); // 11

      this.add(register, BorderLayout.CENTER);

      initController(common, view);
   }

   private Component initNotStarted()
   {
      notStartedPanel = new JPanel(new BorderLayout());
      notStartedPanel.setOpaque(true);
      notStartedPanel.setBackground(SuccessColors.getPanelBackground());
      return SuccessHelper.makePanel(notStartedPanel);
   }

   private Component initNow()
   {
      nowPanel = new JPanel();
      nowPanel.setOpaque(true);
      nowPanel.setBackground(SuccessColors.getPanelBackground());
      return SuccessHelper.makePanel(nowPanel);
   }

   private Component initOneDay()
   {
      oneDayPanel = new JPanel();
      return SuccessHelper.makePanel(oneDayPanel);
   }

   private Component initTwoDays()
   {
      twoDaysPanel = new JPanel();
      return SuccessHelper.makePanel(twoDaysPanel);
   }

   private Component initFiveDays()
   {
      fiveDaysPanel = new JPanel();
      return SuccessHelper.makePanel(fiveDaysPanel);
   }

   private Component initElevenDays()
   {
      elevenDaysPanel = new JPanel();
      return SuccessHelper.makePanel(elevenDaysPanel);
   }

   private Component initNineteenDays()
   {
      nineteenDaysPanel = new JPanel();
      return SuccessHelper.makePanel(nineteenDaysPanel);
   }

   private Component initOneMonth()
   {
      oneMonthPanel = new JPanel();
      return SuccessHelper.makePanel(oneMonthPanel);
   }

   private Component initTwoMonths()
   {
      twoMonthsPanel = new JPanel();
      return SuccessHelper.makePanel(twoMonthsPanel);
   }

   private Component initFiveMonths()
   {
      fiveMonthsPanel = new JPanel();
      return SuccessHelper.makePanel(fiveMonthsPanel);
   }

   private Component initDone()
   {
      donePanel = new JPanel();
      return SuccessHelper.makePanel(donePanel);
   }

   private void initController(Common common, View view)
   {
      register.addChangeListener(_ -> {
         switch (register.getSelectedIndex())
         {
         case 0:
            break;
         case 1:
            SuccessHelper.addContent(common, view, null, notStartedPanel, languageDirection);
            break;
         case 2:
            SuccessHelper.addContent(common, view, Repetition.NOW, nowPanel,
                  languageDirection);
            break;
         case 3:
            SuccessHelper.addContent(common, view, Repetition.ONE_DAY, oneDayPanel,
                  languageDirection);
            break;
         case 4:
            SuccessHelper.addContent(common, view, Repetition.TWO_DAYS, twoDaysPanel,
                  languageDirection);
            break;
         case 5:
            SuccessHelper.addContent(common, view, Repetition.FIVE_DAYS, fiveDaysPanel,
                  languageDirection);
            break;
         case 6:
            SuccessHelper.addContent(common, view, Repetition.ELEVEN_DAYS, elevenDaysPanel,
                  languageDirection);
            break;
         case 7:
            SuccessHelper.addContent(common, view, Repetition.NINETEEN_DAYS,
                  nineteenDaysPanel, languageDirection);
            break;
         case 8:
            SuccessHelper.addContent(common, view, Repetition.ONE_MONTH, oneMonthPanel,
                  languageDirection);
            break;
         case 9:
            SuccessHelper.addContent(common, view, Repetition.TWO_MONTHS, twoMonthsPanel,
                  languageDirection);
            break;
         case 10:
            SuccessHelper.addContent(common, view, Repetition.FIVE_MONTHS, fiveMonthsPanel,
                  languageDirection);
            break;
         case 11:
            SuccessHelper.addContent(common, view, Repetition.DONE, donePanel,
                  languageDirection);
            break;
         }
      });
   }

}
