package vokabeltrainer.types;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.ExpressionKind;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbConjugation;
import vokabeltrainer.types.grammatical.VerbStrength;
import vokabeltrainer.types.grammatical.VerbType;

public class Expression
{
   private UUID uuid;
   private String german;
   private String hebrew;
   private String hebrewInLatin;
   private List<String> searchwordsGerman = new ArrayList<>();
   private List<String> searchwordsHebrew = new ArrayList<>();
   private TrainingStatus trainingStatusDToH = new TrainingStatus();
   private TrainingStatus trainingStatusHToD = new TrainingStatus();
   private Chapter chapter;
   private boolean selected;
   private boolean doNotChange;
   private Database origin;
   private Map<Class<? extends GrammaticalEnum>, Enum<? extends GrammaticalEnum>> grammaticalEnumMap = new HashMap<>();

   public Expression(boolean preset, boolean doNotChange)
   {
      this.doNotChange = doNotChange;
      if (preset)
      {
         uuid = UUID.randomUUID();
         german = "";
         hebrewInLatin = "";
         chapter = new Chapter();
      }
      fillGrammaticalEnumMap();
   }

   private void fillGrammaticalEnumMap()
   {
      // always visible in GUI
      grammaticalEnumMap.put(Gender.class, Gender.GENDER_UNKNOWN);
      grammaticalEnumMap.put(Numerus.class, Numerus.NUMERUS_UNKNOWN);
      grammaticalEnumMap.put(ExpressionKind.class,
            ExpressionKind.EXPRESSIONKIND_UNKNOWN);
      // extra info in GUI
      grammaticalEnumMap.put(Binjan.class, Binjan.BINJAN_NA);
      grammaticalEnumMap.put(GrammaticalPerson.class,
            GrammaticalPerson.GRAMMATICALPERSON_NA);
      grammaticalEnumMap.put(VerbConjugation.class,
            VerbConjugation.VERBCONJUGATION_NA);
      grammaticalEnumMap.put(VerbStrength.class, VerbStrength.VERBSTENGTH_NA);
      grammaticalEnumMap.put(VerbType.class, VerbType.VERBTYPE_NA);
   }

   @SuppressWarnings("unchecked")
   public void setGrammaticalEnum(Class<? extends GrammaticalEnum> clazz,
         String value)
   {
      GrammaticalEnum e = (GrammaticalEnum) grammaticalEnumMap.get(clazz);
      grammaticalEnumMap.put(clazz,
            (Enum<? extends GrammaticalEnum>) e.fromEnumName(value));
   }

   @SuppressWarnings("unchecked")
   public void setGrammaticalEnum(Enum<? extends GrammaticalEnum> e)
   {
      grammaticalEnumMap.put((Class<? extends GrammaticalEnum>) e.getClass(),
            e);
   }

   public Enum<?> getGrammaticalEnum(Class<? extends GrammaticalEnum> clazz)
   {
      return grammaticalEnumMap.get(clazz);
   }

   public void setSearchwordsGerman(String[] searchwords)
   {
      for (int i = 0; i < searchwords.length; i++)
      {
         this.searchwordsGerman.add(searchwords[i].trim());
      }
   }

   public void setSearchwordsHebrew(String[] searchwords)
   {
      for (int i = 0; i < searchwords.length; i++)
      {
         this.searchwordsHebrew.add(searchwords[i].trim());
      }
   }

   public UUID getUuid()
   {
      return uuid;
   }

   public void setUuid(UUID uuid)
   {
      this.uuid = uuid;
   }

   public String getGerman()
   {
      return german;
   }

   public void setGerman(String german)
   {
      this.german = german;
   }

   public String getHebrewInLatin()
   {
      return hebrewInLatin;
   }

   public void setHebrewInLatin(String hebrewInLatin)
   {
      this.hebrewInLatin = hebrewInLatin;
   }

   public String getHebrew()
   {
      return hebrew;
   }

   public void setHebrew(String hebrew)
   {
      this.hebrew = hebrew;
   }

   public List<String> getSearchwordsGerman()
   {
      return searchwordsGerman;
   }

   public void setSearchwordsGerman(List<String> searchwordsGerman)
   {
      this.searchwordsGerman = searchwordsGerman;
   }

   public List<String> getSearchwordsHebrew()
   {
      return searchwordsHebrew;
   }

   public void setSearchwordsHebrew(List<String> searchwordsHebrew)
   {
      this.searchwordsHebrew = searchwordsHebrew;
   }

   public TrainingStatus getTrainingStatusDToH()
   {
      return trainingStatusDToH;
   }

