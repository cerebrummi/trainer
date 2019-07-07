package vokabeltrainer.panels.list;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

public class StringListSelectionModel extends DefaultListSelectionModel
{
   private static final long serialVersionUID = 2889247964257966786L;

   public StringListSelectionModel()
   {
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

}
