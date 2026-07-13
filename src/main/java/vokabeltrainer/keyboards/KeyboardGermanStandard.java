package vokabeltrainer.keyboards;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.Settings;
import vokabeltrainer.common.colors.InputColors;
import vokabeltrainer.editing.GermanLetter;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class KeyboardGermanStandard extends JPanel
{
   private static final long serialVersionUID = 2289311868636133564L;

   private final int BUTTON_SIZE = 36;
   private List<JTextComponent> components;

   private Map<GermanLetter, Component> dataButtons;

   private JPanel keyboardPanel;

   public KeyboardGermanStandard(JTextComponent textfield,
         List<JTextComponent> arrayList, int textFieldHeight)
   {
      dataButtons = new HashMap<>();

      if (textfield != null)
      {
         textfield.setFont(ApplicationFonts.germanFont.deriveFont(30F));
         textfield.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         textfield.setMinimumSize(
               new Dimension(Settings.getKeyboardWidth(), textFieldHeight));
         textfield.setMaximumSize(
               new Dimension(Settings.getKeyboardWidth(), textFieldHeight));
         arrayList.add(textfield);
      }

      this.components = arrayList;

      setLayout(new TotemLayout(this, 4));
      this.setOpaque(false);

      if (textfield != null)
      {
         add(textfield);
         setFocusTraversalPolicy(new OneFocusTraversalPolicy(textfield));
         this.components.add(textfield);
         textfield.grabFocus();
      }

      keyboardPanel = new JPanel();
      keyboardPanel.setOpaque(false);
      keyboardPanel.setLayout(new TotemLayout(keyboardPanel));

      dataButtons.put(GermanLetter.A, makeButton(GermanLetter.A));
      dataButtons.put(GermanLetter.B, makeButton(GermanLetter.B));
      dataButtons.put(GermanLetter.C, makeButton(GermanLetter.C));
      dataButtons.put(GermanLetter.D, makeButton(GermanLetter.D));
      dataButtons.put(GermanLetter.E, makeButton(GermanLetter.E));
      dataButtons.put(GermanLetter.F, makeButton(GermanLetter.F));
      dataButtons.put(GermanLetter.G, makeButton(GermanLetter.G));
      dataButtons.put(GermanLetter.H, makeButton(GermanLetter.H));
      dataButtons.put(GermanLetter.I, makeButton(GermanLetter.I));
      dataButtons.put(GermanLetter.J, makeButton(GermanLetter.J));
      dataButtons.put(GermanLetter.K, makeButton(GermanLetter.K));
      dataButtons.put(GermanLetter.L, makeButton(GermanLetter.L));
      dataButtons.put(GermanLetter.M, makeButton(GermanLetter.M));
      dataButtons.put(GermanLetter.N, makeButton(GermanLetter.N));
      dataButtons.put(GermanLetter.O, makeButton(GermanLetter.O));
      dataButtons.put(GermanLetter.P, makeButton(GermanLetter.P));
      dataButtons.put(GermanLetter.Q, makeButton(GermanLetter.Q));
      dataButtons.put(GermanLetter.R, makeButton(GermanLetter.R));
      dataButtons.put(GermanLetter.S, makeButton(GermanLetter.S));
      dataButtons.put(GermanLetter.T, makeButton(GermanLetter.T));
      dataButtons.put(GermanLetter.U, makeButton(GermanLetter.U));
      dataButtons.put(GermanLetter.V, makeButton(GermanLetter.V));
      dataButtons.put(GermanLetter.W, makeButton(GermanLetter.W));
      dataButtons.put(GermanLetter.X, makeButton(GermanLetter.X));
      dataButtons.put(GermanLetter.Y, makeButton(GermanLetter.Y));
      dataButtons.put(GermanLetter.Z, makeButton(GermanLetter.Z));
      dataButtons.put(GermanLetter.UE, makeButton(GermanLetter.UE));
      dataButtons.put(GermanLetter.AE, makeButton(GermanLetter.AE));
      dataButtons.put(GermanLetter.OE, makeButton(GermanLetter.OE));

      dataButtons.put(GermanLetter.a, makeButton(GermanLetter.a));
      dataButtons.put(GermanLetter.b, makeButton(GermanLetter.b));
      dataButtons.put(GermanLetter.c, makeButton(GermanLetter.c));
      dataButtons.put(GermanLetter.d, makeButton(GermanLetter.d));
      dataButtons.put(GermanLetter.e, makeButton(GermanLetter.e));
      dataButtons.put(GermanLetter.f, makeButton(GermanLetter.f));
      dataButtons.put(GermanLetter.g, makeButton(GermanLetter.g));
      dataButtons.put(GermanLetter.h, makeButton(GermanLetter.h));
      dataButtons.put(GermanLetter.i, makeButton(GermanLetter.i));
      dataButtons.put(GermanLetter.j, makeButton(GermanLetter.j));
      dataButtons.put(GermanLetter.k, makeButton(GermanLetter.k));
      dataButtons.put(GermanLetter.l, makeButton(GermanLetter.l));
      dataButtons.put(GermanLetter.m, makeButton(GermanLetter.m));
      dataButtons.put(GermanLetter.n, makeButton(GermanLetter.n));
      dataButtons.put(GermanLetter.o, makeButton(GermanLetter.o));
      dataButtons.put(GermanLetter.p, makeButton(GermanLetter.p));
      dataButtons.put(GermanLetter.q, makeButton(GermanLetter.q));
      dataButtons.put(GermanLetter.r, makeButton(GermanLetter.r));
      dataButtons.put(GermanLetter.s, makeButton(GermanLetter.s));
      dataButtons.put(GermanLetter.t, makeButton(GermanLetter.t));
      dataButtons.put(GermanLetter.u, makeButton(GermanLetter.u));
      dataButtons.put(GermanLetter.v, makeButton(GermanLetter.v));
      dataButtons.put(GermanLetter.w, makeButton(GermanLetter.w));
      dataButtons.put(GermanLetter.x, makeButton(GermanLetter.x));
      dataButtons.put(GermanLetter.y, makeButton(GermanLetter.y));
      dataButtons.put(GermanLetter.z, makeButton(GermanLetter.z));
      dataButtons.put(GermanLetter.ue, makeButton(GermanLetter.ue));
      dataButtons.put(GermanLetter.ae, makeButton(GermanLetter.ae));
      dataButtons.put(GermanLetter.oe, makeButton(GermanLetter.oe));
      makeRegularKeyboard();

      add(keyboardPanel);
   }

   private Component makeButton(GermanLetter letter)
   {
      DataButtonGerman dataButton;
      dataButton = new DataButtonGerman(letter.getUnicode(),
            letter.getUnicode());
      dataButton.setForeground(InputColors.getTextForeground());
      dataButton.setMargin(new Insets(3, -5, 0, -5));
      dataButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));
      dataButton.setMaximumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));

      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);
      buttonPanel.setLayout(new TotemLayout(buttonPanel));
      buttonPanel.add(dataButton);

      dataButton.addMouseListener(new KeyboardListener());

      return buttonPanel;
   }

   private Component makeSpaceButton()
   {
      DataButtonGerman jButton = new DataButtonGerman("Leerzeichen",
            GermanLetter.SPACE.getUnicode());
      jButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE + 10));
      jButton.setMaximumSize(new Dimension(9 * BUTTON_SIZE, BUTTON_SIZE + 10));
      jButton.addMouseListener(new KeyboardListener());
      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);
      buttonPanel.setLayout(new TotemLayout(buttonPanel));
      buttonPanel.add(jButton);
      dataButtons.put(GermanLetter.SPACE, (Component) buttonPanel);
      return jButton;
   }

   private class KeyboardListener implements MouseListener
   {

      public void mouseClicked(MouseEvent e)
      {

      }

      public void mousePressed(MouseEvent e)
      {
         DataButton jButton = (DataButton) e.getComponent();
         String caption = jButton.getData();

         JTextComponent focusElement = findFocusElement();
         if (focusElement != null)
         {
            int position = focusElement.getCaretPosition();
            String text = focusElement.getText();
            String before = text.substring(0, position);
            String after = text.substring(position);
            focusElement.setText(before + caption + after);
            focusElement.requestFocus();
         }
      }

      private JTextComponent findFocusElement()
      {
         for (JTextComponent component : components)
         {
            if (component.isFocusOwner())
            {
               return component;
            }
         }
         return null;
      }

      public void mouseReleased(MouseEvent e)
      {

      }

      public void mouseEntered(MouseEvent e)
      {

      }

      public void mouseExited(MouseEvent e)
      {

      }

   }

   public Component makeTextfieldWithRegularKeyboard()
   {
      makeRegularKeyboard();
      return this;
   }

   public Component makeRegularKeyboard()
   {
      keyboardPanel.removeAll();

      JPanel row1 = new JPanel();
      row1.setOpaque(false);
      row1.setLayout(new TrainLayout(row1, 4));

      JPanel halfSizedFiller1 = new JPanel();
      halfSizedFiller1.setMinimumSize(new Dimension(5, BUTTON_SIZE));
      halfSizedFiller1.setMaximumSize(new Dimension(5, BUTTON_SIZE));
      halfSizedFiller1.setOpaque(false);
      halfSizedFiller1.setBackground(ApplicationColors.getTransparent());
      row1.add(halfSizedFiller1);

      row1.add(dataButtons.get(GermanLetter.Q));
      row1.add(dataButtons.get(GermanLetter.W));
      row1.add(dataButtons.get(GermanLetter.E));
      row1.add(dataButtons.get(GermanLetter.R));
      row1.add(dataButtons.get(GermanLetter.T));
      row1.add(dataButtons.get(GermanLetter.Y));
      row1.add(dataButtons.get(GermanLetter.U));
      row1.add(dataButtons.get(GermanLetter.I));
      row1.add(dataButtons.get(GermanLetter.O));
      row1.add(dataButtons.get(GermanLetter.P));
      row1.add(dataButtons.get(GermanLetter.UE));

      JPanel row2 = new JPanel();
      row2.setOpaque(false);
      row2.setLayout(new TrainLayout(row2, 4));

      JPanel tinyFiller2 = new JPanel();
      tinyFiller2.setMinimumSize(new Dimension(18, BUTTON_SIZE));
      tinyFiller2.setMaximumSize(new Dimension(18, BUTTON_SIZE));
      tinyFiller2.setOpaque(false);
      tinyFiller2.setBackground(ApplicationColors.getTransparent());
      row2.add(tinyFiller2);

      row2.add(dataButtons.get(GermanLetter.A));
      row2.add(dataButtons.get(GermanLetter.S));
      row2.add(dataButtons.get(GermanLetter.D));
      row2.add(dataButtons.get(GermanLetter.F));
      row2.add(dataButtons.get(GermanLetter.G));
      row2.add(dataButtons.get(GermanLetter.H));
      row2.add(dataButtons.get(GermanLetter.J));
      row2.add(dataButtons.get(GermanLetter.K));
      row2.add(dataButtons.get(GermanLetter.L));
      row2.add(dataButtons.get(GermanLetter.OE));
      row2.add(dataButtons.get(GermanLetter.AE));

      JPanel row3 = new JPanel();
      row3.setOpaque(false);
      row3.setLayout(new TrainLayout(row3, 4));

      JPanel halfSizedFiller3 = new JPanel();
      halfSizedFiller3.setMinimumSize(new Dimension(31, BUTTON_SIZE));
      halfSizedFiller3.setMaximumSize(new Dimension(31, BUTTON_SIZE));
      halfSizedFiller3.setOpaque(false);
      halfSizedFiller3.setBackground(ApplicationColors.getTransparent());
      row3.add(halfSizedFiller3);

      row3.add(dataButtons.get(GermanLetter.Z));
      row3.add(dataButtons.get(GermanLetter.X));
      row3.add(dataButtons.get(GermanLetter.C));
      row3.add(dataButtons.get(GermanLetter.V));
      row3.add(dataButtons.get(GermanLetter.B));
      row3.add(dataButtons.get(GermanLetter.N));
      row3.add(dataButtons.get(GermanLetter.M));

      JPanel bigFiller1 = new JPanel();
      bigFiller1.setOpaque(false);
      bigFiller1.setBackground(ApplicationColors.getTransparent());
      bigFiller1.setMaximumSize(new Dimension(300, 32));

      JPanel row4 = new JPanel();
      row4.setOpaque(false);
      row4.setLayout(new TrainLayout(row4, 4));

      JPanel halfSizedFiller1b = new JPanel();
      halfSizedFiller1b.setMinimumSize(new Dimension(5, BUTTON_SIZE));
      halfSizedFiller1b.setMaximumSize(new Dimension(5, BUTTON_SIZE));
      halfSizedFiller1b.setOpaque(false);
      halfSizedFiller1b.setBackground(ApplicationColors.getTransparent());
      row4.add(halfSizedFiller1b);

      row4.add(dataButtons.get(GermanLetter.q));
      row4.add(dataButtons.get(GermanLetter.w));
      row4.add(dataButtons.get(GermanLetter.e));
      row4.add(dataButtons.get(GermanLetter.r));
      row4.add(dataButtons.get(GermanLetter.t));
      row4.add(dataButtons.get(GermanLetter.y));
      row4.add(dataButtons.get(GermanLetter.u));
      row4.add(dataButtons.get(GermanLetter.i));
      row4.add(dataButtons.get(GermanLetter.o));
      row4.add(dataButtons.get(GermanLetter.p));
      row4.add(dataButtons.get(GermanLetter.ue));

      JPanel row5 = new JPanel();
      row5.setOpaque(false);
      row5.setLayout(new TrainLayout(row5, 4));

      JPanel tinyFiller2b = new JPanel();
      tinyFiller2b.setMinimumSize(new Dimension(18, BUTTON_SIZE));
      tinyFiller2b.setMaximumSize(new Dimension(18, BUTTON_SIZE));
      tinyFiller2b.setOpaque(false);
      tinyFiller2b.setBackground(ApplicationColors.getTransparent());
      row5.add(tinyFiller2b);

      row5.add(dataButtons.get(GermanLetter.a));
      row5.add(dataButtons.get(GermanLetter.s));
      row5.add(dataButtons.get(GermanLetter.d));
      row5.add(dataButtons.get(GermanLetter.f));
      row5.add(dataButtons.get(GermanLetter.g));
      row5.add(dataButtons.get(GermanLetter.h));
      row5.add(dataButtons.get(GermanLetter.j));
      row5.add(dataButtons.get(GermanLetter.k));
      row5.add(dataButtons.get(GermanLetter.l));
      row5.add(dataButtons.get(GermanLetter.oe));
      row5.add(dataButtons.get(GermanLetter.ae));

      JPanel row6 = new JPanel();
      row6.setOpaque(false);
      row6.setLayout(new TrainLayout(row6, 4));

      JPanel halfSizedFiller3b = new JPanel();
      halfSizedFiller3b.setMinimumSize(new Dimension(31, BUTTON_SIZE));
      halfSizedFiller3b.setMaximumSize(new Dimension(31, BUTTON_SIZE));
      halfSizedFiller3b.setOpaque(false);
      halfSizedFiller3b.setBackground(ApplicationColors.getTransparent());
      row6.add(halfSizedFiller3b);

      row6.add(dataButtons.get(GermanLetter.z));
      row6.add(dataButtons.get(GermanLetter.x));
      row6.add(dataButtons.get(GermanLetter.c));
      row6.add(dataButtons.get(GermanLetter.v));
      row6.add(dataButtons.get(GermanLetter.b));
      row6.add(dataButtons.get(GermanLetter.n));
      row6.add(dataButtons.get(GermanLetter.m));

      JPanel bigFiller2 = new JPanel();
      bigFiller2.setOpaque(false);
      bigFiller2.setBackground(ApplicationColors.getTransparent());
      bigFiller2.setMaximumSize(new Dimension(300, 32));

      keyboardPanel.add(row1);
      keyboardPanel.add(row2);
      keyboardPanel.add(row3);
      keyboardPanel.add(bigFiller1);
      keyboardPanel.add(makeSpaceButton());
      keyboardPanel.add(bigFiller2);
      keyboardPanel.add(row4);
      keyboardPanel.add(row5);
      keyboardPanel.add(row6);

      JPanel bigFiller3 = new JPanel();
      bigFiller3.setOpaque(false);
      bigFiller3.setBackground(ApplicationColors.getTransparent());
      bigFiller3.setMaximumSize(new Dimension(300, 32));

      keyboardPanel.add(bigFiller3);

      keyboardPanel.validate();
      keyboardPanel.repaint();

      return keyboardPanel;
   }
}