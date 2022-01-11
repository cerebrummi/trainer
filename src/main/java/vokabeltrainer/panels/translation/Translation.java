package vokabeltrainer.panels.translation;

public enum Translation
{
   ALEFBET("Alefbet"),
   ALEFBET_UEBEN("Alefbet üben"),
   ALFABET("Alfabet"),
   ALLE_BILDER_KANN_MAN("Alle Bilder kann man"),
   ALLE_UMDREHEN("alle umdrehen"),
   AUCH_EINZELN_ANKLICKEN("auch einzeln anklicken."),
   AUF_DER_TASTATUR_DRUECKEN_("auf der Tastatur drücken."),
   AUSWAHLEN__UM_DANN_DAS_TRAINING_ZU_STARTEN_("auswählen, um dann das Training zu starten."),
   AUSWAHL("Auswahl"),
   AUSWAHL_ZUR_DATENBANK_VERSCHIEBEN("Auswahl zur Datenbank verschieben"),
   AUSWAHL_ZUR_LEKTION_VERSCHIEBEN("Auswahl zur Lektion verschieben"),
   AUSWERTEN("auswerten"),
   
   BILDERBUCHSTABEN("Bilderbuchstaben"),
   BITTE_GEBEN_SIE_EINEN_NEUEN_DATENBANKNAMEN_EIN("Bitte geben Sie einen neuen Datenbanknamen ein"),
   BITTE_GEBEN_SIE_EINEN_NEUEN_LEKTIONSNAMEN_EIN("Bitte geben Sie einen neuen Lektionsnamen ein"),
   BITTE_NEUE_WORTE_DURCH_EINGABE_EINER_ANZAHL("Bitte neue Worte durch Eingabe einer Anzahl"),
  
   DATUM("Datum"),
   DEUTSCH("Deutsch"),
   DRUCKSCHRIFT("Druckschrift"),
   
   EINGABE("Eingabe"),
   EINMAL_ANKLICKEN_UND_DANN_ENTER_EINGABE_TASTE("einmal anklicken und dann Enter/Eingabe Taste"),
   EINMAL_KLICKEN_MARKIERT_EINEN_EINTRAG("einmal klicken markiert einen Eintrag"),
   EINSTELLUNGEN_UND_SERVICE("Einstellungen und Service"),
   EINTRAEGE_IN_DIESER_TABELLE("Einträge in dieser Tabelle"),
   EINTRAEGE_LOESCHEN__("Einträge löschen?"),
   ENTER_DRUECKEN_OEFFNET_DEN_MARKIERTEN_EINTRAG("Enter drücken öffnet den markierten Eintrag"),
   ES_WURDEN_KEINE_EINTRAEGE("Es wurden keine Einträge"),
   
   FRAGE("Frage"),
   
   GESAMTAUSWAHL_AUFHEBEN("Gesamtauswahl aufheben"),
   GESAMTAUSWAHL_KOPIEREN("Gesamtauswahl kopieren"),
   GESAMTAUSWAHL_LOESCHEN("Gesamtauswahl löschen"),
   
   HEBRAEISCH("Hebräisch"),
   HINWEIS__DIE_VOKABELN_EINGEBAUTER_DATENBANKEN("Hinweis: Die Vokabeln eingebauter Datenbanken"),
   
   INDEX("Index"),
   INFO("Info"),
   INFORMATION("Information"),
   
   KARTEIKASTEN("Karteikasten"),
   KEINE_WORTE_ZUM_UEBEN_AUSGEWAEHLT("Keine Worte zum Üben ausgewählt."),
   KOENNEN_NICHT_GELOESCHT_WERDEN_("können nicht gelöscht werden."),
   
   LEKTIONEN("Lektionen"),
   
   NACHWEISE("Nachweise"),
   NAECHSTE_AUSWAHL("nächste Auswahl"),
   NEUE_VOKABEL("neue Vokabel"),
   NUR_SELBST_EINGEGEBENE_VOKABELN_KOENNEN_KOPIERT_WERDEN("Nur selbst eingegebene Vokabeln können kopiert werden."),
   
   ODER_WAEHLEN_SIE_EINE_VORHANDENE_DATENBANK_AUS("oder wählen Sie eine vorhandene Datenbank aus."),
   ODER_WAEHLEN_SIE_EINE_VORHANDENE_LEKTION_AUS("oder wählen Sie eine vorhandene Lektion aus."),
   
   SCHREIBSCHRIFT("Schreibschrift"),
   START("Start"),
   SUCHE("Suche"),
   SUCHE_STARTEN("Suche starten"),
   SUCHE_WORT_IN_TABELLE("suche Wort in Tabelle"),
   SUCHWORT("Suchwort"),
   
   TABELLE("Tabelle"),
   TABELLE_AUSWAEHLEN("Tabelle auswählen"),
   TABELLE_BEDIENEN("Tabelle bedienen"),
   TABELLE_KOPIEREN("Tabelle kopieren"),
   TABELLE_SORTIEREN_NACH("Tabelle sortieren nach"),
   TABELLENAUSWAHL_AUFHEBEN("Tabellenauswahl aufheben"),
   TABELLENAUSWAHL_KOPIEREN("Tabellenauswahl kopieren"),
   TABELLENAUSWAHL_LOESCHEN("Tabellenauswahl löschen"),
   TASTATUR_REGULAER("Tastatur, regulär"),
   TASTATUR_VERMISCHT("Tastatur, vermischt"),
   TRAININGSUEBERSICHT("Trainingsübersicht"),
   
   UM_EINE_VOKABEL_WIEDER_AUFZURUFEN__("Um eine Vokabel wieder aufzurufen,"),
   
   VOKABELN_ABFRAGEN("Vokabeln abfragen"),
   
   WOERTERBUCH("Wörterbuch"),
   WOLLEN_SIE_WIRKLICH("Wollen Sie wirklich"),
   WOLLEN_SIE_WIRKLICH_DEN_PAPIERKORB_LEEREN__("Wollen Sie wirklich den Papierkorb leeren?"),
   WOLLEN_SIE_WIRKLICH_DIE_VOKABELN_IN_EINE_ANDERE_DATENBANK_VERSCHIEBEN__("Wollen Sie wirklich die Vokabeln in eine andere Datenbank verschieben?"),
   WOLLEN_SIE_WIRKLICH_DIE_VOKABELN_IN_EINE_ANDERE_LEKTION_VERSCHIEBEN__("Wollen Sie wirklich die Vokabeln in eine andere Lektion verschieben?"),
   WOLLEN_SIE_WIRKLICH_EINEN_EINTRAG_LOESCHEN__("Wollen Sie wirklich einen Eintrag löschen?"),
   WORT_AUF_DEUTSCH_EINGEBEN("Wort auf Deutsch eingeben"),
   WORT_AUF_HEBRAISCH_EINGEBEN("Wort auf Hebräisch eingeben"),
   WORTARTEN("Wortarten"),
   
   ZUM_LOESCHEN_AUSGEWAEHLT("zum Löschen ausgewählt."),
   ZURUECKSETZEN("zurücksetzen"),
   ZWEIMAL_KLICKEN_WAEHLT_EINEN_EINTRAG_AUS__STECKNADEL_("zweimal klicken wählt einen Eintrag aus (Stecknadel)");
   

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
