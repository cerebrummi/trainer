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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.Vector;
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
import vokabeltrainer.panels.statistics.StatisticsTableModel;
import vokabeltrainer.panels.statistics.StatisticsTableRow;
import vokabeltrainer.panels.success.table.SuccessTableModel;
import vokabeltrainer.panels.success.table.SuccessTableRow;
import vokabeltrainer.panels.success.table.SuccessTableRowComparator;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTableModel;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTableRow;
import vokabeltrainer.resources.vocabulary.Vocabulary;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Database;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Repetition;
import vokabeltrainer.types.SearchType;
import vokabeltrainer.types.TrainingStatus;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.ExpressionKind;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.Numerus;

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

   // for saving expressions only, therefore NOT public
   static Collection<Expression> getAlleMapValues()
   {
      return database.getAlleMap().values();
   }

   // for saving expressions only, therefore NOT public
   static Collection<Expression> getNewMapValues()
   {
      return database.getNewMap().values();
   }

   // for saving expressions only, therefore NOT public
   static Collection<Expression> getDeletedMapValues()
   {
      return database.getDeletedMap().values();
   }

   // for saving expressions only, therefore NOT public
   static void integrateNewExpressions()
   {
      database.integrateNewExpressions();
   }

   public static int getAlleMapSize()
   {
      return getDataBaseAtomic().getAlleMap().size();
   }

   public static int getNewMapSize()
   {
      return getDataBaseAtomic().getNewMap().size();
   }

   public static int getDeletedMapSize()
   {
      return getDataBaseAtomic().getDeletedMap().size();
   }
   
   public static int getMapSize(ExpressionKind kind)
   {
      return getDataBaseAtomic().getExpressionMap(kind).size();
   }

   public static ExpressionTableModel findTranslations(Language language,
         String text, ExpressionKind kind, SearchType search, Chapter chapter,
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

   public static Chapter[] getChapterArray()
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

   public static StatisticsTableModel findStatisticsModel()
   {
      return getDataBaseAtomic().findStatisticsModel();
   }

   public static List<Expression> findExpressionssChapter(Chapter chapter)
   {
      return getDataBaseAtomic().findExpressionsChapter(chapter);
   }

   public static void changeKindofExpression(ExpressionKind oldKind,
         Expression newKind)
   {
      getDataBaseAtomic().changeKindofExpressionInDataStrukture(oldKind,
            newKind);
   }

   public static SuccessTableModel findSuccessModel(Language direction,
         Repetition repetition)
   {
      return getDataBaseAtomic().findSuccessModel(direction, repetition);
   }

   public static void unselectAllExpressions()
   {
      getDataBaseAtomic().unselectAllExpressions();
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
      private Set<Chapter> chapterSet = new HashSet<>();
      private final String[][] COLUMNAMES = { { "erste" } };

      private final boolean directoryOkay = checkDirectory();
      private final ConcurrentMap<UUID, Expression> alleMap = new ConcurrentHashMap<>(
            findNumberOfAllVocabulary() + 100);
      private final ConcurrentMap<UUID, Expression> newMap = new ConcurrentHashMap<>(
            100);
      private final ConcurrentMap<UUID, Expression> deletedMap = readFile(
            DELETED_TXT, null, Database.SELF);

      private final Map<ExpressionKind, ConcurrentMap<UUID, Expression>> mapOfMaps = new ConcurrentHashMap<>();

      DataBase()
      {
         for (ExpressionKind mapKind : ExpressionKind.values())
         {
            mapOfMaps.put(mapKind,
                  readFile(mapKind.name() + ".txt", null, Database.SELF));
         }
         
         for (Database database : Settings.getChosenDatabases())
         {
            for (ExpressionKind mapKind : ExpressionKind.values())
            {
               readFile(database.getFolder() + File.separator + mapKind.name()
                     + ".txt", mapOfMaps.get(mapKind), database);
            }
         }

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
               LocalDate nextDate = LocalDate.of(Integer.valueOf(date[2]),
                     Integer.valueOf(date[1]), Integer.valueOf(date[0]));
               Repetition repetition = Repetition.valueOf(items[2]);
               int trys = Integer.valueOf(items[3]);
               if (trys < 1)
               {
                  trys = 1;
               }
               TrainingStatus trainingstatus = new TrainingStatus(repetition,
                     trys, nextDate);
               Expression expression = alleMap.get(uuid);
               if (expression != null && Language.GERMAN == languageDirection)
               {
                  expression.setTrainingStatusDToH(trainingstatus);
               }
               else if (expression != null
                     && Language.HEBREW == languageDirection)
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
            File customDir = new File(Settings.getExpressionPathFolder());
            if (!customDir.exists())
            {
               customDir.mkdirs();
            }
            return true;
         }
         catch (Exception e)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(),
                  "Fehler beim Lesen der selbsteingegebenen Vokabeln.\n"
                        + "Ändern Sie den Ort zum Abspeichern und\n"
                        + "Lesen der Vokabeln in den Einstellungen.",
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

      private ConcurrentMap<UUID, Expression> readFile(String filename,
            ConcurrentMap<UUID, Expression> existingMap, Database origin)
      {
         File file = null;
         if (existingMap == null)
         {
            if (!directoryOkay)
            {
               return new ConcurrentHashMap<UUID, Expression>(100);
            }

            file = new File(Settings.getExpressionPathFolder() + File.separator
                  + filename);
            if (!file.exists())
            {
               return new ConcurrentHashMap<UUID, Expression>(100);
            }

            try (FileInputStream fis = new FileInputStream(file);
                  InputStreamReader isr = new InputStreamReader(fis,
                        StandardCharsets.UTF_8);
                  Reader reader = new BufferedReader(isr);)
            {
               return readData(filename, existingMap, reader, origin);
            }
            catch (IOException e)
            {
               // nothing
            }
         }
         else
         {
            try (InputStreamReader isr = new InputStreamReader(
                  Vocabulary.class.getResourceAsStream(filename),
                  StandardCharsets.UTF_8);
                  Reader reader = new BufferedReader(isr);)
            {
               return readData(filename, existingMap, reader, origin);
            }
            catch (IOException e)
            {
               // nothing
            }

            return null;
         }

         return new ConcurrentHashMap<UUID, Expression>(100);
      }

      private ConcurrentMap<UUID, Expression> readData(String filename,
            ConcurrentMap<UUID, Expression> existingMap, Reader reader,
            Database origin) throws IOException
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

               Expression expression;
               if (existingMap == null)
               {
                  expression = new Expression(false, false);
               }
               else
               {
                  expression = new Expression(false, true);
               }
               int i = 0;

               expression.setUuid(UUID.fromString(items[i]));
               expression.setOrigin(origin);
               i++;

               expression.setChapter(new Chapter(items[i], origin));
               if (!expression.getChapter().getName().isEmpty()
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

               if (existingMap == null)
               {
                  map.put(expression.getUuid(), expression);
               }
               else
               {
                  existingMap.put(expression.getUuid(), expression);
               }

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

      // ############################################################

      private ExpressionTableModel findTranslations(Language language,
            String text, ExpressionKind kind, SearchType search,
            Chapter chapter, Command command)
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

      private List<Expression> findExpressionsChapterSorted(Chapter chapter,
            Language language)
      {
         List<Expression> list = findExpressionsChapter(chapter);
         Collections.sort(list, new ExpressionComparator(language));
         return list;
      }

      private List<Expression> findExpressionsChapter(Chapter chapter)
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
         return mapOfMaps.get(kind).values();
      }

      private boolean equalsGermanSearchWord(String text, Expression expression)
      {
         text = text.trim();
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
         text = text.trim();
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
         text = text.trim();
         return expression.getGerman().startsWith(text);
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
         return new DefaultComboBoxModel<String>(getChapterArrayForEditor());
      }

      private List<String> getChapterListForEditor()
      {
         List<String> chapterList = new ArrayList<>();

         for (Chapter chapter : chapterSet)
         {
            if (chapter.isSelf())
            {
               chapterList.add(chapter.getName());
            }
         }
         Collections.sort(chapterList);
         return chapterList;
      }

      private String[] getChapterArrayForEditor()
      {
         List<String> chapterList = getChapterListForEditor();
         String[] result = new String[chapterList.size()];
         int index = 0;
         for (String chapter : chapterList)
         {
            result[index] = chapter;
            index++;
         }
         return result;
      }

      private List<Chapter> getChapterList()
      {
         List<Chapter> chapterList = new ArrayList<>();

         for (Chapter chapter : chapterSet)
         {
            chapterList.add(chapter);
         }
         Collections.sort(chapterList);
         return chapterList;
      }

      private Chapter[] getChapterArray()
      {
         List<Chapter> chapterList = getChapterList();
         Chapter[] result = new Chapter[chapterList.size()];
         int index = 0;
         for (Chapter chapter : chapterList)
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

         return list;
      }

      private void deleteExpressions(List<Expression> list)
      {
         for (Expression expression : list)
         {
            if (expression.isDoNotChange())
            {
               continue;
            }
            deletedMap.put(expression.getUuid(), expression);
            alleMap.remove(expression.getUuid(), expression);
            newMap.remove(expression.getUuid(), expression);
            
            for(ConcurrentMap<UUID, Expression> map : mapOfMaps.values())
            {
               map.remove(expression.getUuid(), expression);
            }
         }

         reloadChapterSet();
      }

      private void integrateNewExpressions()
      {
         for (Expression expression : newMap.values())
         {
            alleMap.put(expression.getUuid(), expression);
            
            ConcurrentMap<UUID, Expression> map = mapOfMaps.get(expression.getKind());
            map.put(expression.getUuid(), expression);
         }
         this.reloadChapterSet();
         newMap.clear();
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
         Set<Expression> oldToBeTested = findOldExpressionsToBeTested(
               languageDirection, fieldOfTraining);

         switch (fieldOfTraining)
         {
         case AREA_CHAPTER:
            List<TrainingTableRow> unlearnedPerChapter = new ArrayList<>();
            for (Chapter chapter : getChapterList())
            {
               List<Expression> listChapter = this
                     .findExpressionsChapter(chapter);
               TrainingTableRow chapterRow = new TrainingTableRow();
               chapterRow.setFieldOfTraining(fieldOfTraining);
               chapterRow.setChapter(chapter);
               chapterRow.setField(chapter.getName());
               chapterRow.setExpressionListOldWords(
                     findExpressionListOldToBeTestedPerChapter(chapter,
                           oldToBeTested));
               chapterRow.setToBeRepeatedWords(
                     findOldToBeTestedPerChapter(chapter, oldToBeTested));
               chapterRow.setExpressionListNewWords(
                     findNotStudiedWords(languageDirection, listChapter));
               chapterRow.setNotStudiedWords(
                     chapterRow.getExpressionListNewWords().size());
               chapterRow.setAmountOfNewWords(0);
               chapterRow.setFieldDone(chapterRow.getNotStudiedWords() == 0
                     && chapterRow.getToBeRepeatedWords() == 0);
               chapterRow.setStarted(chapterRow.getToBeRepeatedWords() > 0);
               unlearnedPerChapter.add(chapterRow);
            }
            data = new TrainingTableRow[unlearnedPerChapter.size()][1];
            for (int i = 0; i < unlearnedPerChapter.size(); i++)
            {
               data[i][0] = unlearnedPerChapter.get(i);
            }
            break;
         case AREA_SELECTED:
            List<Expression> listSelected = findAllSelectedExpressionsList();
            TrainingTableRow selectedRow = new TrainingTableRow();
            selectedRow.setFieldOfTraining(fieldOfTraining);
            selectedRow.setField("Ausgewählte Wörter");
            selectedRow.setExpressionListOldWords(oldToBeTested);
            selectedRow.setToBeRepeatedWords(oldToBeTested.size());
            selectedRow.setExpressionListNewWords(
                  findNotStudiedWords(languageDirection, listSelected));
            selectedRow.setNotStudiedWords(
                  selectedRow.getExpressionListNewWords().size());
            selectedRow.setAmountOfNewWords(selectedRow.getNotStudiedWords());
            selectedRow.setFieldDone(selectedRow.getNotStudiedWords() == 0
                  && selectedRow.getToBeRepeatedWords() == 0);
            selectedRow.setStarted(selectedRow.getToBeRepeatedWords() > 0);
            data = new TrainingTableRow[1][1];
            data[0][0] = selectedRow;
            break;
         default:
            break;
         }

         return new TrainingTableModel(data);
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

      public Set<Expression> findOldExpressionsToBeTested(
            Language languageDirection, Command fieldOfTraining)
      {
         Set<Expression> result = new HashSet<>();
         LocalDate now = LocalDate.now();
         Collection<Expression> allExpressions = alleMap.values();
         switch (languageDirection)
         {
         case GERMAN:
            for (Expression expression : allExpressions)
            {
               if (Command.AREA_SELECTED == fieldOfTraining)
               {
                  if (expression.isSelected() && expression
                        .getTrainingStatusDToH().isTrainingStarted())
                  {
                     result.add(expression);
                  }
               }
               else
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
            }
            break;
         case HEBREW:
            for (Expression expression : allExpressions)
            {
               if (Command.AREA_SELECTED == fieldOfTraining)
               {
                  if (expression.isSelected() && expression
                        .getTrainingStatusHToD().isTrainingStarted())
                  {
                     result.add(expression);
                  }
               }
               else
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
         }

         return result;
      }

      private int findOldToBeTestedPerChapter(Chapter chapter,
            Set<Expression> allOldToBeTestedExpressions)
      {
         int result = 0;
         for (Expression e : allOldToBeTestedExpressions)
         {
            if (chapter.equals(e.getChapter()))
            {
               result++;
            }
         }
         return result;
      }

      private Set<Expression> findExpressionListOldToBeTestedPerChapter(
            Chapter chapter, Set<Expression> allOldToBeTestedExpressions)
      {
         Set<Expression> result = new HashSet<>();
         for (Expression e : allOldToBeTestedExpressions)
         {
            if (chapter.equals(e.getChapter()))
            {
               result.add(e);
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
      
      public ConcurrentMap<UUID, Expression> getExpressionMap(ExpressionKind kind)
      {
         return mapOfMaps.get(kind);
      }

      private void changeKindofExpressionInDataStrukture(ExpressionKind oldKind,
            Expression changedExpression)
      {
         mapOfMaps.get(oldKind).remove(changedExpression.getUuid());
         mapOfMaps.get(changedExpression.getKind())
               .put(changedExpression.getUuid(), changedExpression);
      }

      private StatisticsTableModel findStatisticsModel()
      {
         Set<LocalDate> datesAll = new HashSet<>();
         Map<LocalDate, List<List<Expression>>> dates = new HashMap<>();

         alleMap.forEach((uuid, expression) -> {
            Expression e = ((Expression) expression);

            if (e.getTrainingStatusDToH().isTrainingStarted()
                  && !e.getTrainingStatusDToH().isTrainingDone()
                  && datesAll.add(e.getTrainingStatusDToH().getNextDate()))
            {
               List<Expression> oneWay = new ArrayList<>();
               List<Expression> otherWay = new ArrayList<>();
               List<List<Expression>> bothWays = new ArrayList<>();
               bothWays.add(oneWay);
               bothWays.add(otherWay);
               dates.put(e.getTrainingStatusDToH().getNextDate(), bothWays);
            }
            if (e.getTrainingStatusHToD().isTrainingStarted()
                  && !e.getTrainingStatusHToD().isTrainingDone()
                  && datesAll.add(e.getTrainingStatusHToD().getNextDate()))
            {
               List<Expression> oneWay = new ArrayList<>();
               List<Expression> otherWay = new ArrayList<>();
               List<List<Expression>> bothWays = new ArrayList<>();
               bothWays.add(oneWay);
               bothWays.add(otherWay);
               dates.put(e.getTrainingStatusHToD().getNextDate(), bothWays);
            }

            if (e.getTrainingStatusDToH().isTrainingStarted()
                  && !e.getTrainingStatusDToH().isTrainingDone())
            {
               dates.get(e.getTrainingStatusDToH().getNextDate()).get(0).add(e);
            }

            if (e.getTrainingStatusHToD().isTrainingStarted()
                  && !e.getTrainingStatusHToD().isTrainingDone())
            {
               dates.get(e.getTrainingStatusHToD().getNextDate()).get(1).add(e);
            }

         });

         List<LocalDate> sortedDates = new LinkedList<>(datesAll);
         sortedDates.sort((date1, date2) -> date1.compareTo(date2));

         Vector<Vector<StatisticsTableRow>> data = new Vector<>();
         Vector<String> columnNames = new Vector<>();
         columnNames.add("eins");
         StatisticsTableModel model = new StatisticsTableModel(data,
               columnNames);

         for (int i = 0; i < sortedDates.size(); i++)
         {
            StatisticsTableRow row = new StatisticsTableRow(i,
                  sortedDates.get(i), dates.get(sortedDates.get(i)).get(0),
                  dates.get(sortedDates.get(i)).get(1), model);
            Vector<StatisticsTableRow> vector = new Vector<>();
            vector.add(row);
            data.add(vector);
         }

         return model;
      }

      private SuccessTableModel findSuccessModel(Language direction,
            Repetition repetition)
      {
         Vector<Vector<SuccessTableRow>> data = new Vector<>();
         Vector<String> columnNames = new Vector<>();
         columnNames.add("eins");

         alleMap.forEach((uuid, expression) -> {
            Expression e = ((Expression) expression);

            if (repetition == null
                  && !e.getTrainingStatus(direction).isTrainingStarted())
            {
               Vector<SuccessTableRow> vector = new Vector<>();
               vector.add(new SuccessTableRow(e));
               data.add(vector);
            }
            else if (repetition == e.getTrainingStatus(direction)
                  .getRepetition())
            {
               Vector<SuccessTableRow> vector = new Vector<>();
               vector.add(new SuccessTableRow(e));
               data.add(vector);
            }
         });
         Collections.sort(data, new SuccessTableRowComparator());
         return new SuccessTableModel(data, columnNames);
      }

      private void unselectAllExpressions()
      {
         alleMap.forEach((uuid, expression) -> {
            expression.setSelected(false);
         });
      }
   }
}
