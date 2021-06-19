package vokabeltrainer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import vokabeltrainer.common.Main;
import vokabeltrainer.editing.NikudDocument;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;

public class InputHebrewPanel extends JTextPane
{
   private static final long serialVersionUID = 2787773393300243696L;

   private JTextField hebrewField;
   private JTextField pleneField;
   private JTextField defektivField;

   private JButton toggleButton;

   private CardLayout layout;
   private Selection selection;
   private int heightTotal;
   private int heightBorderTitel;
   private List<JTextComponent> components = new ArrayList<>();

   private ComponentTitledBorder toggleBorder;

   private JPanel cards;

   public enum Selection
   {
      SIMPLE,
      PLENE_DEFEKTIV
   }

   public InputHebrewPanel(Selection selection, int heightTotal,
         int heightBorderTitel)
   {
      this.selection = selection;
      this.heightTotal = heightTotal;
      this.heightBorderTitel = heightBorderTitel;

      this.setLayout(new BullsEyeLayout(this));

      cards = new JPanel();
      layout = new CardLayout();
      cards.setLayout(layout);

      this.setOpaque(false);
      this.setBackground(ApplicationColors.getTransparent());

      toggleButton = new JButton(
            new ImageIcon(ApplicationImages.getToggleButtonIcon()));
      toggleButton.setFont(Main.getGermanFont(30F));
      toggleButton.setMargin(new Insets(-5, 0, -5, 0));
      toggleButton.setBackground(new Color(0, 0, 0, 0));
      toggleButton.setForeground(ApplicationColors.getGold());
      toggleButton.setPreferredSize(new Dimension(40, 32));

      toggleBorder = new ComponentTitledBorder(toggleButton, this,
            BorderFactory.createEmptyBorder(), 40);
      cards.setBorder(toggleBorder);

      cards.add("simple", initSimpleHebrew());
      cards.add("pleneDefektiv", initPleneDefektivHebrew());

      this.add(cards);
      initController();
      if (Selection.PLENE_DEFEKTIV == selection)
      {
         layout.next(cards);
      }
   }

   private void initController()
   {
      // TODO
      hebrewField.addCaretListener(event -> {
         if (!hebrewField.getText().isEmpty())
         {
            setBlankBorder();
         }
      });

      toggleButton.addActionListener(event -> toggleLayout());

      toggleButton.addMouseListener(new MouseAdapter()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {

         }

         @Override
         public void mousePressed(MouseEvent e)
         {

         }

         @Override
         public void mouseReleased(MouseEvent e)
         {

         }

         @Override
         public void mouseEntered(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e)
         {

            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         }

      });

   }

   private Component initPleneDefektivHebrew()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));

      pleneField = new JTextField();
      pleneField.setDocument(new NikudDocument(true));
      pleneField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      pleneField.setFont(Main.getHebrewFont(29F));
      pleneField.setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 50,
            (heightTotal - heightBorderTitel) / 2));
      pleneField.setMaximumSize(new Dimension(Settings.getKeyboardWidth() - 50,
            (heightTotal - heightBorderTitel) / 2));
      pleneField
            .setBorder(BorderFactory.createTitledBorder("Hebräisch, plene"));
      components.add(pleneField);

      defektivField = new JTextField();
      defektivField.setDocument(new NikudDocument(true));
      defektivField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      defektivField.setFont(Main.getHebrewFont(29F));
      defektivField
            .setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 50,
                  (heightTotal - heightBorderTitel) / 2));
      defektivField
            .setMaximumSize(new Dimension(Settings.getKeyboardWidth() - 50,
                  (heightTotal - heightBorderTitel) / 2));
      defektivField
            .setBorder(BorderFactory.createTitledBorder("Hebräisch, defektiv"));
      components.add(defektivField);

      vertical.add(pleneField);
      vertical.add(defektivField);

      return vertical;
   }

   private Component initSimpleHebrew()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));

      hebrewField = new JTextField();
      hebrewField.setDocument(new NikudDocument(true));
      hebrewField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      hebrewField.setFont(Main.getHebrewFont(29F));
      hebrewField.setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 50,
            (heightTotal - heightBorderTitel) / 2));
      hebrewField.setMaximumSize(new Dimension(Settings.getKeyboardWidth() - 50,
            (heightTotal - heightBorderTitel) / 2));
      hebrewField.setBorder(BorderFactory
            .createTitledBorder("Hebräisch, einfache Schreibweise"));

      components.add(hebrewField);

      vertical.add(hebrewField);
      vertical.add(new JPanel());

      return vertical;
   }

   private void toggleLayout()
   {
      switch (selection)
      {
      case SIMPLE:
         selection = Selection.PLENE_DEFEKTIV;
         layout.next(cards);
         this.hebrewField.setText("");
         break;
      case PLENE_DEFEKTIV:
         selection = Selection.SIMPLE;
         layout.first(cards);
         this.pleneField.setText("");
         this.defektivField.setText("");
      }
      Settings.toggleSimpleHebrewInput();
   }

   public void setHebrewLayout(Selection newSelection)
   {
      if (selection == newSelection)
      {
         return;
      }
      toggleLayout();
   }

   public boolean isSimple()
   {
      return Selection.SIMPLE == selection;
   }

   public String getHebrewFieldText()
   {
      return hebrewField.getText();
   }

   public String getPleneFieldText()
   {
      return pleneField.getText();
   }

   public String getDefektivFieldText()
   {
      return defektivField.getText();
   }

   public void setHebrewFieldText(String hebrewText)
   {
      hebrewField.setText(hebrewText);
   }

   public void setPleneFieldText(String pleneText)
   {
      pleneField.setText(pleneText);
   }

   public void setDefektivFieldText(String defektiveText)
   {
      defektivField.setText(defektiveText);
   }

   public boolean isFilledOut()
   {
      switch (selection)
      {
      case SIMPLE:
         return !hebrewField.getText().strip().isBlank();
      case PLENE_DEFEKTIV:
         return !pleneField.getText().strip().isBlank()
               && !defektivField.getText().strip().isBlank();
      }
      return false;
   }

   public void setBlankBorder()
   {
      BorderFactory.createEmptyBorder(3, 3, 3, 3);

   }

   public void setRedBorder()
   {
      this.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
   }

   @Override
   public void setBackground(Color color)
   {
      if (hebrewField != null)
      {
         hebrewField.setBackground(color);
      }
      if (pleneField != null)
      {
         pleneField.setBackground(color);
      }
      if (defektivField != null)
      {
         defektivField.setBackground(color);
      }
   }

   @Override
   public void setEnabled(boolean enabled)
   {
      if (hebrewField != null)
      {
         this.hebrewField.setEnabled(enabled);
      }
      if (pleneField != null)
      {
         this.pleneField.setEnabled(enabled);
      }
      if (defektivField != null)
      {
         this.defektivField.setEnabled(enabled);
      }
      if (toggleButton != null)
      {
         this.toggleButton.setEnabled(enabled);
      }
   }

   @Override
   public void setEditable(boolean editable)
   {
      if (hebrewField != null)
      {
         this.hebrewField.setEditable(editable);
      }
      if (pleneField != null)
      {
         this.pleneField.setEditable(editable);
      }
      if (defektivField != null)
      {
         this.defektivField.setEditable(editable);
      }
   }

   public Collection<? extends JTextComponent> getTextComponents()
   {
      return components;
   }
}
