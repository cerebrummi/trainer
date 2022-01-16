package vokabeltrainer.types;

import java.time.Period;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public enum Repetition
{
   NOW(
         Period.ZERO, Translation.HEUTE),
   ONE_DAY(
         Period.ofDays(1), Translation.MORGEN),
   TWO_DAYS(
         Period.ofDays(2), Translation._2_TAGE),
   FOUR_DAYS(
         Period.ofDays(4), Translation._4_TAGE),
   ONE_WEEK(
         Period.ofWeeks(1), Translation._1_WOCHE),
   TWO_WEEKS(
         Period.ofWeeks(2), Translation._2_WOCHEN),
   ONE_MONTH(
         Period.ofMonths(1), Translation._1_MONAT),
   TWO_MONTHS(
         Period.ofMonths(2), Translation._2_MONATE),
   FOUR_MONTHS(
         Period.ofMonths(4), Translation._4_MONATE),
   DONE(
         Period.ofYears(100), Translation.FERTIG);
   
   private Period period;
   private Translation translation;

   Repetition(Period period, Translation translation)
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
      Translator translator = Common.getTranslator();
      return translator.realisticTranslate(translation);
   }
}
