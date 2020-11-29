package vokabeltrainer.cmd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.StringJoiner;

import javax.swing.JOptionPane;

import vokabeltrainer.common.Common;
import vokabeltrainer.resources.Images;

public class DirectoryHelper
{

   private DirectoryHelper()
   {
      
   }

   
   public static boolean makeExpressionDirectory(File customDir)
   {
      try
      {
         customDir.mkdirs();
         try
         {
            File original = new File(Images.class
                  .getResource("_2_images/CerebrummiFolder.ico")
                  .getPath());
            File destination = new File(customDir.getPath()
                  + File.separator + "CerebrummiFolder.ico");
            Files.copy(original.toPath(), destination.toPath(),
                  StandardCopyOption.REPLACE_EXISTING);

            File inifile = new File(customDir.getPath()
                  + File.separator + "desktop.ini");
            FileOutputStream stream = new FileOutputStream(inifile);
            OutputStreamWriter writer = new OutputStreamWriter(stream,
                  StandardCharsets.UTF_8);
            StringJoiner joiner = new StringJoiner("\n");
            joiner.add("[.ShellClassInfo]");
            joiner.add(
                  "IconResource=..\\cerebrummi.hebrewtrainer\\CerebrummiFolder.ico,0");
            joiner.add("[ViewState]");
            joiner.add("Mode=");
            joiner.add("Vid=");
            joiner.add("FolderType=Generic");
            joiner.add("");
            writer.write(joiner.toString());
            writer.flush();
            writer.close();

            File desktopINI = new File(customDir.getPath()
                  + File.separator + "desktop.ini");

            Runtime.getRuntime()
                  .exec("attrib -H " + desktopINI.getAbsolutePath());
            Runtime.getRuntime()
                  .exec("attrib +r " + customDir.getAbsolutePath());
            Runtime.getRuntime()
                  .exec("attrib +H " + desktopINI.getAbsolutePath());
            Runtime.getRuntime()
                  .exec("attrib +H " + destination.getAbsolutePath());
         }
         catch (Exception e)
         {
            // nothing
         }
      }
      catch (Exception e)
      {
         JOptionPane.showMessageDialog(Common.getjFrame(),
               "Fehler beim Speichern.", "Fehlermeldung",
               JOptionPane.ERROR_MESSAGE);
         return false;
      }
      return true;
   }
}
