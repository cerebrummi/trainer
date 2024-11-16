package vokabeltrainer.common;

import java.util.Map;

import javax.swing.JOptionPane;

import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;
import vokabeltrainer.panels.translation.Translation;

public class ApplicationSpecialPanels
{
   private static Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap;
   private static String message = Settings.getWindowTitle()
         + Common.getTranslator().realisticTranslate(
               Translation.KONNTE_RESOURCEN_NICHT_LADEN_FEHLER);

   public static Map<NikudLetter, LetterPictureButtonPanel> getLetterPicturesPanelMap()
   {
      return letterPicturesPanelMap;
   }

   public static void setLetterPicturesPanelMap(
         Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap)
   {
      if (letterPicturesPanelMap.size() < 27)
      {
         exitWithMessage(Common.getTranslator()
               .realisticTranslate(Translation.ES_FEHLEN_BUCHSTABENBILDER));
      }
      else if (letterPicturesPanelMap.size() > 27)
      {
         exitWithMessage(Common.getTranslator()
               .realisticTranslate(Translation.ZUVIELE_BUCHSTABENBILDER));
      }
      ApplicationSpecialPanels.letterPicturesPanelMap = letterPicturesPanelMap;
   }

   private static void exitWithMessage(String localMessage)
   {
      JOptionPane.showMessageDialog(null, message + localMessage,
            Common.getTranslator().realisticTranslate(Translation.NACHRICHT),
            JOptionPane.CLOSED_OPTION);
      System.exit(1);
   }
}
