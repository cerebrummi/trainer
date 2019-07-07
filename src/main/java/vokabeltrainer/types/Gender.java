package vokabeltrainer.types;

import javax.swing.DefaultComboBoxModel;

import vokabeltrainer.Settings;

public enum Gender
{
   FEMALE(
         "weiblich",
         "feminin"),
   MALE(
         "männlich",
         "maskulin"),
   NEUTER(
         "sächlich",
         "neutrum"),
   UNKOWN(
         "Geschlecht unbekannt",
         "Geschlecht unbekannt"),
   NA(
         "Geschlecht nicht anwendbar",
         "Geschlecht nicht anwendbar");

   private String germanName;
   private String latinName;

   Gender(String germanName, String latinName)
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

   public static Gender fromString(String gender)
   {
      if (Settings.getLanguage().equals(LanguageSettings.GERMAN))
      {
         if (FEMALE.germanName.equals(gender))
         {
            return FEMALE;
         }

         if (MALE.germanName.equals(gender))
         {
            return MALE;
         }

         if (NEUTER.germanName.equals(gender))
         {
            return NEUTER;
         }

         if (UNKOWN.germanName.equals(gender))
         {
            return UNKOWN;
         }

         if (NA.germanName.equals(gender))
         {
            return NA;
         }
      }
      else if (Settings.getLanguage().equals(LanguageSettings.LATIN))
      {
         if (FEMALE.latinName.equals(gender))
         {
            return FEMALE;
         }

         if (MALE.latinName.equals(gender))
         {
            return MALE;
         }

         if (NEUTER.latinName.equals(gender))
         {
            return NEUTER;
         }

         if (UNKOWN.latinName.equals(gender))
         {
            return UNKOWN;
         }

         if (NA.latinName.equals(gender))
         {
            return NA;
         }
      }
      return NA;
   }

   public static DefaultComboBoxModel<Gender> getHebrewModel()
   {
      DefaultComboBoxModel<Gender> model = new DefaultComboBoxModel<>();
      model.addElement(UNKOWN);
      model.addElement(FEMALE);
      model.addElement(MALE);
      model.addElement(NA);
      return model;
   }

   public static DefaultComboBoxModel<Gender> getGermanModel()
   {
      DefaultComboBoxModel<Gender> model = new DefaultComboBoxModel<>();
      model.addElement(UNKOWN);
      model.addElement(FEMALE);
      model.addElement(MALE);
      model.addElement(NEUTER);
      model.addElement(NA);
      return model;
   }
}
