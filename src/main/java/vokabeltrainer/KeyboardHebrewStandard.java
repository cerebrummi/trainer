package vokabeltrainer;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import vokabeltrainer.common.Main;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class KeyboardHebrewStandard extends JPanel
{
   private static final long serialVersionUID = 2289311868636133564L;

   private final int BUTTON_SIZE = 42;
   private List<JTextComponent> components;

   public KeyboardHebrewStandard(JTextComponent textfield,
         List<JTextComponent> arrayList, int textFieldHeight)
   {
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
      halfSizedFiller1.setMinimumSize(new Dimension(20,BUTTON_SIZE));
      halfSizedFiller1.setMaximumSize(new Dimension(20,BUTTON_SIZE));
      row1.add(halfSizedFiller1);
      
      row1.add(makeButton(HebrewLetter.SSIN.getUnicode()));
      row1.add(makeButton(HebrewLetter.KUF.getUnicode()));
      row1.add(makeButton(HebrewLetter.RESCH.getUnicode()));
      row1.add(makeButton(HebrewLetter.ALEF.getUnicode()));
      row1.add(makeButton(HebrewLetter.TET.getUnicode()));
      row1.add(makeButton(HebrewLetter.WAW.getUnicode()));
      row1.add(makeButton(HebrewLetter.NUNSSOFIT.getUnicode()));
      row1.add(makeButton(HebrewLetter.MEMSSOFIT.getUnicode()));
      row1.add(makeButton(HebrewLetter.FAEI.getUnicode()));
      row1.add(makeButton(HebrewLetter.PAEI.getUnicode()));

      JPanel row2 = new JPanel();
      row2.setOpaque(false);
      row2.setLayout(new TrainLayout(row2, 4));
      
      JPanel tinyFiller2 = new JPanel();
      tinyFiller2.setMinimumSize(new Dimension(0,BUTTON_SIZE));
      tinyFiller2.setMaximumSize(new Dimension(0,BUTTON_SIZE));
      row2.add(tinyFiller2);
   
      row2.add(makeButton(HebrewLetter.SCHIN.getUnicode()));
      row2.add(makeButton(HebrewLetter.DALET.getUnicode()));
      row2.add(makeButton(HebrewLetter.GIMEL.getUnicode()));
      row2.add(makeButton(HebrewLetter.CHAF.getUnicode()));
      row2.add(makeButton(HebrewLetter.AIN.getUnicode()));
      row2.add(makeButton(HebrewLetter.JOD.getUnicode()));
      row2.add(makeButton(HebrewLetter.CHET.getUnicode()));
      row2.add(makeButton(HebrewLetter.LAMED.getUnicode()));
      row2.add(makeButton(HebrewLetter.CHAFSSOFIT.getUnicode()));
      row2.add(makeButton(HebrewLetter.FAEISSOFIT.getUnicode()));
      row2.add(makeButton(HebrewLetter.KAF.getUnicode()));

      JPanel row3 = new JPanel();
      row3.setOpaque(false);
      row3.setLayout(new TrainLayout(row3, 4));

      JPanel halfSizedFiller3 = new JPanel();
      halfSizedFiller3.setMinimumSize(new Dimension(20,BUTTON_SIZE));
      halfSizedFiller3.setMaximumSize(new Dimension(20,BUTTON_SIZE));
      row3.add(halfSizedFiller3);
      
      row3.add(makeButton(HebrewLetter.SSAIN.getUnicode()));
      row3.add(makeButton(HebrewLetter.SSAMECH.getUnicode()));
      row3.add(makeButton(HebrewLetter.WET.getUnicode()));
      row3.add(makeButton(HebrewLetter.HAEI.getUnicode()));
      row3.add(makeButton(HebrewLetter.NUN.getUnicode()));
      row3.add(makeButton(HebrewLetter.MEM.getUnicode()));
      row3.add(makeButton(HebrewLetter.ZADI.getUnicode()));
      row3.add(makeButton(HebrewLetter.TAW.getUnicode()));
      row3.add(makeButton(HebrewLetter.ZADISSOFIT.getUnicode()));
      row3.add(makeButton(HebrewLetter.BET.getUnicode()));

      keyboard.add(row1);
      keyboard.add(row2);
      keyboard.add(row3);
      add(keyboard);
   }

   private Component makeButton(String unicode)
   {
      DataButton jButton = new DataButton(
            "<html><span>" + unicode + "</span></html>", unicode);
      jButton.setMargin(new Insets(0, -5, 3, -5));
      jButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));
      jButton.setMaximumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));

      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);
      buttonPanel.setLayout(new TotemLayout(buttonPanel));
      buttonPanel.add(jButton);

      jButton.addMouseListener(new KeyboardListener(this));
      return buttonPanel;
   }

   class KeyboardListener implements MouseListener
   {
      KeyboardHebrewStandard keyboard;
      JTextComponent jTextArea;

      KeyboardListener(KeyboardHebrewStandard keyboard)
      {
         this.keyboard = keyboard;
      }

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