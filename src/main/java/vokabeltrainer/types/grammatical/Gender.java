package vokabeltrainer.types.grammatical;

import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;

public enum Gender
{
   UNKOWN("Geschlecht unbekannt"),
   FEMALE("weiblich"),
   MALE("männlich"),
   BOTH("weiblich und männlich"),
   NA("Geschlecht nicht anwendbar");

   private String description;

   Gender(String description)
   {
      this.description = description;
   }

   @Override
   public String toString()
   {
      return description;
   }

   public static DefaultComboBoxModel<Gender> getComboBoxModel()
   {
      DefaultComboBoxModel<Gender> model = new DefaultComboBoxModel<>();
      model.addAll(Arrays.asList(Gender.values()));
      return model;
   }
}
