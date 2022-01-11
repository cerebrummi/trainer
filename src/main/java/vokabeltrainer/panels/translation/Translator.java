package vokabeltrainer.panels.translation;

import java.util.HashMap;
import java.util.Map;
import vokabeltrainer.Settings;

public class Translator
{
   private TranslationCodeWrapper language;
   private Map<Translation, String> translationMap;

   public Translator()
   {
      this.language = Settings.getTranslationCodeWrapper();
      translationMap = new HashMap<>();
      TranslationController controller = new TranslationController();
      this.translationMap = controller.findTranslationMap(language);
   }

   public Translator(TranslationCodeWrapper currentCode)
   {
      this.language = currentCode;
      if (TranslationCode.de_original == currentCode.getCode())
      {
         return;
      }
      TranslationController controller = new TranslationController();
      this.translationMap = controller.findTranslationMap(currentCode);
   }

   public String realisticTranslate(Translation translation)
   {
      if (TranslationCode.de_original == language.getCode())
      {
         return translation.getGerman();
      }

      if (translationMap.containsKey(translation))
      {
         return translationMap.get(translation);
      }

      return "missing";
   }

   public String saveTranslate(Translation translation)
   {
      if (TranslationCode.de_original == language.getCode())
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
