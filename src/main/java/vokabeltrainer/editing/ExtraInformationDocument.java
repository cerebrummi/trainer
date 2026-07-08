package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

public class ExtraInformationDocument extends DefaultStyledDocument
{
   private static final long serialVersionUID = 216678564395494689L;

   private String exclusionPattern;
   private int numberOfLettersAllowed = 8000;

   public ExtraInformationDocument()
   {
      exclusionPattern = SignLetter.getInternationalExclusionPattern();
   }

   public ExtraInformationDocument(int size)
   {
      numberOfLettersAllowed = size;
      exclusionPattern = SignLetter.getInternationalExclusionPattern();
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
               LetterType.NONE);
         StringBuilder builder = new StringBuilder();

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         if (checking(list, builder))
         {
            super.replace(offset, length, text, attrs);
         }

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

         List<String> list = LetterHelper.findLetterCodes(str, LetterType.NONE);
         StringBuilder builder = new StringBuilder();

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         if (checking(list, builder))
         {
            super.insertString(offset, str, attr);
         }

         return;
      }
      super.insertString(offset, str, attr);
   }

   private boolean checking(List<String> list, StringBuilder builder)
   {
      for (int i = 0; i < list.size(); i++)
      {
         String code = list.get(i);
         //
         if (exclusionPattern.contains(code))
         {
            return false;
         }
      }
      return true;
   }
}
