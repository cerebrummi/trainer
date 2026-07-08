package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LetterForAnalysis
{
   private NikudLetter content;
   private List<NikudLetter> listLowerPunktation = new ArrayList<>();
   private NikudLetter dagesh;
   private Set<NikudLetter> setUpperPunktation = new HashSet<>();
   private SwedishLetter swedishContent;
   private GermanLetter germanContent;
   private LetterType type;
   private boolean swedish;
   private boolean nikud;
   private boolean german;

   public LetterForAnalysis(NikudLetter content)
   {
      this.content = content;
      type = LetterType.HEBREW;
      nikud = true;
      swedish = false;
      german = false;
   }

   public LetterForAnalysis(SwedishLetter content)
   {
      this.swedishContent = content;
      type = LetterType.SWEDISH;
      nikud = false;
      swedish = true;
      german = false;
   }

   public LetterForAnalysis(GermanLetter content)
   {
      this.germanContent = content;
      type = LetterType.GERMAN;
      nikud = false;
      swedish = false;
      german = true;
   }

   public LetterForAnalysis(Letter content)
   {
      if (content instanceof NikudLetter)
      {
         this.content = (NikudLetter) content;
         type = LetterType.HEBREW;
         nikud = true;
         swedish = false;
         german = false;
      }
      else if (content instanceof SwedishLetter)
      {
         this.swedishContent = (SwedishLetter) content;
         type = LetterType.SWEDISH;
         nikud = false;
         swedish = true;
         german = false;
      }
      else if (content instanceof GermanLetter)
      {
         this.germanContent = (GermanLetter) content;
         type = LetterType.GERMAN;
         nikud = false;
         swedish = false;
         german = true;
      }
   }

   public NikudLetter getNikudContent()
   {
      return content;
   }

   public Letter getContent()
   {
      if (isNikud())
      {
         return content;
      }
      else if (isSwedish())
      {
         return swedishContent;
      }
      else
      {
         return germanContent;
      }
   }

   public SwedishLetter getSwedishContent()
   {
      return swedishContent;
   }

   public GermanLetter getGermanContent()
   {
      return germanContent;
   }

   public boolean isGerman()
   {
      return german;
   }

   public boolean isNotGerman()
   {
      return nikud || swedish;
   }

   public boolean isSwedish()
   {
      return swedish;
   }

   public boolean isNotSwedish()
   {
      return nikud || german;
   }

   public boolean isNikud()
   {
      return nikud;
   }

   public boolean isNotNikud()
   {
      return german || swedish;
   }

   public LetterForAnalysis clone()
   {
      if (this.type == LetterType.HEBREW)
      {
         LetterForAnalysis duplicate = new LetterForAnalysis(content);
         duplicate.setListLowerPunktation(
               Collections.nCopies(1, listLowerPunktation).get(0));
         duplicate.setDagesh(dagesh);
         duplicate.getSetUpperPunktation().addAll(setUpperPunktation);
         return duplicate;
      }
      if (this.type == LetterType.SWEDISH)
      {
         return new LetterForAnalysis(swedishContent);
      }
      return new LetterForAnalysis(germanContent);
   }

   public static boolean isEqual(LetterForAnalysis letter1,
         LetterForAnalysis letter2, LetterType type)
   {
      if (type == LetterType.HEBREW)
      {
         if (letter1.getContent() == letter2.getContent())
         {
            return true;
         }
      }
      else if (type == LetterType.SWEDISH)
      {
         if (letter1.getSwedishContent() == letter2.getSwedishContent())
         {
            return true;
         }
      }
      else if (type == LetterType.GERMAN)
      {
         if (letter1.getGermanContent() == letter2.getGermanContent())
         {
            return true;
         }
      }

      return false;
   }

   public void addToLowerPunktation(NikudLetter punktation)
   {
      listLowerPunktation.add(punktation);
   }

   public void addDagesh(NikudLetter dagesh)
   {
      this.dagesh = dagesh;
   }

   public void addToUpperPunktation(NikudLetter punktation)
   {
      setUpperPunktation.add(punktation);
   }

   public List<NikudLetter> getListLowerPunktation()
   {
      return listLowerPunktation;
   }

   public NikudLetter getDagesh()
   {
      return dagesh;
   }

   public Set<NikudLetter> getSetUpperPunktation()
   {
      return setUpperPunktation;
   }

   public void setListLowerPunktation(List<NikudLetter> listLowerPunktation)
   {
      this.listLowerPunktation = listLowerPunktation;
   }

   public void setDagesh(NikudLetter dagesh)
   {
      this.dagesh = dagesh;
   }

   public void setSetUpperPunktation(Set<NikudLetter> setUpperPunktation)
   {
      this.setUpperPunktation = setUpperPunktation;
   }

   public int getPixelWidth()
   {
      if (content != null)
      {
         return content.getPixelWidth();
      }
      return 18;
   }
}