   public void setTrainingStatusDToH(TrainingStatus trainingStatusDToH)
   {
      this.trainingStatusDToH = trainingStatusDToH;
   }

   public TrainingStatus getTrainingStatusHToD()
   {
      return trainingStatusHToD;
   }

   public void setTrainingStatusHToD(TrainingStatus trainingStatusHToD)
   {
      this.trainingStatusHToD = trainingStatusHToD;
   }

   public Chapter getChapter()
   {
      return chapter;
   }

   public void setChapter(Chapter chapter)
   {
      this.chapter = chapter;
   }

   public String getChapterGermanComparison()
   {
      return chapter.getName() + " " + german;
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
      this.selected = !this.selected;
   }

   public boolean isDoNotChange()
   {
      return doNotChange;
   }

   public Database getOrigin()
   {
      return origin;
   }

   public void setOrigin(Database origin)
   {
      this.origin = origin;
   }

   public List<Class<? extends GrammaticalEnum>> getSortedGrammaticalEnumKeys()
   {
      List<Class<? extends GrammaticalEnum>> keyList = new ArrayList<>();
      keyList.addAll(grammaticalEnumMap.keySet());
      Collections.sort(keyList,
            new Comparator<Class<? extends GrammaticalEnum>>()
            {

               @Override
               public int compare(Class<? extends GrammaticalEnum> o1,
                     Class<? extends GrammaticalEnum> o2)
               {
                  return String.valueOf(o1)
                        .compareToIgnoreCase(String.valueOf(o2));
               }

            });
      return keyList;
   }

   public String[] toGermanArrayForTableEntry()
   {
      int index = 0;
      String[] result = new String[9];
      result[index] = String.valueOf(selected);
      index++;
      result[index] = german;
      index++;
      result[index] = hebrewInLatin;
      index++;
      result[index] = hebrew;
      index++;
      result[index] = ((Gender) grammaticalEnumMap.get(Gender.class))
            .toDescription();
      index++;
      result[index] = ((Numerus) grammaticalEnumMap.get(Numerus.class))
            .toDescription();
      index++;
      result[index] = ((Binjan) grammaticalEnumMap.get(Binjan.class))
            .toDescription();
      index++;
      result[index] = ((ExpressionKind) grammaticalEnumMap
            .get(ExpressionKind.class)).toDescription();
      index++;
      result[index] = "Kapitel: " + chapter.getName();
      return result;
   }

   public String[] toHebrewArray()
   {
      int index = 0;
      String[] result = new String[9];
      result[index] = String.valueOf(selected);
      index++;
      result[index] = hebrew;
      index++;
      result[index] = hebrewInLatin;
      index++;
      result[index] = german;
      index++;
      result[index] = ((Gender) grammaticalEnumMap.get(Gender.class))
            .toDescription();
      index++;
      result[index] = ((Numerus) grammaticalEnumMap.get(Numerus.class))
            .toDescription();
      index++;
      result[index] = ((Binjan) grammaticalEnumMap.get(Binjan.class))
            .toDescription();
      index++;
      result[index] = ((ExpressionKind) grammaticalEnumMap
            .get(ExpressionKind.class)).toDescription();
      index++;
      result[index] = "Kapitel: " + chapter.getName();
      return result;
   }

   public String getAdditionalInfoGerman()
   {
      StringJoiner joiner = new StringJoiner(", ");
      if (!((Numerus) grammaticalEnumMap.get(Numerus.class)).toInfo().isEmpty())
      {
         joiner.add(((Numerus) grammaticalEnumMap.get(Numerus.class)).toInfo());
      }
      if (!((Gender) grammaticalEnumMap.get(Gender.class)).toInfo().isEmpty())
      {
         joiner.add(((Gender) grammaticalEnumMap.get(Gender.class)).toInfo());
      }
      joiner.add(((ExpressionKind) grammaticalEnumMap.get(ExpressionKind.class))
            .toDescription());
      return joiner.toString();
   }

   public String getAdditionalInfoHebrew()
   {
      return ((ExpressionKind) grammaticalEnumMap.get(ExpressionKind.class))
            .toDescription();
   }

   private String addGrammaticalEnumsForPrint(String tag)
   {
      StringJoiner joiner = new StringJoiner(tag);
      for (Class<? extends GrammaticalEnum> clazz : getSortedGrammaticalEnumKeys())
      {
         joiner.add(grammaticalEnumMap.get(clazz).name());
      }
      return joiner.toString();
   }

