package vokabeltrainer;

import java.io.File;

public class CerebrummiNodes
{

   private CerebrummiNodes()
   {
      
   }

   public static String getNode() // the node of preferences
   {
      return File.separator + "cerebrummi" + File.separator + "hebrewtrainer";
   }

   public static String getExpressionNode()
   {
      return "vocabulary";
   }

   public static String getTrainingNode()
   {
      return "training";
   }
   
   public static String getSoundNode()
   {
      return "sound";
   }

   public static String getChoosenExpressionPathNode()
   {
      return "choosenexpressionpath";
   }

   public static String getVolumeNode()
   {
      return "soundvolume";
   }
}
