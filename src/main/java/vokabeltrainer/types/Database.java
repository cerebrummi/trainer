package vokabeltrainer.types;

public enum Database
{
   ROSENGARTEN("rosengarten", "Ivrit Schritt für Schritt"),
   BEKEF("bekef", "Ivrit bekef"),
   SELF("", "selbst eingegeben"),
   IMPORTED("", ""),
   UNKNOWN("","unbekannt"); // to initialize Chapter
   
   private String folder;
   private String name;
   
   Database(String folder, String name)
   {
      this.folder = folder;
      this.name = name;
   }

   public String getFolder()
   {
      return folder;
   }

   public String getName()
   {
      return name;
   }   
}
