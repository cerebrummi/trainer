package vokabeltrainer.table.list.editor;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.View;

public class SearchwordListCellRenderer implements ListCellRenderer<String>
{
   private AntiFocusTextField listComponent;
   private ComponentOrientation orientation;
   
   private App app;
   private View view;
   
   public SearchwordListCellRenderer(App app, View view)
   {
      this.app = app;
      this.view = view;
   }

   @Override
   public Component getListCellRendererComponent(JList<? extends String> list,
         String value, int index, boolean isSelected, boolean cellHasFocus)
   {
      listComponent = new AntiFocusTextField(app, view, value);
      if (isSelected)
      {
         listComponent.setBackground(app.appColors.input.getTextForeground());
         listComponent.setForeground(app.appColors.input.getTextBackground());
      }
      else
      {
         listComponent.setBackground(app.appColors.input.getTextBackground());
         listComponent.setForeground(app.appColors.input.getTextForeground());
      }
      if (orientation != null)
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
