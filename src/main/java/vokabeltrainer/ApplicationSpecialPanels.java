package vokabeltrainer;

import java.util.Map;

import javax.swing.JOptionPane;

import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public class ApplicationSpecialPanels
{
   private static Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap;
   private static String message = "Cerebrummi© konnte Resourcen nicht laden.\\nFehler: ";

   public static Map<HebrewLetter, LetterPictureButtonPanel> getLetterPicturesPanelMap()
   {
      return letterPicturesPanelMap;
   }

   public static void setLetterPicturesPanelMap(
         Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap)
   {
      if(letterPicturesPanelMap.size() < 34)
      {
         exitWithMessage("Es fehlen Buchstabenbilder.");
      }
      ApplicationSpecialPanels.letterPicturesPanelMap = letterPicturesPanelMap;
   }

   private static void exitWithMessage(String localMessage)
   {
      JOptionPane.showMessageDialog(null, message + localMessage, "Nachricht",
            JOptionPane.CLOSED_OPTION);
      System.exit(1);
   }
}
