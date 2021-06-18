package vokabeltrainer;

import java.util.prefs.Preferences;

public class CerebrummiPreferences
{

   private CerebrummiPreferences()
   {

   }

   public static void read()
   {
      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.setSoundOn(
               preferences.getBoolean(CerebrummiNodes.getSoundNode(), true));
      }
      catch (Exception e)
      {
         // nothing
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
         // nothing
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
         // nothing
      }

      try
      {
         Preferences preferences = Preferences.userRoot()
               .node(CerebrummiNodes.getNode());
         Settings.setLetterImagesOn(
               preferences.getBoolean(CerebrummiNodes.getLetterPicturesNode(), true));
      }
      catch (Exception e)
      {
         // nothing
      }
   }
}
