package vokabeltrainer.editing;

import org.apache.commons.lang3.StringUtils;

public enum NikudLetter
      implements
      Letter
{
   AIN(
         "\u05E2",
         " 05E2",
         "ain",
         NikudLetterDistinction.LETTER,
         HebrewLetter.AIN,
         18),
   ALEF(
         "\u05D0",
         " 05D0",
         "alef",
         NikudLetterDistinction.LETTER,
         HebrewLetter.ALEF,
         18),
   CHET(
         "\u05D7",
         " 05D7",
         "chet",
         NikudLetterDistinction.LETTER,
         HebrewLetter.CHET,
         18),
   DALET(
         "\u05D3",
         " 05D3",
         "dalet",
         NikudLetterDistinction.LETTER,
         HebrewLetter.DALET,
         18),
   GIMEL(
         "\u05D2",
         " 05D2",
         "gimel",
         NikudLetterDistinction.LETTER,
         HebrewLetter.GIMEL,
         11),
   GERESCH(
         "\u05F3",
         " 05F3",
         "geresch",
         NikudLetterDistinction.LETTER,
         HebrewLetter.GERESCH,
         9),
   GERSCHAYIM(
         "\u05F4",
         " 05F4",
         "gerschayim",
         NikudLetterDistinction.LETTER,
         HebrewLetter.GERSCHAYIM,
         14),
   HAEI(
         "\u05D4",
         " 05D4",
         "häi",
         NikudLetterDistinction.LETTER,
         HebrewLetter.HAEI,
         18),
   JOD(
         "\u05D9",
         " 05D9",
         "jod",
         NikudLetterDistinction.LETTER,
         HebrewLetter.JOD,
         10),
   KUF(
         "\u05E7",
         " 05E7",
         "kuf",
         NikudLetterDistinction.LETTER,
         HebrewLetter.KUF,
         18),
   LAMED(
         "\u05DC",
         " 05DC",
         "lamed",
         NikudLetterDistinction.LETTER,
         HebrewLetter.LAMED,
         17),
   RESCH(
         "\u05E8",
         " 05E8",
         "resch",
         NikudLetterDistinction.LETTER,
         HebrewLetter.RESCH,
         17),
   SSAIN(
         "\u05D6",
         " 05D6",
         "ssain",
         NikudLetterDistinction.LETTER,
         HebrewLetter.SSAIN,
         9),
   SCHIN(
         "\u05E9",
         " 05E9",
         "schin",
         NikudLetterDistinction.LETTER,
         HebrewLetter.SCHIN,
         19),
   SSAMECH(
         "\u05E1",
         " 05E1",
         "ssamech",
         NikudLetterDistinction.LETTER,
         HebrewLetter.SSAMECH,
         17),
   SPACE(
         "\u0020",
         " 0020",
         "space",
         NikudLetterDistinction.LETTER,
         HebrewLetter.SPACE,
         10),
   NEWSPACE(
         "\u00A0",
         " 00A0",
         "newspace",
         NikudLetterDistinction.LETTER,
         HebrewLetter.NEWSPACE,
         10),
   TAW(
         "\u05EA",
         " 05EA",
         "taw",
         NikudLetterDistinction.LETTER,
         HebrewLetter.TAW,
         17),
   TET(
         "\u05D8",
         " 05D8",
         "tet",
         NikudLetterDistinction.LETTER,
         HebrewLetter.TET,
         17),
   WET(
         "\u05D1",
         " 05D1",
         "wet",
         NikudLetterDistinction.LETTER,
         HebrewLetter.WET,
         18),
   WAW(
         "\u05D5",
         " 05D5",
         "waw",
         NikudLetterDistinction.LETTER,
         HebrewLetter.WAW,
         9),
   ZADI(
         "\u05E6",
         " 05E6",
         "zadi",
         NikudLetterDistinction.LETTER,
         HebrewLetter.ZADI,
         18),
   ZADISSOFIT(
         "\u05E5",
         " 05E5",
         "zadissofit",
         NikudLetterDistinction.LETTER,
         HebrewLetter.ZADISSOFIT,
         18),
   CHAF(
         "\u05DB",
         " 05DB",
         "chaf",
         NikudLetterDistinction.LETTER,
         HebrewLetter.CHAF,
         17),
   CHAFSSOFIT(
         "\u05DA",
         " 05DA",
         "chafssofit",
         NikudLetterDistinction.LETTER,
         HebrewLetter.CHAFSSOFIT,
         18),
   FAEI(
         "\u05E4",
         " 05E4",
         "fäi",
         NikudLetterDistinction.LETTER,
         HebrewLetter.FAEI,
         17),
   FAEISSOFIT(
         "\u05E3",
         " 05E3",
         "fäissofit",
         NikudLetterDistinction.LETTER,
         HebrewLetter.FAEISSOFIT,
         18),
   MEM(
         "\u05DE",
         " 05DE",
         "mem",
         NikudLetterDistinction.LETTER,
         HebrewLetter.MEM,
         17),
   MEMSSOFIT(
         "\u05DD",
         " 05DD",
         "memssofit",
         NikudLetterDistinction.LETTER,
         HebrewLetter.MEMSSOFIT,
         16),
   NUN(
         "\u05E0",
         " 05E0",
         "nun",
         NikudLetterDistinction.LETTER,
         HebrewLetter.NUN,
         10),
   NUNSSOFIT(
         "\u05DF",
         " 05DF",
         "nunssofit",
         NikudLetterDistinction.LETTER,
         HebrewLetter.NUNSSOFIT,
         9),
   SHEVA(
         "\u05B0",
         " 05B0",
         "schwa = e",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   HATAF_SEGOL(
         "\u05B1",
         " 05B1",
         "chataf szegol = e",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   HATAF_PATAH(
         "\u05B2",
         " 05B2",
         "chataf patach = a",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   HATAF_QAMATS(
         "\u05B3",
         " 05B3",
         "chataf kamatz = o",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   HIRIQ(
         "\u05B4",
         " 05B4",
         "chirik = i",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   TSERE(
         "\u05B5",
         " 05B5",
         "zeré = e",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   SEGOL(
         "\u05B6",
         " 05B6",
         "szegol = e",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   PATAH(
         "\u05B7",
         " 05B7",
         "patach = a",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   QAMATS(
         "\u05B8",
         " 05B8",
         "kamatz = a",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   HOLAM(
         "\u05B9",
         " 05B9",
         "cholam = o",
         NikudLetterDistinction.UPPER_PUNKTATION,
         null,
         0),
   HOLAM_HASER(
         "\u05BA",
         " 05BA",
         "cholam chaser",
         NikudLetterDistinction.UPPER_PUNKTATION,
         null,
         0),
   QUBUTS(
         "\u05BB",
         " 05BB",
         "kubutz = u",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   DAGESH(
         "\u05BC",
         " 05BC",
         "dagesch/schuruk",
         NikudLetterDistinction.MIDDLE_PUNKTATION,
         null,
         0),
   METEG(
         "\u05BD",
         " 05BD",
         "meteg",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   MAQAF(
         "\u05BE",
         " 05BE",
         "makaf",
         NikudLetterDistinction.LETTER,
         null,
         14),
   RAFE(
         "\u05BF",
         " 05BF",
         "rafi",
         NikudLetterDistinction.UPPER_PUNKTATION,
         null,
         0),
   PASEQ(
         "\u05C0",
         " 05C0",
         "pasek",
         NikudLetterDistinction.LETTER,
         null,
         4),
   SHIN_DOT(
         "\u05C1",
         " 05C1",
         "schin Punkt",
         NikudLetterDistinction.UPPER_PUNKTATION,
         null,
         0),
   SIN_DOT(
         "\u05C2",
         " 05C2",
         "sin Punkt",
         NikudLetterDistinction.UPPER_PUNKTATION,
         null,
         0),
   SOF_PASUQ(
         "\u05C3",
         " 05C3",
         "sof pasuk",
         NikudLetterDistinction.LETTER,
         null,
         8),
   UPPER_DOT(
         "\u05C4",
         " 05C4",
         "oberer Punkt",
         NikudLetterDistinction.UPPER_PUNKTATION,
         null,
         0),
   LOWER_DOT(
         "\u05C5",
         " 05C5",
         "unterer Punkt",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   HAFUKAH(
         "\u05C6",
         " 05C6",
         "chafukach",
         NikudLetterDistinction.LETTER,
         null,
         10),
   QAMATS_QATAN(
         "\u05C7",
         " 05C7",
         "kamatz katan",
         NikudLetterDistinction.LOWER_PUNKTATION,
         null,
         0),
   JIDDISH_DOUBLE_WAW(
         "\u05F0",
         " 05F0",
         "waw waw",
         NikudLetterDistinction.LETTER,
         null,
         19),
   JIDDISH_WAW_JOD(
         "\u05F1",
         " 05F1",
         "waw jod",
         NikudLetterDistinction.LETTER,
         null,
         20),
   JIDDISH_DOUBLE_JOD(
         "\u05F2",
         " 05F2",
         "jod jod",
         NikudLetterDistinction.LETTER,
         null,
         21);

   private String unicode;
   private String code;
   private String transcript;
   private NikudLetterDistinction distinction;
   private HebrewLetter hebrewLetter;
   private int pixelWidth;

   NikudLetter(String unicode, String code, String transcript,
         NikudLetterDistinction distinction, HebrewLetter hebrewLetter,
         int pixelWidth)
   {
      this.unicode = unicode;
      this.code = code;
      this.transcript = transcript;
      this.distinction = distinction;
      this.hebrewLetter = hebrewLetter;
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

   public HebrewLetter getHebrewLetter()
   {
      return hebrewLetter;
   }
}
