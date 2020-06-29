package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.apache.commons.lang3.StringUtils;

public class HebrewDocument extends PlainDocument
{
   String pattern;

   private static final long serialVersionUID = -9186425449349376170L;

   
   public HebrewDocument(boolean withComma)
   {
      pattern = HebrewLetter.getPatternString(withComma);
   }
   
   public void insertString(int offset, String str, AttributeSet attr)
         throws BadLocationException
   { 
      List<String> list = HebrewLetter.findLetterCodes(str);
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
