package vokabeltrainer.table;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import vokabeltrainer.Settings;
import vokabeltrainer.panels.dictionary.DictionaryControllerConnector;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class ExpressionTable extends JTable
{
   private static final long serialVersionUID = -9006217392935409400L;
   private Language language;
   private ExpressionTableModel model;

   public ExpressionTable(ExpressionTableModel dm, Language language,
         DictionaryControllerConnector connector, boolean editable)
   {
      super(dm, new ExpressionColumnModel(language, editable));
      this.model = dm;
      this.language = language;
      setRowHeight(Settings.dictionaryTableRowHeight());
      putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
      setShowHorizontalLines(false);
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(true);
      this.setCellSelectionEnabled(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.setSurrendersFocusOnKeystroke(true);

      if (editable)
      {
         String editCommand = "edit";
         KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
         getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter,
               editCommand);
         getActionMap().put(editCommand, new EnterAction(this, connector));
      }

      String selectCommand = "select";
      KeyStroke select = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
      getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(select,
            selectCommand);
      getActionMap().put(selectCommand, new SelectAction(this));

      addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1
                  && row == table.getSelectedRow())
            {
               Expression expression = ((Expression) table
                     .getValueAt(table.getSelectedRow(), 0));

               expression.setSelected(!expression.isSelected());

               ((ExpressionTableModel) table.getModel())
                     .fireTableCellUpdated(table.getSelectedRow(), 0);
            }
         }
      });
   }

   public String getTableDataToString()
   {
      StringJoiner joiner = new StringJoiner("\n\n");
      for (Expression[] expressionArray : model.getTableData())
      {
         joiner.add(expressionArray[0].getCopyLines(language));
      }
      return joiner.toString();
   }

   public String getSelectedTableDataToString()
   {
      StringJoiner joiner = new StringJoiner("\n\n");
      for (Expression[] expressionArray : model.getTableData())
      {
         if (expressionArray[0].isSelected())
         {
            joiner.add(expressionArray[0].getCopyLines(language));
         }
      }
      return joiner.toString();
   }

   public void clearTableDataSelection()
   {
      for (Expression[] expressionArray : model.getTableData())
      {
         expressionArray[0].setSelected(false);
      }
   }

   public List<Expression> getSelectedExpressions()
   {
      List<Expression> list = new ArrayList<>();

      for (Expression[] expressionArray : model.getTableData())
      {
         if (expressionArray[0].isSelected())
         {
            list.add(expressionArray[0]);
         }
      }

      return list;
   }

   public void selectAllExpressions()
   {
      for (Expression[] expressionArray : model.getTableData())
      {
         expressionArray[0].setSelected(true);
      }
   }
}
