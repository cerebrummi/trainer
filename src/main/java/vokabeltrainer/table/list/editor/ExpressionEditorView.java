package vokabeltrainer.table.list.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.InfoTextField;
import vokabeltrainer.KeyboardHebrew;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.LetterForSaving;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.ExtraInformationDocument;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.HebrewDocument;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableMultiselect;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableRow;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Database;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbConjugation;
import vokabeltrainer.types.grammatical.VerbStrength;
import vokabeltrainer.types.grammatical.VerbType;
import vokabeltrainer.types.grammatical.expressionkind.Definitions;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class ExpressionEditorView extends JDialog
      implements ExpressionEditorViewConnector
{
   private static final int WIDTH_INFO_PANEL = 240;

   private static final int WIDTH_INPUT_PANEL = Settings.getKeyboardWidth();

   private static final long serialVersionUID = 5853498340870217732L;

   private Expression expression;
   private JTextField german;
   private JTextField hebrewInLatin;
   private InfoTextField hebrew;

   private InfoTextField newSearchwordGerman;
   private JList<String> searchwordsJListGerman;
   private Set<String> searchwordsSetGerman;
   private JButton deleteSearchwordButtonGerman;

   private InfoTextField newSearchwordHebrew;
   private JList<String> searchwordsJListHebrew;
   private Set<String> searchwordsSetHebrew;
   private JButton deleteSearchwordButtonHebrew;

   private KeyboardHebrew keyboard;
   private JPanel layout;
   private boolean save;
   private JButton saveButton;
   private JButton restoreButton;
   private JButton cancelButton;
   private List<JTextComponent> components = new ArrayList<>();
   private String germanTitle = "Deutsch";
   private String hebrewInLatinTitle = "Lautschrift";
   private String searchwordJListGermanTitle = "Deutsche Suchwörter";
   private String searchwordsJListHebrewTitle = "Hebräische Suchwörter";
   private String chapterTitle = "Lektion";
   private JComboBox<String> chapter;
   private JTextPane extraInfo;
   private JScrollPane extraInfoScroller;
   private JButton pasteButton;
   private JButton cutButton;
   private JButton copyButton;

   private ExpressionKindTableMultiselect expressionKindTable;

   @SuppressWarnings("unused")
   private ExpressionEditorControllerConnector connector;

   private JPanel binjanBoxPanel;

   private JPanel genderBoxPanel;

   private JPanel grammaticalPersonBoxPanel;

   private JPanel numerusBoxPanel;

   private JPanel verbConjugationBoxPanel;

   private JPanel verbStrengthBoxPanel;

   private JPanel verbTypeBoxPanel;

   private JComboBox<Binjan> binjanBox;

   private JComboBox<Gender> genderBox;

   private JComboBox<GrammaticalPerson> grammaticalPersonBox;

   private JComboBox<Numerus> numerusBox;

   private JComboBox<VerbConjugation> verbConjugationBox;

   private JComboBox<VerbStrength> verbStrengthBox;

   private JComboBox<VerbType> verbTypeBox;

   private JPanel definitionPanel;

   public ExpressionEditorView(ExpressionEditorControllerConnector connector)
   {
      super(Common.getjFrame(), "Cerebrummi©",
            Dialog.ModalityType.APPLICATION_MODAL);
      this.connector = connector;
      save = false;
      setSize(1271, 680);
      layout = new BackgroundPanelTiled();
      layout.setBorder(
            BorderFactory.createLineBorder(Settings.getGreen(), 15, false));
      layout.setLayout(new TrainLayout(layout, 15));
      getContentPane().add(layout);

      initGuiFields();
      initInput();
      initInfosLeft();
      initInfosRight();
      initInfosExtra();

      initController();
      Component[] focusList = { german, hebrewInLatin, hebrew,
            newSearchwordGerman, newSearchwordHebrew, saveButton, restoreButton,
            cancelButton };
      this.setFocusTraversalPolicy(
            new CerebrummiFocusTraversalPolicy(focusList));
   }

   private void initGuiFields()
   {
      Font germanfont = Main.getGermanFont(16F);
      Font hebrewfont = Main.getHebrewFont(30F);

      german = new JTextField();
      german.setBorder(makeBorderBlank(germanTitle));
      german.setFont(germanfont);
      german.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      german.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      german.setDocument(new GermanDocument(true));

      hebrewInLatin = new JTextField();
      hebrewInLatin.setBorder(makeBorderBlank(this.hebrewInLatinTitle));
      hebrewInLatin.setFont(germanfont);
      hebrewInLatin.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      hebrewInLatin.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      hebrewInLatin.setDocument(new GermanDocument(true));

      hebrew = new InfoTextField("Hebräisch", "Bitte hineinklicken oder  ",
            "mit der Tabulatortaste auswählen.  ",
            "Dann hebräische Tastatur benutzen.  ");
      hebrew.setBlankBorder();
      hebrew.setDocument(new HebrewDocument(true));

      newSearchwordGerman = new InfoTextField("Neues Suchwort Deutsch  ",
            "Bitte je ein Wort eingeben  ", "und dann ENTER drücken!  ");
      newSearchwordGerman.setFont(germanfont);
      newSearchwordGerman.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordGerman.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordGerman.setDocument(new GermanDocument(false));

      searchwordsJListGerman = new JList<>();
      searchwordsJListGerman.setCellRenderer(new ListCellRenderer<String>()
      {
         @Override
         public Component getListCellRendererComponent(
               JList<? extends String> list, String value, int index,
               boolean isSelected, boolean cellHasFocus)
         {
            AntiFocusTextField listComponent = new AntiFocusTextField(value);
            listComponent.setFont(Main.getGermanFont(16F));
            if (isSelected)
            {
               listComponent.setBackground(Color.WHITE);
            }
            else
            {
               listComponent.setBackground(Settings.getBackgroundGold());
            }
            return listComponent;
         }
      });
      searchwordsJListGerman.setFocusable(false);
      searchwordsJListGerman
            .setBorder(makeBorderBlank(this.searchwordJListGermanTitle));
      searchwordsJListGerman.setBackground(new Color(223, 210, 198));
      searchwordsJListGerman
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 300));
      searchwordsJListGerman
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

      newSearchwordHebrew = new InfoTextField("Neues Suchwort Hebräisch  ",
            "Bitte hineinklicken.", "Hebräische Tastatur benutzen.",
            "Danach ENTER drücken!  ");
      newSearchwordHebrew.setFont(hebrewfont);
      newSearchwordHebrew
            .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      newSearchwordHebrew.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordHebrew.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordHebrew.setDocument(new HebrewDocument(false));
      this.components.add(newSearchwordHebrew);

      searchwordsJListHebrew = new JList<>();
      searchwordsJListHebrew.setCellRenderer(new ListCellRenderer<String>()
      {
         @Override
         public Component getListCellRendererComponent(
               JList<? extends String> list, String value, int index,
               boolean isSelected, boolean cellHasFocus)
         {
            AntiFocusTextField listComponent = new AntiFocusTextField(value);
            listComponent.setFont(hebrewfont);
            listComponent
                  .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            if (isSelected)
            {
               listComponent.setBackground(Color.WHITE);
            }
            else
            {
               listComponent.setBackground(Settings.getBackgroundGold());
            }
            return listComponent;
         }
      });
      searchwordsJListHebrew.setFocusable(false);
      searchwordsJListHebrew.setFixedCellHeight(50);
      searchwordsJListHebrew
            .setBorder(makeBorderBlank(this.searchwordsJListHebrewTitle));
      searchwordsJListHebrew.setBackground(Settings.getBackgroundGold());
      searchwordsJListHebrew
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 300));
      searchwordsJListHebrew
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

      deleteSearchwordButtonHebrew = new JButton("lösche Suchwort Hebräisch");
      deleteSearchwordButtonHebrew.setFont(Main.getGermanFont(16F));
      deleteSearchwordButtonHebrew
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteSearchwordButtonHebrew
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 40));

      deleteSearchwordButtonGerman = new JButton("lösche Suchwort Deutsch");
      deleteSearchwordButtonGerman.setFocusable(false);
      deleteSearchwordButtonGerman.setFont(Main.getGermanFont(16F));
      deleteSearchwordButtonGerman
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteSearchwordButtonGerman
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 40));

      saveButton = new JButton("anwenden");
      saveButton.setFont(Main.getGermanFont(16F));
      saveButton.setMinimumSize(new Dimension(120, 40));
      saveButton.setMaximumSize(new Dimension(160, 40));

      restoreButton = new JButton("zurücksetzen");
      restoreButton.setFont(Main.getGermanFont(16F));
      restoreButton.setMinimumSize(new Dimension(120, 40));
      restoreButton.setMaximumSize(new Dimension(160, 40));

      cancelButton = new JButton("abbrechen");
      cancelButton.setFont(Main.getGermanFont(16F));
      cancelButton.setMinimumSize(new Dimension(120, 40));
      cancelButton.setMaximumSize(new Dimension(160, 40));

      chapter = new JComboBox<String>();
      chapter.setEditable(true);
      chapter.setMaximumRowCount(20);
      chapter.setBorder(new TitledBorder(this.chapterTitle));
      chapter.setOpaque(false);
      chapter.setBackground(new Color(0, 0, 0, 0));
      chapter.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      chapter.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));

      extraInfo = new JTextPane();
      extraInfo.setFont(Main.getHebrewFont(30));
      extraInfo.setBorder(
            BorderFactory.createTitledBorder("Weitere Informationen"));
      extraInfo.setDocument(new ExtraInformationDocument());
      StyledDocument doc = extraInfo.getStyledDocument();
      SimpleAttributeSet style = new SimpleAttributeSet();
      StyleConstants.setForeground(style, Settings.getDarkGold());
      StyleConstants.setFontSize(style, 20);
      StyleConstants.setFontFamily(style, "Serif");
      doc.setParagraphAttributes(0, doc.getLength(), style, true);

      components.add(extraInfo);
      extraInfoScroller = new JScrollPane(extraInfo);
      extraInfoScroller.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 200));
      extraInfoScroller.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 300));

      pasteButton = new JButton(new DefaultEditorKit.PasteAction());
      pasteButton.setIcon(new ImageIcon(ApplicationImages.getPaste()));
      pasteButton.setText("");
      pasteButton.setToolTipText("Einfügen");

      cutButton = new JButton(new DefaultEditorKit.CutAction());
      cutButton.setIcon(new ImageIcon(ApplicationImages.getCut()));
      cutButton.setText("");
      cutButton.setToolTipText("Ausschneiden");

      copyButton = new JButton(new DefaultEditorKit.CopyAction());
      copyButton.setIcon(new ImageIcon(ApplicationImages.getCopy2()));
      copyButton.setText("");
      copyButton.setToolTipText("Kopieren");

      expressionKindTable = new ExpressionKindTableMultiselect(ExpressionKind.getModel(),
            WIDTH_INFO_PANEL, this);

      binjanBox = new JComboBox<>(Binjan.values());
      binjanBox.setFont(Main.getGermanFont(14F));
      binjanBox.setEditable(false);
      binjanBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      binjanBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      binjanBox.setMaximumRowCount(Binjan.values().length);
      binjanBoxPanel = new JPanel();
      TotemLayout binjanLayout = new TotemLayout(binjanBoxPanel);
      binjanBoxPanel.setLayout(binjanLayout);
      binjanBoxPanel.add(binjanBox);
      binjanBoxPanel.setOpaque(false);
      binjanBoxPanel.setBackground(Settings.getTransparent());
      binjanBoxPanel.setBorder(new TitledBorder("Binjan"));

      genderBox = new JComboBox<>(Gender.values());
      genderBox.setFont(Main.getGermanFont(14F));
      genderBox.setEditable(false);
      genderBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      genderBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      genderBox.setMaximumRowCount(Gender.values().length);
      genderBoxPanel = new JPanel();
      TotemLayout genderLayout = new TotemLayout(genderBoxPanel);
      genderBoxPanel.setLayout(genderLayout);
      genderBoxPanel.add(genderBox);
      genderBoxPanel.setOpaque(false);
      genderBoxPanel.setBackground(Settings.getTransparent());
      genderBoxPanel.setBorder(BorderFactory.createTitledBorder("Geschlecht"));

      grammaticalPersonBox = new JComboBox<>(GrammaticalPerson.values());
      grammaticalPersonBox.setFont(Main.getGermanFont(14F));
      grammaticalPersonBox.setEditable(false);
      grammaticalPersonBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      grammaticalPersonBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      grammaticalPersonBox.setMaximumRowCount(GrammaticalPerson.values().length);
      grammaticalPersonBoxPanel = new JPanel();
      TotemLayout grammaticalPersonLayout = new TotemLayout(
            grammaticalPersonBoxPanel);
      grammaticalPersonBoxPanel.setLayout(grammaticalPersonLayout);
      grammaticalPersonBoxPanel.add(grammaticalPersonBox);
      grammaticalPersonBoxPanel.setOpaque(false);
      grammaticalPersonBoxPanel.setBackground(Settings.getTransparent());
      grammaticalPersonBoxPanel
            .setBorder(BorderFactory.createTitledBorder("Grammatische Person"));

      numerusBox = new JComboBox<>(Numerus.values());
      numerusBox.setFont(Main.getGermanFont(14F));
      numerusBox.setEditable(false);
      numerusBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      numerusBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      numerusBox.setMaximumRowCount(Numerus.values().length);
      numerusBoxPanel = new JPanel();
      TotemLayout numerusLayout = new TotemLayout(numerusBoxPanel);
      numerusBoxPanel.setLayout(numerusLayout);
      numerusBoxPanel.add(numerusBox);
      numerusBoxPanel.setOpaque(false);
      numerusBoxPanel.setBackground(Settings.getTransparent());
      numerusBoxPanel.setBorder(BorderFactory.createTitledBorder("Numerus"));

      verbConjugationBox = new JComboBox<>(VerbConjugation.values());
      verbConjugationBox.setFont(Main.getGermanFont(14F));
      verbConjugationBox.setEditable(false);
      verbConjugationBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      verbConjugationBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      verbConjugationBox.setMaximumRowCount(VerbConjugation.values().length);
      verbConjugationBoxPanel = new JPanel();
      TotemLayout verbConjugationLayout = new TotemLayout(
            verbConjugationBoxPanel);
      verbConjugationBoxPanel.setLayout(verbConjugationLayout);
      verbConjugationBoxPanel.add(verbConjugationBox);
      verbConjugationBoxPanel.setOpaque(false);
      verbConjugationBoxPanel.setBackground(Settings.getTransparent());
      verbConjugationBoxPanel
            .setBorder(BorderFactory.createTitledBorder("VerbConjugation"));

      verbStrengthBox = new JComboBox<>(VerbStrength.values());
      verbStrengthBox.setFont(Main.getGermanFont(14F));
      verbStrengthBox.setEditable(false);
      verbStrengthBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      verbStrengthBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      verbStrengthBox.setMaximumRowCount(VerbStrength.values().length);
      verbStrengthBoxPanel = new JPanel();
      TotemLayout verbStrengthLayout = new TotemLayout(verbStrengthBoxPanel);
      verbStrengthBoxPanel.setLayout(verbStrengthLayout);
      verbStrengthBoxPanel.add(verbStrengthBox);
      verbStrengthBoxPanel.setOpaque(false);
      verbStrengthBoxPanel.setBackground(Settings.getTransparent());
      verbStrengthBoxPanel
            .setBorder(BorderFactory.createTitledBorder("VerbStärke"));

      verbTypeBox = new JComboBox<>(VerbType.values());
      verbTypeBox.setFont(Main.getGermanFont(14F));
      verbTypeBox.setEditable(false);
      verbTypeBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      verbTypeBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL-10, 50));
      verbTypeBoxPanel = new JPanel();
      TotemLayout verbTypeBoxLayout = new TotemLayout(verbTypeBoxPanel);
      verbTypeBoxPanel.setLayout(verbTypeBoxLayout);
      verbTypeBoxPanel.add(verbTypeBox);
      verbTypeBoxPanel.setOpaque(false);
      verbTypeBoxPanel.setBackground(Settings.getTransparent());
      verbTypeBoxPanel.setBorder(BorderFactory.createTitledBorder("Verb Typ"));

      keyboard = new KeyboardHebrew(hebrew, components, 70, true);
   }

   private TitledBorder makeBorderBlank(String title)
   {
      return new TitledBorder(title);
   }

   private TitledBorder makeBorderRed(String title)
   {
      return new TitledBorder(BorderFactory.createLineBorder(Color.RED, 3),
            title);
   }

   private void initInput()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.add(chapter);
      vertical.add(german);
      vertical.add(hebrewInLatin);
      vertical.add(keyboard);

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.add(saveButton);
      horizontal.add(restoreButton);
      horizontal.add(cancelButton);

      vertical.add(horizontal);
      layout.add(vertical);
   }

   private void initInfosLeft()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));

      JScrollPane scrollPane = new JScrollPane(searchwordsJListGerman);
      scrollPane.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      scrollPane.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

      JScrollPane scrollPane2 = new JScrollPane(searchwordsJListHebrew);
      scrollPane2.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      scrollPane2.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

      vertical.add(newSearchwordGerman);
      vertical.add(scrollPane);
      vertical.add(deleteSearchwordButtonGerman);

      vertical.add(newSearchwordHebrew);
      vertical.add(scrollPane2);
      vertical.add(deleteSearchwordButtonHebrew);

      layout.add(vertical);
   }

   private void initInfosRight()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setBackground(Settings.getTransparent());
      vertical.setLayout(new TotemLayout(vertical, 15));

      JScrollPane scrollPane = new JScrollPane(expressionKindTable);
      scrollPane.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 200));
      scrollPane.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Settings.getLightGrayGold()),
            "Wortarten"));

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setBackground(Settings.getTransparent());
      horizontal.setLayout(new TrainLayout(horizontal, 15));

      horizontal.add(copyButton);
      horizontal.add(cutButton);
      horizontal.add(pasteButton);

      vertical.add(scrollPane);
      vertical.add(extraInfoScroller);
      vertical.add(horizontal);

      layout.add(vertical);
   }

   private void initInfosExtra()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setBackground(Settings.getTransparent());
      vertical.setLayout(new TotemLayout(vertical, 15));

      definitionPanel = new JPanel();
      TotemLayout definitionLayout = new TotemLayout(definitionPanel, 5);
      definitionPanel.setLayout(definitionLayout);
      definitionPanel.setBorder(BorderFactory.createEmptyBorder());
      definitionPanel.setBackground(Settings.getTransparent());
      definitionPanel.setOpaque(false);

      JScrollPane scrollPane2 = new JScrollPane(definitionPanel);
      scrollPane2.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 200));
      scrollPane2.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 600));
      scrollPane2.setBorder(BorderFactory.createEmptyBorder());
      scrollPane2.setViewportBorder(BorderFactory.createEmptyBorder());

      vertical.add(scrollPane2);

      layout.add(vertical);
   }

   private void resetAllBorders()
   {
      chapter.setBorder(makeBorderBlank(this.chapterTitle));
      german.setBorder(makeBorderBlank(this.germanTitle));
      hebrewInLatin.setBorder(makeBorderBlank(this.hebrewInLatinTitle));
      hebrew.setBlankBorder();
      searchwordsJListGerman
            .setBorder(makeBorderBlank(this.searchwordJListGermanTitle));
      searchwordsJListHebrew
            .setBorder(makeBorderBlank(this.searchwordsJListHebrewTitle));
   }

   private void initController()
   {
      german.addActionListener(event -> {
         if (!german.getText().isEmpty())
         {
            german.setBorder(makeBorderBlank(this.germanTitle));
         }
      });

      hebrewInLatin.addActionListener(event -> {
         if (!hebrewInLatin.getText().isEmpty())
         {
            hebrewInLatin.setBorder(makeBorderBlank(this.hebrewInLatinTitle));
         }
      });

      hebrew.addCaretListener(event -> {
         if (!hebrew.getText().isEmpty())
         {
            hebrew.setBlankBorder();
         }
      });

      newSearchwordGerman.addActionListener(event -> {
         String add = newSearchwordGerman.getText().replaceAll(",", "");
         if (!add.isEmpty())
         {
            searchwordsSetGerman.add(add);
            searchwordsJListGerman.setModel(getSearchwordsModelGerman());
            newSearchwordGerman.setText("");
            newSearchwordGerman.requestFocus();
         }
         if (!searchwordsSetGerman.isEmpty())
         {
            searchwordsJListGerman
                  .setBorder(makeBorderBlank(this.searchwordJListGermanTitle));
         }
      });

      deleteSearchwordButtonGerman.addActionListener(event -> {
         if (searchwordsJListGerman.getSelectedValue() != null)
         {
            searchwordsSetGerman
                  .remove(searchwordsJListGerman.getSelectedValue());
            searchwordsJListGerman.setModel(getSearchwordsModelGerman());
         }
      });

      newSearchwordHebrew.addActionListener(event -> {
         String add = newSearchwordHebrew.getText().replaceAll(",", "");
         if (!add.isEmpty())
         {
            searchwordsSetHebrew.add(add);
            searchwordsJListHebrew.setModel(getSearchwordsModelHebrew());
            newSearchwordHebrew.setText("");
            newSearchwordHebrew.requestFocus();
         }
         if (!searchwordsSetHebrew.isEmpty())
         {
            searchwordsJListHebrew
                  .setBorder(makeBorderBlank(this.searchwordsJListHebrewTitle));
         }
      });

      deleteSearchwordButtonHebrew.addActionListener(event -> {
         if (searchwordsJListHebrew.getSelectedValue() != null)
         {
            searchwordsSetHebrew
                  .remove(searchwordsJListHebrew.getSelectedValue());
            searchwordsJListHebrew.setModel(getSearchwordsModelHebrew());
         }
      });

      saveButton.addActionListener(event -> {
         if (testForCompletness())
         {
            save = true;
            saveExpression();
            this.setVisible(false);
         }
      });

      restoreButton.addActionListener(event -> {
         setExpression(expression, false);
         resetAllBorders();
      });

      cancelButton.addActionListener(event -> {
         save = false;
         this.dispose();
      });

   }
   
   public void showGrammaticalEnums(Set<GrammaticalParentEnum> grammaticalEnumsToShow)
   {
      definitionPanel.removeAll();
      if(grammaticalEnumsToShow.contains(GrammaticalParentEnum.GENDER))
      {
         definitionPanel.add(this.genderBoxPanel);
      }
      else
      {
         this.genderBox.setSelectedItem(Gender.GENDER_NA);
      }
      if(grammaticalEnumsToShow.contains(GrammaticalParentEnum.NUMERUS))
      {
         definitionPanel.add(this.numerusBoxPanel);
      }
      else
      {
         this.numerusBox.setSelectedItem(Numerus.NUMERUS_NA);
      }
      if(grammaticalEnumsToShow.contains(GrammaticalParentEnum.GRAMMATICAL_PERSON))
      {
         definitionPanel.add(this.grammaticalPersonBoxPanel);
      }
      else
      {
         this.grammaticalPersonBox.setSelectedItem(GrammaticalPerson.GRAMMATICALPERSON_NA);
      }
      if(grammaticalEnumsToShow.contains(GrammaticalParentEnum.BINJAN))
      {
         definitionPanel.add(this.binjanBoxPanel);
      }
      else
      {
         this.binjanBox.setSelectedItem(Binjan.BINJAN_NA);
      }
      if(grammaticalEnumsToShow.contains(GrammaticalParentEnum.VERB_CONJUGATION))
      {
         definitionPanel.add(this.verbConjugationBoxPanel);
      }
      else
      {
         this.verbConjugationBox.setSelectedItem(VerbConjugation.VERBCONJUGATION_NA);
      }
      if(grammaticalEnumsToShow.contains(GrammaticalParentEnum.VERB_STRENGTH))
      {
         definitionPanel.add(this.verbStrengthBoxPanel);
      }
      else
      {
         verbStrengthBox.setSelectedItem(VerbStrength.VERBSTRENGTH_NA);
      }
      if(grammaticalEnumsToShow.contains(GrammaticalParentEnum.VERB_TYPE))
      {
         definitionPanel.add(this.verbTypeBoxPanel);
      }
      else
      {
         verbTypeBox.setSelectedItem(VerbType.VERBTYPE_NA);
      }
      JPanel filler = new JPanel();
      filler.setMinimumSize(new Dimension(WIDTH_INFO_PANEL-10, 0));
      filler.setMaximumSize(new Dimension(WIDTH_INFO_PANEL-10, 700));
      definitionPanel.add(filler);
      
      definitionPanel.validate();
      definitionPanel.repaint();
   }

   private boolean testForCompletness()
   {
      boolean result = true;
      if (chapter.getSelectedItem() == null)
      {
         chapter.setBorder(makeBorderRed(this.chapterTitle));
         result = false;
      }
      if (german.getText().isEmpty())
      {
         german.setBorder(makeBorderRed(this.germanTitle));
         result = false;
      }
      if (hebrewInLatin.getText().isEmpty())
      {
         hebrewInLatin.setBorder(makeBorderRed(this.hebrewInLatinTitle));
         result = false;
      }
      if (hebrew.getText().isEmpty())
      {
         hebrew.setRedBorder();
         result = false;
      }
      if (searchwordsSetGerman.isEmpty())
      {
         searchwordsJListGerman
               .setBorder(makeBorderRed(this.searchwordJListGermanTitle));
         result = false;
      }
      if (searchwordsSetHebrew.isEmpty())
      {
         searchwordsJListHebrew
               .setBorder(makeBorderRed(this.searchwordsJListHebrewTitle));
         result = false;
      }
      return result;
   }

   private void saveExpression()
   {
      expression.setGerman(cleanTextWithoutComma(german.getText()));
      expression
            .setHebrewInLatin(cleanTextWithoutComma(hebrewInLatin.getText()));
      expression.setHebrew(cleanTextWithoutComma(hebrew.getText()));

      expression.setLetterForSaving(
            LetterForSaving.getLetter(cleanTextWithoutComma(expression.getGerman())));

      Definitions definitions = new Definitions();
      Vector<Vector<ExpressionKindTableRow>> vektorRows = expressionKindTable.getModel().getData();
      for (Vector<ExpressionKindTableRow> vektorRow : vektorRows)
      {
         ExpressionKind expressionKind = vektorRow.get(0).getExpressionKind();
         if (expressionKind.isSelected())
         {
            definitions.addExpressionKind(expressionKind);
            definitions.setGrammaticalEnum(expressionKind,
                  binjanBox.getItemAt(binjanBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind,
                  genderBox.getItemAt(genderBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind, grammaticalPersonBox
                  .getItemAt(grammaticalPersonBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind,
                  numerusBox.getItemAt(numerusBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind, verbConjugationBox
                  .getItemAt(verbConjugationBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind, verbStrengthBox
                  .getItemAt(verbStrengthBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind,
                  verbTypeBox.getItemAt(verbTypeBox.getSelectedIndex()));
         }
      }
      expression.setDefinitions(definitions);

      List<String> wordsGerman = new ArrayList<>();
      for (String word : searchwordsSetGerman)
      {
         wordsGerman.add(cleanText(word));
      }
      expression.setSearchwordsGerman(wordsGerman);
      List<String> wordsHebrew = new ArrayList<>();
      for (String word : searchwordsSetHebrew)
      {
         wordsHebrew.add(cleanText(word));
      }
      expression.setSearchwordsHebrew(wordsHebrew);
      Chapter selfChapter = new Chapter();
      selfChapter.setOrigin(Database.SELF);
      selfChapter
            .setName(cleanTextWithoutComma((String) chapter.getSelectedItem()));
      expression.setChapter(selfChapter);
      expression.setAdditionalInformation(cleanTextWithoutComma(extraInfo.getText()));
   }

   private String cleanText(String text)
   {
      return text.replaceAll("\t", "").replaceAll("\n", "").replaceAll(",", "").strip();
   }

   private String cleanTextWithoutComma(String text)
   {
      return text.replaceAll("\t", "").replaceAll("\n", "").strip();
   }

   public void setExpression(Expression expression, boolean newExpression)
   {
      this.save = false;
      this.expression = expression;
      
      this.chapter.setModel(Data.getChapterComboBoxModel());
      if (expression.getChapter().getName().isEmpty())
      {
         chapter.setSelectedIndex(chapter.getItemCount() - 1);
      }
      else
      {
         this.chapter.setSelectedItem(expression.getChapter().getName());
      }
      
      this.german.setText(expression.getGerman());
      this.hebrewInLatin.setText(expression.getHebrewInLatin());
      this.hebrew.setText(expression.getHebrew());
      
      this.searchwordsSetGerman = new HashSet<>();
      for (String word : expression.getSearchwordsGerman())
      {
         this.searchwordsSetGerman.add(word);
      }
      this.searchwordsJListGerman.setModel(getSearchwordsModelGerman());

      this.searchwordsSetHebrew = new HashSet<>();
      for (String word : expression.getSearchwordsHebrew())
      {
         this.searchwordsSetHebrew.add(word);
      }
      this.searchwordsJListHebrew.setModel(getSearchwordsModelHebrew());
      
      if(newExpression)
      {
         expressionKindTable.setModel(ExpressionKind.getModel());
         binjanBox.setSelectedIndex(0);
         genderBox.setSelectedIndex(0);
         grammaticalPersonBox.setSelectedIndex(0);
         numerusBox.setSelectedIndex(0);
         verbConjugationBox.setSelectedIndex(0);
         verbStrengthBox.setSelectedIndex(0);
         verbTypeBox.setSelectedIndex(0);
      }
      else
      {
         Definitions definitions = expression.getDefinitions();
         Set<ExpressionKind> kinds = definitions.getExpressionKindSet();
         expressionKindTable.setModel(ExpressionKind.getModel(kinds));
         ExpressionKind kind = kinds.stream().findAny().get();

         binjanBox.setSelectedItem(
               definitions.getGrammaticalEnum(kind, Binjan.class));
         genderBox.setSelectedItem(
               definitions.getGrammaticalEnum(kind, Gender.class));
         grammaticalPersonBox.setSelectedItem(
               definitions.getGrammaticalEnum(kind, GrammaticalPerson.class));
         numerusBox.setSelectedItem(
               definitions.getGrammaticalEnum(kind, Numerus.class));
         verbConjugationBox.setSelectedItem(
               definitions.getGrammaticalEnum(kind, VerbConjugation.class));
         verbStrengthBox.setSelectedItem(
               definitions.getGrammaticalEnum(kind, VerbStrength.class));
         verbTypeBox.setSelectedItem(
               definitions.getGrammaticalEnum(kind, VerbType.class));
         
         extraInfo.setText(expression.getAdditionalInformation());
      }
      
   }

   private DefaultComboBoxModel<String> getSearchwordsModelGerman()
   {
      DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
      for (String word : searchwordsSetGerman)
      {
         model.addElement(word);
      }
      return model;
   }

   private DefaultComboBoxModel<String> getSearchwordsModelHebrew()
   {
      DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
      for (String word : searchwordsSetHebrew)
      {
         model.addElement(word);
      }
      return model;
   }

   public Expression getExpression()
   {
      return expression;
   }

   public boolean isSave()
   {
      return save;
   }

   public void setSave(boolean save)
   {
      this.save = save;
   }
}
