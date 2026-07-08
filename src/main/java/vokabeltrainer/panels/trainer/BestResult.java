package vokabeltrainer.panels.trainer;

import vokabeltrainer.InputLanguagePanel.Selection;

public class BestResult
{
   private Selection selection;
   private Result resultHebrew;
   private Result resultPlene;
   private Result resultDefektiv;
   private Result resultSwedish;
   private Result resultGerman;

   public BestResult(Selection selection)
   {
      this.selection = selection;
   }

   public Selection getSelection()
   {
      return selection;
   }

   public void setResultHebrew(Result resultHebrew)
   {
      this.resultHebrew = resultHebrew;
   }

   public void setResultPlene(Result resultPlene)
   {
      this.resultPlene = resultPlene;
   }

   public void setResultDefektiv(Result resultDefektiv)
   {
      this.resultDefektiv = resultDefektiv;
   }

   public void setResultSwedish(Result resultSwedish)
   {
      this.resultSwedish = resultSwedish;
   }

   public void setResultGerman(Result resultGerman)
   {
      this.resultGerman = resultGerman;
   }

   public Result getBestResult()
   {
      if (Selection.GERMAN == selection)
      {
         return resultGerman;
      }

      if (Selection.SWEDISH == selection)
      {
         return resultSwedish;
      }

      if (Selection.SIMPLE == selection)
      {
         return resultHebrew;
      }

      if (resultPlene.getSimilarity() >= resultDefektiv.getSimilarity())
      {
         return resultPlene;
      }

      return resultDefektiv;
   }

}
