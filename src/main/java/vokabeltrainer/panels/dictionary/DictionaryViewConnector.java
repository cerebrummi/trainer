package vokabeltrainer.panels.dictionary;

import java.util.List;

import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public interface DictionaryViewConnector
{
   public void loadChapters();
   public void decideOnTableInteraction(Action action);
   public void unselectExpressionKind();
   public void selectTab(Caller caller);
   public Language getSelectedLanguage();
   public boolean isTableNotNull();
   public String getTableDataToString();
   public String getSelectedTableDataToString();
   public void clearTableDataSelection();
   public void notifyNothingWasSelectedForDeletion(int singularPlural);
   public int askForDeletionConfirmation(int numberOfExpressionsToBeDeleted);
   public List<Expression> getInTableSelectedExpressions();
   public void selectTableData();
}
