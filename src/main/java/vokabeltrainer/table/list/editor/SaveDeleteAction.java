package vokabeltrainer.table.list.editor;

import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.AbstractAction;

public class SaveDeleteAction extends AbstractAction
{
   private static final long serialVersionUID = -6327534032645472378L;

   @Override
   public void actionPerformed(ActionEvent e)
   {
      
      ((JCheckBox) e.getSource()).setSelected(!((JCheckBox) e.getSource()).isSelected());

   }

}
