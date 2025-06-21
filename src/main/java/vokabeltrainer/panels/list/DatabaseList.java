package vokabeltrainer.panels.list;

import javax.swing.JList;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.types.DatabaseDescription;

public class DatabaseList extends JList<DatabaseDescription>
{

   /**
    * 
    */
   private static final long serialVersionUID = -8192484470250703122L;

   public DatabaseList(DatabaseListSelectionModel selectionModel)
   {
      setSelectionModel(selectionModel);
      setFixedCellHeight(30);
      setBackground(ApplicationColors.getShadyBlue());
      
      getActionMap().get("clearSelection").setEnabled(true);
      setCellRenderer(new DatabaseListCellRenderer());
   }
}
