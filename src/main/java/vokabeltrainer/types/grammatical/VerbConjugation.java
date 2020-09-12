package vokabeltrainer.types.grammatical;

public enum VerbConjugation
{
   UNKOWN("unbekannt"),
   INFINITIVE("Infinitiv"),
   PAST("Vergangenheit"),
   PAST_PARTICIPLE("Vergangenheit-Partizip"),
   PRESENT("Gegenwart"),
   FUTURE("Zukunft"),
   IMPERARTIVE("Befehlsform"),
   ACTION_NOUN("Gerundium"), // Gerundium
   NA("nicht anwendbar");
   
   private String description;
   
   VerbConjugation(String description)
   {
      this.description = description;
   }
   
   public String toString()
   {
      return description;
   }
}
