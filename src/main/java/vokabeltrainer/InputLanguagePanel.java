package vokabeltrainer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusEvent.Cause;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.NikudStyledDocument;
import vokabeltrainer.editing.SwedishDocument;
import vokabeltrainer.keyboards.KeyboardLanguage;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.list.editor.LanguageExpressionEditorView;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.types.LLType;

public class InputLanguagePanel extends JTextArea
{
   private static final long serialVersionUID = 2787773393300243696L;

   private JTextPane hebrewField;
   private JTextPane pleneField;
   private JTextPane defektivField;
   private JTextArea swedishField;
   private JTextArea germanField;

   private JButton toggleButton;

   private CardLayout layout;
   private Selection selection;
   private int heightTotal;
   private int widthTotal;
   private int heightBorderTitel;
   private List<JTextComponent> components = new ArrayList<>();

   private ComponentTitledBorder toggleBorder;

   private JPanel cards;

   private Container parent;
   private Translator translator = Common.getTranslator();

   private Color color;

   private KeyboardLanguage keyboard;
   
   private LanguageExpressionEditorView editorView;

   public void setKeyboard(KeyboardLanguage keyboard)
   {
      this.keyboard = keyboard;
   }
   
   public void setEditorView(LanguageExpressionEditorView editorView)
   {
      this.editorView = editorView;
   }

   public enum Selection
   {
      SIMPLE, PLENE_DEFEKTIV, SWEDISH, GERMAN;
   }

   class FocusForwardAction extends AbstractAction
   {
      private static final long serialVersionUID = -8790050293258845388L;

      @Override
      public void actionPerformed(ActionEvent e)
      {
         if (Selection.SIMPLE == selection && hebrewField.isFocusOwner())
         {
            forwardToOutsideTraversalCycle();
         }
         else if (Selection.PLENE_DEFEKTIV == selection
               && pleneField.isFocusOwner())
         {
            forwardPleneDefectiv();
         }
         else if (Selection.SWEDISH == selection && swedishField.isFocusOwner())
         {
            forwardToOutsideTraversalCycle();
         }
         else if (Selection.GERMAN == selection && germanField.isFocusOwner())
         {
            forwardToOutsideTraversalCycle();
         }
      }

      private void forwardPleneDefectiv()
      {
         defektivField.requestFocusInWindow();
      }

      private void forwardToOutsideTraversalCycle()
      {
         parent.getFocusTraversalPolicy()
               .getComponentAfter(parent, InputLanguagePanel.this)
               .requestFocusInWindow(Cause.TRAVERSAL_FORWARD);
      }

   }

   class FocusBackwardAction extends AbstractAction
   {
      private static final long serialVersionUID = -8790050293258845388L;

      @Override
      public void actionPerformed(ActionEvent e)
      {
         if (Selection.SIMPLE == selection && hebrewField.isFocusOwner())
         {
            backwardToOutsideTraversalCycle();
         }
         else if (Selection.PLENE_DEFEKTIV == selection
               && defektivField.isFocusOwner())
         {
            backwardInside();
         }
         else if (Selection.SWEDISH == selection && swedishField.isFocusOwner())
         {
            backwardToOutsideTraversalCycle();
         }
         else if (Selection.GERMAN == selection && germanField.isFocusOwner())
         {
            backwardToOutsideTraversalCycle();
         }
      }

      private void backwardInside()
      {
         pleneField.requestFocusInWindow();
      }

      private void backwardToOutsideTraversalCycle()
      {
         parent.getFocusTraversalPolicy()
               .getComponentBefore(parent, InputLanguagePanel.this)
               .requestFocusInWindow(Cause.TRAVERSAL_FORWARD);
      }

   }

