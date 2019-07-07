package vokabeltrainer.panels.list;

import javax.swing.JList;

public class StringList extends JList<String>
{
   private static final long serialVersionUID = -7098991033192776485L;

   public StringList(StringListSelectionModel selectionModel)
   {
      setSelectionModel(selectionModel);
      setFixedCellHeight(30);
      getActionMap().get("clearSelection").setEnabled(true);
      setCellRenderer(new StringListCellRenderer());
   }
}
