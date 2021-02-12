package vokabeltrainer.keyboards;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.scale.Scale;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class KeyboardHebrewStandard extends JPanel
{
   private static final long serialVersionUID = 2289311868636133564L;

   private final int BUTTON_SIZE = 42;
   private List<JTextComponent> components;

   private Scale scale;

   public KeyboardHebrewStandard(JTextComponent textfield,
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

      setLayout(new TotemLayout(this, 4));
      this.setOpaque(false);

      if (textfield != null)
      {
         add(textfield);
         setFocusTraversalPolicy(new OneFocusTraversalPolicy(textfield));
         textfield.grabFocus();
      }

      JPanel keyboard = new JPanel();
      keyboard.setOpaque(false);
      keyboard.setLayout(new TotemLayout(keyboard));

      JPanel row1 = new JPanel();
      row1.setOpaque(false);
      row1.setLayout(new TrainLayout(row1, 4));

      JPanel halfSizedFiller1 = new JPanel();
      halfSizedFiller1.setMinimumSize(new Dimension(20, BUTTON_SIZE));
      halfSizedFiller1.setMaximumSize(new Dimension(20, BUTTON_SIZE));
      row1.add(halfSizedFiller1);

      row1.add(makeButton(HebrewLetter.SSIN));
      row1.add(makeButton(HebrewLetter.KUF));
      row1.add(makeButton(HebrewLetter.RESCH));
      row1.add(makeButton(HebrewLetter.ALEF));
      row1.add(makeButton(HebrewLetter.TET));
      row1.add(makeButton(HebrewLetter.WAW));
      row1.add(makeButton(HebrewLetter.NUNSSOFIT));
      row1.add(makeButton(HebrewLetter.MEMSSOFIT));
      row1.add(makeButton(HebrewLetter.FAEI));
      row1.add(makeButton(HebrewLetter.PAEI));

      JPanel row2 = new JPanel();
      row2.setOpaque(false);
      row2.setLayout(new TrainLayout(row2, 4));

      JPanel tinyFiller2 = new JPanel();
      tinyFiller2.setMinimumSize(new Dimension(0, BUTTON_SIZE));
      tinyFiller2.setMaximumSize(new Dimension(0, BUTTON_SIZE));
      row2.add(tinyFiller2);

      row2.add(makeButton(HebrewLetter.SCHIN));
      row2.add(makeButton(HebrewLetter.DALET));
      row2.add(makeButton(HebrewLetter.GIMEL));
      row2.add(makeButton(HebrewLetter.CHAF));
      row2.add(makeButton(HebrewLetter.AIN));
      row2.add(makeButton(HebrewLetter.JOD));
      row2.add(makeButton(HebrewLetter.CHET));
      row2.add(makeButton(HebrewLetter.LAMED));
      row2.add(makeButton(HebrewLetter.CHAFSSOFIT));
      row2.add(makeButton(HebrewLetter.FAEISSOFIT));
      row2.add(makeButton(HebrewLetter.KAF));

      JPanel row3 = new JPanel();
      row3.setOpaque(false);
      row3.setLayout(new TrainLayout(row3, 4));

      JPanel halfSizedFiller3 = new JPanel();
      halfSizedFiller3.setMinimumSize(new Dimension(20, BUTTON_SIZE));
      halfSizedFiller3.setMaximumSize(new Dimension(20, BUTTON_SIZE));
      row3.add(halfSizedFiller3);

      row3.add(makeButton(HebrewLetter.SSAIN));
      row3.add(makeButton(HebrewLetter.SSAMECH));
      row3.add(makeButton(HebrewLetter.WET));
      row3.add(makeButton(HebrewLetter.HAEI));
      row3.add(makeButton(HebrewLetter.NUN));
      row3.add(makeButton(HebrewLetter.MEM));
      row3.add(makeButton(HebrewLetter.ZADI));
      row3.add(makeButton(HebrewLetter.TAW));
      row3.add(makeButton(HebrewLetter.ZADISSOFIT));
      row3.add(makeButton(HebrewLetter.BET));

      keyboard.add(row1);
      keyboard.add(row2);
      keyboard.add(row3);
      add(keyboard);
   }

   private Component makeButton(HebrewLetter letter)
   {
      DataButton jButton = new DataButton(ApplicationImages.getLetterIconsMap()
            .get(letter).getScaledInstance(scale.getScaleX(), scale.getScaleY(),
                  java.awt.Image.SCALE_SMOOTH),
            letter.getUnicode());
      jButton.setMargin(new Insets(3, -5, 0, -5));
      jButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));
      jButton.setMaximumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));

      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);
      buttonPanel.setLayout(new TotemLayout(buttonPanel));
      buttonPanel.add(jButton);

      jButton.addMouseListener(new KeyboardListener());
      return buttonPanel;
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