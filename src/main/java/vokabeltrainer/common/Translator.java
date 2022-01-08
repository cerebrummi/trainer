package vokabeltrainer.common;

import java.util.HashMap;
import java.util.Map;

import vokabeltrainer.Settings;
import vokabeltrainer.types.Translation;
import vokabeltrainer.types.TranslationCode;

public class Translator
{
   private TranslationCode language;
   private Map<Translation, String> translationMap;
   
   Translator()
   {
      this.language = Settings.getTranslationCode();
      translationMap = new HashMap<>();
      // TODO load language
   }
   
   public String translate(Translation translation)
   {
      if(TranslationCode.de_DE == language)
      {
         return translation.getGerman();
      }
      
      if(translationMap.containsKey(translation))
      {
         return translationMap.get(translation);
      }
      
      return translation.getGerman();
   }
   
   public String translateTo(Translation translation, TranslationCode code)
   {
      if(TranslationCode.de_DE == code)
      {
         return translation.getGerman();
      }
      
      Map<Translation, String> translationMapCode = new HashMap<>();
      // TODO load language
      
      if(translationMapCode.containsKey(translation))
      {
         return translationMap.get(translation);
      }
      
      return translation.getGerman();
   }
}
