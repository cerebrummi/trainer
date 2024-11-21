package vokabeltrainer.table.list;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.LetterType;

public class ExpressionListCellRenderer
      implements ListCellRenderer<Object>, Serializable
{
   private static final long serialVersionUID = -631988254932147508L;

   private JTextField label;
   private JLabel selected;
   private JLabel empty;

   public ExpressionListCellRenderer()
   {
      label = new JTextField();
      label.setOpaque(false);
      label.setEditable(false);
      label.setBackground(ApplicationColors.getTransparent());
      label.setBorder(BorderFactory.createEmptyBorder());
      selected = new JLabel(new ImageIcon(ApplicationImages.getSelect()));
      empty = new JLabel();
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends Object> list,
         Object value, int index, boolean isSelected, boolean cellHasFocus)
   {
      
      if (index == 0)
      {
         if (Boolean.valueOf((String) value))
         {
            return selected;
         }
         return empty;
      }
      if (index == 3)
      {
         label.setFont(ApplicationFonts.getHebrewHandwrittenFont(30));
         label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      }
      else if (index == 2)
      {
         label = new JTextField();
         label.setOpaque(false);
         label.setEditable(false);
         label.setBackground(ApplicationColors.getTransparent());
         label.setBorder(BorderFactory.createEmptyBorder());
         if(LetterHelper.findLetterTypeLanguages((String)value) == LetterType.HEBREW)
         {
            label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         }
         else
         {
            label.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         }
      }
      else if (index == 1)
      {
         label = new JTextField();
         label.setOpaque(false);
         label.setEditable(false);
         label.setBackground(ApplicationColors.getTransparent());
         label.setBorder(BorderFactory.createEmptyBorder());
         if(LetterHelper.findLetterTypeLanguages((String)value) == LetterType.HEBREW)
         {
            label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         }
         else
         {
            label.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         }
      }
      else
      {
         label.setFont(ApplicationFonts.getGermanFont(16F));
         label.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      }
      label.setText((String) value);
      return label;
   }
}
