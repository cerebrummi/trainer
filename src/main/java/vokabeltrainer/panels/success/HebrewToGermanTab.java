package vokabeltrainer.panels.success;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;

public class HebrewToGermanTab extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -6897934863191718236L;

   private JPanel notStartedPanel;
   private JPanel nowPanel;
   private JPanel oneDayPanel;
   private JPanel twoDaysPanel;
   private JPanel fourDaysPanel;
   private JPanel oneWeekPanel;
   private JPanel twoWeeksPanel;
   private JPanel fourWeeksPanel;
   private JPanel oneMonthPanel;
   private JPanel twoMonthsPanel;
   private JPanel fourMonthsPanel;
   private JPanel oneYearPanel;
   private JPanel donePanel;

   public HebrewToGermanTab()
   {
      this.setLayout(new BorderLayout());
   }

   public void loadBoxes()
   {
      this.removeAll();

      JTabbedPane register = new JTabbedPane();
      register.setFont(Settings.getSecondaryToolBarButtonFont());

      register.addTab("Ungelernt", initNotStarted());
      register.addTab("Jetzt", initNow());
      register.addTab("Einen Tag", initOneDay());
      register.addTab("Zwei Tage", initTwoDays());
      register.addTab("Vier Tage", initFourDays());
      register.addTab("Eine Woche", initOneWeek());
      register.addTab("Zwei Wochen", initTwoWeeks());
      register.addTab("Vier Wochen", initFourWeeks());
      register.addTab("Einen Monat", initOneMonth());
      register.addTab("Zwei Monate", initTwoMonths());
      register.addTab("Vier Monate", initFourMonths());
      register.addTab("Ein Jahr", initOneYear());
      register.addTab("fertig", initDone());

      this.add(register, BorderLayout.CENTER);
   }

   private Component initDone()
   {
      donePanel = new BackgroundPanelTiled();

      return donePanel;
   }

   private Component initOneYear()
   {
      oneYearPanel = new BackgroundPanelTiled();

      return oneYearPanel;
   }

   private Component initFourMonths()
   {
      fourMonthsPanel = new BackgroundPanelTiled();

      return fourMonthsPanel;
   }

   private Component initTwoMonths()
   {
      twoMonthsPanel = new BackgroundPanelTiled();

      return twoMonthsPanel;
   }

   private Component initOneMonth()
   {
      oneMonthPanel = new BackgroundPanelTiled();

      return oneMonthPanel;
   }
   
   private Component initFourWeeks()
   {
      fourWeeksPanel = new BackgroundPanelTiled();

      return fourWeeksPanel;
   }

   private Component initTwoWeeks()
   {
      twoWeeksPanel = new BackgroundPanelTiled();

      return twoWeeksPanel;
   }

   private Component initOneWeek()
   {
      oneWeekPanel = new BackgroundPanelTiled();

      return oneWeekPanel;
   }

   private Component initFourDays()
   {
      fourDaysPanel = new BackgroundPanelTiled();

      return fourDaysPanel;
   }

   private Component initTwoDays()
   {
      twoDaysPanel = new BackgroundPanelTiled();

      return twoDaysPanel;
   }

   private Component initOneDay()
   {
      oneDayPanel = new BackgroundPanelTiled();

      return oneDayPanel;
   }

   private Component initNow()
   {
      nowPanel = new BackgroundPanelTiled();

      return nowPanel;
   }
   
   private Component initNotStarted()
   {
      notStartedPanel = new BackgroundPanelTiled();

      return notStartedPanel;
   }
}
