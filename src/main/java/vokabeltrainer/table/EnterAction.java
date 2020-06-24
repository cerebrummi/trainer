package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vokabeltrainer.panels.dictionary.DictionaryControllerConnector;
import vokabeltrainer.table.list.editor.ExpressionEditorDialog;
import vokabeltrainer.types.Expression;

public class EnterAction extends AbstractAction
{
   private ExpressionTable table;
   private ExpressionEditorDialog editor;
   private DictionaryControllerConnector connector;

   public EnterAction(ExpressionTable table, DictionaryControllerConnector connector)
   {
      this.table = table;
      this.connector = connector;
      editor = new ExpressionEditorDialog();
   }

   private static final long serialVersionUID = 719272853628204094L;

   @Override
   public void actionPerformed(ActionEvent e)
   {
      int selectedRow = table.getSelectedRow();
      if (selectedRow >= 0)
      {
         editor.setExpression((Expression) table.getValueAt(selectedRow, 0));
         editor.setLocationRelativeTo(null);
         editor.setVisible(true);
         if (editor.isSave())
         {
            table.setValueAt(editor.getExpression(), selectedRow, 0);
            ((ExpressionTableModel) table.getModel())
                  .fireTableCellUpdated(selectedRow, 0);
            connector.save();
         }
      }
   }
}
