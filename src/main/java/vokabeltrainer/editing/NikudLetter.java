package vokabeltrainer.editing;

import org.apache.commons.lang3.StringUtils;

public enum NikudLetter implements Letter
{
   AIN(
         "\u05E2",
         " 05E2",
         "ain"),
   ALEF(
         "\u05D0",
         " 05D0",
         "alef"),
   CHET(
         "\u05D7",
         " 05D7",
         "chet"),
   DALET(
         "\u05D3",
         " 05D3",
         "dalet"),
   GIMEL(
         "\u05D2",
         " 05D2",
         "gimel"),
   GERESCH(
         "\u05F3",
         " 05F3",
         "geresch"),
   GERSCHAYIM(
         "\u05F4",
         " 05F4",
         "gerschayim"),
   HAEI(
         "\u05D4",
         " 05D4",
         "häi"),
   JOD(
         "\u05D9",
         " 05D9",
         "jod"),
   KUF(
         "\u05E7",
         " 05E7",
         "kuf"),
   LAMED(
         "\u05DC",
         " 05DC",
         "lamed"),
   RESCH(
         "\u05E8",
         " 05E8",
         "resch"),
   SSIN(
         "\uFb2B",
         " Fb2B",
         "ssin"),
   SSAIN(
         "\u05D6",
         " 05D6",
         "ssain"),
   SCHIN(
         "\u05E9",
         " 05E9",
         "schin"),
   SSAMECH(
         "\u05E1",
         " 05E1",
         "ssamech"),
   SPACE(
         "\u0020",
         " 0020",
         "space"),
   TAW(
         "\u05EA",
         " 05EA",
         "taw"),
   TET(
         "\u05D8",
         " 05D8",
         "tet"),
   WET(
         "\u05D1",
         " 05D1",
         "wet"),
   WAW(
         "\u05D5",
         " 05D5",
         "waw"),
   ZADI(
         "\u05E6",
         " 05E6",
         "zadi"),
   ZADISSOFIT(
         "\u05E5",
         " 05E5",
         "zadissofit"),
   CHAF(
         "\u05DB",
         " 05DB",
         "chaf"),
   CHAFSSOFIT(
         "\u05DA",
         " 05DA",
         "chafssofit"),
   FAEI(
         "\u05E4",
         " 05E4",
         "fäi"),
   FAEISSOFIT(
         "\u05E3",
         " 05E3",
         "fäissofit"),
   MEM(
         "\u05DE",
         " 05DE",
         "mem"),
   MEMSSOFIT(
         "\u05DD",
         " 05DD",
         "memssofit"),
   NUN(
         "\u05E0",
         " 05E0",
         "nun"),
   NUNSSOFIT(
         "\u05DF",
         " 05DF",
         "nunssofit"),
   SHEVA("\u05B0",
         " 05B0",
         "schwa = e"),
   HATAF_SEGOL("\u05B1",
         " 05B1",
         "chataf szegol = e"),
   HATAF_PATAH("\u05B2",
         " 05B2",
         "chataf patach = a"),
   HATAF_QAMATS("\u05B3",
         " 05B3",
         "chataf kamatz = o"),
   HIRIQ("\u05B4",
         " 05B4",
         "chirik = i"),
   TSERE("\u05B5",
         " 05B5",
         "zeré = e"),
   SEGOL("\u05B6",
         " 05B6",
         "szegol = e"),
   PATAH("\u05B7",
         " 05B7",
         "patach = a"),
   QAMATS("\u05B8",
         " 05B8",
         "kamatz = a"),
   HOLAM("\u05B9",
         " 05B9",
         "cholam = o"),
   HOLAM_HASER("\u05BA",
         " 05BA",
         "cholam chaser"),
   QUBUTS("\u05BB",
         " 05BB",
         "kubutz = u"),
   DAGESH("\u05BC",
         " 05BC",
         "dagesch"),
   METEG("\u05BD",
         " 05BD",
         "meteg"),
   MAQAF("\u05BE",
         " 05BE",
         "makaf"),
   RAFE("\u05BF",
         " 05BF",
         "rafi"),
   PASEQ("\u05C0",
         " 05C0",
         "pasek"),
   SHIN_DOT("\u05C1",
         " 05C1",
         "schin Punkt"),
   SIN_DOT("\u05C2",
         " 05C2",
         "sin Punkt"),
   SOF_PASUQ("\u05C3",
         " 05C3",
         "sof pasuk"),
   UPPER_DOT("\u05C4",
         " 05C4",
         "oberer Punkt"),
   LOWER_DOT("\u05C5",
         " 05C5",
         "unterer Punkt"),
   HAFUKAH("\u05C6",
         " 05C6",
         "chafukach"),
   QAMATS_QATAN("\u05C7",
         " 05C7",
         "kamatz katan"),
   JIDDISH_DOUBLE_WAW("\u05F0",
         " 05F0",
         "jiddisch doppeltes waw"),
   JIDDISH_WAW_JOD("\u05F1",
         " 05F1",
         "jiddisch waw jod"),
   JIDDISH_DOUBLE_JOD("\u05F2",
         " 05F2",
         "jiddisch doppeltes jod");

   private String unicode;
   private String code;
   private String transcript;

   NikudLetter(String unicode, String code, String transcript)
   {
      this.unicode = unicode;
      this.code = code;
      this.transcript = transcript;
   }

   public String getUnicode()
   {
      return unicode;
   }

   public String getCode()
   {
      return code;
   }

   public String getTranscript()
   {
      return transcript;
   }

   public static NikudLetter getLetterFromCode(String code)
   {
      for (NikudLetter letter : NikudLetter.values())
      {
         if (StringUtils.containsIgnoreCase(letter.getCode(), code))
         {
            return letter;
         }
      }
      return null;
   }

}
