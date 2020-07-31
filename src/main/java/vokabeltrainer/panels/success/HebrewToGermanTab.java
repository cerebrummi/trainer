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
      donePanel = new BackgroundPanelTiled(new BorderLayout());
      donePanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.DONE)));
      return donePanel;
   }

   private Component initOneYear()
   {
      oneYearPanel = new BackgroundPanelTiled();
      oneYearPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.ONE_YEAR)));
      return oneYearPanel;
   }

   private Component initFourMonths()
   {
      fourMonthsPanel = new BackgroundPanelTiled();
      fourMonthsPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.FOUR_MONTHS)));
      return fourMonthsPanel;
   }

   private Component initTwoMonths()
   {
      twoMonthsPanel = new BackgroundPanelTiled();
      twoMonthsPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.TWO_MONTHS)));
      return twoMonthsPanel;
   }

   private Component initOneMonth()
   {
      oneMonthPanel = new BackgroundPanelTiled();
      oneMonthPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.ONE_MONTH)));
      return oneMonthPanel;
   }

   private Component initTwoWeeks()
   {
      twoWeeksPanel = new BackgroundPanelTiled();
      twoWeeksPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.TWO_WEEKS)));
      return twoWeeksPanel;
   }

   private Component initOneWeek()
   {
      oneWeekPanel = new BackgroundPanelTiled();
      oneWeekPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.ONE_WEEK)));
      return oneWeekPanel;
   }

   private Component initFourDays()
   {
      fourDaysPanel = new BackgroundPanelTiled();
      fourDaysPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.FOUR_DAYS)));
      return fourDaysPanel;
   }

   private Component initTwoDays()
   {
      twoDaysPanel = new BackgroundPanelTiled();
      twoDaysPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.TWO_DAYS)));
      return twoDaysPanel;
   }

   private Component initOneDay()
   {
      oneDayPanel = new BackgroundPanelTiled();
      oneDayPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.ONE_DAY)));
      return oneDayPanel;
   }

   private Component initNow()
   {
      nowPanel = new BackgroundPanelTiled();
      nowPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, Repetition.NOW)));
      return nowPanel;
   }

   private Component initNotStarted()
   {
      notStartedPanel = new BackgroundPanelTiled();
      notStartedPanel.add(
            new SuccessTable(Data.findSuccessModel(Language.HEBREW, null)));
      return notStartedPanel;
   }
}
