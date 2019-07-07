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

   String pattern;

   public GermanDocument(boolean withComma)
   {
      pattern = GermanLetter.getPatternString(withComma);
   }
   
   public void insertString(int offset, String str, AttributeSet attr)
         throws BadLocationException
   { 
      List<String> list = GermanLetter.findLetters(str);
      for (String letter : list)
      {
         if(!StringUtils.containsIgnoreCase(pattern, letter))
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }
      }
      
      super.insertString(offset, str, attr);
   }
}
