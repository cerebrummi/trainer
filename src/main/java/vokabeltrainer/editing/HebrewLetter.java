package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

public enum HebrewLetter implements Letter
{
   AIN(
         "\u05E2",
         "Ameise",
         " 05E2",
         "ain",
         18),
   ALEF(
         "\u05D0",
         "Apfel",
         " 05D0",
         "alef",
         18),
   CHET(
         "\u05D7",
         "China",
         " 05D7",
         "chet",
         18),
   DALET(
         "\u05D3",
         "Domino",
         " 05D3",
         "dalet",
         18),
   GIMEL(
         "\u05D2",
         "Gans",
         " 05D2",
         "gimel",
         11),
   GERESCH(
         "\u05F3",
         "geresch",
         " 05F3",
         "geresch",
         9),
   GERSCHAYIM(
         "\u05F4",
         "gerschayim",
         " 05F4",
         "gerschayim",
         14),
   HAEI(
         "\u05D4",
         "Haus",
         " 05D4",
         "häi",
         18),
   JOD(
         "\u05D9",
         "Igel",
         " 05D9",
         "jod",
         10),
   KUF(
         "\u05E7",
         "Kuh",
         " 05E7",
         "kuf",
         18),
   LAMED(
         "\u05DC",
         "Laterne",
         " 05DC",
         "lamed",
         17),
   RESCH(
         "\u05E8",
         "Rad",
         " 05E8",
         "resch",
         17),
   SSIN(
         "\uFb2B",
         "Sandalen",
         " Fb2B",
         "ssin",
         19),
   SSAIN(
         "\u05D6",
         "Sand",
         " 05D6",
         "ssain",
         9),
   SCHIN(
         "\u05E9",
         "Schaukel",
         " 05E9",
         "schin",
         19),
   SSAMECH(
         "\u05E1",
         "Sonne",
         " 05E1",
         "ssamech",
         17),
   SPACE(
         "\u0020",
         "space",
         " 0020",
         "space",
         10),
   NEWSPACE(
         "\u00A0",
         "newspace",
         " 00A0",
         "space",
         10),
   TAW(
         "\u05EA",
         "Tannen",
         " 05EA",
         "taw",
         17),
   TET(
         "\u05D8",
         "Tanz",
         " 05D8",
         "tet",
         17),
   WET(
         "\u05D1",
         "Wein",
         " 05D1",
         "wet",
         18),
   WAW(
         "\u05D5",
         "Welle",
         " 05D5",
         "waw",
         9),
   ZADI(
         "\u05E6",
         "Zitrone",
         " 05E6",
         "zadi",
         18),
   ZADISSOFIT(
         "\u05E5",
         "Zitrone",
         " 05E5",
         "zadissofit",
         18),
   CHAF(
         "\u05DB",
         "Chemie",
         " 05DB",
         "chaf",
         17),
   CHAFSSOFIT(
         "\u05DA",
         "Chemie",
         " 05DA",
         "chafssofit",
         18),
   FAEI(
         "\u05E4",
         "Feder",
         " 05E4",
         "fäi",
         17),
   FAEISSOFIT(
         "\u05E3",
         "Feder",
         " 05E3",
         "fäissofit",
         18),
   MEM(
         "\u05DE",
         "Maus",
         " 05DE",
         "mem",
         17),
   MEMSSOFIT(
         "\u05DD",
         "Maus",
         " 05DD",
         "memssofit",
         16),
   NUN(
         "\u05E0",
         "Nonne",
         " 05E0",
         "nun",
         10),
   NUNSSOFIT(
         "\u05DF",
         "Nonne",
         " 05DF",
         "nunssofit",
         9),
   BET(
         "\u05D1\u05BC",
         "Bett",
         " 05D1 05BC",
         "bet",
         18),
   KAF(
         "\u05DB\u05BC",
         "Kuchen",
         " 05DB 05BC",
         "kaf",
         17),
   PAEI(
         "\u05E4\u05BC",
         "Post",
         " 05E4 05BC",
         "päi",
         17);

   private String unicode;
   private String german;
   private String code;
   private String transcript;
   private int pixelWidth;

   HebrewLetter(String unicode, String german, String code, String transcript,
         int pixelWidth)
   {
      this.unicode = unicode;
      this.german = german;
      this.code = code;
      this.transcript = transcript;
      this.pixelWidth = pixelWidth;
   }

   public static String getLetterUnicode(HebrewLetter nameHebrew)
   {
      return nameHebrew.getUnicode();
   }

   public static HebrewLetter getLetterFromCode(String code)
   {
      for (HebrewLetter letter : HebrewLetter.values())
      {
         if (StringUtils.containsIgnoreCase(letter.getCode(), code))
         {
            return letter;
         }
      }
      return null;
   }

   @Override
   public String getUnicode()
   {
      return unicode;
   }

   public String getGerman()
   {
      return german;
   }

   @Override
   public String getCode()
   {
      return code;
   }

   public String getTranscript()
   {
      return transcript;
   }

   public int getPixelWidth()
   {
      return pixelWidth;
   }

   public static String getLetterPatternString()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (HebrewLetter letter : HebrewLetter.values())
      {
         if (letter != GERSCHAYIM && letter != GERESCH && letter != NEWSPACE
               && letter != SPACE)
         {
            joiner.add(letter.code);
            joiner.add(letter.code.toLowerCase());
         }
      }
      return joiner.toString();
   }

   public static List<HebrewLetter> findHebrewLetters(String hebrewWord)
   {
      List<String> letterCodes = LetterHelper.findLetterCodes(hebrewWord);
      List<HebrewLetter> hebrewLetters = new ArrayList<>();
      for (String code : letterCodes)
      {
         HebrewLetter hebrewLetter = HebrewLetter.getLetterFromCode(code);
         if (hebrewLetter != null)
         {
            hebrewLetters.add(hebrewLetter);
         }
      }
      return hebrewLetters;
   }

   @Override
   public boolean isNewspace()
   {
      return HebrewLetter.NEWSPACE == this;
   }

   @Override
   public boolean isSpace()
   {
      return HebrewLetter.SPACE == this;
   }
   
   @Override
   public LetterType isType()
   {
      return LetterType.HEBREW;
   }

   @Override
   public Letter getNewspace()
   {
      return HebrewLetter.NEWSPACE;
   }

   @Override
   public HebrewLetter getHebrewLetter()
   {
      return this;
   }
}
