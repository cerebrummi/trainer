package vokabeltrainer.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringJoiner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import vokabeltrainer.PathAndFile;
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.ImportExpressions;
import vokabeltrainer.common.main.SaveExpressions;
import vokabeltrainer.common.main.View;
import vokabeltrainer.common.main.Settings.OperatingSystem;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.panels.settings.InputDatabaseNameDialog;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.resources.html.Nachweise;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class SettingsPanel extends JPanel
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
   private Translator translator;
   private JCheckBox modus;
   private JButton originalFolder;

   public SettingsPanel(App app, Common common, Model model, View view)
   {
      translator = common.getTranslator();
      setLayout(new BullsEyeLayout(this));
      this.setOpaque(true);
      this.setBackground(app.appColors.getTexturedBackgroundColor());
      
      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(false);
      tabbedPane.setFont(app.appFonts.buttonFont);
      tabbedPane.addTab(translator.realisticTranslate(app, 
            Translation.EINSTELLUNGEN_UND_SERVICE), initSettingsTab(app));

      try
      {
         tabbedPane.addTab(translator.realisticTranslate(app, Translation.INFO),
               initImpressumTab(app));
      }
      catch (IOException e1)
      {
         // nothing
      }
      try
      {
         tabbedPane.addTab(translator.realisticTranslate(app, Translation.NACHWEISE),
               initLicencingTab(app));
      }
      catch (IOException e)
      {
         // nothing
      }

      // tabbedPane.addTab(translator.realisticTranslate(
      // Translation.SICHERHEITSKOPIEN), initBackupsTab());

      tabbedPane.addTab(
            translator.realisticTranslate(app, Translation.SCHABBAT_MODUS),
            initSchabbatTab(app));

      add(tabbedPane);

      initColors(app);
      
      initController(app, common, model, view);
   }

   private void initColors(App app)
   {
      waveSoundButton.setForeground(app.appColors.settings.getTextForeground());
      clappingSoundButton.setForeground(app.appColors.settings.getTextForeground());
      splotchSoundButton.setForeground(app.appColors.settings.getTextForeground());
      shredderSoundButton.setForeground(app.appColors.settings.getTextForeground());
      importButton.setForeground(app.appColors.settings.getTextForeground());
      exportButton.setForeground(app.appColors.settings.getTextForeground());
      exportSelectedButton.setForeground(app.appColors.settings.getTextForeground());
      exportDatabaseButton.setForeground(app.appColors.settings.getTextForeground());
      deleteDatabaseButton.setForeground(app.appColors.settings.getTextForeground());
      folderChooserButtonWithoutSaving.setForeground(app.appColors.settings.getTextForeground());
   }

   private Component initSchabbatTab(App app)
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);
      panel.setBackground(app.appColors.getShadyBlue());

      modus = new JCheckBox(translator
            .realisticTranslate(app, Translation.SCHABBAT_MODUS));
      modus.setFont(app.appFonts.buttonFont);
      modus.setForeground(app.appColors.getWhite());
      modus.setSelected(app.settings.isSchabbat_modus());

      panel.add(modus);
      return panel;
   }

   /* TODO
    * private Component initBackupsTab() { JPanel panel = new JPanel();
    * BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
    * panel.setLayout(panelLayout);
    * 
    * panel.add(new JLabel("backup")); return panel; }
    */

   private Component initLicencingTab(App app) throws IOException
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JEditorPane editorPane = new JEditorPane();
      editorPane.setFont(app.appFonts.buttonFont);
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

   private Component initImpressumTab(App app) throws IOException
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JEditorPane editorPane = new JEditorPane();
      editorPane.setFont(app.appFonts.buttonFont);
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

   private Component initSettingsTab(App app)
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JPanel verticalInfo = new JPanel();
      TotemLayout verticalInfoLayout = new TotemLayout(verticalInfo, 60);
      verticalInfo.setLayout(verticalInfoLayout);

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

      vertical1.add(initSoundPanel(app));

      vertical2.add(initSavePanel(app));
      vertical2.add(initDeletePanel(app));

      vertical3.add(initImportPanel(app));
      vertical3.add(initExportPanel(app));

      horizontal.add(vertical1);
      horizontal.add(vertical2);
      horizontal.add(vertical3);
      
      JButton link = new JButton(translator.realisticTranslate(app, Translation.ERKLAEHR_VIDEO));
      link.setBackground(app.appColors.getGreen());
      link.setFont(app.appFonts.buttonFont);
      link.addActionListener(_ -> {
         Desktop desktop = Desktop.getDesktop();
         try
         {
            URI uri = new URI("https://youtu.be/qxvUVM_j8no");
            desktop.browse(uri);
         }
         catch (URISyntaxException | IOException e)
         {
            e.printStackTrace();
         }
      });

      verticalInfo.add(link);
      verticalInfo.add(horizontal);

      panel.add(verticalInfo);

      return panel;
   }

   private Component initDeletePanel(App app)
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel deleteLabel = new JLabel(
            translator.realisticTranslate(app, Translation.LOESCHEN));
      deleteLabel.setFont(app.appFonts.germanFont.deriveFont(30F));
      deleteLabel.setForeground(app.appColors.getGold());

      deleteDatabaseButton = new JButton(translator
            .realisticTranslate(app, Translation.EIGENE_DATENBANK_LOESCHEN));
      deleteDatabaseButton.setFont(app.appFonts.buttonFont);
      deleteDatabaseButton.setToolTipText(translator.realisticTranslate(app, 
            Translation.VERSCHIEBT_ALLE_VOKABELN_EINER_DATENBANK_IN_DEN_PAPIERKORB_));
      deleteDatabaseButton.setMinimumSize(new Dimension(WIDTH, 30));
      deleteDatabaseButton.setMaximumSize(new Dimension(WIDTH, 30));

      vertical.add(deleteLabel);
      vertical.add(deleteDatabaseButton);

      return vertical;
   }

   private Component initSavePanel(App app)
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel saverLabel = new JLabel(
            translator.realisticTranslate(app, Translation.SPEICHERORT));
      saverLabel.setFont(app.appFonts.germanFont.deriveFont(30F));
      saverLabel.setForeground(app.appColors.getGold());

      folderLabel = new JTextArea(app.settings.getExpressionPath());
      folderLabel.setFont(app.appFonts.buttonFont);
      folderLabel.setEditable(false);
      folderLabel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(app, Translation.ORDNER)));
      folderLabel.setMinimumSize(new Dimension(WIDTH, 100));
      folderLabel.setMaximumSize(new Dimension(WIDTH, 100));

      folderChooserButtonWithoutSaving = new JButton(
            translator.realisticTranslate(app, Translation.AENDERN));
      folderChooserButtonWithoutSaving
            .setFont(app.appFonts.buttonFont);
      folderChooserButtonWithoutSaving.setToolTipText(translator
            .realisticTranslate(app, 
                  Translation.LAEDT_ALLE_VOKABELN__DIE_AM_NEUEN_ORT_SCHON_VORHANDEN_SIND_)
            + " " + translator.realisticTranslate(app, 
                  Translation.BELAESST_DIE_AKTUELLEN_VOKABELN_AM_ALTEN_ORT));

      originalFolder = new JButton(translator.realisticTranslate(app, Translation.ZURUECKSETZEN));
      originalFolder.setForeground(app.appColors.settings.getTextForeground());
      originalFolder.setFont(app.appFonts.buttonFont);
      
      vertical.add(saverLabel);
      vertical.add(folderLabel);
      vertical.add(folderChooserButtonWithoutSaving);
      vertical.add(originalFolder);
      
      return vertical;
   }

   private Component initExportPanel(App app)
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      exportButton = new JButton(translator
            .realisticTranslate(app, Translation.ALLE_VOKABELN_EXPORTIEREN));
      exportButton.setFont(app.appFonts.buttonFont);
      exportButton.setToolTipText(
            translator.realisticTranslate(app, Translation.ALLE_VOKABELN_EXPORTIEREN)
                  + " " + translator.realisticTranslate(app, 
                        Translation.IM_CSV_FORMAT_ALS_ZIP_DATEI));

      exportSelectedButton = new JButton(translator
            .realisticTranslate(app, Translation.MARKIERTE_VOKABELN_EXPORTIEREN));
      exportSelectedButton.setFont(app.appFonts.buttonFont);
      exportSelectedButton.setToolTipText(translator
            .realisticTranslate(app, Translation.MARKIERTE_VOKABELN_EXPORTIEREN)
            + " " + translator
                  .realisticTranslate(app, Translation.IM_CSV_FORMAT_ALS_ZIP_DATEI));

      exportDatabaseButton = new JButton(translator.realisticTranslate(app, 
            Translation.VOKABELN_EINER_DATENBANK_EXPORTIEREN));
      exportDatabaseButton.setFont(app.appFonts.buttonFont);
      exportDatabaseButton.setToolTipText(translator.realisticTranslate(app, 
            Translation.VOKABELN_EINER_DATENBANK_EXPORTIEREN) + " "
            + translator
                  .realisticTranslate(app, Translation.IM_CSV_FORMAT_ALS_ZIP_DATEI));
      exportDatabaseButton.setMinimumSize(new Dimension(WIDTH, 30));
      exportDatabaseButton.setMaximumSize(new Dimension(WIDTH, 30));

      JLabel exportLabel = new JLabel(
            translator.realisticTranslate(app, Translation.EXPORT));
      exportLabel.setFont(app.appFonts.germanFont.deriveFont(30F));
      exportLabel.setForeground(app.appColors.getGold());

      JLabel exportExplanation = new JLabel(" " + translator.realisticTranslate(app, 
            Translation.KEIN_EXPORT_VON_INTERNEN_DATENBANKEN_MOEGLICH));
      exportExplanation.setFont(app.appFonts.germanFont.deriveFont(14F));
      exportExplanation.setForeground(app.appColors.getGold());

      vertical.add(exportLabel);
      vertical.add(exportButton);
      vertical.add(exportSelectedButton);
      vertical.add(exportDatabaseButton);
      vertical.add(exportExplanation);

      return vertical;
   }

   private Component initImportPanel(App app)
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel importLabel = new JLabel(
            translator.realisticTranslate(app, Translation.IMPORT));
      importLabel.setFont(app.appFonts.germanFont.deriveFont(30F));
      importLabel.setForeground(app.appColors.getGold());

      importButton = new JButton(
            translator.realisticTranslate(app, Translation.DATENBANK_IMPORTIEREN));
      importButton.setFont(app.appFonts.buttonFont);
      importButton.setMinimumSize(new Dimension(WIDTH, 30));
      importButton.setMaximumSize(new Dimension(WIDTH, 30));

      vertical.add(importLabel);
      vertical.add(importButton);

      return vertical;
   }

   public void setValues(App app)
   {
      soundButton.setIcon(new ImageIcon(app.settings.getSound()));
      soundslider.setValue((int) app.settings.getVolume());
   }

   private Component initSoundPanel(App app)
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel soundLabel = new JLabel(
            translator.realisticTranslate(app, Translation.LAUTSTAERKE));
      soundLabel.setFont(app.appFonts.germanFont.deriveFont(30F));
      soundLabel.setForeground(app.appColors.getDarkGold());

      soundButton = new JButton(new ImageIcon(app.settings.getSound()));
      soundButton.setBorder(BorderFactory.createEmptyBorder());
      soundButton.setOpaque(false);
      soundButton.setBackground(new Color(0, 0, 0, 0));

      soundslider = new JSlider();
      soundslider.setMinimum(-30);
      soundslider.setMaximum(5);
      soundslider.setValue((int) app.settings.getVolume());
      soundslider.setMajorTickSpacing(5);
      soundslider.setMinorTickSpacing(1);
      soundslider.setPaintTicks(true);
      soundslider.setPaintLabels(true);
      soundslider.setSnapToTicks(true);
      soundslider.setMinimumSize(new Dimension(WIDTH, 60));
      soundslider.setMaximumSize(new Dimension(WIDTH, 60));

      waveSoundButton = new JButton(
            translator.realisticTranslate(app, Translation.WELLEN));
      waveSoundButton.setFont(app.appFonts.buttonFont);

      clappingSoundButton = new JButton(
            translator.realisticTranslate(app, Translation.APPLAUS));
      clappingSoundButton.setFont(app.appFonts.buttonFont);

      splotchSoundButton = new JButton(
            translator.realisticTranslate(app, Translation.TINTENSPRITZER));
      splotchSoundButton.setFont(app.appFonts.buttonFont);

      shredderSoundButton = new JButton(
            translator.realisticTranslate(app, Translation.AKTENVERNICHTER));
      shredderSoundButton.setFont(app.appFonts.buttonFont);

      vertical.add(soundLabel);
      vertical.add(soundButton);
      vertical.add(soundslider);
      vertical.add(waveSoundButton);
      vertical.add(clappingSoundButton);
      vertical.add(splotchSoundButton);
      vertical.add(shredderSoundButton);

      return vertical;
   }

   private void initController(App app, Common common, Model model, View view)
   {
      soundslider.addChangeListener(_ -> {
         if (!soundslider.getValueIsAdjusting())
         {
            app.settings.setVolume(soundslider.getValue());
         }
      });

      soundButton.addActionListener(_ -> {
         app.settings.toggleSoundOnOff();
         soundButton.setIcon(new ImageIcon(app.settings.getSound()));
      });

      waveSoundButton.addActionListener(_ -> {
         if (app.settings.isSoundOn())
         {
            new SwingWorker<Void, Void>()
            {
               @Override
               protected Void doInBackground() throws Exception
               {
                  try (Clip clip = AudioSystem.getClip())
                  {
                     clip.open(app.appSound.getWaveSound());
                     FloatControl volume = (FloatControl) clip
                           .getControl(FloatControl.Type.MASTER_GAIN);
                     volume.setValue(app.settings.getVolume());
                     clip.start();
                     do
                     {
                        Thread.sleep(100);
                     } while (clip.isRunning());
                  }
                  catch (LineUnavailableException | IOException e)
                  {
                     // nothing
                  }
                  catch (InterruptedException e)
                  {
                     // nothing
                  }
                  return null;
               }
            }.execute();
         }
      });

      clappingSoundButton.addActionListener(_ -> {
         if (app.settings.isSoundOn())
         {
            new SwingWorker<Void, Void>()
            {
               @Override
               protected Void doInBackground() throws Exception
               {
                  try (Clip clip = AudioSystem.getClip())
                  {
                     clip.open(app.appSound.getClappingSound());
                     FloatControl volume = (FloatControl) clip
                           .getControl(FloatControl.Type.MASTER_GAIN);
                     volume.setValue(app.settings.getVolume());
                     clip.start();
                     do
                     {
                        Thread.sleep(100);
                     } while (clip.isRunning());
                  }
                  catch (LineUnavailableException | IOException e)
                  {
                     // nothing
                  }
                  catch (InterruptedException e)
                  {
                     // nothing
                  }
                  return null;
               }
            }.execute();
         }
      });

      splotchSoundButton.addActionListener(_ -> {
         if (app.settings.isSoundOn())
         {
            new SwingWorker<Void, Void>()
            {
               @Override
               protected Void doInBackground() throws Exception
               {
                  try (Clip clip = AudioSystem.getClip())
                  {
                     clip.open(app.appSound.getSplotchSound());
                     FloatControl volume = (FloatControl) clip
                           .getControl(FloatControl.Type.MASTER_GAIN);
                     volume.setValue(app.settings.getVolume());
                     clip.start();
                     do
                     {
                        Thread.sleep(100);
                     } while (clip.isRunning());
                  }
                  catch (LineUnavailableException | IOException e)
                  {
                     // nothing
                  }
                  catch (InterruptedException e)
                  {
                     // nothing
                  }
                  return null;
               }
            }.execute();
         }
      });

      shredderSoundButton.addActionListener(_ -> {
         if (app.settings.isSoundOn())
         {
            new SwingWorker<Void, Void>()
            {
               @Override
               protected Void doInBackground() throws Exception
               {
                  try (Clip clip = AudioSystem.getClip())
                  {
                     clip.open(app.appSound.getShredderSound());
                     FloatControl volume = (FloatControl) clip
                           .getControl(FloatControl.Type.MASTER_GAIN);
                     volume.setValue(app.settings.getVolume());
                     clip.start();
                     do
                     {
                        Thread.sleep(100);
                     } while (clip.isRunning());
                  }
                  catch (LineUnavailableException | IOException e)
                  {
                     // nothing
                  }
                  catch (InterruptedException e)
                  {
                     // nothing
                  }
                  return null;
               }
            }.execute();
         }
      });

      folderChooserButtonWithoutSaving.addActionListener(_ -> {

         String pathOfFolder = choosesFolderForSave(app);
         if (pathOfFolder != null)
         {
            app.settings.setChoosenExpressionPath(pathOfFolder);
            this.folderLabel.setText(app.settings.getExpressionPath());
            model.data.initDatabase(app, common, view);
         }

      });
      
      originalFolder.addActionListener(_ -> {
         app.settings.setBackExpressionPath();
         this.folderLabel.setText(app.settings.getExpressionPath());
         model.data.initDatabase(app, common, view);
      });

      importButton.addActionListener(_ -> {

         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(app, common, view,
               translator.realisticTranslate(app, Translation.IMPORT));
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

         String pathOfFolderOrFile = choosesFolderOrZipFileForOpen(app);
         if (pathOfFolderOrFile != null)
         {
            new SwingWorker<Void, Void>()
            {
               @Override
               protected Void doInBackground() throws Exception
               {
                  ImportExpressions importer = new ImportExpressions(model);
                  if (importer.importExpressions(app, common, databaseName,
                        overwriteDatabaseNames, pathOfFolderOrFile))
                  {
                     SaveExpressions saver = new SaveExpressions(app, model);
                     saver.save(app, common, view);
                  }

                  return null;
               }

            }.execute();
         }
      });

      exportButton.addActionListener(_ -> {

         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(app, common, view, translator
               .realisticTranslate(app, Translation.EXPORT_ALLER_VOKABELN));
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

         PathAndFile pathOfFolder = choosesFolderAndFileForSave(app);
         if (pathOfFolder != null)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(pathOfFolder);
                  saver.export(app, common, view, databaseName, overwriteDatabaseNames);

                  return null;
               }

            }.execute();
         }
      });

      this.exportSelectedButton.addActionListener(_ -> {
         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(app, common, view, translator
               .realisticTranslate(app, Translation.EXPORT_MARKIERTER_VOKABELN));
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

         PathAndFile pathOfFolder = choosesFolderAndFileForSave(app);
         if (pathOfFolder != null)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(pathOfFolder);
                  saver.export(app, common, view, databaseName, overwriteDatabaseNames, true);

                  return null;
               }

            }.execute();
         }
      });

      exportDatabaseButton.addActionListener(_ -> {

         String databaseChoosen = (String) JOptionPane.showInputDialog(
               view.getMainJPanel(),
               translator.realisticTranslate(app, 
                     Translation.WAEHLEN_SIE_EINE_DATENBANK_FUER_DEN_EXPORT_AUS_),
               translator.realisticTranslate(app, Translation.AUSWAHL),
               JOptionPane.QUESTION_MESSAGE,
               new ImageIcon(app.appImages.getLogo24()),
               model.data.getAllOwnDistinctDatabaseDescriptions(app, common, false), null);

         if (databaseChoosen == null)
         {
            return;
         }

         InputDatabaseNameDialog dialog = new InputDatabaseNameDialog(app, common, view, translator
               .realisticTranslate(app, Translation.EXPORT_EINER_DATENBANK));
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

         PathAndFile pathOfFolder = choosesFolderAndFileForSave(app);
         if (pathOfFolder != null)
         {
            new SwingWorker<Void, Void>()
            {

               @Override
               protected Void doInBackground() throws Exception
               {
                  SaveExpressions saver = new SaveExpressions(pathOfFolder);
                  saver.export(app, common, view, databaseName, overwriteDatabaseNames,
                        databaseChoosen);
                  return null;
               }

            }.execute();
         }
      });

      this.deleteDatabaseButton.addActionListener(_ -> {
         String databaseChoosen = (String) JOptionPane.showInputDialog(
               view.getMainJPanel(),
               translator.realisticTranslate(app, 
                     Translation.WAEHLEN_SIE_EINE_DATENBANK_ZUM_LOESCHEN_AUS),
               translator.realisticTranslate(app, 
                     Translation.DATENBANK_IN_DEN_PAPIERKORB),
               JOptionPane.QUESTION_MESSAGE,
               new ImageIcon(app.appImages.getLogo24()),
               model.data.getAllOwnDistinctDatabaseDescriptions(app, common, false), null);

         if (databaseChoosen == null)
         {
            return;
         }

         if (JOptionPane.showConfirmDialog(view.getjFrame(),
               translator.realisticTranslate(app, 
                     Translation.WOLLEN_SIE_WIRKLICH_DIE_VOKABELN_VON__)
                     + databaseChoosen
                     + translator.realisticTranslate(app, Translation.__LOESCHEN_),
               translator.realisticTranslate(app, Translation.FRAGE),
               JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != 0)
         {
            return;
         }

         new SwingWorker<Void, Void>()
         {

            @Override
            protected Void doInBackground() throws Exception
            {
               model.data.deleteExpressionsOfDatabase(app, common, databaseChoosen);
               new SaveExpressions(app, model).save(app, common, view);
               return null;
            }

         }.execute();
      });

      this.modus.addActionListener(_ -> {
         if (!common.isSchabbat())
         {
            app.settings.setSchabbat_modus(modus.isSelected());
         }
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

   private String choosesFolderForSave(App app)
   {
      JFileChooser folderChooser = new JFileChooser(
            app.settings.getExpressionPath());
      folderChooser.setAcceptAllFileFilterUsed(false);
      folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      int choice = folderChooser.showSaveDialog(this);

      if (JFileChooser.APPROVE_OPTION == choice)
      {
         String result = folderChooser.getSelectedFile().getPath();
         if (!testIfFolderExists(folderChooser.getSelectedFile().getPath()))
         {
            JOptionPane.showMessageDialog(this, translator.realisticTranslate(app, 
                  Translation.DER_GEWAEHLTE_ORDNER_EXISTIERT_NICHT_) + "\n"
                  + result + "\n"
                  + translator.realisticTranslate(app, 
                        Translation.BITTE_WAEHLEN_SIE_EINEN_EXISTIERENDEN_ORDNER_)
                  + "\n" + translator.realisticTranslate(app, Translation.DANKE_),
                  translator.realisticTranslate(app, Translation.NACHRICHT),
                  JOptionPane.CLOSED_OPTION);
            return null;
         }
         return result;
      }
      return null;
   }

   private PathAndFile choosesFolderAndFileForSave(App app)
   {
      JFileChooser folderChooser = new JFileChooser(
            app.settings.getExpressionPath());
      folderChooser.setAcceptAllFileFilterUsed(false);
      folderChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

      int choice = folderChooser.showSaveDialog(this);

      if (JFileChooser.APPROVE_OPTION == choice)
      {
         String splitter = null;
         if (OperatingSystem.WINDOWS == app.settings.getOperatingSystem())
         {
            splitter = File.separator + File.separator;
         }
         else
         {
            splitter = File.separator;
         }

         String[] foldersAndFile = folderChooser.getSelectedFile().getPath()
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
            JOptionPane.showMessageDialog(this, translator.realisticTranslate(app, 
                  Translation.DER_GEWAEHLTE_ORDNER_EXISTIERT_NICHT_) + "\n"
                  + pathAndFile.getPath() + "\n"
                  + translator.realisticTranslate(app, 
                        Translation.BITTE_WAEHLEN_SIE_EINEN_EXISTIERENDEN_ORDNER_)
                  + "\n" + translator.realisticTranslate(app, Translation.DANKE_),
                  translator.realisticTranslate(app, Translation.NACHRICHT),
                  JOptionPane.CLOSED_OPTION);
            return null;
         }

         if (testIfFileExists(pathAndFile.getPathFile()))
         {
            int answer = JOptionPane.showConfirmDialog(this,
                  translator.realisticTranslate(app, 
                        Translation.DIE_DATEI_EXISTIERT_SCHON)
                        + "\n"
                        + translator.realisticTranslate(app, 
                              Translation.SOLL_SIE_UEBERSCHRIEBEN_WERDEN_),
                  translator.realisticTranslate(app, Translation.FRAGE),
                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (JOptionPane.OK_OPTION != answer)
            {
               return null;
            }
         }

         if (testIfFileExists(pathAndFile.getPathFile() + ".zip"))
         {
            int answer = JOptionPane.showConfirmDialog(this,
                  translator.realisticTranslate(app, 
                        Translation.DIE_DATEI_EXISTIERT_SCHON)
                        + "\n"
                        + translator.realisticTranslate(app, 
                              Translation.SOLL_SIE_UEBERSCHRIEBEN_WERDEN_),
                  translator.realisticTranslate(app, Translation.FRAGE),
                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (JOptionPane.OK_OPTION != answer)
            {
               return null;
            }
         }

         if (testIfFileExists(pathAndFile.getPathFile() + ".ZIP"))
         {
            int answer = JOptionPane.showConfirmDialog(this,
                  translator.realisticTranslate(app, 
                        Translation.DIE_DATEI_EXISTIERT_SCHON)
                        + "\n"
                        + translator.realisticTranslate(app, 
                              Translation.SOLL_SIE_UEBERSCHRIEBEN_WERDEN_),
                  translator.realisticTranslate(app, Translation.FRAGE),
                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (JOptionPane.OK_OPTION != answer)
            {
               return null;
            }
         }
         return pathAndFile;
      }
      return null;
   }

   private String choosesFolderOrZipFileForOpen(App app)
   {
      JFileChooser folderChooser = new JFileChooser(
            app.settings.getExpressionPath());
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
            JOptionPane.showMessageDialog(this, translator.realisticTranslate(app, 
                  Translation.DER_GEWAEHLTE_ORDNER_DIE_DATEI_EXISTIERT_NICHT_)
                  + "\n"
                  + translator.realisticTranslate(app, 
                        Translation.ODER_DIE_DATEI_IST_KEINE_ZIP_DATEI)
                  + "\n" + folderChooser.getSelectedFile().getPath() + "\n"
                  + translator.realisticTranslate(app, 
                        Translation.BITTE_WAEHLEN_SIE_EINEN_EXISTIERENDEN_ORDNER_BZW__ZIP_DATEI_)
                  + "\n" + translator.realisticTranslate(app, Translation.DANKE_),
                  translator.realisticTranslate(app, Translation.NACHRICHT),
                  JOptionPane.CLOSED_OPTION);
            return null;
         }
      }

      return null;
   }
}
