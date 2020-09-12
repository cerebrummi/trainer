package vokabeltrainer.types.grammatical;

import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;

public enum VerbConjugation
{
   UNKOWN("Verbkonjugation unbekannt"),
   INFINITIVE("Infinitiv"),
   PAST("Vergangenheit"),
   PAST_PARTICIPLE("Vergangenheit-Partizip"),
   PRESENT("Gegenwart"),
   FUTURE("Zukunft"),
   IMPERARTIVE("Befehlsform"),
   ACTION_NOUN("Gerundium"), // Gerundium
   NA("Verbkonjugation nicht anwendbar");
   
   private String description;
   
   VerbConjugation(String description)
   {
      this.description = description;
   }
   
   public String toString()
   {
      return description;
   }
   
   public static DefaultComboBoxModel<VerbConjugation> getComboBoxModel()
   {
      DefaultComboBoxModel<VerbConjugation> model = new DefaultComboBoxModel<>();
      model.addAll(Arrays.asList(VerbConjugation.values()));
      return model;
   }
}
