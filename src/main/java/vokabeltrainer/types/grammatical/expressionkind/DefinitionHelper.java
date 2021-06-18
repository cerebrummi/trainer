package vokabeltrainer.types.grammatical.expressionkind;

import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;

public class DefinitionHelper
{

   private DefinitionHelper()
   {

   }

   public static List<GrammaticalEnum> getDefinitionsNA(
         ExpressionKind expressionKind)
   {
      List<GrammaticalEnum> list = new ArrayList<>();
      switch (expressionKind)
      {
      case VERB:
      case MODALVERB:
         list.add(GrammaticalPerson.GRAMMATICALPERSON_NA);
         break;
      case PERSONALPRAEFIX:
      case PERSONALSUFFIX:
      case PRONOM:
      case DEMONSTRATIVPRONOM:
      case PERSONALPRONOM:
      case POSSESSIVPRONOM:
         list.add(Binjan.BINJAN_NA);
         list.add(VerbTimes.VERBCONJUGATION_NA);
         break;
      case SUBSTANTIV:
      case UHRZEIT:
      case WOCHENTAG:
      case ZAHL:
      case ORDNUNGSZAHL:
      case JAHRESZEIT:
      case EIGENNAME:
      case BERUF:
      case ALTERSANGABE:
      case ADJEKTIV:
      case ADVERB:
      case FARBE:
         list.add(Binjan.BINJAN_NA);
         list.add(GrammaticalPerson.GRAMMATICALPERSON_NA);
         list.add(VerbTimes.VERBCONJUGATION_NA);
         break;
      case ARTIKEL:
         list.add(Binjan.BINJAN_NA);
         list.add(Numerus.NUMERUS_NA);
         list.add(GrammaticalPerson.GRAMMATICALPERSON_NA);
         list.add(VerbTimes.VERBCONJUGATION_NA);
         break;
      case UMGANGSPRACHE:
      case EXPRESSIONKIND_UNKNOWN:
      case AUSRUF:
      case BINDEWORT:
      case FRAGEWORT:
      case KONSTRUKT:
      case PARTIKEL:
      case PRAEPOSITION:
      case REDEWENDUNG:
      case GLUECKWUNSCH:
      default:
         list.add(Binjan.BINJAN_NA);
         list.add(Gender.GENDER_NA);
         list.add(Numerus.NUMERUS_NA);
         list.add(GrammaticalPerson.GRAMMATICALPERSON_NA);
         list.add(VerbTimes.VERBCONJUGATION_NA);
      }
      return list;
   }

   public static List<GrammaticalEnum> getDefinitionsUNKNOWN(
         ExpressionKind expressionKind)
   {
      List<GrammaticalEnum> listNA = getDefinitionsNA(expressionKind);
      List<GrammaticalEnum> list = new ArrayList<>();
      if (!listNA.contains(Binjan.BINJAN_NA))
      {
         list.add(Binjan.BINJAN_UNKNOWN);
      }
      if (!listNA.contains(Gender.GENDER_NA))
      {
         list.add(Gender.GENDER_UNKNOWN);
      }
      if (!listNA.contains(GrammaticalPerson.GRAMMATICALPERSON_NA))
      {
         list.add(GrammaticalPerson.GRAMMATICALPERSON_UNKNOWN);
      }
      if (!listNA.contains(Numerus.NUMERUS_NA))
      {
         list.add(Numerus.NUMERUS_UNKNOWN);
      }
      if (!listNA.contains(VerbTimes.VERBCONJUGATION_NA))
      {
         list.add(VerbTimes.VERBCONJUGATION_UNKNOWN);
      }
      return list;
   }

}
