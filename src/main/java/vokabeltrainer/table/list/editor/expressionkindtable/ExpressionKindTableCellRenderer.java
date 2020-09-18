package vokabeltrainer.table.list.editor.expressionkindtable;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class ExpressionKindTableCellRenderer implements TableCellRenderer, TableCellEditor
{

   private JLabel selected;
   private JLabel empty;
   private JLabel expressionKind;

   public ExpressionKindTableCellRenderer()
   {
      selected = new JLabel(new ImageIcon(ApplicationImages.getSelect()));
      selected.setBackground(Settings.getVeryLightGold());
      selected.setOpaque(true);
      
      empty = new JLabel();
      empty.setBackground(Settings.getVeryLightGold());
      empty.setOpaque(true);

      expressionKind = new JLabel();
      expressionKind.setFont(Main.getHebrewFont(18F));
      expressionKind.setBackground(Settings.getVeryLightGold());
      expressionKind.setOpaque(true);
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
      ExpressionKind expressionKind = ((ExpressionKindTableRow) value).getExpressionKind();

      if (column == 0)
      {
         if (expressionKind.isSelected())
         {
            return selected;
         }
         else
         {
            return empty;
         }
      }

      this.expressionKind.setText(expressionKind.toString());
      return this.expressionKind;
   }

}
