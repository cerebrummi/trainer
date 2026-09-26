package vokabeltrainer.panels.letterpicture;

import javax.swing.JTextField;

import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.common.main.App;
import vokabeltrainer.editing.LetterHelper;

import java.io.Serial;

public class LetterTextField extends JTextField
{
   @Serial
   private static final long serialVersionUID = -3945876344326402147L;

   private NikudLetter letter;

   public LetterTextField(App app, NikudLetter letter)
   {
      this.letter = letter;
      this.setBackground(app.appColors.alefbet.getTextBackground());
      this.setForeground(app.appColors.alefbet.getTextForeground());
   }

   public NikudLetter getLetter()
   {
      return letter;
   }

   public boolean isOkay()
   {
      if (!this.getText().strip().isEmpty())
      {
         return letter == LetterHelper.findNikudLetters(this.getText().strip())
               .get(0);
      }
      return false;
   }
}
