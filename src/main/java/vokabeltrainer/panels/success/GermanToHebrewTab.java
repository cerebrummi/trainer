package vokabeltrainer.panels.success;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.success.table.SuccessTable;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Repetition;

public class GermanToHebrewTab extends BackgroundPanelTiled
{
   private static final long serialVersionUID = 7350720885659255518L;

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

   public GermanToHebrewTab()
   {
      this.setLayout(new BorderLayout());
   }

   public void loadBoxes()
   {
      this.removeAll();

      register = new JTabbedPane();
      register.setFont(Settings.getSecondaryToolBarButtonFont());

      register.addTab("Start", initInfo());       // 0
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
      infoPanel = new JPanel();
      return infoPanel;
   }
   
   private Component initNotStarted()
   {
      notStartedPanel = new BackgroundPanelTiled();     
      return notStartedPanel;
   }
   
   private Component initNow()
   {
      nowPanel = new BackgroundPanelTiled();
      return nowPanel;
   }
   
   private Component initOneDay()
   {
      oneDayPanel = new BackgroundPanelTiled();
      return oneDayPanel;
   }
   
   private Component initTwoDays()
   {
      twoDaysPanel = new BackgroundPanelTiled();
      return twoDaysPanel;
   }
   
   private Component initFourDays()
   {
      fourDaysPanel = new BackgroundPanelTiled();
      return fourDaysPanel;
   }
   
   private Component initOneWeek()
   {
      oneWeekPanel = new BackgroundPanelTiled();
      return oneWeekPanel;
   }
   
   private Component initTwoWeeks()
   {
      twoWeeksPanel = new BackgroundPanelTiled();
      return twoWeeksPanel;
   }
   
   private Component initOneMonth()
   {
      oneMonthPanel = new BackgroundPanelTiled();
      return oneMonthPanel;
   }
   
   private Component initTwoMonths()
   {
      twoMonthsPanel = new BackgroundPanelTiled();
      return twoMonthsPanel;
   }

   private Component initFourMonths()
   {
      fourMonthsPanel = new BackgroundPanelTiled();
      return fourMonthsPanel;
   }
   
   private Component initOneYear()
   {
      oneYearPanel = new BackgroundPanelTiled();
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
         switch(register.getSelectedIndex())
         {
         case 0:
            break;
         case 1:
            notStartedPanel.removeAll();
            notStartedPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, null)));
            break;
         case 2:
            nowPanel.removeAll();
            nowPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.NOW)));
            break;
         case 3:
            oneDayPanel.removeAll();
            oneDayPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.ONE_DAY)));
            break;
         case 4:
            twoDaysPanel.removeAll();
            twoDaysPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.TWO_DAYS)));
            break;
         case 5:
            fourDaysPanel.removeAll();
            fourDaysPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.FOUR_DAYS)));
            break;
         case 6:
            oneWeekPanel.removeAll();
            oneWeekPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.ONE_WEEK)));
            break;
         case 7:
            twoWeeksPanel.removeAll();
            twoWeeksPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.TWO_WEEKS)));
            break;
         case 8:
            oneMonthPanel.removeAll();
            oneMonthPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.ONE_MONTH)));
            break;
         case 9:
            twoMonthsPanel.removeAll();
            twoMonthsPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.TWO_MONTHS)));
            break;
         case 10:
            fourMonthsPanel.removeAll();
            fourMonthsPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.FOUR_MONTHS)));
            break;
         case 11:
            oneYearPanel.removeAll();
            oneYearPanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.ONE_YEAR)));
            break;
         case 12:
            donePanel.removeAll();
            donePanel.add(
                  new SuccessTable(Data.findSuccessModel(Language.GERMAN, Repetition.DONE)));
            break;
         }
      });
   }
}
