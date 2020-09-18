package vokabeltrainer.types.grammatical.expressionkind;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import vokabeltrainer.json.JSONObject;
import vokabeltrainer.json.JSONObjectProducer;

public enum ExpressionKind implements JSONObjectProducer
{
   EXPRESSIONKIND_UNKNOWN(
         "unbekannt",
         ""),
   ADJEKTIV(
         "Adjektiv",
         ""),
   ADVERB(
         "Adverb",
         ""),
   ALTERSANGABE(
         "Altersangabe",
         ""),
   AUSRUF(
         "Ausruf",
         "hurra, hm, ah, oh, autsch"),
   BERUF(
         "Beruf",
         ""),
   BINDEWORT(
         "Bindewort",
         ""),
   DEMONSTRATIVPRONOM(
         "Demonstrativpronom",
         ""),
   EIGENNAME(
         "Eigenname",
         "Russland, Paris, Berlin"),
   FARBE(
         "Farbe",
         "rot, grün, gelb, blau"),
   FRAGEWORT(
         "Fragewort",
         "wann, wo, warum, wie, wieso, weshalb"),
   PRONOM(
         "Fürwort/Pronom",
         ""),
   GLUECKWUNSCH(
         "Glückwunsch/Gruß",
         ""),
   JAHRESZEIT(
         "Jahreszeit",
         ""),
   KONSTRUKT(
         "Konstrukt/ssmichut",
         ""),
   MODALVERB(
         "Modalverb",
         "wollen, können, müssen"),
   ORDNUNGSZAHL(
         "Ordnungszahl",
         "erste, zweiter, dritte, vierter"),
   PARTIKEL(
         "Partikel",
         ""),
   PERSONALPRAEFIX(
         "Personalpräfix",
         ""),
   PERSONALPRONOM(
         "Personalpronom",
         ""),
   PERSONALSUFFIX(
         "Personalsuffix",
         ""),
   POSSESSIVPRONOM(
         "Possessivpronom",
         ""),
   PRAEPOSITION(
         "Präposition",
         ""),
   REDEWENDUNG(
         "Redewendung",
         ""),
   SUBSTANTIV(
         "Substantiv",
         "Frau, Mann, Haus, Hammer, Küche, Beruf, Lampe"),
   UHRZEIT(
         "Uhrzeit",
         ""),
   UMGANGSPRACHE(
         "Umgangsprache",
         ""),
   VERB(
         "Verb",
         ""),
   WOCHENTAG(
         "Wochentag",
         ""),
   ZAHL(
         "Zahl",
         "eins, zwei, drei, vier, fünf, hundert");

   private String description;
   private String explanation;
   private boolean selected;

   ExpressionKind(String description, String explanation)
   {
      this.description = description;
      this.explanation = explanation;
   }

   public static String[] getExplanations()
   {
      String[] explanations = new String[ExpressionKind.getValuesAsSortedList()
            .size() - 1];
      int counter = 0;
      for (ExpressionKind kind : getValuesAsSortedList())
      {
         if (!kind.equals(ExpressionKind.EXPRESSIONKIND_UNKNOWN))
         {
            explanations[counter] = kind.description + ": " + kind.explanation;
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

   public String toDescription()
   {
      switch (this)
      {
      case ADJEKTIV:
      case ADVERB:
      case ALTERSANGABE:
      case AUSRUF:
      case BERUF:
      case BINDEWORT:
      case DEMONSTRATIVPRONOM:
      case EIGENNAME:
      case FARBE:
      case FRAGEWORT:
      case GLUECKWUNSCH:
      case JAHRESZEIT:
      case KONSTRUKT:
      case MODALVERB:
      case ORDNUNGSZAHL:
      case PARTIKEL:
      case PERSONALPRAEFIX:
      case PERSONALPRONOM:
      case PERSONALSUFFIX:
      case POSSESSIVPRONOM:
      case PRAEPOSITION:
      case PRONOM:
      case REDEWENDUNG:
      case SUBSTANTIV:
      case UHRZEIT:
      case UMGANGSPRACHE:
      case VERB:
      case WOCHENTAG:
      case ZAHL:
         return description;
      case EXPRESSIONKIND_UNKNOWN:
         return "Wortart " + description;
      default:
         return "";
      }
   }

   public static List<ExpressionKind> getValuesAsSortedList()
   {
      List<ExpressionKind> list = Arrays.asList(ExpressionKind.values());
      list.remove(0);

      Collections.sort(list, new Comparator<ExpressionKind>()
      {
         @Override
         public int compare(ExpressionKind o1, ExpressionKind o2)
         {
            if (o1.equals(EXPRESSIONKIND_UNKNOWN)
                  && o2.equals(EXPRESSIONKIND_UNKNOWN))
            {
               return 0;
            }
            if (o1.equals(EXPRESSIONKIND_UNKNOWN))
            {
               return -1;
            }
            if (o2.equals(EXPRESSIONKIND_UNKNOWN))
            {
               return 1;
            }

            Collator coll = Collator.getInstance(Locale.GERMAN);
            coll.setStrength(Collator.PRIMARY);
            return coll.compare(o1.description, o2.description);
         }
      });

      return list;
   }

   public static int getNumberOfValues()
   {
      return ExpressionKind.values().length;
   }

   @Override
   public JSONObject getJSONObject()
   {
      // TODO JSONObject
      return null;
   }

   public boolean isSelected()
   {
      return selected;
   }

   public void setSelected(boolean selected)
   {
      this.selected = selected;
   }
   
   public void toggleSelected()
   {
      selected = !selected;
   }
}