   public InputLanguagePanel(Selection selection, int heightTotal,
         int heightBorderTitel, boolean canBeToggled, Container parent,
         int widthTotal, Color color)
   {
      this.selection = selection;

      this.heightTotal = heightTotal;
      this.heightBorderTitel = heightBorderTitel;
      this.parent = parent;
      this.widthTotal = widthTotal;
      this.color = color;

      this.setLayout(new BullsEyeLayout(this));

      cards = new JPanel();
      layout = new CardLayout();
      cards.setLayout(layout);
      cards.setOpaque(true);
      cards.setBackground(ApplicationColors.getWhite());
      cards.setBorder(BorderFactory.createEmptyBorder());

      this.setOpaque(true);
      this.setBackground(ApplicationColors.getWhite());

      toggleButton = new JButton(
            new ImageIcon(ApplicationImages.getToggleButtonIcon()));

      if (canBeToggled)
      {
         toggleButton.setFont(ApplicationFonts.getGermanFont(30F));
         toggleButton.setMargin(new Insets(-5, 0, -5, 0));
         toggleButton.setBackground(new Color(0, 0, 0, 0));
         toggleButton.setForeground(ApplicationColors.getGold());
         toggleButton.setPreferredSize(new Dimension(40, 32));
         toggleButton.setActionCommand("was_toggled");
         toggleBorder = new ComponentTitledBorder(toggleButton, this,
               BorderFactory.createEmptyBorder(), 40);
         cards.setBorder(toggleBorder);
      }
      else
      {
         cards.setBorder(BorderFactory.createEmptyBorder());
      }

      cards.add(Selection.GERMAN.name(), initGerman());
      cards.add(Selection.SWEDISH.name(), initSwedish());
      cards.add(Selection.PLENE_DEFEKTIV.name(), initPleneDefektivHebrew());
      cards.add(Selection.SIMPLE.name(), initSimpleHebrew());

      this.add(cards);
      initController();

      String focusCommand = "focus_forward";
      KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
      getInputMap(InputLanguagePanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(tab, focusCommand);
      getActionMap().put(focusCommand, new FocusForwardAction());

      String focusBackwardCommand = "focus_backward";
      KeyStroke tabBack = KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
            InputEvent.SHIFT_DOWN_MASK);
      getInputMap(InputLanguagePanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(tabBack, focusBackwardCommand);
      getActionMap().put(focusBackwardCommand, new FocusBackwardAction());
      
      layout.show(cards, selection.name());
   }

   private void initController()
   {
      addFocusListener(new FocusListener()
      {
         @Override
         public void focusGained(FocusEvent e)
         {
            fromOutsideToInside(e);
         }

         private void fromOutsideToInside(FocusEvent e)
         {
            if (e.getCause() == Cause.TRAVERSAL_FORWARD)
            {
               if (Selection.SIMPLE == selection)
               {
                  hebrewField.requestFocusInWindow();
               }
               else if (Selection.PLENE_DEFEKTIV == selection)
               {
                  pleneField.requestFocusInWindow();
               }
               else if(Selection.SWEDISH == selection)
               {
                  swedishField.requestFocusInWindow();
               }
               else if(Selection.GERMAN == selection)
               {
                  germanField.requestFocusInWindow();
               }
            }
            else if (e.getCause() == Cause.TRAVERSAL_BACKWARD)
            {
               if (Selection.SIMPLE == selection)
               {
                  hebrewField.requestFocusInWindow();
               }
               else if (Selection.PLENE_DEFEKTIV == selection)
               {
                  defektivField.requestFocusInWindow();
               }
               else if(Selection.SWEDISH == selection)
               {
                  swedishField.requestFocusInWindow();
               }
               else if(Selection.GERMAN == selection)
               {
                  germanField.requestFocusInWindow();
               }
            }
         }

         @Override
         public void focusLost(FocusEvent e)
         {
         }
      });

      hebrewField.addCaretListener(event -> {
         if (!hebrewField.getText().isEmpty())
         {
            setBlankBorder();
         }
      });
      pleneField.addCaretListener(event -> {
         if (!pleneField.getText().isBlank()
               && !defektivField.getText().isBlank())
         {
            setBlankBorder();
         }
      });
      defektivField.addCaretListener(event -> {
         if (!pleneField.getText().isBlank()
               && !defektivField.getText().isBlank())
         {
            setBlankBorder();
         }
      });
      swedishField.addCaretListener(event -> {
         if (!swedishField.getText().isEmpty())
         {
            setBlankBorder();
         }
      });
      germanField.addCaretListener(event -> {
         if (!germanField.getText().isEmpty())
         {
            setBlankBorder();
         }
      });

      toggleButton.addActionListener(event -> toggleNext());

      toggleButton.addMouseListener(new MouseAdapter()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {
            toggleFocusInside();
         }

         private void toggleFocusInside()
         {
            if (Selection.SIMPLE == selection)
            {
               hebrewField.requestFocusInWindow();
            }
            else if (Selection.PLENE_DEFEKTIV == selection)
            {
               pleneField.requestFocusInWindow();
            }
            else if(Selection.SWEDISH == selection)
            {
               swedishField.requestFocusInWindow();
            }
            else if(Selection.GERMAN == selection)
            {
               germanField.requestFocusInWindow();
            }
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
      vertical.setBackground(this.color);
      vertical.setBorder(BorderFactory.createLineBorder(this.color));

      pleneField = new JTextPane();
      pleneField.setDocument(new NikudStyledDocument(true));
      pleneField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      pleneField.setFont(ApplicationFonts.getHebrewFont(30F));
      pleneField.setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 30,
            (heightTotal - heightBorderTitel) / 2));
      pleneField.setMaximumSize(new Dimension(this.widthTotal,
            (heightTotal - heightBorderTitel) / 2));
      pleneField.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.HEBRAEISCH__PLENE)));
      this.changeLineSpacing(pleneField);
      components.add(pleneField);

      defektivField = new JTextPane();
      defektivField.setDocument(new NikudStyledDocument(true));
      defektivField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      defektivField.setFont(ApplicationFonts.getHebrewFont(30F));
      defektivField
            .setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 30,
                  (heightTotal - heightBorderTitel) / 2));
      defektivField.setMaximumSize(new Dimension(this.widthTotal,
            (heightTotal - heightBorderTitel) / 2));
      defektivField.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.HEBRAEISCH__DEFEKTIV)));
      this.changeLineSpacing(defektivField);
      components.add(defektivField);

      vertical.add(pleneField);
      vertical.add(defektivField);

      return vertical;
   }

   private Component initSimpleHebrew()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setBackground(this.color);
      vertical.setOpaque(true);

      hebrewField = new JTextPane();
      hebrewField.setFont(ApplicationFonts.getHebrewFont(30F));
      hebrewField.setDocument(new NikudStyledDocument(true));
      hebrewField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);     
      hebrewField.setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 30,
            (heightTotal - heightBorderTitel)));
      hebrewField.setMaximumSize(
            new Dimension(this.widthTotal, (heightTotal - heightBorderTitel)));
      hebrewField.setBorder(
            BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0,0,0,10), translator.realisticTranslate(
                  Translation.HEBRAEISCH__EINFACHE_SCHREIBWEISE)));
      changeLineSpacing(hebrewField);
      
      components.add(hebrewField);

      vertical.add(hebrewField);

      return vertical;
   }
   
   private void changeLineSpacing(JTextPane pane) {
      SimpleAttributeSet set = new SimpleAttributeSet(pane.getParagraphAttributes());
      StyleConstants.setLineSpacing(set, 0.5F);
      pane.setParagraphAttributes(set, true);
  }

   private Component initSwedish()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setBackground(this.color);
      vertical.setOpaque(true);

      swedishField = new JTextArea();
      swedishField.setWrapStyleWord(true);
      swedishField.setLineWrap(true);
      swedishField.setDocument(new SwedishDocument(true));
      swedishField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      swedishField.setFont(ApplicationFonts.getGermanFont(20F));
      swedishField
            .setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 30,
                  (heightTotal - heightBorderTitel)));
      swedishField.setMaximumSize(
            new Dimension(this.widthTotal, (heightTotal - heightBorderTitel)));
      swedishField.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.SVENSKA)));

      components.add(swedishField);

      vertical.add(swedishField);

      return vertical;
   }
   
   private Component initGerman()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setBackground(this.color);
      vertical.setOpaque(true);

      germanField = new JTextArea();
      germanField.setWrapStyleWord(true);
      germanField.setLineWrap(true);
      germanField.setDocument(new GermanDocument(true));
      germanField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      germanField.setFont(ApplicationFonts.getGermanFont(20F));
      germanField
            .setMinimumSize(new Dimension(Settings.getKeyboardWidth() - 30,
                  (heightTotal - heightBorderTitel)));
      germanField.setMaximumSize(
            new Dimension(this.widthTotal, (heightTotal - heightBorderTitel)));
      germanField.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.GERMAN)));

      components.add(germanField);

      vertical.add(germanField);

      return vertical;
   }

   private void toggleNext()
   {
      switch (selection)
      {
      case SIMPLE:
         selection = Selection.PLENE_DEFEKTIV;
         layout.show(cards, Selection.PLENE_DEFEKTIV.name());
         this.hebrewField.setText("");
         // is already hebrew
         break;
      case PLENE_DEFEKTIV:
         selection = Selection.SWEDISH;
         layout.show(cards, Selection.SWEDISH.name());
         this.pleneField.setText("");
         this.defektivField.setText("");
         if(editorView != null) editorView.remakeAllBoxes(LLType.SWEDISH);
         break;
      case SWEDISH:
         selection = Selection.GERMAN;
         layout.show(cards, Selection.GERMAN.name());
         this.swedishField.setText("");
         if(editorView != null) editorView.remakeAllBoxes(LLType.GERMAN);
         break;
      case GERMAN:
         selection = Selection.SIMPLE;
         layout.show(cards, Selection.SIMPLE.name());
         this.germanField.setText("");
         if(editorView != null) editorView.remakeAllBoxes(LLType.HEBREW);
         break;
      }

      Settings.setLanguageInput(selection);
      keyboard.setKeyboardNoTextfield(selection);
   }
   

   public void setLayoutNoKeyboard(Selection newselection)
   {
      if(this.selection == newselection)
      {
         return;
      }
      this.selection = newselection;
      layout.show(cards, selection.name());
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

   public String getSwedishFieldText()
   {
      return swedishField.getText();
   }
   
   public String getGermanFieldText()
   {
      return germanField.getText();
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

   public void setSwedishFieldText(String swedishText)
   {
      swedishField.setText(swedishText);
   }
   
   public void setGermanFieldText(String germanText)
   {
      germanField.setText(germanText);
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
      case SWEDISH:
         return !swedishField.getText().strip().isBlank();
      case GERMAN:
         return !germanField.getText().strip().isBlank();
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
      if (swedishField != null)
      {
         swedishField.setBackground(color);
      }
      if (germanField != null)
      {
         germanField.setBackground(color);
      }
   }

   @Override
   public void setEnabled(boolean enabled)
   {
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
      if (swedishField != null)
      {
         this.swedishField.setEditable(editable);
      }
      if (germanField != null)
      {
         this.germanField.setEditable(editable);
      }
      setEnabled(editable);
   }

   public Collection<? extends JTextComponent> getTextComponents()
   {
      return components;
   }

   public Selection getSelection()
   {
      return selection;
   }
   
   public LLType getLLType()
   {
      switch(selection)
      {
      case PLENE_DEFEKTIV:
      case SIMPLE:
         return LLType.HEBREW;
      case SWEDISH:
         return LLType.SWEDISH;
      case GERMAN:
         return LLType.GERMAN;
      }
      return LLType.UNKOWN;
   }

   public void setSelection(Selection selection)
   {
      this.selection = selection;
   }

}
