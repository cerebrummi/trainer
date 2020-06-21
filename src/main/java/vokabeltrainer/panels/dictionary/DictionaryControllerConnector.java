package vokabeltrainer.panels.dictionary;

public interface DictionaryControllerConnector
{
   public void save();
   public void tabbedPaneChanged(int selectedIndex);
   public void newExpression();
   public void copyAllSelectedExpressions();
   public void copyExpressionsOfTable();
   public void copyInTableSelectedExpressions();
   public void unselectTableExpressions();
   public void unselectAllExpressions();
   public void selectTableExpressions();
   public void deleteAllSelectedExpressions();
   public void deleteInTableSelectedExpressions();
   public void putSelectedExpressionsIntoWasteBin();
}
