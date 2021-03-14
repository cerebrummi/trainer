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
   
   private static final int NUMBER_OF_LETTERS_ALLOWED = 600;

   private String signPattern;
   private String numberPattern;

   public ExtraInformationDocument()
   {
      signPattern = SignLetter.getPatternStringExtraInformation();
      numberPattern = NumberLetter.getPatternString();
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

         List<String> list = LetterHelper.findLetterCodes(text);

         if (list == null || list.isEmpty())
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
            NikudLetter nikudLetter = NikudLetter
                  .getLetterFromCode(list.get(i));
            if (germanLetter != null)
            {
               // okay
            }
            else if (hebrewLetter != null)
            {
               // okay
            }
            else if (nikudLetter != null)
            {
               // okay
            }
            else if (StringUtils.containsIgnoreCase(signPattern, list.get(i)))
            {
               // okay
            }
            else if (StringUtils.containsIgnoreCase(numberPattern, list.get(i)))
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
         if (getLength() + str.length() > NUMBER_OF_LETTERS_ALLOWED)
         {
            str = str.substring(0, NUMBER_OF_LETTERS_ALLOWED - getLength());
            if (str.isEmpty())
            {
               Toolkit.getDefaultToolkit().beep();
               return;
            }
         }

         List<String> list = LetterHelper.findLetterCodes(str);

         if (list == null || list.isEmpty())
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
            NikudLetter nikudLetter = NikudLetter
                  .getLetterFromCode(list.get(i));
            if (germanLetter != null)
            {
               // okay
            }
            else if (hebrewLetter != null)
            {
               // okay
            }
            else if (nikudLetter != null)
            {
               // okay
            }
            else if (StringUtils.containsIgnoreCase(signPattern, list.get(i)))
            {
               // okay
            }
            else if (StringUtils.containsIgnoreCase(numberPattern, list.get(i)))
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
