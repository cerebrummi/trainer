package vokabeltrainer.types;

public enum Translation
{
   ALEFBET_UEBEN("Alefbet üben"),
   ALLE_BILDER_KANN_MAN("Alle Bilder kann man"),
   ALLE_UMDREHEN("alle umdrehen"),
   AUCH_EINZELN_ANKLICKEN("auch einzeln anklicken."),
   AUSWERTEN("auswerten"),
   
   BILDERBUCHSTABEN("Bilderbuchstaben"),
  
   DRUCKSCHRIFT("Druckschrift"),
   
   SCHREIBSCHRIFT("Schreibschrift"),
   
   TASTATUR_REGULAER("Tastatur, regulär"),
   TASTATUR_VERMISCHT("Tastatur, vermischt"),
   
   ZURUECKSETZEN("zurücksetzen");

   private String german;
   
   Translation(String german)
   {
      this.german = german;
   }

   public String getGerman()
   {
      return german;
   }
}
