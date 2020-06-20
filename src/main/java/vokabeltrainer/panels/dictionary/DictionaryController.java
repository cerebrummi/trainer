package vokabeltrainer.panels.dictionary;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import javax.swing.SwingUtilities;

import vokabeltrainer.common.Data;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.panels.DictionaryView;
import vokabeltrainer.table.list.editor.ExpressionEditor;
import vokabeltrainer.types.Expression;

public class DictionaryController implements DictionaryControllerConnector
{
   private DictionaryView dictionaryView;

   public DictionaryController()
   {
      this.dictionaryView = new DictionaryView(this);
      Status.push(Status.OPENED_PAGE);
   }

   @Override
   public void save()
   {
      SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            if (new SaveExpressions().save())
            {
               if (Caller.CHAPTER_TAB.equals(Caller.getTabShowing()))
               {
                  dictionaryView.loadChapters();
               }
               Status.push(Status.peek());
               dictionaryView.decideOnTableInteraction(Action.SAVE);
            }

         }
      });

   }

   @Override
   public void tabbedPaneChanged(int selectedIndex)
   {
      if (selectedIndex == Caller.KIND_TAB.getIndex())
      {
         Caller.setTabShowing(Caller.KIND_TAB);
         Status.push(Status.TAB_EXPRESSIONKIND);
         dictionaryView.decideOnTableInteraction(Action.TAB_EXPRESSIONKIND);
      }
      else if (selectedIndex == Caller.CHAPTER_TAB.getIndex())
      {
         Caller.setTabShowing(Caller.CHAPTER_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_CHAPTER);
         dictionaryView.decideOnTableInteraction(Action.TAB_CHAPTER);
         dictionaryView.loadChapters();
      }
      else if (selectedIndex == Caller.NEW_TAB.getIndex())
      {
         Caller.setTabShowing(Caller.NEW_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_NEW_EXPRESSIONS);
         dictionaryView.decideOnTableInteraction(Action.TAB_NEW_EXPRESSIONS);
      }
      else if (selectedIndex == Caller.SELECTED_TAB.getIndex())
      {
         Caller.setTabShowing(Caller.SELECTED_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_SELECTED_EXPRESSIONS);
         dictionaryView
               .decideOnTableInteraction(Action.TAB_SELECTED_EXPRESSIONS);
      }
      else if (selectedIndex == Caller.SEARCH_TAB.getIndex())
      {
         Caller.setTabShowing(Caller.SEARCH_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_SEARCH);
         dictionaryView.decideOnTableInteraction(Action.TAB_SEARCH);
      }
   }

   public DictionaryView getDictionaryPanel()
   {
      return dictionaryView;
   }

   @Override
   public void newExpression()
   {
      ExpressionEditor editor = new ExpressionEditor();
      editor.setExpression(new Expression(true));
      editor.setLocationRelativeTo(null);
      editor.setVisible(true);
      if (editor.isSave())
      {
         Expression expression = editor.getExpression();
         Data.putExpressionInNewMap(expression.getUuid(), expression);
         dictionaryView.selectTab(Caller.NEW_TAB);
         Status.push(Status.peek());
         dictionaryView.decideOnTableInteraction(Action.NEW_EXPRESSION);
      }
   }

   @Override
   public void copyAllSelectedExpressions()
   {
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(
            new StringSelection(Data.getAllSelectedExpressionsAsString(
                  dictionaryView.getSelectedLanguage())),
            null);
   }

   @Override
   public void copyExpressionsOfTable()
   {
      if (dictionaryView.isTableNotNull())
      {
         StringSelection stringSelection = new StringSelection(
               dictionaryView.getTableDataToString());
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(stringSelection, null);
      }
   }

   @Override
   public void copyInTableSelectedExpressions()
   {
      if (dictionaryView.isTableNotNull())
      {
         StringSelection stringSelection = new StringSelection(
               dictionaryView.getSelectedTableDataToString());
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(stringSelection, null);
      }
   }

   @Override
   public void unselectTableExpressions()
   {
      if (dictionaryView.isTableNotNull())
      {
         dictionaryView.clearTableDataSelection();
         Status.push(Status.peek());
         dictionaryView.decideOnTableInteraction(Action.UNSELECT_TABLE);
      }
   }

   @Override
   public void unselectAllExpressions()
   {
      Data.clearAllSelectedExpressions();
      if (dictionaryView.isTableNotNull())
      {
         Status.push(Status.peek());
         dictionaryView.decideOnTableInteraction(Action.UNSELECT_ALL);
      }
   }

   @Override
   public void deleteAllSelectedExpressions()
   {
      List<Expression> list = Data.getAllSelectedExpressions();

      if (list.isEmpty())
      {
         dictionaryView.notifyNothingWasSelectedForDeletion(2);
         return;
      }
      if (dictionaryView.askForDeletionConfirmation(list.size()) == 0)
      {
         Data.deleteExpressions(list);
      }
      if (Caller.CHAPTER_TAB.equals(Caller.getTabShowing()))
      {
         dictionaryView.loadChapters();
      }
      Status.push(Status.peek());
      dictionaryView.decideOnTableInteraction(Action.DELETE_ALL_SELECTED);
   }

}
