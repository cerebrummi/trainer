package vokabeltrainer.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.regex.Pattern;

import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Letter;
import vokabeltrainer.common.LetterForSaving;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.grammatical.expressionkind.Definitions;

public class Expression
{
   private UUID uuid;
   private String ownLanguage;
   private LearningLanguage ll = new LearningLanguage();
   private List<String> searchwordsGerman = new ArrayList<>();
   private List<String> searchwordsHebrew = new ArrayList<>();
   private TrainingStatus trainingStatusDToLL = new TrainingStatus();
   private TrainingStatus trainingStatusLLToD = new TrainingStatus();
   private Chapter chapter;
   private boolean selected;
   private boolean doNotChange;
   private Database origin;
   private Definitions definitions;
   private LetterForSaving letterForSaving;
   private String additionalInformation;
   private LocalDateTime lastModified;
   private String sortingIndex = "";
   private Translator translator = Common.getTranslator();
   private Integer level = 0;
   private boolean visible;

   public Expression(boolean preset) // for unit testing
   {
      this.doNotChange = true;

      if (preset)
      {
         uuid = UUID.randomUUID();
         ownLanguage = "";
         chapter = new Chapter(Database.SELF);
         definitions = new Definitions();
         lastModified = LocalDateTime.now();
      }
   }

