package vokabeltrainer.panels.list;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import java.io.Serial;

public class ChapterListSelectionModel extends DefaultListSelectionModel
{
   @Serial
   private static final long serialVersionUID = 2889247964257966786L;

   public ChapterListSelectionModel()
   {
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

}
