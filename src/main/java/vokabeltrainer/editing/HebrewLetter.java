package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

public enum HebrewLetter
{
   AIN(
         "\u05E2",
         "Ameise",
         " 05E2",
         "ain",
         18,
         "&#1506;"),
   ALEF(
         "\u05D0",
         "Apfel",
         " 05D0",
         "alef",
         18,
         "&#1488;"),
   CHET(
         "\u05D7",
         "China",
         " 05D7",
         "chet",
         17,
         "&#1499;"),
   DALET(
         "\u05D3",
         "Domino",
         " 05D3",
         "dalet",
         17,
         "&#1491;"),
   GIMEL(
         "\u05D2",
         "Gans",
         " 05D2",
         "gimel",
         10,
         "&#1490;"),
   GERESCH(
         "\u05F3",
         "geresch",
         " 05F3",
         "geresch",
         9,
         "\u05F3"),
   GERSCHAYIM(
         "\u05F4",
         "gerschayim",
         " 05F4",
         "gerschayim",
         14,
         "\u05F4"),
   HAEI(
         "\u05D4",
         "Haus",
         " 05D4",
         "häi",
         17,
         "&#1492;"),
   JOD(
         "\u05D9",
         "Igel",
         " 05D9",
         "jod",
         10,
         "&#1497;"),
   KUF(
         "\u05E7",
         "Kuh",
         " 05E7",
         "kuf",
         17,
         "&#1511;"),
   LAMED(
         "\u05DC",
         "Laterne",
         " 05DC",
         "lamed",
         17,
         "&#1500;"),
   RESCH(
         "\u05E8",
         "Rad",
         " 05E8",
         "resch",
         17,
         "&#1512; "),
   SSIN(
         "\uFb2B",
         "Sandalen",
         " Fb2B",
         "ssin",
         18,
         "&#64299;"),
   SSAIN(
         "\u05D6",
         "Sand",
         " 05D6",
         "ssain",
         8,
         "&#1494;"),
   SCHIN(
         "\u05E9",
         "Schaukel",
         " 05E9",
         "schin",
         18,
         "&#64298;"),
   SSAMECH(
         "\u05E1",
         "Sonne",
         " 05E1",
         "ssamech",
         17,
         "&#1505;"),
   SPACE(
         "\u0020",
         "space",
         " 0020",
         "space",
         10,
         "\u05EA"),
   TAW(
         "\u05EA",
         "Tannen",
         " 05EA",
         "taw",
         17,
         "&#1514;"),
   TET(
         "\u05D8",
         "Tanz",
         " 05D8",
         "tet",
         17,
         "&#1496;"),
   WET(
         "\u05D1",
         "Wein",
         " 05D1",
         "wet",
         16,
         "&#1489;"),
   WAW(
         "\u05D5",
         "Welle",
         " 05D5",
         "waw",
         8,
         "&#1493;"),
   ZADI(
         "\u05E6",
         "Zitrone",
         " 05E6",
         "zadi",
         18,
         "&#1510;"),
   ZADISSOFIT(
         "\u05E5",
         "Zitrone",
         " 05E5",
         "zadissofit",
         18,
         "&#1509;"),
   CHAF(
         "\u05DB",
         "Chemie",
         " 05DB",
         "chaf",
         17,
         "&#1499; "),
   CHAFSSOFIT(
         "\u05DA",
         "Chemie",
         " 05DA",
         "chafssofit",
         17,
         "&#1498;"),
   FAEI(
         "\u05E4",
         "Feder",
         " 05E4",
         "fäi",
         17,
         "&#1508;"),
   FAEISSOFIT(
         "\u05E3",
         "Feder",
         " 05E3",
         "fäissofit",
         17,
         "&#1507;"),
   MEM(
         "\u05DE",
         "Maus",
         " 05DE",
         "mem",
         17,
         "&#1502; "),
   MEMSSOFIT(
         "\u05DD",
         "Maus",
         " 05DD",
         "memssofit",
         16,
         "&#1501;"),
   NUN(
         "\u05E0",
         "Nonne",
         " 05E0",
         "nun",
         9,
         "&#1504;"),
   NUNSSOFIT(
         "\u05DF",
         "Nonne",
         " 05DF",
         "nunssofit",
         9,
         "&#1503;"),
   BET(
         "\u05D1\u05BC",
         "Bett",
         " 05D1 05BC",
         "bet",
         16,
         "&#64305;"),
   KAF(
         "\u05DB\u05BC",
         "Kuchen",
         " 05DB 05BC",
         "kaf",
         17,
         "&#64315;"),
   PAEI(
         "\u05E4\u05BC",
         "Post",
         " 05E4 05BC",
         "päi",
         17,
         "&#64324;"),
   QUESTIONMARK(
         "\u003F",
         "Frage",
         " 003F",
         "?",
         17,
         "?");

