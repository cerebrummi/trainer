package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

import vokabeltrainer.TextImage;
import vokabeltrainer.cmd.TextHelper;
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.keyboards.KeyboardGermanStandard;
import vokabeltrainer.keyboards.KeyboardHebrewAllLetters;
import vokabeltrainer.keyboards.KeyboardSwedishStandard;
import vokabeltrainer.panels.dictionary.Action;
import vokabeltrainer.panels.dictionary.DataButton;
import vokabeltrainer.panels.dictionary.Tabulator;
import vokabeltrainer.panels.dictionary.DictionaryControllerConnector;
import vokabeltrainer.panels.dictionary.DictionaryViewConnector;
import vokabeltrainer.panels.dictionary.SearchAction;
import vokabeltrainer.panels.list.ChapterList;
import vokabeltrainer.panels.list.ChapterListSelectionModel;
import vokabeltrainer.panels.list.table.DatabaseTableModel;
import vokabeltrainer.panels.list.table.DatabaseTableMultiselect;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.ExpressionColumnModel;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableRow2;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableSingleselect;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.SearchType;
import vokabeltrainer.types.SortingType;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKindItem;

public class DictionaryView extends JPanel implements DictionaryViewConnector
{
   private static final long serialVersionUID = 9130321171813967337L;

   private ButtonGroup languageGroup;
   private ButtonGroup searchTypeGroupHebrew;
   private ButtonGroup searchTypeGroupGerman;
   private ExpressionTable table;
   private JPanel tablePanel;
   private JButton copyAllSelectedButton;
   private JButton copyInTableSelectedButton;
   private JButton copyTableButton;
   private JButton selectAllInTableButton;
   private JButton clearInTableSelectedButton;
   private JButton clearAllSelectedButton;
   private JButton deleteAllSelectedButton;
   private JButton deleteInTableSelectedButton;
   private JButton wasteBinButton;
   private JButton shredderButton;
   private JTabbedPane tabbedPane;
   private ChapterList chapterList;
   private JPanel chapterPanel;
   private JPanel swapPanel;
   private CardLayout cardLayout;
   private JTextField searchPhraseMy;
   private JTextField searchPhraseOther;
   private JButton otherSearchButton;
   private JButton mySearchButton;
   private JButton tableInfoButton;
   private JPanel horizontalLanguagePanel;
   private DictionaryControllerConnector connector;
   private ChapterListSelectionModel listSelectionModel;
   private ListSelectionListener listSelectionListener;
   private JScrollPane tableScroller;
   private ExpressionKindTableSingleselect expressionKindTable;
   private JRadioButton sortForDateBox;
   private JRadioButton sortForIndexBox;
   private JRadioButton sortForAlphabetBox;
   private ButtonGroup sortingGroup;
   private JButton moveToChapterButton;
   private JComboBox<String> chapterChoiceBox;
   private JComboBox<String> databaseChoiceBox;
   private JButton moveToDatabaseButton;
   private Translator translator;

   private Component keyboard;

   private JPanel otherSearch;

   private JPanel searchVertical;

   private JPanel dataPanel;

   private DatabaseTableModel databaseTableModel;

