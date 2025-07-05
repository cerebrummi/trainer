package vokabeltrainer.panels.letterpicture;

import javax.swing.JTextField;

import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.common.colors.AlefbetColors;
import vokabeltrainer.editing.LetterHelper;

public class LetterTextField extends JTextField
{
   private static final long serialVersionUID = -3945876344326402147L;

   private NikudLetter letter;
   
   public LetterTextField(NikudLetter letter)
   {
      this.letter = letter;
      this.setBackground(AlefbetColors.getTextBackground());
      this.setForeground(AlefbetColors.getTextForeground());
   }

   public NikudLetter getLetter()
   {
      return letter;
   }
   
   public boolean isOkay()
   {
      if(!this.getText().strip().isEmpty())
      {
         return letter == LetterHelper.findNikudLetters(this.getText().strip()).get(0);
      }
      return false;
   }
}
