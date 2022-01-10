package vokabeltrainer.panels.translation;

import java.util.UUID;

public class TranslationCodeWrapper
{
   private boolean available;
   private UUID uuid = null;
   private String anyName = null;
   private TranslationCode code;
   
   public TranslationCodeWrapper(TranslationCode code)
   {
      this.code = code;
   }
   
   public String toString()
   {
      if(TranslationCode.ANY_ltr_ == code || TranslationCode.ANY_rtl_ == code)
      {
         if(anyName != null)
         {
            return anyName;
         }        
      }
      return code.getName();
   }

   public boolean isAvailable()
   {
      if(TranslationCode.de_original == code)
      {
         return true;
      }
     return available;
   }

   public void setAvailable(boolean available)
   {
      this.available = available;
   }

   public UUID getUuid()
   {
      return uuid;
   }

   public void setUuid(UUID uuid)
   {
      this.uuid = uuid;
   }

   public String getAnyName()
   {
      return anyName;
   }

   public void setAnyName(String anyName)
   {
      this.anyName = anyName;
   }

   public TranslationCode getCode()
   {
      return code;
   }

   public void setCode(TranslationCode code)
   {
      this.code = code;
   }
}
