package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.TextImageWithPicture;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.dictionary.DictionaryControllerConnector;
import vokabeltrainer.table.list.editor.ExpressionEditorController;
import vokabeltrainer.table.list.editor.ExpressionEditorView;
import vokabeltrainer.types.Expression;

public class EnterAction extends AbstractAction
{
   private ExpressionTable table;
   private ExpressionEditorView editor;
   private DictionaryControllerConnector connector;

   public EnterAction(ExpressionTable table,
         DictionaryControllerConnector connector)
   {
      this.table = table;
      this.connector = connector;
      editor = new ExpressionEditorController().getExpressionEditorDialog();
   }

   private static final long serialVersionUID = 719272853628204094L;

   @Override
   public void actionPerformed(ActionEvent e)
   {
      int selectedRow = table.getSelectedRow();
      int scrollHeight = connector.getDictionaryPanel().getTableScroller()
            .getVerticalScrollBar().getValue();
      if (selectedRow >= 0)
      {
         Expression expression = (Expression) table.getValueAt(selectedRow, 0);
         if(expression.isDoNotChange())
         {
            JOptionPane.showMessageDialog(Common.getjFrame(), "",
                  Settings.getWindowTitle(), JOptionPane.PLAIN_MESSAGE,
                  new ImageIcon(
                        TextImage.make(
                              "Diese Vokabel aus der Sammlung",
                              expression.getOrigin().getName(),
                              "kann nicht bearbeitet werden.")));
            return;
         }
         editor.setExpression(expression);
         editor.setLocationRelativeTo(null);
         editor.setVisible(true);
         if (editor.isSave())
         {
            if (editor.isKindChanged())
            {
               Data.changeKindofExpression(editor.getOldKind(),
                     editor.getExpression());
            }
            connector.save();
         }
         SwingUtilities.invokeLater(new Runnable()
         {
            public void run()
            {
               if (connector.getDictionaryPanel().isTableNotNull())
               {
                  connector.getDictionaryPanel().getTableScroller()
                        .getVerticalScrollBar()
                        .setMaximum(Settings.dictionaryTableRowHeight()
                              * connector.getDictionaryPanel().getTable()
                                    .getRowCount());
                  connector.getDictionaryPanel().getTableScroller()
                        .getVerticalScrollBar().setValue(scrollHeight);
                  connector.getDictionaryPanel().getTable()
                        .changeSelection(selectedRow, 0, false, false);
                  connector.getDictionaryPanel().getTable().grabFocus();
               }
            }
         });
      }
   }
}
