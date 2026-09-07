package vokabeltrainer.panels.letterpicture;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.JTextComponent;

import vokabeltrainer.common.ApplicationSpecialPanels;
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.editing.SingleLetterDocument;
import vokabeltrainer.keyboards.KeyboardHebrewStandard;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.list.editor.CerebrummiFocusTraversalPolicy;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class LetterPictureAlphabetPanel extends JPanel
{
   private static final String SHUFFLE = "SHUFFLE";

   private static final String REGULAR = "REGULAR";

   private static final String SCHREIBSCHRIFT = "SCHREIBSCHRIFT";

   private static final String DRUCKSCHRIFT = "DRUCKSCHRIFT";

   private static final long serialVersionUID = 2284393162989380186L;

   private final NikudLetter[] keys1 = { NikudLetter.CHET, NikudLetter.SSAIN,
         NikudLetter.WAW, NikudLetter.HAEI, NikudLetter.DALET,
         NikudLetter.GIMEL, NikudLetter.BET, NikudLetter.ALEF };

   private final NikudLetter[] keys2 = { NikudLetter.NUN, NikudLetter.MEMSSOFIT,
         NikudLetter.MEM, NikudLetter.LAMED, NikudLetter.CHAFSSOFIT,
         NikudLetter.KAF, NikudLetter.JOD, NikudLetter.TET };

   private final NikudLetter[] keys3 = { NikudLetter.KUF,
         NikudLetter.ZADISSOFIT, NikudLetter.ZADI, NikudLetter.FAEISSOFIT,
         NikudLetter.PAEI, NikudLetter.AIN, NikudLetter.SSAMECH,
         NikudLetter.NUNSSOFIT };

   private final NikudLetter[] keys4 = { NikudLetter.TAW, NikudLetter.SCHIN,
         NikudLetter.RESCH };

   private Translator translator;

   private LetterTextField alef;
   private LetterTextField wet;
   private LetterTextField gimel;
   private LetterTextField dalet;
   private LetterTextField haei;
   private LetterTextField waw;
   private LetterTextField ssain;
   private LetterTextField chet;
   private LetterTextField tet;
   private LetterTextField jod;
   private LetterTextField chaf;
   private LetterTextField chafssofit;
   private LetterTextField lamed;
   private LetterTextField mem;
   private LetterTextField memssofit;
   private LetterTextField nun;
   private LetterTextField nunssofit;
   private LetterTextField ssamech;
   private LetterTextField ain;
   private LetterTextField faei;
   private LetterTextField faeissofit;
   private LetterTextField zadi;
   private LetterTextField zadissofit;
   private LetterTextField kuf;
   private LetterTextField resch;
   private LetterTextField schin;
   private LetterTextField taw;

   private LetterTextField[] textFields1;
   private LetterTextField[] textFields2;
   private LetterTextField[] textFields3;
   private LetterTextField[] textFields4;

   private List<JTextComponent> textFields;

   Component[] focusList;

   JRadioButton printLettersButton;
   JRadioButton handwrittenLettersButton;
   ButtonGroup switchButtonGroup = new ButtonGroup();

   JRadioButton keyboardRegularButton;
   JRadioButton keyboardShuffleButton;
   ButtonGroup keyboardButtonGroup = new ButtonGroup();

   FocusTraversalPolicy focusTraversalPolicy;

   private JPanel keyboardPanel;

   private CardLayout cardLayout;

   private KeyboardHebrewStandard keyboardPrint;

   private KeyboardHebrewStandard keyboardHandwritten;

   public LetterPictureAlphabetPanel(App app, Common common)
   {
      translator = common.getTranslator();

      alef = new LetterTextField(app, NikudLetter.ALEF);
      wet = new LetterTextField(app, NikudLetter.BET);
      gimel = new LetterTextField(app, NikudLetter.GIMEL);
      dalet = new LetterTextField(app, NikudLetter.DALET);
      haei = new LetterTextField(app, NikudLetter.HAEI);
      waw = new LetterTextField(app, NikudLetter.WAW);
      ssain = new LetterTextField(app, NikudLetter.SSAIN);
      chet = new LetterTextField(app, NikudLetter.CHET);
      tet = new LetterTextField(app, NikudLetter.TET);
      jod = new LetterTextField(app, NikudLetter.JOD);
      chaf = new LetterTextField(app, NikudLetter.KAF);
      chafssofit = new LetterTextField(app, NikudLetter.CHAFSSOFIT);
      lamed = new LetterTextField(app, NikudLetter.LAMED);
      mem = new LetterTextField(app, NikudLetter.MEM);
      memssofit = new LetterTextField(app, NikudLetter.MEMSSOFIT);
      nun = new LetterTextField(app, NikudLetter.NUN);
      nunssofit = new LetterTextField(app, NikudLetter.NUNSSOFIT);
      ssamech = new LetterTextField(app, NikudLetter.SSAMECH);
      ain = new LetterTextField(app, NikudLetter.AIN);
      faei = new LetterTextField(app, NikudLetter.PAEI);
      faeissofit = new LetterTextField(app, NikudLetter.FAEISSOFIT);
      zadi = new LetterTextField(app, NikudLetter.ZADI);
      zadissofit = new LetterTextField(app, NikudLetter.ZADISSOFIT);
      kuf = new LetterTextField(app, NikudLetter.KUF);
      resch = new LetterTextField(app, NikudLetter.RESCH);
      schin = new LetterTextField(app, NikudLetter.SCHIN);
      taw = new LetterTextField(app, NikudLetter.TAW);

      textFields1 = new LetterTextField[] { chet, ssain, waw, haei, dalet,
            gimel, wet, alef };
      textFields2 = new LetterTextField[] { nun, memssofit, mem, lamed,
            chafssofit, chaf, jod, tet };
      textFields3 = new LetterTextField[] { kuf, zadissofit, zadi, faeissofit,
            faei, ain, ssamech, nunssofit };
      textFields4 = new LetterTextField[] { taw, schin, resch };
      
      focusList = new Component[]{ alef, wet, gimel, dalet, haei, waw, ssain, chet,
            tet, jod, chaf, chafssofit, lamed, mem, memssofit, nun, nunssofit,
            ssamech, ain, faei, faeissofit, zadi, zadissofit, kuf, resch, schin,
            taw };

      printLettersButton = new JRadioButton(
            translator.realisticTranslate(app, Translation.DRUCKSCHRIFT));
      handwrittenLettersButton = new JRadioButton(
            translator.realisticTranslate(app, Translation.SCHREIBSCHRIFT));
      keyboardRegularButton = new JRadioButton(
            translator.realisticTranslate(app, Translation.TASTATUR_REGULAER));
      keyboardShuffleButton = new JRadioButton(
            translator.realisticTranslate(app, Translation.TASTATUR_VERMISCHT));

      printLettersButton.setBackground(app.appColors.alefbet.getButton());
      printLettersButton
            .setForeground(app.appColors.alefbet.getButtonForeground());

      handwrittenLettersButton.setBackground(app.appColors.alefbet.getButton());
      handwrittenLettersButton
            .setForeground(app.appColors.alefbet.getButtonForeground());

      keyboardRegularButton.setBackground(app.appColors.alefbet.getButton());
      keyboardRegularButton
            .setForeground(app.appColors.alefbet.getButtonForeground());

      keyboardShuffleButton.setBackground(app.appColors.alefbet.getButton());
      keyboardShuffleButton
            .setForeground(app.appColors.alefbet.getButtonForeground());

      textFields = new ArrayList<>();
      Map<NikudLetter, LetterPictureButtonPanel> panels = ApplicationSpecialPanels
            .getLetterPicturesPanelMap();
      this.setOpaque(true);
      setBackground(app.appColors.alefbet.getButton());
      setForeground(app.appColors.alefbet.getButtonForeground());
      this.setLayout(new TotemLayout(this, 15));
      this.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));

      JPanel row1 = new JPanel();
      row1.setLayout(new TrainLayout(row1, 15));
      row1.setOpaque(false);

      for (int i = 0; i < keys1.length; i++)
      {
         JPanel column = new JPanel();
         column.setLayout(new TotemLayout(column));
         column.setBackground(app.appColors.alefbet.getPanelBackground());
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
         column.setBackground(app.appColors.alefbet.getPanelBackground());
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
         column.setBackground(app.appColors.alefbet.getPanelBackground());
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

      filler.setMinimumSize(new Dimension(310, 50));
      filler.setMaximumSize(new Dimension(310, 50));

      JButton turnButton = new JButton(
            translator.realisticTranslate(app, Translation.ALLE_UMDREHEN));
      turnButton.setBackground(app.appColors.alefbet.getButton());
      turnButton.setForeground(app.appColors.alefbet.getButtonForeground());
      turnButton.setFont(app.appFonts.buttonFont);
      turnButton.setIcon(new ImageIcon(app.appImages.getTurn()));
      turnButton.addActionListener(_ -> {
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
         column.setBackground(app.appColors.alefbet.getPanelBackground());
         column.add(panels.get(keys4[i]));
         column.add(textFields4[i]);
         textFields.add(textFields4[i]);
         row4.add(column);
      }
      this.add(row4);

      for (JTextComponent jtc : textFields)
      {
         jtc.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 14));
         jtc.setMinimumSize(new Dimension(50, 40));
         jtc.setMaximumSize(new Dimension(50, 40));
         jtc.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         jtc.setDocument(new SingleLetterDocument());
      }

      JPanel keyboardPrintPanel = new JPanel(new BorderLayout());
      keyboardPrintPanel.setOpaque(true);
      keyboardPrintPanel
            .setBackground(app.appColors.alefbet.getKeyboardBackground());
      keyboardPrint = new KeyboardHebrewStandard(app, null, textFields, 15,
            false);
      keyboardPrintPanel.add(keyboardPrint, BorderLayout.CENTER);

      JPanel keyboardHandwrittenPanel = new JPanel(new BorderLayout());
      keyboardHandwrittenPanel.setOpaque(true);
      keyboardHandwrittenPanel
            .setBackground(app.appColors.alefbet.getKeyboardBackground());
      keyboardHandwritten = new KeyboardHebrewStandard(app, null, textFields,
            15, true);
      keyboardHandwrittenPanel.add(keyboardHandwritten, BorderLayout.CENTER);

      cardLayout = new CardLayout();
      keyboardPanel = new JPanel(cardLayout);
      keyboardPanel.add(DRUCKSCHRIFT, keyboardPrintPanel);
      keyboardPanel.add(SCHREIBSCHRIFT, keyboardHandwrittenPanel);
      keyboardPanel.setMinimumSize(new Dimension(501, 150));
      keyboardPanel.setMaximumSize(new Dimension(530, 200));
      keyboardPanel.setBackground(app.appColors.alefbet.getPanelBackground());

      this.add(keyboardPanel);

      printLettersButton.addActionListener(_ -> setWriting(app, DRUCKSCHRIFT));
      printLettersButton.setBackground(app.appColors.alefbet.getButton());
      printLettersButton
            .setForeground(app.appColors.alefbet.getTextForeground());

      handwrittenLettersButton
            .addActionListener(_ -> setWriting(app, SCHREIBSCHRIFT));
      handwrittenLettersButton.setBackground(app.appColors.alefbet.getButton());
      handwrittenLettersButton
            .setForeground(app.appColors.alefbet.getTextForeground());

      switchButtonGroup.add(printLettersButton);
      switchButtonGroup.add(handwrittenLettersButton);

      keyboardRegularButton.addActionListener(_ -> setKeyboard(app, REGULAR));
      keyboardRegularButton.setBackground(app.appColors.alefbet.getButton());
      keyboardRegularButton
            .setForeground(app.appColors.alefbet.getTextForeground());

      keyboardShuffleButton.addActionListener(_ -> setKeyboard(app, SHUFFLE));
      keyboardShuffleButton.setBackground(app.appColors.alefbet.getButton());
      keyboardShuffleButton
            .setForeground(app.appColors.alefbet.getTextForeground());

      keyboardButtonGroup.add(keyboardRegularButton);
      keyboardButtonGroup.add(keyboardShuffleButton);

      JPanel buttonPanel = new JPanel();
      TrainLayout buttonPanelLayout = new TrainLayout(buttonPanel, 30);
      buttonPanel.setOpaque(true);
      buttonPanel.setBackground(app.appColors.alefbet.getPanelBackground());
      buttonPanel.setLayout(buttonPanelLayout);

      JPanel letterSwitchPanel = new JPanel();
      TotemLayout letterSwitchPanelLayout = new TotemLayout(letterSwitchPanel,
            15);
      letterSwitchPanel.setOpaque(true);
      letterSwitchPanel
            .setBackground(app.appColors.alefbet.getPanelBackground());
      letterSwitchPanel.setBorder(BorderFactory
            .createLineBorder(app.appColors.alefbet.getPanelBackground(), 5));
      letterSwitchPanel.setLayout(letterSwitchPanelLayout);

      JPanel keyboardShufflePanel = new JPanel();
      TotemLayout keyboardShufflePanelLayout = new TotemLayout(
            keyboardShufflePanel, 15);
      keyboardShufflePanel.setOpaque(true);
      keyboardShufflePanel
            .setBackground(app.appColors.alefbet.getPanelBackground());
      keyboardShufflePanel.setBorder(BorderFactory
            .createLineBorder(app.appColors.alefbet.getPanelBackground(), 5));
      keyboardShufflePanel.setLayout(keyboardShufflePanelLayout);

      letterSwitchPanel.add(printLettersButton);
      letterSwitchPanel.add(handwrittenLettersButton);
      printLettersButton.setSelected(true);

      keyboardShufflePanel.add(keyboardRegularButton);
      keyboardShufflePanel.add(keyboardShuffleButton);
      keyboardRegularButton.setSelected(true);

      buttonPanel.add(letterSwitchPanel);
      buttonPanel.add(keyboardShufflePanel);
      JPanel filler2 = new JPanel();
      filler2.setOpaque(false);
      buttonPanel.add(filler2);

      this.add(buttonPanel);

      setWriting(app, switchButtonGroup.getSelection().getActionCommand());

      this.focusTraversalPolicy = new CerebrummiFocusTraversalPolicy(focusList);
   }

   private void setKeyboard(App app, String actionCommand)
   {
      if (REGULAR == actionCommand)
      {
         keyboardPrint.makeRegularKeyboard(app);
         keyboardHandwritten.makeRegularKeyboard(app);
      }
      else
      {
         keyboardPrint.shuffleKeyboard(app);
         keyboardHandwritten.shuffleKeyboard(app);
      }
   }

   private void setWriting(App app, String actionCommand)
   {
      if (SCHREIBSCHRIFT == actionCommand)
      {
         for (JTextComponent jtc : textFields)
         {
            jtc.setFont(app.appFonts.hebrewHandwrittenFont.deriveFont(30f));
         }
         cardLayout.show(keyboardPanel, SCHREIBSCHRIFT);
      }
      else
      {
         for (JTextComponent jtc : textFields)
         {
            jtc.setFont(app.appFonts.hebrewFont.deriveFont(30f));
         }
         cardLayout.show(keyboardPanel, DRUCKSCHRIFT);
      }
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
