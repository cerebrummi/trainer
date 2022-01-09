package vokabeltrainer.types;

import java.awt.ComponentOrientation;
import java.util.Arrays;

import javax.swing.ComboBoxModel;

import vokabeltrainer.panels.translation.TranslationLanguage;

public enum TranslationCode
{
   none("", ComponentOrientation.LEFT_TO_RIGHT, false),
   af("Afrikaans", ComponentOrientation.LEFT_TO_RIGHT, false),
   af_ZA("Afrikaans (South Africa)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ar("Arabic", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_AE("Arabic (U.A.E.)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_BH("Arabic (Bahrain)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_DZ("Arabic (Algeria)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_EG("Arabic (Egypt)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_IQ("Arabic (Iraq)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_JO("Arabic (Jordan)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_KW("Arabic (Kuwait)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_LB("Arabic (Lebanon)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_LY("Arabic (Libya)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_MA("Arabic (Morocco)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_OM("Arabic (Oman)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_QA("Arabic (Qatar)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_SA("Arabic (Saudi Arabia)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_SY("Arabic (Syria)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_TN("Arabic (Tunisia)", ComponentOrientation.RIGHT_TO_LEFT, false),
   ar_YE("Arabic (Yemen)", ComponentOrientation.RIGHT_TO_LEFT, false),
   az("Azeri (Latin)", ComponentOrientation.LEFT_TO_RIGHT, false),
   az_AZ_L("Azeri (Latin) (Azerbaijan)", ComponentOrientation.LEFT_TO_RIGHT, false),
   az_AZ_C("Azeri (Cyrillic) (Azerbaijan)", ComponentOrientation.LEFT_TO_RIGHT, false),
   be("Belarusian", ComponentOrientation.LEFT_TO_RIGHT, false),
   be_BY("Belarusian (Belarus)", ComponentOrientation.LEFT_TO_RIGHT, false),
   bg("Bulgarian", ComponentOrientation.LEFT_TO_RIGHT, false),
   bg_BG("Bulgarian (Bulgaria)", ComponentOrientation.LEFT_TO_RIGHT, false),
   bs_BA("Bosnian (Bosnia and Herzegovina)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ca("Catalan", ComponentOrientation.LEFT_TO_RIGHT, false),
   ca_ES("Catalan (Spain)", ComponentOrientation.LEFT_TO_RIGHT, false),
   cs("Czech", ComponentOrientation.LEFT_TO_RIGHT, false),
   cs_CZ("Czech (Czech Republic)", ComponentOrientation.LEFT_TO_RIGHT, false),
   cy("Welsh", ComponentOrientation.LEFT_TO_RIGHT, false),
   cy_GB("Welsh (United Kingdom)", ComponentOrientation.LEFT_TO_RIGHT, false),
   da("Danish", ComponentOrientation.LEFT_TO_RIGHT, false),
   da_DK("Danish (Denmark)", ComponentOrientation.LEFT_TO_RIGHT, false),
   de("German", ComponentOrientation.LEFT_TO_RIGHT, false),
   de_AT("German (Austria)", ComponentOrientation.LEFT_TO_RIGHT, false),
   de_CH("German (Switzerland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   de_DE("German (Germany)", ComponentOrientation.LEFT_TO_RIGHT, false),
   de_LI("German (Liechtenstein)", ComponentOrientation.LEFT_TO_RIGHT, false),
   de_LU("German (Luxembourg)", ComponentOrientation.LEFT_TO_RIGHT, false),
   de_original("German Original", ComponentOrientation.LEFT_TO_RIGHT, true),
   dv("Divehi", ComponentOrientation.LEFT_TO_RIGHT, false),
   dv_MV("Divehi (Maldives)", ComponentOrientation.LEFT_TO_RIGHT, false),
   el("Greek", ComponentOrientation.LEFT_TO_RIGHT, false),
   el_GR("Greek (Greece)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en("English", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_AU("English (Australia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_BZ("English (Belize)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_CA("English (Canada)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_CB("English (Caribbean)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_GB("English (United Kingdom)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_IE("English (Ireland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_JM("English (Jamaica)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_NZ("English (New Zealand)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_PH("English (Republic of the Philippines)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_TT("English (Trinidad and Tobago)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_US("English (United States)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_ZA("English (South Africa)", ComponentOrientation.LEFT_TO_RIGHT, false),
   en_ZW("English (Zimbabwe)", ComponentOrientation.LEFT_TO_RIGHT, false),
   eo("Esperanto", ComponentOrientation.LEFT_TO_RIGHT, false),
   es("Spanish", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_AR("Spanish (Argentina)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_BO("Spanish (Bolivia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_CL("Spanish (Chile)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_CO("Spanish (Colombia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_CR("Spanish (Costa Rica", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_DO("Spanish (Dominican Republic)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_EC("Spanish (Ecuador)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_ES_C("Spanish (Castilian)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_ES_S("Spanish (Spain)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_GT("Spanish (Guatemala)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_HN("Spanish (Honduras)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_MX("Spanish (Mexico)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_NI("Spanish (Nicaragua)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_PA("Spanish (Panama)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_PE("Spanish (Peru)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_PR("Spanish (Puerto Rico)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_PY("Spanish (Paraguay)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_SV("Spanish (El Salvador)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_UY("Spanish (Uruguay)", ComponentOrientation.LEFT_TO_RIGHT, false),
   es_VE("Spanish (Venezuela)", ComponentOrientation.LEFT_TO_RIGHT, false),
   et("Estonian", ComponentOrientation.LEFT_TO_RIGHT, false),
   et_EE("Estonian (Estonia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   eu("Basque", ComponentOrientation.LEFT_TO_RIGHT, false),
   eu_ES("Basque (Spain)", ComponentOrientation.LEFT_TO_RIGHT, false),
   fa("Farsi", ComponentOrientation.LEFT_TO_RIGHT, false),
   fa_IR("Farsi (Iran)", ComponentOrientation.RIGHT_TO_LEFT, false),
   fi("Finnish", ComponentOrientation.LEFT_TO_RIGHT, false),
   fi_FI("Finnish (Finland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   fo("Faroese", ComponentOrientation.LEFT_TO_RIGHT, false),
   fo_FO("Faroese (Faroe Islands)", ComponentOrientation.LEFT_TO_RIGHT, false),
   fr("French", ComponentOrientation.LEFT_TO_RIGHT, false),
   fr_BE("French (Belgium)", ComponentOrientation.LEFT_TO_RIGHT, false),
   fr_CA("French (Canada)", ComponentOrientation.LEFT_TO_RIGHT, false),
   fr_CH("French (Switzerland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   fr_FR("French (France)", ComponentOrientation.LEFT_TO_RIGHT, false),
   fr_LU("French (Luxembourg)", ComponentOrientation.LEFT_TO_RIGHT, false),
   fr_MC("French (Principality of Monaco)", ComponentOrientation.LEFT_TO_RIGHT, false),
   gl("Galician", ComponentOrientation.LEFT_TO_RIGHT, false),
   gl_ES("Galician (Spain)", ComponentOrientation.LEFT_TO_RIGHT, false),
   gu("Gujarati", ComponentOrientation.LEFT_TO_RIGHT, false),
   gu_IN("Gujarati (India)", ComponentOrientation.LEFT_TO_RIGHT, false),
   he("Hebrew", ComponentOrientation.RIGHT_TO_LEFT, false),
   he_IL("Hebrew (Israel)", ComponentOrientation.RIGHT_TO_LEFT, false),
   hi("Hindi", ComponentOrientation.LEFT_TO_RIGHT, false),
   hi_IN("Hindi (India)", ComponentOrientation.LEFT_TO_RIGHT, false),
   hr("Croatian", ComponentOrientation.LEFT_TO_RIGHT, false),
   hr_BA("Croatian (Bosnia and Herzegovina)", ComponentOrientation.LEFT_TO_RIGHT, false),
   hr_HR("Croatian (Croatia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   hu("Hungarian", ComponentOrientation.LEFT_TO_RIGHT, false),
   hu_HU("Hungarian (Hungary)", ComponentOrientation.LEFT_TO_RIGHT, false),
   hy("Armenian", ComponentOrientation.LEFT_TO_RIGHT, false),
   hy_AM("Armenian (Armenia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   id("Indonesian", ComponentOrientation.LEFT_TO_RIGHT, false),
   id_ID("Indonesian (Indonesia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   is("Icelandic", ComponentOrientation.LEFT_TO_RIGHT, false),
   is_IS("Icelandic (Iceland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   it("Italian", ComponentOrientation.LEFT_TO_RIGHT, false),
   it_CH("Italian (Switzerland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   it_IT("Italian (Italy)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ja("Japanese", ComponentOrientation.LEFT_TO_RIGHT, false),
   ja_JP("Japanese (Japan)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ka("Georgian", ComponentOrientation.LEFT_TO_RIGHT, false),
   ka_GE("Georgian (Georgia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   kk("Kazakh", ComponentOrientation.LEFT_TO_RIGHT, false),
   kk_KZ("Kazakh (Kazakhstan)", ComponentOrientation.LEFT_TO_RIGHT, false),
   kn("Kannada", ComponentOrientation.LEFT_TO_RIGHT, false),
   kn_IN("Kannada (India)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ko("Korean", ComponentOrientation.LEFT_TO_RIGHT, false),
   ko_KR("Korean (Korea)", ComponentOrientation.LEFT_TO_RIGHT, false),
   kok("Konkani", ComponentOrientation.LEFT_TO_RIGHT, false),
   kok_IN("Konkani (India)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ky("Kyrgyz", ComponentOrientation.LEFT_TO_RIGHT, false),
   ky_KG("Kyrgyz (Kyrgyzstan)", ComponentOrientation.LEFT_TO_RIGHT, false),
   lt("Lithuanian", ComponentOrientation.LEFT_TO_RIGHT, false),
   lt_LT("Lithuanian (Lithuania)", ComponentOrientation.LEFT_TO_RIGHT, false),
   lv("Latvian", ComponentOrientation.LEFT_TO_RIGHT, false),
   lv_LV("Latvian (Latvia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   mi("Maori", ComponentOrientation.LEFT_TO_RIGHT, false),
   mi_NZ("Maori (New Zealand)", ComponentOrientation.LEFT_TO_RIGHT, false),
   mk("FYRO Macedonian", ComponentOrientation.LEFT_TO_RIGHT, false),
   mk_MK("FYRO Macedonian (Former Yugoslav Republic of Macedonia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   mn("Mongolian", ComponentOrientation.LEFT_TO_RIGHT, false),
   mn_MN("Mongolian (Mongolia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   mr("Marathi", ComponentOrientation.LEFT_TO_RIGHT, false),
   mr_IN("Marathi (India)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ms("Malay", ComponentOrientation.LEFT_TO_RIGHT, false),
   ms_BN("Malay (Brunei Darussalam)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ms_MY("Malay (Malaysia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   mt("Maltese", ComponentOrientation.LEFT_TO_RIGHT, false),
   mt_MT("Maltese (Malta)", ComponentOrientation.LEFT_TO_RIGHT, false),
   nb("Norwegian (Bokm?l)", ComponentOrientation.LEFT_TO_RIGHT, false),
   nb_NO("Norwegian (Bokm?l) (Norway)", ComponentOrientation.LEFT_TO_RIGHT, false),
   nl("Dutch", ComponentOrientation.LEFT_TO_RIGHT, false),
   nl_BE("Dutch (Belgium)", ComponentOrientation.LEFT_TO_RIGHT, false),
   nl_NL("Dutch (Netherlands)", ComponentOrientation.LEFT_TO_RIGHT, false),
   nn_NO("Norwegian (Nynorsk) (Norway)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ns("Northern Sotho", ComponentOrientation.LEFT_TO_RIGHT, false),
   ns_ZA("Northern Sotho (South Africa)", ComponentOrientation.LEFT_TO_RIGHT, false),
   pa("Punjabi", ComponentOrientation.LEFT_TO_RIGHT, false),
   pa_IN("Punjabi (India)", ComponentOrientation.LEFT_TO_RIGHT, false),
   pl("Polish", ComponentOrientation.LEFT_TO_RIGHT, false),
   pl_PL("Polish (Poland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ps("Pashto", ComponentOrientation.LEFT_TO_RIGHT, false),
   ps_AR("Pashto (Afghanistan)", ComponentOrientation.LEFT_TO_RIGHT, false),
   pt("Portuguese", ComponentOrientation.LEFT_TO_RIGHT, false),
   pt_BR("Portuguese (Brazil)", ComponentOrientation.LEFT_TO_RIGHT, false),
   pt_PT("Portuguese (Portugal)", ComponentOrientation.LEFT_TO_RIGHT, false),
   qu("Quechua", ComponentOrientation.LEFT_TO_RIGHT, false),
   qu_BO("Quechua (Bolivia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   qu_EC("Quechua (Ecuador)", ComponentOrientation.LEFT_TO_RIGHT, false),
   qu_PE("Quechua (Peru)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ro("Romanian", ComponentOrientation.LEFT_TO_RIGHT, false),
   ro_RO("Romanian (Romania)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ru("Russian", ComponentOrientation.LEFT_TO_RIGHT, false),
   ru_RU("Russian (Russia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sa("Sanskrit", ComponentOrientation.LEFT_TO_RIGHT, false),
   sa_IN("Sanskrit (India)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se("Sami (Northern)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se_FI_N("Sami (Northern) (Finland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se_FI_S("Sami (Skolt) (Finland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se_FI_I("Sami (Inari) (Finland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se_NO_N("Sami (Northern) (Norway)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se_NO_L("Sami (Lule) (Norway)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se_NO_S("Sami (Southern) (Norway)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se_SE_N("Sami (Northern) (Sweden)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se_SE_L("Sami (Lule) (Sweden)", ComponentOrientation.LEFT_TO_RIGHT, false),
   se_SE_S("Sami (Southern) (Sweden)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sk("Slovak", ComponentOrientation.LEFT_TO_RIGHT, false),
   sk_SK("Slovak (Slovakia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sl("Slovenian", ComponentOrientation.LEFT_TO_RIGHT, false),
   sl_SI("Slovenian (Slovenia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sq("Albanian", ComponentOrientation.LEFT_TO_RIGHT, false),
   sq_AL("Albanian (Albania)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sr_BA_L("Serbian (Latin) (Bosnia and Herzegovina)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sr_BA_C("Serbian (Cyrillic) (Bosnia and Herzegovina)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sr_SP_L("Serbian (Latin) (Serbia and Montenegro)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sr_SP_C("Serbian (Cyrillic) (Serbia and Montenegro)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sv("Swedish", ComponentOrientation.LEFT_TO_RIGHT, false),
   sv_FI("Swedish (Finland)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sv_SE("Swedish (Sweden)", ComponentOrientation.LEFT_TO_RIGHT, false),
   sw("Swahili", ComponentOrientation.LEFT_TO_RIGHT, false),
   sw_KE("Swahili (Kenya)", ComponentOrientation.LEFT_TO_RIGHT, false),
   syr("Syriac", ComponentOrientation.LEFT_TO_RIGHT, false),
   syr_SY("Syriac (Syria)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ta("Tamil", ComponentOrientation.LEFT_TO_RIGHT, false),
   ta_IN("Tamil (India)", ComponentOrientation.LEFT_TO_RIGHT, false),
   te("Telugu", ComponentOrientation.LEFT_TO_RIGHT, false),
   te_IN("Telugu (India)", ComponentOrientation.LEFT_TO_RIGHT, false),
   th("Thai", ComponentOrientation.LEFT_TO_RIGHT, false),
   th_TH("Thai (Thailand)", ComponentOrientation.LEFT_TO_RIGHT, false),
   tl("Tagalog", ComponentOrientation.LEFT_TO_RIGHT, false),
   tl_PH("Tagalog (Philippines)", ComponentOrientation.LEFT_TO_RIGHT, false),
   tn("Tswana", ComponentOrientation.LEFT_TO_RIGHT, false),
   tn_ZA("Tswana (South Africa)", ComponentOrientation.LEFT_TO_RIGHT, false),
   tr("Turkish", ComponentOrientation.LEFT_TO_RIGHT, false),
   tr_TR("Turkish (Turkey)", ComponentOrientation.LEFT_TO_RIGHT, false),
   tt("Tatar", ComponentOrientation.LEFT_TO_RIGHT, false),
   tt_RU("Tatar (Russia)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ts("Tsonga", ComponentOrientation.LEFT_TO_RIGHT, false),
   uk("Ukrainian", ComponentOrientation.LEFT_TO_RIGHT, false),
   uk_UA("Ukrainian (Ukraine)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ur("Urdu", ComponentOrientation.LEFT_TO_RIGHT, false),
   ur_PK("Urdu (Islamic Republic of Pakistan)", ComponentOrientation.LEFT_TO_RIGHT, false),
   uz("Uzbek (Latin)", ComponentOrientation.LEFT_TO_RIGHT, false),
   uz_UZ_L("Uzbek (Latin) (Uzbekistan)", ComponentOrientation.LEFT_TO_RIGHT, false),
   uz_UZ_C("Uzbek (Cyrillic) (Uzbekistan)", ComponentOrientation.LEFT_TO_RIGHT, false),
   vi("Vietnamese", ComponentOrientation.LEFT_TO_RIGHT, false),
   vi_VN("Vietnamese (Viet Nam)", ComponentOrientation.LEFT_TO_RIGHT, false),
   xh("Xhosa", ComponentOrientation.LEFT_TO_RIGHT, false),
   xh_ZA("Xhosa (South Africa)", ComponentOrientation.LEFT_TO_RIGHT, false),
   zh("Chinese", ComponentOrientation.LEFT_TO_RIGHT, false),
   zh_CN("Chinese (S)", ComponentOrientation.LEFT_TO_RIGHT, false),
   zh_HK("Chinese (Hong Kong)", ComponentOrientation.LEFT_TO_RIGHT, false),
   zh_MO("Chinese (Macau)", ComponentOrientation.LEFT_TO_RIGHT, false),
   zh_SG("Chinese (Singapore)", ComponentOrientation.LEFT_TO_RIGHT, false),
   zh_TW("Chinese (T)", ComponentOrientation.LEFT_TO_RIGHT, false),
   zu("Zulu", ComponentOrientation.LEFT_TO_RIGHT, false),
   zu_ZA("Zulu (South Africa)", ComponentOrientation.LEFT_TO_RIGHT, false),
   ANY_ltr_("any language, left to right", ComponentOrientation.LEFT_TO_RIGHT, false),
   ANY_rtl_("any language, right to left", ComponentOrientation.RIGHT_TO_LEFT, false);
   
   private String name;
   private ComponentOrientation orientation;
   private boolean available;
   
   TranslationCode(String name, ComponentOrientation orientation, boolean available)
   {
      this.name = name;
      this.orientation = orientation;
      this.available = available;
   }

   public String getName()
   {
      return name;
   }

   public ComponentOrientation getOrientation()
   {
      return orientation;
   }
   
   public String toString()
   {
      return name;
   }
   
   public static TranslationCode[] valuesAvailable()
   {
      return Arrays
            .stream(TranslationCode.values())
            .filter(value -> value.isAvailable())
            .toArray(TranslationCode[]::new);
   }
   
   public static TranslationCode[] valuesNoOriginal()
   {
      return Arrays
            .stream(TranslationCode.values())
            .filter(value -> TranslationCode.de_original != value)
            .toArray(TranslationCode[]::new);
   }
   
   public static String[] stringsNoOriginal()
   {
      return Arrays
            .stream(TranslationCode.values())
            .filter(value -> TranslationCode.de_original != value)
            .map(code -> code.getName())
            .toArray(String[]::new);
   }

   public boolean isAvailable()
   {
      return available;
   }

   public void setAvailable(boolean available)
   {
      this.available = available;
   }

   public static TranslationLanguage[]  anyLanguagesLeftToRight()
   {
      TranslationLanguage[] result = {new TranslationLanguage()};
      // TODO load languages
      return result;
   }
   
   public static TranslationLanguage[]  anyLanguagesRightToLeft()
   {
      TranslationLanguage[] result = {new TranslationLanguage()};
      // TODO load languages
      return result;
   }
}
