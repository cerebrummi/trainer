
package vokabeltrainer.table.list.editor;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.View;

public class AntiFocusTextField extends JTextField
{
   private static final long serialVersionUID = -4364086687323301340L;

   public AntiFocusTextField(App app, View view, String value)
   {
      super(value);
      setFocusable(false);
      setOpaque(true);
      setBackground(app.appColors.getBackgroundGold());
      setBorder(BorderFactory.createEmptyBorder());
      setEnabled(true);
      setFont(view.getNimbus().getDefaults().getFont("internationalFont"));
   }

   public AntiFocusTextField(App app, View view)
   {
      setFocusable(false);
      setOpaque(true);
      setBackground(app.appColors.getBackgroundGold());
      setBorder(BorderFactory.createEmptyBorder());
      setEnabled(true);
      setFont(view.getNimbus().getDefaults().getFont("internationalFont"));
   }

   @Override
   public String toString()
   {
      return this.getText();
   }

}
