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
   private int height;
   private List<JTextComponent> components = new ArrayList<>();

   private ComponentTitledBorder toggleBorder;

   private JPanel cards;

   public enum Selection
   {
      SIMPLE,
      PLENE_DEFEKTIVE
   }

   public InputHebrewPanel(Selection selection, int height)
   {
      this.selection = selection;
      this.height = height;
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
      if (Selection.PLENE_DEFEKTIVE == selection)
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
      pleneField.setMinimumSize(
            new Dimension(Settings.getKeyboardWidth() - 50, height / 2));
      pleneField.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() - 50, height / 2));
      pleneField.setBorder(BorderFactory.createTitledBorder("Hebräisch, plene"));
      components.add(pleneField);

      defektivField = new JTextField();
      defektivField.setDocument(new NikudDocument(true));
      defektivField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      defektivField.setFont(Main.getHebrewFont(29F));
      defektivField.setMinimumSize(
            new Dimension(Settings.getKeyboardWidth() - 50, height / 2));
      defektivField.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() - 50, height / 2));
      defektivField.setBorder(BorderFactory.createTitledBorder("Hebräisch, defektiv"));
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
      hebrewField.setMinimumSize(
            new Dimension(Settings.getKeyboardWidth() - 50, height / 2));
      hebrewField.setMaximumSize(
            new Dimension(Settings.getKeyboardWidth() - 50, height / 2));
      hebrewField.setBorder(BorderFactory.createTitledBorder("Hebräisch, einfache Schreibweise"));
      
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
         selection = Selection.PLENE_DEFEKTIVE;
         layout.next(cards);
         this.hebrewField.setText("");
         break;
      case PLENE_DEFEKTIVE:
         selection = Selection.SIMPLE;
         layout.first(cards);
         this.pleneField.setText("");
         this.defektivField.setText("");
      }
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

   public boolean isFilledOut()
   {
      switch (selection)
      {
      case SIMPLE:
         return !hebrewField.getText().strip().isBlank();
      case PLENE_DEFEKTIVE:
         return !pleneField.getText().strip().isBlank()
               && !defektivField.getText().strip().isBlank();
      }
      return false;
   }

   public void setBlankBorder()
   {
      // TODO Auto-generated method stub

   }

   public void setRedBorder()
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void setEnabled(boolean enabled)
   {
      // TODO
   }

   public Collection<? extends JTextComponent> getTextComponents()
   {
      return components;
   }
}
