package vokabeltrainer.types.grammatical;

import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;

public enum VerbType
{
   UNKOWN("Verbtyp unbekannt"),
   REGULAR("regulär"),
   IRREGULAR("irregulär"),
   AUXILIARY("Hilfsverb"),
   NA("Verbtyp nicht anwendbar");
   
   private String description;
   
   VerbType(String description)
   {
      this.description = description;
   }
   
   public String toString()
   {
      return description;
   }
   
   public static DefaultComboBoxModel<VerbType> getComboBoxModel()
   {
      DefaultComboBoxModel<VerbType> model = new DefaultComboBoxModel<>();
      model.addAll(Arrays.asList(VerbType.values()));
      return model;
   }
}
