package vokabeltrainer.editing;

import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

public enum NikudLetter
      implements
      Letter
{
   AIN(
         "\u05E2",
         " 05E2",
         "ain",
         "Ameise",
         NikudLetterDistinction.LETTER,
         18,
         true),
   ALEF(
         "\u05D0",
         " 05D0",
         "alef",
         "Apfel",
         NikudLetterDistinction.LETTER,
         18,
         true),
   CHET(
         "\u05D7",
         " 05D7",
         "chet",
         "China",
         NikudLetterDistinction.LETTER,
         18,
         true),
   DALET(
         "\u05D3",
         " 05D3",
         "dalet",
         "Domino",
         NikudLetterDistinction.LETTER,
         18,
         true),
   GIMEL(
         "\u05D2",
         " 05D2",
         "gimel",
         "Gans",
         NikudLetterDistinction.LETTER,
         11,
         true),
   GERESCH(
         "\u05F3",
         " 05F3",
         "geresch",
         "",
         NikudLetterDistinction.LETTER,
         9,
         false),
   GERSCHAYIM(
         "\u05F4",
         " 05F4",
         "gerschayim",
         "",
         NikudLetterDistinction.LETTER,
         14,
         false),
   HAEI(
         "\u05D4",
         " 05D4",
         "häi",
         "Haus",
         NikudLetterDistinction.LETTER,
         18,
         true),
   JOD(
         "\u05D9",
         " 05D9",
         "jod",
         "Igel",
         NikudLetterDistinction.LETTER,
         10,
         true),
   KUF(
         "\u05E7",
         " 05E7",
         "kuf",
         "Kuh",
         NikudLetterDistinction.LETTER,
         18,
         true),
   LAMED(
         "\u05DC",
         " 05DC",
         "lamed",
         "Laterne",
         NikudLetterDistinction.LETTER,
         17,
         true),
   RESCH(
         "\u05E8",
         " 05E8",
         "resch",
         "Rad",
         NikudLetterDistinction.LETTER,
         17,
         true),
   SSAIN(
         "\u05D6",
         " 05D6",
         "ssain",
         "Sand",
         NikudLetterDistinction.LETTER,
         9,
         true),
   SCHIN(
         "\u05E9",
         " 05E9",
         "schin",
         "Schaukel",
         NikudLetterDistinction.LETTER,
         19,
         true),
   SSAMECH(
         "\u05E1",
         " 05E1",
         "ssamech",
         "Sonne",
         NikudLetterDistinction.LETTER,
         17,
         true),
   SPACE(
         "\u0020",
         " 0020",
         "space",
         "",
         NikudLetterDistinction.LETTER,
         10,
         false),
   NEWSPACE(
         "\u00A0",
         " 00A0",
         "newspace",
         "",
         NikudLetterDistinction.LETTER,
         10,
         false),
   TAW(
         "\u05EA",
         " 05EA",
         "taw",
         "Tannen",
         NikudLetterDistinction.LETTER,
         17,
         true),
   TET(
         "\u05D8",
         " 05D8",
         "tet",
         "Tanz",
         NikudLetterDistinction.LETTER,
         17,
         true),
   BET(
         "\u05D1",
         " 05D1",
         "bet",
         "Bett",
         NikudLetterDistinction.LETTER,
         18,
         true),
   WAW(
         "\u05D5",
         " 05D5",
         "waw",
         "Welle",
         NikudLetterDistinction.LETTER,
         9,
         true),
   ZADI(
         "\u05E6",
         " 05E6",
         "zadi",
         "Zitrone",
         NikudLetterDistinction.LETTER,
         18,
         true),
   ZADISSOFIT(
         "\u05E5",
         " 05E5",
         "zadissofit",
         "Zitrone",
         NikudLetterDistinction.LETTER,
         18,
         true),
   KAF(
         "\u05DB",
         " 05DB",
         "kaf",
         "Kuchen",
         NikudLetterDistinction.LETTER,
         17,
         true),
   CHAFSSOFIT(
         "\u05DA",
         " 05DA",
         "chafssofit",
         "Chemie",
         NikudLetterDistinction.LETTER,
         18,
         true),
   PAEI(
         "\u05E4",
         " 05E4",
         "päi",
         "Post",
         NikudLetterDistinction.LETTER,
         17,
         true),
   FAEISSOFIT(
         "\u05E3",
         " 05E3",
         "fäissofit",
         "Feder",
         NikudLetterDistinction.LETTER,
         18,
         true),
   MEM(
         "\u05DE",
         " 05DE",
         "mem",
         "Maus",
         NikudLetterDistinction.LETTER,
         17,
         true),
   MEMSSOFIT(
         "\u05DD",
         " 05DD",
         "memssofit",
         "Maus",
         NikudLetterDistinction.LETTER,
         16,
         true),
   NUN(
         "\u05E0",
         " 05E0",
         "nun",
         "Nonne",
         NikudLetterDistinction.LETTER,
         10,
         true),
   NUNSSOFIT(
         "\u05DF",
         " 05DF",
         "nunssofit",
         "Nonne",
         NikudLetterDistinction.LETTER,
         9,
         true),
   SHEVA(
         "\u05B0",
         " 05B0",
         "schwa = e",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HATAF_SEGOL(
         "\u05B1",
         " 05B1",
         "chataf szegol = e",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HATAF_PATAH(
         "\u05B2",
         " 05B2",
         "chataf patach = a",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HATAF_QAMATS(
         "\u05B3",
         " 05B3",
         "chataf kamatz = o",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HIRIQ(
         "\u05B4",
         " 05B4",
         "chirik = i",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   TSERE(
         "\u05B5",
         " 05B5",
         "zeré = e",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   SEGOL(
         "\u05B6",
         " 05B6",
         "szegol = e",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   PATAH(
         "\u05B7",
         " 05B7",
         "patach = a",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   QAMATS(
         "\u05B8",
         " 05B8",
         "kamatz = a",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HOLAM(
         "\u05B9",
         " 05B9",
         "cholam = o",
         "",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   HOLAM_HASER(
         "\u05BA",
         " 05BA",
         "cholam chaser",
         "",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   QUBUTS(
         "\u05BB",
         " 05BB",
         "kubutz = u",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   DAGESH(
         "\u05BC",
         " 05BC",
         "dagesch/schuruk",
         "",
         NikudLetterDistinction.MIDDLE_PUNKTATION,
         0,
         false),
   METEG(
         "\u05BD",
         " 05BD",
         "meteg",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   MAQAF(
         "\u05BE",
         " 05BE",
         "makaf",
         "",
         NikudLetterDistinction.LETTER,
         14,
         false),
   RAFE(
         "\u05BF",
         " 05BF",
         "rafi",
         "",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   PASEQ(
         "\u05C0",
         " 05C0",
         "pasek",
         "",
         NikudLetterDistinction.LETTER,
         4,
         false),
   SHIN_DOT(
         "\u05C1",
         " 05C1",
         "schin Punkt",
         "",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   SIN_DOT(
         "\u05C2",
         " 05C2",
         "sin Punkt",
         "",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   SOF_PASUQ(
         "\u05C3",
         " 05C3",
         "sof pasuk",
         "",
         NikudLetterDistinction.LETTER,
         8,
         false),
   UPPER_DOT(
         "\u05C4",
         " 05C4",
         "oberer Punkt",
         "",
         NikudLetterDistinction.UPPER_PUNKTATION,
         0,
         false),
   LOWER_DOT(
         "\u05C5",
         " 05C5",
         "unterer Punkt",
         null,
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   HAFUKAH(
         "\u05C6",
         " 05C6",
         "chafukach",
         "",
         NikudLetterDistinction.LETTER,
         10,
         false),
   QAMATS_QATAN(
         "\u05C7",
         " 05C7",
         "kamatz katan",
         "",
         NikudLetterDistinction.LOWER_PUNKTATION,
         0,
         false),
   JIDDISH_DOUBLE_WAW(
         "\u05F0",
         " 05F0",
         "waw waw",
         "",
         NikudLetterDistinction.LETTER,
         19,
         false),
   JIDDISH_WAW_JOD(
         "\u05F1",
         " 05F1",
         "waw jod",
         "",
         NikudLetterDistinction.LETTER,
         20,
         false),
   JIDDISH_DOUBLE_JOD(
         "\u05F2",
         " 05F2",
         "jod jod",
         "",
         NikudLetterDistinction.LETTER,
         21,
         false);

   private String unicode;
   private String code;
   private String transcript;
   private String germanPictureName;
   private NikudLetterDistinction distinction;
   private int pixelWidth;
   private boolean handwritten;

   NikudLetter(String unicode, String code, String transcript, String germanPictureName,
         NikudLetterDistinction distinction, int pixelWidth, boolean handwritten)
   {
      this.unicode = unicode;
      this.code = code;
      this.transcript = transcript;
      this.germanPictureName = germanPictureName;
      this.distinction = distinction;
      this.pixelWidth = pixelWidth;
      this.handwritten = handwritten;
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

   public boolean isHandwritten()
   {
      return handwritten;
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

   public static String getLetterPatternStringForSingleLetterDocument()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (NikudLetter letter : NikudLetter.values())
      {
         if (NikudLetterDistinction.LETTER == letter.getDistinction()
               && letter != GERSCHAYIM && letter != GERESCH
               && letter != NEWSPACE && letter != SPACE)
         {
            joiner.add(letter.code);
            joiner.add(letter.code.toLowerCase());
         }
      }
      return joiner.toString();
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
      return LetterType.HEBREW;
   }

   @Override
   public Letter getNewspace()
   {
      return NikudLetter.NEWSPACE;
   }

   public String getGermanPictureName()
   {
      return germanPictureName;
   }
}
