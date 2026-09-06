package vokabeltrainer.panels.translation;

import vokabeltrainer.common.main.App;

public class Translator
{

   public String realisticTranslate(App app, Translation translation)
   {
      if (TranslationCode.de_original == app.settings.getTranslationCode())
      {
         return translation.getGerman();
      }
      else if (TranslationCode.en == app.settings.getTranslationCode())
      {
         return translation.getEnglish();
      }

      return "missing";
   }

}
