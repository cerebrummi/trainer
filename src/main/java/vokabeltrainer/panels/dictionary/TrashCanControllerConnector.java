package vokabeltrainer.panels.dictionary;

import java.util.List;

import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Expression;

public interface TrashCanControllerConnector extends TableConnector
{

   public DictionaryControllerConnector getDictionaryControllerConnector();

   public void restoreSelectedExpressions(List<Expression> selectedExpressions);

   public void selectAllExpressionsInTable();

   public void unselectAllExpressionsInTable();

   public void switchLanguage();

   public ExpressionTableModel loadTableModel();
}
