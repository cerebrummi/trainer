package vokabeltrainer.panels.success.table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.main.App;
import vokabeltrainer.panels.success.table.list.SuccessList;
import vokabeltrainer.types.Expression;

public class SuccessTableCellRenderer
      implements TableCellRenderer, TableCellEditor
{
   private JLabel selected;
   private JLabel empty;
   private JLabel chapter;

   private SuccessList content;

   public SuccessTableCellRenderer(App app)
   {
      selected = new JLabel(new ImageIcon(app.appImages.getSelect()));
      selected.setBackground(app.appColors.success.getTableBackground());
      selected.setOpaque(true);

      empty = new JLabel();
      empty.setBackground(app.appColors.success.getTableBackground());
      empty.setOpaque(true);
      empty.setForeground(app.appColors.success.getTextForeground());

      content = new SuccessList(app);
      content.setBackground(app.appColors.success.getTableBackground());
      content.setForeground(app.appColors.success.getTextForeground());
      content.setOpaque(true);

      chapter = new JLabel();
      chapter.setFont(app.appFonts.germanFont.deriveFont(20f));
      chapter.setBackground(app.appColors.success.getTableBackground());
      chapter.setOpaque(true);
      chapter.setForeground(app.appColors.success.getTextForeground());
      chapter.setHorizontalAlignment(SwingConstants.CENTER);
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

      if (column == 1)
      {
         content
               .setListData(expression.getGermanHebrewGrammarArrayForSuccess());
         return this.content;
      }

      chapter.setText(expression.getChapter().getName());
      return chapter;
   }

}
