package vokabeltrainer.table.list.editor.grammartable;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.types.grammatical.GrammaticalEnum;

public class GrammarTableCellRenderer implements TableCellRenderer, TableCellEditor
{
   private JLabel selected;
   private JLabel empty;
   private JComboBox<? extends GrammaticalEnum> grammaticalEnum;

   public GrammarTableCellRenderer()
   {
      selected = new JLabel(new ImageIcon(ApplicationImages.getSelectDone()));
      selected.setBackground(Settings.getVeryLightGold());
      selected.setOpaque(true);
      
      empty = new JLabel();
      empty.setBackground(Settings.getVeryLightGold());
      empty.setOpaque(true);

      grammaticalEnum = new JComboBox<>();
      grammaticalEnum.setFont(Main.getHebrewFont(18F));
      grammaticalEnum.setBackground(Settings.getVeryLightGold());
      grammaticalEnum.setOpaque(true);
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
      GrammaticalEnum grammaticalEnum = ((GrammarTableRow) value).getGrammaticalEnum();

      if (column == 0)
      {
         if (grammaticalEnum.isSelected())
         {
            return selected;
         }
         else
         {
            return empty;
         }
      }

      this.grammaticalEnum.setSelectedItem(grammaticalEnum.toString());
      return this.grammaticalEnum;
   }
}
