package vokabeltrainer.types.grammatical;

import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;

public enum VerbStrength
{
   UNKOWN("Verbstärke unbekannt"),
   WEAK("schwach"),
   STRONG("stark"),
   NA("Verbstärke nicht anwendbar");
   
   private String description;
   
   VerbStrength(String description)
   {
      this.description = description;
   }
   
   public String toString()
   {
      return description;
   }
   
   public static DefaultComboBoxModel<VerbStrength> getComboBoxModel()
   {
      DefaultComboBoxModel<VerbStrength> model = new DefaultComboBoxModel<>();
      model.addAll(Arrays.asList(VerbStrength.values()));
      return model;
   }
}
