package vokabeltrainer.keyboards;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.scale.Scale;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class KeyboardHebrewNoPunktation extends JPanel
{
   private static final long serialVersionUID = 2289311868636133544L;

   private final int BUTTON_SIZE = 42;
   private List<JTextComponent> components;

   private List<JButton> buttons = new ArrayList<>();
   private List<JLabel> labels = new ArrayList<>();
   
   private Scale scale;

   public KeyboardHebrewNoPunktation(JTextComponent textfield,
         List<JTextComponent> arrayList, int textFieldHeight,
         boolean addTextField)
   {
      scale = new Scale(BUTTON_SIZE);

      if (textfield != null)
      {
         textfield.setFont(Main.getHebrewFont(29F));
         textfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         textfield.setMinimumSize(
               new Dimension(Settings.getKeyboardWidth(), textFieldHeight));
         textfield.setMaximumSize(
               new Dimension(Settings.getKeyboardWidth(), textFieldHeight));
         arrayList.add(textfield);
      }

      this.components = arrayList;

      setLayout(new TotemLayout(this, 10));
      this.setOpaque(false);
      this.setPreferredSize(new Dimension(Settings.getKeyboardWidth(),
            textFieldHeight + 10 + 218));

      if (textfield != null && addTextField)
      {
         add(textfield);
      }

      if (textfield != null)
      {
         setFocusTraversalPolicy(new OneFocusTraversalPolicy(textfield));
         textfield.grabFocus();
      }

      JPanel keyboard = new JPanel();
      keyboard.setOpaque(false);
      keyboard.setLayout(new TotemLayout(keyboard));

      JPanel row1 = new JPanel();
      row1.setOpaque(false);
      row1.setLayout(new TrainLayout(row1, 8));

      row1.add(makeButton(NikudLetter.TET));
      row1.add(makeButton(NikudLetter.CHET));
      row1.add(makeButton(NikudLetter.SSAIN));
      row1.add(makeButton(NikudLetter.WAW));
      row1.add(makeButton(NikudLetter.HAEI));
      row1.add(makeButton(NikudLetter.DALET));
      row1.add(makeButton(NikudLetter.GIMEL));
      row1.add(makeButton(NikudLetter.WET));
      row1.add(makeButton(NikudLetter.ALEF));

      JPanel row2 = new JPanel();
      row2.setOpaque(false);
      row2.setLayout(new TrainLayout(row2, 8));

      row2.add(makeButton(NikudLetter.SSAMECH));
      row2.add(makeButton(NikudLetter.NUNSSOFIT));
      row2.add(makeButton(NikudLetter.NUN));
      row2.add(makeButton(NikudLetter.MEMSSOFIT));
      row2.add(makeButton(NikudLetter.MEM));
      row2.add(makeButton(NikudLetter.LAMED));
      row2.add(makeButton(NikudLetter.CHAFSSOFIT));
      row2.add(makeButton(NikudLetter.CHAF));
      row2.add(makeButton(NikudLetter.JOD));


      JPanel row3 = new JPanel();
      row3.setOpaque(false);
      row3.setLayout(new TrainLayout(row3, 8));

      row3.add(makeButton(NikudLetter.TAW));
      row3.add(makeButton(NikudLetter.SCHIN));
      row3.add(makeButton(NikudLetter.RESCH));
      row3.add(makeButton(NikudLetter.KUF));
      row3.add(makeButton(NikudLetter.ZADISSOFIT));
      row3.add(makeButton(NikudLetter.ZADI));
      row3.add(makeButton(NikudLetter.FAEISSOFIT));
      row3.add(makeButton(NikudLetter.FAEI));
      row3.add(makeButton(NikudLetter.AIN));


      JPanel row4 = new JPanel();
      row4.setOpaque(false);
      row4.setLayout(new TrainLayout(row4, 8));

      row4.add(makeButton(NikudLetter.GERSCHAYIM));
      row4.add(makeSpaceButton());
      row4.add(makeButton(NikudLetter.GERESCH));


      keyboard.add(row1);
      keyboard.add(row2);
      keyboard.add(row3);
      keyboard.add(row4);
      add(keyboard);
   }

   private Component makeSpaceButton()
   {
      DataButton jButton = new DataButton("\u0020", "\u0020");
      jButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE + 10));
      jButton.setMaximumSize(new Dimension(9 * BUTTON_SIZE, BUTTON_SIZE + 10));
      jButton.addMouseListener(new KeyboardListener());
      buttons.add(jButton);
      return jButton;
   }

   private Component makeButton(NikudLetter letter)
   {
      DataButton jButton = new DataButton(
            ApplicationImages.getLetterIconsNikudMap().get(letter).getScaledInstance(
                  scale.getScaleX(), scale.getScaleY(), java.awt.Image.SCALE_SMOOTH),
            letter.getUnicode());
      jButton.setToolTipText(letter.getTranscript());
      jButton.setMargin(new Insets(3, -5, 0, -5));
      jButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));
      jButton.setMaximumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));

      JPanel buttonCaption = new JPanel();
      buttonCaption.setOpaque(false);
      buttonCaption.setLayout(new TotemLayout(buttonCaption));
      buttonCaption.add(jButton);

      JLabel captionLabel = new JLabel(letter.getTranscript());
      captionLabel.setFont(Main.getGermanFont(8F));
      captionLabel.setMinimumSize(new Dimension(BUTTON_SIZE + 2, 10));
      captionLabel.setMaximumSize(new Dimension(BUTTON_SIZE + 2, 10));

      buttonCaption.add(captionLabel);

      jButton.addMouseListener(new KeyboardListener());
      buttons.add(jButton);
      labels.add(captionLabel);
      return buttonCaption;
   }

   class KeyboardListener implements MouseListener
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

   public void setFrozen(boolean frozen)
   {
      if (frozen)
      {
         for (JButton button : buttons)
         {
            button.setEnabled(false);
            button.setVisible(false);
            if (button.getMouseListeners().length > 0)
            {
               button.removeMouseListener(button.getMouseListeners()[0]);
            }
         }
         for (JLabel label : labels)
         {
            label.setVisible(false);
         }
      }
      else
      {
         for (JButton button : buttons)
         {
            button.setEnabled(true);
            button.setVisible(true);
            if (button.getMouseListeners().length == 0)
            {
               button.addMouseListener(new KeyboardListener());
            }
         }
         for (JLabel label : labels)
         {
            label.setVisible(true);
         }
      }
   }
}
