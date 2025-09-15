package vokabeltrainer.panels.list;

import javax.swing.JList;

import vokabeltrainer.common.colors.DictionaryColors;
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
      setFixedCellHeight(40);
      setBackground(DictionaryColors.getBackground());
      
      getActionMap().get("clearSelection").setEnabled(true);
      setCellRenderer(new DatabaseListCellRenderer());
   }
}
