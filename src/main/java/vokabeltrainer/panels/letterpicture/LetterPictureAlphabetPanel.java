package vokabeltrainer.panels.letterpicture;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.ApplicationSpecialPanels;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.HebrewLetter;
import vokabeltrainer.editing.SingleLetterDocument;
import vokabeltrainer.keyboards.KeyboardHebrewStandard;
import vokabeltrainer.table.list.editor.CerebrummiFocusTraversalPolicy;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class LetterPictureAlphabetPanel extends JPanel
{
   private static final long serialVersionUID = 2284393162989380186L;

   private final HebrewLetter[] keys1 = { HebrewLetter.CHET, HebrewLetter.SSAIN,
         HebrewLetter.WAW, HebrewLetter.HAEI, HebrewLetter.DALET,
         HebrewLetter.GIMEL, HebrewLetter.WET, HebrewLetter.BET,
         HebrewLetter.ALEF };

   private final HebrewLetter[] keys2 = { HebrewLetter.NUN, HebrewLetter.MEMSSOFIT,
         HebrewLetter.MEM, HebrewLetter.LAMED, HebrewLetter.CHAFSSOFIT,
         HebrewLetter.CHAF, HebrewLetter.KAF, HebrewLetter.JOD,
         HebrewLetter.TET };

   private final HebrewLetter[] keys3 = { HebrewLetter.KUF, HebrewLetter.ZADISSOFIT,
         HebrewLetter.ZADI, HebrewLetter.FAEISSOFIT, HebrewLetter.FAEI,
         HebrewLetter.PAEI, HebrewLetter.AIN, HebrewLetter.SSAMECH,
         HebrewLetter.NUNSSOFIT };

   private final HebrewLetter[] keys4 = { HebrewLetter.TAW, HebrewLetter.SSIN,
         HebrewLetter.SCHIN, HebrewLetter.RESCH };

   private LetterTextField alef = new LetterTextField(HebrewLetter.ALEF);
   private LetterTextField bet = new LetterTextField(HebrewLetter.BET);
   private LetterTextField wet = new LetterTextField(HebrewLetter.WET);
   private LetterTextField gimel = new LetterTextField(HebrewLetter.GIMEL);
   private LetterTextField dalet = new LetterTextField(HebrewLetter.DALET);
   private LetterTextField haei = new LetterTextField(HebrewLetter.HAEI);
   private LetterTextField waw = new LetterTextField(HebrewLetter.WAW);
   private LetterTextField ssain = new LetterTextField(HebrewLetter.SSAIN);
   private LetterTextField chet = new LetterTextField(HebrewLetter.CHET);
   private LetterTextField tet = new LetterTextField(HebrewLetter.TET);
   private LetterTextField jod = new LetterTextField(HebrewLetter.JOD);
   private LetterTextField kaf = new LetterTextField(HebrewLetter.KAF);
   private LetterTextField chaf = new LetterTextField(HebrewLetter.CHAF);
   private LetterTextField chafssofit = new LetterTextField(
         HebrewLetter.CHAFSSOFIT);
   private LetterTextField lamed = new LetterTextField(HebrewLetter.LAMED);
   private LetterTextField mem = new LetterTextField(HebrewLetter.MEM);
   private LetterTextField memssofit = new LetterTextField(
         HebrewLetter.MEMSSOFIT);
   private LetterTextField nun = new LetterTextField(HebrewLetter.NUN);
   private LetterTextField nunssofit = new LetterTextField(
         HebrewLetter.NUNSSOFIT);
   private LetterTextField ssamech = new LetterTextField(HebrewLetter.SSAMECH);
   private LetterTextField ain = new LetterTextField(HebrewLetter.AIN);
   private LetterTextField paei = new LetterTextField(HebrewLetter.PAEI);
   private LetterTextField faei = new LetterTextField(HebrewLetter.FAEI);
   private LetterTextField faeissofit = new LetterTextField(
         HebrewLetter.FAEISSOFIT);
   private LetterTextField zadi = new LetterTextField(HebrewLetter.ZADI);
   private LetterTextField zadissofit = new LetterTextField(
         HebrewLetter.ZADISSOFIT);
   private LetterTextField kuf = new LetterTextField(HebrewLetter.KUF);
   private LetterTextField resch = new LetterTextField(HebrewLetter.RESCH);
   private LetterTextField schin = new LetterTextField(HebrewLetter.SCHIN);
   private LetterTextField ssin = new LetterTextField(HebrewLetter.SSIN);
   private LetterTextField taw = new LetterTextField(HebrewLetter.TAW);

   private LetterTextField[] textFields1 = { chet, ssain, waw, haei, dalet,
         gimel, wet, bet, alef };
   private LetterTextField[] textFields2 = { nun, memssofit, mem, lamed,
         chafssofit, chaf, kaf, jod, tet };
   private LetterTextField[] textFields3 = { kuf, zadissofit, zadi, faeissofit,
         faei, paei, ain, ssamech, nunssofit };
   private LetterTextField[] textFields4 = { taw, ssin, schin, resch };

   private List<JTextComponent> textFields;

   Component[] focusList = { alef, bet, wet, gimel, dalet, haei, waw, ssain,
         chet, tet, jod, kaf, chaf, chafssofit, lamed, mem, memssofit, nun,
         nunssofit, ssamech, ain, paei, faei, faeissofit, zadi, zadissofit, kuf,
         resch, schin, ssin, taw };

   FocusTraversalPolicy focusTraversalPolicy;

   public LetterPictureAlphabetPanel()
   {
      textFields = new ArrayList<>();
      Map<HebrewLetter, LetterPictureButtonPanel> panels = ApplicationSpecialPanels
            .getLetterPicturesPanelMap();
      this.setOpaque(false);
      this.setLayout(new TotemLayout(this, 15));
      this.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));

      JPanel row1 = new JPanel();
      row1.setLayout(new TrainLayout(row1, 15));
      row1.setOpaque(false);

      for (int i = 0; i < keys1.length; i++)
      {
         JPanel column = new JPanel();
         column.setLayout(new TotemLayout(column));
         column.add(panels.get(keys1[i]));
         column.add(textFields1[i]);
         textFields.add(textFields1[i]);
         row1.add(column);
      }
      this.add(row1);

      JPanel row2 = new JPanel();
      row2.setLayout(new TrainLayout(row2, 15));
      row2.setOpaque(false);

      for (int i = 0; i < keys2.length; i++)
      {
         JPanel column = new JPanel();
         column.setLayout(new TotemLayout(column));
         column.add(panels.get(keys2[i]));
         column.add(textFields2[i]);
         textFields.add(textFields2[i]);
         row2.add(column);
      }
      this.add(row2);

      JPanel row3 = new JPanel();
      row3.setLayout(new TrainLayout(row3, 15));
      row3.setOpaque(false);

      for (int i = 0; i < keys3.length; i++)
      {
         JPanel column = new JPanel();
         column.setLayout(new TotemLayout(column));
         column.add(panels.get(keys3[i]));
         column.add(textFields3[i]);
         textFields.add(textFields3[i]);
         row3.add(column);
      }
      this.add(row3);

      JPanel row4 = new JPanel();
      row4.setLayout(new TrainLayout(row4, 15));
      row4.setOpaque(false);

      JPanel filler = new JPanel(new FlowLayout());
      filler.setOpaque(false);

      filler.setMinimumSize(new Dimension(250, 50));
      filler.setMaximumSize(new Dimension(400, 50));

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

      row4.add(filler);

      for (int i = 0; i < keys4.length; i++)
      {
         JPanel column = new JPanel();
         column.setLayout(new TotemLayout(column));
         column.add(panels.get(keys4[i]));
         column.add(textFields4[i]);
         textFields.add(textFields4[i]);
         row4.add(column);
      }
      this.add(row4);

      JPanel keyboardPanel = new JPanel(new BorderLayout());
      keyboardPanel.setMinimumSize(new Dimension(530, 200));
      keyboardPanel.setMaximumSize(new Dimension(530, 400));

      for (JTextComponent jtc : textFields)
      {
         jtc.setFont(Main.getHebrewFont(30));
         jtc.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 14));
         jtc.setMinimumSize(new Dimension(50, 40));
         jtc.setMaximumSize(new Dimension(50, 40));
         jtc.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         jtc.setDocument(new SingleLetterDocument());
      }
      KeyboardHebrewStandard keyboard = new KeyboardHebrewStandard(null,
            textFields, 15);
      keyboardPanel.add(keyboard, BorderLayout.CENTER);
      this.add(keyboardPanel);

      this.focusTraversalPolicy = new CerebrummiFocusTraversalPolicy(focusList);
   }

   public List<JTextComponent> getTextFields()
   {
      return textFields;
   }

   @Override
   public FocusTraversalPolicy getFocusTraversalPolicy()
   {
      return focusTraversalPolicy;
   }
}