   public String getExpressionPrintLine()
   {
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(uuid.toString());
      joiner.add(chapter.getName());
      joiner.add(german);
      joiner.add(hebrewInLatin);
      joiner.add(hebrew);
      joiner.add(addGrammaticalEnumsForPrint("\t"));
      StringJoiner searchJoinerGerman = new StringJoiner(",");
      for (String word : searchwordsGerman)
      {
         searchJoinerGerman.add(word);
      }
      joiner.add(searchJoinerGerman.toString());

      StringJoiner searchJoinerHebrew = new StringJoiner(",");
      for (String word : searchwordsHebrew)
      {
         searchJoinerHebrew.add(word);
      }
      joiner.add(searchJoinerHebrew.toString());
      return joiner.toString();
   }

   public String getCopyLines(Language language)
   {
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add(chapter.getName());
      if (Language.GERMAN.equals(language))
      {
         joiner.add(german);
         joiner.add(hebrewInLatin);
         joiner.add(hebrew);
      }
      else
      {
         joiner.add(hebrew);
         joiner.add(hebrewInLatin);
         joiner.add(german);
      }
      joiner.add(addGrammaticalEnumsForCopy("\n"));
      StringJoiner searchJoinerGerman = new StringJoiner(",");
      for (String word : searchwordsGerman)
      {
         searchJoinerGerman.add(word);
      }
      joiner.add("Suchworte Deutsch: " + searchJoinerGerman.toString());
      StringJoiner searchJoinerHebrew = new StringJoiner(",");
      for (String word : searchwordsHebrew)
      {
         searchJoinerHebrew.add(word);
      }
      joiner.add("Suchworte Hebräisch: " + searchJoinerHebrew.toString());
      return joiner.toString();
   }

   private String addGrammaticalEnumsForCopy(String tag)
   {
      StringJoiner joiner = new StringJoiner(tag);
      for (Enum<?> e : grammaticalEnumMap.values())
      {
         joiner.add(((GrammaticalEnum) e).toDescription());
      }
      return joiner.toString();
   }

   public String getTrainingPrintLine(Language languageDirection)
   {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(this.uuid.toString());
      if (Language.GERMAN.equals(languageDirection))
      {
         joiner.add(
               this.trainingStatusDToH.getNextDate().format(dateTimeFormatter));
         joiner.add(this.trainingStatusDToH.getRepetition().name());
         joiner.add(String.valueOf(this.trainingStatusDToH.getTrys()));
      }
      else
      {
         joiner.add(
               this.trainingStatusHToD.getNextDate().format(dateTimeFormatter));
         joiner.add(this.trainingStatusHToD.getRepetition().name());
         joiner.add(String.valueOf(this.trainingStatusHToD.getTrys()));
      }
      return joiner.toString();
   }

   public TrainingStatus getTrainingStatus(Language languageDirection)
   {
      if (Language.GERMAN.equals(languageDirection))
      {
         return this.getTrainingStatusDToH();
      }
      return this.getTrainingStatusHToD();
   }

   public String getAdditionalInfoGermanForStatistics()
   {
      StringJoiner joiner = new StringJoiner(", ");
      if (!((Numerus) grammaticalEnumMap.get(Numerus.class)).toInfo().isEmpty())
      {
         joiner.add(((Numerus) grammaticalEnumMap.get(Numerus.class)).toInfo());
      }
      if (!((Gender) grammaticalEnumMap.get(Gender.class)).toInfo().isEmpty())
      {
         joiner.add(((Gender) grammaticalEnumMap.get(Gender.class)).toInfo());
      }
      joiner.add(((ExpressionKind) grammaticalEnumMap.get(ExpressionKind.class))
            .toDescription());
      return joiner.toString();
   }

   public String getWordGermanForStatistics(Language language)
   {
      if (Language.GERMAN == language) // DtoH
      {
         return german + "   [" + this.getTrainingStatusDToH().getTrys()
               + " mal "
               + this.getTrainingStatusDToH().getRepetition().getTranslation()
               + "]  [" + chapter.getName() + "]   "
               + this.getAdditionalInfoGermanForStatistics();
      }
      else // HtoD
      {
         return german + "   [" + this.getTrainingStatusHToD().getTrys()
               + " mal "
               + this.getTrainingStatusHToD().getRepetition().getTranslation()
               + "]  [" + chapter.getName() + "]   "
               + this.getAdditionalInfoGermanForStatistics();
      }
   }

   public String getWordGermanForSuccess()
   {
      return "<html><p>" + chapter.getName() + "</p><br>"
            + "<p style=\"margin-top:5;\"><b>" + german + "</b></p><br>"
            + "<p style=\"margin-top:5;\">" + hebrew + "</p><br>"
            + "<p style=\"margin-top:5;\">" + this.getAdditionalInfoGerman()
            + ", <i>" + hebrewInLatin + "</i></p></html>";
   }
}
