package vokabeltrainer.panels.success;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.Serial;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Repetition;

public class LanguageTab extends JPanel
{
   @Serial
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

   public LanguageTab(App app, Common common, JPanel infoPanel, Direction languageDirection)
   {
      translator = common.getTranslator();
      this.infoPanel = infoPanel;
      this.languageDirection = languageDirection;
      this.setLayout(new BorderLayout());
      this.setOpaque(true);
      this.setBackground(app.appColors.success.getPanelBackground());
   }

   public void loadBoxes(App app, Common common, Model model, View view)
   {
      this.removeAll();

      register = new JTabbedPane();
      register.setOpaque(true);
      register.setBackground(app.appColors.success.getPanelBackground());
      register.setFont(app.appFonts.secondaryToolbarButtonFont);

      register.addTab(translator.realisticTranslate(app, Translation.RICHTUNG),
            infoPanel); // 0
      register.addTab(translator.realisticTranslate(app, Translation.VORRAT),
            initNotStarted(app)); // 1
      register.addTab(translator.realisticTranslate(app, Translation.HEUTE),
            initNow(app)); // 2
      register.addTab(translator.realisticTranslate(app, Translation.MORGEN),
            initOneDay(app)); // 3
      register.addTab(translator.realisticTranslate(app, Translation._2_TAGE),
            initTwoDays(app)); // 4
      register.addTab(translator.realisticTranslate(app, Translation._5_TAGE),
            initFiveDays(app)); // 5
      register.addTab(translator.realisticTranslate(app, Translation._11_TAGE),
            initElevenDays(app)); // 6
      register.addTab(translator.realisticTranslate(app, Translation._19_TAGE),
            initNineteenDays(app)); // 7
      register.addTab(translator.realisticTranslate(app, Translation._1_MONAT),
            initOneMonth(app)); // 8
      register.addTab(translator.realisticTranslate(app, Translation._2_MONATE),
            initTwoMonths(app)); // 9
      register.addTab(translator.realisticTranslate(app, Translation._5_MONATE),
            initFiveMonths(app)); // 10
      register.addTab(translator.realisticTranslate(app, Translation.FERTIG),
            initDone(app)); // 11

      this.add(register, BorderLayout.CENTER);

      initController(app, common, model, view);
   }

   private Component initNotStarted(App app)
   {
      notStartedPanel = new JPanel(new BorderLayout());
      notStartedPanel.setOpaque(true);
      notStartedPanel.setBackground(app.appColors.success.getPanelBackground());
      return SuccessHelper.makePanel(app, notStartedPanel);
   }

   private Component initNow(App app)
   {
      nowPanel = new JPanel();
      nowPanel.setOpaque(true);
      nowPanel.setBackground(app.appColors.success.getPanelBackground());
      return SuccessHelper.makePanel(app, nowPanel);
   }

   private Component initOneDay(App app)
   {
      oneDayPanel = new JPanel();
      return SuccessHelper.makePanel(app, oneDayPanel);
   }

   private Component initTwoDays(App app)
   {
      twoDaysPanel = new JPanel();
      return SuccessHelper.makePanel(app, twoDaysPanel);
   }

   private Component initFiveDays(App app)
   {
      fiveDaysPanel = new JPanel();
      return SuccessHelper.makePanel(app, fiveDaysPanel);
   }

   private Component initElevenDays(App app)
   {
      elevenDaysPanel = new JPanel();
      return SuccessHelper.makePanel(app, elevenDaysPanel);
   }

   private Component initNineteenDays(App app)
   {
      nineteenDaysPanel = new JPanel();
      return SuccessHelper.makePanel(app, nineteenDaysPanel);
   }

   private Component initOneMonth(App app)
   {
      oneMonthPanel = new JPanel();
      return SuccessHelper.makePanel(app, oneMonthPanel);
   }

   private Component initTwoMonths(App app)
   {
      twoMonthsPanel = new JPanel();
      return SuccessHelper.makePanel(app, twoMonthsPanel);
   }

   private Component initFiveMonths(App app)
   {
      fiveMonthsPanel = new JPanel();
      return SuccessHelper.makePanel(app, fiveMonthsPanel);
   }

   private Component initDone(App app)
   {
      donePanel = new JPanel();
      return SuccessHelper.makePanel(app, donePanel);
   }

   private void initController(App app, Common common, Model model, View view)
   {
      register.addChangeListener(_ -> {
         switch (register.getSelectedIndex())
         {
         case 0:
            break;
         case 1:
            SuccessHelper.addContent(app, common, model, view, null, notStartedPanel, languageDirection);
            break;
         case 2:
            SuccessHelper.addContent(app, common, model, view, Repetition.NOW, nowPanel,
                  languageDirection);
            break;
         case 3:
            SuccessHelper.addContent(app, common, model, view, Repetition.ONE_DAY, oneDayPanel,
                  languageDirection);
            break;
         case 4:
            SuccessHelper.addContent(app, common, model, view, Repetition.TWO_DAYS, twoDaysPanel,
                  languageDirection);
            break;
         case 5:
            SuccessHelper.addContent(app, common, model, view, Repetition.FIVE_DAYS, fiveDaysPanel,
                  languageDirection);
            break;
         case 6:
            SuccessHelper.addContent(app, common, model, view, Repetition.ELEVEN_DAYS, elevenDaysPanel,
                  languageDirection);
            break;
         case 7:
            SuccessHelper.addContent(app, common, model, view, Repetition.NINETEEN_DAYS,
                  nineteenDaysPanel, languageDirection);
            break;
         case 8:
            SuccessHelper.addContent(app, common, model, view, Repetition.ONE_MONTH, oneMonthPanel,
                  languageDirection);
            break;
         case 9:
            SuccessHelper.addContent(app, common, model, view, Repetition.TWO_MONTHS, twoMonthsPanel,
                  languageDirection);
            break;
         case 10:
            SuccessHelper.addContent(app, common, model, view, Repetition.FIVE_MONTHS, fiveMonthsPanel,
                  languageDirection);
            break;
         case 11:
            SuccessHelper.addContent(app, common, model, view, Repetition.DONE, donePanel,
                  languageDirection);
            break;
         }
      });
   }

}
