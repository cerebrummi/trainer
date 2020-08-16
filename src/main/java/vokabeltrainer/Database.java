package vokabeltrainer;

public enum Database
{
   ROSENGARTEN("rosengarten", "Ivrit Schritt für Schritt");
   
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
