package vokabeltrainer.panels.success.table.list;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import java.io.Serial;

public class SuccessListSelectionModel extends DefaultListSelectionModel
{
   @Serial
   private static final long serialVersionUID = 1683266153212839450L;

   public SuccessListSelectionModel()
   {
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

}
