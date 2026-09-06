package vokabeltrainer.panels.success.table.list;

import javax.swing.BorderFactory;
import javax.swing.JList;

import vokabeltrainer.common.main.App;

public class SuccessList extends JList<String>
{

   private static final long serialVersionUID = 986774920269343277L;

   public SuccessList(App app)
   {
      setSelectionModel(new SuccessListSelectionModel());
      setBorder(BorderFactory.createEmptyBorder());
      setVisibleRowCount(3);
      this.setFixedCellHeight(25);

      setCellRenderer(new SuccessListCellRenderer(app));
   }

}
