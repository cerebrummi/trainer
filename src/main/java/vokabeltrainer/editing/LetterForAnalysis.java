package vokabeltrainer.editing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LetterForAnalysis
{
   private Letter content;
   private List<NikudLetter> listLowerPunktation = new ArrayList<>();
   private NikudLetter dagesh;
   private Set<NikudLetter> setUpperPunktation = new HashSet<>();

   public LetterForAnalysis(Letter content)
   {
      this.content = content;
   }

   public Letter getContent()
   {
      return content;
   }

   public LetterForAnalysis clone()
   {
      LetterForAnalysis duplicate = new LetterForAnalysis(content);
      duplicate.setListLowerPunktation(
            Collections.nCopies(1, listLowerPunktation).get(0));
      duplicate.setDagesh(dagesh);
      duplicate.getSetUpperPunktation().addAll(setUpperPunktation);
      return duplicate;
   }
   
   public static boolean isEqual(LetterForAnalysis letter1,LetterForAnalysis letter2)
   {
      if(letter1.getContent() == letter2.getContent())
      {
         return true;
      }
      else if (letter1.getContent() instanceof NikudLetter && letter2.getContent() instanceof HebrewLetter)
      {
         if(((NikudLetter)letter1.getContent()).getHebrewLetter() == letter2.getContent())
         {
            return true;
         }
         return false;
      }
      else if (letter2.getContent() instanceof NikudLetter && letter1.getContent() instanceof HebrewLetter)
      {
         if(((NikudLetter)letter2.getContent()).getHebrewLetter() == letter1.getContent())
         {
            return true;
         }
         return false;
      }
      else if (letter1.isBet() && letter2.isBet())
      {
         return true;
      }
      else if (letter1.isKaf() && letter2.isKaf())
      {
         return true;
      }
      else if (letter1.isPaei() && letter2.isPaei())
      {
         return true;
      }
      else if (letter1.isSsin() && letter2.isSsin())
      {
         return true;
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
      return content.getPixelWidth();
   }

   public boolean isBet()
   {
      return HebrewLetter.BET == this.content.getHebrewLetter()
            || (this.dagesh != null
                  && HebrewLetter.WET == this.content.getHebrewLetter());
   }

   public boolean isKaf()
   {
      return HebrewLetter.KAF == this.content.getHebrewLetter()
            || (this.dagesh != null
                  && HebrewLetter.WAW == this.content.getHebrewLetter());
   }

   public boolean isPaei()
   {
      return HebrewLetter.PAEI == this.content.getHebrewLetter()
            || (this.dagesh != null
                  && HebrewLetter.FAEI == this.content.getHebrewLetter());
   }

   public boolean isSsin()
   {
      return HebrewLetter.SSIN == this.content.getHebrewLetter()
            || (this.setUpperPunktation.contains(NikudLetter.SIN_DOT)
                  && HebrewLetter.SCHIN == this.content.getHebrewLetter());
   }
}
