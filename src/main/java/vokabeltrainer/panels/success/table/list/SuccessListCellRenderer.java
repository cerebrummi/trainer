package vokabeltrainer.panels.success.table.list;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.io.Serial;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import vokabeltrainer.common.main.App;

public class SuccessListCellRenderer
      implements ListCellRenderer<Object>, Serializable
{
   @Serial
   private static final long serialVersionUID = -5594206011077904779L;

   private JTextField german;
   private JTextField hebrew;
   private JTextField grammar;

   public SuccessListCellRenderer(App app)
   {
      german = new JTextField();
      hebrew = new JTextField();
      grammar = new JTextField();

      german.setOpaque(false);
      hebrew.setOpaque(false);
      grammar.setOpaque(false);

      german.setEditable(false);
      hebrew.setEditable(false);
      grammar.setEditable(false);

      german.setBackground(app.appColors.getTransparent());
      hebrew.setBackground(app.appColors.getTransparent());
      grammar.setBackground(app.appColors.getTransparent());

      german.setBorder(BorderFactory.createEmptyBorder());
      hebrew.setBorder(BorderFactory.createEmptyBorder());
      grammar.setBorder(BorderFactory.createEmptyBorder());

      german.setFont(app.appFonts.germanFont.deriveFont(16F));
      hebrew.setFont(app.appFonts.hebrewFont.deriveFont(20F));
      grammar.setFont(app.appFonts.germanFont.deriveFont(16F));

      german.setForeground(app.appColors.success.getTextForeground());
      hebrew.setForeground(app.appColors.success.getTextForeground());
      grammar.setForeground(app.appColors.success.getTextForeground());

      hebrew.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends Object> list,
         Object value, int index, boolean isSelected, boolean cellHasFocus)
   {
      if (index == 0)
      {
         german.setText((String) value);
         return german;
      }
      if (index == 1)
      {
         hebrew.setText((String) value);
         return hebrew;
      }
      grammar.setText((String) value);
      return grammar;
   }

}
