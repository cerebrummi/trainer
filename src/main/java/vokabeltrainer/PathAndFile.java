package vokabeltrainer;

import java.io.File;

public class PathAndFile
{

   String path;
   String file;

   public String getPath()
   {
      return path;
   }

   public void setPath(String path)
   {
      this.path = path;
   }

   public String getFile()
   {
      return file;
   }

   public void setFile(String file)
   {
      this.file = file;
   }

   public String getPathFile()
   {
      if (path.isBlank() && file.isBlank())
      {
         return "";
      }
      if (path.isBlank())
      {
         return file;
      }
      if (file.isBlank())
      {
         return path;
      }
      return path + File.separator + file;
   }

   public String getPathFileWithZipTest()
   {
      if (file.endsWith(".zip") || file.endsWith(".ZIP"))
      {
         return getPathFile();
      }
      return getPathFile() + ".zip";
   }

}
