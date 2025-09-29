package vokabeltrainer.panels.success.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class SuccessTableColumnModel extends DefaultTableColumnModel
{
   private static final long serialVersionUID = -496621432204186003L;

   private SuccessTableCellRenderer renderer;
   private Translator translator = Common.getTranslator();
   
   public SuccessTableColumnModel()
   {
      renderer = new SuccessTableCellRenderer();
      
      TableColumn column = new TableColumn();
      column.setHeaderValue(translator.realisticTranslate(Translation.AUSGEWAEHLT));
      column.setCellRenderer(renderer);
      column.setCellEditor(renderer);
      column.setPreferredWidth(100);
      addColumn(column);
      
      TableColumn column1 = new TableColumn();
      column1.setHeaderValue(translator.realisticTranslate(Translation.WORT));
      column1.setCellRenderer(renderer);
      column1.setCellEditor(renderer);
      column1.setPreferredWidth(500);
      addColumn(column1);
      
      TableColumn column2 = new TableColumn();
      column2.setHeaderValue(translator.realisticTranslate(Translation.KAPITEL));
      column2.setCellRenderer(renderer);
      column2.setCellEditor(renderer);
      column2.setPreferredWidth(600);
      addColumn(column2);
   }
}
