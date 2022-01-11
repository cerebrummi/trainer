package vokabeltrainer.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import vokabeltrainer.ApplicationColors;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.ApplicationSound;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.PathAndFile;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.ImportExpressions;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.panels.settings.InputDatabaseNameDialog;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.resources.html.Nachweise;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

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
   private JButton folderChooserButtonWithoutSaving;
   private JTextArea folderLabel;
   private JButton importButton;
   private JButton exportButton;
   private JButton exportSelectedButton;
   private JButton exportDatabaseButton;
   private JButton deleteDatabaseButton;
   private Translator translator = Common.getTranslator();

   public SettingsPanel()
   {
      setLayout(new BullsEyeLayout(this));

      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(false);
      tabbedPane.setFont(Main.getGermanFont(16F));
      tabbedPane.addTab(translator.realisticTranslate(Translation.EINSTELLUNGEN_UND_SERVICE), initSettingsTab());
      try
      {
         tabbedPane.addTab(translator.realisticTranslate(Translation.INFO), initImpressumTab());
      }
      catch (IOException e1)
      {
         // nothing
      }
      try
      {
         tabbedPane.addTab(translator.realisticTranslate(Translation.NACHWEISE), initLicencingTab());
      }
      catch (IOException e)
      {
         // nothing
      }

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
      editorPane.setPage(Nachweise.class.getResource("lizenz.txt"));
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
      saverLabel.setForeground(ApplicationColors.getGold());

      folderLabel = new JTextArea(Settings.getExpressionPath());
      folderLabel.setFont(Settings.getButtonFont());
      folderLabel.setEditable(false);
      folderLabel.setBorder(BorderFactory.createTitledBorder("Ordner"));
      folderLabel.setMinimumSize(new Dimension(WIDTH, 100));
      folderLabel.setMaximumSize(new Dimension(WIDTH, 100));

      folderChooserButtonWithoutSaving = new JButton("ändern");
      folderChooserButtonWithoutSaving.setFont(Settings.getButtonFont());
      folderChooserButtonWithoutSaving
            .setToolTipText(
                  "Lädt alle Vokabeln, die am neuen Ort schon vorhanden sind. Beläßt die aktuellen Vokabeln am alten Ort.");

      JLabel deleteLabel = new JLabel("Datenbank löschen");
      deleteLabel.setFont(Main.getGermanFont(30F));
      deleteLabel.setForeground(ApplicationColors.getGold());

      deleteDatabaseButton = new JButton("eigene Datenbank löschen");
      deleteDatabaseButton.setFont(Settings.getButtonFont());
      deleteDatabaseButton
            .setToolTipText(
                  "Verschiebt alle Vokabeln einer Datenbank in den Papierkorb.");

      vertical.add(saverLabel);
      vertical.add(folderLabel);
      vertical.add(folderChooserButtonWithoutSaving);
      vertical.add(deleteLabel);
      vertical.add(deleteDatabaseButton);

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
      exportSelectedButton
            .setToolTipText(
                  "alle markierten Vokabeln exportieren im csv Format");

      exportDatabaseButton = new JButton(
            "Vokabeln einer Datenbank exportieren");
      exportDatabaseButton.setFont(Settings.getButtonFont());
      exportDatabaseButton
            .setToolTipText(
                  "Vokabeln einer Datenbank exportieren im csv Format");
      exportDatabaseButton.setMinimumSize(new Dimension(WIDTH, 30));
      exportDatabaseButton.setMaximumSize(new Dimension(WIDTH, 30));

      JLabel exportLabel = new JLabel("Export");
      exportLabel.setFont(Main.getGermanFont(30F));
      exportLabel.setForeground(ApplicationColors.getGold());
      
      JLabel exportExplanation = new JLabel(" Kein Export von internen Datenbanken möglich.");
      exportExplanation.setFont(Main.getGermanFont(14F));
      exportExplanation.setForeground(ApplicationColors.getGold());

      vertical.add(exportLabel);
      vertical.add(exportButton);
      vertical.add(exportSelectedButton);
      vertical.add(exportDatabaseButton);
      vertical.add(exportExplanation);

      return vertical;
   }

   private Component initImportPanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel importLabel = new JLabel("Import");
      importLabel.setFont(Main.getGermanFont(30F));
      importLabel.setForeground(ApplicationColors.getGold());

      importButton = new JButton("Datenbank importieren");
      importButton.setFont(Settings.getButtonFont());
      importButton.setMinimumSize(new Dimension(WIDTH, 30));
      importButton.setMaximumSize(new Dimension(WIDTH, 30));

      vertical.add(importLabel);
      vertical.add(importButton);

      return vertical;
   }

   public void setValues()
   {
      soundButton.setIcon(new ImageIcon(Settings.getSound()));
      soundslider.setValue((int) Settings.getVolume());
   }

   private Component initSoundPanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel soundLabel = new JLabel("Lautstärke");
      soundLabel.setFont(Main.getGermanFont(30F));
      soundLabel.setForeground(ApplicationColors.getDarkGold());

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

      folderChooserButtonWithoutSaving.addActionListener(event -> {

         String pathOfFolder = choosesFolderForSave();
         if (pathOfFolder != null)
         {
            Settings.setChoosenExpressionPath(pathOfFolder);
            this.folderLabel.setText(Settings.getExpressionPath());
            Main.initDatabase();
         }

      });

      importButton.addActionListener(event -> {

         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog("Import");
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

         String pathOfFolderOrFile = choosesFolderOrZipFileForOpen();
         if (pathOfFolderOrFile != null)
         {
            new SwingWorker<Void, Void>()
            {
               @Override
               protected Void doInBackground() throws Exception
               {
                  ImportExpressions importer = new ImportExpressions();
                  if (importer
                        .importExpressions(databaseName, overwriteDatabaseNames,
                              pathOfFolderOrFile))
                  {
                     SaveExpressions saver = new SaveExpressions();
                     saver.save();
                  }

                  return null;
               }

            }.execute();
         }
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

         PathAndFile pathOfFolder = choosesFolderAndFileForSave();
         if (pathOfFolder != null)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(pathOfFolder);
                  saver.export(databaseName, overwriteDatabaseNames);

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

         PathAndFile pathOfFolder = choosesFolderAndFileForSave();
         if (pathOfFolder != null)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(pathOfFolder);
                  saver.export(databaseName, overwriteDatabaseNames, true);

                  return null;
               }

            }.execute();
         }
      });

      exportDatabaseButton.addActionListener(event -> {
         
         String databaseChoosen = (String) JOptionPane
               .showInputDialog(Common.getMainJPanel(),
                     "Wählen Sie eine Datenbank für den Export aus.", "Auswahl",
                     JOptionPane.QUESTION_MESSAGE,
                     new ImageIcon(ApplicationImages.getLogo24()),
                     Data.getAllOwnDistinctDatabaseDescriptions(false), null);

         if (databaseChoosen == null)
         {
            return;
         }
         
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

         PathAndFile pathOfFolder = choosesFolderAndFileForSave();
         if (pathOfFolder != null)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(pathOfFolder);
                  saver
                        .export(databaseName, overwriteDatabaseNames,
                              databaseChoosen);
                  return null;
               }

            }.execute();
         }
      });

      this.deleteDatabaseButton.addActionListener(event -> {
         String databaseChoosen = (String) JOptionPane
               .showInputDialog(Common.getMainJPanel(),
                     "Wählen Sie eine Datenbank zum Löschen aus.",
                     "Datenbank in den Papierkorb",
                     JOptionPane.QUESTION_MESSAGE,
                     new ImageIcon(ApplicationImages.getLogo24()),
                     Data.getAllOwnDistinctDatabaseDescriptions(false), null);

         if (databaseChoosen == null)
         {
            return;
         }
         
         if(JOptionPane
            .showConfirmDialog(Common.getjFrame(),
                  "Wollen Sie wirklich die Vokabeln von \"" + databaseChoosen + "\" löschen?",
                  "Frage", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE) != 0)
         {
            return;
         }

         new SwingWorker<Void, Void>()
         {

            @Override
            protected Void doInBackground() throws Exception
            {
               Data.deleteExpressionsOfDatabase(databaseChoosen);
               new SaveExpressions().save();
               return null;
            }

         }.execute();
      });
   }

   private boolean testIfFolderExists(String path)
   {
      File folder = new File(path);
      return folder.exists() && folder.isDirectory();
   }

   private boolean testIfZipFileExists(String path)
   {
      File folder = new File(path);
      return folder.exists() && folder.isFile()
            && (path.endsWith(".zip") || path.endsWith(".ZIP"));
   }

   private boolean testIfFileExists(String path)
   {
      File folder = new File(path);
      return folder.exists() && folder.isFile();
   }

   private String choosesFolderForSave()
   {
      JFileChooser folderChooser = new JFileChooser(
            Settings.getExpressionPath());
      folderChooser.setAcceptAllFileFilterUsed(false);
      folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      int choice = folderChooser.showSaveDialog(this);

      if (JFileChooser.APPROVE_OPTION == choice)
      {
         String result = folderChooser.getSelectedFile().getPath();
         if (!testIfFolderExists(folderChooser.getSelectedFile().getPath()))
         {
            JOptionPane
                  .showMessageDialog(this,
                        "Der gewählte Ordner existiert nicht:\n" + result
                              + "\nBitte wählen Sie einen existierenden Ordner.\nDanke!",
                        "Nachricht", JOptionPane.CLOSED_OPTION);
            return null;
         }
         return result;
      }
      return null;
   }

   private PathAndFile choosesFolderAndFileForSave()
   {
      JFileChooser folderChooser = new JFileChooser(
            Settings.getExpressionPath());
      folderChooser.setAcceptAllFileFilterUsed(false);
      folderChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

      int choice = folderChooser.showSaveDialog(this);

      if (JFileChooser.APPROVE_OPTION == choice)
      {
         String splitter = "\\" + File.separator;
         String[] foldersAndFile = folderChooser
               .getSelectedFile()
               .getPath()
               .split(splitter);
         PathAndFile pathAndFile = new PathAndFile();
         StringJoiner joiner = new StringJoiner(splitter);
         for (int i = 0; i < foldersAndFile.length; i++)
         {
            if (i == foldersAndFile.length - 1)
            {
               pathAndFile.setFile(foldersAndFile[i]);
            }
            else
            {
               joiner.add(foldersAndFile[i]);
            }
         }
         pathAndFile.setPath(joiner.toString());

         if (!testIfFolderExists(pathAndFile.getPath()))
         {
            JOptionPane
                  .showMessageDialog(this,
                        "Der gewählte Ordner existiert nicht:\n"
                              + pathAndFile.getPath()
                              + "\nBitte wählen Sie einen existierenden Ordner.\nDanke!",
                        "Nachricht", JOptionPane.CLOSED_OPTION);
            return null;
         }

         if (testIfFileExists(pathAndFile.getPathFile()))
         {
            int answer = JOptionPane
                  .showConfirmDialog(this,
                        "Die Datei existiert schon,\nsoll Sie überschrieben werden?",
                        "Frage", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
            if (JOptionPane.OK_OPTION != answer)
            {
               return null;
            }
         }

         if (testIfFileExists(pathAndFile.getPathFile() + ".zip"))
         {
            int answer = JOptionPane
                  .showConfirmDialog(this,
                        "Die Datei existiert schon,\nsoll Sie überschrieben werden?",
                        "Frage", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
            if (JOptionPane.OK_OPTION != answer)
            {
               return null;
            }
         }

         if (testIfFileExists(pathAndFile.getPathFile() + ".ZIP"))
         {
            int answer = JOptionPane
                  .showConfirmDialog(this,
                        "Die Datei existiert schon,\nsoll Sie überschrieben werden?",
                        "Frage", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
            if (JOptionPane.OK_OPTION != answer)
            {
               return null;
            }
         }
         return pathAndFile;
      }
      return null;
   }

   private String choosesFolderOrZipFileForOpen()
   {
      JFileChooser folderChooser = new JFileChooser(
            Settings.getExpressionPath());
      folderChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      FileFilter filter = new FileNameExtensionFilter("zip-Datei", "zip");
      folderChooser.setAcceptAllFileFilterUsed(false);
      folderChooser.addChoosableFileFilter(filter);

      int choice = folderChooser.showOpenDialog(this);
      if (JFileChooser.APPROVE_OPTION == choice)
      {
         String result = folderChooser.getSelectedFile().getPath();
         if (testIfFolderExists(result))
         {
            return result;
         }
         else if (testIfZipFileExists(result))
         {
            return result;
         }
         else
         {
            JOptionPane
                  .showMessageDialog(this,
                        "Der gewählte Ordner/die Datei existiert nicht:\n"
                              + "oder die Datei ist keine zip-Datei\n"
                              + folderChooser.getSelectedFile().getPath()
                              + "\nBitte wählen Sie einen existierenden Ordner bzw. zip-Datei.\nDanke!",
                        "Nachricht", JOptionPane.CLOSED_OPTION);
            return null;
         }
      }

      return null;
   }
}
