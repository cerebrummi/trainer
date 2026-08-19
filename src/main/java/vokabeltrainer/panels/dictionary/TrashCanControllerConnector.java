package vokabeltrainer.panels.dictionary;

import java.util.List;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Expression;

public interface TrashCanControllerConnector extends TableConnector
{

   public DictionaryControllerConnector getDictionaryControllerConnector();

   public void restoreSelectedExpressions(App app, Common common, Model model, View view, List<Expression> selectedExpressions);

   public void selectAllExpressionsInTable(App app, Common common, Model model, View view);

   public void unselectAllExpressionsInTable(App app, Common common, Model model, View view);

   public void switchLanguage(App app, Common common, Model model, View view);

   public ExpressionTableModel loadTableModel(Model model);
}
