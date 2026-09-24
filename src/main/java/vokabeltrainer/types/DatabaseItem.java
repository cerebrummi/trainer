package vokabeltrainer.types;

import java.util.ArrayList;
import java.util.List;

import vokabeltrainer.common.main.App;
import vokabeltrainer.types.Chapter.Database;

public class DatabaseItem
{
   private Database database;
   private boolean selected;
   private String databaseName = "";
   private String authors = "";
   private String company = "";

   public DatabaseItem(Database database)
   {
      this.database = database;
   }

   public DatabaseItem(Database database, boolean selected)
   {
      this.database = database;
      this.selected = selected;
   }

   public DatabaseItem(DatabaseDescription database)
   {
      this.database = database.getDatabase();
      this.databaseName = database.getDatabaseName();
      this.authors = database.getAuthors();
      this.company = database.getCompany();
   }

   public Database getDatabase()
   {
      return database;
   }

   public void setDatabase(Database database)
   {
      this.database = database;
   }

   public void toggleSelected()
   {
      this.selected = !this.selected;
   }

   public boolean isSelected()
   {
      return selected;
   }

   public String getDatabaseName()
   {
      return databaseName;
   }

   public String getAuthors()
   {
      return authors;
   }

   public String getCompany()
   {
      return company;
   }

   public static List<DatabaseItem> getAllAvailableDatabaseItems(App app)
   {
      List<DatabaseItem> databaseItemList = new ArrayList<>();

      for (Database database : app.settings.getAvailableDatabases())
      {
         databaseItemList.add(new DatabaseItem(database,
               app.settings.isDatabaseChoosen(database)));
      }

      return databaseItemList;
   }
}
