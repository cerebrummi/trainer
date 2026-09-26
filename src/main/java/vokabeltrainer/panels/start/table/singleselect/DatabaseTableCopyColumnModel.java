package vokabeltrainer.panels.start.table.singleselect;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

import java.io.Serial;

public class DatabaseTableCopyColumnModel extends DefaultTableColumnModel
{
   @Serial
   private static final long serialVersionUID = -126413736438939824L;

   private DatabaseTableCopyCellRenderer renderer;
   private Translator translator;

   public DatabaseTableCopyColumnModel(App app, Common common, int totalWidth)
   {
      translator = common.getTranslator();
      renderer = new DatabaseTableCopyCellRenderer(app, common);
      int width = (totalWidth - 43) / 3;

      TableColumn column1 = new TableColumn();
      column1.setHeaderValue(
            translator.realisticTranslate(app, Translation.DATENBANK));
      column1.setCellRenderer(renderer);
      column1.setCellEditor(renderer);
      column1.setPreferredWidth(width + 43);
      addColumn(column1);

      TableColumn column2 = new TableColumn();
      column2
            .setHeaderValue(translator.realisticTranslate(app, Translation.AUTOREN));
      column2.setCellRenderer(renderer);
      column2.setCellEditor(renderer);
      column2.setPreferredWidth(width);
      addColumn(column2);

      TableColumn column3 = new TableColumn();
      column3.setHeaderValue(translator.realisticTranslate(app, Translation.VERLAG));
      column3.setCellRenderer(renderer);
      column3.setCellEditor(renderer);
      column3.setPreferredWidth(width);
      addColumn(column3);
   }

}
