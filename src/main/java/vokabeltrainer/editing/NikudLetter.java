package vokabeltrainer.editing;

import org.apache.commons.lang3.StringUtils;

public enum NikudLetter implements Letter
{
   AIN(
         "\u05E2",
         " 05E2",
         "ain",
         NikudLetterDistinction.LETTER,
         18),
   ALEF(
         "\u05D0",
         " 05D0",
         "alef",
         NikudLetterDistinction.LETTER,
         18),
   CHET(
         "\u05D7",
         " 05D7",
         "chet",
         NikudLetterDistinction.LETTER,
         17),
   DALET(
         "\u05D3",
         " 05D3",
         "dalet",
         NikudLetterDistinction.LETTER,
         17),
   GIMEL(
         "\u05D2",
         " 05D2",
         "gimel",
         NikudLetterDistinction.LETTER,
         10),
   GERESCH(
         "\u05F3",
         " 05F3",
         "geresch",
         NikudLetterDistinction.LETTER,
         9),
   GERSCHAYIM(
         "\u05F4",
         " 05F4",
         "gerschayim",
         NikudLetterDistinction.LETTER,
         14),
   HAEI(
         "\u05D4",
         " 05D4",
         "häi",
         NikudLetterDistinction.LETTER,
         17),
   JOD(
         "\u05D9",
         " 05D9",
         "jod",
         NikudLetterDistinction.LETTER,
         10),
   KUF(
         "\u05E7",
         " 05E7",
         "kuf",
         NikudLetterDistinction.LETTER,
         17),
   LAMED(
         "\u05DC",
         " 05DC",
         "lamed",
         NikudLetterDistinction.LETTER,
         17),
   RESCH(
         "\u05E8",
         " 05E8",
         "resch",
         NikudLetterDistinction.LETTER,
         17),
   SSAIN(
         "\u05D6",
         " 05D6",
         "ssain",
         NikudLetterDistinction.LETTER,
         8),
   SCHIN(
         "\u05E9",
         " 05E9",
         "schin",
         NikudLetterDistinction.LETTER,
         18),
   SSAMECH(
         "\u05E1",
         " 05E1",
         "ssamech",
         NikudLetterDistinction.LETTER,
         17),
   SPACE(
         "\u0020",
         " 0020",
         "space",
         NikudLetterDistinction.LETTER,
         10),
   NEWSPACE(
         "\u00A0",
         " 00A0",
         "newspace",
         NikudLetterDistinction.LETTER,
         10),
   TAW(
         "\u05EA",
         " 05EA",
         "taw",
         NikudLetterDistinction.LETTER,
         17),
   TET(
         "\u05D8",
         " 05D8",
         "tet",
         NikudLetterDistinction.LETTER,
         17),
   WET(
         "\u05D1",
         " 05D1",
         "wet",
         NikudLetterDistinction.LETTER,
         16),
   WAW(
         "\u05D5",
         " 05D5",
         "waw",
         NikudLetterDistinction.LETTER,
         8),
   ZADI(
         "\u05E6",
         " 05E6",
         "zadi",
         NikudLetterDistinction.LETTER,
         18),
   ZADISSOFIT(
         "\u05E5",
         " 05E5",
         "zadissofit",
         NikudLetterDistinction.LETTER,
         18),
   CHAF(
         "\u05DB",
         " 05DB",
         "chaf",
         NikudLetterDistinction.LETTER,
         17),
   CHAFSSOFIT(
         "\u05DA",
         " 05DA",
         "chafssofit",
         NikudLetterDistinction.LETTER,
         17),
   FAEI(
         "\u05E4",
         " 05E4",
         "fäi",
         NikudLetterDistinction.LETTER,
         17),
   FAEISSOFIT(
         "\u05E3",
         " 05E3",
         "fäissofit",
         NikudLetterDistinction.LETTER,
         17),
   MEM(
         "\u05DE",
         " 05DE",
         "mem",
         NikudLetterDistinction.LETTER,
         17),
   MEMSSOFIT(
         "\u05DD",
         " 05DD",
         "memssofit",
         NikudLetterDistinction.LETTER,
         16),
   NUN(
         "\u05E0",
         " 05E0",
         "nun",
         NikudLetterDistinction.LETTER,
         9),
   NUNSSOFIT(
         "\u05DF",
         " 05DF",
         "nunssofit",
         NikudLetterDistinction.LETTER,
         9),
   SHEVA("\u05B0",
         " 05B0",
         "schwa = e",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   HATAF_SEGOL("\u05B1",
         " 05B1",
         "chataf szegol = e",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   HATAF_PATAH("\u05B2",
         " 05B2",
         "chataf patach = a",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   HATAF_QAMATS("\u05B3",
         " 05B3",
         "chataf kamatz = o",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   HIRIQ("\u05B4",
         " 05B4",
         "chirik = i",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   TSERE("\u05B5",
         " 05B5",
         "zeré = e",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   SEGOL("\u05B6",
         " 05B6",
         "szegol = e",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   PATAH("\u05B7",
         " 05B7",
         "patach = a",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   QAMATS("\u05B8",
         " 05B8",
         "kamatz = a",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   HOLAM("\u05B9",
         " 05B9",
         "cholam = o",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0),
   HOLAM_HASER("\u05BA",
         " 05BA",
         "cholam chaser",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0),
   QUBUTS("\u05BB",
         " 05BB",
         "kubutz = u",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   DAGESH("\u05BC",
         " 05BC",
         "dagesch/schuruk",
         NikudLetterDistinction.MIDDLE_PUNKTATION,
         0),
   METEG("\u05BD",
         " 05BD",
         "meteg",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   MAQAF("\u05BE",
         " 05BE",
         "makaf",
         NikudLetterDistinction.LETTER,
         14),
   RAFE("\u05BF",
         " 05BF",
         "rafi",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0),
   PASEQ("\u05C0",
         " 05C0",
         "pasek",
         NikudLetterDistinction.LETTER,
         4),
   SHIN_DOT("\u05C1",
         " 05C1",
         "schin Punkt",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0),
   SIN_DOT("\u05C2",
         " 05C2",
         "sin Punkt",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0),
   SOF_PASUQ("\u05C3",
         " 05C3",
         "sof pasuk",
         NikudLetterDistinction.LETTER,
         8),
   UPPER_DOT("\u05C4",
         " 05C4",
         "oberer Punkt",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0),
   LOWER_DOT("\u05C5",
         " 05C5",
         "unterer Punkt",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   HAFUKAH("\u05C6",
         " 05C6",
         "chafukach",
         NikudLetterDistinction.LETTER,
         11),
   QAMATS_QATAN("\u05C7",
         " 05C7",
         "kamatz katan",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0),
   JIDDISH_DOUBLE_WAW("\u05F0",
         " 05F0",
         "jiddisch doppeltes waw",
         NikudLetterDistinction.LETTER,
         18),
   JIDDISH_WAW_JOD("\u05F1",
         " 05F1",
         "jiddisch waw jod",
         NikudLetterDistinction.LETTER,
         20),
   JIDDISH_DOUBLE_JOD("\u05F2",
         " 05F2",
         "jiddisch doppeltes jod",
         NikudLetterDistinction.LETTER,
         20);

   private String unicode;
   private String code;
   private String transcript;
   private NikudLetterDistinction distinction;
   private int pixelWidth;

   NikudLetter(String unicode, String code, String transcript, NikudLetterDistinction distinction, int pixelWidth)
   {
      this.unicode = unicode;
      this.code = code;
      this.transcript = transcript;
      this.distinction = distinction;
      this.pixelWidth = pixelWidth;
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

   public int getPixelWidth()
   {
      return pixelWidth;
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

   @Override
   public boolean isNewspace()
   {
      return NikudLetter.NEWSPACE == this;
   }

   @Override
   public boolean isSpace()
   {
      return NikudLetter.SPACE == this;
   }
   
   @Override
   public LetterType isType()
   {
      return LetterType.NIKUD;
   }

   @Override
   public Letter getNewspace()
   {
      return NikudLetter.NEWSPACE;
   }
}
