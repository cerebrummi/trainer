package vokabeltrainer.table;

import java.awt.Component;
import java.util.EventObject;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.ImageData;
import vokabeltrainer.common.SoundData;
import vokabeltrainer.common.colors.TableColors;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.types.Expression;

public class ExpressionCellRenderer2
      implements TableCellRenderer, TableCellEditor
{
   private JPanel content;
   private JLabel picture;
   private JLabel sound;

   public ExpressionCellRenderer2()
   {
      content = new JPanel();
      TotemLayout layout = new TotemLayout(content, 15);
      content.setLayout(layout);
      picture = new JLabel();
      picture.setSize(60, 60);
      picture.setBorder(BorderFactory.createEmptyBorder());
      sound = new JLabel();
      sound.setSize(60, 60);
      sound.setBorder(BorderFactory.createEmptyBorder());
      
      content.add(picture);
      content.add(sound);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      Expression expression = (Expression) value;
      UUID uuid = expression.getUuid();
      if(ImageData.isImageForExpressionAvailable(uuid))
      {
    	  picture.setIcon(new ImageIcon(ApplicationImages.getIcon_eye()));
      }
      else
      {
    	  picture.setIcon(null);
      }
      if(SoundData.isSoundForExpressionAvailable(uuid))
      {
    	  sound.setIcon(new ImageIcon(ApplicationImages.getIcon_notes()));
      }
      else
      {
    	  sound.setIcon(null);
      }
      
      if (isSelected)
      {
         content.setBorder(BorderFactory.createLineBorder(ApplicationColors.brightGreen, 3));
      }
      else
      {
         content.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
      }

      if (row % 2 == 1)
      {
         content.setBackground(TableColors.getRow1());
      }
      else
      {
         content.setBackground(TableColors.getRow2());
      }
      
      return content;
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
}
