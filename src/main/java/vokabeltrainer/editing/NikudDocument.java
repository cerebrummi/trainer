package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.apache.commons.lang3.StringUtils;

public class NikudDocument extends PlainDocument
{
   private static final int NUMBER_OF_LETTERS_ALLOWED = 50;

   private static final long serialVersionUID = -9186425449349376170L;

   private String signPattern;

   public NikudDocument(boolean withComma)
   {
      if (withComma)
      {
         signPattern = SignLetter.getPatternStringNikudWithComma();
      }
      else
      {
         signPattern = SignLetter.getPatternStringNikud();
      }
   }

   @Override
   public void replace(int offset, int length, String text, AttributeSet attrs)
         throws BadLocationException
   {
      if (text != null && !text.isEmpty())
      {
         if (getLength() + text.length() - length > NUMBER_OF_LETTERS_ALLOWED)
         {
            text = text.substring(0,
                  NUMBER_OF_LETTERS_ALLOWED - (getLength() - length));
            if (text.isEmpty())
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }

         List<String> list = LetterHelper.findNikudLetterCodes(text);

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         for (int i = 0; i < list.size(); i++)
         {
            NikudLetter letter = NikudLetter.getLetterFromCode(list.get(i));
            if (letter != null)
            {
               // okay
            }
            else if (StringUtils.containsIgnoreCase(signPattern, list.get(i)))
            {
               // okay
            }
            else
            {
               // remove letter
               list.remove(i);
            }
         }
         super.replace(offset, length, LetterHelper.makeWordFromCodes(list),
               attrs);
         return;
      }
      super.replace(offset, length, text, attrs);
   }

   public void insertString(int offset, String str, AttributeSet attr)
         throws BadLocationException
   {
      if (str != null && !str.isEmpty())
      {
         if (getLength() + str.length() > NUMBER_OF_LETTERS_ALLOWED)
         {
            str = str.substring(0, NUMBER_OF_LETTERS_ALLOWED - getLength());
            if (str.isEmpty())
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }

         List<String> list = LetterHelper.findNikudLetterCodes(str);

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         for (int i = 0; i < list.size(); i++)
         {
            NikudLetter letter = NikudLetter.getLetterFromCode(list.get(i));
            if (letter != null)
            {
               // okay
            }
            else if (StringUtils.containsIgnoreCase(signPattern, list.get(i)))
            {
               // okay
            }
            else
            {
               // remove letter
               list.remove(i);
            }
         }
         super.insertString(offset, LetterHelper.makeWordFromCodes(list), attr);
         return;
      }
      super.insertString(offset, str, attr);
   }
}
