package vokabeltrainer.panels.letterpicture;

import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.tonionlayout.BullsEyeExpanderLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class LetterPictureWordPanel extends JPanel
{
   private static final long serialVersionUID = 4694738420843719008L;

   private Card[] cards = { Card.LETTER, Card.HEBREW };
   private Card[] cards2 = { Card.PICTURE, Card.GERMAN };

   public LetterPictureWordPanel()
   {
      this.setLayout(new BullsEyeExpanderLayout(this));
      this.setOpaque(false);
   }

   public void displayWord(String hebrewWord)
   {
      removeAll();

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));

      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));

      JPanel displayPanel = new JPanel();
      displayPanel.setLayout(new TrainLayout(displayPanel));

      List<String> hebrewLettersCode = LetterHelper
            .findLetterCodes(hebrewWord);
      Collections.reverse(hebrewLettersCode);

      for (String hebrewLetterCode : hebrewLettersCode)
      {
         HebrewLetter hebrewLetter = HebrewLetter
               .getLetterFromCode(hebrewLetterCode);
         if (hebrewLetter != null)
         {
            displayPanel.add(new LetterPictureButtonPanel(
                  ApplicationImages.getLetterPicturesMap().get(hebrewLetter),
                  hebrewLetter, cards));
         }
      }

      vertical.add(displayPanel);

      JPanel displayPanel2 = new JPanel();
      displayPanel2.setLayout(new TrainLayout(displayPanel2));

      for (String hebrewLetterCode : hebrewLettersCode)
      {
         HebrewLetter hebrewLetter = HebrewLetter
               .getLetterFromCode(hebrewLetterCode);
         if (hebrewLetter != null)
         {
            LetterPictureButtonPanel panel = new LetterPictureButtonPanel(
                  ApplicationImages.getLetterPicturesMap().get(hebrewLetter),
                  hebrewLetter, cards2);
            displayPanel2.add(panel);
         }
      }

      vertical.add(displayPanel2);

      horizontal.add(vertical);
      add(horizontal);

      validate();
      repaint();
   }

   public void clear()
   {
      removeAll();
      
      JLabel dummy = new JLabel();
      dummy.setMinimumSize(new Dimension(1200,100));
      dummy.setMaximumSize(new Dimension(1200,100));
      dummy.setBackground(Settings.getTransparent());
      dummy.setOpaque(false);
      add(dummy);
      
      validate();
      repaint();
   }

}
