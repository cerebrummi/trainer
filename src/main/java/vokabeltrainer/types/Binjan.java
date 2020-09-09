package vokabeltrainer.types;

public enum Binjan
{
   NA("nicht anwendbar"), 
   UNKOWN("unbekannt"), 
   PAAL("pa'al"), 
   PAAL_HOHL("pa'al hohl"), 
   PAAL_SCHWACH("pa'al schwach"), 
   PIEL("pi'el"), 
   HIFIL("hif'il"), 
   HITPAEL("hitpa'el"), 
   HUFAL("huf'al"),
   PUAL("pu'al"), 
   NIFAL("nif'al");

   String name;

   Binjan(String name)
   {
      this.name = name;
   }

   @Override
   public String toString()
   {
      return name;
   }
}
