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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import vokabeltrainer.ChapterDatabaseComparator;
import vokabeltrainer.Command;
import vokabeltrainer.ExpressionComparator;
import vokabeltrainer.cmd.DirectoryHelper;
import vokabeltrainer.editing.ExchangeLetter;
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.LetterType;
import vokabeltrainer.panels.statistics.StatisticsTableModel;
import vokabeltrainer.panels.statistics.StatisticsTableRow;
import vokabeltrainer.panels.success.table.SuccessTableModel;
import vokabeltrainer.panels.success.table.SuccessTableRow;
import vokabeltrainer.panels.success.table.SuccessTableRowComparator;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTableModel;
import vokabeltrainer.panels.trainer.dialog.table.TrainingTableRow;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.resources.vocabulary.Vocabulary;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.types.DatabaseDescription;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.FieldOfTraining;
import vokabeltrainer.types.LLType;
import vokabeltrainer.types.LanguageDirection;
import vokabeltrainer.types.Repetition;
import vokabeltrainer.types.SearchType;
import vokabeltrainer.types.SortingIndex;
import vokabeltrainer.types.SortingType;
import vokabeltrainer.types.TrainingStatus;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;
import vokabeltrainer.types.grammatical.expressionkind.Definitions;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

// Maps und Sets werden nie herausgegeben!
public final class Data
{
   private static final AtomicBoolean databaseInUse = new AtomicBoolean(false);
   private static volatile UUID uuidDataBaseLock;
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

   public static ExpressionTableModel findTranslations(String text,
         ExpressionKind kind, SearchType search, Chapter chapter,
         Command command, SortingType sortingType, Integer levelOfDifficulty,
         Direction direction)
   {
      return getDataBaseAtomic().findTranslations(text, kind, search, chapter,
            command, sortingType, levelOfDifficulty, direction);
   }

   public static ExpressionTableModel findTranslationsDeletedWords()
   {
      return getDataBaseAtomic().findTranslationsDeletedWords();
   }

   public static ComboBoxModel<String> getChapterComboBoxModel()
   {
      return getDataBaseAtomic().getChapterComboBoxModel();
   }

   public static ComboBoxModel<Chapter> getChapterComboBoxModelAsChapter()
   {
      return getDataBaseAtomic().getChapterComboBoxModelAsChapter();
   }

   public static ComboBoxModel<String> getOwnDatabasesComboBoxModel()
   {
      return getDataBaseAtomic().getOwnDatabasesComboBoxModel();
   }

   public static String getAllSelectedExpressionsAsString(SortingType sortingType, Direction language)
   {
      return getDataBaseAtomic().getAllSelectedExpressionsAsString(sortingType, language);
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

   public static TrainingTableModel findTrainingModel(
         LanguageDirection languageDirection, FieldOfTraining fieldOfTraining)
   {
      return getDataBaseAtomic().findTrainingModel(languageDirection,
            fieldOfTraining);
   }

   public static StatisticsTableModel findStatisticsModel()
   {
      return getDataBaseAtomic().findStatisticsModel();
   }

   public static SuccessTableModel findSuccessModel(Direction direction,
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
      return getDataBaseAtomic().getAllOwnDistinctDatabaseDescriptions(
            withSelfEvenIfNotInUseYet, false);
   }

   public static ComboBoxModel<String> getInternalDatabasesComboBoxModel()
   {
      return getDataBaseAtomic().getInternalDatabasesComboBoxModel();
   }

   public static void copyInternalDatabase(Database database,
         boolean overwriteDatabaseName, String databaseName)
   {
      getDataBaseAtomic().copyInternalDatabase(database, overwriteDatabaseName,
            databaseName);
   }

   public static Chapter getChapterWithLastModifiedDate()
   {
      return getDataBaseAtomic().getChapterWithLastModifiedDate();
   }

   public static void moveSelectedExpressionsToChapter(String toChapter)
   {
      getDataBaseAtomic().moveSelectedExpressionToChapter(toChapter);
   }

   public static void moveSelectedExpressionsToDatabase(String toDatabase)
   {
      getDataBaseAtomic().moveSelectedExpressionsToDatabase(toDatabase);
   }

   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
   // #########################################################
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
            DELETED_CSV, Database.TO_BE_DETERMINED, LetterForLoading.DELETED);
      private Translator translator = Common.getTranslator();

      DataBase()
      {
         Stream.of(LetterForSaving.values())
               .forEach(letter -> readFileRegular(letter.name() + ".csv",
                     Database.TO_BE_DETERMINED, letter));

         Settings.getChosenDatabases().stream()
               .forEach(database -> Stream.of(LetterForSaving.values())
                     .forEach(letter -> readFileAvailable(letter, database)));

         File customDir = new File(Settings.getTrainingPath());
         if (!customDir.exists())
         {
            customDir.mkdirs();
         }
         else
         {
            File ownToGerman = new File(
                  Settings.getTrainingPath() + File.separator
                        + LanguageDirection.OWN_TO_GERMAN.name() + ".txt");
            File ownToHebrew = new File(
                  Settings.getTrainingPath() + File.separator
                        + LanguageDirection.OWN_TO_HEBREW.name() + ".txt");
            File ownToSwedish = new File(
                  Settings.getTrainingPath() + File.separator
                        + LanguageDirection.OWN_TO_SWEDISH.name() + ".txt");

            File germanToOwn = new File(
                  Settings.getTrainingPath() + File.separator
                        + LanguageDirection.GERMAN_TO_OWN.name() + ".txt");
            File hebrewToOwn = new File(
                  Settings.getTrainingPath() + File.separator
                        + LanguageDirection.HEBREW_TO_OWN.name() + ".txt");
            File swedishToOwn = new File(
                  Settings.getTrainingPath() + File.separator
                        + LanguageDirection.SWEDISH_TO_OWN.name() + ".txt");

            if (ownToGerman.exists())
            {
               readTrainingFile(ownToGerman, LanguageDirection.OWN_TO_GERMAN);
            }

            if (ownToHebrew.exists())
            {
               readTrainingFile(ownToHebrew, LanguageDirection.OWN_TO_HEBREW);
            }

            if (ownToSwedish.exists())
            {
               readTrainingFile(ownToSwedish, LanguageDirection.OWN_TO_SWEDISH);
            }

            if (germanToOwn.exists())
            {
               readTrainingFile(germanToOwn, LanguageDirection.GERMAN_TO_OWN);
            }

            if (hebrewToOwn.exists())
            {
               readTrainingFile(hebrewToOwn, LanguageDirection.HEBREW_TO_OWN);
            }

            if (swedishToOwn.exists())
            {
               readTrainingFile(swedishToOwn, LanguageDirection.SWEDISH_TO_OWN);
            }
         }
      }

