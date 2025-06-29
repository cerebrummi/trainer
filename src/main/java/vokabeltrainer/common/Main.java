package vokabeltrainer.common;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.ToolTipManager;
import vokabeltrainer.cmd.Mode;
import vokabeltrainer.resources.Blue;
import vokabeltrainer.resources.Buchstabenbilder;
import vokabeltrainer.resources.Fonts;
import vokabeltrainer.resources.Gruen;
import vokabeltrainer.resources.Images;
import vokabeltrainer.resources.LetterIcons;
import vokabeltrainer.resources.LetterIconsHandwritten;
import vokabeltrainer.resources.Sounds;

public final class Main
{
   static
   {
      Common.setMode(Mode.LOCAL_ORIGINAL);
   }

   private static String message = Settings.getWindowTitle()
         + " bitte neu starten.\nFehler: ";

   public static void main(String[] args)
   {
      CerebrummiPreferences.read();

      Common.setUI();

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
         Fonts.define();
      }
      catch (Exception e1)
      {
         JOptionPane.showMessageDialog(null,
               message + "Schriftarten können nicht definiert werden",
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
         LetterIcons.readNikud();
      }
      catch (Exception e1)
      {
         JOptionPane.showMessageDialog(null,
               message + "Buchstaben Nikud Icons fehlen", "Nachricht",
               JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      try
      {
         LetterIconsHandwritten.readNikud();
      }
      catch (Exception e1)
      {
         JOptionPane.showMessageDialog(null,
               message + "Buchstaben Nikud Handwritten Icons fehlen",
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
         e.printStackTrace();
         System.exit(1);
      }

      try
      {
         Sounds.read();
      }
      catch (Exception e1)
      {
         e1.printStackTrace();
         JOptionPane.showMessageDialog(null, message + "Geräusche fehlen",
               "Nachricht", JOptionPane.CLOSED_OPTION);
         System.exit(1);
      }

      Data.initDataBase();
      ImageData.initImageDataBase();

      SwingUtilities.invokeLater(() -> {
         System.setProperty("java.awt.headless", "true");
         JFrame window = new JFrame();
         if (Common.getMode().isWeb())
         {
            window.setUndecorated(true);
            window.setSize(new Dimension(1536, 900));
         }
         else
         {
            window.setSize(new Dimension(1536, 980));
         }
         window.setResizable(true);
         window.setIconImage(ApplicationImages.getLogo());
         Common.setjFrame(window);
         Common.getjFrame().getContentPane().setBackground(ApplicationColors.getBackgroundGold());
         Common.getjFrame().getContentPane().validate();
         Common.getjFrame().getContentPane().repaint();
         window.setTitle(
               Settings.getWindowTitle() + " " + Settings.getVersion());
         window.setFont(ApplicationFonts.getGermanFont(14F));
         ToolTipManager.sharedInstance().setDismissDelay(8000);
         ToolTipManager.sharedInstance().setInitialDelay(1000);
         Common.setMainJPanel(
               new vokabeltrainer.common.MainController().getMainView());
         window.getContentPane().add(
               new JScrollPane(Common.getMainJPanel()));
         window.setJMenuBar(Common.getMainJPanel().getMenuBar());
         window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
         // window.setExtendedState(JFrame. MAXIMIZED_BOTH);
         // window.pack();
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

   public static void initDatabase()
   {
      Data.initDataBase();
   }

   public static void resetMenuBar()
   {
      SwingUtilities.invokeLater(() -> {
         vokabeltrainer.common.Common.getjFrame()
               .setJMenuBar(Common.getMainJPanel().getMenuBar());
         vokabeltrainer.common.Common.getjFrame().validate();
         vokabeltrainer.common.Common.getjFrame().repaint();
      });
   }
}
