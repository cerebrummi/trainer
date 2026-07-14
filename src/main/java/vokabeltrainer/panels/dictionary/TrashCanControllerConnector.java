package vokabeltrainer.panels.dictionary;

import java.util.List;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Expression;

public interface TrashCanControllerConnector extends TableConnector
{

   public DictionaryControllerConnector getDictionaryControllerConnector();

   public void restoreSelectedExpressions(Common common, View view, List<Expression> selectedExpressions);

   public void selectAllExpressionsInTable(Common common, View view);

   public void unselectAllExpressionsInTable(Common common, View view);

   public void switchLanguage(Common common, View view);

   public ExpressionTableModel loadTableModel();
}
