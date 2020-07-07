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
      pattern = HebrewLetter.getPatternString(false);
   }
   
   @Override
   public void insertString(int offset, String str, AttributeSet a)
         throws BadLocationException
   {
      str = StringUtils.strip(str);
      List<String> list = HebrewLetter.findLetterCodes(str);
      if(list.size() > 1)
      {
         if(list.get(list.size()-1).length() == 10)
         {
            super.insertString(offset, str.substring(str.length()-2), a);
         }
         else
         {
            super.insertString(offset, str.substring(str.length()-1), a);
         }
         return;
      }
      for (String letter : list)
      {
         if(!StringUtils.containsIgnoreCase(pattern, letter))
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }
      }
      super.insertString(offset, str, a);
   }
}
