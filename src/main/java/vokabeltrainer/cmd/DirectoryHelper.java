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
