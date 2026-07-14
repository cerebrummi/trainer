package vokabeltrainer.types;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public enum SearchType
{
   SEARCHWORD(Translation.SUCHE_NACH_SUCHWORT), WORDSTART(
         Translation.SUCHE_NACH_WORTANFANG);

   Translation meaning;

   SearchType(Translation meaning)
   {
      this.meaning = meaning;
   }

   public String getMeaning(Common common, Direction language)
   {
      Translator translator = common.getTranslator();
      switch (language)
      {
      case OWN_TO_NEW:
         return translator.realisticTranslate(meaning);
      case NEW_TO_OWN:
         switch (this)
         {
         case SEARCHWORD:
            return translator.realisticTranslate(meaning) + " " + translator
                  .realisticTranslate(Translation._OHNE_PUNKTIERUNG_);
         case WORDSTART:
            return translator.realisticTranslate(meaning) + " " + translator
                  .realisticTranslate(Translation._OHNE_PUNKTIERUNG_);
         }
      }
      return "";
   }

}
