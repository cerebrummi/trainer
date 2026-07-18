package vokabeltrainer.common.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.panels.translation.TranslationCode;
import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.types.LanguageSettings;
import vokabeltrainer.types.WritingDirection;

public class Settings
{
   public enum LanguageStored
   {
      HEBREW_SIMPLE, HEBREW_PLENE_DEFEKTIV, SWEDISH, GERMAN
   }

   public enum OperatingSystem
   {
      WINDOWS, LINUX
   }

   private  boolean soundOn = false;
   private  String chosenExpressionPath = null;
   private  float volume = -20;
   private  boolean letterImagesOn = true;
   private  Selection languageInput = readLanguageInput();
   private  String version = "8.1";

   private  LinkedList<Database> oldChosenDatabases = new LinkedList<>();
   private  LinkedList<Database> chosenDatabases = new LinkedList<>();
   private  Database[] availableDatabases;
   private  Database[] availableDatabasesOriginal = {
         Database.GRUNDWORTSCHATZ };
   
   {
      availableDatabases = availableDatabasesOriginal;
   }
   private  String rememberDatabaseForInput = "";
   private  String rememberChapterForInput = "";

   private  TranslationCode translationCode = TranslationCode.de_original;

   private  boolean repetition_now = true;
   private  boolean repetition_one_day;
   private  boolean repetition_two_days;
   private  boolean repetition_five_days;
   private  boolean repetition_eleven_days;
   private  boolean repetition_nineteen_days;
   private  boolean repetition_one_month;
   private  boolean repetition_two_months;
   private  boolean repetition_five_months;
   private  boolean repetition_done = true;

   private  boolean schabbat_modus = false;

   private  boolean darkmodeOn = false;

   private  WritingDirection myWritingDirection = readMyWritingDirection();

   private  OperatingSystem operatingSystem = figureBetriebssystem();

   private App app;
   
   Settings(App app)
   {
      this.app = app;
   }

   private  OperatingSystem figureBetriebssystem()
   {
      if ("\\".equalsIgnoreCase(File.separator))
      {
         return OperatingSystem.WINDOWS;
      }
      return OperatingSystem.LINUX;
   }

   public  OperatingSystem getOperatingSystem()
   {
      return operatingSystem;
   }

   public  String getVersion()
   {
      return version;
   }

   public  int dictionaryTableRowHeight()
   {
      return 250;
   }

   public  LanguageSettings getLanguage()
   {
      return LanguageSettings.GERMAN;
   }

   public  String getExpressionFolder()
   {
      return "cerebrummi-hebrewtrainer";
   }

   public  String getWebExportFolder()
   {
      return ""; // The server sets the folder
   }

   public  String getWebExportPath()
   {
      return getWebExportFolder(); // The server sets the path ?
   }

   public  String getExpressionPath()
   {
      if (chosenExpressionPath == null || chosenExpressionPath.isBlank())
      {
         chosenExpressionPath = System.getProperty("user.home");
      }
      return chosenExpressionPath;
   }
   
   public  void setBackExpressionPath()
   {
      String home = System.getProperty("user.home");
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.put(CerebrummiNodes.getChoosenExpressionPathNode(),
            home);

      chosenExpressionPath = home;
   }
   
   public  void setChoosenExpressionPath(String choosenExpressionPath)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.put(CerebrummiNodes.getChoosenExpressionPathNode(),
            choosenExpressionPath);

