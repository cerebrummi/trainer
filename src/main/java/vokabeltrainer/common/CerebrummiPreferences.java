package vokabeltrainer.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.panels.translation.TranslationCode;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Chapter.Database;

public class CerebrummiPreferences
{

   private CerebrummiPreferences()
   {
      // nothing
   }

   public static void read()
   {

      Preferences preferences = Preferences.userRoot()
            .node(CerebrummiNodes.getNode());
      Settings.setDarkmodeOn(
            preferences.getBoolean(CerebrummiNodes.getDarkmodeNode(), false));
      Settings.setSoundOn(
            preferences.getBoolean(CerebrummiNodes.getSoundNode(), true));
      Settings.setChoosenExpressionPath(preferences
            .get(CerebrummiNodes.getChoosenExpressionPathNode(), null));
      Settings.setVolume(
            preferences.getFloat(CerebrummiNodes.getVolumeNode(), -20f));
      Settings.setLetterImagesOn(preferences
            .getBoolean(CerebrummiNodes.getLetterPicturesNode(), true));

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

      List<String> possibleDatabases = Arrays.stream(Chapter.Database.values())
            .filter(database -> !database.equals(Database.IMPORTED))
            .filter(database -> !database.equals(Database.SELF))
            .filter(database -> !database.equals(Database.TO_BE_DETERMINED))
            .filter(database -> !database.equals(Database.UNKNOWN))
            .map(database -> database.name().toLowerCase())
            .collect(Collectors.toList());

      List<Database> choosenDatabases = new ArrayList<>();

      for (String name : possibleDatabases)
      {
         if (preferences.getBoolean(name, false))
         {
            Database database = Database.valueOf(name.toUpperCase());
            if (Common.getMode().isFree() && !database.isCopyrighted())
            {
               choosenDatabases.add(database);
            }
            else if (!Common.getMode().isFree())
            {
               choosenDatabases.add(database);
            }
         }
      }
      Settings.setChosenDatabases(choosenDatabases);

      Settings.setTranslationCode(TranslationCode
            .valueOf(preferences.get(CerebrummiNodes.getTranslationLanguage(),
                  TranslationCode.de_original.name())));
      Settings.setAnyName(preferences.get(CerebrummiNodes.getAnyName(), null));

      Settings.initRepetition_one_day(preferences
            .getBoolean(CerebrummiNodes.getRepetitionOneDay(), false));
      Settings.initRepetition_two_days(preferences
            .getBoolean(CerebrummiNodes.getRepetitionTwoDays(), true));
      Settings.initRepetition_five_days(preferences
            .getBoolean(CerebrummiNodes.getRepetitionFiveDays(), true));
      Settings.initRepetition_eleven_days(preferences
            .getBoolean(CerebrummiNodes.getRepetitionElevenDays(), false));
      Settings.initRepetition_nineteen_days(preferences
            .getBoolean(CerebrummiNodes.getRepetitionNineteenDays(), true));
      Settings.initRepetition_one_month(preferences
            .getBoolean(CerebrummiNodes.getRepetitionOneMonth(), false));
      Settings.initRepetition_two_months(preferences
            .getBoolean(CerebrummiNodes.getRepetitionTwoMonths(), false));
      Settings.initRepetition_five_months(preferences
            .getBoolean(CerebrummiNodes.getRepetitionFiveMonths(), false));
      
      Settings.initSchabbat_modus(
            preferences.getBoolean(CerebrummiNodes.getSchabbatModus(), false));
      
      Settings.setLanguageInput(Selection.valueOf(preferences
            .get(CerebrummiNodes.getLanguageNode(), Selection.GERMAN.name())));
   }
}
