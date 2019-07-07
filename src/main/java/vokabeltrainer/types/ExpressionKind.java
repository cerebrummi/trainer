package vokabeltrainer.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import vokabeltrainer.Settings;

public enum ExpressionKind
{
   UNKOWN(
         "Wortart unbekannt",
         "Wortart unbekannt",
         ""),
   ADJEKTIV(
         "Eigenschaftswort",
         "Adjektiv",
         "jung, freundlich, weiblich, wichtig, groß"),
   ADVERB(
         "Umstandswort",
         "Adverb",
         "noch, schon, genug, meistens, jetzt, hier, dort"),
   ARTIKEL(
         "Artikel",
         "Artikel",
         "ein, eine, der, die, das"),
   BEGRIFF(
         "Redewendung",
         "Redewendung",
         "Guten Tag!, Wie geht es?"),
   EIGENNAME(
         "Eigenname",
         "Nomen Proprium",
         "Italien, Hamburg, Israel"),
   FRAGE(
         "Fragewort",
         "Fragewort",
         "wann, wo, warum, wie, wieso, weshalb"),
   INTERJEKTION(
         "Ausrufewort",
         "Interjekt",
         "hurra, hm, ah, oh, autsch"),
   KONJUNKTION(
         "Bindewort",
         "Konjunktion",
         "aber, und, oder, denn, weil, obwohl, seit, nachdem"),
   NUMERAL(
         "Zahlwort",
         "Numeral",
         "eins, zwei, drei, vier, fünf, hundert"),
   PARTIKEL(
         "Partikel",
         "Partikel",
         "auch, sehr, ziemlich, sogar, eben, gerade"),
   PRAEPOSITION(
         "Verhältniswort",
         "Präposition",
         "durch, um, gegen, mit, bei, von, ab, in, und"),
   PRONOM(
         "Fürwort",
         "Pronom",
         "er, ich, diese, man, eine, welches, meine, jemand, sich"),
   SUBSTANTIV(
         "Hauptwort",
         "Substantiv",
         "Frau, Haus, Hammer, Küche, Beruf, Lampe"),
   VERB(
         "Tätigkeitswort",
         "Verb",
         "laufen, gehen, schwimmen, essen, trinken");

   private String germanName;
   private String latinName;
   private String explanation;

   ExpressionKind(String germanName, String latinName, String explanation)
   {
      this.germanName = germanName;
      this.latinName = latinName;
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
            if (Settings.getLanguage().equals(LanguageSettings.GERMAN))
            {
               explanations[counter] = kind.germanName + " >>> "
                     + kind.explanation;
            }
            else
            {
               explanations[counter] = kind.latinName + " >>> "
                     + kind.explanation;
            }
            counter++;
         }
      }
      return explanations;
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

   public static ExpressionKind fromString(String kind)
   {
      ExpressionKind result = fromGermanString(kind);
      if (result != null)
      {
         return result;
      }
      result = fromLatinString(kind);
      if (result != null)
      {
         return result;
      }
      return UNKOWN;
   }

   private static ExpressionKind fromGermanString(String kind)
   {
      for (ExpressionKind expressionKind : ExpressionKind.values())
      {
         if (expressionKind.germanName.equals(kind))
         {
            return expressionKind;
         }
      }
      return null;
   }

   private static ExpressionKind fromLatinString(String kind)
   {
      for (ExpressionKind expressionKind : ExpressionKind.values())
      {
         if (expressionKind.latinName.equals(kind))
         {
            return expressionKind;
         }
      }
      return null;
   }

   public static DefaultComboBoxModel<ExpressionKind> getModel()
   {
      DefaultComboBoxModel<ExpressionKind> model = new DefaultComboBoxModel<>();

      for (ExpressionKind kind : getValues())
      {
         model.addElement(kind);
      }

      return model;
   }

   public static List<ExpressionKind> getValues()
   {
      List<ExpressionKind> list = new ArrayList<>();

      for (ExpressionKind kind : ExpressionKind.values())
      {
         list.add(kind);
      }

      if (Settings.getLanguage().equals(LanguageSettings.GERMAN))
      {
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
               return o1.germanName.compareTo(o2.germanName);
            }

         });
      }
      else
      {
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
               return o1.latinName.compareTo(o2.latinName);
            }

         });
      }

      return list;
   }
}
