package vokabeltrainer.panels.letterpicture;

import java.awt.FlowLayout;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import de.copepod.tonion.TotemLayout;
import de.copepod.tonion.TrainLayout;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.editing.HebrewLetter;

public class LetterPictureWordPanel extends JPanel
{
   private static final long serialVersionUID = 4694738420843719008L;

   public LetterPictureWordPanel()
   {
      this.setLayout(new FlowLayout());
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

      List<String> hebrewLettersCode = HebrewLetter.findLetters(hebrewWord);
      Collections.reverse(hebrewLettersCode);

      for (String hebrewLetterCode : hebrewLettersCode)
      {
         HebrewLetter hebrewLetter = HebrewLetter.getLetter(hebrewLetterCode);
         if (hebrewLetter != null)
         {
            displayPanel.add(new LetterPictureButtonPanel(
                  ApplicationImages.getLetterPicturesMap().get(hebrewLetter),
                  hebrewLetter.getGerman(), hebrewLetter));
         }
      }

      vertical.add(displayPanel);

      JPanel displayPanel2 = new JPanel();
      displayPanel2.setLayout(new TrainLayout(displayPanel2));

      for (String hebrewLetterCode : hebrewLettersCode)
      {
         HebrewLetter hebrewLetter = HebrewLetter.getLetter(hebrewLetterCode);
         if (hebrewLetter != null)
         {
            LetterPictureButtonPanel panel = new LetterPictureButtonPanel(
                  ApplicationImages.getLetterPicturesMap().get(hebrewLetter),
                  hebrewLetter.getGerman(), hebrewLetter);
            panel.nextCard();
            displayPanel2.add(panel);
         }
      }

      vertical.add(displayPanel2);

      horizontal.add(vertical);
      add(horizontal);

      validate();
      repaint();
   }

   

}
