package vokabeltrainer.panels.trainer.dialog.table;

import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.IntegerSpinnerEditor;
import vokabeltrainer.editing.IntegerSpinnerModel;

public class TrainingTableCellRendererEditor
      implements TableCellEditor, TableCellRenderer
{
   private JTable table;
   private JLabel field;
   private JLabel totalWords;
   private JLabel notStudiedWords;
   private JSpinner amountOfNewWordsSpinner;
   private JButton amountOfNewWordsButton;
   private JLabel fieldDone;
   private JLabel fieldEmpty;
   private JLabel fieldNotStarted;
   private TrainingTableRow trainingCellRow;
   private boolean editingStopped = true;
   private IntegerSpinnerModel spinnerNumberModel;

   private int row;
   private int column;

   public TrainingTableCellRendererEditor()
   {
      Font font = Main.getGermanFont(20F);
      field = new JLabel();
      field.setFont(font);
      totalWords = new JLabel();
      totalWords.setFont(font);
      notStudiedWords = new JLabel();
      notStudiedWords.setFont(font);
      amountOfNewWordsSpinner = new JSpinner();
      amountOfNewWordsSpinner.setFont(font);
      
      spinnerNumberModel = new IntegerSpinnerModel();
      spinnerNumberModel.setMinimum(0);
      amountOfNewWordsSpinner.setModel(spinnerNumberModel);
      amountOfNewWordsSpinner.setEditor(new IntegerSpinnerEditor(amountOfNewWordsSpinner));
            

      amountOfNewWordsSpinner.addChangeListener(event -> {
         trainingCellRow.setAmountOfNewWords(
               Integer.valueOf((String) amountOfNewWordsSpinner.getValue()));
         ((TrainingTableModel) table.getModel()).fireTableCellUpdated(row,
               column);
         editingStopped = true;
      });
      amountOfNewWordsButton = new JButton();
      amountOfNewWordsButton.setFont(font);
      amountOfNewWordsButton.addActionListener(event -> {
         table.editCellAt(row, column);
      });
      fieldDone = new JLabel();
      fieldDone.setIcon(new ImageIcon(ApplicationImages.getDone()));
      fieldEmpty = new JLabel();
      fieldNotStarted = new JLabel();
      fieldNotStarted.setIcon(new ImageIcon(ApplicationImages.getEmptyList()));
   }

   @Override
   public Object getCellEditorValue()
   {
      return trainingCellRow;
   }

   @Override
   public boolean isCellEditable(EventObject anEvent)
   {
      return true;
   }

   @Override
   public boolean shouldSelectCell(EventObject anEvent)
   {
      return false;
   }

   @Override
   public boolean stopCellEditing()
   {
      try
      {
         amountOfNewWordsSpinner.commitEdit();
         editingStopped = true;
      }
      catch (ParseException e)
      {
         e.printStackTrace();
      }
      return editingStopped;
   }

   @Override
   public void cancelCellEditing()
   {

   }

   @Override
   public void addCellEditorListener(CellEditorListener l)
   {

   }

   @Override
   public void removeCellEditorListener(CellEditorListener l)
   {

   }

   @Override
   public Component getTableCellEditorComponent(JTable table, Object value,
         boolean isSelected, int row, int column)
   {
      if (this.table == null)
      {
         this.table = table;
      }
      this.trainingCellRow = (TrainingTableRow) value;
      this.row = row;
      this.column = column;

      if (column == 3)
      {
         
         spinnerNumberModel
               .setMaximum(((TrainingTableRow) value).getNotStudiedWords());
         amountOfNewWordsSpinner
               .setValue(String.valueOf(trainingCellRow.getAmountOfNewWords()));
         editingStopped = false;
         if(((TrainingTableRow) value).getNotStudiedWords() > 0)
         {
            return amountOfNewWordsSpinner;
         }
      }
      return null;
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      TrainingTableRow renderedTrainingCellRow = (TrainingTableRow) value;

      if (column == 0) // Bereich
      {
         field.setText(renderedTrainingCellRow.getField());
         return field;
      }

      if (column == 1)
      {
         totalWords.setText(
               String.valueOf(renderedTrainingCellRow.getTotalWords()));
         return totalWords;
      }

      if (column == 2) // Wörter gesamt
      {
         notStudiedWords.setText(
               String.valueOf(renderedTrainingCellRow.getNotStudiedWords()));
         return notStudiedWords;
      }

      if (column == 3 && renderedTrainingCellRow.getTotalWords() == 0) // ungelernte Wörter
      {
         fieldEmpty.setText("");
         return fieldEmpty;
      }
      else if (column == 3 && renderedTrainingCellRow.isFieldDone())
      {
         fieldEmpty.setText("");
         return fieldEmpty;
      }
      else if (column == 3)
      {
         amountOfNewWordsButton.setText(
               String.valueOf(renderedTrainingCellRow.getAmountOfNewWords()));
         return amountOfNewWordsButton;
      }
      
      
      if (column == 4 &&  renderedTrainingCellRow.getTotalWords() == 0) // leer, fertig
      {
         return fieldNotStarted;
      }
      else if (column == 4 &&  renderedTrainingCellRow.isFieldDone())
      {
         return fieldDone;
      }
      else if (column == 4)
      {
         fieldEmpty.setText("");
         return fieldEmpty;
      }
         
      return null;
   }

   public JSpinner getAmountOfNewWordsSpinner()
   {
      return amountOfNewWordsSpinner;
   }

}
