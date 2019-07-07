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

   public String toString()
   {
      return meaning;
   }
}
