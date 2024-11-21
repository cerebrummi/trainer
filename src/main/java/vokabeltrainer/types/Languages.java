package vokabeltrainer.types;

public enum Languages
{
   AFAR("aa","Afar"),
   ABKHAZIAN("ab", "Abkhazian"),
   AFRIKAANS("af", "Afirkaans"),
   AKAN("ak", "Akan"),
   AMHARIC("am", "Amharic"),
   ARABIC("ar", "Arabic"),
   ARAGONESE("an", "Aragonese"),
   ASSAMESE("as", "Assamese"),
   AVARIC("av", "Avaric"),
   AYMARA("ay","Aymara"),
   AZERBAIJANI("az", "Azerbaijani"),
   BASHKIR("ba", "Bashkir"),
   BAMBARA("bm", "Bambara"),
   BELARUSIAN("be", "Belarusian"),
   BENGALI("bn", "Bengali"),
   BISLAMA("bi", "Bislama"),
   TIBETAN("bo", "Tibetan"),
   BOSNIAN("bs", "Bosnian"),
   BRETON("br", "Breton"),
   BULGARIAN("bg", "Bulgarian"),
   CATALAN("ca", "Catalan"),
   CZECH("cs", "Czech"),
   CHAMORRO("ch", "Chamorro"),
   CHECHEN("ce", "Chechen"),
   OLD_SLAVONIC("cu", "Old Slavonic"),
   CHUVASH("cv", "Chuvash"),
   CORNISH("kw", "Cornish"),
   CORSICAN("co", "Corsican"),
   CREE("cr", "Cree"),
   WELSH("cy", "Welsh"),
   DANISH("da", "Danish"),
   GERMAN("de", "Deutsch"),
   DIVEHI("dv", "Divehi"),
   DZONGKHA("dz", "Dzongkha"),
   GREEK("el", "Greek"),
   ENGLISH("en", "English"),
   ESPERANTO("eo", "Esperanto"),
   ESTONIAN("et", "Estonian"),
   BASQUE("eu", "Basque"),
   EWE("ee", "Ewe"),
   FAROESE("fo", "Faroese"),
   FARSI("fa", "Farsi"),
   FIJIAN("fj", "Fijian"),
   FINNISH("fi", "Finnish"),
   FRENSH("fr", "Français"),
   WESTERN_FRISIAN("fy", "West Friesisch"),
   FULAH("ff", "Fula"),
   GAELIC("gd", "Gaelic"),
   IRISH("ga", "Irish"),
   GALICIAN("gl", "Galician"),
   MANX("gv", "Manx"),
   GUARANI("gn", "Guarani"),
   GUJARATI("gu", "Gujarati"),
   HAITIAN("ht", "Haitian"),
   HAUSA("ha", "Hausa"),
   HEBREW("he", "Hebrew"),
   JIDDISH("dd", "Jiddish"),
   HERERO("hz", "Herero"),
   HINDI("hi", "Hindi"),
   NIRI_MOTU("ho", "Hiri Motu"),
   CROATIAN("hr", "Croatian"),
   HUNGARIAN("hu", "Hungarian"),
   ARMENIAN("hy", "Armenian"),
   IGBO("ig", "Igbo"),
   IDO("io", "Ido"),
   NUOSU("ii", "Nuosu"),
   INUKTITUT("iu", "Inuktitut"),
   INTERLINGUE("ie", "Interlingue"),
   INTERLINGUA("ia", "Interlingua"),
   INDONESIAN("id", "Indonesian"),
   INUPIAQ("ik", "Inupiaq"),
   ICELANDIC("is", "Icelandic"),
   ITALIAN("it", "Italian"),
   JAVANESE("jv", "Javanese"),
   JAPANESE("ja", "Japanese"),
   KALAALLISUT("kl", "Kalaallisut"),
   KANNADA("kn", "Kannada"),
   KASHMIRI("ks", "Kashmiri"),
   GEORGIAN("ka", "Georgian")
   ;
   
   private String shortcut;
   private String name;

   Languages(String shortcut, String name)
   {
      this.shortcut = shortcut;
      this.name = name;
   }

   public String getShortcut()
   {
      return shortcut;
   }

   public String getName()
   {
      return name;
   }
   
   public static String[] fullValues()
   {
      Languages[] values = Languages.values();
      
      String [] fullValues = new String[values.length];
      
      for(int i = 0; i < values.length; i++)
      {
         fullValues[i] = values[i].getShortcut() + " | " + values[i].getName();
      }
      
      return fullValues;
      
   }
}
