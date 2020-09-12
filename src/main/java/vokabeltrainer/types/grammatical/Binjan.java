package vokabeltrainer.types.grammatical;

import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;

public enum Binjan
{
   UNKOWN("Binjan unbekannt"), 
   PAAL("pa'al"), 
   PAAL_HOHL("pa'al hohl"), 
   PAAL_SCHWACH("pa'al schwach"), 
   PIEL("pi'el"), 
   HIFIL("hif'il"), 
   HITPAEL("hitpa'el"), 
   HUFAL("huf'al"),
   PUAL("pu'al"), 
   NIFAL("nif'al"),
   NA("Binjan nicht anwendbar");

   String description;

   Binjan(String description)
   {
      this.description = description;
   }

   @Override
   public String toString()
   {
      return description;
   }
   
   public static DefaultComboBoxModel<Binjan> getComboBoxModel()
   {
      DefaultComboBoxModel<Binjan> model = new DefaultComboBoxModel<>();
      model.addAll(Arrays.asList(Binjan.values()));
      return model;
   }
}
