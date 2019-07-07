package vokabeltrainer.types;

public enum Binjan
{
   UNKOWN(
         "Binjan unbekannt"),
   NA(
         "Binjan nicht anwendbar"),
   PAAL(
         "Paal"),
   PIEL(
         "Piel"),
   HIFIL(
         "Hifil"),
   HITPAEL(
         "Hitpael"),
   HUFAL(
         "Hufal"),
   PUAL(
         "Pual"),
   NIFAL(
         "Nifal");

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
      if (UNKOWN.name.equals(binjan))
      {
         return UNKOWN;
      }
      if (NA.name.equals(binjan))
      {
         return NA;
      }
      if (PAAL.name.equals(binjan))
      {
         return PAAL;
      }
      if (PIEL.name.equals(binjan))
      {
         return PIEL;
      }
      if (HIFIL.name.equals(binjan))
      {
         return HIFIL;
      }
      if (HITPAEL.name.equals(binjan))
      {
         return HITPAEL;
      }
      if (HUFAL.name.equals(binjan))
      {
         return HUFAL;
      }
      if (PUAL.name.equals(binjan))
      {
         return PUAL;
      }
      if (NIFAL.name.equals(binjan))
      {
         return NIFAL;
      }
      return null;
   }
}
