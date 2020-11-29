package vokabeltrainer.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;

import vokabeltrainer.ApplicationSound;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.common.SaveTraining;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class SettingsPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = 1162776912036921483L;
   private JSlider soundslider;
   private JButton soundButton;
   private JButton waveSoundButton;
   private JButton clappingSoundButton;
   private JButton splotchSoundButton;
   private JButton shredderSoundButton;
   private JFileChooser folderChooser;
   private JButton folderChooserButton;
   private JButton folderChooserButton2;
   private JLabel folderLabel;

   public SettingsPanel()
   {
      setLayout(new BullsEyeLayout(this));

      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(false);
      tabbedPane.setFont(Main.getGermanFont(16F));
      tabbedPane.addTab("Einstellungen", initSettingsTab());
      tabbedPane.addTab("Impressum", initImpressumTab());
      tabbedPane.addTab("Nachweise", initLicencingTab());

      add(tabbedPane);

      initController();
   }  

   private Component initLicencingTab()
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      return panel;
   }

   private Component initImpressumTab()
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      return panel;
   }

   private Component initSettingsTab()
   {
      JPanel panel = new JPanel();
      BullsEyeLayout panelLayout = new BullsEyeLayout(panel);
      panel.setLayout(panelLayout);

      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 15);
      horizontal.setLayout(horizontalLayout);

      JPanel vertical1 = new JPanel();
      TotemLayout vertical1Layout = new TotemLayout(vertical1, 15);
      vertical1.setLayout(vertical1Layout);

      JPanel vertical2 = new JPanel();
      TotemLayout vertical2Layout = new TotemLayout(vertical2, 15);
      vertical2.setLayout(vertical2Layout);

      JPanel vertical3 = new JPanel();
      TotemLayout vertical3Layout = new TotemLayout(vertical3, 15);
      vertical3.setLayout(vertical3Layout);

      vertical1.add(initSoundPanel());

      vertical2.add(initSavePanel());
      vertical2.add(initImportPanel());
      vertical2.add(initExportPanel());

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
      
      folderLabel = new JLabel(Settings.getExpressionPath());
      folderLabel.setFont(Settings.getButtonFont());

      folderChooserButton = new JButton("ändern mit Speichern");
      folderChooserButton.setFont(Settings.getButtonFont());
      folderChooserButton.setToolTipText("Behält die aktuellen Vokabeln und speichert sie am neuen Ort. Vorsicht! Alle Vokabeln die am neuen Ort schon waren, gehen verloren!");
      
      folderChooserButton2 = new JButton("ändern ohne Speichern");
      folderChooserButton2.setFont(Settings.getButtonFont());
      folderChooserButton2.setToolTipText("Lädt alle Vokabeln, die am neuen Ort schon vorhanden sind. Beläßt die aktuellen Vokabeln am alten Ort.");

      vertical.add(saverLabel);
      vertical.add(folderLabel);
      vertical.add(folderChooserButton);
      vertical.add(folderChooserButton2);

      return vertical;
   }

   private Component initExportPanel()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JLabel exportLabel = new JLabel("Export");
      exportLabel.setFont(Main.getGermanFont(30F));
      exportLabel.setForeground(Settings.getGold());

      vertical.add(exportLabel);

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

      vertical.add(importLabel);

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
      soundslider.setMinimumSize(new Dimension(300, 60));
      soundslider.setMaximumSize(new Dimension(300, 60));

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

      folderChooserButton.addActionListener(event -> {
         folderChooser = new JFileChooser(Settings.getExpressionPath());
         folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         int returnVal = folderChooser.showOpenDialog(this);

         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            Settings.setChoosenExpressionPath(
                  folderChooser.getSelectedFile().getPath());
            
            this.folderLabel.setText(Settings.getExpressionPath());
            
            new SwingWorker<Void, Void>(){

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
      
      folderChooserButton2.addActionListener(event -> {
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
   }
}

/*
 * textPanel.add(new JLabel("Ausschluss von Haftung")); textPanel.add(new
 * JLabel("Es wird keine Haftung übernommen.")); textPanel.add(new
 * JLabel("Dieses Programm darf nur auf eigenes")); textPanel.add(new
 * JLabel("Risiko installiert und betrieben werden.")); textPanel.add(new
 * JLabel("")); textPanel.add(new
 * JLabel("ClappingSound: Attribution License, https://freesound.org/people/ebcrosby/sounds/339483/"
 * )); textPanel.add(new
 * JLabel("WaveSound: Creative Commons License, https://freesound.org/people/florianreichelt/sounds/450755/"
 * )); textPanel.add(new JLabel("")); textPanel.add(new
 * JLabel("Alle Bilder sind von pixelbay.com"));
 */

/*
 * ClappingSound: Attribution License: Attribution — You must give appropriate
 * credit, provide a link to the license, and indicate if changes were made. You
 * may do so in any reasonable manner, but not in any way that suggests the
 * licensor endorses you or your use.
 * https://freesound.org/people/ebcrosby/sounds/339483/
 */

/*
 * WaveSound: Creative Commons License.: No Copyright The person who associated
 * a work with this deed has dedicated the work to the public domain by waiving
 * all of his or her rights to the work worldwide under copyright law, including
 * all related and neighboring rights, to the extent allowed by law. You can
 * copy, modify, distribute and perform the work, even for commercial purposes,
 * all without asking permission.
 * https://freesound.org/people/florianreichelt/sounds/450755/
 */