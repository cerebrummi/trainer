package vokabeltrainer.table.list.editor.grammartable;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class GrammarTableModel extends DefaultTableModel
{

   private static final long serialVersionUID = 5531116455769991535L;

   private Vector<Vector<GrammarTableRow>> data;

   public GrammarTableModel(Vector<Vector<GrammarTableRow>> data,
         Vector<String> columnNames)
   {
      super(data, columnNames);
      this.data = data;
   }

   public Vector<Vector<GrammarTableRow>> getData()
   {
      return data;
   }
}
