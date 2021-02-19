package vokabeltrainer.editing;

public class LetterForAnalysis
{
   Letter content;

   public LetterForAnalysis(Letter content)
   {
      this.content = content;
   }

   public Letter getContent()
   {
      return content;
   }

   public LetterForAnalysis clone()
   {
      LetterForAnalysis duplicate = new LetterForAnalysis(content);

      return duplicate;
   }
}
