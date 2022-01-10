package vokabeltrainer.panels.translation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.panels.notifications.OkayExpressionsSavedNotification;

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
                  "Fehler beim Speichern der Übersetzung." + "\n"
                        + "Der Ordner kann nicht angelegt werden.",
                  "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
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
               "Fehler beim Speichern der Übersetzung." + "\n"
                     + "Der Sprache kann nicht angelegt werden.",
               "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
      }
   }

   private void save(List<TranslationField> fields) throws IOException
   {
      TranslationCode code = fields.get(0).getCode();
      String fileName = code.name();

      if (TranslationCode.ANY_ltr_ == code || TranslationCode.ANY_rtl_ == code)
      {
         fileName += fields.get(0).getUuid();
      }

      File file = new File(
            Settings.getTranslationPath() + File.separator + fileName + ".txt");
      FileOutputStream stream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(stream,
            StandardCharsets.UTF_8);
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add("name\t" + fields.get(0).getName());
      for (TranslationField field : fields)
      {
         String text = field.getText().replaceAll("\t", "");
         joiner.add(field.getTranslation().name() + "\t" + text);
      }
      writer.write(joiner.toString());
      writer.flush();
      writer.close();

   }

   public TranslationLanguage[] findTranslationLanguagesANY(
         TranslationCode code)
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
            TranslationLanguage[] result = { new TranslationLanguage() };
            return result;
         }
      }

      List<TranslationLanguage> resultList = new ArrayList<>();
      resultList.add(new TranslationLanguage());
      for (File file : customDir.listFiles())
      {
         String name = file.getName();
         if (name.startsWith(code.name()))
         {
            name = name.substring(code.name().length(), name.length() - 4);
            TranslationLanguage translationLanguage = new TranslationLanguage();
            translationLanguage.setUuid(UUID.fromString(name));

            try (FileInputStream fis = new FileInputStream(file);
                  InputStreamReader isr = new InputStreamReader(fis,
                        StandardCharsets.UTF_8);
                  Reader reader = new BufferedReader(isr);)
            {
               StringBuffer buffer = new StringBuffer();
               String input;
               int ch;
               while ((ch = reader.read()) > -1)
               {
                  buffer.append((char) ch);
               }
               reader.close();
               input = buffer.toString().trim();
               if (input.isEmpty())
               {
                  continue;
               }
               translationLanguage.setText(input.split("\n")[0].split("\t")[1]);
               resultList.add(translationLanguage);
            }
            catch (IOException e)
            {

            }
         }
      }
      return resultList.stream().toArray(TranslationLanguage[]::new);
   }

   public void loadAvailableTranslations()
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
            // nothing
         }
         return;
      }

      Set<String> allCodes = Arrays.stream(TranslationCode.values())
            .map(code -> code.name()).collect(Collectors.toSet());

      for (File file : customDir.listFiles())
      {
         String name = file.getName();
         name = name.substring(0, name.length() - 4);

         if (allCodes.contains(name))
         {
            TranslationCode.valueOf(name).setAvailable(true);
         }
         else if (name.startsWith(TranslationCode.ANY_ltr_.name()))
         {
            TranslationCode.ANY_ltr_.setAvailable(true);
         }
         else if (name.startsWith(TranslationCode.ANY_rtl_.name()))
         {
            TranslationCode.ANY_rtl_.setAvailable(true);
         }
      }
   }

   public Map<Translation, String> findTranslationMap(TranslationCode code,
         UUID uuid)
   {
      Map<Translation, String> translationMap = new HashMap<>();

      if (!code.isAvailable())
      {
         return translationMap;
      }

      File customDir = new File(Settings.getTranslationPath());

      if (!customDir.exists())
      {
         try
         {
            customDir.mkdirs();
         }
         catch (Exception e)
         {
            // nothing
         }
         return translationMap;
      }

      File file;

      if (uuid != null)
      {
         file = new File(code.name() + uuid + ".txt");
      }
      else
      {
         file = new File(code.name() + ".txt");
      }

      try (FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis,
                  StandardCharsets.UTF_8);
            Reader reader = new BufferedReader(isr);)
      {
         StringBuffer buffer = new StringBuffer();
         String input;
         int ch;
         while ((ch = reader.read()) > -1)
         {
            buffer.append((char) ch);
         }
         reader.close();
         input = buffer.toString().trim();
         if (input.isEmpty())
         {
            return translationMap;
         }

         String[] rows = input.split("\n");

         for (int i = 1; i < rows.length; i++)
         {
            try
            {
               String[] cols = rows[i].split("\t");
               translationMap.put(Translation.valueOf(cols[0]), cols[1]);
            }
            catch (Exception e)
            {

            }
         }
      }
      catch (IOException e)
      {
         // nothing
      }

      return translationMap;
   }
}
