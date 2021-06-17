package vokabeltrainer.keyboards;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

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

public class KeyboardHebrewSimple extends JPanel
{
   private static final long serialVersionUID = -7532952398298332087L;

   private final int BUTTON_SIZE = 42;
   private List<JTextComponent> components;

   private Scale scale;

   public KeyboardHebrewSimple(JTextComponent textfield,
         List<JTextComponent> arrayList, int textFieldHeight)
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
     
      row1.add(makeButton(NikudLetter.CHET));
      row1.add(makeButton(NikudLetter.SSAIN));
      row1.add(makeButton(NikudLetter.WAW));
      row1.add(makeButton(NikudLetter.HAEI));
      row1.add(makeButton(NikudLetter.DALET));
      row1.add(makeButton(NikudLetter.GIMEL));
      row1.add(makeButton(NikudLetter.WET));
//      row1.add(makeButton(NikudLetter.BET));
      row1.add(makeButton(NikudLetter.ALEF));

      JPanel row2 = new JPanel();
      row2.setOpaque(false);
      row2.setLayout(new TrainLayout(row2, 8));

      row2.add(makeButton(NikudLetter.NUN));
      row2.add(makeButton(NikudLetter.MEMSSOFIT));
      row2.add(makeButton(NikudLetter.MEM));
      row2.add(makeButton(NikudLetter.LAMED));
      row2.add(makeButton(NikudLetter.CHAFSSOFIT));
      row2.add(makeButton(NikudLetter.CHAF));
//      row2.add(makeButton(NikudLetter.KAF));
      row2.add(makeButton(NikudLetter.JOD));
      row2.add(makeButton(NikudLetter.TET));

      JPanel row3 = new JPanel();
      row3.setOpaque(false);
      row3.setLayout(new TrainLayout(row3, 8));

      row3.add(makeButton(NikudLetter.KUF));
      row3.add(makeButton(NikudLetter.ZADISSOFIT));
      row3.add(makeButton(NikudLetter.ZADI));
      row3.add(makeButton(NikudLetter.FAEISSOFIT));
      row3.add(makeButton(NikudLetter.FAEI));
//      row3.add(makeButton(NikudLetter.PAEI));
      row3.add(makeButton(NikudLetter.AIN));
      row3.add(makeButton(NikudLetter.SSAMECH));
      row3.add(makeButton(NikudLetter.NUNSSOFIT));

      JPanel row4 = new JPanel();
      row4.setOpaque(false);
      row4.setLayout(new TrainLayout(row4, 8));

      row4.add(makeButton(NikudLetter.GERSCHAYIM));
      row4.add(makeButton(NikudLetter.GERESCH));
      row4.add(makeButton(NikudLetter.TAW));
      row4.add(makeSpaceButton());
//      row4.add(makeButton(NikudLetter.SSIN));
      row4.add(makeButton(NikudLetter.SCHIN));
      row4.add(makeButton(NikudLetter.RESCH));

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
      jButton.setMaximumSize(new Dimension(4 * BUTTON_SIZE, BUTTON_SIZE + 10));
      jButton.addMouseListener(new KeyboardListener());
      return jButton;
   }

   private Component makeButton(NikudLetter letter)
   {
      DataButton jButton = new DataButton(ApplicationImages.getLetterIconsNikudMap()
            .get(letter).getScaledInstance(scale.getScaleX(), scale.getScaleY(), java.awt.Image.SCALE_SMOOTH),
            letter.getUnicode());
      jButton.setMargin(new Insets(3, -5, 0, -5));
      jButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));
      jButton.setMaximumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));

      JPanel buttonCaption = new JPanel();
      buttonCaption.setOpaque(false);
      buttonCaption.setLayout(new TotemLayout(buttonCaption));
      buttonCaption.add(jButton);
      JLabel captionLabel = new JLabel(" ");

      captionLabel.setFont(Main.getGermanFont(8F));
      captionLabel.setMinimumSize(new Dimension(BUTTON_SIZE + 2, 10));
      captionLabel.setMaximumSize(new Dimension(BUTTON_SIZE + 2, 10));

      buttonCaption.add(captionLabel);

      jButton.addMouseListener(new KeyboardListener());
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
}
