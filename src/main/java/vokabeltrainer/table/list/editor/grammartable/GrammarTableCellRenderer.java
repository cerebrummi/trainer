package vokabeltrainer.table.list.editor.grammartable;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbConjugation;
import vokabeltrainer.types.grammatical.VerbStrength;
import vokabeltrainer.types.grammatical.VerbType;

public class GrammarTableCellRenderer
      implements TableCellRenderer, TableCellEditor
{
   private JLabel selected;
   private JLabel empty;
   private JComboBox<Binjan> binjanBox;
   private JComboBox<Gender> genderBox;
   private JComboBox<GrammaticalPerson> grammaticalPersonBox;
   private JComboBox<Numerus> numerusBox;
   private JComboBox<VerbConjugation> verbConjugationBox;
   private JComboBox<VerbStrength> verbStrengthBox;
   private JComboBox<VerbType> verbTypeBox;

   public GrammarTableCellRenderer()
   {
      selected = new JLabel(new ImageIcon(ApplicationImages.getSelectDone()));
      selected.setBackground(Settings.getVeryLightGold());
      selected.setOpaque(true);

      empty = new JLabel();
      empty.setBackground(Settings.getVeryLightGold());
      empty.setOpaque(true);

      binjanBox = new JComboBox<>(Binjan.values());
      binjanBox.setFont(Main.getHebrewFont(18F));
      binjanBox.setBackground(Settings.getVeryLightGold());
      binjanBox.setOpaque(true);

      genderBox = new JComboBox<>(Gender.values());
      genderBox.setFont(Main.getHebrewFont(18F));
      genderBox.setBackground(Settings.getVeryLightGold());
      genderBox.setOpaque(true);
      
      grammaticalPersonBox = new JComboBox<>(GrammaticalPerson.values());
      grammaticalPersonBox.setFont(Main.getHebrewFont(18F));
      grammaticalPersonBox.setBackground(Settings.getVeryLightGold());
      grammaticalPersonBox.setOpaque(true);
      
      numerusBox = new JComboBox<>(Numerus.values());
      numerusBox.setFont(Main.getHebrewFont(18F));
      numerusBox.setBackground(Settings.getVeryLightGold());
      numerusBox.setOpaque(true);
      
      verbConjugationBox = new JComboBox<>(VerbConjugation.values());
      verbConjugationBox.setFont(Main.getHebrewFont(18F));
      verbConjugationBox.setBackground(Settings.getVeryLightGold());
      verbConjugationBox.setOpaque(true);
      
      verbStrengthBox = new JComboBox<>(VerbStrength.values());
      verbStrengthBox.setFont(Main.getHebrewFont(18F));
      verbStrengthBox.setBackground(Settings.getVeryLightGold());
      verbStrengthBox.setOpaque(true);
      
      verbTypeBox = new JComboBox<>(VerbType.values());
      verbTypeBox.setFont(Main.getHebrewFont(18F));
      verbTypeBox.setBackground(Settings.getVeryLightGold());
      verbTypeBox.setOpaque(true);
   }

   @Override
   public Object getCellEditorValue()
   {
      return null;
   }

   @Override
   public boolean isCellEditable(EventObject anEvent)
   {
      return false;
   }

   @Override
   public boolean shouldSelectCell(EventObject anEvent)
   {
      return false;
   }

   @Override
   public boolean stopCellEditing()
   {
      return false;
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
      return null;
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      GrammaticalEnum grammaticalEnum = ((GrammarTableRow) value)
            .getGrammaticalEnum();

      if (column == 0)
      {
         if (grammaticalEnum.isSelected())
         {
            return selected;
         }
         else
         {
            return empty;
         }
      }

      switch (grammaticalEnum.getParent())
      {
      case BINJAN:
         this.binjanBox.setSelectedItem(grammaticalEnum);
         return this.binjanBox;
      case GENDER:
         this.genderBox.setSelectedItem(grammaticalEnum);
         return this.genderBox;
      case GRAMMATICAL_PERSON:
         this.grammaticalPersonBox.setSelectedItem(grammaticalEnum);
         return this.grammaticalPersonBox;
      case NUMERUS:
         this.numerusBox.setSelectedItem(grammaticalEnum);
         return this.numerusBox;
      case VERB_CONJUGATION:
         this.verbConjugationBox.setSelectedItem(grammaticalEnum);
         return this.verbConjugationBox;
      case VERB_STRENGTH:
         this.verbStrengthBox.setSelectedItem(grammaticalEnum);
         return this.verbStrengthBox;
      case VERB_TYPE:
         this.verbTypeBox.setSelectedItem(grammaticalEnum);
         return this.verbTypeBox;
      default:
         return null;
      }

   }
}
