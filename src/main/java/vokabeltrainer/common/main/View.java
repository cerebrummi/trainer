package vokabeltrainer.common.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vokabeltrainer.cmd.Mode;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.MainController;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.MainView;
import vokabeltrainer.panels.translation.Translation;

public final class View
{
   private JFrame jFrame;
   private MainView mainJPanel;
   private NimbusLookAndFeel nimbus;
   private Common common;
   
   // package on purpose
   View(Common common, Mode mode)
   {
      this.common = common;
      setUI();
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
      jFrame.setIconImage(ApplicationImages.getLogo());
      jFrame.getContentPane()
            .setBackground(ApplicationColors.getBackgroundGold());
      jFrame.setTitle(Settings.getWindowTitle() + " " + Settings.getVersion());
      jFrame.setFont(ApplicationFonts.germanFont.deriveFont(14F));
      ToolTipManager.sharedInstance().setDismissDelay(8000);
      ToolTipManager.sharedInstance().setInitialDelay(1000);
      mainJPanel = new MainController(common).getMainView();
      jFrame.getContentPane().add(new JScrollPane(mainJPanel));
      jFrame.setJMenuBar(mainJPanel.getMenuBar());
      jFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      jFrame.setLocationRelativeTo(null);
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
   
   public void setUI()
   {
      try
      {
         nimbus = new NimbusLookAndFeel();
         UIManager.setLookAndFeel(nimbus);

         nimbus.getDefaults().put("internationalFont",
               new Font(Font.SANS_SERIF, Font.PLAIN, 16));
         ApplicationFonts.internationalFont = nimbus.getDefaults()
               .getFont("internationalFont");

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
               common.getTranslator().realisticTranslate(Translation.ABBRECHEN));
         UIManager.put("OptionPane.yesButtonText",
               common.getTranslator().realisticTranslate(Translation.JA));
         UIManager.put("OptionPane.noButtonText",
               common.getTranslator().realisticTranslate(Translation.NEIN));
         UIManager.put("OptionPane.titleText",
               common.getTranslator().realisticTranslate(Translation.BILD_LOESCHEN));

         UIManager.put("FileChooser.openButtonText",
               common.getTranslator().realisticTranslate(Translation.OEFFNEN));
         UIManager.put("FileChooser.cancelButtonText",
               common.getTranslator().realisticTranslate(Translation.ABBRECHEN));
         UIManager.put("FileChooser.saveButtonText",
               common.getTranslator().realisticTranslate(Translation.SPEICHERN));
         UIManager.put("FileChooser.cancelButtonToolTipText", common.getTranslator()
               .realisticTranslate(Translation.ABBRECHEN_DER_AUSWAHL));
         UIManager.put("FileChooser.saveButtonToolTipText", common.getTranslator()
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
               common.getTranslator().realisticTranslate(Translation.OEFFNEN));
         UIManager.put("FileChooser.saveDialogTitleText",
               common.getTranslator().realisticTranslate(Translation.SPEICHERN));

      }
      catch (UnsupportedLookAndFeelException e3)
      {
         // nothing
      }

   }
}
