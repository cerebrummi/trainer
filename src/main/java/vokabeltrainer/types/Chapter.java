package vokabeltrainer.types;

import java.text.Collator;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;

import vokabeltrainer.common.main.Common;
import vokabeltrainer.panels.start.table.multiselect.DatabaseTableModel;
import vokabeltrainer.panels.start.table.multiselect.DatabaseTableRow;
import vokabeltrainer.panels.start.table.singleselect.DatabaseTableCopyModel;
import vokabeltrainer.panels.start.table.singleselect.DatabaseTableCopyRow;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class Chapter implements Comparable<Chapter>
{
   private String name = "";
   private DatabaseDescription databaseDescription = new DatabaseDescription();
   private Common common;

   public Chapter(Common common)
   {
      this(common, "", "", Database.TO_BE_DETERMINED);
   }

   public Chapter(Common common, Database origin)
   {
      this(common, "", "", origin);
   }

   public Chapter(Common common, String name, Database origin)
   {
      this(common, "", name, origin);
   }

   public Chapter(Common common, String databaseName, String name,
         Database origin)
   {
      this.common = common;
      databaseDescription.setDatabaseName(databaseName.strip());
      this.name = name;
      databaseDescription.setDatabase(origin);
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
      return databaseDescription.getDatabase();
   }

   public void setOrigin(Database origin)
   {
      databaseDescription.setDatabase(origin);
   }

   public DatabaseDescription getDatabaseDescription()
   {
      return databaseDescription;
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
      return coll.compare(this.getDatabaseName(common) + this.name,
            o.getDatabaseName(common) + o.name);
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(databaseDescription.getDatabaseName(), name);
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
      return Objects.equals(databaseDescription.getDatabaseName(),
            other.databaseDescription.getDatabaseName())
            && Objects.equals(name, other.name);
   }

   public String getDatabaseFolder(Database database)
   {
      return database.getFolder();
   }

   public String getDatabaseName(Common common)
   {
      if (Database.IMPORTED == databaseDescription.getDatabase()
            || Database.SELF == databaseDescription.getDatabase()
            || Database.UNKNOWN == databaseDescription.getDatabase())
      {
         return databaseDescription.getDatabaseName();
      }
      return databaseDescription.getDatabase().getName(common);
   }

   public void setDatabaseName(String databaseName)
   {
      if (Database.IMPORTED == databaseDescription.getDatabase()
            || Database.SELF == databaseDescription.getDatabase()
            || Database.UNKNOWN == databaseDescription.getDatabase())
      {
         this.databaseDescription.setDatabaseName(databaseName);
      }
   }

   public enum Database
   {
      GRUNDWORTSCHATZ("grundwortschatz", "Grundwortschatz", "630 Vokabeln",
            "Neuhebräisch", LLType.HEBREW,
            false), 
      ROSENGARTENLOOS("rosengartenloos", "Rosengarten & Loos",
                  "IVRIT Schritt für Schritt: Die ersten 12 Kapitel.",
                  "COPYRIGHT S. Marix Verlag: Es ist nicht gestattet Texte zu speichern.",
                  LLType.HEBREW,
                  true), 
      SELF("", "", "", "", LLType.UNKOWN, false), COPY("",
                        "Kopie", "", "", LLType.UNKOWN, false), IMPORTED("",
                              "importiert", "", "", LLType.UNKOWN,
                              false), 
      UNKNOWN("", "unbekannt", "", "",
                                    LLType.UNKOWN, false), 
      TO_BE_DETERMINED("",
                                          "soll bestimmt werden", "", "",
                                          LLType.UNKOWN, false);

      public void setLlType(LLType llType)
      {
         this.llType = llType;
      }

      private String folder;
      private String name;
      private String authors;
      private String company;
      private LLType llType;
      private boolean copyrighted;

      Database(String folder, String name, String authors, String company,
            LLType llType, boolean copyrighted)
      {
         this.folder = folder;
         this.name = name;
         this.authors = authors;
         this.company = company;
         this.llType = llType;
         this.copyrighted = copyrighted;
      }

      public String getFolder()
      {
         return folder;
      }

      public String getName(Common common)
      {
         if (this == Database.SELF)
         {
            Translator translator = common.getTranslator();
            return translator.realisticTranslate(Translation.SELBST_EINGEGEBEN);
         }
         return name;
      }

      public boolean isCopyrighted()
      {
         return copyrighted;
      }

      public static DatabaseTableModel getModelAvailableDatabases()
      {
         Vector<Vector<DatabaseTableRow>> data = new Vector<>();
         for (DatabaseItem item : DatabaseItem.getAllAvailableDatabaseItems())
         {
            Vector<DatabaseTableRow> row = new Vector<>();
            row.add(new DatabaseTableRow(item));
            data.add(row);
         }
         Vector<String> columnNames = new Vector<>();
         columnNames.add("eins");
         return new DatabaseTableModel(data, columnNames);
      }

      public static DatabaseTableCopyModel getModelCopyAvailableDatabases()
      {
         Vector<Vector<DatabaseTableCopyRow>> data = new Vector<>();
         for (DatabaseItem item : DatabaseItem.getAllAvailableDatabaseItems())
         {
            if (item.getDatabase().isCopyrighted())
            {
               continue;
            }
            Vector<DatabaseTableCopyRow> row = new Vector<>();
            row.add(new DatabaseTableCopyRow(item));
            data.add(row);
         }
         Vector<String> columnNames = new Vector<>();
         columnNames.add("eins");
         return new DatabaseTableCopyModel(data, columnNames);
      }

      public String getAuthors()
      {
         return authors;
      }

      public String getCompany()
      {
         return company;
      }

      public LLType getLlType()
      {
         return llType;
      }

   }

   public static Database findOrigin(Common common, String databaseName)
   {
      if (Database.GRUNDWORTSCHATZ.getName(common).equals(databaseName))
      {
         return Database.GRUNDWORTSCHATZ;
      }
      return Database.SELF;
   }
}
