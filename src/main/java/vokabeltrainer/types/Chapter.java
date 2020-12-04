package vokabeltrainer.types;

import java.text.Collator;
import java.util.Locale;

public class Chapter implements Comparable<Chapter>
{
   private String name = "";
   private Database origin = Database.TO_BE_DETERMINED;
   private String databaseName = "";
   private String databaseFolder = "";

   public Chapter()
   {

   }

   public Chapter(Database origin)
   {
      this.origin = origin;
   }

   public Chapter(String name, Database origin)
   {
      this.name = name;
      this.origin = origin;
   }

   public Chapter(String databaseName, String name, Database origin)
   {
      this.databaseName = databaseName;
      this.name = name;
      this.origin = origin;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Database getOrigin()
   {
      return origin;
   }

   public void setOrigin(Database origin)
   {
      this.origin = origin;
   }

   @Override
   public int compareTo(Chapter o)
   {
      if (this.equals(o))
      {
         return 0;
      }
      Collator coll = Collator.getInstance(Locale.GERMAN);
      coll.setStrength(Collator.PRIMARY);
      return coll.compare(this.name, o.name);
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Chapter other = (Chapter) obj;
      if (name == null)
      {
         if (other.name != null)
            return false;
      }
      else if (!name.equals(other.name))
         return false;
      return true;
   }

   public String getDatabaseFolder(Database database)
   {
      if (Database.ADDITIONAL_VALUE == database)
      {
         return this.databaseFolder;
      }
      return database.getFolder();
   }

   public String getDatabaseName()
   {
      if (Database.ADDITIONAL_VALUE == origin || Database.IMPORTED == origin
            || Database.SELF == origin || Database.UNKNOWN == origin)
      {
         return this.databaseName;
      }
      return origin.getName();
   }

   public void setDatabaseName(String databaseName)
   {
      if (Database.ADDITIONAL_VALUE == origin || Database.IMPORTED == origin
            || Database.SELF == origin || Database.UNKNOWN == origin)
      {
         this.databaseName = databaseName;
      }
   }

   public enum Database
   {
      ROSENGARTEN(
            "rosengarten",
            "Ivrit Schritt für Schritt"),
      BEKEF(
            "bekef",
            "Ivrit bekef"),
      SELF(
            "",
            "selbst eingegeben"),
      IMPORTED(
            "",
            "importiert"),
      UNKNOWN(
            "",
            "unbekannt"),
      TO_BE_DETERMINED(
            "",
            "soll bestimmt werden"),
      ADDITIONAL_VALUE(
            "",
            "weiterer Wert");

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
   
   public static Database findOrigin(String databaseName)
   {
      if (Database.ROSENGARTEN.getName().equals(databaseName))
      {
         return Database.ROSENGARTEN;
      }
      
      if (Database.BEKEF.getName().equals(databaseName))
      {
         return Database.BEKEF;
      }
      return Database.UNKNOWN;
   }
}
