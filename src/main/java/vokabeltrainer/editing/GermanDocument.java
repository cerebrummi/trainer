package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class GermanDocument extends PlainDocument
{
   private static final long serialVersionUID = 7089213677826493757L;

   private String signPattern;
   private String numberPattern;
   private int numberOfLettersAllowed = 800;

   public GermanDocument(boolean withComma)
   {
      if (withComma)
      {
         signPattern = SignLetter.getPatternStringGermanWithComma();
      }
      else
      {
         signPattern = SignLetter.getPatternStringGerman();
      }
      numberPattern = NumberLetter.getPatternString();
   }

   public GermanDocument(int size)
   {
      numberOfLettersAllowed = size;
      signPattern = SignLetter.getPatternStringForFileNames();
   }

   @Override
   public void replace(int offset, int length, String text, AttributeSet attrs)
         throws BadLocationException
   {
      if (text != null && !text.isEmpty())
      {
         if (getLength() + text.length() - length > numberOfLettersAllowed)
         {
            text = text.substring(0,
                  numberOfLettersAllowed - (getLength() - length));
            if (text.isEmpty())
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }

         List<String> list = LetterHelper.findLetterCodes(text,
               LetterType.GERMAN);
         StringBuilder builder = new StringBuilder();

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         checking(list, builder);
         super.replace(offset, length, builder.toString(), attrs);
         return;
      }
      super.replace(offset, length, text, attrs);
   }

   @Override
   public void insertString(int offset, String str, AttributeSet attr)
         throws BadLocationException
   {
      if (str != null && !str.isEmpty())
      {
         if (getLength() + str.length() > numberOfLettersAllowed)
         {
            str = str.substring(0, numberOfLettersAllowed - getLength());
            if (str.isEmpty())
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }

         List<String> list = LetterHelper.findLetterCodes(str,
               LetterType.GERMAN);
         StringBuilder builder = new StringBuilder();

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         checking(list, builder);
         super.insertString(offset, builder.toString(), attr);
         return;
      }
      super.insertString(offset, str, attr);
   }

   private void checking(List<String> list, StringBuilder builder)
   {
      for (int i = 0; i < list.size(); i++)
      {
         Letter letter = LetterHelper.getLetterFromCode(list.get(i),
               LetterType.GERMAN);
         String signcode = list.get(i).replace(LetterType.GERMAN.getRealm(), LetterType.SIGN.getRealm());
         String numbercode = list.get(i).replace(LetterType.GERMAN.getRealm(), LetterType.NUMBER.getRealm());
         //
         if (letter != null && letter instanceof GermanLetter)
         {
            // okay
            builder.append(letter.getUnicode());
         }
         else if (signPattern.contains(signcode))
         {
            // okay
            builder
            .append(LetterHelper
                  .getLetterFromCode(signcode, LetterType.SIGN)
                  .getUnicode());
         }
         else if (numberPattern.contains(numbercode))
         {
            // okay
            builder
            .append(LetterHelper
                  .getLetterFromCode(numbercode, LetterType.NUMBER)
                  .getUnicode());
         }
         else
         {
            // remove letter
         }
      }
   }
}
