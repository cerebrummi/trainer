package vokabeltrainer.types;

import java.time.Period;

public enum Repetition
{
   NOW(
         Period.ZERO),
   ONE_DAY(
         Period.ofDays(1)),
   TWO_DAYS(
         Period.ofDays(2)),
   FOUR_DAYS(
         Period.ofDays(4)),
   ONE_WEEK(
         Period.ofWeeks(1)),
   TWO_WEEKS(
         Period.ofWeeks(2)),
   ONE_MONTH(
         Period.ofMonths(1)),
   TWO_MONTHS(
         Period.ofMonths(2)),
   FOUR_MONTHS(
         Period.ofMonths(4)),
   ONE_YEAR(
         Period.ofYears(1)),
   DONE(
         Period.ofYears(100));
   private Period period;

   Repetition(Period period)
   {
      this.period = period;
   }

   public Period getPeriod()
   {
      return period;
   }
}
