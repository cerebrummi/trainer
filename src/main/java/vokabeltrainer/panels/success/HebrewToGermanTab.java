package vokabeltrainer.panels.success;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.success.table.SuccessTable;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Repetition;

public class HebrewToGermanTab extends JPanel
{
   private static final long serialVersionUID = -6897934863191718236L;

   private JPanel infoPanel;
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
   private JPanel oneYearPanel;
   private JPanel donePanel;

   private JTabbedPane register;

   public HebrewToGermanTab()
   {
      this.setLayout(new BorderLayout());
      this.setOpaque(false);
      this.setBackground(Settings.getTransparent());
   }

   public void loadBoxes()
   {
      this.removeAll();

      register = new JTabbedPane();
      register.setFont(Settings.getSecondaryToolBarButtonFont());

      register.addTab("Start", initInfo());             // 0
      register.addTab("Ungelernt", initNotStarted());   // 1
      register.addTab("Jetzt", initNow());              // 2
      register.addTab("Einen Tag", initOneDay());       // 3
      register.addTab("Zwei Tage", initTwoDays());      // 4
      register.addTab("Vier Tage", initFourDays());     // 5
      register.addTab("Eine Woche", initOneWeek());     // 6
      register.addTab("Zwei Wochen", initTwoWeeks());   // 7
      register.addTab("Einen Monat", initOneMonth());   // 8
      register.addTab("Zwei Monate", initTwoMonths());  // 9
      register.addTab("Vier Monate", initFourMonths()); // 10
      register.addTab("Ein Jahr", initOneYear());       // 11
      register.addTab("fertig", initDone());            // 12

      this.add(register, BorderLayout.CENTER);
      
      initController();
   }

   private Component initInfo()
   {
      infoPanel = new InformationTabHtoD();
      return infoPanel;
   }
   
   private Component initNotStarted()
   {
      notStartedPanel = new BackgroundPanelTiled(new BorderLayout());     
      return notStartedPanel;
   }
   
   private Component initNow()
   {
      nowPanel = new BackgroundPanelTiled(new BorderLayout());
      return nowPanel;
   }
   
   private Component initOneDay()
   {
      oneDayPanel = new BackgroundPanelTiled(new BorderLayout());
      return oneDayPanel;
   }
   
   private Component initTwoDays()
   {
      twoDaysPanel = new BackgroundPanelTiled(new BorderLayout());
      return twoDaysPanel;
   }
   
   private Component initFourDays()
   {
      fourDaysPanel = new BackgroundPanelTiled(new BorderLayout());
      return fourDaysPanel;
   }
   
   private Component initOneWeek()
   {
      oneWeekPanel = new BackgroundPanelTiled(new BorderLayout());
      return oneWeekPanel;
   }
   
   private Component initTwoWeeks()
   {
      twoWeeksPanel = new BackgroundPanelTiled(new BorderLayout());
      return twoWeeksPanel;
   }
   
   private Component initOneMonth()
   {
      oneMonthPanel = new BackgroundPanelTiled(new BorderLayout());
      return oneMonthPanel;
   }
   
   private Component initTwoMonths()
   {
      twoMonthsPanel = new BackgroundPanelTiled(new BorderLayout());
      return twoMonthsPanel;
   }

   private Component initFourMonths()
   {
      fourMonthsPanel = new BackgroundPanelTiled(new BorderLayout());
      return fourMonthsPanel;
   }
   
   private Component initOneYear()
   {
      oneYearPanel = new BackgroundPanelTiled(new BorderLayout());
      return oneYearPanel;
   }
   
   private Component initDone()
   {
      donePanel = new BackgroundPanelTiled(new BorderLayout());
      return donePanel;
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
            SuccessTable table = new SuccessTable(Data.findSuccessModel(Language.HEBREW,
                  null));
            notStartedPanel.add(new JScrollPane(table), BorderLayout.CENTER);
            break;
         case 2:
            SuccessHelper.addContent(Repetition.NOW, nowPanel, Language.HEBREW);
            break;
         case 3:
            SuccessHelper.addContent(Repetition.ONE_DAY, oneDayPanel, Language.HEBREW);
            break;
         case 4:
            SuccessHelper.addContent(Repetition.TWO_DAYS, twoDaysPanel, Language.HEBREW);
            break;
         case 5:
            SuccessHelper.addContent(Repetition.FOUR_DAYS, fourDaysPanel, Language.HEBREW);
            break;
         case 6:
            SuccessHelper.addContent(Repetition.ONE_WEEK, oneWeekPanel, Language.HEBREW);
            break;
         case 7:
            SuccessHelper.addContent(Repetition.TWO_WEEKS, twoWeeksPanel, Language.HEBREW);
            break;
         case 8:
            SuccessHelper.addContent(Repetition.ONE_MONTH, oneMonthPanel, Language.HEBREW);
            break;
         case 9:
            SuccessHelper.addContent(Repetition.TWO_MONTHS, twoMonthsPanel, Language.HEBREW);
            break;
         case 10:
            SuccessHelper.addContent(Repetition.FOUR_MONTHS, fourMonthsPanel, Language.HEBREW);
            break;
         case 11:
            SuccessHelper.addContent(Repetition.ONE_YEAR, oneYearPanel, Language.HEBREW);
            break;
         case 12:
            SuccessHelper.addContent(Repetition.DONE, donePanel, Language.HEBREW);
            break;
         }
      });
   }

}
