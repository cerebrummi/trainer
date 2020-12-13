package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.apache.commons.lang3.StringUtils;

public class GermanDocument extends PlainDocument
{
   private static final long serialVersionUID = 7089213677826493757L;

   private String signPattern;
   private int size = 50;

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
   }

   public GermanDocument(int size)
   {
      this.size = size;
      signPattern = SignLetter.getPatternStringForFileNames();
   }

   @Override
   public void replace(int offset, int length, String text, AttributeSet attrs)
         throws BadLocationException
   {
      if (text != null && !text.isEmpty())
      {
         List<String> list = LetterHelper.findLetterCodes(text);

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         if (getLength() + list.size() - length > size)
         {
            text = text.substring(0, (size - 1) - getLength() + 1);
            if (text.isEmpty())
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }

         for (int i = 0; i < list.size(); i++)
         {
            GermanLetter letter = GermanLetter.getLetterFromCode(list.get(i));
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

   @Override
   public void insertString(int offset, String str, AttributeSet attr)
         throws BadLocationException
   {
      if (str != null && !str.isEmpty())
      {
         List<String> list = LetterHelper.findLetterCodes(str);

         if (list == null || list.isEmpty())
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         if (getLength() + list.size() > size)
         {
            str = str.substring(0, (size - 1) - getLength() + 1);
            if (str.isEmpty())
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }

         for (int i = 0; i < list.size(); i++)
         {
            GermanLetter letter = GermanLetter.getLetterFromCode(list.get(i));
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
