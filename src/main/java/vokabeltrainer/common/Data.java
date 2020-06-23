package vokabeltrainer.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.prefs.Preferences;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import vokabeltrainer.Command;
import vokabeltrainer.ExpressionComparator;
import vokabeltrainer.Settings;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTableModel;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTableRow;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Binjan;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.ExpressionKind;
import vokabeltrainer.types.Gender;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Numerus;
import vokabeltrainer.types.Repetition;
import vokabeltrainer.types.SearchType;
import vokabeltrainer.types.TrainingStatus;

// Maps und Sets werden nie herausgegeben!
public final class Data
{
   private static final AtomicBoolean databaseInUse = new AtomicBoolean(false);
   private static UUID uuidDataBaseLock;
   private static DataBase database;

   private Data()
   {

   }

   static void initDataBase()
   {
      database = new DataBase();
   }

   static boolean lockDataBase(UUID uuid)
   {
      if (databaseInUse.get())
      {
         return false;
      }

      uuidDataBaseLock = uuid;
      databaseInUse.set(true);
      return true;
   }

   static boolean unlockDataBase(UUID uuid)
   {
      if (uuidDataBaseLock.equals(uuid))
      {
         databaseInUse.set(false);
         return true;
      }
      return false;
   }

   private static void checkDataBaseInUseAndWait()
   {
      while (databaseInUse.get())
      {
         try
         {
            Thread.sleep(100);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
   }

   private static DataBase getDataBaseAtomic()
   {
      checkDataBaseInUseAndWait();
      return database;
   }

   static Collection<Expression> getAlleMapValues()
   {
      return database.getAlleMap().values();
   }

   static Collection<Expression> getNewMapValues()
   {
      return database.getNewMap().values();
   }

   static Collection<Expression> getDeletedMapValues()
   {
      return database.getDeletedMap().values();
   }

   public static int getAlleMapSize()
   {
      return getDataBaseAtomic().getAlleMap().size();
   }

   public static int getNewMapSize()
   {
      return getDataBaseAtomic().getNewMap().size();
   }

   public static int getInterjektionMapSize()
   {
      return getDataBaseAtomic().getInterjektionMap().size();
   }

   public static int getNomenMapSize()
   {
      return getDataBaseAtomic().getNomenMap().size();
   }

   public static int getNumeralMapSize()
   {
      return getDataBaseAtomic().getNumeralMap().size();
   }

   public static int getPronomMapSize()
   {
      return getDataBaseAtomic().getPronomMap().size();
   }

   public static int getUnkownMapSize()
   {
      return getDataBaseAtomic().getUnkownMap().size();
   }

   public static int getVerbMapSize()
   {
      return getDataBaseAtomic().getVerbMap().size();
   }
   
   public static int getConstructusMapSize()
   {
      return getDataBaseAtomic().getConstructusMap().size();
   }

   public static int getDeletedMapSize()
   {
      return getDataBaseAtomic().getDeletedMap().size();
   }

   public static int getFrageMapSize()
   {
      return getDataBaseAtomic().getFrageMap().size();
   }

   public static int getBegriffMapSize()
   {
      return getDataBaseAtomic().getBegriffMap().size();
   }

   public static int getAdjektivMapSize()
   {
      return getDataBaseAtomic().getAdjektivMap().size();
   }

   public static int getAdverbMapSize()
   {
      return getDataBaseAtomic().getAdverbMap().size();
   }

   public static int getPartikelMapSize()
   {
      return getDataBaseAtomic().getPartikelMap().size();
   }

   public static int getSubstantivMapSize()
   {
      return getDataBaseAtomic().getSubstantivMap().size();
   }

   public static ExpressionTableModel findTranslations(Language language,
         String text, ExpressionKind kind, SearchType search, String chapter,
         Command command)
   {
      return getDataBaseAtomic().findTranslations(language, text, kind, search,
            chapter, command);
   }

   public static ExpressionTableModel findTranslationsNewWords(
         Language language)
   {
      return getDataBaseAtomic().findTranslationsNewWords(language);
   }

   public static ExpressionTableModel findTranslationsDeletedWords(
         Language language)
   {
      return getDataBaseAtomic().findTranslationsDeletedWords(language);
   }

   public static ComboBoxModel<String> getChapterComboBoxModel()
   {
      return getDataBaseAtomic().getChapterComboBoxModel();
   }

   public static String getAllSelectedExpressionsAsString(Language language)
   {
      return getDataBaseAtomic().getAllSelectedExpressionsAsString(language);
   }

   public static void clearAllSelectedExpressions()
   {
      getDataBaseAtomic().clearAllSelectedExpressions();
   }

   public static void deleteExpressions(List<Expression> list)
   {
      getDataBaseAtomic().deleteExpressions(list);
   }

   public static void restoreExpressions(List<Expression> list)
   {
      getDataBaseAtomic().restoreExpressions(list);
   }

   public static void shredderDeletedExpressions()
   {
      getDataBaseAtomic().shredderDeletedExpressions();
   }

   public static List<Expression> getAllSelectedExpressions()
   {
      return getDataBaseAtomic().findAllSelectedExpressionsList();
   }

   public static String[] getChapterArray()
   {
      return getDataBaseAtomic().getChapterArray();
   }

   public static void putExpressionInNewMap(UUID uuid, Expression expression)
   {
      getDataBaseAtomic().getNewMap().put(uuid, expression);
   }

   public static List<Expression> findAllExpressionsList()
   {
      return getDataBaseAtomic().findAllExpressionsList();
   }

   public static TrainingTableModel findTrainingModel(
         Language languageDirection, Command fieldOfTraining)
   {
      return getDataBaseAtomic().findTrainingModel(languageDirection,
            fieldOfTraining);
   }

   public static List<Expression> findExpressionssChapter(String chapter)
   {
      return getDataBaseAtomic().findExpressionsChapter(chapter);
   }

   public static Set<Expression> findOldExpressionsToBeTested(
         Language languageDirection)
   {
      return getDataBaseAtomic()
            .findOldExpressionsToBeTested(languageDirection);
   }

   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // ###################### DataBase #########################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################

   private static class DataBase
   {
      private final static String DELETED_TXT = "DELETED.txt";
      private Set<String> chapterSet = new HashSet<>();
      private final String[][] COLUMNAMES = { { "erste" } };
      private final String[][] TRAINING_COLUMNAMES = { { "Gebiet" },
            { "Wörter gesamt" }, { "ungelernte Wörter" }, { "davon lernen" },
            { "fertig gelernt" } };

      private final boolean directoryOkay = checkDirectory();
      private final ConcurrentMap<UUID, Expression> alleMap = new ConcurrentHashMap<>(
            findNumberOfAllVocabulary() + 100);
      private final ConcurrentMap<UUID, Expression> newMap = new ConcurrentHashMap<>(
            100);

      private final ConcurrentMap<UUID, Expression> deletedMap = readFile(
            DELETED_TXT);
      private final ConcurrentMap<UUID, Expression> unkownMap = readFile(
            "UNKOWN.txt");
      private final ConcurrentMap<UUID, Expression> adjektivMap = readFile(
            "ADJEKTIV.txt");
      private final ConcurrentMap<UUID, Expression> adverbMap = readFile(
            "ADVERB.txt");
      private final ConcurrentMap<UUID, Expression> begriffMap = readFile(
            "BEGRIFF.txt");
      private final ConcurrentMap<UUID, Expression> frageMap = readFile(
            "FRAGE.txt");
      private final ConcurrentMap<UUID, Expression> interjektionMap = readFile(
            "INTERJEKTION.txt");
      private final ConcurrentMap<UUID, Expression> numeralMap = readFile(
            "NUMERAL.txt");
      private final ConcurrentMap<UUID, Expression> partikelMap = readFile(
            "PARTIKEL.txt");
      private final ConcurrentMap<UUID, Expression> pronomMap = readFile(
            "PRONOM.txt");
      private final ConcurrentMap<UUID, Expression> substantivMap = readFile(
            "SUBSTANTIV.txt");
      private final ConcurrentMap<UUID, Expression> verbMap = readFile(
            "VERB.txt");
      private final ConcurrentMap<UUID, Expression> constructusMap = readFile(
            "KONSTRUKT.txt");

      DataBase()
      {
         File customDir = new File(Settings.getTrainingPath());
         if (!customDir.exists())
         {
            customDir.mkdirs();
         }
         else
         {
            File german = new File(Settings.getTrainingPath() + File.separator
                  + Language.GERMAN.name() + ".txt");
            File hebrew = new File(Settings.getTrainingPath() + File.separator
                  + Language.HEBREW.name() + ".txt");

            if (german.exists())
            {
               readTrainingFile(german, Language.GERMAN);
            }
            
            if (hebrew.exists())
            {
               readTrainingFile(hebrew, Language.HEBREW);
            }
         }
      }

      private void readTrainingFile(File german, Language languageDirection)
      {
         try (FileInputStream fis = new FileInputStream(german);
               InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.UTF_8);
               Reader reader = new BufferedReader(isr);)
         {
            StringBuffer buffer = new StringBuffer();
            String input;
            int ch;
            while ((ch = reader.read()) > -1)
            {
               buffer.append((char) ch);
            }
            reader.close();
            input = buffer.toString().trim();
            if (input.isEmpty())
            {
               return;
            }
            String[] rows = input.split("\n");

            for (String row : rows)
            {
               if (row.isEmpty())
               {
                  continue;
               }
               String[] items = row.split("\t");
               UUID uuid = UUID.fromString(items[0]);
               String[] date = items[1].split("\\.");
               LocalDate nextDate = LocalDate.of(
                     Integer.valueOf(date[2]),
                     Integer.valueOf(date[1]),
                     Integer.valueOf(date[0]));
               Repetition repetition = Repetition.valueOf(items[2]);
               int trys = Integer.valueOf(items[3]);
               if(trys==0)
               {
                  trys = 1;
               }
               TrainingStatus trainingstatus = new TrainingStatus(repetition,
                     trys, nextDate);
               Expression expression = alleMap.get(uuid);
               if(expression != null && Language.GERMAN == languageDirection)
               {
                  expression.setTrainingStatusDToH(trainingstatus);
               }
               else if(expression != null && Language.HEBREW == languageDirection)
               {
                  expression.setTrainingStatusHToD(trainingstatus);
               }
            }

         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }

      private static boolean checkDirectory()
      {
         try
         {
            File customDir = new File(Settings.getExpressionPath());
            if (!customDir.exists())
            {
               customDir.mkdirs();
            }
            return true;
         }
         catch (Exception e)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(),
                  "Fehler beim Lesen.\n"
                        + "Ändern Sie den Ort zum Abspeichern und\n"
                        + "Lesen der Daten in den Einstellungen.",
                  "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
            return false;
         }
      }

      private int findNumberOfAllVocabulary()
      {
         Preferences preferences = Preferences.userRoot()
               .node(Settings.getNode());
         int numberOfVocabulary = preferences
               .getInt(Settings.getExpressionNode(), 0);
         if (numberOfVocabulary > 30000)
         {
            numberOfVocabulary = 30000;
         }
         return numberOfVocabulary;
      }

      private ConcurrentMap<UUID, Expression> readFile(String filename)
      {
         if (!directoryOkay)
         {
            return new ConcurrentHashMap<UUID, Expression>(100);
         }

         File file = new File(
               Settings.getExpressionPath() + File.separator + filename);
         if (!file.exists())
         {
            return new ConcurrentHashMap<UUID, Expression>(100);
         }
         try (FileInputStream fis = new FileInputStream(file);
               InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.UTF_8);
               Reader reader = new BufferedReader(isr);)
         {
            StringBuffer buffer = new StringBuffer();
            String input;
            int ch;
            while ((ch = reader.read()) > -1)
            {
               buffer.append((char) ch);
            }
            reader.close();
            input = buffer.toString().trim();

            if (input.isEmpty())
            {
               return new ConcurrentHashMap<UUID, Expression>(100);
            }

            String[] rows = input.split("\n");

            ConcurrentMap<UUID, Expression> map = new ConcurrentHashMap<>(
                  rows.length + 100);
            for (String row : rows)
            {
               if (row.isEmpty())
               {
                  continue;
               }
               try
               {
                  String[] items = row.split("\t");

                  Expression expression = new Expression();
                  int i = 0;

                  expression.setUuid(UUID.fromString(items[i]));
                  i++;
                  expression.setChapter(items[i]);
                  if (!expression.getChapter().isEmpty()
                        && !DELETED_TXT.equals(filename))
                  {
                     chapterSet.add(expression.getChapter());
                  }
                  i++;
                  expression.setGerman(items[i]);
                  i++;
                  expression.setHebrewInLatin(items[i]);
                  i++;
                  expression.setHebrew(items[i]);
                  i++;
                  expression.setGenderHebrew(Gender.valueOf(items[i]));
                  i++;
                  expression.setNumerusHebrew(Numerus.valueOf(items[i]));
                  i++;
                  expression.setBinjan(Binjan.valueOf(items[i]));
                  i++;
                  expression.setKind(ExpressionKind.valueOf(items[i]));
                  i++;
                  expression.setSearchwordsGerman(items[i].split(","));
                  i++;
                  expression.setSearchwordsHebrew(items[i].split(","));

                  map.put(expression.getUuid(), expression);
                  if (!DELETED_TXT.equals(filename))
                  {
                     alleMap.put(expression.getUuid(), expression);
                  }
               }
               catch (Exception e2)
               {
                  e2.printStackTrace();
               }

            }
            return map;
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
         return new ConcurrentHashMap<UUID, Expression>(100);
      }

      // ############################################################

      private ExpressionTableModel findTranslations(Language language,
            String text, ExpressionKind kind, SearchType search, String chapter,
            Command command)
      {
         Collection<Expression> expressions = null;

         if (text == null && kind == null && search == null && chapter == null
               && command != null)
         {
            if (Command.ALL_SELECTED.equals(command))
            {
               List<Expression> selectedExpressions = findAllSelectedExpressionsList();
               Collections.sort(selectedExpressions,
                     new ExpressionComparator(language));
               return new ExpressionTableModel(
                     convertToExpressionModelArray(selectedExpressions),
                     COLUMNAMES);
            }
         }
         else if (text == null && kind == null && search == null
               && chapter != null && command == null)
         {
            return new ExpressionTableModel(
                  convertToExpressionModelArray(
                        findExpressionsChapterSorted(chapter, language)),
                  COLUMNAMES);
         }
         else if (text == null && kind != null && search == null
               && chapter == null && command == null)
         {
            return new ExpressionTableModel(convertToExpressionModelArray(
                  findExpressionsKind(kind, language)), COLUMNAMES);
         }
         else if (text != null && kind == null && search != null
               && chapter == null && command == null)
         {
            if (text.isEmpty())
            {
               expressions = null;
            }
            else
            {
               expressions = alleMap.values();
            }
         }
         else
         {
            System.out.println(
                  "Data: Search: Es wurde eine nicht berücksichtigte Kombination gefunden:\n"
                        + "Language = " + language + ", kind = " + kind
                        + ", search = " + search + "\n" + "chapter = " + chapter
                        + ", command = " + command);
         }

         return new ExpressionTableModel(
               convertToExpressionModelArray(
                     filterExpressions(text, language, search, expressions)),
               COLUMNAMES);
      }

      private ExpressionTableModel findTranslationsNewWords(Language language)
      {
         Collection<Expression> expressions = newMap.values();
         Expression[] expressionArray = expressions
               .toArray(new Expression[expressions.size()]);
         Arrays.sort(expressionArray, new ExpressionComparator(language));

         return new ExpressionTableModel(
               convertToExpressionModelArray(expressionArray), COLUMNAMES);
      }

      private ExpressionTableModel findTranslationsDeletedWords(
            Language language)
      {
         Collection<Expression> expressions = deletedMap.values();
         Expression[] expressionArray = expressions
               .toArray(new Expression[expressions.size()]);
         Arrays.sort(expressionArray, new ExpressionComparator(language));

         return new ExpressionTableModel(
               convertToExpressionModelArray(expressionArray), COLUMNAMES);
      }

      private List<Expression> filterExpressions(String text, Language language,
            SearchType search, Collection<Expression> expressions)
      {
         List<Expression> list = new ArrayList<>();

         if (expressions != null)
         {
            for (Expression expression : expressions)
            {
               if (Language.GERMAN.equals(language)
                     && SearchType.SEARCHWORD.equals(search))
               {
                  if (equalsGermanSearchWord(text, expression))
                  {
                     list.add(expression);
                  }
               }
               else if (Language.GERMAN.equals(language)
                     && SearchType.WORDSTART.equals(search))
               {
                  if (equalsGermanWordStart(text, expression))
                  {
                     list.add(expression);
                  }
               }
               else if (Language.HEBREW.equals(language)
                     && SearchType.SEARCHWORD.equals(search))
               {
                  if (equalsHebrewSearchWord(text, expression))
                  {
                     list.add(expression);
                  }
               }
               else if (Language.HEBREW.equals(language)
                     && SearchType.WORDSTART.equals(search))
               {
                  if (equalsHebrewWordStart(text, expression))
                  {
                     list.add(expression);
                  }
               }
            }
         }
         Collections.sort(list, new ExpressionComparator(language));
         return list;
      }

      private List<Expression> findExpressionsChapterSorted(String chapter,
            Language language)
      {
         List<Expression> list = findExpressionsChapter(chapter);
         Collections.sort(list, new ExpressionComparator(language));
         return list;
      }

      private List<Expression> findExpressionsChapter(String chapter)
      {
         List<Expression> list = new ArrayList<>();
         Collection<Expression> expressions = alleMap.values();
         for (Expression expression : expressions)
         {
            if (expression.getChapter().equals(chapter))
            {
               list.add(expression);
            }
         }
         return list;
      }

      private List<Expression> findExpressionsKind(ExpressionKind kind,
            Language language)
      {
         List<Expression> list = new ArrayList<>();
         Collection<Expression> expressions = findMapValues(kind);
         for (Expression expression : expressions)
         {
            if (expression.getKind().equals(kind))
            {
               list.add(expression);
            }
         }
         Collections.sort(list, new ExpressionComparator(language));
         return list;
      }

      private Collection<Expression> findMapValues(ExpressionKind kind)
      {
         switch (kind)
         {
         case BEGRIFF:
            return begriffMap.values();
         case ADJEKTIV:
            return adjektivMap.values();
         case FRAGE:
            return frageMap.values();
         case UNKOWN:
            return unkownMap.values();
         case VERB:
            return verbMap.values();
         case NUMERAL:
            return numeralMap.values();
         case ADVERB:
            return adverbMap.values();
         case INTERJEKTION:
            return interjektionMap.values();
         case PRONOM:
            return pronomMap.values();
         case PARTIKEL:
            return partikelMap.values();
         case SUBSTANTIV:
            return substantivMap.values();
         case KONSTRUKT:
            return constructusMap.values();
         }
         throw new IllegalArgumentException("Data: Wortarten: das gibt es nicht");
      }

      private boolean equalsGermanSearchWord(String text, Expression expression)
      {
         text = text.toLowerCase().trim();
         List<String> searchwords = expression.getSearchwordsGerman();
         for (String word : searchwords)
         {
            if (word.equals(text))
            {
               return true;
            }
         }
         return false;
      }

      private boolean equalsHebrewSearchWord(String text, Expression expression)
      {
         text = text.toLowerCase().trim();
         List<String> searchwords = expression.getSearchwordsHebrew();
         for (String word : searchwords)
         {
            if (word.equals(text))
            {
               return true;
            }
         }
         return false;
      }

      private boolean equalsHebrewWordStart(String text, Expression expression)
      {
         text = text.trim();
         return expression.getHebrew().startsWith(text);
      }

      private boolean equalsGermanWordStart(String text, Expression expression)
      {
         text = text.toLowerCase().trim();
         return expression.getGerman().toLowerCase().startsWith(text);
      }

      private Expression[][] convertToExpressionModelArray(
            Expression[] expressionArray)
      {
         Expression[][] expressionModel = new Expression[expressionArray.length][1];
         for (int i = 0; i < expressionArray.length; i++)
         {
            expressionModel[i][0] = expressionArray[i];
         }
         return expressionModel;
      }

      private Expression[][] convertToExpressionModelArray(
            List<Expression> list)
      {
         Expression[][] data = new Expression[list.size()][1];

         for (int i = 0; i < list.size(); i++)
         {
            data[i][0] = list.get(i);
         }
         return data;
      }

      private Map<UUID, Expression> getNewMap()
      {
         return newMap;
      }

      private ComboBoxModel<String> getChapterComboBoxModel()
      {
         return new DefaultComboBoxModel<String>(getChapterArray());
      }

      private List<String> getChapterList()
      {
         List<String> chapterList = new ArrayList<>();

         for (String chapter : chapterSet)
         {
            chapterList.add(chapter);
         }
         Collections.sort(chapterList);
         return chapterList;
      }

      private String[] getChapterArray()
      {
         List<String> chapterList = getChapterList();
         String[] result = new String[chapterList.size()];
         int index = 0;
         for (String chapter : chapterList)
         {
            result[index] = chapter;
            index++;
         }
         return result;
      }

      private String getAllSelectedExpressionsAsString(Language language)
      {
         List<Expression> list = new ArrayList<>();

         for (Expression expression : alleMap.values())
         {
            if (expression.isSelected())
            {
               list.add(expression);
            }
         }

         for (Expression expression : newMap.values())
         {
            if (expression.isSelected())
            {
               list.add(expression);
            }
         }

         Collections.sort(list, new ExpressionComparator(language));

         StringJoiner joiner = new StringJoiner("\n\n");

         for (Expression expression : list)
         {
            joiner.add(expression.getCopyLines(language));
         }

         return joiner.toString();
      }

      private void clearAllSelectedExpressions()
      {
         for (Expression expression : alleMap.values())
         {
            expression.setSelected(false);
         }

         for (Expression expression : newMap.values())
         {
            expression.setSelected(false);
         }
      }

      private List<Expression> findAllSelectedExpressionsList()
      {
         List<Expression> list = new ArrayList<>();

         for (Expression expression : alleMap.values())
         {
            if (expression.isSelected())
            {
               list.add(expression);
            }
         }

         for (Expression expression : newMap.values())
         {
            if (expression.isSelected())
            {
               list.add(expression);
            }
         }
         return list;
      }

      private void deleteExpressions(List<Expression> list)
      {
         for (Expression expression : list)
         {
            deletedMap.put(expression.getUuid(), expression);

            alleMap.remove(expression.getUuid(), expression);
            adjektivMap.remove(expression.getUuid(), expression);
            adverbMap.remove(expression.getUuid(), expression);
            begriffMap.remove(expression.getUuid(), expression);
            frageMap.remove(expression.getUuid(), expression);
            interjektionMap.remove(expression.getUuid(), expression);
            newMap.remove(expression.getUuid(), expression);
            numeralMap.remove(expression.getUuid(), expression);
            partikelMap.remove(expression.getUuid(), expression);
            pronomMap.remove(expression.getUuid(), expression);
            substantivMap.remove(expression.getUuid(), expression);
            unkownMap.remove(expression.getUuid(), expression);
            verbMap.remove(expression.getUuid(), expression);
            constructusMap.remove(expression.getUuid(), expression);
         }

         reloadChapterSet();
      }

      private void restoreExpressions(List<Expression> list)
      {
         for (Expression expression : list)
         {
            deletedMap.remove(expression.getUuid(), expression);
            newMap.put(expression.getUuid(), expression);
         }
      }

      private void shredderDeletedExpressions()
      {
         deletedMap.clear();
      }

      private void reloadChapterSet()
      {
         chapterSet = new HashSet<>();
         Set<Entry<UUID, Expression>> allSet = alleMap.entrySet();
         for (Entry<UUID, Expression> entry : allSet)
         {
            chapterSet.add(entry.getValue().getChapter());
         }
      }

      private List<Expression> findAllExpressionsList()
      {
         List<Expression> list = new ArrayList<Expression>();
         for (Expression expression : getAlleMap().values())
         {
            list.add(expression);
         }
         return list;
      }

      private TrainingTableModel findTrainingModel(Language languageDirection,
            Command fieldOfTraining)
      {
         TrainingTableRow[][] data = null;
         switch (fieldOfTraining)
         {
         case AREA_ALL:
            List<Expression> listAll = this.findAllExpressionsList();
            data = new TrainingTableRow[1][1];
            TrainingTableRow row = new TrainingTableRow();
            row.setFieldOfTraining(fieldOfTraining);
            row.setField("Alle Wörter");
            row.setTotalWords(listAll.size());
            row.setExpressionList(
                  findNotStudiedWords(languageDirection, listAll));
            row.setNotStudiedWords(row.getExpressionList().size());
            row.setAmountOfNewWords(0);
            row.setFieldDone(row.getNotStudiedWords() == 0);
            data[0][0] = row;
            break;
         case AREA_CHAPTER:
            List<TrainingTableRow> unlearnedPerChapter = new ArrayList<>();
            for (String chapter : getChapterList())
            {
               List<Expression> listChapter = this
                     .findExpressionsChapter(chapter);
               TrainingTableRow chapterRow = new TrainingTableRow();
               chapterRow.setFieldOfTraining(fieldOfTraining);
               chapterRow.setChapter(chapter);
               chapterRow.setField(chapter);
               chapterRow.setTotalWords(listChapter.size());
               chapterRow.setExpressionList(
                     findNotStudiedWords(languageDirection, listChapter));
               chapterRow
                     .setNotStudiedWords(chapterRow.getExpressionList().size());
               chapterRow.setAmountOfNewWords(0);
               chapterRow.setFieldDone(chapterRow.getNotStudiedWords() == 0);
               unlearnedPerChapter.add(chapterRow);
            }
            data = new TrainingTableRow[unlearnedPerChapter.size()][1];
            for (int i = 0; i < unlearnedPerChapter.size(); i++)
            {
               data[i][0] = unlearnedPerChapter.get(i);
            }
            break;
         case AREA_EXPRESSION_KIND:
            List<TrainingTableRow> unlearnedPerKind = new ArrayList<>();
            for (ExpressionKind kind : ExpressionKind.getValues())
            {
               Collection<Expression> listKind = this.findMapValues(kind);
               TrainingTableRow kindRow = new TrainingTableRow();
               kindRow.setFieldOfTraining(fieldOfTraining);
               kindRow.setKind(kind);
               kindRow.setField(kind.toString());
               kindRow.setTotalWords(listKind.size());
               kindRow.setExpressionList(
                     findNotStudiedWords(languageDirection, listKind));
               kindRow.setNotStudiedWords(kindRow.getExpressionList().size());
               kindRow.setAmountOfNewWords(0);
               kindRow.setFieldDone(kindRow.getNotStudiedWords() == 0);
               unlearnedPerKind.add(kindRow);
            }
            data = new TrainingTableRow[unlearnedPerKind.size()][1];
            for (int i = 0; i < unlearnedPerKind.size(); i++)
            {
               data[i][0] = unlearnedPerKind.get(i);
            }
            break;
         case AREA_SELECTED:
            List<Expression> listSelected = findAllSelectedExpressionsList();
            TrainingTableRow selectedRow = new TrainingTableRow();
            selectedRow.setFieldOfTraining(fieldOfTraining);
            selectedRow.setField("Ausgewählte Wörter");
            selectedRow.setTotalWords(listSelected.size());
            selectedRow.setExpressionList(
                  findNotStudiedWords(languageDirection, listSelected));
            selectedRow
                  .setNotStudiedWords(selectedRow.getExpressionList().size());
            selectedRow.setAmountOfNewWords(0);
            selectedRow.setFieldDone(selectedRow.getNotStudiedWords() == 0);
            data = new TrainingTableRow[1][1];
            data[0][0] = selectedRow;
            break;
         default:
            break;
         }

         return new TrainingTableModel(data, TRAINING_COLUMNAMES);
      }

      private List<Expression> findNotStudiedWords(Language languageDirection,
            List<Expression> list)
      {
         List<Expression> result = new ArrayList<>();
         switch (languageDirection)
         {
         case GERMAN:
            for (Expression expression : list)
            {
               if (!expression.getTrainingStatusDToH().isTrainingStarted())
               {
                  result.add(expression);
               }
            }
            break;
         case HEBREW:
            for (Expression expression : list)
            {
               if (!expression.getTrainingStatusHToD().isTrainingStarted())
               {
                  result.add(expression);
               }
            }
         }
         return result;
      }

      private List<Expression> findNotStudiedWords(Language languageDirection,
            Collection<Expression> list)
      {
         List<Expression> result = new ArrayList<>();
         switch (languageDirection)
         {
         case GERMAN:
            for (Expression expression : list)
            {
               if (!expression.getTrainingStatusDToH().isTrainingStarted())
               {
                  result.add(expression);
               }
            }
            break;
         case HEBREW:
            for (Expression expression : list)
            {
               if (!expression.getTrainingStatusHToD().isTrainingStarted())
               {
                  result.add(expression);
               }
            }
         }
         return result;
      }

      public Set<Expression> findOldExpressionsToBeTested(
            Language languageDirection)
      {
         Set<Expression> result = new HashSet<>();
         LocalDate now = LocalDate.now();
         Collection<Expression> allExpressions = alleMap.values();
         switch (languageDirection)
         {
         case GERMAN:
            for (Expression expression : allExpressions)
            {
               if (expression.getTrainingStatusDToH().isTrainingStarted()
                     && (now.isEqual(
                           expression.getTrainingStatusDToH().getNextDate())
                           || now.isAfter(expression.getTrainingStatusDToH()
                                 .getNextDate())))
               {
                  result.add(expression);
               }
            }
            break;
         case HEBREW:
            for (Expression expression : allExpressions)
            {
               if (expression.getTrainingStatusHToD().isTrainingStarted()
                     && (now.isEqual(
                           expression.getTrainingStatusHToD().getNextDate())
                           || now.isAfter(expression.getTrainingStatusHToD()
                                 .getNextDate())))
               {
                  result.add(expression);
               }
            }
         }

         return result;
      }

      private Map<UUID, Expression> getDeletedMap()
      {
         return deletedMap;
      }

      private ConcurrentMap<UUID, Expression> getAlleMap()
      {
         return alleMap;
      }

      private ConcurrentMap<UUID, Expression> getAdjektivMap()
      {
         return adjektivMap;
      }

      private ConcurrentMap<UUID, Expression> getAdverbMap()
      {
         return adverbMap;
      }

      private ConcurrentMap<UUID, Expression> getBegriffMap()
      {
         return begriffMap;
      }

      private ConcurrentMap<UUID, Expression> getFrageMap()
      {
         return frageMap;
      }

      private ConcurrentMap<UUID, Expression> getInterjektionMap()
      {
         return interjektionMap;
      }

      private ConcurrentMap<UUID, Expression> getNomenMap()
      {
         return substantivMap;
      }

      private ConcurrentMap<UUID, Expression> getNumeralMap()
      {
         return numeralMap;
      }

      private ConcurrentMap<UUID, Expression> getPronomMap()
      {
         return pronomMap;
      }

      private ConcurrentMap<UUID, Expression> getUnkownMap()
      {
         return unkownMap;
      }

      private ConcurrentMap<UUID, Expression> getVerbMap()
      {
         return verbMap;
      }

      private ConcurrentMap<UUID, Expression> getPartikelMap()
      {
         return partikelMap;
      }

      private ConcurrentMap<UUID, Expression> getSubstantivMap()
      {
         return substantivMap;
      }
      
      private ConcurrentMap<UUID, Expression> getConstructusMap()
      {
         return constructusMap;
      }
   }

}
