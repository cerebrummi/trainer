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
      List<String> list = HebrewLetter.findLetterCodesAll(text);
      
      if (getLength() + list.size() - length > 1)
      {
         Toolkit.getDefaultToolkit().beep();
         return;
      }

      for (int i = 0; i < list.size(); i++)
      {
         if (StringUtils.containsIgnoreCase(pattern, list.get(i)))
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
      List<String> list = HebrewLetter.findLetterCodesAll(str);
      
      if (getLength() + list.size() > 1)
      {
         Toolkit.getDefaultToolkit().beep();
         return;
      } 

      for (int i = 0; i < list.size(); i++)
      {
         if (StringUtils.containsIgnoreCase(pattern, list.get(i)))
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
