package vokabeltrainer.common;

import java.util.Map;

import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public class ApplicationSpecialPanels
{
   private static Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap;
   
   public static Map<NikudLetter, LetterPictureButtonPanel> getLetterPicturesPanelMap()
   {
      return letterPicturesPanelMap;
   }

   public static void setLetterPicturesPanelMap(
         Map<NikudLetter, LetterPictureButtonPanel> letterPicturesPanelMap)
   {
      if (letterPicturesPanelMap.size() < 27)
      {
         System.exit(2026);
      }
      else if (letterPicturesPanelMap.size() > 27)
      {
         System.exit(2028);
      }
      ApplicationSpecialPanels.letterPicturesPanelMap = letterPicturesPanelMap;
   }
}