      private Chapter getChapterWithLastModifiedDate()
      {
         if (chapterSet.isEmpty() || alleMap.isEmpty())
         {
            return new Chapter();
         }
         return alleMap.values().stream()
               .sorted(new ExpressionComparator(SortingType.DATE)).findFirst()
               .get().getChapter();
      }

      private void readTrainingFile(File german,
            LanguageDirection languageDirection)
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
               if (repetition == null)
               {
                  continue;
               }
               int trys = Integer.valueOf(items[3]);
               if (trys < 1)
               {
                  trys = 1;
               }
               if (trys > 4)
               {
                  trys = 4;
               }
               TrainingStatus trainingstatus = new TrainingStatus(repetition,
                     trys, nextDate);
               Expression expression = alleMap.get(uuid);
               if (expression != null
                     && (LanguageDirection.OWN_TO_GERMAN == languageDirection
                           || LanguageDirection.OWN_TO_SWEDISH == languageDirection
                           || LanguageDirection.OWN_TO_HEBREW == languageDirection))
               {
                  expression.setTrainingStatusDToLL(trainingstatus);
               }
               else if (expression != null
                     && (LanguageDirection.GERMAN_TO_OWN == languageDirection
                           || LanguageDirection.SWEDISH_TO_OWN == languageDirection
                           || LanguageDirection.HEBREW_TO_OWN == languageDirection))
               {
                  expression.setTrainingStatusLLToD(trainingstatus);
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
               if (!DirectoryHelper.makeDirectory(customDir))
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

      public void copyInternalDatabase(Database database,
            boolean overwriteDatabaseName, String databaseName)
      {
         Stream.of(LetterForSaving.values())
               .forEach(letter -> readFileAvailable(letter, database,
                     overwriteDatabaseName, databaseName));
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
            Stream.of(LetterForSaving.values())
                  .forEach(letter -> readFileImport(databasePath, letter,
                        databaseName, overwriteDatabaseNames));
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
         try (InputStream fis = Vocabulary.class
               .getResourceAsStream(origin.getFolder() + File.separator
                     + letter.name() + ".csv");
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
      // ############## copy available databases #################
      // #########################################################
      private void readFileAvailable(LetterForSaving letter, Database origin,
            boolean overwriteDatabaseName, String databaseName)
      {
         try (InputStream fis = Vocabulary.class
               .getResourceAsStream(origin.getFolder() + File.separator
                     + letter.name() + ".csv");
               InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.UTF_8);
               Reader reader = new BufferedReader(isr);)
         {
            if (overwriteDatabaseName)
            {
               readData(letter.name() + ".csv", reader, Database.COPY, letter,
                     true, databaseName, false);
            }
            else
            {
               readData(letter.name() + ".csv", reader, Database.COPY, letter,
                     false, origin.getName() + " Kopie", false);
            }
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
            Database origin, Letter letter)
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
      // ################# read Data #############################
      // #########################################################
      private ConcurrentMap<UUID, Expression> readData(String filename,
            Reader reader, Database origin, Letter letter, boolean overwrite,
            String databasename, boolean doNotChange) throws IOException
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

               if (Database.COPY == origin)
               {
                  expression.setUuid(UUID.randomUUID());
               }
               else
               {
                  expression.setUuid(UUID.fromString(entries[index]));
               }
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
                  expression.setChapter(new Chapter(databasename,
                        entries[index], Database.SELF));
               }
               else if (Database.COPY == origin)
               {
                  index++;
                  expression.setChapter(new Chapter(databasename,
                        entries[index], Database.SELF));
               }
               else if (Settings.getAvailableDatabases().contains(origin))
               {
                  index++;
                  expression.setChapter(
                        new Chapter(origin.getName(), entries[index], origin));
               }
               else
               {
                  String nameOfDatabase = entries[index];
                  index++;
                  expression.setChapter(
                        new Chapter(nameOfDatabase, entries[index], database));
               }

               index++;
               expression.setOwnLanguage(entries[index]);
               index++;
               expression.getLL()
                     .setSimpleHebrew(Boolean.valueOf(entries[index]));
               index++;
               expression.getLL().setHebrew(entries[index]);
               if (expression.getLL().getHebrew()
                     .contains(ExchangeLetter.SSIN.getUnicode()))
               {
                  expression.getLL()
                        .setHebrew(LetterHelper.turnExchangeSsinIntoNikudSsin(
                              expression.getLL().getHebrew()));
               }
               index++;
               expression.getLL().setHebrewPlene(entries[index]);
               if (expression.getLL().getHebrewPlene()
                     .contains(ExchangeLetter.SSIN.getUnicode()))
               {
                  expression.getLL().setHebrewPlene(
                        LetterHelper.turnExchangeSsinIntoNikudSsin(
                              expression.getLL().getHebrew()));
               }
               index++;
               expression.getLL().setHebrewDefektiv(entries[index]);
               if (expression.getLL().getHebrewDefektiv()
                     .contains(ExchangeLetter.SSIN.getUnicode()))
               {
                  expression.getLL().setHebrewDefektiv(
                        LetterHelper.turnExchangeSsinIntoNikudSsin(
                              expression.getLL().getHebrew()));
               }
               index++;
               expression.getLL().setSwedish(entries[index]);
               index++;
               expression.getLL().setGerman(entries[index]);

               if (!expression.getLL().getSwedish().isBlank())
               {
                  expression.getLL().setLltype(LLType.SWEDISH);
                  expression.getChapter().getDatabaseDescription()
                        .setLlType(LLType.SWEDISH);
               }
               else if (!expression.getLL().getGerman().isBlank())
               {
                  expression.getLL().setLltype(LLType.GERMAN);
                  expression.getChapter().getDatabaseDescription()
                        .setLlType(LLType.GERMAN);
               }
               else
               {
                  expression.getLL().setLltype(LLType.HEBREW);
                  expression.getChapter().getDatabaseDescription()
                        .setLlType(LLType.HEBREW);
               }
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
               Gender gender = Gender.GENDER_NA;
               Numerus numerus = Numerus.NUMERUS_NA;
               GrammaticalPerson person = GrammaticalPerson.GRAMMATICALPERSON_NA;
               Binjan binjan = Binjan.BINJAN_NA;
               VerbTimes times = VerbTimes.VERBTIMES_NA;
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
                  times = VerbTimes.valueOf(entries[index]);
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
                  definitions.setGrammaticalEnum(kind, times);
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
               index++;
               try
               {
                  expression.setSortingIndex(entries[index]);
                  SortingIndex.setCounter(expression.getSortingIndex());
               }
               catch (Exception e)
               {
                  expression.setSortingIndex(
                        String.valueOf(SortingIndex.getCounter()));
               }
               index++;
               try
               {
                  expression.setLevel(Integer.valueOf(entries[index].strip()));
               }
               catch (Exception e)
               {
                  // nothing
               }

               if (LetterForLoading.DELETED != letter)
               {
                  expression.setLetterForSaving(letter);
               }
               else
               {
                  expression.setLetterForSaving(
                        LetterForSaving.getLetter(expression.getOwnLanguage()));
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
               // nothing: broken expressions are not read
               e1.printStackTrace();
            }
         }
         return map;
      }

