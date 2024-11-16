package vokabeltrainer.types;

import java.util.Objects;

import vokabeltrainer.types.Chapter.Database;

public class DatabaseDescription implements Comparable<DatabaseDescription>
{
   private Database database = Database.TO_BE_DETERMINED;
   private String databaseName = "";
   private String authors = "";
   private String company = "";
   private LLType llType = LLType.UNKOWN;

   public DatabaseDescription()
   {
      
   }
   
   public DatabaseDescription(Database database)
   {
      this.database = database;
      databaseName = database.getName();
      authors = database.getAuthors();
      company = database.getCompany();
      llType = database.getLlType();
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

   public LLType getLlType()
   {
      return llType;
   }

   public void setLlType(LLType llType)
   {
      this.llType = llType;
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(authors, company, database, databaseName, llType);
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
            && Objects.equals(databaseName, other.databaseName)
            && llType == other.llType;
   }

   @Override
   public int compareTo(DatabaseDescription o)
   {
      if(this.getDatabaseName().equals(Database.SELF.getName()))
      {
         return 1;
      }
      if(o.getDatabaseName().equals(Database.SELF.getName()))
      {
         return -1;
      }
      return this.databaseName.compareTo(o.databaseName);
   }

}
