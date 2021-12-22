package vokabeltrainer.types.grammatical.expressionkind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

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
         "Adjektiv/Eigenschaftswort",
         ExpressionKindHelper.ADJECTIVE_ENUMS),
   ADVERB(
         "Adverb",
         ExpressionKindHelper.ADVERB_ENUMS),
   ADVERB_PLUS(
         "Adverb mit Zusatz",
         ExpressionKindHelper.ADVERB_PLUS_ENUMS),
   ALTERSANGABE(
         "Altersangabe",
         ExpressionKindHelper.ALTERSANGABE_ENUMS),
   AUSRUF(
         "Interjektion/Ausruf",
         ExpressionKindHelper.AUSRUF_ENUMS),
   ARTIKEL(
         "Artikel",
         ExpressionKindHelper.ARTIKEL_ENUMS),
   BERUF(
         "Beruf",
         ExpressionKindHelper.BERUF_ENUMS),
   BINDEWORT(
         "Konjunktion/Bindewort",
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
         "Pronom/Fürwort",
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
   RELATIVPRONOM(
         "Relativpronom",
         ExpressionKindHelper.RELATIVPRONOM_ENUMS),
   SUBSTANTIV(
         "Substantiv/Hauptwort",
         ExpressionKindHelper.SUBSTANTIV_ENUMS),
   UHRZEIT(
         "Uhrzeit",
         ExpressionKindHelper.UHRZEIT_ENUMS),
   UMGANGSPRACHE(
         "Umgangsprache",
         ExpressionKindHelper.UMGANGSSPRACHE_ENUMS),
   VERB(
         "Verb/Tuwort",
         ExpressionKindHelper.VERB_ENUMS),
   WOCHENTAG(
         "Wochentag",
         ExpressionKindHelper.WOCHENTAG_ENUMS),
   ZAHL(
         "Zahl",
         ExpressionKindHelper.ZAHL_ENUMS);

   private String description;
   private GrammaticalParentEnum[] grammaticalParentEnums;

   ExpressionKind(String description,
         GrammaticalParentEnum[] grammaticalParentEnums)
   {
      this.description = description;
      this.grammaticalParentEnums = grammaticalParentEnums;
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
      case ADVERB_PLUS:
      case ALTERSANGABE:
      case AUSRUF:
      case ARTIKEL:
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
      case RELATIVPRONOM:
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

   public static int getNumberOfValues()
   {
      return ExpressionKind.values().length;
   }

   public String getDescription()
   {
      return description;
   }

   public static ExpressionKindTableModel getModelForMultiselect()
   {
      Vector<Vector<ExpressionKindTableRow>> data = new Vector<>();
      for (ExpressionKindItem kind : ExpressionKindHelper
            .getAllExpressionKindItems())
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

   public static ExpressionKindTableModel2 getModelForSingleselect()
   {
      Vector<Vector<ExpressionKindTableRow2>> data = new Vector<>();
      for (ExpressionKindItem kind : ExpressionKindHelper
            .getAllExpressionKindItems())
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

   public static ExpressionKindTableModel getModelForMultiselect(
         Set<ExpressionKind> expressionKinds)
   {
      Set<ExpressionKindItem> expressionKindItems = new HashSet<>();
      Vector<Vector<ExpressionKindTableRow>> data = new Vector<>();
      for (ExpressionKind kind : ExpressionKindHelper
            .getAllExpressionKinds())
      {
         ExpressionKindItem item = new ExpressionKindItem(kind);
         if (expressionKinds.contains(kind))
         {
            item.setSelected(true);
            expressionKindItems.add(item);
         }
         else
         {
            item.setSelected(false);
            expressionKindItems.add(item);
         }
         Vector<ExpressionKindTableRow> row = new Vector<>();
         row.add(new ExpressionKindTableRow(item));
         data.add(row);
      }
      Vector<String> columnNames = new Vector<>();
      columnNames.add("eins");
      return new ExpressionKindTableModel(data, columnNames);
   }

   public static Set<GrammaticalParentEnum> getSetOfGrammaticalParentEnums(
         List<ExpressionKindTableRow> rows)
   {
      return rows
            .stream()
            .map(row -> Arrays
                  .stream(row
                        .getExpressionKindItem()
                        .getKind()
                        .getGrammaticalParentEnums())
                  .collect(Collectors.toSet()))
            .flatMap(Set::stream)
            .collect(Collectors.toSet());
   }

   public static Set<GrammaticalParentEnum> getSetOfGrammaticalParentEnums(
         Set<ExpressionKind> kinds)
   {
      return kinds
            .stream()
            .map(kind -> Arrays
                  .stream(kind.getGrammaticalParentEnums())
                  .collect(Collectors.toSet()))
            .flatMap(Set::stream)
            .collect(Collectors.toSet());
   }

   public GrammaticalParentEnum[] getGrammaticalParentEnums()
   {
      return grammaticalParentEnums;
   }
}
