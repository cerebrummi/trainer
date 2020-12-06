package vokabeltrainer.types;

import vokabeltrainer.types.Chapter.Database;

public class DatabaseItem
{
   private Database database;
   private boolean selected;
   
   public DatabaseItem(Database database)
   {
      this.database = database;
   }

   public DatabaseItem(Database database, boolean selected)
   {
      this.database = database;
      this.selected = selected;
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

   public void setSelected(boolean selected)
   {
      this.selected = selected;
   }
   
}
