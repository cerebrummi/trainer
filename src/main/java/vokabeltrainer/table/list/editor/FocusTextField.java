
package vokabeltrainer.table.list.editor;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import vokabeltrainer.Settings;

public class FocusTextField extends JTextField
{
   private static final long serialVersionUID = -4364086687323301340L;

   public FocusTextField(String value)
   {
      super(value);
      setFocusable(false);
      setOpaque(true);
      setBackground(Settings.getBackgroundGold());
      setBorder(BorderFactory.createEmptyBorder());
      setEnabled(true);
   }
}
