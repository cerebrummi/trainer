package vokabeltrainer.common;

import java.util.Map;

import javax.swing.JOptionPane;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;
import vokabeltrainer.panels.translation.Translation;

public class ApplicationSpecialPanels
{
   private static Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap;
   private static String message;
   ApplicationSpecialPanels(Common common)
   {
      message = Settings.getWindowTitle()
            + common.getTranslator().realisticTranslate(
                  Translation.KONNTE_RESOURCEN_NICHT_LADEN_FEHLER);
   }
   
   public static Map<NikudLetter, LetterPictureButtonPanel> getLetterPicturesPanelMap()
   {
      return letterPicturesPanelMap;
   }

   public static void setLetterPicturesPanelMap(Common common,
         Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap)
   {
      if (letterPicturesPanelMap.size() < 27)
      {
         exitWithMessage(common, common.getTranslator()
               .realisticTranslate(Translation.ES_FEHLEN_BUCHSTABENBILDER));
      }
      else if (letterPicturesPanelMap.size() > 27)
      {
         exitWithMessage(common, common.getTranslator()
               .realisticTranslate(Translation.ZUVIELE_BUCHSTABENBILDER));
      }
      ApplicationSpecialPanels.letterPicturesPanelMap = letterPicturesPanelMap;
   }

   private static void exitWithMessage(Common common, String localMessage)
   {
      JOptionPane.showMessageDialog(null, message + localMessage,
            common.getTranslator().realisticTranslate(Translation.NACHRICHT),
            JOptionPane.CLOSED_OPTION);
      System.exit(1);
   }
}
