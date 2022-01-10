package vokabeltrainer.panels.translation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import vokabeltrainer.Settings;

public class Translator
{
   private TranslationCode language;
   private Map<Translation, String> translationMap;

   public Translator()
   {
      this.language = Settings.getTranslationCode();
      translationMap = new HashMap<>();
      TranslationController controller = new TranslationController();
      this.translationMap = controller.findTranslationMap(
            Settings.getTranslationCode(), Settings.getTranslationUUID());
   }

   public Translator(TranslationCode currentCode, UUID uuid)
   {
      this.language = currentCode;
      TranslationController controller = new TranslationController();
      this.translationMap = controller.findTranslationMap(currentCode, uuid);
   }

   public String translate(Translation translation)
   {
      if (TranslationCode.de_original == language)
      {
         return translation.getGerman();
      }

      if (translationMap.containsKey(translation))
      {
         return translationMap.get(translation);
      }

      return translation.getGerman();
   }

}
