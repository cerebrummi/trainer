package vokabeltrainer.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.event.HyperlinkEvent.EventType;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.ApplicationSound;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.ImportExpressions;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.common.SaveTraining;
import vokabeltrainer.panels.settings.InputDatabaseNameDialog;
import vokabeltrainer.panels.settings.table.multiselect.DatabaseTable;
import vokabeltrainer.resources.html.Nachweise;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Chapter.Database;

public class SettingsPanel extends BackgroundPanelTiled
{
   private static final int WIDTH = 330;
   private static final long serialVersionUID = 1162776912036921483L;
   private JSlider soundslider;
   private JButton soundButton;
   private JButton waveSoundButton;
   private JButton clappingSoundButton;
   private JButton splotchSoundButton;
   private JButton shredderSoundButton;
   private JFileChooser folderChooser;
   private JButton folderChooserButton;
   private JButton folderChooserButtonWithoutSaving;
   private JTextArea folderLabel;
   private JButton importButton;
   private JButton exportButton;
   private JButton exportSelectedButton;
   private JButton exportDatabaseButton;

   public SettingsPanel()
   {
      setLayout(new BullsEyeLayout(this));

      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(false);
      tabbedPane.setFont(Main.getGermanFont(16F));
      tabbedPane.addTab("Einstellungen und Service", initSettingsTab());
      try
      {
         tabbedPane.addTab("Impressum und Haftungsausschluss",
               initImpressumTab());
      }
      catch (IOException e1)
      {
         // nothing
      }
      try
      {
         tabbedPane.addTab("Nachweise", initLicencingTab());
      }
      catch (IOException e)
      {
         // nothing
      }
      tabbedPane.addTab("Verfügbare Datenbanken", initDatabaseTab());

      add(tabbedPane);

      initController();
   }

   private Component initLicencingTab() throws IOException
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JEditorPane editorPane = new JEditorPane();
      editorPane.setFont(Settings.getButtonFont());
      editorPane.setContentType("text/html");
      editorPane.setPage(Nachweise.class.getResource("Nachweise"));
      editorPane.setEditable(false);
      editorPane.addHyperlinkListener(event -> {
         if (event.getEventType() == EventType.ACTIVATED
               && event.getInputEvent() instanceof MouseEvent)
         {
            MouseEvent mouseEvent = (MouseEvent) event.getInputEvent();
            if (mouseEvent.getClickCount() == 1)
            {
               if (!Desktop.isDesktopSupported())
               {
                  return;
               }
               try
               {
                  Desktop.getDesktop().browse(event.getURL().toURI());
               }
               catch (Exception e)
               {
                  // nothing
               }
            }
         }

      });

      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JScrollPane scroller = new JScrollPane(editorPane);
      scroller.setMinimumSize(new Dimension(500, 300));
      scroller.setMaximumSize(new Dimension(1000, 500));

      vertical.add(scroller);
      panel.add(vertical);

