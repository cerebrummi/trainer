package vokabeltrainer.table.list;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import java.io.Serial;

public class ExpressionListSelectionModel extends DefaultListSelectionModel
{
   @Serial
   private static final long serialVersionUID = -8862868649115178949L;

   public ExpressionListSelectionModel()
   {
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

}
