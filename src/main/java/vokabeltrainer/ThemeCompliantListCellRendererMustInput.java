package vokabeltrainer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import vokabeltrainer.common.main.App;

public class ThemeCompliantListCellRendererMustInput
      implements ListCellRenderer<String>
{
   private ListCellRenderer<Object> wrappedRenderer = new DefaultListCellRenderer();

   private App app;
   
   public ThemeCompliantListCellRendererMustInput(App app)
   {
      this.app = app;
   }
   
   @Override
   public Component getListCellRendererComponent(JList<? extends String> list,
         String value, int index, boolean isSelected, boolean cellHasFocus)
   {
      Component c = wrappedRenderer.getListCellRendererComponent(list, value,
            index, isSelected, cellHasFocus);
      c.setBackground(app.appColors.getLightYellow());
      return c;
   }
}