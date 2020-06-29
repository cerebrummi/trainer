package vokabeltrainer;

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

import vokabeltrainer.common.Main;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class KeyboardHebrew extends JPanel
{
   private static final long serialVersionUID = 2289311868636133544L;

   private final int BUTTON_SIZE = 42;
   private List<JTextComponent> components;

   public KeyboardHebrew(JTextComponent textfield,
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

      setLayout(new TotemLayout(this, 10));
      this.setOpaque(false);
      this.setPreferredSize(new Dimension(Settings.getKeyboardWidth(),
            textFieldHeight + 10 + 218));

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
      row1.setLayout(new TrainLayout(row1, 8));

      row1.add(makeButton(HebrewLetter.CHET.getUnicode(),
            HebrewLetter.CHET.getTranscript()));
      row1.add(makeButton(HebrewLetter.SSAIN.getUnicode(),
            HebrewLetter.SSAIN.getTranscript()));
      row1.add(makeButton(HebrewLetter.WAW.getUnicode(),
            HebrewLetter.WAW.getTranscript()));
      row1.add(makeButton(HebrewLetter.HAEI.getUnicode(),
            HebrewLetter.HAEI.getTranscript()));
      row1.add(makeButton(HebrewLetter.DALET.getUnicode(),
            HebrewLetter.DALET.getTranscript()));
      row1.add(makeButton(HebrewLetter.GIMEL.getUnicode(),
            HebrewLetter.GIMEL.getTranscript()));
      row1.add(makeButton(HebrewLetter.WET.getUnicode(),
            HebrewLetter.WET.getTranscript()));
      row1.add(makeButton(HebrewLetter.BET.getUnicode(),
            HebrewLetter.BET.getTranscript()));
      row1.add(makeButton(HebrewLetter.ALEF.getUnicode(),
            HebrewLetter.ALEF.getTranscript()));

      JPanel row2 = new JPanel();
      row2.setOpaque(false);
      row2.setLayout(new TrainLayout(row2, 8));

      row2.add(makeButton(HebrewLetter.NUN.getUnicode(),
            HebrewLetter.NUN.getTranscript()));
      row2.add(makeButton(HebrewLetter.MEMSSOFIT.getUnicode(),
            HebrewLetter.MEMSSOFIT.getTranscript()));
      row2.add(makeButton(HebrewLetter.MEM.getUnicode(),
            HebrewLetter.MEM.getTranscript()));
      row2.add(makeButton(HebrewLetter.LAMED.getUnicode(),
            HebrewLetter.LAMED.getTranscript()));
      row2.add(makeButton(HebrewLetter.CHAFSSOFIT.getUnicode(),
            HebrewLetter.CHAFSSOFIT.getTranscript()));
      row2.add(makeButton(HebrewLetter.CHAF.getUnicode(),
            HebrewLetter.CHAF.getTranscript()));
      row2.add(makeButton(HebrewLetter.KAF.getUnicode(),
            HebrewLetter.KAF.getTranscript()));
      row2.add(makeButton(HebrewLetter.JOD.getUnicode(),
            HebrewLetter.JOD.getTranscript()));
      row2.add(makeButton(HebrewLetter.TET.getUnicode(),
            HebrewLetter.TET.getTranscript()));

      JPanel row3 = new JPanel();
      row3.setOpaque(false);
      row3.setLayout(new TrainLayout(row3, 8));

      row3.add(makeButton(HebrewLetter.KUF.getUnicode(),
            HebrewLetter.KUF.getTranscript()));
      row3.add(makeButton(HebrewLetter.ZADISSOFIT.getUnicode(),
            HebrewLetter.ZADISSOFIT.getTranscript()));
      row3.add(makeButton(HebrewLetter.ZADI.getUnicode(),
            HebrewLetter.ZADI.getTranscript()));
      row3.add(makeButton(HebrewLetter.FAEISSOFIT.getUnicode(),
            HebrewLetter.FAEISSOFIT.getTranscript()));
      row3.add(makeButton(HebrewLetter.FAEI.getUnicode(),
            HebrewLetter.FAEI.getTranscript()));
      row3.add(makeButton(HebrewLetter.PAEI.getUnicode(),
            HebrewLetter.PAEI.getTranscript()));
      row3.add(makeButton(HebrewLetter.AIN.getUnicode(),
            HebrewLetter.AIN.getTranscript()));
      row3.add(makeButton(HebrewLetter.SSAMECH.getUnicode(),
            HebrewLetter.SSAMECH.getTranscript()));
      row3.add(makeButton(HebrewLetter.NUNSSOFIT.getUnicode(),
            HebrewLetter.NUNSSOFIT.getTranscript()));

      JPanel row4 = new JPanel();
      row4.setOpaque(false);
      row4.setLayout(new TrainLayout(row4, 8));

      row4.add(makeButton(HebrewLetter.GERSCHAYIM.getUnicode(),
            HebrewLetter.GERSCHAYIM.getTranscript()));
      row4.add(makeButton(HebrewLetter.GERESCH.getUnicode(),
            HebrewLetter.GERESCH.getTranscript()));
      row4.add(makeButton(HebrewLetter.TAW.getUnicode(),
            HebrewLetter.TAW.getTranscript()));
      row4.add(makeSpaceButton());
      row4.add(makeButton(HebrewLetter.SSIN.getUnicode(),
            HebrewLetter.SSIN.getTranscript()));
      row4.add(makeButton(HebrewLetter.SCHIN.getUnicode(),
            HebrewLetter.SCHIN.getTranscript()));
      row4.add(makeButton(HebrewLetter.RESCH.getUnicode(),
            HebrewLetter.RESCH.getTranscript()));

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
      jButton.addMouseListener(new KeyboardListener(this));
      return jButton;
   }

   private Component makeButton(String unicode, String tooltip)
   {
      DataButton jButton = new DataButton(
            "<html><span>" + unicode + "</span></html>", unicode);
      jButton.setToolTipText(tooltip);
      jButton.setMargin(new Insets(0, -5, 3, -5));
      jButton.setMinimumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));
      jButton.setMaximumSize(new Dimension(BUTTON_SIZE + 2, BUTTON_SIZE));

      JPanel buttonCaption = new JPanel();
      buttonCaption.setOpaque(false);
      buttonCaption.setLayout(new TotemLayout(buttonCaption));
      buttonCaption.add(jButton);
      JLabel captionLabel = new JLabel(tooltip);

      captionLabel.setFont(Main.getGermanFont(8F));
      captionLabel.setMinimumSize(new Dimension(BUTTON_SIZE + 2, 10));
      captionLabel.setMaximumSize(new Dimension(BUTTON_SIZE + 2, 10));

      buttonCaption.add(captionLabel);

      jButton.addMouseListener(new KeyboardListener(this));
      return buttonCaption;
   }

   class KeyboardListener implements MouseListener
   {
      KeyboardHebrew keyboard;
      JTextComponent jTextArea;

      KeyboardListener(KeyboardHebrew keyboard)
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
