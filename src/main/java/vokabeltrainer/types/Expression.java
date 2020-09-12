package vokabeltrainer.types;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
   // grammatical
   private ExpressionKind kind;
   private Gender gender;
   private Numerus numerus;
   private Binjan binjan;
   private GrammaticalPerson grammaticalPerson;
   private VerbConjugation verbConjugation;
   private VerbStrength verbStrength;
   private VerbType verbType;

   private List<Enum<?>> grammaticalEnums = new ArrayList<>();

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
      // always visible
      gender = Gender.UNKOWN;
      numerus = Numerus.UNKNOWN;
      kind = ExpressionKind.UNKOWN;
      // extra information
      binjan = Binjan.NA;
      grammaticalPerson = GrammaticalPerson.NA;
      verbConjugation = VerbConjugation.NA;
      verbStrength = VerbStrength.NA;
      verbType = VerbType.NA;
      grammaticalEnums.add(kind);
      grammaticalEnums.add(gender);
      grammaticalEnums.add(numerus);
      grammaticalEnums.add(binjan);
      grammaticalEnums.add(grammaticalPerson);
      grammaticalEnums.add(verbConjugation);
      grammaticalEnums.add(verbType);
      grammaticalEnums.add(verbStrength);
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

   public Gender getGender()
   {
      return gender;
   }

   public void setGender(Gender genderHebrew)
   {
      this.gender = genderHebrew;
   }

   public Numerus getNumerus()
   {
      return numerus;
   }

   public void setNumerus(Numerus numerusHebrew)
   {
      this.numerus = numerusHebrew;
   }

   public Binjan getBinjan()
   {
      return binjan;
   }

   public void setBinjan(Binjan binjan)
   {
      this.binjan = binjan;
   }

   public ExpressionKind getKind()
   {
      return kind;
   }

   public void setKind(ExpressionKind kind)
   {
      this.kind = kind;
   }

   public GrammaticalPerson getGrammaticalPerson()
   {
      return grammaticalPerson;
   }

   public void setGrammaticalPerson(GrammaticalPerson grammaticalPerson)
   {
      this.grammaticalPerson = grammaticalPerson;
   }

   public VerbConjugation getVerbConjugation()
   {
      return verbConjugation;
   }

   public void setVerbConjugation(VerbConjugation verbConjugation)
   {
      this.verbConjugation = verbConjugation;
   }

   public VerbStrength getVerbStrength()
   {
      return verbStrength;
   }

   public void setVerbStrength(VerbStrength verbStrength)
   {
      this.verbStrength = verbStrength;
   }

   public VerbType getVerbType()
   {
      return verbType;
   }

   public void setVerbType(VerbType verbType)
   {
      this.verbType = verbType;
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
      result[index] = gender.toDescription();
      index++;
      result[index] = numerus.toDescription();
      index++;
      result[index] = binjan.toDescription();
      index++;
      result[index] = kind.toDescription();
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
      result[index] = gender.toDescription();
      index++;
      result[index] = numerus.toDescription();
      index++;
      result[index] = binjan.toDescription();
      index++;
      result[index] = kind.toDescription();
      index++;
      result[index] = "Kapitel: " + chapter.getName();
      return result;
   }

   public String getAdditionalInfoGerman()
   {
      StringJoiner joiner = new StringJoiner(", ");
      if (!(numerus.equals(Numerus.UNKNOWN) || numerus.equals(Numerus.NA)))
      {
         joiner.add(numerus.toString());
      }
      if (!(gender.equals(Gender.UNKOWN) || gender.equals(Gender.NA)))
      {
         joiner.add(gender.toString());
      }
      joiner.add(kind.toString());
      return joiner.toString();
   }

   public String getAdditionalInfoHebrew()
   {
      return kind.toString();
   }

   public List<Enum<?>> getGrammaticalEnums()
   {
      return grammaticalEnums;
   }
   
   private String addGrammaticalEnumsForPrint(String tag)
   {
      StringJoiner joiner = new StringJoiner(tag);
      for(Enum<?> e : this.grammaticalEnums)
      {
         joiner.add(e.name());
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
      for(Enum<?> e : this.grammaticalEnums)
      {
         joiner.add(((GrammaticalEnum)e).toDescription());
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
      if (!(numerus.equals(Numerus.UNKNOWN) || numerus.equals(Numerus.NA)))
      {
         joiner.add(numerus.toString());
      }
      if (!(gender.equals(Gender.UNKOWN) || gender.equals(Gender.NA)))
      {
         joiner.add(gender.toString());
      }
      ;
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
