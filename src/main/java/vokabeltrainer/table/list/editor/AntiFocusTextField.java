
package vokabeltrainer.table.list.editor;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Common;

public class AntiFocusTextField extends JTextField
{
   private static final long serialVersionUID = -4364086687323301340L;

   public AntiFocusTextField(String value)
   {
      super(value);
      setFocusable(false);
      setOpaque(true);
      setBackground(ApplicationColors.getBackgroundGold());
      setBorder(BorderFactory.createEmptyBorder());
      setEnabled(true);
      setFont(Common.getNimbus().getDefaults().getFont("internationalFont"));
   }

   public AntiFocusTextField()
   {
      setFocusable(false);
      setOpaque(true);
      setBackground(ApplicationColors.getBackgroundGold());
      setBorder(BorderFactory.createEmptyBorder());
      setEnabled(true);
      setFont(Common.getNimbus().getDefaults().getFont("internationalFont"));
   }

   @Override
   public String toString()
   {
      return this.getText();
   }

}
