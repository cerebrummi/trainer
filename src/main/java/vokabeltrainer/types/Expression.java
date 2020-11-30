package vokabeltrainer.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import vokabeltrainer.common.LetterForSaving;
import vokabeltrainer.types.grammatical.expressionkind.Definitions;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

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
   private Definitions definitions;
   private LetterForSaving letterForSaving;
   private String additionalInformation;
   private LocalDateTime lastModified;

   public Expression(boolean preset, boolean doNotChange)
   {
      this.doNotChange = doNotChange;
      if (preset)
      {
         uuid = UUID.randomUUID();
         german = "";
         hebrewInLatin = "";
         chapter = new Chapter();
         definitions = new Definitions();
         lastModified = LocalDateTime.now();
      }
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

   public Definitions getDefinitions()
   {
      return definitions;
   }

   public void setDefinitions(Definitions definitions)
   {
      this.definitions = definitions;
   }

   public boolean addExpressionKind(ExpressionKind expressionKind)
   {
      return definitions.addExpressionKind(expressionKind);
   }

   public LetterForSaving getLetterForSaving()
   {
      return letterForSaving;
   }

   public void setLetterForSaving(LetterForSaving letterForSaving)
   {
      this.letterForSaving = letterForSaving;
   }

   public String getAdditionalInformation()
   {
      return additionalInformation;
   }

   public void setAdditionalInformation(String additionalInformation)
   {
      this.additionalInformation = additionalInformation;
   }

   public LocalDateTime getLastModified()
   {
      return lastModified;
   }

   public void setLastModified(LocalDateTime lastModified)
   {
      this.lastModified = lastModified;
   }
   
   public void toggleLastModified()
   {
      this.lastModified = LocalDateTime.now();
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
      result[index] = definitions.getGenderDescriptions();
      index++;
      result[index] = definitions.getNumerusDescriptions();
      index++;
      result[index] = definitions.getBinjanDescriptions();
      index++;
      result[index] = definitions.getExpressionKindDescriptions();
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
      result[index] = definitions.getGenderDescriptions();
      index++;
      result[index] = definitions.getNumerusDescriptions();
      index++;
      result[index] = definitions.getBinjanDescriptions();
      index++;
      result[index] = definitions.getExpressionKindDescriptions();
      index++;
      result[index] = "Kapitel: " + chapter.getName();
      return result;
   }

   public String getAdditionalInfoGerman()
   {
      StringJoiner joiner = new StringJoiner(", ");
      if (!definitions.getNumerusInfos().isEmpty())
      {
         joiner.add(definitions.getNumerusInfos());
      }
      if (!definitions.getGenderInfos().isEmpty())
      {
         joiner.add(definitions.getGenderInfos());
      }
      joiner.add(definitions.getExpressionKindDescriptions());
      return joiner.toString();
   }

   public String getAdditionalInfoHebrew()
   {
      return definitions.getExpressionKindDescriptions();
   }

   public String getExpressionPrintLineForSaving()
   {
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(uuid.toString());
      joiner.add(chapter.getName());
      joiner.add(german);
      joiner.add(hebrew);
      joiner.add(hebrewInLatin);
      joiner.add(definitions.getExpressionKindsForSaving());
      joiner.add(definitions.getGrammaticalEnumsForSaving());
      joiner.add(additionalInformation);
      joiner.add(getSearchWordsGermanForSaving());
      joiner.add(getSearchWordsHebrewForSaving());
      joiner.add(lastModified.toString());
      return joiner.toString();
   }
   
   private String getSearchWordsGermanForSaving()
   {
      StringJoiner joiner = new StringJoiner(",");
      for(String word : this.searchwordsGerman)
      {
         joiner.add(word);
      }
      return joiner.toString();
   }
   
   private String getSearchWordsHebrewForSaving()
   {
      StringJoiner joiner = new StringJoiner(",");
      for(String word : this.searchwordsHebrew)
      {
         joiner.add(word);
      }
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
      joiner.add(definitions.addGrammaticalEnumsForCopy("\n"));
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
      if (!definitions.getNumerusInfos().isEmpty())
      {
         joiner.add(definitions.getNumerusInfos());
      }
      if (!definitions.getGenderInfos().isEmpty())
      {
         joiner.add(definitions.getGenderInfos());
      }
      joiner.add(definitions.getExpressionKindDescriptions());
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
