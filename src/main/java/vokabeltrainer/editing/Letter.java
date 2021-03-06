package vokabeltrainer.editing;

public interface Letter
{

   String getCode();

   String getUnicode();
   
   boolean isNewspace();
   
   boolean isSpace();
   
   LetterType isType();

   Letter getNewspace();
   
   int getPixelWidth();
   
   String name();
   
   HebrewLetter getHebrewLetter();
   
   String getTranscript();
}
