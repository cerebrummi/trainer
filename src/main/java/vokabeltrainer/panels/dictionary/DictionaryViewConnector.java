package vokabeltrainer.panels.dictionary;

import java.util.List;

import javax.swing.JScrollPane;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.list.table.DatabaseTableModel;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.SearchType;
import vokabeltrainer.types.SortingType;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public interface DictionaryViewConnector
{
   public void loadChapters(Common common);

   public void unselectExpressionKind();

   public void selectTab(Tabulator caller);

   public Direction getSelectedLanguage();

   public boolean isTableNotNull();

   public String getTableDataToString();

   public String getSelectedTableDataToString();

   public void clearTableDataSelection();

   public List<Expression> getInTableSelectedExpressions(
         boolean exceptDoNotChange);

   public void selectTableData();

   public void displayNoTable();

   public Chapter getSelectedChapter();

   public ExpressionKind getSelectedExpressionKind();

   public SearchType getSelectedSearchTypeGerman();

   public SearchType getSelectedSearchTypeHebrew();

   public void tablePanelValidateRepaint();

   public String getSearchPhraseGerman();

   public String getSearchPhraseOther();

   public void switchSearchLanguagePanel(String actionCommand);

   public void selectChapter(Common common, Chapter currentChapter);

   public ExpressionTable getTable();

   public JScrollPane getTableScroller();



   public void clearTable();

   public void removeChapterListSelectionListener();

   public void addChapterListSelectionListener();

   public void doShowTable(Common common, View view, ExpressionTableModel tableModel);

 

   public SortingType getSortNow();

   public int askForMovingToChapterConfirmation(View view);

   public int askForMovingToDatabaseConfirmation(View view);
   
   public int askForShredderConfirmation(View view);
   
   public int askForDeletionConfirmation(View view, int numberOfExpressionsToBeDeleted);
   
   public void notifyNothingWasSelectedForDeletion(View view);
   
   public void setValues(Common common, View view);

   public void loadDatabases();

   public DatabaseTableModel getDatabaseTableModel();
}
