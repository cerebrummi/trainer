package vokabeltrainer.common.main;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import vokabeltrainer.cmd.DirectoryHelper;
import vokabeltrainer.panels.translation.Translator;


public final class Common
{    
   private DirectoryHelper directoryHelper = new DirectoryHelper();
   private Translator translator = new Translator();
   
   //package on purpose
   Common()
   {

   }

   public DirectoryHelper getDirectoryHelper()
   {
      return directoryHelper;
   }
   
   public Translator getTranslator()
   {
      return this.translator;
   }
   
   public boolean isSchabbatPossible(LocalDate date)
   {     
      DayOfWeek day = date.getDayOfWeek();
      if (day.equals(DayOfWeek.FRIDAY) || day.equals(DayOfWeek.SATURDAY))
      {
         return true;
      }

      return false;
   }
   
   public boolean isSchabbat()
   {
      ZonedDateTime now = ZonedDateTime.now();
      DayOfWeek day = now.getDayOfWeek();
      int hour = now.getHour();
      if (day.equals(DayOfWeek.FRIDAY) && hour > 18)
      {
         return true;
      }
      else if (day.equals(DayOfWeek.SATURDAY) && hour < 18)
      {
         return true;
      }

      return false;
   }

   public void setTranslator(Translator translator)
   {
      this.translator = translator; 
   }
}
