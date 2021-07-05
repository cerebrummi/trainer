package vokabeltrainer.types;

public enum SearchType
{
   SEARCHWORD(
         "Suche nach Suchwort"),
   WORDSTART(
         "Suche nach Wortanfang");

   String meaning;

   SearchType(String meaning)
   {
      this.meaning = meaning;
   }

   public String getMeaning(Language language)
   {
      switch (language)
      {
      case GERMAN_TO_HEBREW:
         return meaning;
      case HEBREW_TO_GERMAN:
         switch (this)
         {
         case SEARCHWORD:
            return meaning + " (mit Punktierung)";
         case WORDSTART:
            return meaning + " (ohne Punktierung)";
         }
      }
      return "";
   }

}
