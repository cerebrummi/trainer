package vokabeltrainer.panels.list;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

public class DatabaseListSelectionModel extends DefaultListSelectionModel
{

   /**
    * 
    */
   private static final long serialVersionUID = -4370452523552014692L;

   public DatabaseListSelectionModel()
   {
      setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
   }

}
