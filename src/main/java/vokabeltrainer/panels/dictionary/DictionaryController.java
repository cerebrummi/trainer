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

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
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

   public DictionaryController(App app, Common common, Model model, View view)
   {
      this.dictionaryViewConnector = new DictionaryView(app, common, model, view, this);
      Status.init(Status.OPENED_PAGE);
   }

   @Override
   public void tabbedPaneChanged(App app, Common common, Model model, View view, int selectedIndex)
   {
      if (selectedIndex == Tabulator.KIND_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.KIND_TAB);
         Status.push(Status.TAB_EXPRESSIONKIND);
         popToDecideOnTableInteraction(app, common, model, view, Action.TAB_EXPRESSIONKIND);
      }
      else if (selectedIndex == Tabulator.CHAPTER_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.CHAPTER_TAB);
         dictionaryViewConnector.unselectExpressionKind();
         Status.push(Status.TAB_CHAPTER);
         popToDecideOnTableInteraction(app, common, model, view, Action.TAB_CHAPTER);
         dictionaryViewConnector.loadChapters(app, common, model);
      }
      else if (selectedIndex == Tabulator.DATA_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.DATA_TAB);
         dictionaryViewConnector.unselectExpressionKind();
         Status.push(Status.DATA_CHAPTER);
         popToDecideOnTableInteraction(app, common, model, view, Action.DATA_CHAPTER);
         dictionaryViewConnector.loadDatabases(app, model);
      }
      else if (selectedIndex == Tabulator.SELECTED_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.SELECTED_TAB);
         dictionaryViewConnector.unselectExpressionKind();
         Status.push(Status.TAB_SELECTED_EXPRESSIONS);
         popToDecideOnTableInteraction(app, common, model, view, Action.TAB_SELECTED_EXPRESSIONS);
      }
      else if (selectedIndex == Tabulator.SEARCH_TAB.getIndex())
      {
         Tabulator.setTabShowing(Tabulator.SEARCH_TAB);
         dictionaryViewConnector.unselectExpressionKind();
         Status.push(Status.TAB_SEARCH);
         popToDecideOnTableInteraction(app, common, model, view, Action.TAB_SEARCH);
      }
   }

   public DictionaryViewConnector getDictionaryPanel()
   {
      return dictionaryViewConnector;
   }

   @Override
   public void copyAllSelectedExpressions(Model model, View view, SortingType sortingType)
   {
      copyStringToClipboard(view, model.data.getAllSelectedExpressionsAsString(sortingType,
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
   public void unselectTableExpressions(App app, Common common, Model model, View view)
   {
      if (dictionaryViewConnector.isTableNotNull())
      {
         dictionaryViewConnector.clearTableDataSelection();
         Status.pushToKeep();
         popToDecideOnTableInteraction(app, common, model, view, Action.UNSELECT_TABLE);
      }
   }

   @Override
   public void unselectAllExpressions(App app, Common common, Model model, View view)
   {
      model.data.clearAllSelectedExpressions();
      if (dictionaryViewConnector.isTableNotNull())
      {
         Status.pushToKeep();
         popToDecideOnTableInteraction(app, common, model, view, Action.UNSELECT_ALL);
      }
   }

   @Override
   public void deleteAllSelectedExpressions(App app, Common common, Model model, View view)
   {
      List<Expression> list = model.data.getAllSelectedExpressions(true);

      if (list.isEmpty())
      {
         dictionaryViewConnector.notifyNothingWasSelectedForDeletion(view);
         return;
      }
      if (dictionaryViewConnector.askForDeletionConfirmation(view, list.size()) == 0)
      {
         model.data.deleteExpressions(list);
      }
      if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
      {
         dictionaryViewConnector.loadChapters(app, common, model);
      }
      if (Tabulator.DATA_TAB.equals(Tabulator.getTabShowing()))
      {
         dictionaryViewConnector.loadDatabases(app, model);
      }
      Status.pushToKeep();
      popToDecideOnTableInteraction(app, common, model, view, Action.DELETE_ALL_SELECTED);
      save(app, common, model, view);
   }

   @Override
   public void deleteInTableSelectedExpressions(App app, Common common, Model model, View view)
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
            model.data.deleteExpressions(list);
         }
         if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
         {
            dictionaryViewConnector.loadChapters(app, common, model);
         }
         if (Tabulator.DATA_TAB.equals(Tabulator.getTabShowing()))
         {
            dictionaryViewConnector.loadDatabases(app, model);
         }
         Status.pushToKeep();
         popToDecideOnTableInteraction(app, common, model, view, Action.DELETE_SELECTED_IN_TABLE);
         save(app, common, model, view);
      }
      else
      {
         dictionaryViewConnector.notifyNothingWasSelectedForDeletion(view);
      }
   }

   @Override
   public void openTrashCanDialog(App app, Common common, Model model, View view)
   {
      TrashCanDialog dialog = new TrashCanController(app, common, model, view, this,
            dictionaryViewConnector.getSelectedLanguage()).getTrashCanDialog();
      dialog.setLocationRelativeTo(view.getjFrame());
      dialog.setVisible(true);
      if (dialog.isRestore())
      {
         Status.pushToKeep();
         popToDecideOnTableInteraction(app, common, model, view, Action.WORK_WASTEBIN);
      }
   }

   @Override
   public void selectTableExpressions(App app, Common common, Model model, View view)
   {
      if (dictionaryViewConnector.isTableNotNull())
      {
         dictionaryViewConnector.selectTableData();
         Status.pushToKeep();
         popToDecideOnTableInteraction(app, common, model, view, Action.SELECT_TABLE);
      }
   }

   @Override
   public void shredderDeletedExpressions(App app, Common common, Model model, View view)
   {
      if (dictionaryViewConnector.askForShredderConfirmation(view) == 0)
      {
         if (app.settings.isSoundOn())
         {
            new SwingWorker<Void, Void>()
            {
               @Override
               protected Void doInBackground() throws Exception
               {
                  try (Clip clip = AudioSystem.getClip())
                  {
                     clip.open(app.appSound.getShredderSound());
                     FloatControl volume = (FloatControl) clip
                           .getControl(FloatControl.Type.MASTER_GAIN);
                     volume.setValue(app.settings.getVolume());
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

         model.data.shredderDeletedExpressions();
         save(app, common, model, view);
      }
   }

   @Override
   public void searchOtherLanguage(App app, Common common, Model model, View view)
   {
      dictionaryViewConnector.clearTable();
      Status.push(Status.SEARCH_WHICH_NEW);
      popToDecideOnTableInteraction(app, common, model, view, Action.SEARCH_WHICH_NEW);
   }

   @Override
   public void searchMyLanguage(App app, Common common, Model model, View view)
   {
      dictionaryViewConnector.clearTable();
      Status.push(Status.SEARCH_WHICH_OWN);
      popToDecideOnTableInteraction(app, common, model, view, Action.SEARCH_WHICH_OWN);
   }

   public void popToDecideOnTableInteraction(App app, Common common,  Model model, View view, Action action)
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
                  dictionaryViewConnector.selectChapter(app, common, model, currentChapter);
                  break;
               case RESTORE_WHICH_SEARCH_OWN:
                  searchMyLanguage(app, common, model, view);
                  break;
               case RESTORE_WHICH_SEARCH_NEW:
                  searchOtherLanguage(app, common, model, view);
                  break;
               case TABLE_CHAPTER_WHICH:
                  dictionaryViewConnector.clearTable();
                  tableModel = model.data.findTranslations(common, null, null, null,
                        currentChapter, null, dictionaryViewConnector.getSortNow(), null,
                        dictionaryViewConnector.getSelectedLanguage(), null);
                  dictionaryViewConnector.removeChapterListSelectionListener();
                  dictionaryViewConnector.selectChapter(app, common, model, currentChapter);
                  dictionaryViewConnector.addChapterListSelectionListener();
                  break;
               case TABLE_EXPRESSIONKIND_WHICH:
                  dictionaryViewConnector.clearTable();
                  ExpressionKind expressionKind = dictionaryViewConnector
                        .getSelectedExpressionKind();
                  if (expressionKind != null)
                  {
                     tableModel = model.data.findTranslations(common, null, expressionKind,
                           null, null, null, dictionaryViewConnector.getSortNow(), null,
                           dictionaryViewConnector.getSelectedLanguage(), dictionaryViewConnector
                                 .getDatabaseTableModel().getSelectedRows());
                  }
                  break;
               case TABLE_SEARCH_WHICH_OWN:
                  dictionaryViewConnector.clearTable();
                  tableModel = model.data.findTranslations(common, 
                        dictionaryViewConnector.getSearchPhraseGerman(), null,
                        dictionaryViewConnector.getSelectedSearchTypeGerman(), null,
                        null, dictionaryViewConnector.getSortNow(), null,
                        dictionaryViewConnector.getSelectedLanguage(), dictionaryViewConnector
                              .getDatabaseTableModel().getSelectedRows());
                  break;
               case TABLE_SEARCH_WHICH_NEW:
                  dictionaryViewConnector.clearTable();
                  tableModel = model.data.findTranslations(common,
                        dictionaryViewConnector.getSearchPhraseOther(), null,
                        dictionaryViewConnector.getSelectedSearchTypeHebrew(), null,
                        null, dictionaryViewConnector.getSortNow(), null,
                        dictionaryViewConnector.getSelectedLanguage(), dictionaryViewConnector
                              .getDatabaseTableModel().getSelectedRows());
                  break;
               case TABLE_SELECTED_EXPRESSIONS:
                  dictionaryViewConnector.clearTable();
                  tableModel = model.data.findTranslations(common,null, null, null, null,
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
               EmptyNotification.display(app, view);
               dictionaryViewConnector.tablePanelValidateRepaint();
            }
            else
            {
               dictionaryViewConnector.doShowTable(app, common, view, tableModel);
            }

            if (dictionaryViewConnector.getTable() != null)
            {
               dictionaryViewConnector.getTable().scrollToExpression(currentExpression);
            }
         }
      }.execute();
   }

   @Override
   public void switchLanguage(App app, Common common, Model model, View view, String actionCommand)
   {
      dictionaryViewConnector.switchSearchLanguagePanel(actionCommand);
      Status.pushToKeep();
      popToDecideOnTableInteraction(app, common, model, view, Action.valueOf(actionCommand));
   }

   @Override
   public void displayChapterWhich(App app, Common common, Model model, View view, Chapter chapter)
   {
      this.currentChapter = chapter;
      Status.push(Status.CHAPTER_WHICH);
      popToDecideOnTableInteraction(app, common, model, view, Action.CHAPTER_WHICH);
   }

   @Override
   public void displayExpressionKindWhich(App app, Common common, Model model, View view)
   {
      Status.push(Status.EXPRESSIONKIND_WHICH);
      popToDecideOnTableInteraction(app, common, model, view, Action.EXPRESSIONKIND_WHICH);
   }

   @Override
   public void sortTableNow(App app, Common common, Model model, View view)
   {
      if (dictionaryViewConnector.isTableNotNull())
      {
         Status.pushToKeep();
         popToDecideOnTableInteraction(app, common, model, view, Action.SORT_NOW);
      }
   }

   @Override
   public void displayTableAfterOpeningPage(App app, Common common, Model model, View view)
   {
      Status.pushToKeep();
      popToDecideOnTableInteraction(app, common, model, view, Action.OPENED_PAGE);
   }

   @Override
   public void moveExpressionsToChapter(App app, Common common, Model model, View view, String toChapter)
   {
      if (dictionaryViewConnector.askForMovingToChapterConfirmation(view) == 0)
      {
         model.data.moveSelectedExpressionsToChapter(toChapter);

         SaveExpressions saver = new SaveExpressions(app, model);
         saver.save(app, common, view);

         Status.pushToKeep();
         popToDecideOnTableInteraction(app, common, model, view, Action.MOVE_TO_CHAPTER);
      }
   }

   @Override
   public void moveExpressionsToDatabase(App app, Common common, Model model, View view, String toDatabase)
   {
      if (dictionaryViewConnector.askForMovingToDatabaseConfirmation(view) == 0)
      {
         model.data.moveSelectedExpressionsToDatabase(toDatabase);

         SaveExpressions saver = new SaveExpressions(app, model);
         saver.save(app, common, view);

         Status.pushToKeep();
         popToDecideOnTableInteraction(app, common, model, view, Action.MOVE_TO_DATABASE);
      }
   }

   @Override
   public void save(App app, Common common, Model model, View view)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            if (new SaveExpressions(app, model).save(app, common, view))
            {
               if (Tabulator.CHAPTER_TAB.equals(Tabulator.getTabShowing()))
               {
                  dictionaryViewConnector.loadChapters(app, common, model);
               }
               else if (Tabulator.DATA_TAB.equals(Tabulator.getTabShowing()))
               {
                  dictionaryViewConnector.loadDatabases(app, model);
               }
               Status.pushToKeep();
               popToDecideOnTableInteraction(app, common, model, view, Action.SAVE);
            }
            return null;
         }
      }.execute();
   }

   @Override
   public void fireTableCellUpdated(App app, Common common, Model model, View view, JTable table, int selectedRow, int column)
   {
      if (Tabulator.SELECTED_TAB.equals(Tabulator.getTabShowing()))
      {
         Status.pushToKeep();
         popToDecideOnTableInteraction(app, common, model, view, Action.UNSELECT_EXPRESSION);
      }
      else
      {
         ((ExpressionTableModel) table.getModel())
               .fireTableCellUpdated(table.getSelectedRow(), 0);
      }
   }

}