      // ############################################################

      private ExpressionTableModel findTranslations(String text,
            ExpressionKind kind, SearchType search, Chapter chapter,
            Command command, SortingType sortingType, Integer levelOfDifficulty,
            Direction direction)
      {
         Collection<Expression> expressions = null;

         if (levelOfDifficulty != null)
         {
            List<Expression> selectedExpressions = findAllLevelOfDifficultyExpressionList(
                  levelOfDifficulty);
            Collections.sort(selectedExpressions,
                  new ExpressionComparator(sortingType));
            return new ExpressionTableModel(
                  convertToExpressionModelArray(selectedExpressions),
                  COLUMNAMES);
         }
         else if (Command.ALL_SELECTED == command)
         {
            List<Expression> selectedExpressions = findAllSelectedExpressionsList(
                  false);
            Collections.sort(selectedExpressions,
                  new ExpressionComparator(sortingType));
            return new ExpressionTableModel(
                  convertToExpressionModelArray(selectedExpressions),
                  COLUMNAMES);
         }
         else if (text == null && kind == null && search == null
               && chapter != null && command == null)
         {
            return new ExpressionTableModel(
                  convertToExpressionModelArray(findExpressionsChapterSorted(
                        chapter, direction, sortingType)),
                  COLUMNAMES);
         }
         else if (text == null && kind != null && search == null
               && chapter == null && command == null)
         {
            return new ExpressionTableModel(convertToExpressionModelArray(
                  findSortedExpressionsOfKind(kind, direction, sortingType)),
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
                        + "Direction = " + direction + ", kind = " + kind
                        + ", search = " + search + "\n" + "chapter = " + chapter
                        + ", command = " + command + ", sortForDate = "
                        + sortingType);
         }

         return new ExpressionTableModel(
               convertToExpressionModelArray(filterExpressions(text, direction,
                     search, expressions, sortingType)),
               COLUMNAMES);
      }

      private List<Expression> findAllLevelOfDifficultyExpressionList(
            Integer levelOfDifficulty)
      {
         return alleMap.values().stream().filter(
               expression -> levelOfDifficulty.equals(expression.getLevel()))
               .collect(Collectors.toList());
      }

      private ExpressionTableModel findTranslationsDeletedWords()
      {
         Collection<Expression> expressions = deletedMap.values();
         Expression[] expressionArray = expressions
               .toArray(new Expression[expressions.size()]);
         Arrays.sort(expressionArray, new ExpressionComparator(SortingType.DATE));

         return new ExpressionTableModel(
               convertToExpressionModelArray(expressionArray), COLUMNAMES);
      }

