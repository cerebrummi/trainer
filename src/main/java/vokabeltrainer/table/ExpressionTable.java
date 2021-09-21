package vokabeltrainer.table;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import vokabeltrainer.Settings;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class ExpressionTable extends JTable
{
   private static final long serialVersionUID = -9006217392935409400L;
   private Language language;
   private ExpressionTableModel model;

   public ExpressionTable(ExpressionTableModel dm, Language language,
         TableConnector connector, boolean editable)
   {
      super(dm, new ExpressionColumnModel(language));
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

      if (editable && connector != null)
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

               expression.toggleSelected();

               ((ExpressionTableModel) table.getModel())
                     .fireTableCellUpdated(table.getSelectedRow(), 0);
            }
         }
      });
   }

   public String getTableDataToString()
   {
      return Arrays.stream(model.getTableData())
         .filter(expressionArray -> expressionArray[0].isDoChange())
         .map(expressionArray -> expressionArray[0].getCopyLines(language))
         .collect(Collectors.joining("\n"));
   }

   public String getSelectedTableDataToString()
   {
      return Arrays.stream(model.getTableData())
            .filter(expressionArray -> expressionArray[0].isDoChange())
            .filter(expressionArray -> expressionArray[0].isSelected())
            .map(expressionArray -> expressionArray[0].getCopyLines(language))
            .collect(Collectors.joining("\n"));
   }

   public void clearTableDataSelection()
   {
      for (Expression[] expressionArray : model.getTableData())
      {
         expressionArray[0].setSelected(false);
      }
   }

   public List<Expression> getSelectedExpressions(boolean exceptDoNotChange)
   {
      List<Expression> list = new ArrayList<>();

      for (Expression[] expressionArray : model.getTableData())
      {
         if(exceptDoNotChange && expressionArray[0].isDoNotChange())
         {
            continue;
         }
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
