package vokabeltrainer.panels.success;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import vokabeltrainer.ApplicationColors;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.success.table.SuccessTable;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Repetition;

public class LanguageTab extends JPanel
{
   private static final long serialVersionUID = 7350720885659255518L;

   private JPanel notStartedPanel;
   private JPanel nowPanel;
   private JPanel oneDayPanel;
   private JPanel twoDaysPanel;
   private JPanel fourDaysPanel;
   private JPanel oneWeekPanel;
   private JPanel twoWeeksPanel;
   private JPanel oneMonthPanel;
   private JPanel twoMonthsPanel;
   private JPanel fourMonthsPanel;
   private JPanel donePanel;

   private JTabbedPane register;

   private JPanel infoPanel;
   private Language languageDirection; // German => German to Hebrew,
                                       // Hebrew => Hebrew to German

   public LanguageTab(JPanel infoPanel, Language languageDirection)
   {
      this.infoPanel = infoPanel;
      this.languageDirection = languageDirection;
      this.setLayout(new BorderLayout());
      this.setOpaque(false);
      this.setBackground(ApplicationColors.getTransparent());
   }

   public void loadBoxes()
   {
      this.removeAll();

      register = new JTabbedPane();
      register.setFont(Settings.getSecondaryToolBarButtonFont());

      register.addTab("Richtung", infoPanel); // 0
      register.addTab("Ungelernt", initNotStarted()); // 1
      register.addTab("Start", initNow()); // 2
      register.addTab("Einen Tag", initOneDay()); // 3
      register.addTab("Zwei Tage", initTwoDays()); // 4
      register.addTab("Vier Tage", initFourDays()); // 5
      register.addTab("Eine Woche", initOneWeek()); // 6
      register.addTab("Zwei Wochen", initTwoWeeks()); // 7
      register.addTab("Einen Monat", initOneMonth()); // 8
      register.addTab("Zwei Monate", initTwoMonths()); // 9
      register.addTab("Vier Monate", initFourMonths()); // 10
      register.addTab("fertig", initDone()); // 11

      this.add(register, BorderLayout.CENTER);

      initController();
   }

   private Component initNotStarted()
   {
      notStartedPanel = new BackgroundPanelTiled(new BorderLayout());
      return notStartedPanel;
   }

   private Component initNow()
   {
      nowPanel = new JPanel();
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

   private Component initFourDays()
   {
      fourDaysPanel = new JPanel();
      return SuccessHelper.makePanel(fourDaysPanel);
   }

   private Component initOneWeek()
   {
      oneWeekPanel = new JPanel();
      return SuccessHelper.makePanel(oneWeekPanel);
   }

   private Component initTwoWeeks()
   {
      twoWeeksPanel = new JPanel();
      return SuccessHelper.makePanel(twoWeeksPanel);
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

   private Component initFourMonths()
   {
      fourMonthsPanel = new JPanel();
      return SuccessHelper.makePanel(fourMonthsPanel);
   }

   private Component initDone()
   {
      donePanel = new JPanel();
      return SuccessHelper.makePanel(donePanel);
   }

   private void initController()
   {
      register.addChangeListener(event -> {
         switch (register.getSelectedIndex())
         {
         case 0:
            break;
         case 1:
            notStartedPanel.removeAll();
            JPanel wrapper = new JPanel();
            BullsEyeLayout wrapperLayout = new BullsEyeLayout(wrapper);
            wrapper.setLayout(wrapperLayout);
            wrapper.setBackground(ApplicationColors.getDarkGold());
            wrapper.setOpaque(true);         
            
            SuccessTable table = new SuccessTable(
                  Data.findSuccessModel(languageDirection, null));
            JScrollPane scroller = new JScrollPane(table);
            scroller.setMinimumSize(new Dimension(1017,508));
            scroller.setMaximumSize(new Dimension(1200,655));
            scroller.setBackground(ApplicationColors.getDarkGold());
            scroller.setOpaque(true);
            scroller.getViewport().setBackground(ApplicationColors.getLightBlue());
            scroller.getViewport().setOpaque(true);
            
            wrapper.add(scroller);
            
            notStartedPanel.add(wrapper, BorderLayout.CENTER);
            break;
         case 2:
            SuccessHelper.addContent(Repetition.NOW, nowPanel,
                  languageDirection);
            break;
         case 3:
            SuccessHelper.addContent(Repetition.ONE_DAY, oneDayPanel,
                  languageDirection);
            break;
         case 4:
            SuccessHelper.addContent(Repetition.TWO_DAYS, twoDaysPanel,
                  languageDirection);
            break;
         case 5:
            SuccessHelper.addContent(Repetition.FOUR_DAYS, fourDaysPanel,
                  languageDirection);
            break;
         case 6:
            SuccessHelper.addContent(Repetition.ONE_WEEK, oneWeekPanel,
                  languageDirection);
            break;
         case 7:
            SuccessHelper.addContent(Repetition.TWO_WEEKS, twoWeeksPanel,
                  languageDirection);
            break;
         case 8:
            SuccessHelper.addContent(Repetition.ONE_MONTH, oneMonthPanel,
                  languageDirection);
            break;
         case 9:
            SuccessHelper.addContent(Repetition.TWO_MONTHS, twoMonthsPanel,
                  languageDirection);
            break;
         case 10:
            SuccessHelper.addContent(Repetition.FOUR_MONTHS, fourMonthsPanel,
                  languageDirection);
            break;
         case 11:
            SuccessHelper.addContent(Repetition.DONE, donePanel,
                  languageDirection);
            break;
         }
      });
   }

}
