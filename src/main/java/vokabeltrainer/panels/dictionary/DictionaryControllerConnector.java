package vokabeltrainer.panels.dictionary;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.SortingType;

public interface DictionaryControllerConnector extends TableConnector
{
   public void tabbedPaneChanged(Common common, View view, int selectedIndex);

   public void copyExpressionsOfTable(View view);

   public void copyInTableSelectedExpressions(View view);

   public void unselectTableExpressions(Common common, View view);

   public void unselectAllExpressions(Common common, View view);

   public void selectTableExpressions(Common common, View view);

   public void deleteAllSelectedExpressions(Common common, View view);

   public void deleteInTableSelectedExpressions(Common common, View view);

   public void openTrashCanDialog(Common common, View view);

   public void shredderDeletedExpressions(Common common, View view);

   public void searchOtherLanguage(Common common, View view);

   public void searchMyLanguage(Common common, View view);

   public void popToDecideOnTableInteraction(Common common, View view, Action action);

   public void switchLanguage(Common common, View view, String actionCommand);

   public void displayChapterWhich(Common common, View view, Chapter chapter);

   public void displayExpressionKindWhich(Common common, View view);

   public DictionaryViewConnector getDictionaryPanel();

   public void sortTableNow(Common common, View view);

   public void displayTableAfterOpeningPage(Common common, View view);

   public void moveExpressionsToChapter(Common common, View view, String toChapter);

   public void moveExpressionsToDatabase(Common common, View view, String databaseAim);

   public void copyAllSelectedExpressions(View view, SortingType sortingType);
}
