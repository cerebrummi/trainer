package vokabeltrainer.panels.success.table;

import java.awt.Component;
import java.awt.Font;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.TrainingStatus;

public class SuccessTableCellRenderer
      implements TableCellRenderer, TableCellEditor
{
   private JCheckBox box;
   private JLabel expressionGerman;

   public SuccessTableCellRenderer()
   {
      Font font = Main.getGermanFont(20F);

      box = new JCheckBox();
      
      expressionGerman = new JLabel();
      expressionGerman.setFont(font);
   }

   @Override
   public Object getCellEditorValue()
   {
      return null;
   }

   @Override
   public boolean isCellEditable(EventObject anEvent)
   {
      return false;
   }

   @Override
   public boolean shouldSelectCell(EventObject anEvent)
   {
      return false;
   }

   @Override
   public boolean stopCellEditing()
   {
      return false;
   }

   @Override
   public void cancelCellEditing()
   {

   }

   @Override
   public void addCellEditorListener(CellEditorListener l)
   {

   }

   @Override
   public void removeCellEditorListener(CellEditorListener l)
   {

   }

   @Override
   public Component getTableCellEditorComponent(JTable table, Object value,
         boolean isSelected, int row, int column)
   {
      return null;
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      Expression expression = ((SuccessTableRow) value).getExpression();

      if(column == 0)
      {
         box.setSelected(expression.isSelected());
         return box;
      }
      
      expressionGerman.setText(expression.getWordGermanForSuccess());
      return expressionGerman;
   }

}