      return panel;
   }

   private Component initImpressumTab() throws IOException
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JEditorPane editorPane = new JEditorPane();
      editorPane.setFont(Settings.getButtonFont());
      editorPane.setContentType("text/html");
      editorPane.setPage(Nachweise.class.getResource("Impressum"));
      editorPane.setEditable(false);
      editorPane.addHyperlinkListener(event -> {
         if (event.getEventType() == EventType.ACTIVATED
               && event.getInputEvent() instanceof MouseEvent)
         {
            MouseEvent mouseEvent = (MouseEvent) event.getInputEvent();
            if (mouseEvent.getClickCount() == 1)
            {
               if (!Desktop.isDesktopSupported())
               {
                  return;
               }
               try
               {
                  Desktop.getDesktop().browse(event.getURL().toURI());
               }
               catch (Exception e)
               {
                  // nothing
               }
            }
         }

      });

      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JScrollPane scroller = new JScrollPane(editorPane);
      scroller.setMinimumSize(new Dimension(500, 300));
      scroller.setMaximumSize(new Dimension(1000, 500));

      vertical.add(scroller);
      panel.add(vertical);

      return panel;
   }

   private Component initSettingsTab()
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 60);
      horizontal.setLayout(horizontalLayout);

      JPanel vertical1 = new JPanel();
      TotemLayout vertical1Layout = new TotemLayout(vertical1, 60);
      vertical1.setLayout(vertical1Layout);

      JPanel vertical2 = new JPanel();
      TotemLayout vertical2Layout = new TotemLayout(vertical2, 60);
      vertical2.setLayout(vertical2Layout);

      JPanel vertical3 = new JPanel();
      TotemLayout vertical3Layout = new TotemLayout(vertical3, 60);
      vertical3.setLayout(vertical3Layout);

      vertical1.add(initSoundPanel());

      vertical2.add(initSavePanel());
      vertical3.add(initImportPanel());
      vertical3.add(initExportPanel());

      horizontal.add(vertical1);
      horizontal.add(vertical2);
      horizontal.add(vertical3);

      panel.add(horizontal);

      return panel;
   }

   private Component initSavePanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel saverLabel = new JLabel("Speicherort");
      saverLabel.setFont(Main.getGermanFont(30F));
      saverLabel.setForeground(Settings.getGold());

      folderLabel = new JTextArea(Settings.getExpressionPath());
      folderLabel.setFont(Settings.getButtonFont());
      folderLabel.setEditable(false);
      folderLabel.setBorder(BorderFactory.createTitledBorder("Ordner"));
      folderLabel.setMinimumSize(new Dimension(WIDTH, 100));
      folderLabel.setMaximumSize(new Dimension(WIDTH, 100));

      folderChooserButton = new JButton("ändern mit Speichern");
      folderChooserButton.setFont(Settings.getButtonFont());
      folderChooserButton.setToolTipText(
            "Behält die aktuellen Vokabeln und speichert sie am neuen Ort. Vorsicht! Alle Vokabeln die am neuen Ort schon waren, gehen verloren!");

      folderChooserButtonWithoutSaving = new JButton("ändern ohne Speichern");
      folderChooserButtonWithoutSaving.setFont(Settings.getButtonFont());
      folderChooserButtonWithoutSaving.setToolTipText(
            "Lädt alle Vokabeln, die am neuen Ort schon vorhanden sind. Beläßt die aktuellen Vokabeln am alten Ort.");

      vertical.add(saverLabel);
      vertical.add(folderLabel);
      vertical.add(folderChooserButton);
      vertical.add(folderChooserButtonWithoutSaving);

      return vertical;
   }

   private Component initExportPanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      exportButton = new JButton("alle Vokabeln exportieren");
      exportButton.setFont(Settings.getButtonFont());
      exportButton.setToolTipText("alle Vokabeln exportieren im csv Format");

      exportSelectedButton = new JButton("markierte Vokabeln exportieren");
      exportSelectedButton.setFont(Settings.getButtonFont());
      exportSelectedButton.setToolTipText(
            "alle markierten Vokabeln exportieren im csv Format");

      exportDatabaseButton = new JButton(
            "Vokabeln einer Datenbank exportieren");
      exportDatabaseButton.setFont(Settings.getButtonFont());
      exportDatabaseButton.setToolTipText(
            "Vokabeln einer Datenbank exportieren im csv Format");
      exportDatabaseButton.setMinimumSize(new Dimension(WIDTH, 30));
      exportDatabaseButton.setMaximumSize(new Dimension(WIDTH, 30));

      JLabel exportLabel = new JLabel("Export");
      exportLabel.setFont(Main.getGermanFont(30F));
      exportLabel.setForeground(Settings.getGold());

      vertical.add(exportLabel);
      vertical.add(exportButton);
      vertical.add(exportSelectedButton);
      vertical.add(exportDatabaseButton);

      return vertical;
   }

   private Component initImportPanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel importLabel = new JLabel("Import");
      importLabel.setFont(Main.getGermanFont(30F));
      importLabel.setForeground(Settings.getGold());

      importButton = new JButton("Datenbank importieren");
      importButton.setFont(Settings.getButtonFont());
      importButton.setMinimumSize(new Dimension(WIDTH, 30));
      importButton.setMaximumSize(new Dimension(WIDTH, 30));

      vertical.add(importLabel);
      vertical.add(importButton);

      return vertical;
   }

   private Component initSoundPanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel soundLabel = new JLabel("Lautstärke");
      soundLabel.setFont(Main.getGermanFont(30F));
      soundLabel.setForeground(Settings.getDarkGold());

      soundButton = new JButton(new ImageIcon(Settings.getSound()));
      soundButton.setBorder(BorderFactory.createEmptyBorder());
      soundButton.setOpaque(false);
      soundButton.setBackground(new Color(0, 0, 0, 0));

      soundslider = new JSlider();
      soundslider.setMinimum(-30);
      soundslider.setMaximum(5);
      soundslider.setValue((int) Settings.getVolume());
      soundslider.setMajorTickSpacing(5);
      soundslider.setMinorTickSpacing(1);
      soundslider.setPaintTicks(true);
      soundslider.setPaintLabels(true);
      soundslider.setSnapToTicks(true);
      soundslider.setMinimumSize(new Dimension(WIDTH, 60));
      soundslider.setMaximumSize(new Dimension(WIDTH, 60));

      waveSoundButton = new JButton("Wellen");
      waveSoundButton.setFont(Settings.getButtonFont());

      clappingSoundButton = new JButton("Applaus");
      clappingSoundButton.setFont(Settings.getButtonFont());

      splotchSoundButton = new JButton("Tintenspritzer");
      splotchSoundButton.setFont(Settings.getButtonFont());

      shredderSoundButton = new JButton("Aktenvernichter");
      shredderSoundButton.setFont(Settings.getButtonFont());

      vertical.add(soundLabel);
      vertical.add(soundButton);
      vertical.add(soundslider);
      vertical.add(waveSoundButton);
      vertical.add(clappingSoundButton);
      vertical.add(splotchSoundButton);
      vertical.add(shredderSoundButton);

      return vertical;
   }

   private Component initDatabaseTab()
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 60);
      horizontal.setLayout(horizontalLayout);

      JPanel vertical1 = new JPanel();
      TotemLayout vertical1Layout = new TotemLayout(vertical1, 60);
      vertical1.setLayout(vertical1Layout);

      vertical1.add(initDatabaseTablePanel());

      horizontal.add(vertical1);

      panel.add(horizontal);

      return panel;
   }

   private Component initDatabaseTablePanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel databaseLabel = new JLabel("Datenbanken");
      databaseLabel.setFont(Main.getGermanFont(30F));
      databaseLabel.setForeground(Settings.getDarkGold());

      DatabaseTable databaseTable = new DatabaseTable(
            Chapter.Database.getModel(), WIDTH*3);
      
      JScrollPane scroller = new JScrollPane(databaseTable);
      scroller.setMinimumSize(new Dimension(WIDTH*3, 300));
      scroller.setMaximumSize(new Dimension(WIDTH*3, 500));
      
      vertical.add(databaseLabel);
      vertical.add(scroller);

      return vertical;
   }

   private void initController()
   {
      soundslider.addChangeListener(event -> {
         if (!soundslider.getValueIsAdjusting())
         {
            Settings.setVolume(soundslider.getValue());
         }
      });

      soundButton.addActionListener(event -> {
         Settings.toggleSoundOnOff();
         soundButton.setIcon(new ImageIcon(Settings.getSound()));
      });

      waveSoundButton.addActionListener(event -> {
         if (Settings.isSoundOn())
         {
            try
            {
               Clip clip = AudioSystem.getClip();
               clip.open(ApplicationSound.getWaveSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(Settings.getVolume());
               clip.start();
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
         }
      });

      clappingSoundButton.addActionListener(event -> {
         if (Settings.isSoundOn())
         {
            try
            {
               Clip clip = AudioSystem.getClip();
               clip.open(ApplicationSound.getClappingSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(Settings.getVolume());
               clip.start();
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
         }
      });

      splotchSoundButton.addActionListener(event -> {
         if (Settings.isSoundOn())
         {
            try
            {
               Clip clip = AudioSystem.getClip();
               clip.open(ApplicationSound.getSplotchSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(Settings.getVolume());
               clip.start();
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
         }
      });

      shredderSoundButton.addActionListener(event -> {
         if (Settings.isSoundOn())
         {
            try
            {
               Clip clip = AudioSystem.getClip();
               clip.open(ApplicationSound.getShredderSound());
               FloatControl volume = (FloatControl) clip
                     .getControl(FloatControl.Type.MASTER_GAIN);
               volume.setValue(Settings.getVolume());
               clip.start();
            }
            catch (LineUnavailableException | IOException e)
            {
               // nothing
            }
         }
      });

      folderChooserButton.addActionListener(event -> {
         folderChooser = new JFileChooser(Settings.getExpressionPath());
         folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         int returnVal = folderChooser.showOpenDialog(this);

         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            Settings.setChoosenExpressionPath(
                  folderChooser.getSelectedFile().getPath());

            this.folderLabel.setText(Settings.getExpressionPath());

            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions();
                  saver.save();
                  SaveTraining trainingSaver = new SaveTraining();
                  trainingSaver.save();
                  return null;
               }

            }.execute();
         }
      });

      folderChooserButtonWithoutSaving.addActionListener(event -> {
         folderChooser = new JFileChooser(Settings.getExpressionPath());
         folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         int returnVal = folderChooser.showOpenDialog(this);

         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            Settings.setChoosenExpressionPath(
                  folderChooser.getSelectedFile().getPath());

            this.folderLabel.setText(Settings.getExpressionPath());

            Main.initDatabase();
         }
      });

      importButton.addActionListener(event -> {

         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog("Import");
         dialog.setVisible(true);

         final String databaseName;
         final boolean overwriteDatabaseNames;
         final String databasePath;

         if (!dialog.isStartImportOrExport())
         {
            dialog.dispose();
            return;
         }
         else
         {
            databaseName = dialog.getDatabaseName();
            overwriteDatabaseNames = dialog.isOverwrite();
            dialog.dispose();
         }

         JFileChooser folderChooser = new JFileChooser(
               Settings.getExpressionPath());
         folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         int returnVal = folderChooser.showOpenDialog(Common.getjFrame());

         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            databasePath = folderChooser.getSelectedFile().getPath();
         }
         else
         {
            return;
         }

         new SwingWorker<Void, Void>()
         {
            @Override
            protected Void doInBackground() throws Exception
            {
               ImportExpressions importer = new ImportExpressions();
               if (importer.importExpressions(databaseName,
                     overwriteDatabaseNames, databasePath))
               {
                  SaveExpressions saver = new SaveExpressions();
                  saver.save();
               }

               return null;
            }

         }.execute();
      });

      exportButton.addActionListener(event -> {

         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(
               "Export alle Vokabeln");
         dialog.setVisible(true);

         final String databaseName;
         final boolean overwriteDatabaseNames;

         if (!dialog.isStartImportOrExport())
         {
            dialog.dispose();
            return;
         }
         else
         {
            databaseName = dialog.getDatabaseName();
            overwriteDatabaseNames = dialog.isOverwrite();
            dialog.dispose();
         }

         folderChooser = new JFileChooser(Settings.getExpressionPath());
         folderChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
         int returnVal = folderChooser.showOpenDialog(this);

         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(
                        folderChooser.getSelectedFile().getPath());
                  saver.save(databaseName, overwriteDatabaseNames);
                  return null;
               }

            }.execute();
         }
      });

      this.exportSelectedButton.addActionListener(event -> {
         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(
               "Export markierte Vokabeln");
         dialog.setVisible(true);

         final String databaseName;
         final boolean overwriteDatabaseNames;

         if (!dialog.isStartImportOrExport())
         {
            dialog.dispose();
            return;
         }
         else
         {
            databaseName = dialog.getDatabaseName();
            overwriteDatabaseNames = dialog.isOverwrite();
            dialog.dispose();
         }

         folderChooser = new JFileChooser(Settings.getExpressionPath());
         folderChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
         int returnVal = folderChooser.showOpenDialog(this);

         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(
                        folderChooser.getSelectedFile().getPath());
                  saver.save(databaseName, overwriteDatabaseNames, true);
                  return null;
               }

            }.execute();
         }
      });

      exportDatabaseButton.addActionListener(event -> {
         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(
               "Export eine Datenbank");
         dialog.setVisible(true);

         final String databaseName;
         final boolean overwriteDatabaseNames;

         if (!dialog.isStartImportOrExport())
         {
            dialog.dispose();
            return;
         }
         else
         {
            databaseName = dialog.getDatabaseName();
            overwriteDatabaseNames = dialog.isOverwrite();
            dialog.dispose();
         }

         String databaseChoosen = (String) JOptionPane.showInputDialog(
               Common.getMainJPanel(), "Wählen Sie eine Datenbank aus.",
               "Auswahl", JOptionPane.QUESTION_MESSAGE,
               new ImageIcon(ApplicationImages.getLogo24()),
               Data.getAllDatabases(), Database.SELF.getName());

         if (databaseChoosen == null)
         {
            return;
         }

         folderChooser = new JFileChooser(Settings.getExpressionPath());
         folderChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
         int returnVal = folderChooser.showOpenDialog(this);

         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(
                        folderChooser.getSelectedFile().getPath());
                  saver.save(databaseName, overwriteDatabaseNames,
                        databaseChoosen);
                  return null;
               }

            }.execute();
         }
      });
   }
}
