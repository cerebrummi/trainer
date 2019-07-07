package vokabeltrainer;

import java.util.Map;

import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.panels.letterpicture.LetterPictureButtonPanel;

public class ApplicationSpecialPanels
{
   private static Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap;

   public static Map<HebrewLetter, LetterPictureButtonPanel> getLetterPicturesPanelMap()
   {
      return letterPicturesPanelMap;
   }

   public static void setLetterPicturesPanelMap(
         Map<HebrewLetter, LetterPictureButtonPanel> letterPicturesPanelMap)
   {
      ApplicationSpecialPanels.letterPicturesPanelMap = letterPicturesPanelMap;
   }

}
