package vokabeltrainer.types;

public enum Binjan
{
   NA(
         "Binjan nicht anwendbar"
   ), UNKOWN(
         "Binjan unbekannt"
   ), PAAL(
         "pa'al"
   ), PAAL_HOHL(
         "pa'al hohl"
   ), PAAL_SCHWACH(
         "pa'al schwach"
   ), PIEL(
         "pi'el"
   ), HIFIL(
         "hif'il"
   ), HITPAEL(
         "hitpa'el"
   ), HUFAL(
         "huf'al"
   ), PUAL(
         "pu'al"
   ), NIFAL(
         "nif'al"
   );

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

   public static Binjan fromString(String binjan)
   {
      for (Binjan b : Binjan.values())
      {
         if (b.name.equalsIgnoreCase(binjan))
         {
            return b;
         }
      }
      return null;
   }
}
