package vokabeltrainer.types;

import java.text.Collator;
import java.util.Locale;

public class Chapter implements Comparable<Chapter>
{
   private String name = "";
   private Database origin = Database.UNKNOWN;
   private String databaseNameInCaseImported = "";

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

   public Chapter(String databaseNameInCaseImported, String name,
         Database origin)
   {
      this.databaseNameInCaseImported = databaseNameInCaseImported;
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

   public boolean isSelf()
   {
      return origin == Database.SELF;
   }

   public boolean isImported()
   {
      return origin == Database.IMPORTED;
   }

   public String getDatabaseName()
   {
      if (isImported())
      {
         return databaseNameInCaseImported;
      }
      return origin.getName();
   }

   public void setDatabaseName(String databaseNameInCaseImported) throws IllegalAccessException
   {
      if (isImported())
      {
         this.databaseNameInCaseImported = databaseNameInCaseImported;
      }
      throw new IllegalAccessException("diese Datenbank ist nicht importiert");
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

}