   public DictionaryView(App app, Common common, Model model, View view, DictionaryControllerConnector connector)
   {
      translator = common.getTranslator();
      this.connector = connector;
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(app.appColors.getBackground());

      JPanel layout = new JPanel();
      TrainLayout layoutLayout = new TrainLayout(layout, 15);
      layout.setLayout(layoutLayout);
      layout.setOpaque(false);
      add(layout);

      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setOpaque(false);
      vertical.setBackground(app.appColors.dictionary.getBackground());

      horizontalLanguagePanel = new JPanel();
      horizontalLanguagePanel
            .setLayout(new TrainLayout(horizontalLanguagePanel, 15));
      horizontalLanguagePanel.setBackground(app.appColors.dictionary.getBackground());
      horizontalLanguagePanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(app, Translation.SPRACHEN)));
      languageGroup = new ButtonGroup();
      initLanguageButtonGroup(app, languageGroup);
      Enumeration<AbstractButton> enumeration1 = languageGroup.getElements();
      while (enumeration1.hasMoreElements())
      {
         AbstractButton button = enumeration1.nextElement();
         button.setBackground(app.appColors.dictionary.getButton());
         button.setForeground(app.appColors.dictionary.getButtonForeground());
         button.setMinimumSize(new Dimension(90, 30));
         button.setMaximumSize(new Dimension(120, 60));
         button.addActionListener(
               _ -> this.connector.switchLanguage(app, common, model, view, button.getActionCommand()));
         horizontalLanguagePanel.add(button);
      }

      JPanel horizontalSortPanel = new JPanel();
      horizontalSortPanel.setLayout(new TrainLayout(horizontalSortPanel, 15));
      horizontalSortPanel.setOpaque(true);
      horizontalSortPanel.setBackground(app.appColors.dictionary.getBackground());
      horizontalSortPanel.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(app, Translation.TABELLE_SORTIEREN_NACH)));

      sortForAlphabetBox = new JRadioButton(
            translator.realisticTranslate(app, Translation.ALFABET));
      sortForAlphabetBox.setFont(app.appFonts.radioButtonFont);
      sortForAlphabetBox.setMinimumSize(new Dimension(70, 30));
      sortForAlphabetBox.setMaximumSize(new Dimension(100, 60));
      sortForAlphabetBox.setActionCommand(SortingType.ALPHABET.name());

      sortForDateBox = new JRadioButton(
            translator.realisticTranslate(app, Translation.DATUM));
      sortForDateBox.setFont(app.appFonts.radioButtonFont);
      sortForDateBox.setMinimumSize(new Dimension(70, 30));
      sortForDateBox.setMaximumSize(new Dimension(100, 60));
      sortForDateBox.setActionCommand(SortingType.DATE.name());

      sortForIndexBox = new JRadioButton(
            translator.realisticTranslate(app, Translation.INDEX));
      sortForIndexBox.setFont(app.appFonts.radioButtonFont);
      sortForIndexBox.setMinimumSize(new Dimension(70, 30));
      sortForIndexBox.setMaximumSize(new Dimension(100, 60));
      sortForIndexBox.setActionCommand(SortingType.INDEX.name());

      sortingGroup = new ButtonGroup();
      sortingGroup.add(sortForAlphabetBox);
      sortingGroup.add(sortForDateBox);
      sortingGroup.add(sortForIndexBox);

      sortForIndexBox.setSelected(true);

      horizontalSortPanel.add(sortForAlphabetBox);
      horizontalSortPanel.add(sortForDateBox);
      horizontalSortPanel.add(sortForIndexBox);
      horizontalLanguagePanel.add(horizontalSortPanel);

      tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(true);
      tabbedPane.setBackground(app.appColors.dictionary.getBackground());
      tabbedPane.setFont(app.appFonts.buttonFont);
      tabbedPane.addTab(translator.realisticTranslate(app, Translation.DATENBANK),
            initDatabaseTab());
      tabbedPane.addTab(translator.realisticTranslate(app, Translation.LEKTIONEN),
            initChaptersTab());
      tabbedPane.addTab(translator.realisticTranslate(app, Translation.SUCHE),
            initSearchTab(app, common, model, view));
      tabbedPane.addTab(translator.realisticTranslate(app, Translation.WORTARTEN),
            initExpressionKindsTab(app, common, model, view));
      tabbedPane.addTab(translator.realisticTranslate(app, Translation.AUSWAHL),
            initSelectedTab(app, common, model));
      tabbedPane.setMinimumSize(new Dimension(420, 400));
      tabbedPane.setMaximumSize(new Dimension(600, 700));

      vertical.add(horizontalLanguagePanel);
      vertical.add(tabbedPane);

      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setMinimumSize(new Dimension(420, 400));
      tablePanel.setMaximumSize(new Dimension(513, 800));
      tablePanel.setOpaque(false);

      JPanel filler2 = new JPanel();
      filler2.setMinimumSize(new Dimension(1, 1));
      filler2.setMaximumSize(new Dimension(1000, 2000));
      filler2.setOpaque(false);

      JPanel filler3 = new JPanel();
      filler3.setMinimumSize(new Dimension(1, 1));
      filler3.setMaximumSize(new Dimension(1000, 2000));
      filler3.setOpaque(false);

      layout.add(vertical);
      layout.add(filler2);
      layout.add(tablePanel);
      layout.add(filler3);
      layout.add(initServicePanel(app));

      Tabulator.setTabShowing(Tabulator.CHAPTER_TAB);

      initChapterController(app, common, model, view);

      loadDatabases(app, model);
      initController(app, common, model, view);
   }

   private void initChapterController(App app, Common common, Model model, View view)
   {
      listSelectionListener = new ListSelectionListener()
      {

         @Override
         public void valueChanged(ListSelectionEvent event)
         {
            if (!event.getValueIsAdjusting())
            {
               connector.displayChapterWhich(app, common, model, view, getSelectedChapter());
            }
         }

      };
   }

   private Component initSearchTab(App app, Common common, Model model, View view)
   {
      JPanel outerWrapper = new JPanel();
      BullsEyeLayout outerLayout = new BullsEyeLayout(outerWrapper);
      outerWrapper.setLayout(outerLayout);
      outerWrapper.setOpaque(true);
      outerWrapper.setBackground(app.appColors.dictionary.getBackground());

      searchVertical = new JPanel();
      searchVertical.setLayout(new TotemLayout(searchVertical));
      searchVertical.setOpaque(false);

      initSearchPanel(app, common, model, view, Direction.OWN_TO_NEW);

      outerWrapper.add(searchVertical);
      return outerWrapper;
   }

   public void initSearchPanel(App app, Common common, Model model, View view, Direction selectedLanguage)
   {
      JPanel mySearch = new JPanel();
      mySearch.setLayout(new TotemLayout(mySearch, 5));
      mySearch.setOpaque(false);

      mySearch.add(new JLabel(
            translator.realisticTranslate(app, Translation.STELLEN_SIE_UM)));

      searchPhraseMy = new JTextField();
      searchPhraseMy.setBorder(new TitledBorder(translator
            .realisticTranslate(app, Translation.WORT_AUF_DEUTSCH_EINGEBEN)));
      mySearch.add(searchPhraseMy);
      searchPhraseMy.setMinimumSize(
            new Dimension(app.settings.getKeyboardWidth() + 50, 70));
      searchPhraseMy.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() + 250, 70));
      setWritingDirection(app);

      JPanel filler = new JPanel();
      filler.setOpaque(false);
      filler.setMinimumSize(
            new Dimension(app.settings.getKeyboardWidth() + 50, 100));
      filler.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() + 250, 270));
      mySearch.add(filler);

      searchTypeGroupGerman = new ButtonGroup();
      mySearch.add(initSearchRadioButtonPanel(app, common, searchTypeGroupGerman,
            Direction.OWN_TO_NEW));

      mySearchButton = new JButton(
            translator.realisticTranslate(app, Translation.SUCHE_STARTEN));
      mySearchButton.setBackground(app.appColors.dictionary.getButton());
      mySearchButton.setForeground(app.appColors.dictionary.getButtonForeground());
      mySearchButton.setFont(app.appFonts.buttonFont);
      mySearchButton.setIcon(new ImageIcon(app.appImages.getSearch()));
      JPanel wrapper = new JPanel(new FlowLayout());
      wrapper.setOpaque(false);
      wrapper.setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 30));
      wrapper.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() + 50, 50));
      wrapper.add(mySearchButton);
      mySearch.add(wrapper);

      otherSearch = new JPanel();
      otherSearch.setLayout(new TotemLayout(otherSearch, 5));
      otherSearch.setOpaque(false);

      searchPhraseOther = new JTextField();
      searchPhraseOther.setBorder(new TitledBorder(translator
            .realisticTranslate(app, Translation.WORT_AUF_HEBRAISCH_EINGEBEN)));

      JPanel filler2 = new JPanel();
      filler2.setOpaque(false);
      filler2.setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 5));
      filler2.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() + 50, 14));

      searchTypeGroupHebrew = new ButtonGroup();

      switch (app.settings.getLanguageInput())
      {
      case GERMAN:
         KeyboardGermanStandard germanKeyboardMaker = new KeyboardGermanStandard(app,
               searchPhraseOther, new ArrayList<JTextComponent>(), 70);
         keyboard = germanKeyboardMaker.makeTextfieldWithRegularKeyboard(app);
         break;
      case PLENE_DEFEKTIV:
      case SIMPLE:
         keyboard = new KeyboardHebrewAllLetters(app, searchPhraseOther,
               new ArrayList<JTextComponent>(), 70, true);
         break;
      case SWEDISH:
         KeyboardSwedishStandard swedishKeyboardMaker = new KeyboardSwedishStandard(app,
               searchPhraseOther, new ArrayList<JTextComponent>(), 70);
         keyboard = swedishKeyboardMaker.makeTextfieldWithRegularKeyboard(app);
         break;
      }

      otherSearchButton = new JButton(
            translator.realisticTranslate(app, Translation.SUCHE_STARTEN));
      otherSearchButton.setBackground(app.appColors.dictionary.getButton());
      otherSearchButton.setForeground(app.appColors.dictionary.getButtonForeground());
      otherSearchButton.setFont(app.appFonts.buttonFont);
      otherSearchButton.setIcon(new ImageIcon(app.appImages.getSearch()));
      JPanel wrapper1 = new JPanel(new FlowLayout());
      wrapper1.setOpaque(false);
      wrapper1.setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 30));
      wrapper1.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() + 50, 50));
      wrapper1.add(otherSearchButton);

      otherSearch.add(filler2);
      otherSearch.add(new JLabel(
            translator.realisticTranslate(app, Translation.SPRACHE_UMSTELLEN)));
      otherSearch.add(keyboard);
      otherSearch.add(initSearchRadioButtonPanel(app, common, searchTypeGroupHebrew,
            Direction.NEW_TO_OWN));
      otherSearch.add(wrapper1);

      cardLayout = new CardLayout();
      swapPanel = new JPanel(cardLayout);
      swapPanel.setOpaque(false);
      swapPanel.setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 420));
      swapPanel.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() + 50, 620));
      mySearch.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
      otherSearch.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
      swapPanel.add(Direction.OWN_TO_NEW.name(), mySearch);
      swapPanel.add(Direction.NEW_TO_OWN.name(), otherSearch);

      cardLayout.show(swapPanel, selectedLanguage.name());

      searchVertical.add(swapPanel);

      otherSearchButton.addActionListener(_ -> connector.searchOtherLanguage(app, common, model, view));

      mySearchButton.addActionListener(_ -> connector.searchMyLanguage(app, common, model, view));
   }

   public void setWritingDirection(App app)
   {
      switch (app.settings.getMyWritingDirection())
      {
      case LEFT_TO_RIGHT:
         searchPhraseMy
               .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
         break;
      case RIGHT_TO_LEFT:
         searchPhraseMy
               .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         break;
      }
   }

   private JPanel initSearchRadioButtonPanel(App app, Common common, ButtonGroup group,
         Direction language)
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 5));
      vertical.setOpaque(false);

      for (SearchType type : SearchType.values())
      {
         JRadioButton radioButton = new JRadioButton(type.getMeaning(app, common, language));
         radioButton.setActionCommand(type.name());
         radioButton.setBackground(app.appColors.dictionary.getButton());
         radioButton.setForeground(app.appColors.dictionary.getButtonForeground());
         if (SearchType.WORDSTART.equals(type))
         {
            radioButton.setSelected(true);
         }
         radioButton.setFont(app.appFonts.buttonFont);
         JPanel wrapper = new JPanel(new FlowLayout());
         wrapper.setOpaque(false);
         wrapper.setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 25));
         wrapper.setMaximumSize(new Dimension(app.settings.getKeyboardWidth(), 25));
         wrapper.add(radioButton);
         vertical.add(wrapper);
         group.add(radioButton);
      }

      return vertical;
   }

   private Component initSelectedTab(App app, Common common, Model model)
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);
      vertical.setOpaque(false);
      vertical.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

      JPanel horizontalMoveToChapterPanel = new JPanel();
      horizontalMoveToChapterPanel.setOpaque(false);
      TrainLayout horizontalMoveToChapterLayout = new TrainLayout(
            horizontalMoveToChapterPanel, 15);
      horizontalMoveToChapterPanel.setLayout(horizontalMoveToChapterLayout);

      chapterChoiceBox = new JComboBox<>();
      chapterChoiceBox.setEditable(true);
      chapterChoiceBox.setModel(model.data.getChapterComboBoxModel(app));
      chapterChoiceBox.setMinimumSize(new Dimension(250, 30));
      chapterChoiceBox.setMaximumSize(new Dimension(250, 30));

      moveToChapterButton = new JButton(translator
            .realisticTranslate(app, Translation.AUSWAHL_ZUR_LEKTION_VERSCHIEBEN));
      moveToChapterButton.setBackground(app.appColors.dictionary.getButton());
      moveToChapterButton.setForeground(app.appColors.dictionary.getButtonForeground());
      moveToChapterButton.setFont(app.appFonts.buttonFont);

      horizontalMoveToChapterPanel.add(chapterChoiceBox);
      horizontalMoveToChapterPanel.add(moveToChapterButton);

      JPanel horizontalMoveToDatabasePanel = new JPanel();
      horizontalMoveToDatabasePanel.setOpaque(false);
      TrainLayout horizontalMoveToDatabaseLayout = new TrainLayout(
            horizontalMoveToDatabasePanel, 15);
      horizontalMoveToDatabasePanel.setLayout(horizontalMoveToDatabaseLayout);

      databaseChoiceBox = new JComboBox<>();
      databaseChoiceBox.setEditable(true);
      databaseChoiceBox.setModel(model.data.getOwnDatabasesComboBoxModel(app, common));
      databaseChoiceBox.setMinimumSize(new Dimension(250, 30));
      databaseChoiceBox.setMaximumSize(new Dimension(250, 30));

      moveToDatabaseButton = new JButton(translator
            .realisticTranslate(app, Translation.AUSWAHL_ZUR_DATENBANK_VERSCHIEBEN));
      moveToDatabaseButton.setBackground(app.appColors.dictionary.getButton());
      moveToDatabaseButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      moveToDatabaseButton.setFont(app.appFonts.buttonFont);

      horizontalMoveToDatabasePanel.add(databaseChoiceBox);
      horizontalMoveToDatabasePanel.add(moveToDatabaseButton);

      vertical.add(horizontalMoveToChapterPanel);
      vertical.add(horizontalMoveToDatabasePanel);

      return vertical;
   }

   private JPanel initDatabaseTab()
   {
      dataPanel = new JPanel();
      dataPanel.setLayout(new BorderLayout());
      dataPanel.setOpaque(false);
      return dataPanel;
   }

   private JPanel initChaptersTab()
   {
      chapterPanel = new JPanel();
      chapterPanel.setLayout(new BorderLayout());
      chapterPanel.setOpaque(false);
      return chapterPanel;
   }

   private JPanel initExpressionKindsTab(App app, Common common, Model model, View view)
   {
      JPanel vertical1 = new JPanel(new BorderLayout());
      vertical1.setOpaque(true);
      vertical1.setBackground(app.appColors.dictionary.getBackground());
      expressionKindTable = new ExpressionKindTableSingleselect(app, common, model, view, 
            ExpressionKind.getModelForSingleselect(), 300, connector);
      JScrollPane scroller = new JScrollPane(expressionKindTable);
      scroller.setMinimumSize(new Dimension(400, 470));
      scroller.setMaximumSize(new Dimension(400, 470));
      scroller.setBorder(BorderFactory.createTitledBorder(translator
            .realisticTranslate(app, Translation.DOPPELCLICK)));

      JPanel scrollerWrapper = new JPanel();
      scrollerWrapper.setOpaque(false);
      scrollerWrapper.setBackground(app.appColors.getTransparent());
      BullsEyeLayout scrollerWrapperLayout = new BullsEyeLayout(
            scrollerWrapper);
      scrollerWrapper.setLayout(scrollerWrapperLayout);

      scrollerWrapper.add(scroller);
      vertical1.add(scrollerWrapper, BorderLayout.CENTER);

      return vertical1;
   }

   private Component initServicePanel(App app)
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical));

      copyAllSelectedButton = new JButton(
            translator.realisticTranslate(app, Translation.GESAMTAUSWAHL_KOPIEREN));
      copyAllSelectedButton.setBackground(app.appColors.dictionary.getButton());
      copyAllSelectedButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      copyAllSelectedButton.setFont(app.appFonts.buttonFont);
      copyAllSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      copyAllSelectedButton.setIcon(new ImageIcon(app.appImages.getCopy()));
      copyAllSelectedButton.setToolTipText(translator.realisticTranslate(app, 
            Translation.NUR_SELBST_EINGEGEBENE_VOKABELN_KOENNEN_KOPIERT_WERDEN));
      copyAllSelectedButton.setMinimumSize(new Dimension(200, 40));
      copyAllSelectedButton.setMaximumSize(new Dimension(600, 40));

      copyInTableSelectedButton = new JButton(translator
            .realisticTranslate(app, Translation.TABELLENAUSWAHL_KOPIEREN));
      copyInTableSelectedButton.setBackground(app.appColors.dictionary.getButton());
      copyInTableSelectedButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      copyInTableSelectedButton.setFont(app.appFonts.buttonFont);
      copyInTableSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      copyInTableSelectedButton
            .setIcon(new ImageIcon(app.appImages.getCopy()));
      copyInTableSelectedButton.setToolTipText(translator.realisticTranslate(app, 
            Translation.NUR_SELBST_EINGEGEBENE_VOKABELN_KOENNEN_KOPIERT_WERDEN));
      copyInTableSelectedButton.setMinimumSize(new Dimension(200, 40));
      copyInTableSelectedButton.setMaximumSize(new Dimension(600, 40));

      copyTableButton = new JButton(
            translator.realisticTranslate(app, Translation.TABELLE_KOPIEREN));
      copyTableButton.setBackground(app.appColors.dictionary.getButton());
      copyTableButton.setForeground(app.appColors.dictionary.getButtonForeground());
      copyTableButton.setFont(app.appFonts.buttonFont);
      copyTableButton.setHorizontalAlignment(SwingConstants.LEFT);
      copyTableButton.setIcon(new ImageIcon(app.appImages.getCopy()));
      copyTableButton.setToolTipText(translator.realisticTranslate(app, 
            Translation.NUR_SELBST_EINGEGEBENE_VOKABELN_KOENNEN_KOPIERT_WERDEN));
      copyTableButton.setMinimumSize(new Dimension(200, 40));
      copyTableButton.setMaximumSize(new Dimension(600, 40));

      selectAllInTableButton = new JButton(
            translator.realisticTranslate(app, Translation.TABELLE_AUSWAEHLEN));
      selectAllInTableButton.setBackground(app.appColors.dictionary.getButton());
      selectAllInTableButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      selectAllInTableButton.setFont(app.appFonts.buttonFont);
      selectAllInTableButton.setHorizontalAlignment(SwingConstants.LEFT);
      selectAllInTableButton
            .setIcon(new ImageIcon(app.appImages.getSelect()));
      selectAllInTableButton.setMinimumSize(new Dimension(200, 40));
      selectAllInTableButton.setMaximumSize(new Dimension(600, 40));

      clearAllSelectedButton = new JButton(
            translator.realisticTranslate(app, Translation.GESAMTAUSWAHL_AUFHEBEN));
      clearAllSelectedButton.setBackground(app.appColors.dictionary.getButton());
      clearAllSelectedButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      clearAllSelectedButton.setFont(app.appFonts.buttonFont);
      clearAllSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      clearAllSelectedButton
            .setIcon(new ImageIcon(app.appImages.getClear()));
      clearAllSelectedButton.setMinimumSize(new Dimension(200, 40));
      clearAllSelectedButton.setMaximumSize(new Dimension(600, 40));

      clearInTableSelectedButton = new JButton(translator
            .realisticTranslate(app, Translation.TABELLENAUSWAHL_AUFHEBEN));
      clearInTableSelectedButton.setBackground(app.appColors.dictionary.getButton());
      clearInTableSelectedButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      clearInTableSelectedButton.setFont(app.appFonts.buttonFont);
      clearInTableSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      clearInTableSelectedButton
            .setIcon(new ImageIcon(app.appImages.getClear()));
      clearInTableSelectedButton.setMinimumSize(new Dimension(200, 40));
      clearInTableSelectedButton.setMaximumSize(new Dimension(600, 40));

      deleteInTableSelectedButton = new JButton(translator
            .realisticTranslate(app, Translation.TABELLENAUSWAHL_LOESCHEN));
      deleteInTableSelectedButton.setBackground(app.appColors.dictionary.getButton());
      deleteInTableSelectedButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      deleteInTableSelectedButton.setFont(app.appFonts.buttonFont);
      deleteInTableSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      deleteInTableSelectedButton
            .setIcon(new ImageIcon(app.appImages.getDeleteWord()));
      deleteInTableSelectedButton.setMinimumSize(new Dimension(200, 40));
      deleteInTableSelectedButton.setMaximumSize(new Dimension(600, 40));

      deleteAllSelectedButton = new JButton(
            translator.realisticTranslate(app, Translation.GESAMTAUSWAHL_LOESCHEN));
      deleteAllSelectedButton.setBackground(app.appColors.dictionary.getButton());
      deleteAllSelectedButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      deleteAllSelectedButton.setFont(app.appFonts.buttonFont);
      deleteAllSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      deleteAllSelectedButton
            .setIcon(new ImageIcon(app.appImages.getDeleteWord()));
      deleteAllSelectedButton.setMinimumSize(new Dimension(200, 40));
      deleteAllSelectedButton.setMaximumSize(new Dimension(600, 40));

      JPanel copyPanel = new JPanel();
      copyPanel.setLayout(new TotemLayout(copyPanel, 10));
      copyPanel.setBackground(app.appColors.dictionary.getBackground());
      copyPanel.setBorder(BorderFactory.createMatteBorder(5, 3, 5, 3,
            app.appColors.dictionary.getBackground()));
      copyPanel.add(copyInTableSelectedButton);
      copyPanel.add(copyTableButton);
      copyPanel.add(copyAllSelectedButton);

      JPanel selectUnselectPanel = new JPanel();
      selectUnselectPanel.setLayout(new TotemLayout(selectUnselectPanel, 10));
      selectUnselectPanel.setBackground(app.appColors.dictionary.getBackground());
      selectUnselectPanel.setBorder(BorderFactory.createMatteBorder(5, 3, 5, 3,
            app.appColors.dictionary.getBackground()));
      selectUnselectPanel.add(selectAllInTableButton);
      selectUnselectPanel.add(clearInTableSelectedButton);
      selectUnselectPanel.add(clearAllSelectedButton);

      JPanel deletePanel = new JPanel();
      deletePanel.setLayout(new TotemLayout(deletePanel, 10));
      deletePanel.setBackground(new Color(169, 136, 103));
      deletePanel.setBorder(BorderFactory.createMatteBorder(5, 3, 5, 3,
            new Color(169, 136, 103)));
      deletePanel.add(deleteInTableSelectedButton);
      deletePanel.add(deleteAllSelectedButton);

      JPanel infoPanel = new JPanel();
      infoPanel.setLayout(new TrainLayout(infoPanel, 10));
      infoPanel.setBackground(app.appColors.dictionary.getBackground());
      infoPanel.setBorder(BorderFactory.createMatteBorder(5, 3, 5, 3,
            app.appColors.dictionary.getBackground()));

      JLabel infoLabel = new JLabel(
            translator.realisticTranslate(app, Translation.TABELLE_BEDIENEN));
      infoLabel.setForeground(app.appColors.dictionary.getInfoTextForeground());
      infoLabel.setFont(app.appFonts.buttonFont);
      infoLabel.setMinimumSize(new Dimension(200, 40));
      infoLabel.setMaximumSize(new Dimension(600, 40));

      tableInfoButton = new JButton(
            new ImageIcon(app.appImages.getInfoButtonIcon()));
      tableInfoButton.setBackground(app.appColors.getTransparent());
      tableInfoButton.setMinimumSize(new Dimension(20, 40));
      tableInfoButton.setMaximumSize(new Dimension(20, 40));
      tableInfoButton.setMargin(new Insets(0, 0, 0, 0));
      infoPanel.add(tableInfoButton);
      infoPanel.add(infoLabel);

      JPanel trashPanel = new JPanel();
      TrainLayout trashPanelLayout = new TrainLayout(trashPanel, 15);
      trashPanel.setLayout(trashPanelLayout);
      trashPanel.setOpaque(false);
      trashPanel.setMinimumSize(new Dimension(300, 100));
      trashPanel.setMaximumSize(new Dimension(600, 120));

      JPanel trashIconPanel = new JPanel(new GridLayout(3, 2));
      trashIconPanel.setOpaque(false);

      wasteBinButton = new JButton(
            new ImageIcon(app.appImages.getTrashcan()));
      wasteBinButton.setBorder(
            BorderFactory.createLineBorder(app.appColors.dictionary.getWasteBinBorder(), 2));
      wasteBinButton.setBorderPainted(true);
      wasteBinButton.setContentAreaFilled(false);
      wasteBinButton.setFocusPainted(false);

      shredderButton = new JButton(
            new ImageIcon(app.appImages.getShredder()));
      shredderButton.setBorder(
            BorderFactory.createLineBorder(app.appColors.dictionary.getShredderButtonBorder(), 2));
      shredderButton.setBorderPainted(true);
      shredderButton.setContentAreaFilled(false);
      shredderButton.setFocusPainted(false);

      JPanel filler1 = new JPanel();
      filler1.setOpaque(false);

      JPanel filler2 = new JPanel();
      filler2.setOpaque(false);

      JPanel filler3 = new JPanel();
      filler3.setOpaque(false);

      JPanel filler4 = new JPanel();
      filler4.setOpaque(false);

      trashIconPanel.add(filler1);
      trashIconPanel.add(filler2);
      trashIconPanel.add(wasteBinButton);
      trashIconPanel.add(shredderButton);
      trashIconPanel.add(filler3);
      trashIconPanel.add(filler4);

      trashPanel.add(trashIconPanel);

      JPanel filler = new JPanel();
      filler.setMinimumSize(new Dimension(1, 1));
      filler.setMaximumSize(new Dimension(800, 800));
      filler.setOpaque(false);

      vertical.add(selectUnselectPanel);
      vertical.add(copyPanel);
      vertical.add(deletePanel);
      vertical.add(infoPanel);
      vertical.add(trashPanel);
      vertical.add(filler);
      return vertical;
   }

   private void initController(App app, Common common, Model model, View view)
   {
      tabbedPane.addChangeListener(
            _ -> connector.tabbedPaneChanged(app, common, model, view, tabbedPane.getSelectedIndex()));

      copyAllSelectedButton.addActionListener(
            _ -> connector.copyAllSelectedExpressions(app, model, view, SortingType
                  .valueOf(sortingGroup.getSelection().getActionCommand())));

      copyTableButton
            .addActionListener(_ -> connector.copyExpressionsOfTable(app, view));

      copyInTableSelectedButton
            .addActionListener(_ -> connector.copyInTableSelectedExpressions(app, view));

      clearInTableSelectedButton
            .addActionListener(_ -> connector.unselectTableExpressions(app, common, model, view));

      clearAllSelectedButton
            .addActionListener(_ -> connector.unselectAllExpressions(app, common, model, view));

      deleteAllSelectedButton
            .addActionListener(_ -> connector.deleteAllSelectedExpressions(app, common, model, view));

      deleteInTableSelectedButton.addActionListener(
            _ -> connector.deleteInTableSelectedExpressions(app, common, model, view));

      wasteBinButton.addActionListener(_ -> connector.openTrashCanDialog(app, common, model, view));

      selectAllInTableButton
            .addActionListener(_ -> connector.selectTableExpressions(app, common, model, view));

      shredderButton
            .addActionListener(_ -> connector.shredderDeletedExpressions(app, common, model, view));

      searchPhraseMy.addKeyListener(new KeyAdapter()
      {
         @Override
         public void keyPressed(KeyEvent e)
         {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
               connector.searchMyLanguage(app, common, model, view);
            }
         }
      });

      searchPhraseOther.addKeyListener(new KeyAdapter()
      {
         @Override
         public void keyPressed(KeyEvent e)
         {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
               connector.searchOtherLanguage(app, common, model, view);
            }
         }
      });

      tableInfoButton.addActionListener(_ -> {
         JOptionPane.showMessageDialog(horizontalLanguagePanel, "",
               app.settings.getWindowTitle(), JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(app,
                     translator.realisticTranslate(app, Translation.TABELLE),
                     translator.realisticTranslate(app, 
                           Translation.EINMAL_KLICKEN_MARKIERT_EINEN_EINTRAG),
                     translator.realisticTranslate(app, 
                           Translation.ENTER_DRUECKEN_OEFFNET_DEN_MARKIERTEN_EINTRAG),
                     translator.realisticTranslate(app, 
                           Translation.ZWEIMAL_KLICKEN_WAEHLT_EINEN_EINTRAG_AUS__STECKNADEL_)
         // ,
         // translator.realisticTranslate(
         // Translation.SCHWEDISCH_TASTE_B_STARTET_DAS_BUCHSTABIEREN)
         )));
      });

      tableInfoButton.addMouseListener(new MouseListener()
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

      sortForDateBox.addActionListener(_ -> {
         connector.sortTableNow(app, common, model, view);
      });

      sortForAlphabetBox.addActionListener(_ -> {
         connector.sortTableNow(app, common, model, view);
      });

      sortForIndexBox.addActionListener(_ -> {
         connector.sortTableNow(app, common, model, view);
      });

      moveToChapterButton.addActionListener(_ -> {
         String chapterAim = (String) chapterChoiceBox.getSelectedItem();
         chapterAim = cleanTextLeaveComma(chapterAim);
         if (chapterAim.isBlank())
         {
            JOptionPane.showMessageDialog(view.getjFrame(), translator
                  .realisticTranslate(app, 
                        Translation.BITTE_GEBEN_SIE_EINEN_NEUEN_LEKTIONSNAMEN_EIN)
                  + "\n "
                  + translator.realisticTranslate(app, 
                        Translation.ODER_WAEHLEN_SIE_EINE_VORHANDENE_LEKTION_AUS),
                  translator.realisticTranslate(app, Translation.INFORMATION),
                  JOptionPane.INFORMATION_MESSAGE);
         }
         else
         {
            connector.moveExpressionsToChapter(app, common, model, view, chapterAim);
            chapterChoiceBox.setModel(model.data.getChapterComboBoxModel(app));
         }
      });

      moveToDatabaseButton.addActionListener(_ -> {
         String databaseAim = (String) databaseChoiceBox.getSelectedItem();
         databaseAim = cleanTextLeaveComma(databaseAim);
         if (databaseAim.isBlank())
         {
            JOptionPane.showMessageDialog(view.getjFrame(), translator
                  .realisticTranslate(app, 
                        Translation.BITTE_GEBEN_SIE_EINEN_NEUEN_DATENBANKNAMEN_EIN)
                  + "\n "
                  + translator.realisticTranslate(app, 
                        Translation.ODER_WAEHLEN_SIE_EINE_VORHANDENE_DATENBANK_AUS),
                  translator.realisticTranslate(app, Translation.INFORMATION),
                  JOptionPane.INFORMATION_MESSAGE);
         }
         else
         {
            connector.moveExpressionsToDatabase(app, common, model, view, databaseAim);
            databaseChoiceBox.setModel(model.data.getOwnDatabasesComboBoxModel(app, common));
         }
      });
   }

   @Override
   public int askForMovingToDatabaseConfirmation(App app, View view)
   {
      return JOptionPane.showConfirmDialog(view.getjFrame(),
            translator.realisticTranslate(app, 
                  Translation.WOLLEN_SIE_WIRKLICH_DIE_VOKABELN_IN_EINE_ANDERE_DATENBANK_VERSCHIEBEN__),
            translator.realisticTranslate(app, Translation.FRAGE),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
   }

   @Override
   public int askForMovingToChapterConfirmation(App app, View view)
   {
      return JOptionPane.showConfirmDialog(view.getjFrame(),
            translator.realisticTranslate(app, 
                  Translation.WOLLEN_SIE_WIRKLICH_DIE_VOKABELN_IN_EINE_ANDERE_LEKTION_VERSCHIEBEN__),
            translator.realisticTranslate(app, Translation.FRAGE),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
   }

   @Override
   public int askForShredderConfirmation(App app, View view)
   {
      return JOptionPane.showConfirmDialog(view.getjFrame(),
            translator.realisticTranslate(app, 
                  Translation.WOLLEN_SIE_WIRKLICH_DEN_PAPIERKORB_LEEREN__),
            translator.realisticTranslate(app, Translation.FRAGE),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
   }

   @Override
   public int askForDeletionConfirmation(App app, View view, int number)
   {
      String message;
      if (number == 1)
      {
         message = translator.realisticTranslate(app, 
               Translation.WOLLEN_SIE_WIRKLICH_EINEN_EINTRAG_LOESCHEN__);
      }
      else
      {
         message = translator
               .realisticTranslate(app, Translation.WOLLEN_SIE_WIRKLICH) + " "
               + number + " " + translator
                     .realisticTranslate(app, Translation.EINTRAEGE_LOESCHEN__);
      }

      message += "\n";
      message += translator.realisticTranslate(app, 
            Translation.HINWEIS__DIE_VOKABELN_EINGEBAUTER_DATENBANKEN);
      message += "\n";
      message += translator
            .realisticTranslate(app, Translation.KOENNEN_NICHT_GELOESCHT_WERDEN_);
      return JOptionPane.showConfirmDialog(view.getjFrame(), message,
            translator.realisticTranslate(app, Translation.FRAGE),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
   }

   public void notifyNothingWasSelectedForDeletion(App app, View view)
   {
      String message = translator
            .realisticTranslate(app, Translation.ES_WURDEN_KEINE_EINTRAEGE);
      message += "\n";
      message += translator
            .realisticTranslate(app, Translation.ZUM_LOESCHEN_AUSGEWAEHLT);
      message += "\n";
      message += translator.realisticTranslate(app, 
            Translation.HINWEIS__DIE_VOKABELN_EINGEBAUTER_DATENBANKEN);
      message += "\n";
      message += translator
            .realisticTranslate(app, Translation.KOENNEN_NICHT_GELOESCHT_WERDEN_);
      JOptionPane.showMessageDialog(view.getjFrame(), message, "Nachricht",
            JOptionPane.CLOSED_OPTION);

   }

   public void loadDatabases(App app, Model model)
   {
      dataPanel.removeAll();
      Vector<String> names = new Vector<>();
      names.add(translator.realisticTranslate(app, Translation.DATENBANK));
      databaseTableModel = new DatabaseTableModel(model.data.getDatabaseArray(),
            names);
      DatabaseTableMultiselect databaseTable = new DatabaseTableMultiselect(app,
            databaseTableModel, app.settings.getKeyboardWidth());
      JScrollPane scroller = new JScrollPane(databaseTable);
      scroller.setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 300));
      scroller.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() + 50, 700));
      scroller.setBorder(BorderFactory.createEmptyBorder());

      dataPanel.add(scroller);
      dataPanel.validate();
      dataPanel.repaint();
   }

   public void loadChapters(App app, Common common, Model model)
   {
      chapterPanel.removeAll();
      listSelectionModel = new ChapterListSelectionModel();
      addChapterListSelectionListener();
      chapterList = new ChapterList(app, listSelectionModel);
      chapterList.setListData(
            model.data.getChapterArray(app, common, databaseTableModel.getSelectedRows()));
      chapterList.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

      JScrollPane scroller = new JScrollPane(chapterList);
      scroller.setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 300));
      scroller.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() + 50, 700));
      scroller.setBorder(BorderFactory.createEmptyBorder());

      chapterPanel.add(scroller);
      chapterPanel.validate();
      chapterPanel.repaint();
   }

   public void addChapterListSelectionListener()
   {
      listSelectionModel.addListSelectionListener(listSelectionListener);
   }

   public void removeChapterListSelectionListener()
   {
      listSelectionModel.removeListSelectionListener(listSelectionListener);
   }

   public void clearTable()
   {
      stopTableEditing();
      tablePanel.removeAll();
      table = null;
   }

   private void stopTableEditing()
   {
      if (table != null && table.isEditing())
      {
         table.getCellEditor().stopCellEditing();
      }
   }

   public void doShowTable(App app, Common common, Model model, View view, ExpressionTableModel tableModel)
   {
      tablePanel.removeAll();

      JTextField scrollsearchField = new JTextField();
      scrollsearchField.setOpaque(false);
      scrollsearchField.setBorder(BorderFactory.createTitledBorder(
            translator.realisticTranslate(app, Translation.SUCHWORT)));
      DataButton scrollsearchButton = new DataButton(
            translator.realisticTranslate(app, Translation.SUCHE_WORT_IN_TABELLE));
      scrollsearchButton.setOpaque(true);
      scrollsearchButton.setBackground(app.appColors.dictionary.getButton());
      scrollsearchButton.setForeground(app.appColors.dictionary.getButtonForeground());
      scrollsearchButton.setFont(app.appFonts.buttonFont);
      DataButton scrollsearchPinButton = new DataButton(
            translator.realisticTranslate(app, Translation.NAECHSTE_AUSWAHL));
      scrollsearchPinButton
            .setIcon(new ImageIcon(app.appImages.getSelect()));
      scrollsearchPinButton.setOpaque(true);
      scrollsearchPinButton.setBackground(app.appColors.dictionary.getBackground());
      scrollsearchPinButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      scrollsearchPinButton.setFont(app.appFonts.buttonFont);

      table = new ExpressionTable(app, common, model, view, tableModel, this.getSelectedLanguage(),
            connector, true,
            new ExpressionColumnModel(app, common, model, this.getSelectedLanguage()));
      tableScroller = new JScrollPane(table);
      tableScroller.setOpaque(false);
      tableScroller.getViewport().setOpaque(false);
      tableScroller.setViewportBorder(BorderFactory.createEmptyBorder());
      tableScroller.getVerticalScrollBar().setUnitIncrement(30);
      tableScroller.setMinimumSize(new Dimension(300, 300));
      tableScroller.setMaximumSize(new Dimension(500, 800));

      JPanel southWrapperPanel = new JPanel(new BorderLayout());

      JPanel scrollsearchPanel = new JPanel();
      TrainLayout scrollsearchPanelLayout = new TrainLayout(scrollsearchPanel,
            15);
      scrollsearchPanel.setLayout(scrollsearchPanelLayout);
      scrollsearchPanel.add(scrollsearchButton);
      scrollsearchPanel.add(scrollsearchPinButton);

      scrollsearchField.getDocument().addDocumentListener(new DocumentListener()
      {
         public void changedUpdate(DocumentEvent e)
         {
            setDataForDataButton();
         }

         public void removeUpdate(DocumentEvent e)
         {
            setDataForDataButton();
         }

         public void insertUpdate(DocumentEvent e)
         {
            setDataForDataButton();
         }

         public void setDataForDataButton()
         {
            if (scrollsearchField.getText().strip().length() < 2)
            {
               return;
            }
            scrollsearchButton.setData(table.findExpressionsFromPattern(
                  scrollsearchField.getText().strip()));
         }
      });

      scrollsearchButton.addActionListener(_ -> {
         table.scrollToExpression(scrollsearchButton.getIndexExpression());
         scrollsearchButton.nextIndex();
      });

      String searchCommand = "search";
      KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
      scrollsearchField
            .getInputMap(JTextField.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(enter, searchCommand);
      scrollsearchField.getActionMap().put(searchCommand,
            new SearchAction(scrollsearchButton));

      scrollsearchPinButton.addActionListener(_ -> {
         scrollsearchPinButton.setData(table.getSelectedExpressions(false));
         table.scrollToExpression(scrollsearchPinButton.getCurrentExpression());
         scrollsearchPinButton.nextExpression();
      });

      JPanel infotablePanel = new JPanel();
      TrainLayout infotablePanelLayout = new TrainLayout(infotablePanel, 15);
      infotablePanel.setLayout(infotablePanelLayout);
      JLabel numberOfEntriesLabel = new JLabel(
            " " + tableModel.getRowCount() + " " + translator
                  .realisticTranslate(app, Translation.EINTRAEGE_IN_DIESER_TABELLE));
      numberOfEntriesLabel.setFont(app.appFonts.buttonFont);
      infotablePanel.add(numberOfEntriesLabel);

      southWrapperPanel.add(scrollsearchField, BorderLayout.NORTH);
      southWrapperPanel.add(scrollsearchPanel, BorderLayout.CENTER);
      southWrapperPanel.add(infotablePanel, BorderLayout.SOUTH);

      tablePanel.add(tableScroller, BorderLayout.CENTER);
      tablePanel.add(southWrapperPanel, BorderLayout.SOUTH);
      tablePanelValidateRepaint();
   }

   @Override
   public void tablePanelValidateRepaint()
   {
      tablePanel.validate();
      tablePanel.repaint();
   }

   private void initLanguageButtonGroup(App app, ButtonGroup languageTypeGroup)
   {
      Font font = app.appFonts.germanFont.deriveFont(20F);
      JRadioButton german = new JRadioButton(
            translator.realisticTranslate(app, Translation.MEINE_SPRACHE));
      german.setActionCommand(Action.OWN_TO_NEW.name());
      german.setFont(font);
      german.setSelected(true);
      languageTypeGroup.add(german);
      german.setMinimumSize(new Dimension(100, 26));
      german.setMaximumSize(new Dimension(205, 32));

      JRadioButton hebrew = new JRadioButton(
            translator.realisticTranslate(app, Translation.NEUE_SPRACHE));
      hebrew.setActionCommand(Action.NEW_TO_OWN.name());
      hebrew.setFont(font);
      languageTypeGroup.add(hebrew);
      hebrew.setMinimumSize(new Dimension(100, 26));
      hebrew.setMaximumSize(new Dimension(205, 32));
   }

   @Override
   public void unselectExpressionKind()
   {
      Vector<Vector<ExpressionKindTableRow2>> vektorRows = expressionKindTable
            .getModel().getData();
      for (Vector<ExpressionKindTableRow2> vektorRow : vektorRows)
      {
         ExpressionKindItem expressionKind = vektorRow.get(0)
               .getExpressionKind();
         if (expressionKind.isSelected())
         {
            expressionKind.setSelected(false);
         }
      }
   }

   @Override
   public void selectTab(Tabulator tabulator)
   {
      tabbedPane.setSelectedIndex(tabulator.getIndex());
   }

   @Override
   public Direction getSelectedLanguage()
   {
      return Direction.valueOf(languageGroup.getSelection().getActionCommand());
   }

   @Override
   public boolean isTableNotNull()
   {
      return table != null;
   }

   @Override
   public String getTableDataToString(App app)
   {
      return table.getTableDataToString(app);
   }

   @Override
   public String getSelectedTableDataToString(App app)
   {
      return table.getSelectedTableDataToString(app);
   }

   @Override
   public void clearTableDataSelection()
   {
      table.clearTableDataSelection();
   }

   @Override
   public List<Expression> getInTableSelectedExpressions(
         boolean exceptDoNotChange)
   {
      return table.getSelectedExpressions(exceptDoNotChange);
   }

   @Override
   public void selectTableData()
   {
      table.selectAllExpressions();
   }

   @Override
   public void displayNoTable()
   {
      clearTable();
      tablePanelValidateRepaint();
   }

   @Override
   public Chapter getSelectedChapter()
   {
      return chapterList.getSelectedValue();
   }

   @Override
   public ExpressionKind getSelectedExpressionKind()
   {
      if (expressionKindTable.getSelectedRow() > -1)
      {
         return ((ExpressionKindTableRow2) expressionKindTable
               .getValueAt(expressionKindTable.getSelectedRow(), 0))
                     .getExpressionKind().getKind();
      }

      return null;
   }

   @Override
   public SearchType getSelectedSearchTypeGerman()
   {
      return SearchType
            .valueOf(searchTypeGroupGerman.getSelection().getActionCommand());
   }

   @Override
   public SearchType getSelectedSearchTypeHebrew()
   {
      return SearchType
            .valueOf(searchTypeGroupHebrew.getSelection().getActionCommand());
   }

   @Override
   public String getSearchPhraseGerman()
   {
      return searchPhraseMy.getText().trim();
   }

   @Override
   public String getSearchPhraseOther()
   {
      return searchPhraseOther.getText().trim();
   }

   @Override
   public void switchSearchLanguagePanel(String actionCommand)
   {
      cardLayout.show(swapPanel, actionCommand);
   }

   @Override
   public void selectChapter(App app, Common common, Model model, Chapter currentChapter)
   {
      chapterList.setSelectedValue(currentChapter, true);
      if (chapterList.getSelectedValue() == null)
      {
         loadChapters(app, common, model);
         chapterList.setSelectedValue(currentChapter, true);
      }
   }

   public JScrollPane getTableScroller()
   {
      return tableScroller;
   }

   public ExpressionTable getTable()
   {
      return table;
   }

   @Override
   public void setValues(App app, Common common, Model model, View view)
   {
      model.data.determineReloadDatabases(app, common, view);
      this.loadChapters(app, common, model);
      this.loadDatabases(app, model);
      this.displayNoTable();
      setWritingDirection(app);
      this.searchVertical.removeAll();
      Direction selectedLanguage = this.getSelectedLanguage();
      this.initSearchPanel(app, common, model, view, selectedLanguage);
      chapterChoiceBox.setModel(model.data.getChapterComboBoxModel(app));
      this.searchVertical.validate();
      this.searchVertical.repaint();
      connector.displayTableAfterOpeningPage(app, common, model, view);
   }

   @Override
   public SortingType getSortNow()
   {
      return SortingType
            .valueOf(sortingGroup.getSelection().getActionCommand());
   }

   private String cleanTextLeaveComma(String text)
   {
      return TextHelper.cleanText(text);
   }

   @Override
   public DatabaseTableModel getDatabaseTableModel()
   {
      return databaseTableModel;
   }

}
