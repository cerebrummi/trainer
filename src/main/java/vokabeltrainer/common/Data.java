package vokabeltrainer.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import vokabeltrainer.CerebrummiNodes;
import vokabeltrainer.Command;
import vokabeltrainer.ExpressionComparator;
import vokabeltrainer.Settings;
import vokabeltrainer.cmd.DirectoryHelper;
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
import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.types.DatabaseDescription;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Repetition;
import vokabeltrainer.types.SearchType;
import vokabeltrainer.types.TrainingStatus;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbConjugation;
import vokabeltrainer.types.grammatical.VerbStrength;
import vokabeltrainer.types.grammatical.VerbType;
import vokabeltrainer.types.grammatical.expressionkind.Definitions;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

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

   // for importing expressions only, therefore NOT public
   static boolean importDatabase(String databasePath, String databaseName,
         boolean overwriteDatabaseNames)
   {
      return database.importDatabase(databasePath, databaseName,
            overwriteDatabaseNames);
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

   public static ExpressionTableModel findTranslations(Language language,
         String text, ExpressionKind kind, SearchType search, Chapter chapter,
         Command command, boolean sortForDate)
   {
      return getDataBaseAtomic().findTranslations(language, text, kind, search,
            chapter, command, sortForDate);
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

   public static ComboBoxModel<String> getOwnDatabasesComboBoxModel()
   {
      return getDataBaseAtomic().getOwnDatabasesComboBoxModel();
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

   public static void deleteExpressionsOfDatabase(String databaseChoosen)
   {
      getDataBaseAtomic().deleteExpressionsOfDatabase(databaseChoosen);
   }

   public static void restoreExpressions(List<Expression> list)
   {
      getDataBaseAtomic().restoreExpressions(list);
   }

   public static void shredderDeletedExpressions()
   {
      getDataBaseAtomic().shredderDeletedExpressions();
   }

   public static List<Expression> getAllSelectedExpressions(
         boolean exceptDoNotChange)
   {
      return getDataBaseAtomic()
            .findAllSelectedExpressionsList(exceptDoNotChange);
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

   public static SuccessTableModel findSuccessModel(Language direction,
         Repetition repetition)
   {
      return getDataBaseAtomic().findSuccessModel(direction, repetition);
   }

   public static void unselectAllExpressions()
   {
      getDataBaseAtomic().unselectAllExpressions();
   }

   public static boolean determineReloadDatabases()
   {
      if (new HashSet<>(Settings.getChosenDatabases())
            .equals(new HashSet<>(Settings.getOldChosenDatabases())))
      {
         return false;
      }
      // reload data
      initDataBase();
      Settings.setOldChosenDatabases(
            new LinkedList<>(Settings.getChosenDatabases()));
      return true;
   }

   public static String[] getAllOwnDistinctDatabaseDescriptions(
         boolean withSelfEvenIfNotInUseYet)
   {
      return getDataBaseAtomic()
            .getAllOwnDistinctDatabaseDescriptions(withSelfEvenIfNotInUseYet);
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
      private final static String DELETED_CSV = "DELETED.csv";
      private Set<Chapter> chapterSet = new HashSet<>();
      private final String[][] COLUMNAMES = { { "erste" } };

      private final boolean directoryOkay = checkDirectory();
      private final ConcurrentMap<UUID, Expression> alleMap = new ConcurrentHashMap<>(
            findNumberOfAllVocabulary() + 100);
      private final ConcurrentMap<UUID, Expression> newMap = new ConcurrentHashMap<>(
            100);
      private final ConcurrentMap<UUID, Expression> deletedMap = readFileRegular(
            DELETED_CSV, Database.TO_BE_DETERMINED, LetterForSaving.DELETED);

      DataBase()
      {
         for (LetterForSaving letter : LetterForSaving.values())
         {
            readFileRegular(letter.name() + ".csv", Database.TO_BE_DETERMINED,
                  letter);
         }

         for (Database database : Settings.getChosenDatabases())
         {
            for (LetterForSaving letter : LetterForSaving.values())
            {
               readFileAvailable(letter, database);
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
               if (!DirectoryHelper.makeExpressionDirectory(customDir))
               {
                  JOptionPane.showMessageDialog(Common.getjFrame(),
                        "Es hat beim Lesen einen Fehler gegeben.\n"
                              + "Wählen Sie unter Einstellungen einen anderen Speicherort.",
                        "Fehler", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
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
               .node(CerebrummiNodes.getNode());
         int numberOfVocabulary = preferences
               .getInt(CerebrummiNodes.getExpressionNode(), 0);
         if (numberOfVocabulary > 30000)
         {
            numberOfVocabulary = 30000;
         }
         return numberOfVocabulary;
      }

      // #########################################################
      // ######################## import #########################
      // #########################################################
      private boolean importDatabase(String databasePath, String databaseName,
            boolean overwriteDatabaseNames)
      {
         if (databasePath.endsWith(".zip"))
         {
            try (ZipFile zipFile = new ZipFile(databasePath);)
            {
               for (LetterForSaving letter : LetterForSaving.values())
               {
                  try
                  {
                     readZipFileImport(zipFile,
                           zipFile.getEntry(letter.name() + ".csv"), letter,
                           databaseName, overwriteDatabaseNames);
                  }
                  catch (Exception e1)
                  {
                     // nothing
                  }
               }
            }
            catch (Exception e)
            {
               return false;
            }

         }
         else
         {
            for (LetterForSaving letter : LetterForSaving.values())
            {
               readFileImport(databasePath, letter, databaseName,
                     overwriteDatabaseNames);
            }
         }

         return true;
      }

      // #########################################################
      // ######################## import #########################
      // #########################################################
      private void readFileImport(String path, LetterForSaving letter,
            String databaseName, boolean overwrite)
      {
         File file = new File(path + File.separator + letter.name() + ".csv");
         if (!file.exists())
         {
            return;
         }

         try (FileInputStream fis = new FileInputStream(file);
               InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.UTF_8);
               Reader reader = new BufferedReader(isr);)
         {
            readData(letter.name() + ".csv", reader, Database.IMPORTED, letter,
                  overwrite, databaseName, false);
         }
         catch (IOException e)
         {
            // nothing
         }
      }

      private void readZipFileImport(ZipFile zipFile, ZipEntry entry,
            LetterForSaving letter, String databaseName, boolean overwrite)
      {
         try (InputStream stream = zipFile.getInputStream(entry);
               InputStreamReader isr = new InputStreamReader(stream, "UTF-8");
               Reader reader = new BufferedReader(isr);)
         {
            readData(letter.name() + ".csv", reader, Database.IMPORTED, letter,
                  overwrite, databaseName, false);
         }
         catch (Exception e)
         {
            // nothing
         }
      }

      // #########################################################
      // ################# available databases ###################
      // #########################################################
      private void readFileAvailable(LetterForSaving letter, Database origin)
      {
         try (InputStream fis = Vocabulary.class.getResourceAsStream(
               origin.getFolder() + File.separator + letter.name() + ".csv");
               InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.UTF_8);
               Reader reader = new BufferedReader(isr);)
         {
            readData(letter.name() + ".csv", reader, origin, letter, false,
                  origin.getName(), true);
         }
         catch (IOException e)
         {
            // nothing
         }
      }

      // #########################################################
      // ####################### regular #########################
      // #########################################################
      private ConcurrentMap<UUID, Expression> readFileRegular(String filename,
            Database origin, LetterForSaving letter)
      {
         File file = null;

         if (!directoryOkay)
         {
            return new ConcurrentHashMap<UUID, Expression>(100);
         }

         file = new File(
               Settings.getExpressionPathFolder() + File.separator + filename);
         if (!file.exists())
         {
            return new ConcurrentHashMap<UUID, Expression>(100);
         }

         try (FileInputStream fis = new FileInputStream(file);
               InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.UTF_8);
               Reader reader = new BufferedReader(isr);)
         {
            return readData(filename, reader, origin, letter, false, null,
                  false);
         }
         catch (IOException e)
         {
            // nothing
         }

         return new ConcurrentHashMap<UUID, Expression>(100);
      }

      // #########################################################
      // #########################################################
      // #########################################################
      private ConcurrentMap<UUID, Expression> readData(String filename,
            Reader reader, Database origin, LetterForSaving letter,
            boolean overwrite, String databasename, boolean doNotChange)
            throws IOException
      {
         StringBuffer buffer = new StringBuffer();
         String input;
         int ch;
         while ((ch = reader.read()) > -1)
         {
            buffer.append((char) ch);
         }
         reader.close();
         input = buffer.toString().strip();

         if (input.isEmpty())
         {
            return new ConcurrentHashMap<UUID, Expression>(100);
         }

         String[] rows = input.split("\n");

         ConcurrentMap<UUID, Expression> map = new ConcurrentHashMap<>(
               rows.length + 100);
         int counter = 0;
         for (String row : rows)
         {
            if (counter == 0) // headerrow
            {
               counter++;
               continue;
            }
            if (row.strip().isEmpty())
            {
               continue;
            }

            try
            {

               Expression expression = new Expression(false, doNotChange);
               // read csv file row
               int index = 0;
               String[] entries = row.split("\t");

               expression.setUuid(UUID.fromString(entries[index]));
               if (alleMap.containsKey(expression.getUuid())
                     || map.containsKey(expression.getUuid()))
               {
                  expression.setUuid(UUID.randomUUID());
               }
               index++;
               Database database;
               try
               {
                  database = Database.valueOf(entries[index]);
               }
               catch (Exception e2)
               {
                  database = Database.UNKNOWN;
               }
               index++;
               if (Database.IMPORTED == origin && databasename != null
                     && overwrite)
               {
                  index++;
                  expression.setChapter(
                        new Chapter(databasename, entries[index], database));
               }
               else if (Settings.getAvailableDatabases().contains(origin))
               {
                  index++;
                  expression.setChapter(new Chapter(entries[index], origin));
               }
               else
               {
                  String nameOfDatabase = entries[index];
                  index++;
                  expression.setChapter(
                        new Chapter(nameOfDatabase, entries[index], database));
               }

               index++;
               expression.setGerman(entries[index]);
               index++;
               expression.setHebrew(entries[index]);
               index++;

               Definitions definitions = new Definitions();
               List<ExpressionKind> kinds = new ArrayList<>();
               String[] expressionKinds = entries[index].split(",");
               for (String kind : expressionKinds)
               {
                  try
                  {
                     definitions
                           .addExpressionKind(ExpressionKind.valueOf(kind));
                     kinds.add(ExpressionKind.valueOf(kind));
                  }
                  catch (Exception e)
                  {
                     // nothing
                  }
               }
               index++;
               Gender gender = Gender.PLEASE_CHOOSE;
               Numerus numerus = Numerus.PLEASE_CHOOSE;
               GrammaticalPerson person = GrammaticalPerson.PLEASE_CHOOSE;
               Binjan binjan = Binjan.PLEASE_CHOOSE;
               VerbConjugation conjugation = VerbConjugation.PLEASE_CHOOSE;
               VerbStrength strength = VerbStrength.PLEASE_CHOOSE;
               VerbType type = VerbType.PLEASE_CHOOSE;
               try
               {
                  gender = Gender.valueOf(entries[index]);
               }
               catch (Exception e)
               {
                  // nothing
               }
               index++;
               try
               {
                  numerus = Numerus.valueOf(entries[index]);
               }
               catch (Exception e)
               {
                  // nothing
               }
               index++;
               try
               {
                  person = GrammaticalPerson.valueOf(entries[index]);
               }
               catch (Exception e)
               {
                  // nothing
               }
               index++;
               try
               {
                  binjan = Binjan.valueOf(entries[index]);
               }
               catch (Exception e)
               {
                  // nothing
               }
               index++;
               try
               {
                  conjugation = VerbConjugation.valueOf(entries[index]);
               }
               catch (Exception e)
               {
                  // nothing
               }
               index++;
               try
               {
                  strength = VerbStrength.valueOf(entries[index]);
               }
               catch (Exception e)
               {
                  // nothing
               }
               index++;
               try
               {
                  type = VerbType.valueOf(entries[index]);
               }
               catch (Exception e)
               {
                  // nothing
               }
               for (ExpressionKind kind : kinds)
               {
                  definitions.setGrammaticalEnum(kind, gender);
                  definitions.setGrammaticalEnum(kind, numerus);
                  definitions.setGrammaticalEnum(kind, person);
                  definitions.setGrammaticalEnum(kind, binjan);
                  definitions.setGrammaticalEnum(kind, conjugation);
                  definitions.setGrammaticalEnum(kind, strength);
                  definitions.setGrammaticalEnum(kind, type);
               }
               expression.setDefinitions(definitions);
               index++;
               expression.setAdditionalInformation(entries[index]);
               index++;
               if (!entries[index].isBlank())
               {
                  expression.setSearchwordsGerman(entries[index].split(","));
               }
               index++;
               if (!entries[index].isBlank())
               {
                  expression.setSearchwordsHebrew(entries[index].split(","));
               }
               index++;
               try
               {
                  expression
                        .setLastModified(LocalDateTime.parse(entries[index]));
               }
               catch (Exception e)
               {
                  expression.toggleLastModified();
               }
               if (LetterForSaving.DELETED != letter)
               {
                  expression.setLetterForSaving(letter);
               }
               else
               {
                  expression.setLetterForSaving(
                        LetterForSaving.getLetter(expression.getGerman()));
               }

               if (!DELETED_CSV.equals(filename))
               {
                  alleMap.put(expression.getUuid(), expression);
               }
               else
               {
                  map.put(expression.getUuid(), expression);
               }

               if (!expression.getChapter().getName().isEmpty()
                     && !DELETED_CSV.equals(filename))
               {
                  chapterSet.add(expression.getChapter());
               }
            }
            catch (Exception e1)
            {
               // nothing broken expressions are not read
               e1.printStackTrace();
            }
         }
         return map;
      }

      // ############################################################

      private ExpressionTableModel findTranslations(Language language,
            String text, ExpressionKind kind, SearchType search,
            Chapter chapter, Command command, boolean sortForDate)
      {
         Collection<Expression> expressions = null;

         if (text == null && kind == null && search == null && chapter == null
               && command != null)
         {
            if (Command.ALL_SELECTED.equals(command))
            {
               List<Expression> selectedExpressions = findAllSelectedExpressionsList(
                     false);
               Collections.sort(selectedExpressions,
                     new ExpressionComparator(language, sortForDate));
               return new ExpressionTableModel(
                     convertToExpressionModelArray(selectedExpressions),
                     COLUMNAMES);
            }
         }
         else if (text == null && kind == null && search == null
               && chapter != null && command == null)
         {
            return new ExpressionTableModel(convertToExpressionModelArray(
                  findExpressionsChapterSorted(chapter, language, sortForDate)),
                  COLUMNAMES);
         }
         else if (text == null && kind != null && search == null
               && chapter == null && command == null)
         {
            return new ExpressionTableModel(convertToExpressionModelArray(
                  findSortedExpressionsOfKind(kind, language, sortForDate)),
                  COLUMNAMES);
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
                        + ", command = " + command + ", sortForDate = "
                        + sortForDate);
         }

         return new ExpressionTableModel(
               convertToExpressionModelArray(filterExpressions(text, language,
                     search, expressions, sortForDate)),
               COLUMNAMES);
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
            SearchType search, Collection<Expression> expressions,
            boolean sortForDate)
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
         Collections.sort(list,
               new ExpressionComparator(language, sortForDate));

         return list;
      }

      private List<Expression> findExpressionsChapterSorted(Chapter chapter,
            Language language, boolean sortForDate)
      {
         List<Expression> list = findExpressionsChapter(chapter);
         Collections.sort(list,
               new ExpressionComparator(language, sortForDate));
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

      private List<Expression> findSortedExpressionsOfKind(ExpressionKind kind,
            Language language, boolean sortForDate)
      {
         List<Expression> list = findExpressionsOfKind(kind);
         Collections.sort(list,
               new ExpressionComparator(language, sortForDate));
         return list;
      }

      private List<Expression> findExpressionsOfKind(ExpressionKind kind)
      {
         List<Expression> list = new ArrayList<>();
         for (Expression expression : alleMap.values())
         {
            if (expression.getDefinitions().getExpressionKindSet()
                  .contains(kind))
            {
               list.add(expression);
            }
         }
         return list;
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

      private ComboBoxModel<String> getOwnDatabasesComboBoxModel()
      {
         return new DefaultComboBoxModel<String>(
               this.getAllOwnDistinctDatabaseDescriptions(true));
      }

      private List<String> getChapterListForEditor()
      {
         List<String> chapterList = new ArrayList<>();
         List<Database> availableDatabases = Settings.getAvailableDatabases();
         for (Chapter chapter : chapterSet)
         {
            if (!availableDatabases.contains(chapter.getOrigin()))
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

      private List<Expression> findAllSelectedExpressionsList(
            boolean exceptDoNotChange)
      {
         List<Expression> list = new ArrayList<>();

         for (Expression expression : alleMap.values())
         {
            if (exceptDoNotChange && expression.isDoNotChange())
            {
               continue;
            }
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
         }

         reloadChapterSet();
      }

      public void deleteExpressionsOfDatabase(String databaseChoosen)
      {
         deleteExpressions(findAllExpressionsOfDatabase(databaseChoosen));
      }

      private void integrateNewExpressions()
      {
         for (Expression expression : newMap.values())
         {
            alleMap.put(expression.getUuid(), expression);
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
            List<Expression> listSelected = findAllSelectedExpressionsList(
                  false);
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

      private Set<Expression> findOldExpressionsToBeTested(
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

      private String[] getAllOwnDistinctDatabaseDescriptions(
            boolean withSelfEvenIfNotInUseYet)
      {
         List<DatabaseDescription> result = alleMap.values().stream()
               .filter(expression -> expression.isDoChange())
               .map(Expression::getChapter).map(Chapter::getDatabaseDescription)
               .distinct().collect(Collectors.toList());
         if (withSelfEvenIfNotInUseYet
               && !result.contains(new DatabaseDescription(Database.SELF)))
         {
            result.add(new DatabaseDescription(Database.SELF));
         }
         Collections.sort(result);
         return result.stream().map(DatabaseDescription::getDatabaseName)
               .toArray(String[]::new);
      }

      private List<Expression> findAllExpressionsOfDatabase(
            String databaseChoosen)
      {
         return alleMap
               .values().stream().filter(expression -> expression.getChapter()
                     .getDatabaseName().equals(databaseChoosen))
               .collect(Collectors.toList());
      }
   }
}
