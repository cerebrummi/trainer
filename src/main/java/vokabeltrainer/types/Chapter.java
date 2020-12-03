package vokabeltrainer.types;

import java.text.Collator;
import java.util.Locale;

public class Chapter implements Comparable<Chapter>
{
   private String name = "";
   private Database origin = Database.TO_BE_DETERMINED;
   private String databaseName = "";

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

   public Chapter(String databaseName, String name,
         Database origin)
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

   public boolean isSelf()
   {
      return origin == Database.SELF;
   }

   public boolean isImported()
   {
      return origin == Database.IMPORTED;
   }
   
   public boolean isUnknown()
   {
      return origin == Database.UNKNOWN;
   }
   
   public String getDatabaseName()
   {
      if (isImported() || isSelf())
      {
         return databaseName;
      }
      return origin.getName();
   }

   public void setDatabaseName(String databaseName) throws IllegalAccessException
   {
      if (isImported() || isSelf() || isUnknown())
      {
         this.databaseName = databaseName;
      }
      throw new IllegalAccessException("diese Datenbank kann nicht umbenannt werden");
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
