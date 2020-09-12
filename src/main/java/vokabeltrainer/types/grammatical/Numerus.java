package vokabeltrainer.types.grammatical;

import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;

public enum Numerus
{
   UNKNOWN("Numerus unbekannt"),
   SINGULAR("Singular"),
   DUAL("Dual"),
   PLURAL("Plural"),
   NA("Numerus nicht anwendbar");

   private String description;

   Numerus(String description)
   {
      this.description = description;
   }

   @Override
   public String toString()
   {
      return description;
   }

   public static DefaultComboBoxModel<Numerus> getComboBoxModel()
   {
      DefaultComboBoxModel<Numerus> model = new DefaultComboBoxModel<>();
      model.addAll(Arrays.asList(Numerus.values()));
      return model;
   }
}
