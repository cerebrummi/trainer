package vokabeltrainer.types.grammatical.expressionkind;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;

import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableModel;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableRow;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableModel2;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableRow2;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;

public enum ExpressionKind
{
   EXPRESSIONKIND_UNKNOWN(
         "unbekannt",
         ExpressionKindHelper.UNKNOWN_ENUMS),
   ADJEKTIV(
         "Adjektiv",
         ExpressionKindHelper.ADJECTIVE_ENUMS),
   ADVERB(
         "Adverb",
         ExpressionKindHelper.ADVERB_ENUMS),
   ALTERSANGABE(
         "Altersangabe",
         ExpressionKindHelper.ALTERSANGABE_ENUMS),
   AUSRUF(
         "Ausruf",
         ExpressionKindHelper.AUSRUF_ENUMS),
   BERUF(
         "Beruf",
         ExpressionKindHelper.BERUF_ENUMS),
   BINDEWORT(
         "Bindewort",
         ExpressionKindHelper.BINDEWORT_ENUMS),
   DEMONSTRATIVPRONOM(
         "Demonstrativpronom",
         ExpressionKindHelper.DEMONSTRATIVPRONOM_ENUMS),
   EIGENNAME(
         "Eigenname",
         ExpressionKindHelper.EIGENNAME_ENUMS),
   FARBE(
         "Farbe",
         ExpressionKindHelper.FARBE_ENUMS),
   FRAGEWORT(
         "Fragewort",
         ExpressionKindHelper.FRAGEWORT_ENUMS),
   PRONOM(
         "Fürwort/Pronom",
         ExpressionKindHelper.PRONOM_ENUMS),
   GLUECKWUNSCH(
         "Glückwunsch/Gruß",
         ExpressionKindHelper.GLUECKWUNSCH_ENUMS),
   JAHRESZEIT(
         "Jahreszeit",
         ExpressionKindHelper.JAHRESZEIT_ENUMS),
   KONSTRUKT(
         "Konstrukt/ssmichut",
         ExpressionKindHelper.KONSTRUKT_ENUMS),
   MODALVERB(
         "Modalverb",
         ExpressionKindHelper.MODALVERB_ENUMS),
   ORDNUNGSZAHL(
         "Ordnungszahl",
         ExpressionKindHelper.ORDNUNGSZAHL_ENUMS),
   PARTIKEL(
         "Partikel",
         ExpressionKindHelper.PARTIKEL_ENUMS),
   PERSONALPRAEFIX(
         "Personalpräfix",
         ExpressionKindHelper.PERSONALPRAEFIX_ENUMS),
   PERSONALPRONOM(
         "Personalpronom",
         ExpressionKindHelper.PERSONALPRONOM_ENUMS),
   PERSONALSUFFIX(
         "Personalsuffix",
         ExpressionKindHelper.PERSONALSUFFIX_ENUMS),
   POSSESSIVPRONOM(
         "Possessivpronom",
         ExpressionKindHelper.POSSESSIVPRONOM_ENUMS),
   PRAEPOSITION(
         "Präposition",
         ExpressionKindHelper.PRAEPOSITION_ENUMS),
   REDEWENDUNG(
         "Redewendung",
         ExpressionKindHelper.REDEWENDUNG_ENUMS),
   SUBSTANTIV(
         "Substantiv",
         ExpressionKindHelper.SUBSTANTIV_ENUMS),
   UHRZEIT(
         "Uhrzeit",
         ExpressionKindHelper.UHRZEIT_ENUMS),
   UMGANGSPRACHE(
         "Umgangsprache",
         ExpressionKindHelper.UMGANGSSPRACHE_ENUMS),
   VERB(
         "Verb",
         ExpressionKindHelper.VERB_ENUMS),
   WOCHENTAG(
         "Wochentag",
         ExpressionKindHelper.WOCHENTAG_ENUMS),
   ZAHL(
         "Zahl",
         ExpressionKindHelper.ZAHL_ENUMS);

   private String description;
   private boolean selected;
   private GrammaticalParentEnum[] grammaticalEnums;

   ExpressionKind(String description, GrammaticalParentEnum[] grammaticalEnums)
   {
      this.description = description;
      this.grammaticalEnums = grammaticalEnums;
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
      for (ExpressionKind kind : ExpressionKind.values())
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
      for (ExpressionKind kind : ExpressionKind.values())
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

   public static ExpressionKindTableModel getModel(
         Set<ExpressionKind> expressionKinds)
   {
      Vector<Vector<ExpressionKindTableRow>> data = new Vector<>();
      for (ExpressionKind kind : ExpressionKind.values())
      {
         if (expressionKinds.contains(kind))
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

   public Set<GrammaticalParentEnum> getSetOfGrammaticalEnums()
   {
      return new HashSet<GrammaticalParentEnum>(
            Arrays.asList(this.grammaticalEnums));
   }

   public static Set<GrammaticalParentEnum> getSetOfGrammaticalParentEnums(
         List<ExpressionKindTableRow> rows)
   {
      Set<GrammaticalParentEnum> grammaticalEnums = new HashSet<>();

      for (ExpressionKindTableRow row : rows)
      {
         grammaticalEnums
               .addAll(row.getExpressionKind().getSetOfGrammaticalEnums());
      }

      return grammaticalEnums;
   }

   public static Set<GrammaticalParentEnum> getSetOfGrammaticalParentEnums(
         Set<ExpressionKind> kinds)
   {
      Set<GrammaticalParentEnum> grammaticalEnums = new HashSet<>();

      for (ExpressionKind kind : kinds)
      {
         grammaticalEnums.addAll(kind.getSetOfGrammaticalEnums());
      }

      return grammaticalEnums;
   }
}