      private List<Expression> filterExpressions(String text,
            Direction language, SearchType search,
            Collection<Expression> expressions, SortingType sortingType)
      {
         if (expressions == null)
         {
            return Collections.emptyList();
         }

         Predicate<Expression> ownSearchword = expression -> equalsGermanSearchWord(
               text, expression);
         Predicate<Expression> ownWordstart = expression -> equalsGermanWordStart(
               text, expression);
         Predicate<Expression> hebrewSearchword = expression -> equalsHebrewSearchWord(
               text, expression);
         Predicate<Expression> hebrewWordstart = expression -> equalsNewWordStart(
               text, expression);

         Predicate<Expression> ownToNew = expression -> Direction.OWN_TO_NEW
               .equals(language);
         Predicate<Expression> newToOwn = expression -> Direction.NEW_TO_OWN
               .equals(language);

         Predicate<Expression> searchWord = expression -> SearchType.SEARCHWORD
               .equals(search);
         Predicate<Expression> wordStart = expression -> SearchType.WORDSTART
               .equals(search);

         Predicate<Expression> germanToHebrewSearchword = ownToNew
               .and(searchWord).and(ownSearchword);
         Predicate<Expression> germanToHebrewWordstart = ownToNew.and(wordStart)
               .and(ownWordstart);
         Predicate<Expression> hebrewToGermanSearchword = newToOwn
               .and(searchWord).and(hebrewSearchword);
         Predicate<Expression> hebrewToGermanWordstart = newToOwn.and(wordStart)
               .and(hebrewWordstart);

         return expressions.stream()
               .filter(germanToHebrewSearchword.or(germanToHebrewWordstart)
                     .or(hebrewToGermanSearchword).or(hebrewToGermanWordstart))
               .sorted(new ExpressionComparator(sortingType))
               .collect(Collectors.toList());
      }

      private List<Expression> findExpressionsChapterSorted(Chapter chapter,
            Direction language, SortingType sortingType)
      {
         List<Expression> list = findExpressionsChapter(chapter);
         Collections.sort(list,
               new ExpressionComparator(sortingType, language));
         return list;
      }

      private List<Expression> findExpressionsChapter(Chapter chapter)
      {
         return alleMap.values().stream()
               .filter(expression -> chapter.equals(expression.getChapter()))
               .collect(Collectors.toList());
      }

      private List<Expression> findSortedExpressionsOfKind(ExpressionKind kind,
            Direction language, SortingType sortingType)
      {
         return alleMap.values().stream()
               .filter(expression -> expression.getDefinitions()
                     .getExpressionKindSet().contains(kind))
               .sorted(new ExpressionComparator(sortingType))
               .collect(Collectors.toList());
      }

      private boolean equalsGermanSearchWord(String text, Expression expression)
      {
         final String trimmedText = text.trim();

         return expression.getSearchwordsGerman().stream()
               .anyMatch(word -> word.equalsIgnoreCase(trimmedText));
      }

      private boolean equalsHebrewSearchWord(String text, Expression expression)
      {
         final String trimmedText = text.trim();

         List<LetterForAnalysis> textList = LetterHelper
               .findLetterForAnalysisList(trimmedText, LetterType.HEBREW);

         List<List<LetterForAnalysis>> searchWordListofLists = LetterHelper
               .findListofNikudLetterForAnalysisListsHebrewSearchwords(
                     expression);

         return searchWordListofLists.stream()
               .filter(
                     searchWordList -> textList.size() == searchWordList.size())
               .filter(searchWordList -> allLettersMatchingInEqualSizedLists(
                     searchWordList, textList, LetterType.HEBREW))
               .findAny().isPresent();
      }

      private boolean allLettersMatchingInEqualSizedLists(
            List<LetterForAnalysis> list1, List<LetterForAnalysis> list2, LetterType letterType)
      {
         return IntStream.range(0, list1.size())
               .allMatch((i) -> LetterForAnalysis.isEqual(list1.get(i),
                     list2.get(i), letterType));
      }

      private boolean equalsNewWordStart(String text, Expression expression)
      {
         text = text.trim();

         switch (Settings.getLanguageInput())
         {
         case GERMAN:
            if(expression.getLL().getGerman().isBlank())
            {
               return false;
            }
            List<LetterForAnalysis> textList = LetterHelper
                  .findLetterForAnalysisList(text, LetterType.GERMAN);
    
            List<LetterForAnalysis> expressionList = LetterHelper
                  .findLetterForAnalysisList(expression.getLL().getGerman(),
                        LetterType.GERMAN);
            if (textList.size() > expressionList.size())
            {
               return false;
            }
            return allLettersMatchingInEqualSizedLists(textList,
                  expressionList, LetterType.GERMAN);
         case SIMPLE:
         case PLENE_DEFEKTIV:
            return searchForHebrew(text, expression);
         case SWEDISH:
            if(expression.getLL().getSwedish().isBlank())
            {
               return false;
            }
            List<LetterForAnalysis> textList1 = LetterHelper
                  .findLetterForAnalysisList(text, LetterType.SWEDISH);
            List<LetterForAnalysis> expressionList1 = LetterHelper
                  .findLetterForAnalysisList(expression.getLL().getSwedish(),
                        LetterType.SWEDISH);
            if (textList1.size() > expressionList1.size())
            {
               return false;
            }
            return allLettersMatchingInEqualSizedLists(textList1,
                  expressionList1, LetterType.SWEDISH);
         default:
            break;
         }
         return false;
      }

      public boolean searchForHebrew(String text, Expression expression)
      {
         List<LetterForAnalysis> textList = LetterHelper
               .findLetterForAnalysisList(text, LetterType.HEBREW);

         if (expression.getLL().isSimpleHebrew())
         {
            List<LetterForAnalysis> expressionList = LetterHelper
                  .findLetterForAnalysisList(expression.getLL().getHebrew(),
                        LetterType.HEBREW);
            if (textList.size() > expressionList.size())
            {
               return false;
            }

            return allLettersMatchingInEqualSizedLists(textList,
                  expressionList, LetterType.HEBREW);
         }

         List<LetterForAnalysis> expressionListPlene = LetterHelper
               .findLetterForAnalysisList(expression.getLL().getHebrewPlene(),
                     LetterType.HEBREW);
         if (textList.size() <= expressionListPlene.size())
         {
            if (IntStream.range(0, textList.size())
                  .allMatch((i) -> LetterForAnalysis.isEqual(textList.get(i),
                        expressionListPlene.get(i), LetterType.HEBREW)))
            {
               return true;
            }
         }

         List<LetterForAnalysis> expressionListDefektiv = LetterHelper
               .findLetterForAnalysisList(
                     expression.getLL().getHebrewDefektiv(), LetterType.HEBREW);
         if (textList.size() <= expressionListDefektiv.size())
         {
            if (IntStream.range(0, textList.size())
                  .allMatch((i) -> LetterForAnalysis.isEqual(textList.get(i),
                        expressionListDefektiv.get(i), LetterType.HEBREW)))
            {
               return true;
            }
         }

         return false;
      }

