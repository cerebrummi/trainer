package vokabeltrainer.common;

import java.awt.Font;
import java.awt.Insets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vokabeltrainer.cmd.Mode;
import vokabeltrainer.panels.MainView;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public final class Common
{
   private static MainView mainJPanel;
   private static JFrame jFrame;

   private static boolean setMainJPanelOnlyOnce = true;
   private static boolean setJFrameOnlyOnce = true;
   private static boolean setModeOnlyOnce = true;
   
   private static Translator translator = new Translator();
   private static Settings settings;

   private static NimbusLookAndFeel nimbus;
   private static Mode mode;

   private Common()
   {
      // nothing
   }

   public static void setUI()
   {
      try
      {
         NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
         Common.setNimbus(nimbus);
         UIManager.setLookAndFeel(nimbus);

         nimbus.getDefaults().put("internationalFont",
               new Font(Font.SANS_SERIF, Font.PLAIN, 16));
         ApplicationFonts.setInternationalFont(
               nimbus.getDefaults().getFont("internationalFont"));

         UIManager.put("nimbusFocus", ApplicationColors.getSunflowerYellow());
         UIManager.put("nimbusBlueGrey", ApplicationColors.getLightGrayGold());
         UIManager.put("nimbusSelection", ApplicationColors.getGreen());

         UIManager.put("Button.foreground", ApplicationColors.getBlack());
         UIManager.put("List.foreground", ApplicationColors.getDarkGold());

         UIManager.put("ToolBar:Button.contentMargins",
               new Insets(5, 15, 5, 15));

         UIManager.put("ComboBox.forceOpaque", false);

         UIManager.put("TitledBorder.border", new Insets(10, 10, 10, 10));
         UIManager.put("TitledBorder.position", TitledBorder.ABOVE_BOTTOM);
         UIManager.put("TitledBorder.font",
               nimbus.getDefaults().getFont("internationalFont"));

         UIManager.put("Table.opaque", false);
         UIManager.put("List.opaque", false);
         UIManager.put("Table.cellRenderer", false);
         UIManager.put("OptionPane.buttonFont",
               nimbus.getDefaults().getFont("internationalFont"));

         UIManager.put("OptionPane.cancelButtonText",
               translator.realisticTranslate(Translation.ABBRECHEN));
         UIManager.put("OptionPane.yesButtonText",
               translator.realisticTranslate(Translation.JA));
         UIManager.put("OptionPane.noButtonText",
               translator.realisticTranslate(Translation.NEIN));
         UIManager.put("OptionPane.titleText",
               translator.realisticTranslate(Translation.BILD_LOESCHEN));

         UIManager.put("FileChooser.openButtonText",
               translator.realisticTranslate(Translation.OEFFNEN));
         UIManager.put("FileChooser.cancelButtonText",
               translator.realisticTranslate(Translation.ABBRECHEN));
         UIManager.put("FileChooser.saveButtonText",
               translator.realisticTranslate(Translation.SPEICHERN));
         UIManager.put("FileChooser.cancelButtonToolTipText", translator
               .realisticTranslate(Translation.ABBRECHEN_DER_AUSWAHL));
         UIManager.put("FileChooser.saveButtonToolTipText", translator
               .realisticTranslate(Translation.AUSGEWAEHLTE_DATEI_SPEICHERN));
         UIManager.put("FileChooser.openButtonToolTipText",
               "Ausgewählte Datei öffnen");
         UIManager.put("FileChooser.upFolderToolTipText", "Eine Ebene höher");
         UIManager.put("FileChooser.homeFolderToolTipText", "Home");
         UIManager.put("FileChooser.newFolderToolTipText",
               "Neuen Ordner erstellen");
         UIManager.put("FileChooser.listViewButtonToolTipText", "Liste");
         UIManager.put("FileChooser.detailsViewButtonToolTipText", "Details");
         UIManager.put("FileChooser.lookInLabelText", "Suchen in:");
         UIManager.put("FileChooser.fileNameLabelText", "Dateiname:");
         UIManager.put("FileChooser.filesOfTypeLabelText", "Dateityp:");
         UIManager.put("FileChooser.acceptAllFileFilterText",
               "Alle Dateien (*.*)");
         UIManager.put("FileChooser.folderNameLabelText", "Ordnername:");
         UIManager.put("FileChooser.openDialogTitleText",
               translator.realisticTranslate(Translation.OEFFNEN));
         UIManager.put("FileChooser.saveDialogTitleText",
               translator.realisticTranslate(Translation.SPEICHERN));

      }
      catch (UnsupportedLookAndFeelException e3)
      {
         // nothing
      }

   }

   static void setMode(Mode mode)
   {
      if(setModeOnlyOnce)
      {
         Common.mode = mode;
         setModeOnlyOnce = false;
      }
   }

   public static Mode getMode()
   {
      return mode;
   }

   public static MainView getMainJPanel()
   {
      return mainJPanel;
   }

   static void setMainJPanel(MainView mainJPanel)
   {
      if (setMainJPanelOnlyOnce)
      {
         Common.mainJPanel = mainJPanel;
         setMainJPanelOnlyOnce = false;
      }
   }

   public static JFrame getjFrame()
   {
      return jFrame;
   }

   static void setjFrame(JFrame jFrame)
   {
      if (setJFrameOnlyOnce)
      {
         Common.jFrame = jFrame;
         setJFrameOnlyOnce = false;
      }
   }

   public static Translator getTranslator()
   {
      return translator;
   }

   public static void setTranslator(Translator translator)
   {
      Common.translator = translator;
   }

   public static Settings getSettings()
   {
      return settings;
   }

   public static NimbusLookAndFeel getNimbus()
   {
      return nimbus;
   }

   public static void setNimbus(NimbusLookAndFeel nimbus)
   {
      Common.nimbus = nimbus;
   }

   public static boolean isSchabbat()
   {
      ZonedDateTime now = ZonedDateTime.now();
      DayOfWeek day = now.getDayOfWeek();
      int hour = now.getHour();
      if (day.equals(DayOfWeek.FRIDAY) && hour > 18)
      {
         return true;
      }
      else if (day.equals(DayOfWeek.SATURDAY) && hour < 18)
      {
         return true;
      }

      return false;
   }

   public static boolean isSchabbatPossible(LocalDate date)
   {
      DayOfWeek day = date.getDayOfWeek();
      if (day.equals(DayOfWeek.FRIDAY) || day.equals(DayOfWeek.SATURDAY))
      {
         return true;
      }

      return false;
   }
}
