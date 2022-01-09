package vokabeltrainer.panels.translation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.JOptionPane;

import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.panels.notifications.OkayExpressionsSavedNotification;
import vokabeltrainer.types.TranslationCode;

public class TranslationController
{

   public void saveTranslations(List<TranslationField> fields)
   {
      
      File customDir = new File(Settings.getTranslationPath());
      if (!customDir.exists())
      {
         try
         {
            customDir.mkdirs();
         }
         catch (Exception e)
         {
            JOptionPane.showMessageDialog(Common.getjFrame(),
                  "Fehler beim Speichern der Übersetzung."
                  + "\n"
                  + "Der Ordner kann nicht angelegt werden.", "Fehlermeldung",
                  JOptionPane.ERROR_MESSAGE);
            return;
         }
      }
      
      try
      {
         save(fields);
         OkayExpressionsSavedNotification.display();
      }
      catch (IOException e)
      {
         JOptionPane.showMessageDialog(Common.getjFrame(),
               "Fehler beim Speichern der Übersetzung."
               + "\n"
               + "Der Sprache kann nicht angelegt werden.", "Fehlermeldung",
               JOptionPane.ERROR_MESSAGE);
      }
   }

   
   private void save(List<TranslationField> fields) throws IOException
   {
      TranslationCode code = fields.get(0).getCode();
      String fileName = code.name();
      
      if(TranslationCode.ANY_ltr_ == code || TranslationCode.ANY_rtl_ == code)
      {
         fileName += fields.get(0).getUuid();
      }
      
      File file = new File(Settings.getTranslationPath() + File.separator
            + fileName + ".txt");
      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add("name\t"+fields.get(0).getName());
      for (TranslationField field : fields)
      {
         String text = field.getText().replaceAll("\t", "");
         joiner.add(field.getTranslation().name()+"\t"+text);
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();

   }
}
