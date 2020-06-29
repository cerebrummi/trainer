package vokabeltrainer.panels.letterpicture;

import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import vokabeltrainer.editing.HebrewLetter;

public class LetterTextField extends JTextField
{
   private static final long serialVersionUID = -3945876344326402147L;

   private HebrewLetter letter;
   
   public LetterTextField(HebrewLetter letter)
   {
      this.letter = letter;
   }

   public HebrewLetter getLetter()
   {
      return letter;
   }
   
   public boolean isOkay()
   {
      if(!StringUtils.strip(this.getText()).isEmpty())
      {
         return letter == HebrewLetter.findHebrewLetters(StringUtils.strip(this.getText())).get(0);
      }
      return false;
   }
}
