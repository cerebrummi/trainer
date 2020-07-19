package vokabeltrainer.panels;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.common.Main;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class SettingsPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = 1162776912036921483L;
   
   public SettingsPanel ()
   {
      setLayout(new TrainLayout(this));

      JPanel filler = new JPanel();
      filler.setPreferredSize(new Dimension(100, 200));
      filler.setOpaque(false);          
      
      JPanel textPanel = new JPanel();
      textPanel.setLayout(new TotemLayout(textPanel, 15));
      
      JPanel filler3 = new JPanel();
      filler3.setPreferredSize(new Dimension(100, 60));
      filler3.setOpaque(false);
      
      JLabel title = new JLabel("Cerebrummi© Ivrit/Hebräisch");
      title.setFont(Main.getGermanFont(30F));
      
      JLabel from = new JLabel("von Birke Heeren");
      from.setFont(Main.getGermanFont(20F));
      
      JLabel email = new JLabel("kontakt@greifintegra.de");
      email.setFont(Main.getGermanFont(20F));
      
      JPanel filler4 = new JPanel();
      filler4.setPreferredSize(new Dimension(100, 60));
      filler4.setOpaque(false);
      
      textPanel.add(filler3);
      textPanel.add(title);
      textPanel.add(from);
      textPanel.add(email);
      textPanel.add(new JLabel(""));
      textPanel.add(new JLabel("Ausschluss von Haftung"));
      textPanel.add(new JLabel("Es wird keine Haftung übernommen."));
      textPanel.add(new JLabel("Dieses Programm darf nur auf eigenes"));
      textPanel.add(new JLabel("Risiko installiert und betrieben werden."));
      textPanel.add(new JLabel(""));
      textPanel.add(new JLabel("ClappingSound: Attribution License, https://freesound.org/people/ebcrosby/sounds/339483/"));
      textPanel.add(new JLabel("WaveSound: Creative Commons License, https://freesound.org/people/florianreichelt/sounds/450755/"));
      textPanel.add(new JLabel(""));
      textPanel.add(new JLabel("Alle Bilder sind von pixelbay.com"));
      
      
      /*
       * ClappingSound: Attribution License: Attribution — You must give
       * appropriate credit, provide a link to the license, and indicate if changes
       * were made. You may do so in any reasonable manner, but not in any way that
       * suggests the licensor endorses you or your use.
       * https://freesound.org/people/ebcrosby/sounds/339483/
       */

      /*
       * WaveSound: Creative Commons License.: No Copyright The person who
       * associated a work with this deed has dedicated the work to the public
       * domain by waiving all of his or her rights to the work worldwide under
       * copyright law, including all related and neighboring rights, to the extent
       * allowed by law. You can copy, modify, distribute and perform the work,
       * even for commercial purposes, all without asking permission.
       * https://freesound.org/people/florianreichelt/sounds/450755/
       */
      
      textPanel.add(filler4);
      
      JPanel filler2 = new JPanel();
      filler2.setPreferredSize(new Dimension(100, 200));
      filler2.setOpaque(false);

      add(filler);
      add(textPanel);
      add(filler2); 
   }
}
