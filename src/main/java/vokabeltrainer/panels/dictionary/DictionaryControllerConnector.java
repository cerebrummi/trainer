package vokabeltrainer.panels.dictionary;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.SortingType;

public interface DictionaryControllerConnector extends TableConnector
{
   public void tabbedPaneChanged(App app, Common common, Model model, View view, int selectedIndex);

   public void copyExpressionsOfTable(App app, View view);

   public void copyInTableSelectedExpressions(App app, View view);

   public void unselectTableExpressions(App app, Common common, Model model, View view);

   public void unselectAllExpressions(App app, Common common, Model model, View view);

   public void selectTableExpressions(App app, Common common, Model model, View view);

   public void deleteAllSelectedExpressions(App app, Common common, Model model, View view);

   public void deleteInTableSelectedExpressions(App app, Common common, Model model, View view);

   public void openTrashCanDialog(App app, Common common, Model model, View view);

   public void shredderDeletedExpressions(App app, Common common, Model model, View view);

   public void searchOtherLanguage(App app, Common common, Model model, View view);

   public void searchMyLanguage(App app, Common common, Model model, View view);

   public void popToDecideOnTableInteraction(App app, Common common, Model model, View view, Action action);

   public void switchLanguage(App app, Common common, Model model, View view, String actionCommand);

   public void displayChapterWhich(App app, Common common, Model model, View view, Chapter chapter);

   public void displayExpressionKindWhich(App app, Common common, Model model, View view);

   public DictionaryViewConnector getDictionaryPanel();

   public void sortTableNow(App app, Common common, Model model, View view);

   public void displayTableAfterOpeningPage(App app, Common common, Model model, View view);

   public void moveExpressionsToChapter(App app, Common common, Model model, View view, String toChapter);

   public void moveExpressionsToDatabase(App app, Common common, Model model, View view, String databaseAim);

   public void copyAllSelectedExpressions(App app, Model model, View view, SortingType sortingType);
}
