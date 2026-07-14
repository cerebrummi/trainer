package vokabeltrainer.table.list.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import vokabeltrainer.InfoCheckBox;
import vokabeltrainer.InfoComboBox;
import vokabeltrainer.InfoTextField;
import vokabeltrainer.InputLanguagePanel;
import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.TextImage;
import vokabeltrainer.cmd.TextHelper;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.LetterForSaving;
import vokabeltrainer.common.Settings;
import vokabeltrainer.common.colors.InputColors;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Data;
import vokabeltrainer.common.main.View;
import vokabeltrainer.editing.ExtraInformationDocument;
import vokabeltrainer.editing.InternationalDocument;
import vokabeltrainer.keyboards.KeyboardLanguage;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.EscapeAction;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableMultiselect;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableRow;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Chapter.Database;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.LLType;
import vokabeltrainer.types.LearningLanguage;
import vokabeltrainer.types.SortingIndex;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;
import vokabeltrainer.types.grammatical.expressionkind.Definitions;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKindItem;

public class LanguageExpressionEditorView extends JDialog
      implements ExpressionEditorViewConnector
{
   private static final int WIDTH_INFO_PANEL = 230;

   private static final int WIDTH_BOX_PANEL = 230;

   private static final int WIDTH_INPUT_PANEL = Settings.getKeyboardWidth();

   private static final long serialVersionUID = 5853498340870217732L;

   private Translator translator;
   private Expression expression;
   private boolean newExpression;
   private JTextField ownLanguage;
   private InputLanguagePanel language;

   private JTextField indexField;

   private InfoTextField newSearchwordOwn;
   private JList<String> searchwordsJListOwn;
   private Set<String> searchwordsSetGerman;
   private JButton deleteSearchwordButtonGerman;

   private InfoTextField newSearchwordNew;
   private JList<String> searchwordsJListNew;
   private Set<String> searchwordsSetHebrew;
   private JButton deleteSearchwordButtonHebrew;

   private KeyboardLanguage keyboard;

   private JPanel outerLayout;
   private JPanel layout;
   private boolean save;
   private JButton saveButton;
   private JButton restoreButton;
   private JButton cancelButton;
   private List<JTextComponent> components = new ArrayList<>();
   private String germanTitle;
   private String searchwordJListGermanTitle;
   private String searchwordsJListHebrewTitle;
   private String chapterTitle;
   private InfoComboBox chapter;
   private JTextPane extraInfo;
   private JScrollPane extraInfoScroller;
   private JButton pasteButton;
   private JButton cutButton;
   private JButton copyButton;

   private JButton imageButton;

   private ExpressionKindTableMultiselect expressionKindTable;
   private boolean frozen;

   private JPanel binjanBoxPanel;

   private JPanel genderBoxPanel;

   private JPanel grammaticalPersonBoxPanel;

   private JPanel numerusBoxPanel;

   private JPanel verbTimesBoxPanel;

   private JComboBox<Binjan> binjanBox;

   private JComboBox<Gender> genderBox;

   private JComboBox<GrammaticalPerson> grammaticalPersonBox;

   private JComboBox<Numerus> numerusBox;

   private JComboBox<VerbTimes> verbTimesBox;

   private JPanel definitionPanel;

   private JScrollPane scrollPaneExpressionTable;

   private InfoComboBox databaseNameField;

   private JLabel lastModiefiedLabel;

   private InfoCheckBox visible;

   private NikudExpressionEditorControllerConnector connector;

   public LanguageExpressionEditorView(Common common, View view,
         NikudExpressionEditorControllerConnector connector)
   {
      super(view.getjFrame(), Settings.getWindowTitle(),
            Dialog.ModalityType.APPLICATION_MODAL);

      this.connector = connector;
      translator = common.getTranslator();
      
      germanTitle = translator
            .realisticTranslate(Translation.DEUTSCH);
      searchwordJListGermanTitle = translator
            .realisticTranslate(Translation.DEUTSCHE_SUCHWOERTER);
      searchwordsJListHebrewTitle = translator
            .realisticTranslate(Translation.HEBRAEISCHE_SUCHWOERTER);
      chapterTitle = translator
            .realisticTranslate(Translation.LEKTION);
      
      save = false;
      setResizable(true);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setSize(Math.min(screenSize.width - 60, 1320),
            Math.min(screenSize.height - 60, 825));

      outerLayout = new JPanel();
      outerLayout.setBackground(InputColors.getEditorBackground());
      outerLayout.setBorder(BorderFactory
            .createLineBorder(InputColors.getEditorBackground(), 15, false));
      outerLayout.setLayout(new TotemLayout(outerLayout, 15));

      layout = new JPanel();
      layout.setOpaque(false);
      layout.setBackground(ApplicationColors.getTransparent());
      layout.setLayout(new TrainLayout(layout, 15));

      initGuiFields(common, view);
      layout.add(initInput());
      layout.add(initInfosLeft());
      layout.add(initInfosRight());
      layout.add(initInfosExtra());

      outerLayout.add(initTopPanel());
      outerLayout.add(layout);

      getContentPane().add(new JScrollPane(outerLayout));

      initController(common, view);
      Component[] focusList = { ownLanguage, language, newSearchwordOwn,
            newSearchwordNew, extraInfo };
      this.setFocusTraversalPolicy(
            new CerebrummiFocusTraversalPolicy(focusList));
   }

   private void initGuiFields(Common common, View view)
   {
      ownLanguage = new JTextField();
      ownLanguage.setFont(ApplicationFonts.internationalFont);
      ownLanguage.setBorder(makeBorderBlank(germanTitle));
      ownLanguage.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      ownLanguage.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      ownLanguage.setDocument(new InternationalDocument());
      switch (Settings.getMyWritingDirection())
      {
      case LEFT_TO_RIGHT:
         ownLanguage
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         break;
      case RIGHT_TO_LEFT:
         ownLanguage
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         break;
      }

      if (Settings.isSimpleHebrewInput())
      {
         language = new InputLanguagePanel(common, Selection.SIMPLE, 152, 6, true, this,
               WIDTH_INPUT_PANEL, ApplicationColors.getLightYellow());
      }
      else if (Settings.isHebrewPleneDefektivInput())
      {
         language = new InputLanguagePanel(common, Selection.PLENE_DEFEKTIV, 152, 6,
               true, this, WIDTH_INPUT_PANEL,
               ApplicationColors.getLightYellow());
      }
      else if (Settings.isSwedishInput())
      {
         language = new InputLanguagePanel(common, Selection.SWEDISH, 152, 6, false,
               this, WIDTH_INPUT_PANEL, ApplicationColors.getLightYellow());
      }
      else
      {
         language = new InputLanguagePanel(common, Selection.GERMAN, 152, 6, false,
               this, WIDTH_INPUT_PANEL, ApplicationColors.getLightYellow());
      }
      language.setBlankBorder();

      newSearchwordOwn = new InfoTextField(view,
            translator.realisticTranslate(Translation.NEUES_SUCHWORT_DEUTSCH)
                  + "  ",
            translator.realisticTranslate(
                  Translation.BITTE_JE_EIN_WORT_EINGEBEN) + "  ",
            translator.realisticTranslate(Translation.UND_DANN_ENTER_DRUECKEN_)
                  + "  ");

      newSearchwordOwn.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordOwn.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordOwn.setDocument(new InternationalDocument());

      searchwordsJListOwn = new JList<>();
      searchwordsJListOwn.setBackground(InputColors.getTextBackground());
      searchwordsJListOwn.setCellRenderer(new ListCellRenderer<String>()
      {
         @Override
         public Component getListCellRendererComponent(
               JList<? extends String> list, String value, int index,
               boolean isSelected, boolean cellHasFocus)
         {
            AntiFocusTextField listComponent = new AntiFocusTextField(view,
                  value);
            if (isSelected)
            {
               listComponent.setBackground(InputColors.getTextForeground());
               listComponent.setForeground(InputColors.getTextBackground());
            }
            else
            {
               listComponent.setBackground(InputColors.getTextBackground());
               listComponent.setForeground(InputColors.getTextForeground());
            }
            return listComponent;
         }
      });
      searchwordsJListOwn.setFocusable(false);
      searchwordsJListOwn
            .setBorder(makeBorderBlank(this.searchwordJListGermanTitle));
      searchwordsJListOwn.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 300));
      searchwordsJListOwn.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      JPopupMenu popupGerman = new JPopupMenu();
      JMenuItem copyMenuGerman = new JMenuItem(
            translator.realisticTranslate(Translation.KOPIEREN));
      copyMenuGerman.addActionListener(_ -> {
         StringSelection stringSelection = new StringSelection(
               searchwordsJListOwn.getSelectedValue());
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(stringSelection, null);
      });
      popupGerman.add(copyMenuGerman);
      searchwordsJListOwn.setComponentPopupMenu(popupGerman);

      newSearchwordNew = new InfoTextField(view,
            translator.realisticTranslate(Translation.NEUES_SUCHWORT_HEBRAEISCH)
                  + "  ",
            translator.realisticTranslate(Translation.BITTE_HINEINKLICKEN_),
            translator.realisticTranslate(
                  Translation.HEBRAEISCHE_TASTATUR_BENUTZEN),
            translator.realisticTranslate(Translation.DANACH_ENTER_DRUECKEN_)
                  + "  ");

      newSearchwordNew
            .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      newSearchwordNew.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordNew.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordNew.setDocument(new InternationalDocument());
      this.components.add(newSearchwordNew);

      searchwordsJListNew = new JList<>();
      searchwordsJListNew.setBackground(InputColors.getTextBackground());
      searchwordsJListNew.setCellRenderer(new ListCellRenderer<String>()
      {
         @Override
         public Component getListCellRendererComponent(
               JList<? extends String> list, String value, int index,
               boolean isSelected, boolean cellHasFocus)
         {
            AntiFocusTextField listComponent = new AntiFocusTextField(view,
                  value);
            if (isSelected)
            {
               listComponent.setBackground(InputColors.getTextForeground());
               listComponent.setForeground(InputColors.getTextBackground());
            }
            else
            {
               listComponent.setBackground(InputColors.getTextBackground());
               listComponent.setForeground(InputColors.getTextForeground());
            }
            return listComponent;
         }
      });
      searchwordsJListNew.setFocusable(false);
      searchwordsJListNew.setFixedCellHeight(50);
      searchwordsJListNew
            .setBorder(makeBorderBlank(this.searchwordsJListHebrewTitle));
      searchwordsJListNew.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 300));
      searchwordsJListNew.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      JPopupMenu popupHebrew = new JPopupMenu();
      JMenuItem copyMenuHebrew = new JMenuItem(
            translator.realisticTranslate(Translation.KOPIEREN));
      copyMenuHebrew.addActionListener(_ -> {
         StringSelection stringSelection = new StringSelection(
               searchwordsJListNew.getSelectedValue());
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents(stringSelection, null);
      });
      popupHebrew.add(copyMenuHebrew);
      searchwordsJListNew.setComponentPopupMenu(popupHebrew);

      deleteSearchwordButtonHebrew = new JButton(translator
            .realisticTranslate(Translation.LOESCHE_SUCHWORT_HEBRAEISCH));
      deleteSearchwordButtonHebrew.setFont(ApplicationFonts.buttonFont);
      deleteSearchwordButtonHebrew
            .setForeground(InputColors.getTextForeground());
      deleteSearchwordButtonHebrew
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteSearchwordButtonHebrew
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 40));

      deleteSearchwordButtonGerman = new JButton(translator
            .realisticTranslate(Translation.LOESCHE_SUCHWORT_DEUTSCH));
      deleteSearchwordButtonGerman.setFocusable(false);
      deleteSearchwordButtonGerman.setFont(ApplicationFonts.buttonFont);
      deleteSearchwordButtonGerman
            .setForeground(InputColors.getTextForeground());
      deleteSearchwordButtonGerman
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteSearchwordButtonGerman
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 40));

      saveButton = new JButton(
            translator.realisticTranslate(Translation.SPEICHERN));
      saveButton.setFont(ApplicationFonts.buttonFont);
      saveButton.setForeground(InputColors.getTextForeground());
      saveButton.setMinimumSize(new Dimension(120, 40));
      saveButton.setMaximumSize(new Dimension(160, 40));

      restoreButton = new JButton(
            translator.realisticTranslate(Translation.ZURUECKSETZEN));
      restoreButton.setFont(ApplicationFonts.buttonFont);
      restoreButton.setForeground(InputColors.getTextForeground());
      restoreButton.setMinimumSize(new Dimension(120, 40));
      restoreButton.setMaximumSize(new Dimension(160, 40));

      cancelButton = new JButton(
            translator.realisticTranslate(Translation.ABBRECHEN));
      cancelButton.setFont(ApplicationFonts.buttonFont);
      cancelButton.setForeground(InputColors.getTextForeground());
      cancelButton.setMinimumSize(new Dimension(120, 40));
      cancelButton.setMaximumSize(new Dimension(160, 40));

      chapter = new InfoComboBox(this.chapterTitle,
            translator
                  .realisticTranslate(Translation.HINEINKLICKEN_UND_SCHREIBEN),
            translator.realisticTranslate(Translation.ENTER_DRUECKEN__UM),
            translator
                  .realisticTranslate(Translation.EINEN_EINTRAG_ZU_AENDERN));
      chapter.setEditable(true);
      chapter.setMaximumRowCount(20);
      chapter.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      chapter.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));

      indexField = new JTextField();
      indexField.setBorder(
            makeBorderBlank(translator.realisticTranslate(Translation.INDEX)));
      indexField.setOpaque(false);
      indexField.setBackground(ApplicationColors.getTransparent());
      indexField.setDocument(new InternationalDocument());
      indexField.setMinimumSize(new Dimension(85, 70));
      indexField.setMaximumSize(new Dimension(85, 70));

      databaseNameField = new InfoComboBox(
            translator.realisticTranslate(Translation.DATENBANK),
            translator
                  .realisticTranslate(Translation.HINEINKLICKEN_UND_SCHREIBEN),
            translator.realisticTranslate(Translation.ENTER_DRUECKEN__UM),
            translator
                  .realisticTranslate(Translation.EINEN_EINTRAG_ZU_AENDERN));
      databaseNameField.setFont(ApplicationFonts.buttonFont);
      databaseNameField.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      databaseNameField.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      databaseNameField.setEditable(true);
      databaseNameField.setMaximumRowCount(20);

      extraInfo = new JTextPane();
      extraInfo.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.WEITERE_INFORMATIONEN)));
      extraInfo.setDocument(new ExtraInformationDocument());
      StyledDocument doc = extraInfo.getStyledDocument();
      SimpleAttributeSet style = new SimpleAttributeSet();
      StyleConstants.setForeground(style, InputColors.getInfoTextForeground());
      StyleConstants.setFontSize(style, 20);
      StyleConstants.setFontFamily(style, "Serif");
      doc.setParagraphAttributes(0, doc.getLength(), style, true);
      extraInfo.setFocusTraversalKeys(
            KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
      extraInfo.setFocusTraversalKeys(
            KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

      components.add(extraInfo);
      extraInfoScroller = new JScrollPane(extraInfo);
      extraInfoScroller.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      extraInfoScroller.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 240));

      pasteButton = new JButton(new DefaultEditorKit.PasteAction());
      pasteButton.setForeground(InputColors.getTextForeground());
      pasteButton.setIcon(new ImageIcon(ApplicationImages.getPaste()));
      pasteButton.setText("");
      pasteButton.setToolTipText(
            translator.realisticTranslate(Translation.EINFUEGEN));
      pasteButton
            .setMinimumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));
      pasteButton
            .setMaximumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));

      cutButton = new JButton(new DefaultEditorKit.CutAction());
      cutButton.setForeground(InputColors.getTextForeground());
      cutButton.setIcon(new ImageIcon(ApplicationImages.getCut()));
      cutButton.setText("");
      cutButton.setToolTipText(
            translator.realisticTranslate(Translation.AUSSCNEIDEN));
      cutButton.setMinimumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));
      cutButton.setMaximumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));

      copyButton = new JButton(new DefaultEditorKit.CopyAction());
      copyButton.setForeground(InputColors.getTextForeground());
      copyButton.setIcon(new ImageIcon(ApplicationImages.getCopy2()));
      copyButton.setText("");
      copyButton.setToolTipText(
            translator.realisticTranslate(Translation.KOPIEREN));
      copyButton.setMinimumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));
      copyButton.setMaximumSize(new Dimension((WIDTH_INFO_PANEL - 30) / 3, 40));

      imageButton = new JButton();
      imageButton.setFont(ApplicationFonts.buttonFont);
      imageButton.setForeground(InputColors.getTextForeground());
      imageButton.setText(
            translator.realisticTranslate(Translation.BILDER_ANZEIGEN));
      imageButton.setMinimumSize(new Dimension(WIDTH_BOX_PANEL, 40));
      imageButton.setMaximumSize(new Dimension(WIDTH_BOX_PANEL, 40));

      expressionKindTable = new ExpressionKindTableMultiselect(
            ExpressionKind.getModelForMultiselect(), WIDTH_INFO_PANEL, this);

      makeAllBoxes();

      binjanBoxPanel = new JPanel();
      TotemLayout binjanLayout = new TotemLayout(binjanBoxPanel);
      binjanBoxPanel.setLayout(binjanLayout);
      binjanBoxPanel.add(binjanBox);
      binjanBoxPanel.setOpaque(false);
      binjanBoxPanel.setBackground(ApplicationColors.getTransparent());
      binjanBoxPanel.setBorder(new TitledBorder(
            translator.realisticTranslate(Translation.BINJAN___STAMM)));

      genderBoxPanel = new JPanel();
      TotemLayout genderLayout = new TotemLayout(genderBoxPanel);
      genderBoxPanel.setLayout(genderLayout);
      genderBoxPanel.add(genderBox);
      genderBoxPanel.setOpaque(false);
      genderBoxPanel.setBackground(ApplicationColors.getTransparent());
      genderBoxPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.GESCHLECHT)));

      grammaticalPersonBoxPanel = new JPanel();
      TotemLayout grammaticalPersonLayout = new TotemLayout(
            grammaticalPersonBoxPanel);
      grammaticalPersonBoxPanel.setLayout(grammaticalPersonLayout);
      grammaticalPersonBoxPanel.add(grammaticalPersonBox);
      grammaticalPersonBoxPanel.setOpaque(false);
      grammaticalPersonBoxPanel
            .setBackground(ApplicationColors.getTransparent());
      grammaticalPersonBoxPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.GRAMMATISCHE_PERSON)));

      numerusBoxPanel = new JPanel();
      TotemLayout numerusLayout = new TotemLayout(numerusBoxPanel);
      numerusBoxPanel.setLayout(numerusLayout);
      numerusBoxPanel.add(numerusBox);
      numerusBoxPanel.setOpaque(false);
      numerusBoxPanel.setBackground(ApplicationColors.getTransparent());
      numerusBoxPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.NUMERUS)));

      verbTimesBoxPanel = new JPanel();
      TotemLayout verbConjugationLayout = new TotemLayout(verbTimesBoxPanel);
      verbTimesBoxPanel.setLayout(verbConjugationLayout);
      verbTimesBoxPanel.add(verbTimesBox);
      verbTimesBoxPanel.setOpaque(false);
      verbTimesBoxPanel.setBackground(ApplicationColors.getTransparent());
      verbTimesBoxPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(Translation.ZEITFORM)));

      keyboard = new KeyboardLanguage(language, components, 152, true, false);
   }

   private void makeAllBoxes()
   {
      switch (Settings.getLanguageInput())
      {
      case PLENE_DEFEKTIV:
      case SIMPLE:
         makeAllBoxes(LLType.HEBREW);
         break;
      case SWEDISH:
         makeAllBoxes(LLType.SWEDISH);
         break;
      case GERMAN:
         makeAllBoxes(LLType.GERMAN);
      }
   }

   private void makeAllBoxes(LLType llType)
   {
      makeVerbTimesBox(VerbTimes.values(llType));
      makeNumerusBox(Numerus.values(llType));
      makeGrammaticalPersonBox(GrammaticalPerson.values(llType));
      makeGenderBox(Gender.values(llType));
      makeBinjanBox(Binjan.values(llType));
   }

   private void makeVerbTimesBox(VerbTimes[] values)
   {
      verbTimesBox = new JComboBox<>(values);
      verbTimesBox.setFont(ApplicationFonts.comboBoxFont);
      verbTimesBox.setEditable(false);
      verbTimesBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      verbTimesBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      verbTimesBox.setMaximumRowCount(VerbTimes.values().length);
   }

   private void makeNumerusBox(Numerus[] values)
   {
      numerusBox = new JComboBox<>(values);
      numerusBox.setFont(ApplicationFonts.comboBoxFont);
      numerusBox.setEditable(false);
      numerusBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      numerusBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      numerusBox.setMaximumRowCount(Numerus.values().length);
   }

   private void makeGrammaticalPersonBox(GrammaticalPerson[] values)
   {
      grammaticalPersonBox = new JComboBox<>(values);
      grammaticalPersonBox.setFont(ApplicationFonts.comboBoxFont);
      grammaticalPersonBox.setEditable(false);
      grammaticalPersonBox
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      grammaticalPersonBox
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      grammaticalPersonBox
            .setMaximumRowCount(GrammaticalPerson.values().length);
   }

   private void makeGenderBox(Gender[] values)
   {
      genderBox = new JComboBox<>(values);
      genderBox.setFont(ApplicationFonts.comboBoxFont);
      genderBox.setEditable(false);
      genderBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      genderBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      genderBox.setMaximumRowCount(Gender.values().length);
   }

   private void makeBinjanBox(Binjan[] values)
   {
      binjanBox = new JComboBox<>(values);
      binjanBox.setFont(ApplicationFonts.comboBoxFont);
      binjanBox.setEditable(false);
      binjanBox.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      binjanBox.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 30));
      binjanBox.setMaximumRowCount(Binjan.values().length);
   }

   private void remakeAllBoxes(Expression expression)
   {
      remakeVerbTimesBox(VerbTimes.values(expression));
      remakeNumerusBox(Numerus.values(expression));
      remakeGrammaticalPersonBox(GrammaticalPerson.values(expression));
      remakeGenderBox(Gender.values(expression));
      remakeBinjanBox(Binjan.values(expression));
   }

   public void remakeAllBoxes(LLType llType)
   {
      remakeVerbTimesBox(VerbTimes.values(llType));
      remakeNumerusBox(Numerus.values(llType));
      remakeGrammaticalPersonBox(GrammaticalPerson.values(llType));
      remakeGenderBox(Gender.values(llType));
      remakeBinjanBox(Binjan.values(llType));
   }

   private void remakeVerbTimesBox(VerbTimes[] values)
   {
      verbTimesBox.removeAllItems();
      for (VerbTimes verbTimes : values)
      {
         verbTimesBox.addItem(verbTimes);
      }
      verbTimesBox.setMaximumRowCount(values.length);
      verbTimesBox.validate();
      verbTimesBox.repaint();
   }

   private void remakeNumerusBox(Numerus[] values)
   {
      numerusBox.removeAllItems();
      for (Numerus numerus : values)
      {
         numerusBox.addItem(numerus);
      }
      numerusBox.setMaximumRowCount(values.length);
      numerusBox.validate();
      numerusBox.repaint();
   }

   private void remakeGrammaticalPersonBox(GrammaticalPerson[] values)
   {
      grammaticalPersonBox.removeAllItems();
      for (GrammaticalPerson person : values)
      {
         grammaticalPersonBox.addItem(person);
      }
      grammaticalPersonBox.setMaximumRowCount(values.length);
      grammaticalPersonBox.validate();
      grammaticalPersonBox.repaint();
   }

   private void remakeGenderBox(Gender[] values)
   {
      genderBox.removeAllItems();
      for (Gender gender : values)
      {
         genderBox.addItem(gender);
      }
      genderBox.setMaximumRowCount(values.length);
      genderBox.validate();
      genderBox.repaint();
   }

   private void remakeBinjanBox(Binjan[] values)
   {
      binjanBox.removeAllItems();
      for (Binjan binjan : values)
      {
         binjanBox.addItem(binjan);
      }
      binjanBox.setMaximumRowCount(values.length);
      binjanBox.validate();
      binjanBox.repaint();
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

   private Component initTopPanel()
   {
      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.setOpaque(false);
      horizontal.setBackground(ApplicationColors.getTransparent());
      horizontal.add(databaseNameField);
      horizontal.add(chapter);
      horizontal.add(indexField);
      return horizontal;
   }

   private Component initInput()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setBackground(ApplicationColors.getTransparent());
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.add(ownLanguage);
      vertical.add(keyboard);

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setBackground(ApplicationColors.getTransparent());
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.add(saveButton);
      horizontal.add(restoreButton);
      horizontal.add(cancelButton);

      vertical.add(horizontal);
      return vertical;
   }

   private Component initInfosLeft()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));

      JScrollPane scrollPane = new JScrollPane(searchwordsJListOwn);
      scrollPane.setBorder(BorderFactory.createEmptyBorder());
      scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
      scrollPane.setOpaque(false);
      scrollPane.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      scrollPane.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

      JScrollPane scrollPane2 = new JScrollPane(searchwordsJListNew);
      scrollPane2.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      scrollPane2.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      scrollPane2.setBorder(BorderFactory.createEmptyBorder());
      scrollPane2.setViewportBorder(BorderFactory.createEmptyBorder());
      scrollPane2.setOpaque(false);

      vertical.add(newSearchwordOwn);
      vertical.add(scrollPane);
      vertical.add(deleteSearchwordButtonGerman);

      vertical.add(newSearchwordNew);
      vertical.add(scrollPane2);
      vertical.add(deleteSearchwordButtonHebrew);

      return vertical;
   }

   private Component initInfosRight()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setBackground(ApplicationColors.getTransparent());
      vertical.setLayout(new TotemLayout(vertical, 15));

      scrollPaneExpressionTable = new JScrollPane(expressionKindTable);
      scrollPaneExpressionTable
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 200));
      scrollPaneExpressionTable
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 300));
      scrollPaneExpressionTable.setBorder(BorderFactory.createTitledBorder(
            BorderFactory
                  .createLineBorder(ApplicationColors.getLightGrayGold()),
            translator.realisticTranslate(
                  Translation.WORTARTEN__MEHRFACHAUSWAHL_)));

      JPanel horizontalEye = new JPanel();
      horizontalEye.setBackground(ApplicationColors.getMediumBlue());
      horizontalEye.setLayout(new TrainLayout(horizontalEye, 15));

      visible = new InfoCheckBox(
            translator.realisticTranslate(Translation.SICHTBAR));
      visible.setFont(ApplicationFonts.buttonFont);
      visible.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 85, 70));
      visible.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 85, 70));

      JButton labelEye = new JButton(new ImageIcon(ApplicationImages.getEye()));
      labelEye.setMinimumSize(new Dimension(70, 70));
      labelEye.setMaximumSize(new Dimension(70, 70));
      labelEye.setOpaque(false);
      labelEye.setBackground(ApplicationColors.getLightGrayBlue());
      labelEye.setBorder(BorderFactory.createEmptyBorder());

      labelEye.addActionListener(_ -> {
         JOptionPane.showMessageDialog(this, "", Settings.getWindowTitle(),
               JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(
                     translator.realisticTranslate(
                           Translation.BEIM_VOKABEL_ABFRAGEN),
                     translator.realisticTranslate(
                           Translation.IHRE_SPRACHE_LERNSPRACHE),
                     translator.realisticTranslate(
                           Translation.GRAMMATIK_SICHTBAR_MACHEN))));
      });

      labelEye.addMouseListener(new MouseAdapter()
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

      lastModiefiedLabel = new JLabel();
      lastModiefiedLabel.setFont(ApplicationFonts.germanFont.deriveFont(14F));

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setBackground(ApplicationColors.getTransparent());
      horizontal.setLayout(new TrainLayout(horizontal, 15));

      horizontalEye.add(visible);
      horizontalEye.add(labelEye);

      horizontal.add(copyButton);
      horizontal.add(cutButton);
      horizontal.add(pasteButton);

      vertical.add(scrollPaneExpressionTable);
      vertical.add(horizontalEye);
      vertical.add(lastModiefiedLabel);
      vertical.add(extraInfoScroller);
      vertical.add(horizontal);

      return vertical;
   }

   private Component initInfosExtra()
   {
      definitionPanel = new JPanel();
      TotemLayout definitionLayout = new TotemLayout(definitionPanel, 15);
      definitionPanel.setLayout(definitionLayout);
      definitionPanel.setBorder(BorderFactory.createEmptyBorder());
      definitionPanel.setOpaque(false);
      definitionPanel.setBackground(ApplicationColors.getTransparent());

      JPanel filler = new JPanel();
      filler.setOpaque(false);
      filler.setBackground(InputColors.getTransparent());
      filler.setMinimumSize(new Dimension(WIDTH_BOX_PANEL, 200));
      filler.setMaximumSize(new Dimension(WIDTH_BOX_PANEL, 850));

      JPanel innerScroll = new JPanel();
      innerScroll.setOpaque(false);
      innerScroll.setBackground(ApplicationColors.getTransparent());
      innerScroll.setLayout(new TotemLayout(innerScroll, 15));

      innerScroll.add(definitionPanel);
      innerScroll.add(filler);
      innerScroll.add(imageButton);

      JScrollPane scrollPane2 = new JScrollPane(innerScroll);
      scrollPane2.setMinimumSize(new Dimension(WIDTH_BOX_PANEL, 200));
      scrollPane2.setMaximumSize(new Dimension(WIDTH_BOX_PANEL, 800));
      scrollPane2.setBorder(BorderFactory.createEmptyBorder());
      scrollPane2.setViewportBorder(BorderFactory.createEmptyBorder());
      scrollPane2.setOpaque(true);
      scrollPane2.setBackground(InputColors.getEditorBackground());
      scrollPane2.getViewport()
            .setBackground(InputColors.getEditorBackground());

      return scrollPane2;
   }

   private void resetAllBorders()
   {
      chapter.setBlankBorder();
      ownLanguage.setBorder(makeBorderBlank(this.germanTitle));
      language.setBlankBorder();
   }

   private void initController(Common common, View view)
   {
      ownLanguage.addActionListener(_ -> {
         if (!ownLanguage.getText().isEmpty())
         {
            ownLanguage.setBorder(makeBorderBlank(this.germanTitle));
         }
      });

      newSearchwordOwn.addActionListener(_ -> {
         String add = newSearchwordOwn.getText().replaceAll(",", "");
         add = TextHelper.cleanText(add);
         if (!add.isEmpty())
         {
            searchwordsSetGerman.add(add);
            searchwordsJListOwn.setModel(getSearchwordsModelGerman());
            newSearchwordOwn.setText("");
            newSearchwordOwn.requestFocus();
         }
      });

      deleteSearchwordButtonGerman.addActionListener(_ -> {
         if (searchwordsJListOwn.getSelectedValue() != null)
         {
            searchwordsSetGerman.remove(searchwordsJListOwn.getSelectedValue());
            searchwordsJListOwn.setModel(getSearchwordsModelGerman());
         }
      });

      newSearchwordNew.addActionListener(_ -> {
         String add = newSearchwordNew.getText().replaceAll(",", "");
         add = TextHelper.cleanText(add);
         if (!add.isEmpty())
         {
            searchwordsSetHebrew.add(add);
            searchwordsJListNew.setModel(getSearchwordsModelHebrew());
            newSearchwordNew.setText("");
            newSearchwordNew.requestFocus();
         }
      });

      deleteSearchwordButtonHebrew.addActionListener(_ -> {
         if (searchwordsJListNew.getSelectedValue() != null)
         {
            searchwordsSetHebrew.remove(searchwordsJListNew.getSelectedValue());
            searchwordsJListNew.setModel(getSearchwordsModelHebrew());
         }
      });

      saveButton.addActionListener(_ -> {
         if (testForCompletness())
         {
            save = true;
            saveExpression(common);
            this.setVisible(false);
         }
      });

      restoreButton.addActionListener(_ -> {
         setExpressionForReset(common, view);
         resetAllBorders();
      });

      cancelButton.addActionListener(_ -> {
         this.disposeDialog();
      });

      getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE_KEY");
      getRootPane().getActionMap().put("ESCAPE_KEY", new EscapeAction(this));
   }

   public void disposeDialog()
   {
      save = false;
      this.dispose();
   }

   public void showGrammaticalParentEnums(
         Set<GrammaticalParentEnum> grammaticalParentEnumsToShow)
   {
      definitionPanel.removeAll();
      if (grammaticalParentEnumsToShow.contains(GrammaticalParentEnum.GENDER))
      {
         definitionPanel.add(this.genderBoxPanel);
      }
      else
      {
         this.genderBox.setSelectedItem(Gender.GENDER_NA);
      }
      if (grammaticalParentEnumsToShow.contains(GrammaticalParentEnum.NUMERUS))
      {
         definitionPanel.add(this.numerusBoxPanel);
      }
      else
      {
         this.numerusBox.setSelectedItem(Numerus.NUMERUS_NA);
      }
      if (grammaticalParentEnumsToShow
            .contains(GrammaticalParentEnum.GRAMMATICAL_PERSON))
      {
         definitionPanel.add(this.grammaticalPersonBoxPanel);
      }
      else
      {
         this.grammaticalPersonBox
               .setSelectedItem(GrammaticalPerson.GRAMMATICALPERSON_NA);
      }
      if (grammaticalParentEnumsToShow.contains(GrammaticalParentEnum.BINJAN))
      {
         definitionPanel.add(this.binjanBoxPanel);
      }
      else
      {
         this.binjanBox.setSelectedItem(Binjan.BINJAN_NA);
      }
      if (grammaticalParentEnumsToShow
            .contains(GrammaticalParentEnum.VERB_TIMES))
      {
         definitionPanel.add(this.verbTimesBoxPanel);
      }
      else
      {
         this.verbTimesBox.setSelectedItem(VerbTimes.VERBTIMES_NA);
      }

      JPanel filler = new JPanel();
      filler.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 10, 0));
      filler.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 10, 700));
      filler.setOpaque(false);
      filler.setBackground(ApplicationColors.getTransparent());
      definitionPanel.add(filler);

      definitionPanel.validate();
      definitionPanel.repaint();
   }

   private boolean testForCompletness()
   {
      boolean result = true;
      if (((String) chapter.getSelectedItem()).isBlank())
      {
         chapter.setRedBorder();
         result = false;
      }
      if (ownLanguage.getText().isEmpty())
      {
         ownLanguage.setBorder(makeBorderRed(this.germanTitle));
         result = false;
      }
      if (!language.isFilledOut())
      {
         language.setRedBorder();
         result = false;
      }

      return result;
   }

   private void saveExpression(Common common)
   {
      expression.setOwnLanguage(cleanText(ownLanguage.getText()));

      expression.setLearningLanguage(new LearningLanguage(
            cleanText(language.getHebrewFieldText()),
            cleanText(language.getPleneFieldText()),
            cleanText(language.getDefektivFieldText()), language.isSimple(),
            cleanText(language.getSwedishFieldText()),
            cleanText(language.getGermanFieldText())));

      expression.setLetterForSaving(
            LetterForSaving.getLetter(cleanText(expression.getOwnLanguage())));

      Definitions definitions = new Definitions();
      Vector<Vector<ExpressionKindTableRow>> vektorRows = expressionKindTable
            .getModel().getData();
      for (Vector<ExpressionKindTableRow> vektorRow : vektorRows)
      {
         ExpressionKindItem expressionKind = vektorRow.get(0)
               .getExpressionKindItem();
         if (expressionKind.isSelected())
         {
            definitions.addExpressionKind(expressionKind.getKind());
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  binjanBox.getItemAt(binjanBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  genderBox.getItemAt(genderBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  grammaticalPersonBox
                        .getItemAt(grammaticalPersonBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  numerusBox.getItemAt(numerusBox.getSelectedIndex()));
            definitions.setGrammaticalEnum(expressionKind.getKind(),
                  verbTimesBox.getItemAt(verbTimesBox.getSelectedIndex()));
         }
      }
      if (definitions.getExpressionKindSet().isEmpty())
      {
         definitions.addExpressionKind(ExpressionKind.EXPRESSIONKIND_UNKNOWN);
      }
      else if (definitions.getExpressionKindSet().contains(ExpressionKind.TEXT))
      {
         expression.setLevel(1);
      }

      expression.setDefinitions(definitions);

      List<String> wordsGerman = new ArrayList<>();
      for (String word : searchwordsSetGerman)
      {
         wordsGerman.add(cleanTextAndNoComma(word));
      }
      expression.setSearchwordsGerman(wordsGerman);
      List<String> wordsHebrew = new ArrayList<>();
      for (String word : searchwordsSetHebrew)
      {
         wordsHebrew.add(cleanTextAndNoComma(word));
      }
      expression.setSearchwordsHebrew(wordsHebrew);
      Chapter selfChapter = new Chapter(common);
      selfChapter.setOrigin(Database.SELF);
      selfChapter.setName(cleanText((String) chapter.getSelectedItem()));
      expression.setChapter(selfChapter);

      Settings.setRememberChapterForInput(selfChapter.getName());

      expression.setAdditionalInformation(cleanText(extraInfo.getText()));

      if (((String) databaseNameField.getSelectedItem()).isBlank())
      {
         expression.getChapter().setDatabaseName(Database.SELF.getName(common));
      }
      else
      {
         expression.getChapter().setDatabaseName(
               cleanText((String) databaseNameField.getSelectedItem()));
      }

      Settings.setRememberDatabaseForInput(
            expression.getChapter().getDatabaseName(common));

      if (indexField.getText().isBlank())
      {
         expression.setSortingIndex(String.valueOf(SortingIndex.getCounter()));
      }
      else
      {
         expression.setSortingIndex(cleanText(indexField.getText()));
      }
      SortingIndex.setCounter(expression.getSortingIndex());

      expression.setLastModified(LocalDateTime.now());

      expression.setVisible(visible.isSelected());
   }

   private String cleanTextAndNoComma(String text)
   {
      return TextHelper.cleanTextSanitizeForCsv(text);
   }

   private String cleanText(String text)
   {
      return TextHelper.cleanText(text);
   }

   private void setExpressionForReset(Common common, View view)
   {
      setExpression(common, view, this.expression, this.newExpression);
   }

   public void setExpression(Common common, View view, Expression expression,
         boolean newExpression)
   {
      this.save = false;
      this.expression = expression;
      this.newExpression = newExpression;
      setWritingDirection(expression.getLL().getLltype());
      if (!newExpression)
      {
         this.remakeAllBoxes(expression);

         if (expression.getLL().isSwedish())
         {
            this.keyboard.setKeyboard(Selection.SWEDISH);
            this.language.setSwedishFieldText(expression.getLL().getSwedish());
         }
         else if (expression.getLL().isPleneDefektiv())
         {
            this.keyboard.setKeyboard(Selection.PLENE_DEFEKTIV);
            this.language
                  .setPleneFieldText(expression.getLL().getHebrewPlene());
            this.language
                  .setDefektivFieldText(expression.getLL().getHebrewDefektiv());
         }
         else if (expression.getLL().isSimpleHebrew())
         {
            this.keyboard.setKeyboard(Selection.SIMPLE);
            this.language.setHebrewFieldText(expression.getLL().getHebrew());
         }
         else if (expression.getLL().isGerman())
         {
            this.keyboard.setKeyboard(Selection.GERMAN);
            this.language.setGermanFieldText(expression.getLL().getGerman());
         }
      }

      this.chapter.setModel(Data.getChapterComboBoxModel());
      if (newExpression)
      {
         this.chapter.setSelectedItem(Settings.getRememberChapterForInput());
      }
      else
      {
         this.chapter.setSelectedItem(expression.getChapter().getName());
      }

      this.indexField.setText(expression.getSortingIndex());

      this.ownLanguage.setText(expression.getOwnLanguage());

      this.searchwordsSetGerman = new HashSet<>();
      for (String word : expression.getSearchwordsGerman())
      {
         this.searchwordsSetGerman.add(word);
      }
      this.searchwordsJListOwn.setModel(getSearchwordsModelGerman());

      this.searchwordsSetHebrew = new HashSet<>();
      for (String word : expression.getSearchwordsHebrew())
      {
         this.searchwordsSetHebrew.add(word);
      }
      this.searchwordsJListNew.setModel(getSearchwordsModelHebrew());

      if (newExpression)
      {
         expressionKindTable.setModel(ExpressionKind.getModelForMultiselect());
         binjanBox.setSelectedIndex(0);
         genderBox.setSelectedIndex(0);
         grammaticalPersonBox.setSelectedIndex(0);
         numerusBox.setSelectedIndex(0);
         verbTimesBox.setSelectedIndex(0);
         extraInfo.setText("");
         imageButton.setVisible(false);
      }
      else
      {
         imageButton.setVisible(true);
         imageButton.addActionListener(
               _ -> connector.openPictureView(common, view, expression));
         Definitions definitions = expression.getDefinitions();
         Set<ExpressionKind> kinds = definitions.getExpressionKindSet();
         expressionKindTable
               .setModel(ExpressionKind.getModelForMultiselect(kinds));

         ExpressionKind kind;
         if (kinds.stream().findAny().isPresent())
         {
            kind = kinds.stream().findAny().get();
            binjanBox.setSelectedItem(
                  definitions.getGrammaticalEnum(kind, Binjan.class));
            genderBox.setSelectedItem(
                  definitions.getGrammaticalEnum(kind, Gender.class));
            grammaticalPersonBox.setSelectedItem(definitions
                  .getGrammaticalEnum(kind, GrammaticalPerson.class));
            numerusBox.setSelectedItem(
                  definitions.getGrammaticalEnum(kind, Numerus.class));
            verbTimesBox.setSelectedItem(
                  definitions.getGrammaticalEnum(kind, VerbTimes.class));

            showGrammaticalParentEnums(
                  ExpressionKind.getSetOfGrammaticalParentEnums(kinds));

            scrollPaneExpressionTable.getVerticalScrollBar()
                  .setMaximum(expressionKindTable.getMaxScrollValue());
            scrollPaneExpressionTable.getVerticalScrollBar()
                  .setValue(expressionKindTable.getScrollValue());
         }
         extraInfo.setText(expression.getAdditionalInformation());
      }

      if (expression.isDoChange())
      {
         databaseNameField.setModel(Data.getOwnDatabasesComboBoxModel(common));
      }
      else
      {
         databaseNameField.setModel(Data.getInternalDatabasesComboBoxModel(common));
      }

      if (newExpression)
      {
         databaseNameField
               .setSelectedItem(Settings.getRememberDatabaseForInput());
      }
      else
      {
         databaseNameField
               .setSelectedItem(expression.getChapter().getDatabaseName(common));
      }

      lastModiefiedLabel.setText(translator.realisticTranslate(Translation.VOM)
            + " "
            + expression.getLastModified()
                  .format(DateTimeFormatter.ofPattern(
                        translator.realisticTranslate(Translation._DATE_TIME)))
            + " " + translator.realisticTranslate(Translation.UHR));

      visible.setSelected(expression.isVisible());
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

   public boolean isFrozen()
   {
      return frozen;
   }

   public boolean setFrozen(boolean frozen)
   {
      boolean works = !frozen;
      try
      {
         this.ownLanguage.setEditable(works);
         this.language.setEditable(works);
         this.keyboard.setFrozen(frozen);
         this.saveButton.setEnabled(works);
         this.saveButton.setVisible(works);
         this.restoreButton.setEnabled(works);
         this.restoreButton.setVisible(works);
         this.newSearchwordOwn.setEditable(works);
         this.newSearchwordOwn.setVisible(works);
         this.deleteSearchwordButtonGerman.setVisible(works);
         this.newSearchwordNew.setEditable(works);
         this.newSearchwordNew.setVisible(works);
         this.deleteSearchwordButtonHebrew.setVisible(works);
         this.expressionKindTable.setFrozen(frozen);
         this.databaseNameField.setEditable(works);
         this.extraInfo.setEditable(works);
         this.binjanBox.setEditable(works);
         this.genderBox.setEditable(works);
         this.numerusBox.setEditable(works);
         this.grammaticalPersonBox.setEditable(works);
         this.binjanBox.setEditable(works);
         this.verbTimesBox.setEditable(works);
         this.binjanBox.setEnabled(works);
         this.genderBox.setEnabled(works);
         this.numerusBox.setEnabled(works);
         this.grammaticalPersonBox.setEnabled(works);
         this.binjanBox.setEnabled(works);
         this.verbTimesBox.setEnabled(works);
         this.copyButton.setVisible(works);
         this.cutButton.setVisible(works);
         this.pasteButton.setVisible(works);
         this.indexField.setEditable(works);
         this.visible.setEnabled(works);
         this.frozen = frozen;
         return true;
      }
      catch (Exception e)
      {
         this.frozen = false;
         return false;
      }

   }

   public KeyboardLanguage getKeyboard()
   {
      return keyboard;
   }

   public void setWritingDirection(LLType llType)
   {
      switch (Settings.getMyWritingDirection())
      {
      case LEFT_TO_RIGHT:
         ownLanguage
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         newSearchwordOwn
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         searchwordsJListOwn
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         this.indexField
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         break;
      case RIGHT_TO_LEFT:
         ownLanguage
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         newSearchwordOwn
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         searchwordsJListOwn
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         this.indexField
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         break;
      }

      switch (llType)
      {
      case GERMAN:
      case SWEDISH:
         this.newSearchwordNew
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         this.searchwordsJListNew
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         break;
      case HEBREW:
         this.newSearchwordNew
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         this.searchwordsJListNew
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         break;
      case UNKOWN:
         switch (Settings.getLanguageInput())
         {
         case GERMAN:
         case SWEDISH:
            this.newSearchwordNew
                  .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            this.searchwordsJListNew
                  .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            break;
         case PLENE_DEFEKTIV:
         case SIMPLE:
            this.newSearchwordNew
                  .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            this.searchwordsJListNew
                  .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         }
      }
   }
}
