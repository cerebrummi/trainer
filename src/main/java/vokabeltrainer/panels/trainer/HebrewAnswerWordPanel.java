package vokabeltrainer.panels.trainer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.copepod.tonion.TrainLayout;
import vokabeltrainer.LetterFeedbackImage;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.types.Expression;

public class HebrewAnswerWordPanel extends JPanel
{
   private static final long serialVersionUID = 3783416522992027245L;

   private boolean okay = true;

   public HebrewAnswerWordPanel(Expression expression, String answer)
   {
      List<HebrewLetter> answerLetters = HebrewLetter.findHebrewLetters(answer);
      List<HebrewLetter> expressionLetters = HebrewLetter
            .findHebrewLetters(expression.getHebrew());

      List<BufferedImage> letterFeedbackImages = new ArrayList<>();

      int width = 0;

      for (int i = 0; i < answerLetters.size()
            && i < expressionLetters.size(); i++)
      {
         if (answerLetters.get(i).equals(expressionLetters.get(i)))
         {
            letterFeedbackImages
                  .add(LetterFeedbackImage.make(answerLetters.get(i), true));
         }
         else
         {
            okay = false;
            letterFeedbackImages
                  .add(LetterFeedbackImage.make(answerLetters.get(i), false));
         }
         width += answerLetters.get(i).getPixelWidth();
      }

      if (answerLetters.size() - expressionLetters.size() > 0)
      {
         okay = false;
         for (int i = expressionLetters.size(); i < answerLetters.size(); i++)
         {
            letterFeedbackImages
                  .add(LetterFeedbackImage.make(answerLetters.get(i), false));
            width += answerLetters.get(i).getPixelWidth();
         }
      }

      if (expressionLetters.size() - answerLetters.size() > 0)
      {
         okay = false;
         for (int i = answerLetters.size(); i < expressionLetters.size(); i++)
         {
            letterFeedbackImages
                  .add(LetterFeedbackImage.make(HebrewLetter.SPACE, false));
            width += HebrewLetter.SPACE.getPixelWidth();
         }
      }

      Collections.reverse(letterFeedbackImages);

      this.setPreferredSize(new Dimension(width
            + (Math.max(answerLetters.size(), expressionLetters.size()) * 4),
            64));
      this.setLayout(new TrainLayout(this, 4));

      for (BufferedImage image : letterFeedbackImages)
      {
         JLabel label = new JLabel(new ImageIcon(image));
         label.setMinimumSize(new Dimension(image.getWidth(), 64));
         label.setMaximumSize(new Dimension(image.getWidth(), 64));
         this.add(label);
      }
   }

   public boolean isOkay()
   {
      return okay;
   }
}