      private boolean equalsGermanWordStart(String text, Expression expression)
      {
         text = text.trim();
         return expression.getOwnLanguage().startsWith(text);
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

      private ComboBoxModel<Chapter> getChapterComboBoxModelAsChapter()
      {
         return new DefaultComboBoxModel<Chapter>(chapterSet.stream()
               .filter(chapter -> chapter.getDatabaseDescription().getDatabase()
                     .equals(Database.SELF))
               .sorted((c1, c2) -> ChapterDatabaseComparator.compareChapter(c1,
                     c2))
               .toArray(size -> new Chapter[size]));
      }

      private ComboBoxModel<String> getOwnDatabasesComboBoxModel()
      {
         return new DefaultComboBoxModel<String>(
               this.getAllOwnDistinctDatabaseDescriptions(true, true));
      }

      private ComboBoxModel<String> getInternalDatabasesComboBoxModel()
      {
         return new DefaultComboBoxModel<String>(
               this.getInternalDatabaseNames());
      }

      private String[] getChapterArrayForEditor()
      {
         final List<Database> availableDatabases = Settings
               .getAvailableDatabases();

         List<String> chapterList = chapterSet.stream().filter(
               chapter -> !availableDatabases.contains(chapter.getOrigin()))
               .sorted().map(chapter -> chapter.getName())
               .collect(Collectors.toCollection(LinkedList::new));

         chapterList.add(0, "");

         return chapterList.stream().toArray(String[]::new);
      }

      private Chapter[] getChapterArray()
      {
         return chapterSet.stream().sorted(new ChapterDatabaseComparator())
               .toArray(Chapter[]::new);
      }

      private String getAllSelectedExpressionsAsString(SortingType sortingType, Direction language)
      {
         return alleMap.values().stream()
               .filter(expression -> expression.isSelected())
               .filter(expression -> expression.isDoChange())
               .sorted(new ExpressionComparator(sortingType))
               .map(expression -> expression.getCopyLines(language))
               .collect(Collectors.joining("\n\n"));
      }

      private void clearAllSelectedExpressions()
      {
         alleMap.values().stream()
               .forEach(expression -> expression.setSelected(false));
      }

      private List<Expression> findAllSelectedExpressionsList(
            boolean exceptDoNotChange)
      {
         if (exceptDoNotChange)
         {
            return alleMap.values().stream()
                  .filter(expression -> expression.isDoChange())
                  .filter(expression -> expression.isSelected())
                  .collect(Collectors.toList());
         }
         return alleMap.values().stream()
               .filter(expression -> expression.isSelected())
               .collect(Collectors.toList());
      }

      private void deleteExpressions(List<Expression> list)
      {
         list.stream().filter(expression -> expression.isDoChange())
               .forEach(expression -> expressionDeleteOperation(expression));
         reloadChapterSet();
      }

      private void expressionDeleteOperation(Expression expression)
      {
         deletedMap.put(expression.getUuid(), expression);
         alleMap.remove(expression.getUuid(), expression);
         newMap.remove(expression.getUuid(), expression);
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
         chapterSet = alleMap.entrySet().stream()
               .map(entry -> entry.getValue().getChapter())
               .collect(Collectors.toSet());
      }

      private TrainingTableModel findTrainingModel(
            LanguageDirection languageDirection,
            FieldOfTraining fieldOfTraining)
      {
         TrainingTableRow[][] data = null;
         final Set<Expression> oldToBeTested = findOldExpressionsToBeTested(
               languageDirection, fieldOfTraining);

         Predicate<Chapter> german = c -> c.getDatabaseDescription()
               .getLlType() == LLType.GERMAN;
         Predicate<Chapter> swedish = c -> c.getDatabaseDescription()
               .getLlType() == LLType.SWEDISH;
         Predicate<Chapter> hebrew = c -> c.getDatabaseDescription()
               .getLlType() == LLType.HEBREW;

         switch (fieldOfTraining)
         {
         case AREA_CHAPTER:
            if (LanguageDirection.OWN_TO_HEBREW == languageDirection
                  || LanguageDirection.HEBREW_TO_OWN == languageDirection)
            {
               data = chapterSet.stream().filter(hebrew).sorted()
                     .map(chapter -> makeChapterRow(languageDirection,
                           fieldOfTraining, oldToBeTested, chapter))
                     .map(trainingTableRow -> new TrainingTableRow[] {
                           trainingTableRow })
                     .toArray(size -> new TrainingTableRow[size][1]);
            }
            else if (LanguageDirection.OWN_TO_SWEDISH == languageDirection
                  || LanguageDirection.SWEDISH_TO_OWN == languageDirection)
            {
               data = chapterSet.stream().filter(swedish).sorted()
                     .map(chapter -> makeChapterRow(languageDirection,
                           fieldOfTraining, oldToBeTested, chapter))
                     .map(trainingTableRow -> new TrainingTableRow[] {
                           trainingTableRow })
                     .toArray(size -> new TrainingTableRow[size][1]);
            }
            else
            {
               data = chapterSet.stream().filter(german).sorted()
                     .map(chapter -> makeChapterRow(languageDirection,
                           fieldOfTraining, oldToBeTested, chapter))
                     .map(trainingTableRow -> new TrainingTableRow[] {
                           trainingTableRow })
                     .toArray(size -> new TrainingTableRow[size][1]);
            }
            break;
         case AREA_SELECTED:
            List<Expression> listSelected = findAllSelectedExpressionsList(
                  false);
            TrainingTableRow selectedRow = makeSelectedRow(languageDirection,
                  fieldOfTraining, oldToBeTested, listSelected);
            data = new TrainingTableRow[1][1];
            data[0][0] = selectedRow;
         case AREA_SELECTED_TEMPORARY:
            List<Expression> listSelected2 = findAllSelectedExpressionsList(
                  false);
            TrainingTableRow selectedRow2 = makeSelectedRow(languageDirection,
                  fieldOfTraining, null, listSelected2);
            data = new TrainingTableRow[1][1];
            data[0][0] = selectedRow2;
         }

         return new TrainingTableModel(data);
      }

      private TrainingTableRow makeSelectedRow(
            LanguageDirection languageDirection,
            FieldOfTraining fieldOfTraining,
            final Set<Expression> oldToBeTested, List<Expression> listSelected)
      {
         TrainingTableRow selectedRow = new TrainingTableRow();
         selectedRow.setFieldOfTraining(fieldOfTraining);
         selectedRow.setField(
               translator.realisticTranslate(Translation.AUSGEWAEHLTE_WOERTER));
         selectedRow.setExpressionListOldWords(oldToBeTested);
         if (oldToBeTested != null)
         {
            selectedRow.setToBeRepeatedWords(oldToBeTested.size());
            selectedRow.setExpressionListNewWords(
                  findNotStudiedWords(languageDirection, listSelected));
            selectedRow.setNotStudiedWords(
                  selectedRow.getExpressionListNewWords().size());
            selectedRow.setAmountOfNewWords(selectedRow.getNotStudiedWords());
            selectedRow.setFieldDone(selectedRow.getNotStudiedWords() == 0
                  && selectedRow.getToBeRepeatedWords() == 0);
            selectedRow.setStarted(selectedRow.getToBeRepeatedWords() > 0);
         }
         else
         {
            selectedRow.setToBeRepeatedWords(0);
            selectedRow.setExpressionListNewWords(
                   listSelected);
            selectedRow.setNotStudiedWords(
                  selectedRow.getExpressionListNewWords().size());
            selectedRow.setAmountOfNewWords(selectedRow.getNotStudiedWords());
            selectedRow.setFieldDone(selectedRow.getNotStudiedWords() == 0
                  && selectedRow.getToBeRepeatedWords() == 0);
            selectedRow.setStarted(selectedRow.getToBeRepeatedWords() > 0);
         }

         return selectedRow;
      }

      private TrainingTableRow makeChapterRow(
            LanguageDirection languageDirection,
            FieldOfTraining fieldOfTraining,
            final Set<Expression> oldToBeTested, Chapter chapter)
      {
         List<Expression> listChapter = this.findExpressionsChapter(chapter);
         TrainingTableRow chapterRow = new TrainingTableRow();
         chapterRow.setFieldOfTraining(fieldOfTraining);
         chapterRow.setChapter(chapter);
         chapterRow
               .setField(chapter.getDatabaseName() + " | " + chapter.getName());
         chapterRow.setExpressionListOldWords(
               findSetOfOldExpressionsToBeTestedPerChapter(chapter,
                     oldToBeTested));
         chapterRow.setToBeRepeatedWords(
               findNumberOfOldToBeTestedPerChapter(chapter, oldToBeTested));
         chapterRow.setExpressionListNewWords(
               findNotStudiedWords(languageDirection, listChapter));
         chapterRow.setNotStudiedWords(
               chapterRow.getExpressionListNewWords().size());
         chapterRow.setAmountOfNewWords(0);
         chapterRow.setFieldDone(chapterRow.getNotStudiedWords() == 0
               && chapterRow.getToBeRepeatedWords() == 0);
         chapterRow.setStarted(chapterRow.getToBeRepeatedWords() > 0);
         return chapterRow;
      }

      private List<Expression> findNotStudiedWords(
            LanguageDirection languageDirection, List<Expression> list)
      {
         Predicate<Expression> hebrew = e -> e.getLL()
               .getLltype() == LLType.HEBREW;
         Predicate<Expression> swedish = e -> e.getLL()
               .getLltype() == LLType.SWEDISH;
         Predicate<Expression> german = e -> e.getLL()
               .getLltype() == LLType.GERMAN;

         switch (languageDirection)
         {
         case OWN_TO_HEBREW:
            return list
                  .stream().filter(hebrew).filter(expression -> !expression
                        .getTrainingStatusDToLL().isTrainingStarted())
                  .collect(Collectors.toList());
         case HEBREW_TO_OWN:
            return list
                  .stream().filter(hebrew).filter(expression -> !expression
                        .getTrainingStatusLLToD().isTrainingStarted())
                  .collect(Collectors.toList());
         case OWN_TO_SWEDISH:
            return list
                  .stream().filter(swedish).filter(expression -> !expression
                        .getTrainingStatusDToLL().isTrainingStarted())
                  .collect(Collectors.toList());
         case SWEDISH_TO_OWN:
            return list
                  .stream().filter(swedish).filter(expression -> !expression
                        .getTrainingStatusLLToD().isTrainingStarted())
                  .collect(Collectors.toList());
         case OWN_TO_GERMAN:
            return list
                  .stream().filter(german).filter(expression -> !expression
                        .getTrainingStatusDToLL().isTrainingStarted())
                  .collect(Collectors.toList());
         case GERMAN_TO_OWN:
            return list
                  .stream().filter(swedish).filter(expression -> !expression
                        .getTrainingStatusLLToD().isTrainingStarted())
                  .collect(Collectors.toList());
         default:
            return new ArrayList<>();
         }
      }

      private Set<Expression> findOldExpressionsToBeTested(
            LanguageDirection languageDirection,
            FieldOfTraining fieldOfTraining)
      {
         Predicate<Expression> hebrew = e -> e.getLL()
               .getLltype() == LLType.HEBREW;
         Predicate<Expression> swedish = e -> e.getLL()
               .getLltype() == LLType.SWEDISH;
         Predicate<Expression> german = e -> e.getLL()
               .getLltype() == LLType.GERMAN;

         if (LanguageDirection.OWN_TO_HEBREW == languageDirection
               && FieldOfTraining.AREA_SELECTED == fieldOfTraining)
         {
            return alleMap.values().stream()
                  .filter(expression -> expression.isSelected()).filter(hebrew)
                  .filter(expression -> expression.getTrainingStatusDToLL()
                        .isTrainingStarted())
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.HEBREW_TO_OWN == languageDirection
               && FieldOfTraining.AREA_SELECTED == fieldOfTraining)
         {
            return alleMap.values().stream()
                  .filter(expression -> expression.isSelected()).filter(hebrew)
                  .filter(expression -> expression.getTrainingStatusLLToD()
                        .isTrainingStarted())
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.OWN_TO_SWEDISH == languageDirection
               && FieldOfTraining.AREA_SELECTED == fieldOfTraining)
         {
            return alleMap.values().stream()
                  .filter(expression -> expression.isSelected()).filter(swedish)
                  .filter(expression -> expression.getTrainingStatusDToLL()
                        .isTrainingStarted())
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.SWEDISH_TO_OWN == languageDirection
               && FieldOfTraining.AREA_SELECTED == fieldOfTraining)
         {
            return alleMap.values().stream()
                  .filter(expression -> expression.isSelected()).filter(swedish)
                  .filter(expression -> expression.getTrainingStatusLLToD()
                        .isTrainingStarted())
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.OWN_TO_GERMAN == languageDirection
               && FieldOfTraining.AREA_SELECTED == fieldOfTraining)
         {
            return alleMap.values().stream()
                  .filter(expression -> expression.isSelected()).filter(german)
                  .filter(expression -> expression.getTrainingStatusDToLL()
                        .isTrainingStarted())
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.GERMAN_TO_OWN == languageDirection
               && FieldOfTraining.AREA_SELECTED == fieldOfTraining)
         {
            return alleMap.values().stream()
                  .filter(expression -> expression.isSelected()).filter(german)
                  .filter(expression -> expression.getTrainingStatusLLToD()
                        .isTrainingStarted())
                  .collect(Collectors.toSet());
         }

         final LocalDate now = LocalDate.now();

         if (LanguageDirection.OWN_TO_HEBREW == languageDirection
               && FieldOfTraining.AREA_CHAPTER == fieldOfTraining)
         {
            Predicate<Expression> started = e -> e.getTrainingStatusDToLL()
                  .isTrainingStarted();
            Predicate<Expression> isDueNow = e -> now
                  .isEqual(e.getTrainingStatusDToLL().getNextDate());
            Predicate<Expression> wasDueBefore = e -> now
                  .isAfter(e.getTrainingStatusDToLL().getNextDate());
            return alleMap.values().stream().filter(started)
                  .filter(isDueNow.or(wasDueBefore)).filter(hebrew)
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.HEBREW_TO_OWN == languageDirection
               && FieldOfTraining.AREA_CHAPTER == fieldOfTraining)
         {
            Predicate<Expression> started = e -> e.getTrainingStatusLLToD()
                  .isTrainingStarted();
            Predicate<Expression> isDueNow = e -> now
                  .isEqual(e.getTrainingStatusLLToD().getNextDate());
            Predicate<Expression> wasDueBefore = e -> now
                  .isAfter(e.getTrainingStatusLLToD().getNextDate());
            return alleMap.values().stream().filter(started)
                  .filter(isDueNow.or(wasDueBefore)).filter(hebrew)
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.OWN_TO_SWEDISH == languageDirection
               && FieldOfTraining.AREA_CHAPTER == fieldOfTraining)
         {
            Predicate<Expression> started = e -> e.getTrainingStatusDToLL()
                  .isTrainingStarted();
            Predicate<Expression> isDueNow = e -> now
                  .isEqual(e.getTrainingStatusDToLL().getNextDate());
            Predicate<Expression> wasDueBefore = e -> now
                  .isAfter(e.getTrainingStatusDToLL().getNextDate());
            return alleMap.values().stream().filter(started)
                  .filter(isDueNow.or(wasDueBefore)).filter(swedish)
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.SWEDISH_TO_OWN == languageDirection
               && FieldOfTraining.AREA_CHAPTER == fieldOfTraining)
         {
            Predicate<Expression> started = e -> e.getTrainingStatusLLToD()
                  .isTrainingStarted();
            Predicate<Expression> isDueNow = e -> now
                  .isEqual(e.getTrainingStatusLLToD().getNextDate());
            Predicate<Expression> wasDueBefore = e -> now
                  .isAfter(e.getTrainingStatusLLToD().getNextDate());
            return alleMap.values().stream().filter(started)
                  .filter(isDueNow.or(wasDueBefore)).filter(swedish)
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.OWN_TO_GERMAN == languageDirection
               && FieldOfTraining.AREA_CHAPTER == fieldOfTraining)
         {
            Predicate<Expression> started = e -> e.getTrainingStatusDToLL()
                  .isTrainingStarted();
            Predicate<Expression> isDueNow = e -> now
                  .isEqual(e.getTrainingStatusDToLL().getNextDate());
            Predicate<Expression> wasDueBefore = e -> now
                  .isAfter(e.getTrainingStatusDToLL().getNextDate());
            return alleMap.values().stream().filter(started)
                  .filter(isDueNow.or(wasDueBefore)).filter(german)
                  .collect(Collectors.toSet());
         }

         if (LanguageDirection.GERMAN_TO_OWN == languageDirection
               && FieldOfTraining.AREA_CHAPTER == fieldOfTraining)
         {
            Predicate<Expression> started = e -> e.getTrainingStatusLLToD()
                  .isTrainingStarted();
            Predicate<Expression> isDueNow = e -> now
                  .isEqual(e.getTrainingStatusLLToD().getNextDate());
            Predicate<Expression> wasDueBefore = e -> now
                  .isAfter(e.getTrainingStatusLLToD().getNextDate());
            return alleMap.values().stream().filter(started)
                  .filter(isDueNow.or(wasDueBefore)).filter(german)
                  .collect(Collectors.toSet());
         }

         return Collections.emptySet();
      }

      private int findNumberOfOldToBeTestedPerChapter(Chapter chapter,
            Set<Expression> allOldToBeTestedExpressions)
      {
         return allOldToBeTestedExpressions.stream()
               .filter(expression -> chapter.equals(expression.getChapter()))
               .mapToInt(expression -> 1).sum();
      }

      private Set<Expression> findSetOfOldExpressionsToBeTestedPerChapter(
            Chapter chapter, Set<Expression> allOldToBeTestedExpressions)
      {
         return allOldToBeTestedExpressions.stream()
               .filter(expression -> chapter.equals(expression.getChapter()))
               .collect(Collectors.toSet());
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
         Predicate<Expression> trainingDToHStarted = e -> e
               .getTrainingStatusDToLL().isTrainingStarted();
         Predicate<Expression> trainingDToHNotDone = e -> !e
               .getTrainingStatusDToLL().isTrainingDone();

         final Map<LocalDate, List<Expression>> mapDtoH = alleMap.values()
               .stream().filter(trainingDToHStarted).filter(trainingDToHNotDone)
               .collect(Collectors.groupingBy(expression -> expression
                     .getTrainingStatusDToLL().getNextDate()));

         Predicate<Expression> trainingHToDStarted = e -> e
               .getTrainingStatusLLToD().isTrainingStarted();
         Predicate<Expression> trainingHToDNotDone = e -> !e
               .getTrainingStatusLLToD().isTrainingDone();

         final Map<LocalDate, List<Expression>> mapHtoD = alleMap.values()
               .stream().filter(trainingHToDStarted).filter(trainingHToDNotDone)
               .collect(Collectors.groupingBy(expression -> expression
                     .getTrainingStatusLLToD().getNextDate()));

         Set<LocalDate> unsortedAllDates = new HashSet<>();
         unsortedAllDates.addAll(mapDtoH.keySet());
         unsortedAllDates.addAll(mapHtoD.keySet());
         List<LocalDate> sortedAllDates = unsortedAllDates.stream().sorted()
               .collect(Collectors.toList());

         Vector<Vector<StatisticsTableRow>> data = new Vector<>();
         Vector<String> columnNames = new Vector<>();
         columnNames.add("eins");
         StatisticsTableModel model = new StatisticsTableModel(data,
               columnNames);

         Stream.iterate(0, i -> i + 1).limit(sortedAllDates.size())
               .forEachOrdered(i -> {
                  StatisticsTableRow row = new StatisticsTableRow(i,
                        sortedAllDates.get(i),
                        mapDtoH.get(sortedAllDates.get(i)) == null
                              ? Collections.emptyList()
                              : mapDtoH.get(sortedAllDates.get(i)),
                        mapHtoD.get(sortedAllDates.get(i)) == null
                              ? Collections.emptyList()
                              : mapHtoD.get(sortedAllDates.get(i)),
                        model);
                  Vector<StatisticsTableRow> vector = new Vector<>();
                  vector.add(row);
                  data.add(vector);
               });

         return model;
      }

      private SuccessTableModel findSuccessModel(Direction direction,
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
            boolean withSelfEvenIfNotInUseYet, boolean withEmptySelection)
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

         List<String> resultAsString = result.stream()
               .map(DatabaseDescription::getDatabaseName).distinct()
               .collect(Collectors.toCollection(LinkedList::new));

         if (withEmptySelection)
         {
            resultAsString.add(0, "");
         }

         return resultAsString.stream().toArray(String[]::new);
      }

