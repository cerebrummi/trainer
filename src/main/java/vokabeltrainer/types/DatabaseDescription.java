package vokabeltrainer.types;

import java.util.Objects;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.types.Chapter.Database;

public class DatabaseDescription implements Comparable<DatabaseDescription>
{
   private Database database;
   private String databaseName;
   private String authors;
   private String company;
   private boolean selected;

   public DatabaseDescription()
   {
      this("");
   }

   public DatabaseDescription(String databaseName)
   {
      this.databaseName = databaseName;
   }

   public DatabaseDescription(Common common, Database database)
   {
      this.database = database;
      databaseName = database.getName(common);
      authors = database.getAuthors();
      company = database.getCompany();
   }
   
   @Override
   public String toString()
   {
      return databaseName;
   }

   public Database getDatabase()
   {
      return database;
   }

   public void setDatabase(Database database)
   {
      this.database = database;
   }

   public String getDatabaseName()
   {
      return databaseName;
   }

   public void setDatabaseName(String databaseName)
   {
      this.databaseName = databaseName;
   }

   public String getAuthors()
   {
      return authors;
   }

   public void setAuthors(String authors)
   {
      this.authors = authors;
   }

   public String getCompany()
   {
      return company;
   }

   public void setCompany(String company)
   {
      this.company = company;
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(authors, company, database, databaseName);
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
      DatabaseDescription other = (DatabaseDescription) obj;
      return Objects.equals(authors, other.authors)
            && Objects.equals(company, other.company)
            && database == other.database
            && Objects.equals(databaseName, other.databaseName);
   }

   @Override
   public int compareTo(DatabaseDescription o)
   {
      // if(this.getDatabaseName().equals(Database.SELF.getName()))
      // {
      // return 1;
      // }
      // if(o.getDatabaseName().equals(Database.SELF.getName()))
      // {
      // return -1;
      // }
      return this.databaseName.compareTo(o.databaseName);
   }

   public boolean isSelected()
   {
      return selected;
   }

   public void setSelected(boolean selected)
   {
      this.selected = selected;
   }

   public void toggleSelected()
   {
      this.selected = !this.selected;
   }
}
