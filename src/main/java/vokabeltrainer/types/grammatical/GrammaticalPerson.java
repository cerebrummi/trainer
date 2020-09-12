package vokabeltrainer.types.grammatical;

import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;

public enum GrammaticalPerson
{
   UNKOWN("Person unbekannt"),
   ERSTE_PERSON("1. Person"),
   ZWEITE_PERSON("2. Person"),
   DRITTE_PERSON("3. Person"),
   NA("Person nicht anwendbar");
   
   private String description;
   
   GrammaticalPerson(String description)
   {
      this.description = description;
   }
   
   @Override
   public String toString()
   {
      return description;
   }
   
   public static DefaultComboBoxModel<GrammaticalPerson> getComboBoxModel()
   {
      DefaultComboBoxModel<GrammaticalPerson> model = new DefaultComboBoxModel<>();
      model.addAll(Arrays.asList(GrammaticalPerson.values()));
      return model;
   }
}
