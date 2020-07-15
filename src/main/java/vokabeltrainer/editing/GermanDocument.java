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

   private String pattern;

   public GermanDocument(boolean withComma)
   {
      if (withComma)
      {
         pattern = SignLetter.getPatternStringGermanWithComma();
      }
      else
      {
         pattern = SignLetter.getPatternStringGerman();
      }
   }

   @Override
   public void replace(int offset, int length, String text, AttributeSet attrs)
         throws BadLocationException
   {
      List<String> list = HebrewLetter.findLetterCodesAll(text);
      
      if (getLength() + list.size() - length > 50)
      {
         Toolkit.getDefaultToolkit().beep();
         return;
      }

      for (int i = 0; i < list.size(); i++)
      {
         GermanLetter letter = GermanLetter.getLetterFromCode(list.get(i));
         if (letter != null)
         {
            // okay
         }
         else if (StringUtils.containsIgnoreCase(pattern, list.get(i)))
         {
            // okay
         }
         else
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }
      }
      super.replace(offset, length, text, attrs);
   }

   @Override
   public void insertString(int offset, String str, AttributeSet attr)
         throws BadLocationException
   {
      List<String> list = HebrewLetter.findLetterCodesAll(str);
      
      if (getLength() + list.size() > 50)
      {
         Toolkit.getDefaultToolkit().beep();
         return;
      }

      for (int i = 0; i < list.size(); i++)
      {
         GermanLetter letter = GermanLetter.getLetterFromCode(list.get(i));
         if (letter != null)
         {
            // okay
         }
         else if (StringUtils.containsIgnoreCase(pattern, list.get(i)))
         {
            // okay
         }
         else
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }
      }

      super.insertString(offset, str, attr);
   }
}
