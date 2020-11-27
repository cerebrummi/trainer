package vokabeltrainer.panels.dictionary;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import vokabeltrainer.ApplicationSound;
import vokabeltrainer.Command;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.panels.DictionaryView;
import vokabeltrainer.panels.notifications.EmptyNotification;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.table.list.editor.ExpressionEditorController;
import vokabeltrainer.table.list.editor.ExpressionEditorView;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class DictionaryController implements DictionaryControllerConnector
{
   private DictionaryViewConnector dictionaryView;
   private Chapter currentChapter;

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
               if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
               {
                  dictionaryView.loadChapters();
               }
               Status.push(Status.peek());
               decideOnTableInteraction(Action.SAVE);
            }
         }
      });
   }

   @Override
   public void tabbedPaneChanged(int selectedIndex)
   {
      if (selectedIndex == Tabulator.KIND_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.KIND_TAB);
         Status.push(Status.TAB_EXPRESSIONKIND);
         decideOnTableInteraction(Action.TAB_EXPRESSIONKIND);
      }
      else if (selectedIndex == Tabulator.CHAPTER_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.CHAPTER_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_CHAPTER);
         decideOnTableInteraction(Action.TAB_CHAPTER);
         dictionaryView.loadChapters();
      }
      else if (selectedIndex == Tabulator.SELECTED_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.SELECTED_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_SELECTED_EXPRESSIONS);
         decideOnTableInteraction(Action.TAB_SELECTED_EXPRESSIONS);
      }
      else if (selectedIndex == Tabulator.SEARCH_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.SEARCH_TAB);
         dictionaryView.unselectExpressionKind();
         Status.push(Status.TAB_SEARCH);
         decideOnTableInteraction(Action.TAB_SEARCH);
      }
   }

   public DictionaryViewConnector getDictionaryPanel()
   {
      return dictionaryView;
   }

   @Override
   public void openNewExpressionDialog()
   {
      int selectedRow = -1;
      if (dictionaryView.isTableNotNull())
      {
         selectedRow = dictionaryView.getTable().getSelectedRow();
      }
      ExpressionEditorView editor = new ExpressionEditorController()
            .getExpressionEditorDialog();
      editor.setExpression(new Expression(true, false), true);
      editor.setLocationRelativeTo(null);
      editor.setVisible(true);
      if (editor.isSave())
      {
         Expression expression = editor.getExpression();
         Data.putExpressionInNewMap(expression.getUuid(), expression);
         Status.push(Status.peek());
         decideOnTableInteraction(Action.NEW_EXPRESSION);
         save();
         if (dictionaryView.isTableNotNull())
         {
            final int selectedRowNow = selectedRow;
            SwingUtilities.invokeLater(new Runnable()
            {
               public void run()
               {
                  dictionaryView.getTableScroller().getVerticalScrollBar()
                        .setMaximum(Settings.dictionaryTableRowHeight()
                              * dictionaryView.getTable().getRowCount());
                  dictionaryView.getTableScroller().getVerticalScrollBar()
                        .setValue(Settings.dictionaryTableRowHeight()
                              * selectedRowNow);
                  dictionaryView.getTable().changeSelection(selectedRowNow, 0,
                        false, false);
               }
            });
         }
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
         decideOnTableInteraction(Action.UNSELECT_TABLE);
      }
   }

   @Override
   public void unselectAllExpressions()
   {
      Data.clearAllSelectedExpressions();
      if (dictionaryView.isTableNotNull())
      {
         Status.push(Status.peek());
         decideOnTableInteraction(Action.UNSELECT_ALL);
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
      if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
      {
         dictionaryView.loadChapters();
      }
      Status.push(Status.peek());
      decideOnTableInteraction(Action.DELETE_ALL_SELECTED);
      save();
   }

   @Override
   public void deleteInTableSelectedExpressions()
   {
      if (dictionaryView.isTableNotNull())
      {
         List<Expression> list = dictionaryView.getInTableSelectedExpressions();
         if (list.isEmpty())
         {
            dictionaryView.notifyNothingWasSelectedForDeletion(2);
            return;
         }
         if (dictionaryView.askForDeletionConfirmation(list.size()) == 0)
         {
            Data.deleteExpressions(list);
         }
         if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
         {
            dictionaryView.loadChapters();
         }
         Status.push(Status.peek());
         decideOnTableInteraction(Action.DELETE_SELECTED_IN_TABLE);
         save();
      }
      else
      {
         dictionaryView.notifyNothingWasSelectedForDeletion(2);
      }
   }

   @Override
   public void openTrashCanDialog()
   {
      TrashCanDialog dialog = new TrashCanController(this,
            dictionaryView.getSelectedLanguage()).getTrashCanDialog();
      dialog.setLocationRelativeTo(null);
      dialog.setVisible(true);
      if (dialog.isRestore())
      {
         Status.push(Status.peek());
         decideOnTableInteraction(Action.NEW_EXPRESSION);
         save();
      }
   }

   @Override
   public void selectTableExpressions()
   {
      if (dictionaryView.isTableNotNull())
      {
         dictionaryView.selectTableData();
         Status.push(Status.peek());
         decideOnTableInteraction(Action.SELECT_TABLE);
      }
   }

   @Override
   public void shredderDeletedExpressions()
   {
      if (dictionaryView.askForShredderConfirmation() == 0)
      {
         try
         {
            Clip clip = AudioSystem.getClip();
            clip.open(ApplicationSound.getShredderSound());
            clip.start();
         }
         catch (LineUnavailableException | IOException e)
         {
            // nothing
         }

         Data.shredderDeletedExpressions();
         save();
      }
   }

   @Override
   public void searchHebrew()
   {
      dictionaryView.clearTable();
      Status.push(Status.SEARCH_WHICH_HEBREW);
      decideOnTableInteraction(Action.SEARCH_WHICH_HEBREW);
   }

   @Override
   public void searchGerman()
   {
      dictionaryView.clearTable();
      Status.push(Status.SEARCH_WHICH_GERMAN);
      decideOnTableInteraction(Action.SEARCH_WHICH_GERMAN);
   }

   public void decideOnTableInteraction(Action action)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            ExpressionTableModel tableModel = null;

            switch (Interaction
                  .getCommand(new Interaction(action, Status.pop())))
            {
            case NOTHING:
               break;
            case NO_TABLE:
               dictionaryView.displayNoTable();
               break;
            case RESTORE_WHICH_CHAPTER:
               dictionaryView.selectChapter(currentChapter);
               break;
            case RESTORE_WHICH_EXPRESSIONKIND:
               // nothing
               break;
            case RESTORE_WHICH_SEARCH_GERMAN:
               searchGerman();
               break;
            case RESTORE_WHICH_SEARCH_HEBREW:
               searchHebrew();
               break;
            case TABLE_CHAPTER_WHICH:
               dictionaryView.clearTable();
               tableModel = Data.findTranslations(
                     dictionaryView.getSelectedLanguage(), null, null, null,
                     currentChapter, null);
               dictionaryView.removeChapterListSelectionListener();
               dictionaryView.selectChapter(currentChapter);
               dictionaryView.addChapterListSelectionListener();
               break;
            case TABLE_EXPRESSIONKIND_WHICH:
               dictionaryView.clearTable();
               ExpressionKind expressionKind = dictionaryView
                     .getSelectedExpressionKind();
               if (expressionKind != null)
               {
                  tableModel = Data.findTranslations(
                        dictionaryView.getSelectedLanguage(), null,
                        expressionKind, null, null, null);
               }
               break;
            case TABLE_SEARCH_WHICH_GERMAN:
               dictionaryView.clearTable();
               tableModel = Data.findTranslations(
                     dictionaryView.getSelectedLanguage(),
                     dictionaryView.getSearchPhraseGerman(), null,
                     dictionaryView.getSelectedSearchTypeGerman(), null, null);
               break;
            case TABLE_SEARCH_WHICH_HEBREW:
               dictionaryView.clearTable();
               tableModel = Data.findTranslations(
                     dictionaryView.getSelectedLanguage(),
                     dictionaryView.getSearchPhraseHebrew(), null,
                     dictionaryView.getSelectedSearchTypeHebrew(), null, null);
               break;
            case TABLE_SELECTED_EXPRESSIONS:
               dictionaryView.clearTable();
               tableModel = Data.findTranslations(
                     dictionaryView.getSelectedLanguage(), null, null, null,
                     null, Command.ALL_SELECTED);
            }

            if (tableModel == null)
            {
               // nothing
            }
            else if (tableModel.getRowCount() == 0)
            {
               EmptyNotification.display();
               dictionaryView.tableValidateRepaint();
            }
            else
            {
               dictionaryView.doShowTable(tableModel);
            }
            return null;
         }
      }.execute();
   }

   @Override
   public void switchLanguage(String actionCommand)
   {
      dictionaryView.switchSearchLanguagePanel(actionCommand);
      Status.push(Status.peek());
      decideOnTableInteraction(Action.valueOf(actionCommand));
   }

   @Override
   public void displayChapterWhich(Chapter chapter)
   {
      this.currentChapter = chapter;
      Status.push(Status.CHAPTER_WHICH);
      decideOnTableInteraction(Action.CHAPTER_WHICH);
   }

   @Override
   public void displayExpressionKindWhich()
   {
      Status.push(Status.EXPRESSIONKIND_WHICH);
      decideOnTableInteraction(Action.EXPRESSIONKIND_WHICH);
   }

}
