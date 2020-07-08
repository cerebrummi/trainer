package vokabeltrainer.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Resources;
import vokabeltrainer.Settings;

public final class Main
{
   private static Font germanFont;
   private static Font germanBoldFont;
   private static Font hebrewFont;

   public static void main(String[] args)
   {
      try
      {
         Resources.read();
      }
      catch (Exception e1)
      {
         System.out.println("Could not read resources.");
         e1.printStackTrace();
      }

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
         window.setResizable(false);
         window.setIconImage(ApplicationImages.getLogo());
         vokabeltrainer.common.Common.setjFrame(window);
         window.setTitle("Cerebrummi©");
         window.setFont(germanFont.deriveFont(14F));
         ToolTipManager.sharedInstance().setDismissDelay(8000);
         ToolTipManager.sharedInstance().setInitialDelay(1000);
         vokabeltrainer.common.Common.setMainJPanel(
               new vokabeltrainer.common.MainController().getMainView());
         window.getContentPane()
               .add(vokabeltrainer.common.Common.getMainJPanel());
         window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
         window.setSize(1300, 735);
         window.setLocationRelativeTo(null);
         window.setVisible(true);
      });
   }

   /*
    * ClappingSound: Attribution License: Attribution — You must give
    * appropriate credit, provide a link to the license, and indicate if changes
    * were made. You may do so in any reasonable manner, but not in any way that
    * suggests the licensor endorses you or your use.
    * https://freesound.org/people/ebcrosby/sounds/339483/
    */

   /*
    * WaveSound: Creative Commons 0 License.: No Copyright The person who
    * associated a work with this deed has dedicated the work to the public
    * domain by waiving all of his or her rights to the work worldwide under
    * copyright law, including all related and neighboring rights, to the extent
    * allowed by law. You can copy, modify, distribute and perform the work,
    * even for commercial purposes, all without asking permission.
    * https://freesound.org/people/florianreichelt/sounds/450755/
    */

   public static Font getGermanFont(float size)
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

   public static Font getHeaderFont(float size)
   {
      return germanFont.deriveFont(size);
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
}
