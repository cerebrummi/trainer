package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.apache.commons.lang3.StringUtils;

public class SingleLetterDocument extends PlainDocument
{
   private static final long serialVersionUID = -6914168959814651195L;

   String pattern;

   public SingleLetterDocument()
   {
      pattern = HebrewLetter.getLetterPatternString();
   }

   @Override
   public void replace(int offset, int length, String text, AttributeSet attrs)
         throws BadLocationException
   {
      if (getLength() + text.length() - length > 1)
      {
         Toolkit.getDefaultToolkit().beep();
         return;
      }
      
      List<String> list = HebrewLetter.findLetterCodesAll(text);

      for (int i = 0; i < list.size(); i++)
      {
         HebrewLetter letter = HebrewLetter.getLetterFromCode(list.get(i));
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
   
   public void insertString(int offset, String str, AttributeSet attr)
         throws BadLocationException
   {
      if (getLength() + str.length() > 1)
      {
         Toolkit.getDefaultToolkit().beep();
         return;
      }
      List<String> list = HebrewLetter.findLetterCodesAll(str);

      for (int i = 0; i < list.size(); i++)
      {
         HebrewLetter letter = HebrewLetter.getLetterFromCode(list.get(i));
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
