package vokabeltrainer.panels.success.table;

import java.awt.Component;
import java.awt.Font;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.common.Main;
import vokabeltrainer.types.Expression;

public class SuccessTableCellRenderer
      implements TableCellRenderer, TableCellEditor
{
   private JLabel selected;
   private JLabel empty;
   private JLabel expressionGerman;

   public SuccessTableCellRenderer()
   {
      Font font = Main.getGermanFont(20F);

      selected = new JLabel(new ImageIcon(ApplicationImages.getSelect()));
      empty = new JLabel();

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

      if (column == 0)
      {
         if (expression.isSelected())
         {
            return selected;
         }
         else
         {
            return empty;
         }
      }

      expressionGerman.setText(expression.getWordGermanForSuccess());
      return expressionGerman;
   }

}
