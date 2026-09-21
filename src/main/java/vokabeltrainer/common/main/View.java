package vokabeltrainer.common.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vokabeltrainer.cmd.Mode;
import vokabeltrainer.common.MainController;
import vokabeltrainer.panels.MainView;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.resources.Buchstabenbilder;
import vokabeltrainer.resources.LetterIcons;
import vokabeltrainer.resources.LetterIconsHandwritten;

public final class View
{
   private JFrame jFrame;
   private MainView mainJPanel;
   private NimbusLookAndFeel nimbus;

   // package on purpose
   View(App app, Common common, Mode mode)
   {
      setUI(app, common);

      jFrame = new JFrame();

      if (mode.isWeb())
      {
         jFrame.setUndecorated(true);
         jFrame.setSize(new Dimension(1536, 900));
      }
      else
      {
         jFrame.setSize(new Dimension(1536, 980));
      }
      jFrame.setResizable(true);
      jFrame.setIconImage(app.appImages.getLogo());
      jFrame.getContentPane().setBackground(app.appColors.getBackgroundGold());
      jFrame.setTitle(
            app.settings.getWindowTitle() + " " + app.settings.getVersion());
      jFrame.setFont(app.appFonts.germanFont.deriveFont(14F));
      ToolTipManager.sharedInstance().setDismissDelay(8000);
      ToolTipManager.sharedInstance().setInitialDelay(1000);
      mainJPanel = new MainController(app, common).getMainView();
      jFrame.getContentPane().add(new JScrollPane(mainJPanel));
      jFrame.setJMenuBar(mainJPanel.getMenuBar());
      jFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      jFrame.setLocationRelativeTo(null);
   }

   public void makeBuchstabenbilder(App app, Common common)
   {
      try
      {
         LetterIcons.readNikud(app);
         LetterIconsHandwritten.readNikud(app);
         Buchstabenbilder.read(app, common);
      }
      catch (IOException e1)
      {
         System.exit(10);
      }
   }

   public void startApp()
   {
      jFrame.setVisible(true);
   }

   public JFrame getjFrame()
   {
      return jFrame;
   }

   public MainView getMainJPanel()
   {
      return mainJPanel;
   }

   public NimbusLookAndFeel getNimbus()
   {
      return nimbus;
   }

   public void setUI(App app, Common common)
   {
      app.readColorModes();
      try
      {
         nimbus = new NimbusLookAndFeel();
         UIManager.setLookAndFeel(nimbus);

         nimbus.getDefaults().put("internationalFont",
               new Font(Font.SANS_SERIF, Font.PLAIN, 16));
         app.appFonts.internationalFont = nimbus.getDefaults()
               .getFont("internationalFont");

         UIManager.put("nimbusFocus", app.appColors.getSunflowerYellow());
         UIManager.put("nimbusBlueGrey", app.appColors.getBoxForeground());
         UIManager.put("nimbusSelection", app.appColors.getWasteBinBorder());

         UIManager.put("Button.foreground", app.appColors.getBlack());
         UIManager.put("List.foreground", app.appColors.getDarkGold());

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

         UIManager.put("OptionPane.cancelButtonText", common.getTranslator()
               .realisticTranslate(app, Translation.ABBRECHEN));
         UIManager.put("OptionPane.yesButtonText",
               common.getTranslator().realisticTranslate(app, Translation.JA));
         UIManager.put("OptionPane.noButtonText",
               common.getTranslator().realisticTranslate(app, Translation.NEIN));
         UIManager.put("OptionPane.titleText", common.getTranslator()
               .realisticTranslate(app, Translation.BILD_LOESCHEN));

         UIManager.put("FileChooser.openButtonText",
               common.getTranslator().realisticTranslate(app, Translation.OEFFNEN));
         UIManager.put("FileChooser.cancelButtonText", common.getTranslator()
               .realisticTranslate(app, Translation.ABBRECHEN));
         UIManager.put("FileChooser.saveButtonText", common.getTranslator()
               .realisticTranslate(app, Translation.SPEICHERN));
         UIManager.put("FileChooser.cancelButtonToolTipText",
               common.getTranslator()
                     .realisticTranslate(app, Translation.ABBRECHEN_DER_AUSWAHL));
         UIManager.put("FileChooser.saveButtonToolTipText",
               common.getTranslator().realisticTranslate(app, 
                     Translation.AUSGEWAEHLTE_DATEI_SPEICHERN));
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
               common.getTranslator().realisticTranslate(app, Translation.OEFFNEN));
         UIManager.put("FileChooser.saveDialogTitleText", common.getTranslator()
               .realisticTranslate(app, Translation.SPEICHERN));

      }
      catch (UnsupportedLookAndFeelException e3)
      {
         // nothing
      }

      makeBuchstabenbilder(app, common);

   }
}
