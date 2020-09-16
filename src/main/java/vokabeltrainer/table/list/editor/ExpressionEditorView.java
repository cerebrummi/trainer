package vokabeltrainer.table.list.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.InfoTextField;
import vokabeltrainer.KeyboardHebrew;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.WideComboBox;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.ExtraInformationDocument;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.HebrewDocument;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Database;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbConjugation;
import vokabeltrainer.types.grammatical.VerbStrength;
import vokabeltrainer.types.grammatical.VerbType;
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
   private WideComboBox<Gender> gender;
   private WideComboBox<Numerus> numerus;

   private InfoTextField newSearchwordGerman;
   private JList<String> searchwordsJListGerman;
   private Set<String> searchwordsSetGerman;
   private JButton deleteSearchwordButtonGerman;

   private InfoTextField newSearchwordHebrew;
   private JList<String> searchwordsJListHebrew;
   private Set<String> searchwordsSetHebrew;
   private JButton deleteSearchwordButtonHebrew;

   private WideComboBox<ExpressionKind> expressionKindComboBox;
   private JList<ExpressionKind> expressionKindJList;
   private Set<ExpressionKind> expressionKindSet;
   private JButton deleteExpressionKindButton;

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
   private WideComboBox<String> chapter;
   private JButton infoExpressionKindButton;
   private WideComboBox<Binjan> binjan;
   private WideComboBox<VerbConjugation> verbConjugation;
   private WideComboBox<VerbStrength> verbStrength;
   private WideComboBox<VerbType> verbType;
   private WideComboBox<GrammaticalPerson> grammaticalPerson;

   @SuppressWarnings("unused")
   private ExpressionEditorControllerConnector connector;

   private JTextPane extraInfo;

   private JScrollPane extraInfoScroller;

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

      infoExpressionKindButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      infoExpressionKindButton.setBackground(new Color(0, 0, 0, 0));
      infoExpressionKindButton.setMinimumSize(new Dimension(50, 50));
      infoExpressionKindButton.setMaximumSize(new Dimension(50, 50));

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

      gender = new WideComboBox<>(Gender.values());
      gender.setBorder(new TitledBorder("Geschlecht"));
      gender.setFont(germanfont);
      gender.setEditable(false);
      gender.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      gender.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      numerus = new WideComboBox<>(Numerus.values());
      numerus.setFocusable(false);
      numerus.setBorder(new TitledBorder("Numerus"));
      numerus.setFont(germanfont);
      numerus.setEditable(false);
      numerus.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      numerus.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      binjan = new WideComboBox<>(Binjan.values());
      binjan.setMaximumRowCount(Binjan.values().length);
      binjan.setBorder(new TitledBorder("Binjan"));
      binjan.setFont(germanfont);
      binjan.setEditable(false);
      binjan.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      binjan.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      verbConjugation = new WideComboBox<>(VerbConjugation.values());
      verbConjugation.setMaximumRowCount(VerbConjugation.values().length);
      verbConjugation.setBorder(new TitledBorder("Konjugation"));
      verbConjugation.setFont(germanfont);
      verbConjugation.setEditable(false);
      verbConjugation.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      verbConjugation.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      verbStrength = new WideComboBox<>(VerbStrength.values());
      verbStrength.setMaximumRowCount(VerbStrength.values().length);
      verbStrength.setBorder(new TitledBorder("Stärke"));
      verbStrength.setFont(germanfont);
      verbStrength.setEditable(false);
      verbStrength.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      verbStrength.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      verbType = new WideComboBox<>(VerbType.values());
      verbType.setMaximumRowCount(VerbType.values().length);
      verbType.setBorder(new TitledBorder("Typ"));
      verbType.setFont(germanfont);
      verbType.setEditable(false);
      verbType.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      verbType.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      grammaticalPerson = new WideComboBox<>(GrammaticalPerson.values());
      grammaticalPerson.setMaximumRowCount(GrammaticalPerson.values().length);
      grammaticalPerson.setBorder(new TitledBorder("Person"));
      grammaticalPerson.setFont(germanfont);
      grammaticalPerson.setEditable(false);
      grammaticalPerson.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      grammaticalPerson.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

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

      expressionKindComboBox = new WideComboBox<>(ExpressionKind.values());
      expressionKindComboBox
            .setMaximumRowCount(ExpressionKind.getNumberOfValues() / 2);
      expressionKindComboBox.setBorder(new TitledBorder("Wortart"));
      expressionKindComboBox.setFont(germanfont);
      expressionKindComboBox.setEditable(false);
      expressionKindComboBox
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 50, 50));
      expressionKindComboBox
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 50, 50));
      this.expressionKindComboBox
      .setSelectedItem(ExpressionKind.NOTHING);

      expressionKindJList = new JList<>();
      expressionKindJList.setCellRenderer(new ListCellRenderer<ExpressionKind>()
      {
         @Override
         public Component getListCellRendererComponent(
               JList<? extends ExpressionKind> list, ExpressionKind value,
               int index, boolean isSelected, boolean cellHasFocus)
         {
            AntiFocusExpressionKindField listComponent = new AntiFocusExpressionKindField(
                  value);
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

      deleteExpressionKindButton = new JButton("lösche Wortart");
      deleteExpressionKindButton.setFocusable(false);
      deleteExpressionKindButton.setFont(Main.getGermanFont(16F));
      deleteExpressionKindButton
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteExpressionKindButton
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

      chapter = new WideComboBox<String>();
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
      extraInfoScroller.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      extraInfoScroller.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

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
      scrollPane.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      scrollPane.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 200));

      JScrollPane scrollPane2 = new JScrollPane(searchwordsJListHebrew);
      scrollPane2.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      scrollPane2.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 200));

      vertical.add(gender);
      vertical.add(numerus);

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
      vertical.setLayout(new TotemLayout(vertical, 15));

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setLayout(new TrainLayout(horizontal));
      horizontal.add(expressionKindComboBox);
      horizontal.add(infoExpressionKindButton);

      vertical.add(horizontal);
      vertical.add(new JScrollPane(expressionKindJList));
      vertical.add(deleteExpressionKindButton);
      vertical.add(extraInfoScroller);
      
      layout.add(vertical);
   }

   private void initInfosExtra()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));
      
      // TODO

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
      infoExpressionKindButton.addActionListener(event -> {
         JOptionPane.showMessageDialog(this, "", "Cerebrummi©",
               JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(ExpressionKind.getExplanations())));
      });

      infoExpressionKindButton.addMouseListener(new MouseListener()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {

         }

         @Override
         public void mousePressed(MouseEvent e)
         {

         }

         @Override
         public void mouseReleased(MouseEvent e)
         {

         }

         @Override
         public void mouseEntered(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         }

      });

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
         setExpression(expression);
         resetAllBorders();
      });

      cancelButton.addActionListener(event -> {
         save = false;
         this.dispose();
      });

      expressionKindComboBox.addItemListener(event -> {
         ExpressionKind choosen = (ExpressionKind) this.expressionKindComboBox
               .getSelectedItem();
         
         if(expressionKindSet.add(choosen))
         {
            expressionKindJList.add(new AntiFocusExpressionKindField(choosen));
         }
      });

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

      // TODO expression setDefinition

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
   }

   private String cleanText(String text)
   {
      return text.replaceAll("\t", "").replaceAll("\n", "").replaceAll(",", "");
   }

   private String cleanTextWithoutComma(String text)
   {
      return text.replaceAll("\t", "").replaceAll("\n", "");
   }

   public void setExpression(Expression expression)
   {
      this.save = false;
      this.expression = expression;
      this.german.setText(expression.getGerman());
      this.hebrewInLatin.setText(expression.getHebrewInLatin());
      this.hebrew.setText(expression.getHebrew());

      // TODO

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

      this.expressionKindSet = new HashSet<>();
      expressionKindSet = expression.getDefinitions().getExpressionKindSet();
      this.expressionKindJList.setModel(getExpressionKindModel());

      this.chapter.setModel(Data.getChapterComboBoxModel());
      if (expression.getChapter().getName().isEmpty())
      {
         chapter.setSelectedIndex(chapter.getItemCount() - 1);
      }
      else
      {
         this.chapter.setSelectedItem(expression.getChapter().getName());
      }
   }

   private DefaultComboBoxModel<ExpressionKind> getExpressionKindModel()
   {
      DefaultComboBoxModel<ExpressionKind> model = new DefaultComboBoxModel<>();
      for (ExpressionKind kind : expressionKindSet)
      {
         model.addElement(kind);
      }
      return model;
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
