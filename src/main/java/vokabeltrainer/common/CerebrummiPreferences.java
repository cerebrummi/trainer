package vokabeltrainer.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.cmd.Mode;
import vokabeltrainer.panels.translation.TranslationCode;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Chapter.Database;

public interface CerebrummiPreferences
{
   static void read(Mode mode)
   {
      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.setDarkmodeOn(preferences
               .getBoolean(CerebrummiNodes.getDarkmodeNode(), false));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.setSoundOn(
               preferences.getBoolean(CerebrummiNodes.getSoundNode(), true));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.setChoosenExpressionPath(preferences
               .get(CerebrummiNodes.getChoosenExpressionPathNode(), null));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.setVolume(
               preferences.getFloat(CerebrummiNodes.getVolumeNode(), -20f));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.setLetterImagesOn(preferences
               .getBoolean(CerebrummiNodes.getLetterPicturesNode(), true));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());

         if (!preferences.nodeExists(CerebrummiNodes.getChoosenDatabases()))
         {
            preferences.put(CerebrummiNodes.getChoosenDatabases(), "");
         }

         preferences = preferences.node(CerebrummiNodes.getChoosenDatabases());

         List<String> possibleDatabases = Arrays
               .stream(Chapter.Database.values())
               .filter(database -> !database.equals(Database.IMPORTED))
               .filter(database -> !database.equals(Database.SELF))
               .filter(database -> !database.equals(Database.TO_BE_DETERMINED))
               .filter(database -> !database.equals(Database.UNKNOWN))
               .map(database -> database.name().toLowerCase())
               .collect(Collectors.toList());

         List<Database> choosenDatabases = new ArrayList<>();

         for (String name : possibleDatabases)
         {
            try
            {
               if (preferences.getBoolean(name, false))
               {
                  Database database = Database.valueOf(name.toUpperCase());
                  if (mode.isFree() && !database.isCopyrighted())
                  {
                     choosenDatabases.add(database);
                  }
                  else if (!mode.isFree())
                  {
                     choosenDatabases.add(database);
                  }
               }
            }
            catch (Exception e)
            {
            }
         }

         Settings.setChosenDatabases(choosenDatabases);
      }
      catch (BackingStoreException e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.setTranslationCode(TranslationCode.valueOf(
               preferences.get(CerebrummiNodes.getTranslationLanguage(),
                     TranslationCode.de_original.name())));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.initRepetition_one_day(preferences
               .getBoolean(CerebrummiNodes.getRepetitionOneDay(), false));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.initRepetition_two_days(preferences
               .getBoolean(CerebrummiNodes.getRepetitionTwoDays(), true));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.initRepetition_five_days(preferences
               .getBoolean(CerebrummiNodes.getRepetitionFiveDays(), true));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.initRepetition_eleven_days(preferences
               .getBoolean(CerebrummiNodes.getRepetitionElevenDays(), false));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.initRepetition_nineteen_days(preferences
               .getBoolean(CerebrummiNodes.getRepetitionNineteenDays(), true));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.initRepetition_one_month(preferences
               .getBoolean(CerebrummiNodes.getRepetitionOneMonth(), false));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.initRepetition_two_months(preferences
               .getBoolean(CerebrummiNodes.getRepetitionTwoMonths(), false));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.initRepetition_five_months(preferences
               .getBoolean(CerebrummiNodes.getRepetitionFiveMonths(), false));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.initSchabbat_modus(preferences
               .getBoolean(CerebrummiNodes.getSchabbatModus(), false));
      }
      catch (Exception e)
      {
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.setLanguageInput(Selection.valueOf(preferences.get(
               CerebrummiNodes.getLanguageNode(), Selection.GERMAN.name())));
      }
      catch (Exception e)
      {
      }
   }
}
