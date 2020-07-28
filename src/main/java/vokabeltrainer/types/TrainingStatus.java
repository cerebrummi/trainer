package vokabeltrainer.types;

import java.time.LocalDate;

public class TrainingStatus
{
   private Repetition repetition;
   private int trys;
   private int totalTrys;
   private LocalDate nextDate;
   private boolean trainingStarted;

   public TrainingStatus(Repetition repetition)
   {
      if (!repetition.equals(Repetition.NOW))
      {
         throw new IllegalArgumentException(
               "This constructor is only for initilization of new training words");
      }
      this.repetition = Repetition.NOW;
      trys = 1;
      nextDate = LocalDate.now();
      trainingStarted = true;
   }

   public TrainingStatus(Repetition repetition, int trys, LocalDate nextDate)
   {
      this.repetition = repetition;
      this.trys = trys;
      this.nextDate = nextDate;
      trainingStarted = true;
   }

   public TrainingStatus()
   {
      trainingStarted = false;
   }

   public void nextRepetition()
   {
      LocalDate now = LocalDate.now();
      switch(repetition)
      {
      case NOW: repetition = Repetition.ONE_DAY;
         break;
      case ONE_DAY: repetition = Repetition.TWO_DAYS;
         break;
      case TWO_DAYS: repetition = Repetition.FOUR_DAYS;
         break;
      case FOUR_DAYS: repetition = Repetition.ONE_WEEK;
         break;
      case ONE_WEEK: repetition = Repetition.TWO_WEEKS;
         break;
      case TWO_WEEKS: repetition = Repetition.ONE_MONTH;
         break;
      case ONE_MONTH: repetition = Repetition.TWO_MONTHS;
         break;
      case TWO_MONTHS: repetition = Repetition.FOUR_MONTHS;
         break;
      case FOUR_MONTHS: repetition = Repetition.ONE_YEAR;
         break;
      case ONE_YEAR: repetition = Repetition.DONE;
         break;
      case DONE:
         break; 
      }
      this.nextDate = now.plus(repetition.getPeriod());
   }
   
   public void previousRepetition()
   {
      LocalDate now = LocalDate.now();
      switch(repetition)
      {
      case NOW:
         break;
      case ONE_DAY: repetition = Repetition.NOW;
         break;
      case TWO_DAYS: repetition = Repetition.ONE_DAY;
         break;
      case FOUR_DAYS: repetition = Repetition.TWO_DAYS;
         break;
      case ONE_WEEK: repetition = Repetition.FOUR_DAYS;
         break;
      case TWO_WEEKS: repetition = Repetition.ONE_WEEK;
         break;
      case ONE_MONTH: repetition = Repetition.TWO_WEEKS;
         break;
      case TWO_MONTHS: repetition = Repetition.ONE_MONTH;
         break;
      case FOUR_MONTHS: repetition = Repetition.TWO_MONTHS;
         break;
      case ONE_YEAR: repetition = Repetition.FOUR_MONTHS;
         break;
      case DONE:
         break;
      }
      this.nextDate = now.plus(repetition.getPeriod());
   }
   
   public boolean isTrainingDone()
   {
      if(Repetition.DONE == this.repetition)
      {
         return true;
      }
      return false;
   }
   
   public boolean isTrainingStarted()
   {
      return trainingStarted;
   }

   public void setTrainingStarted(boolean trainingStarted)
   {
      this.trainingStarted = trainingStarted;
   }

   public Repetition getRepetition()
   {
      return repetition;
   }

   public void setRepetition(Repetition repetition)
   {
      this.repetition = repetition;
   }

   public LocalDate getNextDate()
   {
      return nextDate;
   }

   public void setNextDate(LocalDate nextDate)
   {
      this.nextDate = nextDate;
   }

   public int getTrys()
   {
      return trys;
   }

   public void setTrys(int trys)
   {
      this.trys = trys;
   }

   public int getTotalTrys()
   {
      return totalTrys;
   }

   public void setTotalTrys(int totalTrys)
   {
      this.totalTrys = totalTrys;
   }
}
