package vokabeltrainer.panels.sentences.table.list;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import vokabeltrainer.common.main.App;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.LetterType;

public class ExpressionListCellRenderer
      implements ListCellRenderer<Object>, Serializable
{
   private static final long serialVersionUID = -631988254932147508L;

   private JTextArea label;
   private JLabel selected;
   private JLabel empty;
   private App app;

   public ExpressionListCellRenderer(App app)
   {
      this.app = app;
      label = new JTextArea();
      label.setWrapStyleWord(true);
      label.setLineWrap(true);
      label.setOpaque(false);
      label.setEditable(false);
      label.setBackground(app.appColors.getTransparent());
      label.setBorder(BorderFactory.createEmptyBorder());
      selected = new JLabel(new ImageIcon(app.appImages.getSelect()));
      empty = new JLabel();
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends Object> list,
         Object value, int index, boolean isSelected, boolean cellHasFocus)
   {
      label.setText((String) value);
      if (index == 0)
      {
         if (Boolean.valueOf((String) value))
         {
            return selected;
         }
         return empty;
      }
      else if (index == 1)
      {
         if (LetterHelper
               .findLetterTypeLanguages((String) value) == LetterType.HEBREW)
         {
            label.setFont(app.appFonts.hebrewFont.deriveFont(20F));
            label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         }
         else
         {
            label.setFont(app.appFonts.germanBoldFont.deriveFont(20F));
         }
      }
      else
      {
         label.setFont(app.appFonts.germanFont.deriveFont(16F));
         label.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      }

      label.setPreferredSize(new Dimension(1380, 100));

      return new JScrollPane(label);
   }
}
