package vokabeltrainer.types.grammatical;

public enum Binjan
{
   UNKOWN("unbekannt"), 
   PAAL("pa'al"), 
   PAAL_HOHL("pa'al hohl"), 
   PAAL_SCHWACH("pa'al schwach"), 
   PIEL("pi'el"), 
   HIFIL("hif'il"), 
   HITPAEL("hitpa'el"), 
   HUFAL("huf'al"),
   PUAL("pu'al"), 
   NIFAL("nif'al"),
   NA("nicht anwendbar");

   String description;

   Binjan(String description)
   {
      this.description = description;
   }

   @Override
   public String toString()
   {
      return description;
   }
}
