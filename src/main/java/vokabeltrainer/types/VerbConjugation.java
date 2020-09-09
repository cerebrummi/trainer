package vokabeltrainer.types;

public enum VerbConjugation
{
   NA("nicht anwendbar"),
   UNKOWN("unbekannt"),
   INFINITIVE("Infinitiv"),
   PAST("Vergangenheit"),
   PAST_PARTICIPLE("Vergangenheit-Partizip"),
   PRESENT("Gegenwart"),
   FUTURE("Zukunft"),
   IMPERARTIVE("Befehlsform"),
   ACTION_NOUN("Gerundium"); // Gerundium
   
   private String name;
   
   VerbConjugation(String name)
   {
      this.name = name;
   }
   
   public String toString()
   {
      return name;
   }
}
