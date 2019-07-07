package vokabeltrainer.panels.letterpicture;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.copepod.tonion.TotemLayout;
import de.copepod.tonion.TrainLayout;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.ApplicationSpecialPanels;
import vokabeltrainer.Settings;
import vokabeltrainer.editing.HebrewLetter;

public class LetterPictureAlphabetPanel extends JPanel
{
   private static final long serialVersionUID = 2284393162989380186L;

   HebrewLetter[] keys1 = { HebrewLetter.DALET, HebrewLetter.GIMEL,
         HebrewLetter.WET, HebrewLetter.BET, HebrewLetter.ALEF };

   HebrewLetter[] keys2 = { HebrewLetter.TET, HebrewLetter.CHET,
         HebrewLetter.SSAIN, HebrewLetter.WAW, HebrewLetter.HAEI };

   HebrewLetter[] keys3 = { HebrewLetter.LAMED, HebrewLetter.CHAFSSOFIT,
         HebrewLetter.CHAF, HebrewLetter.KAF, HebrewLetter.JOD };

   HebrewLetter[] keys4 = { HebrewLetter.SSAMECH, HebrewLetter.NUNSSOFIT,
         HebrewLetter.NUN, HebrewLetter.MEMSSOFIT, HebrewLetter.MEM };

   HebrewLetter[] keys5 = { HebrewLetter.ZADI, HebrewLetter.FAEISSOFIT,
         HebrewLetter.FAEI, HebrewLetter.PAEI, HebrewLetter.AIN };

   HebrewLetter[] keys6 = { HebrewLetter.SSIN, HebrewLetter.SCHIN,
         HebrewLetter.RESCH, HebrewLetter.KUF, HebrewLetter.ZADISSOFIT };

   public LetterPictureAlphabetPanel()
   {
      Map<HebrewLetter, LetterPictureButtonPanel> panels = ApplicationSpecialPanels
            .getLetterPicturesPanelMap();
      this.setOpaque(false);
      this.setLayout(new TotemLayout(this, 15));
      this.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));

      JPanel row1 = new JPanel();
      row1.setLayout(new TrainLayout(row1, 15));
      row1.setOpaque(false);

      for (HebrewLetter key : keys1)
      {
         row1.add(panels.get(key));
      }
      this.add(row1);

      JPanel row2 = new JPanel();
      row2.setLayout(new TrainLayout(row2, 15));
      row2.setOpaque(false);

      for (HebrewLetter key : keys2)
      {
         row2.add(panels.get(key));
      }
      this.add(row2);

      JPanel row3 = new JPanel();
      row3.setLayout(new TrainLayout(row3, 15));
      row3.setOpaque(false);

      for (HebrewLetter key : keys3)
      {
         row3.add(panels.get(key));
      }
      this.add(row3);

      JPanel row4 = new JPanel();
      row4.setLayout(new TrainLayout(row4, 15));
      row4.setOpaque(false);

      for (HebrewLetter key : keys4)
      {
         row4.add(panels.get(key));
      }
      this.add(row4);

      JPanel row5 = new JPanel();
      row5.setLayout(new TrainLayout(row5, 15));
      row5.setOpaque(false);

      for (HebrewLetter key : keys5)
      {
         row5.add(panels.get(key));
      }
      this.add(row5);

      JPanel row6 = new JPanel();
      row6.setLayout(new TrainLayout(row6, 15));
      row6.setOpaque(false);

      for (HebrewLetter key : keys6)
      {
         row6.add(panels.get(key));
      }
      this.add(row6);

      JPanel row7 = new JPanel();
      row7.setLayout(new TrainLayout(row7, 15));
      row7.setOpaque(false);

      JPanel filler = new JPanel();
      filler.setOpaque(false);

      JButton turnButton = new JButton("alle umdrehen");
      turnButton.setFont(Settings.getButtonFont());
      turnButton.setIcon(new ImageIcon(ApplicationImages.getTurn()));
      turnButton.addActionListener(event -> {
         for (LetterPictureButtonPanel panel : panels.values())
         {
            panel.nextCard();
         }
      });

      filler.add(turnButton);

      filler.setMinimumSize(new Dimension(200, 50));
      filler.setMinimumSize(new Dimension(245, 50));
      row7.add(filler);
      row7.add(panels.get(HebrewLetter.TAW));
      this.add(row7);
   }
}