      private String[] getInternalDatabaseNames()
      {
         return Arrays.stream(Settings.getAvailableDatabasesAsArray())
               .map(database -> database.getName()).toArray(String[]::new);
      }

      private List<Expression> findAllExpressionsOfDatabase(
            String databaseChoosen)
      {
         return alleMap
               .values().stream().filter(expression -> expression.getChapter()
                     .getDatabaseName().equals(databaseChoosen))
               .collect(Collectors.toList());
      }

      private void moveSelectedExpressionToChapter(final String toChapter)
      {
         chapterSet.clear();

         alleMap.values().stream().filter(expression -> expression.isDoChange())
               .filter(expression -> expression.isSelected())
               .forEach(expression -> moveExpressionToChapter(expression,
                     toChapter));

         reloadChapterSet();
      }

      private void moveExpressionToChapter(Expression expression,
            String toChapter)
      {
         expression.getChapter().setName(toChapter);
      }

      private void moveSelectedExpressionsToDatabase(final String toDatabase)
      {
         alleMap.values().stream().filter(expression -> expression.isDoChange())
               .filter(expression -> expression.isSelected())
               .forEach(expression -> moveExpressionToDatabase(expression,
                     toDatabase));
      }

      private void moveExpressionToDatabase(Expression expression,
            String toDatabase)
      {
         expression.getChapter().getDatabaseDescription()
               .setDatabaseName(toDatabase);
      }
   }
}
