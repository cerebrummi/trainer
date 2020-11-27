package vokabeltrainer.types.grammatical.expressionkind;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;

import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableModel;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableRow;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableModel2;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableRow2;

public enum ExpressionKind
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
   
   public static ExpressionKindTableModel getModel() // for Multiselect
   {
      Vector<Vector<ExpressionKindTableRow>> data = new Vector<>();
      for(ExpressionKind kind : ExpressionKind.values())
      {
         kind.setSelected(false);
         Vector<ExpressionKindTableRow> row = new Vector<>();
         row.add(new ExpressionKindTableRow(kind));
         data.add(row);
      }
      Vector<String> columnNames = new Vector<>();
      columnNames.add("eins");
      return new ExpressionKindTableModel(data, columnNames);
   }
   
   public static ExpressionKindTableModel2 getModel2() // for Singleselect
   {
      Vector<Vector<ExpressionKindTableRow2>> data = new Vector<>();
      for(ExpressionKind kind : ExpressionKind.values())
      {
         kind.setSelected(false);
         Vector<ExpressionKindTableRow2> row = new Vector<>();
         row.add(new ExpressionKindTableRow2(kind));
         data.add(row);
      }
      Vector<String> columnNames = new Vector<>();
      columnNames.add("eins");
      return new ExpressionKindTableModel2(data, columnNames);
   }
   
   public static ExpressionKindTableModel getModel(Set<ExpressionKind> expressionKinds)
   {   
      Vector<Vector<ExpressionKindTableRow>> data = new Vector<>();
      for(ExpressionKind kind : ExpressionKind.values())
      {
         if(expressionKinds.contains(kind))
         {
            kind.setSelected(true);
         }
         else
         {
            kind.setSelected(false);
         }
         Vector<ExpressionKindTableRow> row = new Vector<>();
         row.add(new ExpressionKindTableRow(kind));
         data.add(row);
      }
      Vector<String> columnNames = new Vector<>();
      columnNames.add("eins");
      return new ExpressionKindTableModel(data, columnNames);
   }
}
