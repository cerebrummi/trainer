package vokabeltrainer.panels.dictionary;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import vokabeltrainer.common.ApplicationSound;
import vokabeltrainer.common.Settings;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Data;
import vokabeltrainer.common.main.SaveExpressions;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.DictionaryView;
import vokabeltrainer.panels.notifications.EmptyNotification;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.SortingType;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class DictionaryController implements DictionaryControllerConnector
{
   private DictionaryViewConnector dictionaryViewConnector;
   private Chapter currentChapter;
   private Expression currentExpression;

   public DictionaryController(Common common, View view)
   {
      this.dictionaryViewConnector = new DictionaryView(common, view, this);
      Status.init(Status.OPENED_PAGE);
   }

   @Override
   public void tabbedPaneChanged(Common common, View view, int selectedIndex)
   {
      if (selectedIndex == Tabulator.KIND_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.KIND_TAB);
         Status.push(Status.TAB_EXPRESSIONKIND);
         popToDecideOnTableInteraction(common, view, Action.TAB_EXPRESSIONKIND);
      }
      else if (selectedIndex == Tabulator.CHAPTER_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.CHAPTER_TAB);
         dictionaryViewConnector.unselectExpressionKind();
         Status.push(Status.TAB_CHAPTER);
         popToDecideOnTableInteraction(common, view, Action.TAB_CHAPTER);
         dictionaryViewConnector.loadChapters(common);
      }
      else if (selectedIndex == Tabulator.DATA_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.DATA_TAB);
         dictionaryViewConnector.unselectExpressionKind();
         Status.push(Status.DATA_CHAPTER);
         popToDecideOnTableInteraction(common, view, Action.DATA_CHAPTER);
         dictionaryViewConnector.loadDatabases();
      }
      else if (selectedIndex == Tabulator.SELECTED_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.SELECTED_TAB);
         dictionaryViewConnector.unselectExpressionKind();
         Status.push(Status.TAB_SELECTED_EXPRESSIONS);
         popToDecideOnTableInteraction(common, view, Action.TAB_SELECTED_EXPRESSIONS);
      }
      else if (selectedIndex == Tabulator.SEARCH_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.SEARCH_TAB);
         dictionaryViewConnector.unselectExpressionKind();
         Status.push(Status.TAB_SEARCH);
         popToDecideOnTableInteraction(common, view, Action.TAB_SEARCH);
      }
   }

   public DictionaryViewConnector getDictionaryPanel()
   {
      return dictionaryViewConnector;
   }

   @Override
   public void copyAllSelectedExpressions(View view, SortingType sortingType)
   {
      copyStringToClipboard(view, Data.getAllSelectedExpressionsAsString(sortingType,
            dictionaryViewConnector.getSelectedLanguage()));
   }

   @Override
   public void copyExpressionsOfTable(View view)
   {
      if (dictionaryViewConnector.isTableNotNull())
      {
         copyStringToClipboard(view, dictionaryViewConnector.getTableDataToString());
      }
   }

   @Override
   public void copyInTableSelectedExpressions(View view)
   {
      if (dictionaryViewConnector.isTableNotNull())
      {
         copyStringToClipboard(view, dictionaryViewConnector.getSelectedTableDataToString());
      }
   }

   private void copyStringToClipboard(View view, String stringToCopy)
   {
      if (checkIfAnythingToCopyWithMessageIfNot(view, stringToCopy))
      {
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(new StringSelection(stringToCopy), null);
      }
   }

   private boolean checkIfAnythingToCopyWithMessageIfNot(View view, String stringToCopy)
   {
      if (stringToCopy.isBlank())
      {
         JOptionPane.showMessageDialog(view.getjFrame(),
               "Nur selbst eingegebene Vokabeln\nk�nnen kopiert werden.");
         return false;
      }
      return true;
   }

   @Override
   public void unselectTableExpressions(Common common, View view)
   {
      if (dictionaryViewConnector.isTableNotNull())
      {
         dictionaryViewConnector.clearTableDataSelection();
         Status.pushToKeep();
         popToDecideOnTableInteraction(common, view, Action.UNSELECT_TABLE);
      }
   }

   @Override
   public void unselectAllExpressions(Common common, View view)
   {
      Data.clearAllSelectedExpressions();
      if (dictionaryViewConnector.isTableNotNull())
      {
         Status.pushToKeep();
         popToDecideOnTableInteraction(common, view, Action.UNSELECT_ALL);
      }
   }

   @Override
   public void deleteAllSelectedExpressions(Common common, View view)
   {
      List<Expression> list = Data.getAllSelectedExpressions(true);

      if (list.isEmpty())
      {
         dictionaryViewConnector.notifyNothingWasSelectedForDeletion(view);
         return;
      }
      if (dictionaryViewConnector.askForDeletionConfirmation(view, list.size()) == 0)
      {
         Data.deleteExpressions(list);
      }
      if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
      {
         dictionaryViewConnector.loadChapters(common);
      }
      if (Tabulator.DATA_TAB.equals(Tabulator.getTabShowing()))
      {
         dictionaryViewConnector.loadDatabases();
      }
      Status.pushToKeep();
      popToDecideOnTableInteraction(common, view, Action.DELETE_ALL_SELECTED);
      save(common, view);
   }

   @Override
   public void deleteInTableSelectedExpressions(Common common, View view)
   {
      if (dictionaryViewConnector.isTableNotNull())
      {
         List<Expression> list = dictionaryViewConnector
               .getInTableSelectedExpressions(true);
         if (list.isEmpty())
         {
            dictionaryViewConnector.notifyNothingWasSelectedForDeletion(view);
            return;
         }
         if (dictionaryViewConnector.askForDeletionConfirmation(view, list.size()) == 0)
         {
            Data.deleteExpressions(list);
         }
         if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
         {
            dictionaryViewConnector.loadChapters(common);
         }
         if (Tabulator.DATA_TAB.equals(Tabulator.getTabShowing()))
         {
            dictionaryViewConnector.loadDatabases();
         }
         Status.pushToKeep();
         popToDecideOnTableInteraction(common, view, Action.DELETE_SELECTED_IN_TABLE);
         save(common, view);
      }
      else
      {
         dictionaryViewConnector.notifyNothingWasSelectedForDeletion(view);
      }
   }

   @Override
   public void openTrashCanDialog(Common common, View view)
   {
      TrashCanDialog dialog = new TrashCanController(common, view, this,
            dictionaryViewConnector.getSelectedLanguage()).getTrashCanDialog();
      dialog.setLocationRelativeTo(view.getjFrame());
      dialog.setVisible(true);
      if (dialog.isRestore())
      {
         Status.pushToKeep();
         popToDecideOnTableInteraction(common, view, Action.WORK_WASTEBIN);
      }
   }

   @Override
   public void selectTableExpressions(Common common, View view)
   {
      if (dictionaryViewConnector.isTableNotNull())
      {
         dictionaryViewConnector.selectTableData();
         Status.pushToKeep();
         popToDecideOnTableInteraction(common, view, Action.SELECT_TABLE);
      }
   }

   @Override
   public void shredderDeletedExpressions(Common common, View view)
   {
      if (dictionaryViewConnector.askForShredderConfirmation(view) == 0)
      {
         if (Settings.isSoundOn())
         {
            new SwingWorker<Void, Void>()
            {
               @Override
               protected Void doInBackground() throws Exception
               {
                  try (Clip clip = AudioSystem.getClip())
                  {
                     clip.open(ApplicationSound.getShredderSound());
                     FloatControl volume = (FloatControl) clip
                           .getControl(FloatControl.Type.MASTER_GAIN);
                     volume.setValue(Settings.getVolume());
                     clip.start();
                     do
                     {
                        Thread.sleep(100);
                     } while (clip.isRunning());
                  }
                  catch (LineUnavailableException | IOException e)
                  {
                     // nothing
                  }
                  catch (InterruptedException e)
                  {
                     // nothing
                  }
                  return null;
               }
            }.execute();
         }

         Data.shredderDeletedExpressions();
         save(common, view);
      }
   }

   @Override
   public void searchOtherLanguage(Common common, View view)
   {
      dictionaryViewConnector.clearTable();
      Status.push(Status.SEARCH_WHICH_NEW);
      popToDecideOnTableInteraction(common, view, Action.SEARCH_WHICH_NEW);
   }

   @Override
   public void searchMyLanguage(Common common, View view)
   {
      dictionaryViewConnector.clearTable();
      Status.push(Status.SEARCH_WHICH_OWN);
      popToDecideOnTableInteraction(common, view, Action.SEARCH_WHICH_OWN);
   }

   public void popToDecideOnTableInteraction(Common common, View view, Action action)
   {
      new SwingWorker<Void, Void>()
      {
         private ExpressionTableModel tableModel = null;

         @Override
         protected Void doInBackground() throws Exception
         {
            Status status = Status.pop();

            if (dictionaryViewConnector.getTable() != null)
            {
               int selectedRow = dictionaryViewConnector.getTable().getSelectedRow();
               if (selectedRow >= 0)
               {
                  currentExpression = (Expression) dictionaryViewConnector.getTable()
                        .getValueAt(selectedRow, 0);
               }
            }

            Command commando = DictionaryStateMachine
                  .getCommand(new DictionaryInteraction(action, status));

            if (commando == null)
            {
               dictionaryViewConnector.displayNoTable();
            }
            else
            {
               switch (commando)
               {
               case ERROR: // default
                  dictionaryViewConnector.displayNoTable();
                  break;
               case NOTHING:
                  break;
               case NO_TABLE:
                  dictionaryViewConnector.displayNoTable();
                  break;
               case RESTORE_WHICH_CHAPTER:
                  dictionaryViewConnector.selectChapter(common, currentChapter);
                  break;
               case RESTORE_WHICH_SEARCH_OWN:
                  searchMyLanguage(common, view);
                  break;
               case RESTORE_WHICH_SEARCH_NEW:
                  searchOtherLanguage(common, view);
                  break;
               case TABLE_CHAPTER_WHICH:
                  dictionaryViewConnector.clearTable();
                  tableModel = Data.findTranslations(common, null, null, null,
                        currentChapter, null, dictionaryViewConnector.getSortNow(), null,
                        dictionaryViewConnector.getSelectedLanguage(), null);
                  dictionaryViewConnector.removeChapterListSelectionListener();
                  dictionaryViewConnector.selectChapter(common, currentChapter);
                  dictionaryViewConnector.addChapterListSelectionListener();
                  break;
               case TABLE_EXPRESSIONKIND_WHICH:
                  dictionaryViewConnector.clearTable();
                  ExpressionKind expressionKind = dictionaryViewConnector
                        .getSelectedExpressionKind();
                  if (expressionKind != null)
                  {
                     tableModel = Data.findTranslations(common, null, expressionKind,
                           null, null, null, dictionaryViewConnector.getSortNow(), null,
                           dictionaryViewConnector.getSelectedLanguage(), dictionaryViewConnector
                                 .getDatabaseTableModel().getSelectedRows());
                  }
                  break;
               case TABLE_SEARCH_WHICH_OWN:
                  dictionaryViewConnector.clearTable();
                  tableModel = Data.findTranslations(common, 
                        dictionaryViewConnector.getSearchPhraseGerman(), null,
                        dictionaryViewConnector.getSelectedSearchTypeGerman(), null,
                        null, dictionaryViewConnector.getSortNow(), null,
                        dictionaryViewConnector.getSelectedLanguage(), dictionaryViewConnector
                              .getDatabaseTableModel().getSelectedRows());
                  break;
               case TABLE_SEARCH_WHICH_NEW:
                  dictionaryViewConnector.clearTable();
                  tableModel = Data.findTranslations(common,
                        dictionaryViewConnector.getSearchPhraseOther(), null,
                        dictionaryViewConnector.getSelectedSearchTypeHebrew(), null,
                        null, dictionaryViewConnector.getSortNow(), null,
                        dictionaryViewConnector.getSelectedLanguage(), dictionaryViewConnector
                              .getDatabaseTableModel().getSelectedRows());
                  break;
               case TABLE_SELECTED_EXPRESSIONS:
                  dictionaryViewConnector.clearTable();
                  tableModel = Data.findTranslations(common,null, null, null, null,
                        vokabeltrainer.Command.ALL_SELECTED,
                        dictionaryViewConnector.getSortNow(), null,
                        dictionaryViewConnector.getSelectedLanguage(), null);
                  break;
               }
            }

            return null;
         }

         @Override
         protected void done()
         {
            if (tableModel == null)
            {
               // nothing
            }
            else if (tableModel.getRowCount() == 0)
            {
               EmptyNotification.display(view);
               dictionaryViewConnector.tablePanelValidateRepaint();
            }
            else
            {
               dictionaryViewConnector.doShowTable(common, view, tableModel);
            }

            if (dictionaryViewConnector.getTable() != null)
            {
               dictionaryViewConnector.getTable().scrollToExpression(currentExpression);
            }
         }
      }.execute();
   }

   @Override
   public void switchLanguage(Common common, View view, String actionCommand)
   {
      dictionaryViewConnector.switchSearchLanguagePanel(actionCommand);
      Status.pushToKeep();
      popToDecideOnTableInteraction(common, view, Action.valueOf(actionCommand));
   }

   @Override
   public void displayChapterWhich(Common common, View view, Chapter chapter)
   {
      this.currentChapter = chapter;
      Status.push(Status.CHAPTER_WHICH);
      popToDecideOnTableInteraction(common, view, Action.CHAPTER_WHICH);
   }

   @Override
   public void displayExpressionKindWhich(Common common, View view)
   {
      Status.push(Status.EXPRESSIONKIND_WHICH);
      popToDecideOnTableInteraction(common, view, Action.EXPRESSIONKIND_WHICH);
   }

   @Override
   public void sortTableNow(Common common, View view)
   {
      if (dictionaryViewConnector.isTableNotNull())
      {
         Status.pushToKeep();
         popToDecideOnTableInteraction(common, view, Action.SORT_NOW);
      }
   }

   @Override
   public void displayTableAfterOpeningPage(Common common, View view)
   {
      Status.pushToKeep();
      popToDecideOnTableInteraction(common, view, Action.OPENED_PAGE);
   }

   @Override
   public void moveExpressionsToChapter(Common common, View view, String toChapter)
   {
      if (dictionaryViewConnector.askForMovingToChapterConfirmation(view) == 0)
      {
         Data.moveSelectedExpressionsToChapter(toChapter);

         SaveExpressions saver = new SaveExpressions();
         saver.save(common, view);

         Status.pushToKeep();
         popToDecideOnTableInteraction(common, view, Action.MOVE_TO_CHAPTER);
      }
   }

   @Override
   public void moveExpressionsToDatabase(Common common, View view, String toDatabase)
   {
      if (dictionaryViewConnector.askForMovingToDatabaseConfirmation(view) == 0)
      {
         Data.moveSelectedExpressionsToDatabase(toDatabase);

         SaveExpressions saver = new SaveExpressions();
         saver.save(common, view);

         Status.pushToKeep();
         popToDecideOnTableInteraction(common, view, Action.MOVE_TO_DATABASE);
      }
   }

   @Override
   public void save(Common common, View view)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            if (new SaveExpressions().save(common, view))
            {
               if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
               {
                  dictionaryViewConnector.loadChapters(common);
               }
               else if (Tabulator.DATA_TAB.equals(Tabulator.getTabShowing()))
               {
                  dictionaryViewConnector.loadDatabases();
               }
               Status.pushToKeep();
               popToDecideOnTableInteraction(common, view, Action.SAVE);
            }
            return null;
         }
      }.execute();
   }

   @Override
   public void fireTableCellUpdated(Common common, View view, JTable table, int selectedRow, int column)
   {
      if (Tabulator.SELECTED_TAB.equals(Tabulator.getTabShowing()))
      {
         Status.pushToKeep();
         popToDecideOnTableInteraction(common, view, Action.UNSELECT_EXPRESSION);
      }
      else
      {
         ((ExpressionTableModel) table.getModel())
               .fireTableCellUpdated(table.getSelectedRow(), 0);
      }
   }

}
