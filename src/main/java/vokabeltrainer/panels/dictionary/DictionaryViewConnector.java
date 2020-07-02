package vokabeltrainer.panels.dictionary;

import java.util.List;

import javax.swing.JScrollPane;

import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.ExpressionKind;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.SearchType;

public interface DictionaryViewConnector
{
   public void loadChapters();
   public void unselectExpressionKind();
   public void selectTab(Tabulator caller);
   public Language getSelectedLanguage();
   public boolean isTableNotNull();
   public String getTableDataToString();
   public String getSelectedTableDataToString();
   public void clearTableDataSelection();
   public void notifyNothingWasSelectedForDeletion(int singularPlural);
   public int askForDeletionConfirmation(int numberOfExpressionsToBeDeleted);
   public List<Expression> getInTableSelectedExpressions();
   public void selectTableData();
   public void displayNoTable();
   public String getSelectedChapter();
   public ExpressionKind getSelectedExpressionKind();
   public SearchType getSelectedSearchTypeGerman();
   public SearchType getSelectedSearchTypeHebrew();
   public void tableValidateRepaint();
   public String getSearchPhraseGerman();
   public String getSearchPhraseHebrew();
   public void switchSearchLanguagePanel(String actionCommand);
   public void selectChapter(String currentChapter);
   public ExpressionTable getTable();
   public JScrollPane getTableScroller();
   public int askForShredderConfirmation();
   public void clearTable();
   public void removeChapterListSelectionListener();
   public void addChapterListSelectionListener();
   public void doShowTable(ExpressionTableModel tableModel);
}
