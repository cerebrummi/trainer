package vokabeltrainer.table.list.editor;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import vokabeltrainer.common.colors.InputColors;

public class SearchwordListCellRenderer implements ListCellRenderer<String>
{

   private AntiFocusTextField listComponent;
   private ComponentOrientation orientation;

   @Override
   public Component getListCellRendererComponent(JList<? extends String> list,
         String value, int index, boolean isSelected, boolean cellHasFocus)
   {
      listComponent = new AntiFocusTextField(value);
      if (isSelected)
      {
         listComponent.setBackground(InputColors.getTextForeground());
         listComponent.setForeground(InputColors.getTextBackground());
      }
      else
      {
         listComponent
               .setBackground(InputColors.getTextBackground());
         listComponent.setForeground(InputColors.getTextForeground());
      }
      if(orientation != null)
      {
         listComponent.setComponentOrientation(orientation);
      }
      return listComponent;
   }

   public void setComponentOrientation(ComponentOrientation orientation)
   {
      this.orientation = orientation;
   }

   public Component getComponent()
   {
      return this.listComponent;
   }

}
