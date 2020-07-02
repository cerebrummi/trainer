package vokabeltrainer.words;

public class WordMatchingResult
{
   private boolean completelyFalse;
   private boolean partlyFalse;
   private boolean okay;

   public boolean isCompletelyFalse()
   {
      return completelyFalse;
   }

   public void setCompletelyFalse(boolean completelyFalse)
   {
      this.completelyFalse = completelyFalse;
   }

   public boolean isOkay()
   {
      return okay;
   }

   public void setOkay(boolean okay)
   {
      this.okay = okay;
   }

   public boolean isPartlyFalse()
   {
      return partlyFalse;
   }

   public void setPartlyFalse(boolean partlyFalse)
   {
      this.partlyFalse = partlyFalse;
   }

}