      chosenExpressionPath = choosenExpressionPath;
   }

   public  String getExpressionPathFolder()
   {
      return getExpressionPath() + File.separator + getExpressionFolder();
   }

   public  String getTrainingPath()
   {
      return getExpressionPath() + File.separator + getTrainingFolder();
   }

   private  String getTrainingFolder()
   {
      return getExpressionFolder() + "-training";
   }

   public  String getImagePath()
   {
      return getExpressionPath() + File.separator + getImageFolder();
   }

   private  String getImageFolder()
   {
      return getExpressionFolder() + "-images";
   }

   public  String getSoundPath()
   {
      return getExpressionPath() + File.separator + getSoundFolder();
   }

   private  String getSoundFolder()
   {
      return getExpressionFolder() + "-sounds";
   }

   public  String getTranslationPath()
   {
      return getExpressionPath() + File.separator + getTranslationFolder();
   }

   private  String getTranslationFolder()
   {
      return getExpressionFolder() + "-languages";
   }

   public  int getKeyboardWidth()
   {
      return 474;
   }

   public  String getWindowTitle()
   {
      return "Cerebrummi";
   }

   public  BufferedImage getSound()
   {
      if (soundOn)
      {
         return app.appImages.getSoundOn();
      }
      return app.appImages.getSoundOff();
   }

   public  boolean isSoundOn()
   {
      return soundOn;
   }

   public  void toggleSoundOnOff()
   {
      soundOn = !soundOn;
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSoundNode(), soundOn);
   }

   public  void setSoundOn(boolean soundOn)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSoundNode(), soundOn);
      this.soundOn = soundOn;
   }

   public  boolean isDarkmodeOn()
   {
      return darkmodeOn;
   }

   public  void setDarkmodeOn(boolean darkmodeOn)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getDarkmodeNode(), darkmodeOn);
      this.darkmodeOn = darkmodeOn;
   }

   public  void toggleDarkmodeOn()
   {
      darkmodeOn = !darkmodeOn;
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getDarkmodeNode(), darkmodeOn);
   }

   public  void setTranslationCode(TranslationCode translationCode)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.put(CerebrummiNodes.getTranslationLanguage(),
            translationCode.name());
      this.translationCode = translationCode;
   }

   public  TranslationCode getTranslationCode()
   {
      return translationCode;
   }

   public  boolean isLetterImagesOn()
   {
      return letterImagesOn;
   }

   public  void toggleLetterImagesOnOff()
   {
      letterImagesOn = !letterImagesOn;
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getLetterPicturesNode(),
            letterImagesOn);
   }

   public  void setLetterImagesOn(boolean letterImagesOn)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getLetterPicturesNode(),
            letterImagesOn);
      this.letterImagesOn = letterImagesOn;
   }



   public  void setChosenDatabases(List<Database> chosenDatabases)
   {
      chosenDatabases = new LinkedList<>();
      chosenDatabases.addAll(chosenDatabases);
   }

   public  boolean isDatabaseChoosen(Database database)
   {
      return chosenDatabases.contains(database);
   }

   public  LinkedList<Database> getChosenDatabases()
   {
      return chosenDatabases;
   }

   public  void addChosenDatabase(Database chosen)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      try
      {
         if (!preferences.nodeExists(CerebrummiNodes.getChoosenDatabases()))
         {
            preferences.put(CerebrummiNodes.getChoosenDatabases(), "");
         }
      }
      catch (BackingStoreException e)
      {
         // nothing
      }
      preferences = preferences.node(CerebrummiNodes.getChoosenDatabases());
      preferences.putBoolean(chosen.name().toLowerCase(), true);

      chosenDatabases.add(chosen);
   }

   public  void removeChosenDatabase(Database chosen)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      try
      {
         if (!preferences.nodeExists(CerebrummiNodes.getChoosenDatabases()))
         {
            preferences.put(CerebrummiNodes.getChoosenDatabases(), "");
         }
      }
      catch (BackingStoreException e)
      {
         // nothing
      }
      preferences = preferences.node(CerebrummiNodes.getChoosenDatabases());

      preferences.putBoolean(chosen.name().toLowerCase(), false);

      chosenDatabases.remove(chosen);
   }

   public  LinkedList<Database> getOldChosenDatabases()
   {
      return oldChosenDatabases;
   }

   public  void setOldChosenDatabases(
         LinkedList<Database> oldChosenDatabases)
   {
      this.oldChosenDatabases = oldChosenDatabases;
   }

   public  float getVolume()
   {
      return volume;
   }

   public  void setVolume(float volume)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putInt(CerebrummiNodes.getVolumeNode(), (int) volume);
      this.volume = volume;
   }

   public  List<Database> getAvailableDatabases()
   {
      return Arrays.asList(availableDatabases);
   }

   public  Database[] getAvailableDatabasesAsArray()
   {
      return availableDatabases;
   }

   public  boolean isSimpleHebrewInput()
   {
      return languageInput == Selection.SIMPLE;
   }

   public  boolean isHebrewPleneDefektivInput()
   {
      return languageInput == Selection.PLENE_DEFEKTIV;
   }

   public  boolean isSwedishInput()
   {
      return languageInput == Selection.SWEDISH;
   }

   public  boolean isGermanInput()
   {
      return languageInput == Selection.GERMAN;
   }

   public  void setLanguageInput(Selection selection)
   {
      languageInput = selection;

      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.put(CerebrummiNodes.getLanguageNode(),
            languageInput.name());
   }

   public  Selection getLanguageInput()
   {
      return languageInput;
   }

   public  WritingDirection getMyWritingDirection()
   {
      return myWritingDirection;
   }

   public  void setMyWritingDirection(WritingDirection myWritingDirection)
   {
      this.myWritingDirection = myWritingDirection;

      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.put(CerebrummiNodes.getWritingDirectionNode(),
            myWritingDirection.name());
   }

   public  WritingDirection readMyWritingDirection()
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      String s = preferences.get(CerebrummiNodes.getWritingDirectionNode(),
            WritingDirection.LEFT_TO_RIGHT.name());

      if (s == null)
      {
         setMyWritingDirection(WritingDirection.LEFT_TO_RIGHT);
         return WritingDirection.LEFT_TO_RIGHT;
      }

      return WritingDirection.valueOf(s);
   }

   public  Selection readLanguageInput()
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      String s = preferences.get(CerebrummiNodes.getLanguageNode(),
            Selection.GERMAN.name());

      if (s == null)
      {
         setLanguageInput(Selection.GERMAN);
         return Selection.GERMAN;
      }

      return Selection.valueOf(s);
   }

   public  String getRememberDatabaseForInput()
   {
      return rememberDatabaseForInput;
   }

   public  void setRememberDatabaseForInput(
         String rememberDatabaseForInput)
   {
      this.rememberDatabaseForInput = rememberDatabaseForInput;
   }

   public  String getRememberChapterForInput()
   {
      return rememberChapterForInput;
   }

   public  void setRememberChapterForInput(String rememberChapterForInput)
   {
      this.rememberChapterForInput = rememberChapterForInput;
   }

   public boolean isRepetition_now()
   {
      return repetition_now;
   }

   public  void setRepetition_now(boolean repetition_now)
   {
      // nothing
   }

   public  boolean isRepetition_one_day()
   {
      return repetition_one_day;
   }

   public  void setRepetition_one_day(boolean repetition_one_day)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getRepetitionOneDay(),
            repetition_one_day);
      this.repetition_one_day = repetition_one_day;
   }

   public  void initRepetition_one_day(boolean repetition_one_day)
   {
      this.repetition_one_day = repetition_one_day;
   }

   public  boolean isRepetition_two_days()
   {
      return repetition_two_days;
   }

   public  void setRepetition_two_days(boolean repetition_two_days)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getRepetitionTwoDays(),
            repetition_two_days);
      this.repetition_two_days = repetition_two_days;
   }

   public  void initRepetition_two_days(boolean repetition_two_days)
   {
      this.repetition_two_days = repetition_two_days;
   }

   public  boolean isRepetition_five_days()
   {
      return repetition_five_days;
   }

   public  void setRepetition_five_days(boolean repetition_five_days)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getRepetitionFiveDays(),
            repetition_five_days);
      this.repetition_five_days = repetition_five_days;
   }

   public  void initRepetition_five_days(boolean repetition_five_days)
   {
      this.repetition_five_days = repetition_five_days;
   }

   public  boolean isRepetition_eleven_days()
   {
      return repetition_eleven_days;
   }

   public  void setRepetition_eleven_days(boolean repetition_eleven_days)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getRepetitionElevenDays(),
            repetition_eleven_days);
      this.repetition_eleven_days = repetition_eleven_days;
   }

   public  void initRepetition_eleven_days(boolean repetition_eleven_days)
   {
      this.repetition_eleven_days = repetition_eleven_days;
   }

   public  boolean isRepetition_nineteen_days()
   {
      return repetition_nineteen_days;
   }

   public  void setRepetition_nineteen_days(
         boolean repetition_nineteen_days)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getRepetitionNineteenDays(),
            repetition_nineteen_days);
      this.repetition_nineteen_days = repetition_nineteen_days;
   }

   public  void initRepetition_nineteen_days(
         boolean repetition_nineteen_days)
   {
      this.repetition_nineteen_days = repetition_nineteen_days;
   }

   public  boolean isRepetition_one_month()
   {
      return repetition_one_month;
   }

   public  void setRepetition_one_month(boolean repetition_one_month)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getRepetitionOneMonth(),
            repetition_one_month);
      this.repetition_one_month = repetition_one_month;
   }

   public  void initRepetition_one_month(boolean repetition_one_month)
   {
      this.repetition_one_month = repetition_one_month;
   }

   public  boolean isRepetition_two_months()
   {
      return repetition_two_months;
   }

   public  void setRepetition_two_months(boolean repetition_two_months)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getRepetitionTwoMonths(),
            repetition_two_months);
      this.repetition_two_months = repetition_two_months;
   }

   public  void initRepetition_two_months(boolean repetition_two_months)
   {
      this.repetition_two_months = repetition_two_months;
   }

   public  boolean isRepetition_five_months()
   {
      return repetition_five_months;
   }

   public  void setRepetition_five_months(boolean repetition_five_months)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getRepetitionFiveMonths(),
            repetition_five_months);
      this.repetition_five_months = repetition_five_months;
   }

   public  void initRepetition_five_months(boolean repetition_five_months)
   {
      this.repetition_five_months = repetition_five_months;
   }

   public  boolean isRepetition_done()
   {
      return repetition_done;
   }

   public  void setRepetition_done(boolean repetition_done)
   {
      // nothing
   }

   public  boolean isSchabbat_modus()
   {
      return schabbat_modus;
   }

   public  void setSchabbat_modus(boolean schabbat_modus)
   {
      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      preferences.putBoolean(CerebrummiNodes.getSchabbatModus(),
            schabbat_modus);
      this.schabbat_modus = schabbat_modus;
   }

   public  void initSchabbat_modus(boolean schabbat_modus)
   {
      this.schabbat_modus = schabbat_modus;
   }



}
