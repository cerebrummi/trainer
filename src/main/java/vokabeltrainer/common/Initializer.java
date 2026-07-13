package vokabeltrainer.common;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JFrame;
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

public class Initializer
{
   Initializer()
   {
      Common.setMode(Mode.LOCAL_ORIGINAL);
      
      CerebrummiPreferences.read();

      Common.setUI();

      try
      {
         Fonts.read();
      }
      catch (FontFormatException | IOException e)
      {
         System.exit(1);
      }

      Fonts.define();

      try
      {
         Images.read();
         LetterIcons.readNikud();
         LetterIconsHandwritten.readNikud();
         Buchstabenbilder.read();
      }
      catch (IOException e1)
      {
         System.exit(1);
      }

      Sounds.read();

      Data.initDataBase();
      ImageData.initImageDataBase();
      SoundData.initSoundDataBase();
   }
   
   void programStart()
   {
      SwingUtilities.invokeLater(() -> {
         final JFrame window = new JFrame();
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
         Common.getjFrame().getContentPane()
               .setBackground(ApplicationColors.getBackgroundGold());
         Common.getjFrame().getContentPane().validate();
         Common.getjFrame().getContentPane().repaint();
         window.setTitle(
               Settings.getWindowTitle() + " " + Settings.getVersion());
         window.setFont(ApplicationFonts.germanFont.deriveFont(14F));
         ToolTipManager.sharedInstance().setDismissDelay(8000);
         ToolTipManager.sharedInstance().setInitialDelay(1000);
         Common.setMainJPanel(
               new vokabeltrainer.common.MainController(this).getMainView());
         window.getContentPane().add(new JScrollPane(Common.getMainJPanel()));
         window.setJMenuBar(Common.getMainJPanel().getMenuBar());
         window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
         window.setLocationRelativeTo(null);
         window.setVisible(true);
      });
   }
   
   void preloadAfterProgramStart()
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground()
         {
            try
            {
               Blue.read();
               Gruen.read();
            }
            catch (IOException e)
            {
               System.exit(1);
            }
            return null;
         }

      }.execute();
   }
   
   public void initDatabase()
   {
      Data.initDataBase();
   }
   
   public void resetMenuBar()
   {
      SwingUtilities.invokeLater(() -> {
         vokabeltrainer.common.Common.getjFrame()
               .setJMenuBar(Common.getMainJPanel().getMenuBar());
         vokabeltrainer.common.Common.getjFrame().validate();
         vokabeltrainer.common.Common.getjFrame().repaint();
      });
   }
}
