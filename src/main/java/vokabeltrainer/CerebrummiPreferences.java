package vokabeltrainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Chapter.Database;

public class CerebrummiPreferences
{

   private CerebrummiPreferences()
   {

   }

   public static void read()
   {
      try
      {
         Preferences preferences = Preferences
               .userRoot()
               .node(CerebrummiNodes.getNode());
         Settings
               .setSoundOn(preferences
                     .getBoolean(CerebrummiNodes.getSoundNode(), true));
      }
      catch (Exception e)
      {
         // nothing
      }

      try
      {
         Preferences preferences = Preferences
               .userRoot()
               .node(CerebrummiNodes.getNode());
         Settings
               .setChoosenExpressionPath(preferences
                     .get(CerebrummiNodes.getChoosenExpressionPathNode(),
                           null));
      }
      catch (Exception e)
      {
         // nothing
      }

      try
      {
         Preferences preferences = Preferences
               .userRoot()
               .node(CerebrummiNodes.getNode());
         Settings
               .setVolume(preferences
                     .getFloat(CerebrummiNodes.getVolumeNode(), -20f));
      }
      catch (Exception e)
      {
         // nothing
      }

      try
      {
         Preferences preferences = Preferences
               .userRoot()
               .node(CerebrummiNodes.getNode());
         Settings
               .setLetterImagesOn(preferences
                     .getBoolean(CerebrummiNodes.getLetterPicturesNode(),
                           true));
      }
      catch (Exception e)
      {
         // nothing
      }

      try
      {
         Preferences preferences = Preferences
               .userRoot()
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
               .filter(database -> !database
                     .equals(Database.TO_BE_DETERMINED))
               .filter(database -> !database.equals(Database.UNKNOWN))
               .map(database -> database.name().toLowerCase())
               .collect(Collectors.toList());
         
         List<Database> choosenDatabases = new ArrayList<>();
         
         for(String name : possibleDatabases)
         {
            try
            {
               if(preferences.getBoolean(name, false))
               {
                  choosenDatabases.add(Database.valueOf(name.toUpperCase()));
               }
            }
            catch (Exception e)
            {
               // nothing
            }
         }
         
         Settings.setChosenDatabases(choosenDatabases);
      }
      catch (BackingStoreException e)
      {
         // nothing
      }

   }
}
