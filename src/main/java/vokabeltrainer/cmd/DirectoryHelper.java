package vokabeltrainer.cmd;

import java.io.File;
import javax.swing.JOptionPane;

import vokabeltrainer.common.Common;

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
               "Fehler beim Speichern.", "Fehlermeldung: \n" + e,
               JOptionPane.ERROR_MESSAGE);
         return false;
      }
      return true;
   }
}
