package vokabeltrainer.panels.list;

import javax.swing.JList;

import vokabeltrainer.common.main.App;
import vokabeltrainer.types.Chapter;

import java.io.Serial;

public class ChapterList extends JList<Chapter>
{
   @Serial
   private static final long serialVersionUID = 6405025849507747779L;

   public ChapterList(App app, ChapterListSelectionModel selectionModel)
   {
      setSelectionModel(selectionModel);
      setFixedCellHeight(30);
      getActionMap().get("clearSelection").setEnabled(true);
      setCellRenderer(new ChapterListCellRenderer(app));
   }
}