   private String unicode;
   private String german;
   private String code;
   private String transcript;
   private int pixelWidth;
   private String htmlcode;

   HebrewLetter(String unicode, String german, String code, String transcript,
         int pixelWidth, String htmlcode)
   {
      this.unicode = unicode;
      this.german = german;
      this.code = code;
      this.transcript = transcript;
      this.pixelWidth = pixelWidth;
      this.htmlcode = htmlcode;
   }

   public static String getLetterUnicode(HebrewLetter nameHebrew)
   {
      return nameHebrew.getUnicode();
   }

   public static HebrewLetter getLetter(String code)
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

   public String getUnicode()
   {
      return unicode;
   }

   public String getGerman()
   {
      return german;
   }

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

   public String getHtmlcode()
   {
      return htmlcode;
   }

   public static String getPatternString(boolean withComma)
   {
      StringJoiner joiner = new StringJoiner(",");
      for (HebrewLetter letter : HebrewLetter.values())
      {
         joiner.add(letter.code);
      }
      joiner.add(" 0021"); // !
      joiner.add(" 002E"); // .
      if (withComma)
      {
         joiner.add(" 002C"); // ,
      }
      return joiner.toString();
   }

   public static List<String> findLetters(String hebrewWord)
   {
      List<String> letters = new ArrayList<>();
      for (int i = 0, c = 0; i < hebrewWord.length()
            && c < hebrewWord.length();)
      {
         try
         {
            String code = String.format(" %04x", (int) hebrewWord.charAt(c));
            if (i == 0 && (code.equalsIgnoreCase(" 05BC")
                  || code.equalsIgnoreCase(" 05c2")))
            {
               i++;
               c++;
               continue; // wrong spelling, dagesch and ssin dot can not be in
                         // the beginning
            }

            if (code.equalsIgnoreCase(" 05BC")) // dagesch
            {
               letters.set(i - 1, letters.get(i - 1) + code);
               c++;
            }
            else if (code.equalsIgnoreCase(" 05c2")) // ssin dot
            {
               letters.set(i - 1, " Fb2B");
               c++;
            }
            else
            {
               letters.add(code);
               i++;
               c++;
            }
         }
         catch (Exception e)
         {
            c++;
         }
      }
      return letters;
   }

   public static List<HebrewLetter> findHebrewLetters(String hebrewWord)
   {
      List<String> letters = new ArrayList<>();
      for (int i = 0, c = 0; i < hebrewWord.length()
            && c < hebrewWord.length();)
      {
         try
         {
            String code = String.format(" %04x", (int) hebrewWord.charAt(c));
            if (i == 0 && (code.equalsIgnoreCase(" 05BC")
                  || code.equalsIgnoreCase(" 05c2")))
            {
               i++;
               c++;
               continue; // wrong spelling, dagesch and ssin dot can not be in
                         // the beginning
            }

            if (code.equalsIgnoreCase(" 05BC")) // dagesch
            {
               letters.set(i - 1, letters.get(i - 1) + code);
               c++;
            }
            else if (code.equalsIgnoreCase(" 05c2")) // ssin dot
            {
               letters.set(i - 1, " Fb2B");
               c++;
            }
            else
            {
               letters.add(code);
               i++;
               c++;
            }
         }
         catch (Exception e)
         {
            c++;
         }
      }

      List<HebrewLetter> hebrewLetters = new ArrayList<>();
      for (String letter : letters)
      {
         HebrewLetter hebrewLetter = HebrewLetter.getLetter(letter);
         if (hebrewLetter != null)
         {
            hebrewLetters.add(hebrewLetter);
         }
      }
      return hebrewLetters;
   }
}