   public Expression(boolean preset, boolean doNotChange)
   {
      this.doNotChange = doNotChange;

      if (preset)
      {
         uuid = UUID.randomUUID();
         ownLanguage = "";
         chapter = new Chapter(Database.SELF);
         definitions = new Definitions();
         lastModified = LocalDateTime.now();
         ll.setSimpleHebrew(Settings.isSimpleHebrewInput());
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

   public String getOwnLanguage()
   {
      return ownLanguage;
   }

   public void setOwnLanguage(String german)
   {
      this.ownLanguage = german;
   }

   public LearningLanguage getLL()
   {
      return ll;
   }

   public void setLearningLanguage(LearningLanguage hebrew)
   {
      this.ll = hebrew;
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

   public TrainingStatus getTrainingStatusDToLL()
   {
      return trainingStatusDToLL;
   }

   public void setTrainingStatusDToLL(TrainingStatus trainingStatusDToH)
   {
      this.trainingStatusDToLL = trainingStatusDToH;
   }

   public TrainingStatus getTrainingStatusLLToD()
   {
      return trainingStatusLLToD;
   }

   public void setTrainingStatusLLToD(TrainingStatus trainingStatusHToD)
   {
      this.trainingStatusLLToD = trainingStatusHToD;
   }

   public TemporaryTrainingStatus getTemporaryTrainingStatus() 
   {
      return new TemporaryTrainingStatus();
   }

   public void setTemporaryTrainingStatus(TemporaryTrainingStatus temporaryTrainingStatus) 
   {
	 // nothing
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
      return chapter.getName() + " " + ownLanguage;
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

   public boolean isDoChange()
   {
      return !doNotChange;
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

   public LetterForSaving getLetterForSaving()
   {
      return letterForSaving;
   }

   public void setLetterForSaving(Letter letterForSaving)
   {
      if (letterForSaving instanceof LetterForSaving)
      {
         this.letterForSaving = (LetterForSaving) letterForSaving;
      }
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

   public String getSortingIndex()
   {
      return sortingIndex;
   }

   public void setSortingIndex(String sortingIndex)
   {
      this.sortingIndex = sortingIndex;
   }

   public Integer getLevel()
   {
      return level;
   }

   public void setLevel(Integer level)
   {
      this.level = level;
   }

   public boolean isVisible()
   {
      return visible;
   }

   public void setVisible(boolean visible)
   {
      this.visible = visible;
   }

   public String[] toHebrewArrayForTableEntry()
   {
      int index = 0;
      String[] result = new String[10];
      result[index] = String.valueOf(selected);
      index++;
      result[index] = ll.toString();
      index++;
      result[index] = ownLanguage;
      index++;
      result[index] = ll.toTableEntry();
      index++;
      result[index] = definitions.getGenderDescriptions();
      index++;
      result[index] = definitions.getNumerusDescriptions() + " "
            + definitions.getGrammaticalPersonDescriptions();
      index++;
      StringJoiner joiner = new StringJoiner(", ");
      if (!definitions.getBinjanDescriptions().isBlank())
      {
         joiner.add(definitions.getBinjanDescriptions());
      }
      if (!definitions.getVerbTimeDescriptions().isBlank())
      {
         joiner.add(definitions.getVerbTimeDescriptions());
      }
      result[index] = joiner.toString();
      index++;
      result[index] = definitions.getExpressionKindDescriptions();
      index++;
      result[index] = translator.realisticTranslate(Translation.KAPITEL) + ": "
            + chapter.getName() + ", "
            + translator.realisticTranslate(Translation.INDEX) + ": "
            + sortingIndex;
      index++;
      result[index] = chapter.getDatabaseName() + " "
            + translator.realisticTranslate(Translation.VOM) + " "
            + lastModified.format(DateTimeFormatter.ofPattern(
                  translator.realisticTranslate(Translation._DATE_TIME)));
      return result;
   }
   
   public String[] toHebrewArrayForTableEntry2()
   {
      int index = 0;
      String[] result = new String[10];
      result[index] = String.valueOf(selected);
      index++;
      result[index] = ll.toString();
      index++;
      result[index] = ownLanguage;
      index++;
      result[index] = ll.toTableEntry();
      index++;
      result[index] = definitions.getGenderDescriptions();
      index++;
      result[index] = definitions.getNumerusDescriptions() + " "
            + definitions.getGrammaticalPersonDescriptions();
      index++;
      StringJoiner joiner = new StringJoiner(", ");
      if (!definitions.getBinjanDescriptions().isBlank())
      {
         joiner.add(definitions.getBinjanDescriptions());
      }
      if (!definitions.getVerbTimeDescriptions().isBlank())
      {
         joiner.add(definitions.getVerbTimeDescriptions());
      }
      result[index] = joiner.toString();
      index++;
      result[index] = definitions.getExpressionKindDescriptions();
      index++;
      result[index] = translator.realisticTranslate(Translation.KAPITEL) + ": "
            + chapter.getName() + ", "
            + translator.realisticTranslate(Translation.INDEX) + ": "
            + sortingIndex;
      index++;
      result[index] = chapter.getDatabaseName() + " "
            + translator.realisticTranslate(Translation.VOM) + " "
            + lastModified.format(DateTimeFormatter.ofPattern(
                  translator.realisticTranslate(Translation._DATE_TIME)));
      return result;
   }
   
   public String[] toSwedishArrayForTableEntry()
   {
      int index = 0;
      String[] result = new String[10];
      result[index] = String.valueOf(selected);
      index++;
      result[index] = ll.toString();
      index++;
      result[index] = ownLanguage;
      index++;
      result[index] = "";
      index++;
      result[index] = definitions.getGenderDescriptions();
      index++;
      result[index] = definitions.getNumerusDescriptions() + " "
            + definitions.getGrammaticalPersonDescriptions();
      index++;
      StringJoiner joiner = new StringJoiner(", ");
      if (!definitions.getBinjanDescriptions().isBlank())
      {
         joiner.add(definitions.getBinjanDescriptions());
      }
      if (!definitions.getVerbTimeDescriptions().isBlank())
      {
         joiner.add(definitions.getVerbTimeDescriptions());
      }
      result[index] = joiner.toString();
      index++;
      result[index] = definitions.getExpressionKindDescriptions();
      index++;
      result[index] = translator.realisticTranslate(Translation.KAPITEL) + ": "
            + chapter.getName() + ", "
            + translator.realisticTranslate(Translation.INDEX) + ": "
            + sortingIndex;
      index++;
      result[index] = chapter.getDatabaseName() + " "
            + translator.realisticTranslate(Translation.VOM) + " "
            + lastModified.format(DateTimeFormatter.ofPattern(
                  translator.realisticTranslate(Translation._DATE_TIME)));
      return result;
   }
   
   public String[] toSwedishArrayForTableEntry2()
   {
      int index = 0;
      String[] result = new String[10];
      result[index] = String.valueOf(selected);
      index++;
      result[index] = ownLanguage;
      index++;
      result[index] = ll.toString();
      index++;
      result[index] = "";
      index++;
      result[index] = definitions.getGenderDescriptions();
      index++;
      result[index] = definitions.getNumerusDescriptions() + " "
            + definitions.getGrammaticalPersonDescriptions();
      index++;
      StringJoiner joiner = new StringJoiner(", ");
      if (!definitions.getBinjanDescriptions().isBlank())
      {
         joiner.add(definitions.getBinjanDescriptions());
      }
      if (!definitions.getVerbTimeDescriptions().isBlank())
      {
         joiner.add(definitions.getVerbTimeDescriptions());
      }
      result[index] = joiner.toString();
      index++;
      result[index] = definitions.getExpressionKindDescriptions();
      index++;
      result[index] = translator.realisticTranslate(Translation.KAPITEL) + ": "
            + chapter.getName() + ", "
            + translator.realisticTranslate(Translation.INDEX) + ": "
            + sortingIndex;
      index++;
      result[index] = chapter.getDatabaseName() + " "
            + translator.realisticTranslate(Translation.VOM) + " "
            + lastModified.format(DateTimeFormatter.ofPattern(
                  translator.realisticTranslate(Translation._DATE_TIME)));
      return result;
   }
   
   public String[] toArrayForTableEntry()
   {
      int index = 0;
      String[] result = new String[3];
      result[index] = String.valueOf(selected);
      index++;
      result[index] = ll.toString();
      index++;
      result[index] = ownLanguage;
      return result;
   }

   public String getAdditionalInfo()
   {
      return additionalInformation;
   }

   public String getGrammarInfo(boolean withExpressionKind)
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
      if (!definitions.getGrammaticalPersonInfos().isEmpty())
      {
         joiner.add(definitions.getGrammaticalPersonInfos());
      }
      if (!definitions.getBinjanInfos().isEmpty())
      {
         joiner.add(definitions.getBinjanInfos());
      }
      if (!definitions.getVerbConjugationInfos().isEmpty())
      {
         joiner.add(definitions.getVerbConjugationInfos());
      }
      if (withExpressionKind
            && !definitions.getExpressionKindDescriptions().isBlank())
      {
         joiner.add(definitions.getExpressionKindDescriptions());
      }
      return joiner.toString();
   }

   public String getExpressionPrintLineForSaving()
   {
      return getExpressionPrintLineForSaving(chapter.getDatabaseName());
   }

   public String getExpressionPrintLineForSaving(String databaseName)
   {
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(uuid.toString());
      Database db = Chapter.findOrigin(databaseName);
      joiner.add(db.name());
      joiner.add(databaseName);
      joiner.add(chapter.getName());
      joiner.add(ownLanguage);
      joiner.add(String.valueOf(ll.isSimpleHebrew()));
      joiner.add(ll.getHebrew());
      joiner.add(ll.getHebrewPlene());
      joiner.add(ll.getHebrewDefektiv());
      joiner.add(ll.getSwedish());
      joiner.add(ll.getGerman());
      joiner.add(definitions.getExpressionKindsForSaving());
      joiner.add(definitions.getGrammaticalEnumsForSaving());
      joiner.add(additionalInformation);
      joiner.add(getSearchWordsGermanForSaving());
      joiner.add(getSearchWordsHebrewForSaving());
      joiner.add(lastModified.toString());
      joiner.add(sortingIndex);
      joiner.add(String.valueOf(level));
      joiner.add(String.valueOf(visible));
      return joiner.toString();
   }

   private String getSearchWordsGermanForSaving()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (String word : this.searchwordsGerman)
      {
         joiner.add(word);
      }
      return joiner.toString();
   }

   private String getSearchWordsHebrewForSaving()
   {
      StringJoiner joiner = new StringJoiner(",");
      for (String word : this.searchwordsHebrew)
      {
         joiner.add(word);
      }
      return joiner.toString();
   }

   public String getCopyLines(Direction language)
   {
      StringJoiner joiner = new StringJoiner("\t");
      if (Direction.OWN_TO_NEW.equals(language))
      {
         joiner.add(ownLanguage);
         joiner.add(ll.toString());
      }
      else
      {
         joiner.add(ll.toString());
         joiner.add(ownLanguage);
      }
      joiner.add(chapter.getName());
      joiner.add(definitions.addExpressionKindsForCopy(", "));
      joiner.add(definitions.addGrammaticalEnumsForCopy(", "));
      StringJoiner searchJoinerGerman = new StringJoiner(", ");
      for (String word : searchwordsGerman)
      {
         searchJoinerGerman.add(word);
      }
      joiner.add(translator.realisticTranslate(Translation.SUCHWORTE)
            + " "
            + translator.realisticTranslate(Translation.DEUTSCH)
            + ": " + searchJoinerGerman.toString());
      StringJoiner searchJoinerHebrew = new StringJoiner(", ");
      for (String word : searchwordsHebrew)
      {
         searchJoinerHebrew.add(word);
      }
      joiner.add(translator.realisticTranslate(Translation.SUCHWORTE)
            + " "
            + translator.realisticTranslate(Translation.HEBRAEISCH)
            + ": " + searchJoinerHebrew.toString());
      if (!additionalInformation.isBlank())
      {
         joiner.add(additionalInformation);
      }
      return joiner.toString();
   }

   public String getTrainingPrintLine(LanguageDirection languageDirection)
   {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
      StringJoiner joiner = new StringJoiner("\t");
      joiner.add(this.uuid.toString());
      if (Direction.OWN_TO_NEW.equals(languageDirection.getDirection()))
      {
         joiner.add(
               this.trainingStatusDToLL.getNextDate().format(dateTimeFormatter));
         joiner.add(this.trainingStatusDToLL.getRepetition().name());
         joiner.add(String.valueOf(this.trainingStatusDToLL.getTrys()));
      }
      else
      {
         joiner.add(
               this.trainingStatusLLToD.getNextDate().format(dateTimeFormatter));
         joiner.add(this.trainingStatusLLToD.getRepetition().name());
         joiner.add(String.valueOf(this.trainingStatusLLToD.getTrys()));
      }
      return joiner.toString();
   }

   public TrainingStatus getTrainingStatus(Direction languageDirection)
   {
      if (Direction.OWN_TO_NEW.equals(languageDirection))
      {
         return this.getTrainingStatusDToLL();
      }
      return this.getTrainingStatusLLToD();
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
      return joiner.toString();
   }

   public String getWordGermanForStatistics(Direction language)
   {
      if (Direction.OWN_TO_NEW.equals(language))
      {
    	 if(this.getTrainingStatusDToLL().getRepetition() == null)
    	 {
    		  return "Fehler bei " + ownLanguage;
    	 }
    	 
         return ownLanguage + "   [" + this.getTrainingStatusDToLL().getTrys() + " "
               + translator.realisticTranslate(Translation.MAL) + " "
               + this.getTrainingStatusDToLL().getRepetition().getTranslation()
               + "]  [" + chapter.getName() + "]   "
               + this.getAdditionalInfoGermanForStatistics();
      }
      else
      {
         return ownLanguage + "   [" + this.getTrainingStatusLLToD().getTrys() + " "
               + translator.realisticTranslate(Translation.MAL) + " "
               + this.getTrainingStatusLLToD().getRepetition().getTranslation()
               + "]  [" + chapter.getName() + "]   "
               + this.getAdditionalInfoGermanForStatistics();
      }
   }

   public String[] getGermanHebrewGrammarArrayForSuccess()
   {
      String[] content = new String[3];
      content[0] = ownLanguage;
      content[1] = ll.toString();
      content[2] = getGrammarInfo(true);
      return content;
   }

   public boolean findPattern(Pattern pattern)
   {
      if (pattern.matcher(ownLanguage).find())
      {
         return true;
      }
      if (pattern.matcher(ll.getHebrew()).find())
      {
         return true;
      }
      if (pattern.matcher(ll.getHebrewPlene()).find())
      {
         return true;
      }
      if (pattern.matcher(ll.getHebrewDefektiv()).find())
      {
         return true;
      }
      return false;
   }
}
