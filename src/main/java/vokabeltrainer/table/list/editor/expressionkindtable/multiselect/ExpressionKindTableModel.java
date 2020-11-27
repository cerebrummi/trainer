package vokabeltrainer.table.list.editor.expressionkindtable.multiselect;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ExpressionKindTableModel extends DefaultTableModel
{
   private static final long serialVersionUID = 3445974088132962568L;

   private Vector<Vector<ExpressionKindTableRow>> data;

   public ExpressionKindTableModel(Vector<Vector<ExpressionKindTableRow>> data,
         Vector<String> columnNames)
   {
      super(data, columnNames);
      this.data = data;
   }

   public Vector<Vector<ExpressionKindTableRow>> getData()
   {
      return data;
   }
}
