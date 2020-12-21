package vokabeltrainer.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.CerebrummiPreferences;
import vokabeltrainer.Settings;
import vokabeltrainer.resources.Blue;
import vokabeltrainer.resources.Buchstabenbilder;
import vokabeltrainer.resources.Fonts;
import vokabeltrainer.resources.Gruen;
import vokabeltrainer.resources.Images;
import vokabeltrainer.resources.LetterIcons;
import vokabeltrainer.resources.Sounds;

public final class Main
{
   private static Font germanFont;
   private static Font germanBoldFont;
   private static Font hebrewFont;
   private static String message = "Cerebrummi© bitte neu starten.\nFehler: ";

   public static void main(String[] args)
   {
      LocalDate thisPointInTime = LocalDate.now();
      LocalDate endOfTest = LocalDate.of(2022, 1, 1);

      if (endOfTest.isBefore(thisPointInTime))
      {
         JOptionPane.showMessageDialog(null,
               "Die Testzeit für diese kostenloste Testversion\nvon Cerebrummi© Ivrit 0.9 ist abgelaufen.",
               "Nachricht", JOptionPane.CLOSED_OPTION);
         System.exit(0);
      }

      try
      {
         Fonts.read();
      }
      catch (Exception e1)
      {
         JOptionPane.showMessageDialog(null, message + "Schriftarten fehlen",
               "Nachricht", JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      try
      {
         Images.read();
      }
      catch (Exception e1)
      {
         JOptionPane.showMessageDialog(null, message + "Bilder fehlen",
               "Nachricht", JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      try
      {
         Buchstabenbilder.read();
      }
      catch (Exception e)
      {
         JOptionPane.showMessageDialog(null,
               message + "Buchstabenbilder fehlen", "Nachricht",
               JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      try
      {
         LetterIcons.read();
      }
      catch (Exception e1)
      {
         JOptionPane.showMessageDialog(null,
               message + "Buchstaben Icons fehlen", "Nachricht",
               JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      try
      {
         Sounds.read();
      }
      catch (Exception e1)
      {
         JOptionPane.showMessageDialog(null, message + "Geräusche fehlen",
               "Nachricht", JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      CerebrummiPreferences.read();

      Data.initDataBase();

      try
      {
         NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
         UIManager.setLookAndFeel(nimbus);
         UIManager.put("control", Settings.getTexturedBackgroundColor());
         UIManager.put("nimbusBlueGrey", Settings.getLightGrayGold());
         UIManager.put("nimbusBase", Settings.getDarkGold());
         UIManager.put("textForeground", Color.BLACK);
         UIManager.put("nimbusFocus", new Color(255, 220, 35));
         UIManager.put("ToolBar:Button.contentMargins",
               new Insets(5, 15, 5, 15));
         UIManager.put("TextField.background", Settings.getLightYellow());
         UIManager.put("ComboBox.forceOpaque", false);
         UIManager.put("TitledBorder.border", new Insets(10, 10, 10, 10));
         UIManager.put("TitledBorder.position", TitledBorder.ABOVE_BOTTOM);
         UIManager.put("TitledBorder.font", getGermanFont(16F));
         UIManager.put("TitledBorder.titleColor", Color.GRAY);
         UIManager.put("Table.opaque", false);
         UIManager.put("List.opaque", false);
         UIManager.put("Table.cellRenderer", false);
         UIManager.put("OptionPane.buttonFont", Main.getGermanFont(16F));

      }
      catch (UnsupportedLookAndFeelException e3)
      {
         e3.printStackTrace();
      }

      SwingUtilities.invokeLater(() -> {
         javax.swing.JFrame window = new JFrame();
         window.setResizable(true);
         window.setIconImage(ApplicationImages.getLogo());
         vokabeltrainer.common.Common.setjFrame(window);
         window.setTitle(
               "Cerebrummi©/Ivrit Version 0.9 Dies ist eine kostenlose Testversion, die bis zum 31.12.2021 funktioniert.");
         window.setFont(germanFont.deriveFont(14F));
         ToolTipManager.sharedInstance().setDismissDelay(8000);
         ToolTipManager.sharedInstance().setInitialDelay(1000);
         vokabeltrainer.common.Common.setMainJPanel(
               new vokabeltrainer.common.MainController().getMainView());
         window.getContentPane()
               .add(new JScrollPane(vokabeltrainer.common.Common.getMainJPanel()));
         window.setJMenuBar(Common.getMainJPanel().getMenuBar());
         window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
         window.setMinimumSize(new Dimension(1600,950));
         window.setExtendedState(JFrame. MAXIMIZED_BOTH);
         window.setLocationRelativeTo(null);
         window.setVisible(true);
      });

      new SwingWorker<Void, Void>()
      {

         @Override
         protected Void doInBackground() throws Exception
         {
            try
            {
               Blue.read();
            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(null,
                     message + "Blaue Bilder fehlen", "Nachricht",
                     JOptionPane.CLOSED_OPTION);
               System.exit(1);
            }

            try
            {
               Gruen.read();
            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(null,
                     message + "Grüne Bilder fehlen", "Nachricht",
                     JOptionPane.CLOSED_OPTION);
               System.exit(1);
            }
            return null;
         }

      }.execute();
   }

   public static Font getGermanFont(float size)
   {
      return germanFont.deriveFont(size);
   }

   public static Font getHeaderFont(float size)
   {
      return germanFont.deriveFont(size);
   }

   public static Font getGermanBoldFont(float size)
   {
      return germanBoldFont.deriveFont(size);
   }

   public static Font getHebrewFont(float size)
   {
      return hebrewFont.deriveFont(size);
   }

   public static void setGermanFont(Font germanFont)
   {
      Main.germanFont = germanFont;
   }

   public static void setGermanBoldFont(Font germanBoldFont)
   {
      Main.germanBoldFont = germanBoldFont;
   }

   public static void setHebrewFont(Font hebrewFont)
   {
      Main.hebrewFont = hebrewFont;
   }

   public static void initDatabase()
   {
      Data.initDataBase();
   }
}
