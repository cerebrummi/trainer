package vokabeltrainer.types.grammatical;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public enum ExpressionKind
{
   UNKOWN("Wortart unbekannt",
         ""),
   ADJEKTIV("Adjektiv",
         ""),
   ADVERB("Adverb",
         ""),
   ALTERSANGABE("Altersangabe",
         ""),
   AUSRUF("Ausruf",
         "hurra, hm, ah, oh, autsch"),
   BERUF("Beruf",
         ""),
   BINDEWORT("Bindewort",
         ""),
   DEMONSTRATIVPRONOM("Demonstrativpronom",
         ""),
   EIGENNAME("Eigenname",
         ""),
   FARBE("Farbe",
         "rot, grün, gelb, blau"),
   FRAGEWORT("Fragewort",
         "wann, wo, warum, wie, wieso, weshalb"),
   PRONOM("Fürwort/Pronom",
         ""),
   GLUECKWUNSCH("Glückwunsch/Gruß",
         ""),
   JAHRESZEIT("Jahreszeit",
         ""),
   MODALVERB("Modalverb",
         "wollen, können, müssen"),
   ORDNUNGSZAHL("Ordnungszahl",
         "erste, zweiter, dritte, vierter"),
   PARTIKEL("Partikel",
         ""),
   PERSONALPRAEFIX("Personalpräfix",
         ""),
   PERSONALPRONOM("Personalpronom",
         ""),
   PERSONALSUFFIX("Personalsuffix",
         ""),
   POSSESSIVPRONOM("Possessivpronom",
         ""),
   PRAEPOSITION("Präposition",
         ""),
   REDEWENDUNG("Redewendung",
         ""),
   SUBSTANTIV("Substantiv",
         "Frau, Mann, Haus, Hammer, Küche, Beruf, Lampe"),
   KONSTRUKT("Konstrukt/ßmichut",
         ""),
   UHRZEIT("Uhrzeit",
         ""),
   UMGANGSPRACHE("Umgangsprache",
         ""),
   VERB("Verb",
         ""),
   WOCHENTAG("Wochentag",
         ""),
   ZAHL("Zahl",
         "eins, zwei, drei, vier, fünf, hundert");

   private String description;
   private String explanation;

   ExpressionKind(String description, String explanation)
   {
      this.description = description;
      this.explanation = explanation;
   }

   public static String[] getExplanations()
   {
      String[] explanations = new String[ExpressionKind.getValues().size() - 1];
      int counter = 0;
      for (ExpressionKind kind : getValues())
      {
         if (!kind.equals(ExpressionKind.UNKOWN))
         {
               explanations[counter] = kind.description + " >> "
                     + kind.explanation;
            counter++;
         }
      }
      return explanations;
   }

   @Override
   public String toString()
   {
      return description;
   }

   public static DefaultComboBoxModel<ExpressionKind> getComboBoxModel()
   {
      DefaultComboBoxModel<ExpressionKind> model = new DefaultComboBoxModel<>();
      model.addAll(Arrays.asList(ExpressionKind.values()));
      return model;
   }

   public static List<ExpressionKind> getValues()
   {
      List<ExpressionKind> list = Arrays.asList(ExpressionKind.values());

      Collections.sort(list, new Comparator<ExpressionKind>()
      {
         @Override
         public int compare(ExpressionKind o1, ExpressionKind o2)
         {
            if (o1.equals(UNKOWN) && o2.equals(UNKOWN))
            {
               return 0;
            }
            if (o1.equals(UNKOWN))
            {
               return -1;
            }
            if (o2.equals(UNKOWN))
            {
               return 1;
            }
            return o1.description.compareTo(o2.description);
         }

      });

      return list;
   }
   
   public static int getNumberOfValues()
   {
      return ExpressionKind.values().length;
   }
}
