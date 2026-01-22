package vokabeltrainer.panels.list.table;

import vokabeltrainer.types.DatabaseDescription;

public class DatabaseTableRow
{
   private DatabaseDescription databaseDescription;

   public DatabaseTableRow(DatabaseDescription databaseDescription)
   {
      this.databaseDescription = databaseDescription;
   }

   public DatabaseDescription getDescription()
   {
      return databaseDescription;
   }

}
