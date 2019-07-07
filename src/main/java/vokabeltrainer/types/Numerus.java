package vokabeltrainer.types;

import javax.swing.DefaultComboBoxModel;

import vokabeltrainer.Settings;

public enum Numerus
{
   SINGULAR(
         "Einzahl",
         "Singular"),
   PLURAL(
         "Mehrzahl",
         "Plural"),
   DUAL(
         "Dual",
         "Dual"),
   UNKNOWN(
         "Numerus unbekannt",
         "Numerus unbekannt"),
   NA(
         "Numerus nicht anwendbar",
         "Numerus nicht anwendbar");

   private String germanName;
   private String latinName;

   Numerus(String germanName, String latinName)
   {
      this.germanName = germanName;
      this.latinName = latinName;
   }

   @Override
   public String toString()
   {
      if (Settings.getLanguage().equals(LanguageSettings.GERMAN))
      {
         return germanName;
      }
      return latinName;
   }

   public static Numerus fromString(String numerus)
   {
      if (Settings.getLanguage().equals(LanguageSettings.GERMAN))
      {
         if (SINGULAR.germanName.equals(numerus))
         {
            return SINGULAR;
         }

         if (PLURAL.germanName.equals(numerus))
         {
            return PLURAL;
         }

         if (DUAL.germanName.equals(numerus))
         {
            return DUAL;
         }

         if (NA.germanName.equals(numerus))
         {
            return NA;
         }

         if (UNKNOWN.germanName.equals(numerus))
         {
            return UNKNOWN;
         }
      }
      else if (Settings.getLanguage().equals(LanguageSettings.LATIN))
      {

         if (SINGULAR.latinName.equals(numerus))
         {
            return SINGULAR;
         }

         if (PLURAL.latinName.equals(numerus))
         {
            return PLURAL;
         }

         if (DUAL.latinName.equals(numerus))
         {
            return DUAL;
         }

         if (NA.latinName.equals(numerus))
         {
            return NA;
         }

         if (UNKNOWN.latinName.equals(numerus))
         {
            return UNKNOWN;
         }
      }

      return NA;
   }

   public static DefaultComboBoxModel<Numerus> getModelHebrew()
   {
      DefaultComboBoxModel<Numerus> model = new DefaultComboBoxModel<>();
      model.addElement(UNKNOWN);
      model.addElement(SINGULAR);
      model.addElement(DUAL);
      model.addElement(PLURAL);
      model.addElement(NA);
      return model;
   }

   public static DefaultComboBoxModel<Numerus> getModelGerman()
   {
      DefaultComboBoxModel<Numerus> model = new DefaultComboBoxModel<>();
      model.addElement(UNKNOWN);
      model.addElement(SINGULAR);
      model.addElement(PLURAL);
      model.addElement(NA);
      return model;
   }
}
