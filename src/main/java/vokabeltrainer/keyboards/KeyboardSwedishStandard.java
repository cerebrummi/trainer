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

import vokabeltrainer.common.main.App;
import vokabeltrainer.editing.SwedishLetter;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class KeyboardSwedishStandard extends JPanel
{
   private static final long serialVersionUID = 2289311868636133564L;

   private final int BUTTON_SIZE = 36;
   private List<JTextComponent> components;

   private Map<SwedishLetter, Component> dataButtons;

   private JPanel keyboardPanel;

   public KeyboardSwedishStandard(App app, JTextComponent textfield,
         List<JTextComponent> arrayList, int textFieldHeight)
   {
      dataButtons = new HashMap<>();

      if (textfield != null)
      {
         textfield.setFont(app.appFonts.germanFont.deriveFont(30F));
         textfield.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         textfield.setMinimumSize(
               new Dimension(app.settings.getKeyboardWidth(), textFieldHeight));
         textfield.setMaximumSize(
               new Dimension(app.settings.getKeyboardWidth(), textFieldHeight));
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

      dataButtons.put(SwedishLetter.A, makeButton(app, SwedishLetter.A));
      dataButtons.put(SwedishLetter.B, makeButton(app, SwedishLetter.B));
      dataButtons.put(SwedishLetter.C, makeButton(app, SwedishLetter.C));
      dataButtons.put(SwedishLetter.D, makeButton(app, SwedishLetter.D));
      dataButtons.put(SwedishLetter.E, makeButton(app, SwedishLetter.E));
      dataButtons.put(SwedishLetter.F, makeButton(app, SwedishLetter.F));
      dataButtons.put(SwedishLetter.G, makeButton(app, SwedishLetter.G));
      dataButtons.put(SwedishLetter.H, makeButton(app, SwedishLetter.H));
      dataButtons.put(SwedishLetter.I, makeButton(app, SwedishLetter.I));
      dataButtons.put(SwedishLetter.J, makeButton(app, SwedishLetter.J));
      dataButtons.put(SwedishLetter.K, makeButton(app, SwedishLetter.K));
      dataButtons.put(SwedishLetter.L, makeButton(app, SwedishLetter.L));
      dataButtons.put(SwedishLetter.M, makeButton(app, SwedishLetter.M));
      dataButtons.put(SwedishLetter.N, makeButton(app, SwedishLetter.N));
      dataButtons.put(SwedishLetter.O, makeButton(app, SwedishLetter.O));
      dataButtons.put(SwedishLetter.P, makeButton(app, SwedishLetter.P));
      dataButtons.put(SwedishLetter.Q, makeButton(app, SwedishLetter.Q));
      dataButtons.put(SwedishLetter.R, makeButton(app, SwedishLetter.R));
      dataButtons.put(SwedishLetter.S, makeButton(app, SwedishLetter.S));
      dataButtons.put(SwedishLetter.T, makeButton(app, SwedishLetter.T));
      dataButtons.put(SwedishLetter.U, makeButton(app, SwedishLetter.U));
      dataButtons.put(SwedishLetter.V, makeButton(app, SwedishLetter.V));
      dataButtons.put(SwedishLetter.W, makeButton(app, SwedishLetter.W));
      dataButtons.put(SwedishLetter.X, makeButton(app, SwedishLetter.X));
      dataButtons.put(SwedishLetter.Y, makeButton(app, SwedishLetter.Y));
      dataButtons.put(SwedishLetter.Z, makeButton(app, SwedishLetter.Z));
      dataButtons.put(SwedishLetter.ARING, makeButton(app, SwedishLetter.ARING));
      dataButtons.put(SwedishLetter.AE, makeButton(app, SwedishLetter.AE));
      dataButtons.put(SwedishLetter.OE, makeButton(app, SwedishLetter.OE));

      dataButtons.put(SwedishLetter.a, makeButton(app, SwedishLetter.a));
      dataButtons.put(SwedishLetter.b, makeButton(app, SwedishLetter.b));
      dataButtons.put(SwedishLetter.c, makeButton(app, SwedishLetter.c));
      dataButtons.put(SwedishLetter.d, makeButton(app, SwedishLetter.d));
      dataButtons.put(SwedishLetter.e, makeButton(app, SwedishLetter.e));
      dataButtons.put(SwedishLetter.f, makeButton(app, SwedishLetter.f));
      dataButtons.put(SwedishLetter.g, makeButton(app, SwedishLetter.g));
      dataButtons.put(SwedishLetter.h, makeButton(app, SwedishLetter.h));
      dataButtons.put(SwedishLetter.i, makeButton(app, SwedishLetter.i));
      dataButtons.put(SwedishLetter.j, makeButton(app, SwedishLetter.j));
      dataButtons.put(SwedishLetter.k, makeButton(app, SwedishLetter.k));
      dataButtons.put(SwedishLetter.l, makeButton(app, SwedishLetter.l));
      dataButtons.put(SwedishLetter.m, makeButton(app, SwedishLetter.m));
      dataButtons.put(SwedishLetter.n, makeButton(app, SwedishLetter.n));
      dataButtons.put(SwedishLetter.o, makeButton(app, SwedishLetter.o));
      dataButtons.put(SwedishLetter.p, makeButton(app, SwedishLetter.p));
      dataButtons.put(SwedishLetter.q, makeButton(app, SwedishLetter.q));
      dataButtons.put(SwedishLetter.r, makeButton(app, SwedishLetter.r));
      dataButtons.put(SwedishLetter.s, makeButton(app, SwedishLetter.s));
      dataButtons.put(SwedishLetter.t, makeButton(app, SwedishLetter.t));
      dataButtons.put(SwedishLetter.u, makeButton(app, SwedishLetter.u));
      dataButtons.put(SwedishLetter.v, makeButton(app, SwedishLetter.v));
      dataButtons.put(SwedishLetter.w, makeButton(app, SwedishLetter.w));
      dataButtons.put(SwedishLetter.x, makeButton(app, SwedishLetter.x));
      dataButtons.put(SwedishLetter.y, makeButton(app, SwedishLetter.y));
      dataButtons.put(SwedishLetter.z, makeButton(app, SwedishLetter.z));
      dataButtons.put(SwedishLetter.aring, makeButton(app, SwedishLetter.aring));
      dataButtons.put(SwedishLetter.ae, makeButton(app, SwedishLetter.ae));
      dataButtons.put(SwedishLetter.oe, makeButton(app, SwedishLetter.oe));
      makeRegularKeyboard(app);

      add(keyboardPanel);
   }

   private Component makeButton(App app, SwedishLetter letter)
   {
      DataButtonSwedish dataButton;
      dataButton = new DataButtonSwedish(app, letter.getUnicode(),
            letter.getUnicode());
      dataButton.setForeground(app.appColors.input.getTextForeground());
      dataButton.setMargin(new Insets(3, -5, 0, -5));
      dataButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));
      dataButton.setMaximumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));
      dataButton.setToolTipText(letter.getPronunciation());

      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);
      buttonPanel.setBackground(app.appColors.getTransparent());
      buttonPanel.setLayout(new TotemLayout(buttonPanel));
      buttonPanel.add(dataButton);

      dataButton.addMouseListener(new KeyboardListener());

      return buttonPanel;
   }

   private Component makeSpaceButton(App app)
   {
      DataButtonSwedish jButton = new DataButtonSwedish(app,
            SwedishLetter.SPACE.getPronunciation(),
            SwedishLetter.SPACE.getUnicode());
      jButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE + 10));
      jButton.setMaximumSize(new Dimension(9 * BUTTON_SIZE, BUTTON_SIZE + 10));
      jButton.addMouseListener(new KeyboardListener());
      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);
      buttonPanel.setLayout(new TotemLayout(buttonPanel));
      buttonPanel.add(jButton);
      dataButtons.put(SwedishLetter.SPACE, (Component) buttonPanel);
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

   public Component makeTextfieldWithRegularKeyboard(App app)
   {
      makeRegularKeyboard(app);
      return this;
   }

   public Component makeRegularKeyboard(App app)
   {
      keyboardPanel.removeAll();

      JPanel row1 = new JPanel();
      row1.setOpaque(false);
      row1.setLayout(new TrainLayout(row1, 4));

      JPanel halfSizedFiller1 = new JPanel();
      halfSizedFiller1.setMinimumSize(new Dimension(5, BUTTON_SIZE));
      halfSizedFiller1.setMaximumSize(new Dimension(5, BUTTON_SIZE));
      halfSizedFiller1.setOpaque(false);
      halfSizedFiller1.setBackground(app.appColors.getTransparent());
      row1.add(halfSizedFiller1);

      row1.add(dataButtons.get(SwedishLetter.Q));
      row1.add(dataButtons.get(SwedishLetter.W));
      row1.add(dataButtons.get(SwedishLetter.E));
      row1.add(dataButtons.get(SwedishLetter.R));
      row1.add(dataButtons.get(SwedishLetter.T));
      row1.add(dataButtons.get(SwedishLetter.Y));
      row1.add(dataButtons.get(SwedishLetter.U));
      row1.add(dataButtons.get(SwedishLetter.I));
      row1.add(dataButtons.get(SwedishLetter.O));
      row1.add(dataButtons.get(SwedishLetter.P));
      row1.add(dataButtons.get(SwedishLetter.ARING));

      JPanel row2 = new JPanel();
      row2.setOpaque(false);
      row2.setLayout(new TrainLayout(row2, 4));

      JPanel tinyFiller2 = new JPanel();
      tinyFiller2.setMinimumSize(new Dimension(18, BUTTON_SIZE));
      tinyFiller2.setMaximumSize(new Dimension(18, BUTTON_SIZE));
      tinyFiller2.setOpaque(false);
      tinyFiller2.setBackground(app.appColors.getTransparent());
      row2.add(tinyFiller2);

      row2.add(dataButtons.get(SwedishLetter.A));
      row2.add(dataButtons.get(SwedishLetter.S));
      row2.add(dataButtons.get(SwedishLetter.D));
      row2.add(dataButtons.get(SwedishLetter.F));
      row2.add(dataButtons.get(SwedishLetter.G));
      row2.add(dataButtons.get(SwedishLetter.H));
      row2.add(dataButtons.get(SwedishLetter.J));
      row2.add(dataButtons.get(SwedishLetter.K));
      row2.add(dataButtons.get(SwedishLetter.L));
      row2.add(dataButtons.get(SwedishLetter.OE));
      row2.add(dataButtons.get(SwedishLetter.AE));

      JPanel row3 = new JPanel();
      row3.setOpaque(false);
      row3.setLayout(new TrainLayout(row3, 4));

      JPanel halfSizedFiller3 = new JPanel();
      halfSizedFiller3.setMinimumSize(new Dimension(31, BUTTON_SIZE));
      halfSizedFiller3.setMaximumSize(new Dimension(31, BUTTON_SIZE));
      halfSizedFiller3.setOpaque(false);
      halfSizedFiller3.setBackground(app.appColors.getTransparent());
      row3.add(halfSizedFiller3);

      row3.add(dataButtons.get(SwedishLetter.Z));
      row3.add(dataButtons.get(SwedishLetter.X));
      row3.add(dataButtons.get(SwedishLetter.C));
      row3.add(dataButtons.get(SwedishLetter.V));
      row3.add(dataButtons.get(SwedishLetter.B));
      row3.add(dataButtons.get(SwedishLetter.N));
      row3.add(dataButtons.get(SwedishLetter.M));

      JPanel bigFiller1 = new JPanel();
      bigFiller1.setOpaque(false);
      bigFiller1.setBackground(app.appColors.getTransparent());
      bigFiller1.setMaximumSize(new Dimension(300, 32));

      JPanel row4 = new JPanel();
      row4.setOpaque(false);
      row4.setLayout(new TrainLayout(row4, 4));

      JPanel halfSizedFiller1b = new JPanel();
      halfSizedFiller1b.setMinimumSize(new Dimension(5, BUTTON_SIZE));
      halfSizedFiller1b.setMaximumSize(new Dimension(5, BUTTON_SIZE));
      halfSizedFiller1b.setOpaque(false);
      halfSizedFiller1b.setBackground(app.appColors.getTransparent());
      row4.add(halfSizedFiller1b);

      row4.add(dataButtons.get(SwedishLetter.q));
      row4.add(dataButtons.get(SwedishLetter.w));
      row4.add(dataButtons.get(SwedishLetter.e));
      row4.add(dataButtons.get(SwedishLetter.r));
      row4.add(dataButtons.get(SwedishLetter.t));
      row4.add(dataButtons.get(SwedishLetter.y));
      row4.add(dataButtons.get(SwedishLetter.u));
      row4.add(dataButtons.get(SwedishLetter.i));
      row4.add(dataButtons.get(SwedishLetter.o));
      row4.add(dataButtons.get(SwedishLetter.p));
      row4.add(dataButtons.get(SwedishLetter.aring));

      JPanel row5 = new JPanel();
      row5.setOpaque(false);
      row5.setLayout(new TrainLayout(row5, 4));

      JPanel tinyFiller2b = new JPanel();
      tinyFiller2b.setMinimumSize(new Dimension(18, BUTTON_SIZE));
      tinyFiller2b.setMaximumSize(new Dimension(18, BUTTON_SIZE));
      tinyFiller2b.setOpaque(false);
      tinyFiller2b.setBackground(app.appColors.getTransparent());
      row5.add(tinyFiller2b);

      row5.add(dataButtons.get(SwedishLetter.a));
      row5.add(dataButtons.get(SwedishLetter.s));
      row5.add(dataButtons.get(SwedishLetter.d));
      row5.add(dataButtons.get(SwedishLetter.f));
      row5.add(dataButtons.get(SwedishLetter.g));
      row5.add(dataButtons.get(SwedishLetter.h));
      row5.add(dataButtons.get(SwedishLetter.j));
      row5.add(dataButtons.get(SwedishLetter.k));
      row5.add(dataButtons.get(SwedishLetter.l));
      row5.add(dataButtons.get(SwedishLetter.oe));
      row5.add(dataButtons.get(SwedishLetter.ae));

      JPanel row6 = new JPanel();
      row6.setOpaque(false);
      row6.setLayout(new TrainLayout(row6, 4));

      JPanel halfSizedFiller3b = new JPanel();
      halfSizedFiller3b.setMinimumSize(new Dimension(31, BUTTON_SIZE));
      halfSizedFiller3b.setMaximumSize(new Dimension(31, BUTTON_SIZE));
      halfSizedFiller3b.setOpaque(false);
      halfSizedFiller3b.setBackground(app.appColors.getTransparent());
      row6.add(halfSizedFiller3b);

      row6.add(dataButtons.get(SwedishLetter.z));
      row6.add(dataButtons.get(SwedishLetter.x));
      row6.add(dataButtons.get(SwedishLetter.c));
      row6.add(dataButtons.get(SwedishLetter.v));
      row6.add(dataButtons.get(SwedishLetter.b));
      row6.add(dataButtons.get(SwedishLetter.n));
      row6.add(dataButtons.get(SwedishLetter.m));

      JPanel bigFiller2 = new JPanel();
      bigFiller2.setOpaque(false);
      bigFiller2.setBackground(app.appColors.getTransparent());
      bigFiller2.setMaximumSize(new Dimension(300, 32));

      keyboardPanel.add(row1);
      keyboardPanel.add(row2);
      keyboardPanel.add(row3);
      keyboardPanel.add(bigFiller1);
      keyboardPanel.add(makeSpaceButton(app));
      keyboardPanel.add(bigFiller2);
      keyboardPanel.add(row4);
      keyboardPanel.add(row5);
      keyboardPanel.add(row6);

      JPanel bigFiller3 = new JPanel();
      bigFiller3.setOpaque(false);
      bigFiller3.setBackground(app.appColors.getTransparent());
      bigFiller3.setMaximumSize(new Dimension(300, 32));

      keyboardPanel.add(bigFiller3);

      keyboardPanel.validate();
      keyboardPanel.repaint();

      return keyboardPanel;
   }
}