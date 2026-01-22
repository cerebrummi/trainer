package vokabeltrainer.types;

import vokabeltrainer.common.Settings;

public class LearningLanguage
{
   private boolean simpleHebrew = true;
   private String hebrew = "";
   private String hebrewPlene = "";
   private String hebrewDefektiv = "";
   private String swedish = "";
   private String german = "";
   private LLType lltype;

   public LearningLanguage()
   {
      switch (Settings.getLanguageInput())
      {
      case PLENE_DEFEKTIV:
      case SIMPLE:
         this.lltype = LLType.HEBREW;
         break;
      case SWEDISH:
         this.lltype = LLType.SWEDISH;
         break;
      case GERMAN:
         this.lltype = LLType.GERMAN;
         break;
      }
   }

   public LearningLanguage(String hebrew, String hebrewPlene, String hebrewDefektiv,
         boolean simpleHebrew, String swedish, String german)
   {
      this.hebrew = hebrew;
      this.hebrewPlene = hebrewPlene;
      this.hebrewDefektiv = hebrewDefektiv;
      this.simpleHebrew = simpleHebrew;
      this.swedish = swedish;
      this.german = german;
      if(!this.swedish.isBlank())
      {
         this.lltype = LLType.SWEDISH;
      }
      else if(!this.german.isBlank())
      {
         this.lltype = LLType.GERMAN;
      }
      else
      {
         this.lltype = LLType.HEBREW;
      }
   }

   public LLType getLltype()
   {
      return lltype;
   }

   public void setLltype(LLType lltype)
   {
      this.lltype = lltype;
   }

   public boolean isSimpleHebrew()
   {
      return this.lltype == LLType.HEBREW && simpleHebrew;
   }
   
   public boolean isPleneDefektiv()
   {
      return this.lltype == LLType.HEBREW && !simpleHebrew;
   }
   
   public boolean isSwedish()
   {
      return LLType.SWEDISH == this.lltype;
   }

   public boolean isGerman()
   {
      return LLType.GERMAN == this.lltype;
   }
   
   public void setSimpleHebrew(boolean simpleHebrew)
   {
      this.simpleHebrew = simpleHebrew;
   }

   public String getHebrewPlene()
   {
      return hebrewPlene;
   }

   public void setHebrewPlene(String hebrewPlene)
   {
      this.hebrewPlene = hebrewPlene;
   }

   public String getHebrewDefektiv()
   {
      return hebrewDefektiv;
   }

   public void setHebrewDefektiv(String hebrewDefektiv)
   {
      this.hebrewDefektiv = hebrewDefektiv;
   }

   public String getHebrew()
   {
      return hebrew;
   }
   
   public String getHebrewNoMatterWhichKind()
   {
      if(simpleHebrew)
      {
         return hebrew;
      }
      
      return hebrewPlene + " | " + hebrewDefektiv;
   }

   public void setHebrew(String hebrew)
   {
      this.hebrew = hebrew;
   }

   public String getSwedish()
   {
      return swedish;
   }

   public void setSwedish(String swedish)
   {
      this.swedish = swedish;
   }

   public String getGerman()
   {
      return german;
   }

   public void setGerman(String german)
   {
      this.german = german;
   }

   @Override
   public String toString()
   {
      if (!this.swedish.isBlank())
      {
         return swedish;
      }
      
      if (!this.german.isBlank())
      {
         return german;
      }
      
      if (this.simpleHebrew)
      {
         return hebrew;
      }
      
      return hebrewPlene + " | " + hebrewDefektiv;
   }
   
   public String toTableEntry()
   {
      if(this.isSwedish())
      {
         return swedish;
      }
      
      if (this.simpleHebrew)
      {  
         return hebrew;
      }
      
      if(this.isGerman())
      {
         return german;
      }
      
      return hebrewPlene + " | " + hebrewDefektiv;
   }

   public String getHewbrewAccordingToType(HebrewType selectionType)
   {
      switch (selectionType)
      {
      case DEFEKTIV:
         return hebrewDefektiv;
      case PLENE:
         return hebrewPlene;
      case SIMPLE:
         return hebrew;
      }
      return null;
   }
}
