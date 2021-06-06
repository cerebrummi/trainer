package vokabeltrainer.panels.dictionary;

import vokabeltrainer.types.Chapter;

public interface DictionaryControllerConnector
{
   public void save();
   public void tabbedPaneChanged(int selectedIndex);
   public void openNewExpressionDialog();
   public void openNewNikudExpressionDialog();
   public void copyAllSelectedExpressions();
   public void copyExpressionsOfTable();
   public void copyInTableSelectedExpressions();
   public void unselectTableExpressions();
   public void unselectAllExpressions();
   public void selectTableExpressions();
   public void deleteAllSelectedExpressions();
   public void deleteInTableSelectedExpressions();
   public void openTrashCanDialog();
   public void shredderDeletedExpressions();
   public void searchHebrew();
   public void searchGerman();
   public void decideOnTableInteraction(Action action);
   public void switchLanguage(String actionCommand);
   public void displayChapterWhich(Chapter chapter);
   public void displayExpressionKindWhich();
   public DictionaryViewConnector getDictionaryPanel();
   public void sortTableNow();
}
