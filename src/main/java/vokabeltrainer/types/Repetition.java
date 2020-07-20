package vokabeltrainer.types;

import java.time.Period;

public enum Repetition
{
   NOW(
         Period.ZERO, "heute"),
   ONE_DAY(
         Period.ofDays(1), "nach einem Tag"),
   TWO_DAYS(
         Period.ofDays(2), "nach zwei Tagen"),
   FOUR_DAYS(
         Period.ofDays(4), "nach vier Tagen"),
   ONE_WEEK(
         Period.ofWeeks(1), "nach einer Woche"),
   TWO_WEEKS(
         Period.ofWeeks(2), "nach zwei Wochen"),
   ONE_MONTH(
         Period.ofMonths(1), "nach einem Monat"),
   TWO_MONTHS(
         Period.ofMonths(2), "nach zwei Monaten"),
   FOUR_MONTHS(
         Period.ofMonths(4), "nach vier Monaten"),
   ONE_YEAR(
         Period.ofYears(1), "nach einem Jahr"),
   DONE(
         Period.ofYears(100), "fertig");
   
   private Period period;
   private String translation;

   Repetition(Period period, String translation)
   {
      this.period = period;
      this.translation = translation;
   }

   public Period getPeriod()
   {
      return period;
   }

   public String getTranslation()
   {
      return translation;
   }
}
