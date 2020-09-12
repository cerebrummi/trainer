package vokabeltrainer.editing;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import org.apache.commons.lang3.StringUtils;

public class ExtraInformationDocument extends DefaultStyledDocument
{
   private static final long serialVersionUID = 216678564395494689L;

   private String signPattern;

   public ExtraInformationDocument()
   {
      signPattern = SignLetter.getPatternStringExtraInformation();
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

         if (getLength() + list.size() - length > 250)
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         for (int i = 0; i < list.size(); i++)
         {
            GermanLetter germanLetter = GermanLetter
                  .getLetterFromCode(list.get(i));
            HebrewLetter hebrewLetter = HebrewLetter
                  .getLetterFromCode(list.get(i));
            if (germanLetter != null)
            {
               // okay
            }
            else if (hebrewLetter != null)
            {
               // okay
            }
            else if (StringUtils.containsIgnoreCase(signPattern, list.get(i)))
            {
               // okay
            }
            else
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }
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

         if (getLength() + list.size() > 250)
         {
            Toolkit.getDefaultToolkit().beep();
            return;
         }

         for (int i = 0; i < list.size(); i++)
         {
            GermanLetter germanLetter = GermanLetter
                  .getLetterFromCode(list.get(i));
            HebrewLetter hebrewLetter = HebrewLetter
                  .getLetterFromCode(list.get(i));
            if (germanLetter != null)
            {
               // okay
            }
            else if (hebrewLetter != null)
            {
               // okay
            }
            else if (StringUtils.containsIgnoreCase(signPattern, list.get(i)))
            {
               // okay
            }
            else
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }
      }

      super.insertString(offset, str, attr);
   }
}
