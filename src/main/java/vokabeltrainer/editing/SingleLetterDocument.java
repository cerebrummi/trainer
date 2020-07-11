package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Collections;
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
   public void insertString(int offset, String str, AttributeSet a)
         throws BadLocationException
   {
      str = StringUtils.strip(str);
      List<String> list = HebrewLetter.findLetterCodes(str);
      if (list.size() > 1 && pattern.contains(list.get(1)))
      {
         super.insertString(0,
               HebrewLetter.getLetterFromCode(list.get(1)).getUnicode(), a);
      }
      else if (list.size() == 1 && pattern.contains(list.get(0)))
      {
         super.insertString(0,
               HebrewLetter.getLetterFromCode(list.get(0)).getUnicode(), a);
      }
      else
      {
         Toolkit.getDefaultToolkit().beep();
      }
   }
}
