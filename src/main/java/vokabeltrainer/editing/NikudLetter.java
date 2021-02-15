package vokabeltrainer.editing;

import org.apache.commons.lang3.StringUtils;

public enum NikudLetter implements Letter
{
   AIN(
         "\u05E2",
         " 05E2",
         "ain",
         NikudLetterDistinction.LETTER),
   ALEF(
         "\u05D0",
         " 05D0",
         "alef",
         NikudLetterDistinction.LETTER),
   CHET(
         "\u05D7",
         " 05D7",
         "chet",
         NikudLetterDistinction.LETTER),
   DALET(
         "\u05D3",
         " 05D3",
         "dalet",
         NikudLetterDistinction.LETTER),
   GIMEL(
         "\u05D2",
         " 05D2",
         "gimel",
         NikudLetterDistinction.LETTER),
   GERESCH(
         "\u05F3",
         " 05F3",
         "geresch",
         NikudLetterDistinction.LETTER),
   GERSCHAYIM(
         "\u05F4",
         " 05F4",
         "gerschayim",
         NikudLetterDistinction.LETTER),
   HAEI(
         "\u05D4",
         " 05D4",
         "häi",
         NikudLetterDistinction.LETTER),
   JOD(
         "\u05D9",
         " 05D9",
         "jod",
         NikudLetterDistinction.LETTER),
   KUF(
         "\u05E7",
         " 05E7",
         "kuf",
         NikudLetterDistinction.LETTER),
   LAMED(
         "\u05DC",
         " 05DC",
         "lamed",
         NikudLetterDistinction.LETTER),
   RESCH(
         "\u05E8",
         " 05E8",
         "resch",
         NikudLetterDistinction.LETTER),
   SSAIN(
         "\u05D6",
         " 05D6",
         "ssain",
         NikudLetterDistinction.LETTER),
   SCHIN(
         "\u05E9",
         " 05E9",
         "schin",
         NikudLetterDistinction.LETTER),
   SSAMECH(
         "\u05E1",
         " 05E1",
         "ssamech",
         NikudLetterDistinction.LETTER),
   SPACE(
         "\u0020",
         " 0020",
         "space",
         NikudLetterDistinction.LETTER),
   NEWSPACE(
         "\u00A0",
         " 00A0",
         "newspace",
         NikudLetterDistinction.LETTER),
   TAW(
         "\u05EA",
         " 05EA",
         "taw",
         NikudLetterDistinction.LETTER),
   TET(
         "\u05D8",
         " 05D8",
         "tet",
         NikudLetterDistinction.LETTER),
   WET(
         "\u05D1",
         " 05D1",
         "wet",
         NikudLetterDistinction.LETTER),
   WAW(
         "\u05D5",
         " 05D5",
         "waw",
         NikudLetterDistinction.LETTER),
   ZADI(
         "\u05E6",
         " 05E6",
         "zadi",
         NikudLetterDistinction.LETTER),
   ZADISSOFIT(
         "\u05E5",
         " 05E5",
         "zadissofit",
         NikudLetterDistinction.LETTER),
   CHAF(
         "\u05DB",
         " 05DB",
         "chaf",
         NikudLetterDistinction.LETTER),
   CHAFSSOFIT(
         "\u05DA",
         " 05DA",
         "chafssofit",
         NikudLetterDistinction.LETTER),
   FAEI(
         "\u05E4",
         " 05E4",
         "fäi",
         NikudLetterDistinction.LETTER),
   FAEISSOFIT(
         "\u05E3",
         " 05E3",
         "fäissofit",
         NikudLetterDistinction.LETTER),
   MEM(
         "\u05DE",
         " 05DE",
         "mem",
         NikudLetterDistinction.LETTER),
   MEMSSOFIT(
         "\u05DD",
         " 05DD",
         "memssofit",
         NikudLetterDistinction.LETTER),
   NUN(
         "\u05E0",
         " 05E0",
         "nun",
         NikudLetterDistinction.LETTER),
   NUNSSOFIT(
         "\u05DF",
         " 05DF",
         "nunssofit",
         NikudLetterDistinction.LETTER),
   SHEVA("\u05B0",
         " 05B0",
         "schwa = e",
         NikudLetterDistinction.LOWER_PUNKTATION),
   HATAF_SEGOL("\u05B1",
         " 05B1",
         "chataf szegol = e",
         NikudLetterDistinction.LOWER_PUNKTATION),
   HATAF_PATAH("\u05B2",
         " 05B2",
         "chataf patach = a",
         NikudLetterDistinction.LOWER_PUNKTATION),
   HATAF_QAMATS("\u05B3",
         " 05B3",
         "chataf kamatz = o",
         NikudLetterDistinction.LOWER_PUNKTATION),
   HIRIQ("\u05B4",
         " 05B4",
         "chirik = i",
         NikudLetterDistinction.LOWER_PUNKTATION),
   TSERE("\u05B5",
         " 05B5",
         "zeré = e",
         NikudLetterDistinction.LOWER_PUNKTATION),
   SEGOL("\u05B6",
         " 05B6",
         "szegol = e",
         NikudLetterDistinction.LOWER_PUNKTATION),
   PATAH("\u05B7",
         " 05B7",
         "patach = a",
         NikudLetterDistinction.LOWER_PUNKTATION),
   QAMATS("\u05B8",
         " 05B8",
         "kamatz = a",
         NikudLetterDistinction.LOWER_PUNKTATION),
   HOLAM("\u05B9",
         " 05B9",
         "cholam = o",
         NikudLetterDistinction.UPPER_PUNKTATION),
   HOLAM_HASER("\u05BA",
         " 05BA",
         "cholam chaser",
         NikudLetterDistinction.UPPER_PUNKTATION),
   QUBUTS("\u05BB",
         " 05BB",
         "kubutz = u",
         NikudLetterDistinction.LOWER_PUNKTATION),
   DAGESH("\u05BC",
         " 05BC",
         "dagesch/schuruk",
         NikudLetterDistinction.MIDDLE_PUNKTATION),
   METEG("\u05BD",
         " 05BD",
         "meteg",
         NikudLetterDistinction.LOWER_PUNKTATION),
   MAQAF("\u05BE",
         " 05BE",
         "makaf",
         NikudLetterDistinction.LETTER),
   RAFE("\u05BF",
         " 05BF",
         "rafi",
         NikudLetterDistinction.UPPER_PUNKTATION),
   PASEQ("\u05C0",
         " 05C0",
         "pasek",
         NikudLetterDistinction.LETTER),
   SHIN_DOT("\u05C1",
         " 05C1",
         "schin Punkt",
         NikudLetterDistinction.UPPER_PUNKTATION),
   SIN_DOT("\u05C2",
         " 05C2",
         "sin Punkt",
         NikudLetterDistinction.UPPER_PUNKTATION),
   SOF_PASUQ("\u05C3",
         " 05C3",
         "sof pasuk",
         NikudLetterDistinction.LETTER),
   UPPER_DOT("\u05C4",
         " 05C4",
         "oberer Punkt",
         NikudLetterDistinction.UPPER_PUNKTATION),
   LOWER_DOT("\u05C5",
         " 05C5",
         "unterer Punkt",
         NikudLetterDistinction.LOWER_PUNKTATION),
   HAFUKAH("\u05C6",
         " 05C6",
         "chafukach",
         NikudLetterDistinction.LETTER),
   QAMATS_QATAN("\u05C7",
         " 05C7",
         "kamatz katan",
         NikudLetterDistinction.LOWER_PUNKTATION),
   JIDDISH_DOUBLE_WAW("\u05F0",
         " 05F0",
         "jiddisch doppeltes waw",
         NikudLetterDistinction.LETTER),
   JIDDISH_WAW_JOD("\u05F1",
         " 05F1",
         "jiddisch waw jod",
         NikudLetterDistinction.LETTER),
   JIDDISH_DOUBLE_JOD("\u05F2",
         " 05F2",
         "jiddisch doppeltes jod",
         NikudLetterDistinction.LETTER);

   private String unicode;
   private String code;
   private String transcript;
   private NikudLetterDistinction distinction;

   NikudLetter(String unicode, String code, String transcript, NikudLetterDistinction distinction)
   {
      this.unicode = unicode;
      this.code = code;
      this.transcript = transcript;
      this.distinction = distinction;
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

   public NikudLetterDistinction getDistinction()
   {
      return distinction;
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
