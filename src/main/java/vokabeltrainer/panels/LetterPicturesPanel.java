package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Command;
import vokabeltrainer.KeyboardHebrew;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.panels.dialogs.EmptyNotification;
import vokabeltrainer.panels.letterpicture.LetterPictureAlphabetPanel;
import vokabeltrainer.panels.letterpicture.LetterPictureWordPanel;
import vokabeltrainer.panels.list.StringList;
import vokabeltrainer.panels.list.StringListSelectionModel;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.ExpressionKind;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.SearchType;

public class LetterPicturesPanel extends BackgroundPanelTiled
{
   enum Caller
   {
      CHAPTER_TAB(
            0),
      SEARCH_TAB(
            1),
      KIND_TAB(
            2),
      NEW_TAB(
            3),
      SELECTED_TAB(
            4);

      private int index;
      private static Caller tabShowing;

      Caller(int index)
      {
         this.index = index;
      }

      int getIndex()
      {
         return index;
      }
   }

   private static final long serialVersionUID = 9130321171813967337L;

   private ButtonGroup languageGroup;
   private ButtonGroup searchTypeGroup;
   private ButtonGroup searchTypeGroupHebrew;
   private ButtonGroup searchTypeGroupGerman;
   private List<JRadioButton> radioButtons;
   private ExpressionTable table;
   private JPanel tablePanel;
   private JTabbedPane tabbedPane;
   private StringList chapterList;
   private JPanel chapterPanel;
   private JPanel swapPanel;
   private CardLayout cardLayout;
   private JTextField searchPhraseHebrew;
   private JTextField searchPhraseGerman;
   private JButton hebrewSearchButton;
   private JButton germanSearchButton;
   private LetterPictureWordPanel wordPanel;
   private JButton pictureInfoButton;
   private JPanel letterPanel;
   private JButton tableInfoButton;
   private JPanel horizontalLanguagePanel;

   public LetterPicturesPanel()
   {
      setLayout(new TotemLayout(this, 10));

      JPanel horizontal0 = new JPanel();
      horizontal0.setLayout(new TrainLayout(horizontal0, 15));
      horizontal0.setOpaque(false);

      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.setOpaque(false);

      horizontalLanguagePanel = new JPanel();
      horizontalLanguagePanel
            .setLayout(new TrainLayout(horizontalLanguagePanel, 15));
      horizontalLanguagePanel.setOpaque(false);
      horizontalLanguagePanel
            .setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
      languageGroup = new ButtonGroup();
      initLanguageButtonGroup(languageGroup);
      Enumeration<AbstractButton> enumeration1 = languageGroup.getElements();
      while (enumeration1.hasMoreElements())
      {
         AbstractButton button = enumeration1.nextElement();
         button.addActionListener(event -> {
            cardLayout.show(swapPanel, button.getActionCommand());
            decideOnTable(button.getActionCommand());
         });
         horizontalLanguagePanel.add(button);
      }
      JPanel filler = new JPanel();
      filler.setOpaque(false);
      filler.setMinimumSize(new Dimension(50, 32));
      filler.setMaximumSize(new Dimension(150, 32));
      horizontalLanguagePanel.add(filler);
      tableInfoButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      tableInfoButton.setBackground(new Color(0, 0, 0, 0));
      tableInfoButton.setMinimumSize(new Dimension(14, 32));
      tableInfoButton.setMaximumSize(new Dimension(14, 32));
      tableInfoButton.setMargin(new Insets(0, 0, 0, 0));
      horizontalLanguagePanel.add(tableInfoButton);

      tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(false);
      tabbedPane.setFont(Main.getGermanFont(16F));
      tabbedPane.addTab("Lektionen", initChaptersTab());
      tabbedPane.addTab("Suche", initSearchTab());
      tabbedPane.addTab("Wortarten", initExpressionKindsTab());
      tabbedPane.addTab("Neue", initNewWordsTab());
      tabbedPane.addTab("Auswahl", initSelectedTab());

      vertical.add(horizontalLanguagePanel);
      vertical.add(tabbedPane);

      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setMinimumSize(new Dimension(420, 507));
      tablePanel.setMaximumSize(new Dimension(513, 507));
      tablePanel.setOpaque(false);

      horizontal0.add(vertical);
      horizontal0.add(tablePanel);
      horizontal0.add(initLetterPanel());

      add(horizontal0);
      add(initPictureWord());

      Caller.tabShowing = Caller.CHAPTER_TAB;
      loadChapters();

      initController();
   }

   private Component initPictureWord()
   {
      wordPanel = new LetterPictureWordPanel();
      return wordPanel;
   }

   private Component initLetterPanel()
   {
      letterPanel = new JPanel();
      letterPanel.setLayout(new TotemLayout(letterPanel));
      letterPanel.setOpaque(false);

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.setOpaque(false);

      JLabel title = new JLabel("Alphabet");
      title.setFont(Main.getGermanFont(24F));
      horizontal.add(title);

      pictureInfoButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      pictureInfoButton.setBackground(new Color(0, 0, 0, 0));
      pictureInfoButton.setMinimumSize(new Dimension(50, 50));
      pictureInfoButton.setMaximumSize(new Dimension(50, 50));
      pictureInfoButton.setMargin(new Insets(0, 0, 0, 0));
      horizontal.add(pictureInfoButton);

      letterPanel.add(horizontal);
      letterPanel.add(new LetterPictureAlphabetPanel());

      return letterPanel;
   }

   private Component initSearchTab()
   {
      JPanel vertical1 = new JPanel();
      vertical1.setLayout(new TotemLayout(vertical1));
      vertical1.setOpaque(false);

      JPanel germanSearch = new JPanel();
      germanSearch.setLayout(new TotemLayout(germanSearch, 5));
      germanSearch.setBackground(Color.WHITE);
      searchPhraseGerman = new JTextField();
      searchPhraseGerman
            .setBorder(new TitledBorder("Wort auf Deutsch eingeben"));
      germanSearch.add(searchPhraseGerman);
      searchPhraseGerman.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 70));
      searchPhraseGerman.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 70));

      JPanel filler = new JPanel();
      filler.setOpaque(false);
      filler.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 100));
      filler.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 270));
      germanSearch.add(filler);

      searchTypeGroupGerman = new ButtonGroup();
      germanSearch.add(initSearchRadioButtonPanel(searchTypeGroupGerman));

      germanSearchButton = new JButton("Suche starten");
      germanSearchButton.setFont(Settings.getButtonFont());
      germanSearchButton.setIcon(new ImageIcon(ApplicationImages.getSearch()));
      JPanel wrapper = new JPanel(new FlowLayout());
      wrapper.setOpaque(false);
      wrapper.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 30));
      wrapper.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 50));
      wrapper.add(germanSearchButton);
      germanSearch.add(wrapper);

      JPanel hebrewSearch = new JPanel();
      hebrewSearch.setLayout(new TotemLayout(hebrewSearch, 5));
      hebrewSearch.setBackground(Color.WHITE);
      searchPhraseHebrew = new JTextField();
      searchPhraseHebrew
            .setBorder(new TitledBorder("Wort auf Hebräisch eingeben"));

      JPanel wrapper2 = new JPanel(new FlowLayout());
      wrapper2.setOpaque(false);
      wrapper2.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 200));
      wrapper2.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 300));
      KeyboardHebrew keyboard = new KeyboardHebrew(searchPhraseHebrew,
            new ArrayList<JTextComponent>(),70);
      wrapper2.add(keyboard);
      hebrewSearch.add(wrapper2);

      JPanel filler2 = new JPanel();
      filler2.setOpaque(false);
      filler2.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 1));
      filler2.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 8));
      hebrewSearch.add(filler2);

      searchTypeGroupHebrew = new ButtonGroup();
      hebrewSearch.add(initSearchRadioButtonPanel(searchTypeGroupHebrew));

      hebrewSearchButton = new JButton("Suche starten");
      hebrewSearchButton.setFont(Settings.getButtonFont());
      hebrewSearchButton.setIcon(new ImageIcon(ApplicationImages.getSearch()));
      JPanel wrapper1 = new JPanel(new FlowLayout());
      wrapper1.setOpaque(false);
      wrapper1.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 30));
      wrapper1.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 50));
      wrapper1.add(hebrewSearchButton);
      hebrewSearch.add(wrapper1);

      cardLayout = new CardLayout();
      swapPanel = new JPanel(cardLayout);
      swapPanel.setOpaque(false);
      swapPanel.setPreferredSize(new Dimension(Settings.getKeyboardWidth(), 420));
      swapPanel.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 420));
      swapPanel.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 420));
      swapPanel.add(Language.GERMAN.name(), germanSearch);
      swapPanel.add(Language.HEBREW.name(), hebrewSearch);

      vertical1.add(swapPanel);

      return vertical1;
   }

   private JPanel initSearchRadioButtonPanel(ButtonGroup group)
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 5));
      vertical.setOpaque(false);

      for (SearchType type : SearchType.values())
      {
         JRadioButton radioButton = new JRadioButton(type.toString());
         radioButton.setActionCommand(type.name());
         if (SearchType.WORDSTART.equals(type))
         {
            radioButton.setSelected(true);
         }
         radioButton.setFont(Settings.getButtonFont());
         JPanel wrapper = new JPanel(new FlowLayout());
         wrapper.setOpaque(false);
         wrapper.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 25));
         wrapper.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 25));
         wrapper.add(radioButton);
         vertical.add(wrapper);
         group.add(radioButton);
      }

      return vertical;
   }

   private Component initSelectedTab()
   {
      JPanel vertical1 = new JPanel();
      vertical1.setOpaque(false);
      return vertical1;
   }

   private Component initNewWordsTab()
   {
      JPanel vertical1 = new JPanel();
      vertical1.setOpaque(false);
      return vertical1;
   }

   private JPanel initChaptersTab()
   {
      chapterPanel = new JPanel();
      chapterPanel.setLayout(new TotemLayout(chapterPanel));
      chapterPanel.setOpaque(false);
      return chapterPanel;
   }

   private JPanel initExpressionKindsTab()
   {
      JPanel vertical1 = new JPanel();
      vertical1.setOpaque(false);
      vertical1.setLayout(new TotemLayout(vertical1, 15));

      radioButtons = new ArrayList<>();
      initRadioButtonPanel(vertical1);

      return vertical1;
   }

   private void initController()
   {
      tabbedPane.addChangeListener(event -> {
         if (tabbedPane.getSelectedIndex() == Caller.KIND_TAB.getIndex())
         {
            Caller.tabShowing = Caller.KIND_TAB;
            clearTable();
            this.tablePanel.validate();
            this.tablePanel.repaint();
         }
         else if (tabbedPane.getSelectedIndex() == Caller.CHAPTER_TAB
               .getIndex())
         {
            Caller.tabShowing = Caller.CHAPTER_TAB;
            this.searchTypeGroup.clearSelection();
            clearTable();
            this.tablePanel.validate();
            this.tablePanel.repaint();
            loadChapters();
         }
         else if (tabbedPane.getSelectedIndex() == Caller.NEW_TAB.getIndex())
         {
            Caller.tabShowing = Caller.NEW_TAB;
            this.searchTypeGroup.clearSelection();
            clearTable();
            decideOnTable(Command.NEW_TAB.name());
         }
         else if (tabbedPane.getSelectedIndex() == Caller.SELECTED_TAB
               .getIndex())
         {
            Caller.tabShowing = Caller.SELECTED_TAB;
            this.searchTypeGroup.clearSelection();
            clearTable();
            decideOnTable(Command.SELECTED_TAB.name());
         }
         else if (tabbedPane.getSelectedIndex() == Caller.SEARCH_TAB.getIndex())
         {
            Caller.tabShowing = Caller.SEARCH_TAB;
            this.searchTypeGroup.clearSelection();
            clearTable();
            this.tablePanel.validate();
            this.tablePanel.repaint();
         }
      });

      hebrewSearchButton.addActionListener(event -> {
         clearTable();
         decideOnTable(Command.SEARCH_HEBREW.name());
      });

      germanSearchButton.addActionListener(event -> {
         clearTable();
         decideOnTable(Command.SEARCH_GERMAN.name());
      });

      pictureInfoButton.addActionListener(event -> {
         JOptionPane.showMessageDialog(letterPanel, "",
               "Cerebrummi©", JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make("Bilderbuchstaben",
                     "Alle Bilder kann man", "auch einzeln anklicken.")));
      });

      pictureInfoButton.addMouseListener(new MouseListener()
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

      tableInfoButton.addActionListener(event -> {
         JOptionPane.showMessageDialog(horizontalLanguagePanel, "",
               "Cerebrummi©", JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make("Tabelle",
                     "einmal klicken markiert einen Eintrag",
                     "zweimal klicken wählt einen Eintrag aus",
                     "Einträge können im Wörterbuch bearbeitet werden")));
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

   }

   private void loadChapters()
   {
      chapterPanel.removeAll();
      StringListSelectionModel listSelectionModel = new StringListSelectionModel();
      listSelectionModel.addListSelectionListener(event -> {
         decideOnTable(chapterList.getSelectedValue());
      });
      chapterList = new StringList(listSelectionModel);
      chapterList.setBackground(Color.WHITE);
      chapterList.setListData(Data.getChapterArray());
      chapterList.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

      JScrollPane scroller = new JScrollPane(chapterList);
      scroller.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 400));
      scroller.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 500));
      scroller.setBorder(BorderFactory.createEmptyBorder());

      chapterPanel.add(scroller, BorderLayout.CENTER);
      chapterPanel.validate();
      chapterPanel.repaint();
   }

   private void decideOnTable(String actionCommand)
   {
      ExpressionTableModel tableModel = null;

      switch (Caller.tabShowing)
      {

      case KIND_TAB:
         if (Command.SEARCH_GERMAN.name().equals(actionCommand)
               || Command.SEARCH_HEBREW.name().equals(actionCommand)
               || Command.SELECTED_TAB.name().equals(actionCommand)
               || Command.NEW_TAB.name().equals(actionCommand)
               || Command.NEW.name().equals(actionCommand))
         {
            throw new IllegalStateException(
                  "DictoraryPanel: decideOnTable, KIND_TAB " + actionCommand);
         }
         if (Language.GERMAN.name().equals(actionCommand)
               || Language.HEBREW.name().equals(actionCommand)
               || Command.DELETE.name().equals(actionCommand)
               || Command.CLEAR.name().equals(actionCommand)
               || Command.SELECT_ALL.name().equals(actionCommand)
               || Command.SAVE.name().equals(actionCommand))
         {
            if (searchTypeGroup.getSelection() == null)
            {
               clearTable();
               tablePanel.validate();
               tablePanel.repaint();
               return;
            }
         }
         // chapter: not on kind tab
         // ExpressionKind
         clearTable();
         tableModel = Data.findTranslations(
               Language
                     .valueOf(languageGroup.getSelection().getActionCommand()),
               null,
               ExpressionKind.valueOf(
                     searchTypeGroup.getSelection().getActionCommand()),
               null, null, null);
         break;

      case CHAPTER_TAB:
         if (Command.SEARCH_GERMAN.name().equals(actionCommand)
               || Command.SEARCH_HEBREW.name().equals(actionCommand)
               || Command.SELECTED_TAB.name().equals(actionCommand)
               || Command.NEW_TAB.name().equals(actionCommand)
               || Command.NEW.name().equals(actionCommand))
         {
            throw new IllegalStateException(
                  "DictoraryPanel: decideOnTable, CHAPTER_TAB "
                        + actionCommand);
         }

         if (Language.GERMAN.name().equals(actionCommand)
               || Language.HEBREW.name().equals(actionCommand)
               || Command.DELETE.name().equals(actionCommand)
               || Command.CLEAR.name().equals(actionCommand)
               || Command.SELECT_ALL.name().equals(actionCommand)
               || Command.SAVE.name().equals(actionCommand))
         {
            clearTable();
            if (chapterList.getSelectedIndex() == -1)
            {
               tablePanel.validate();
               tablePanel.repaint();
               return;
            }
         }
         // chapter
         // ExpressionKind: not on chapter tab
         clearTable();
         tableModel = Data.findTranslations(
               Language
                     .valueOf(languageGroup.getSelection().getActionCommand()),
               null, null, null, chapterList.getSelectedValue(), null);

         break;

      case NEW_TAB:
         if (Command.SEARCH_GERMAN.name().equals(actionCommand)
               || Command.SEARCH_HEBREW.name().equals(actionCommand)
               || Command.SELECTED_TAB.name().equals(actionCommand))
         {
            throw new IllegalStateException(
                  "DictoraryPanel: decideOnTable, NEW_TAB " + actionCommand);
         }
         if (Command.SAVE.name().equals(actionCommand))
         {
            clearTable();
            tablePanel.validate();
            tablePanel.repaint();
            return;
         }
         if (Language.GERMAN.name().equals(actionCommand)
               || Language.HEBREW.name().equals(actionCommand)
               || Command.DELETE.name().equals(actionCommand)
               || Command.NEW.name().equals(actionCommand)
               || Command.CLEAR.name().equals(actionCommand)
               || Command.SELECT_ALL.name().equals(actionCommand)
               || Command.NEW_TAB.name().equals(actionCommand))
         {
            clearTable();
            tableModel = Data.findTranslationsNewWords(Language
                  .valueOf(languageGroup.getSelection().getActionCommand()));
         }
         // chapter: not on new tab
         // ExpressionKind: not on new tab
         break;

      case SELECTED_TAB:
         if (Command.SEARCH_GERMAN.name().equals(actionCommand)
               || Command.SEARCH_HEBREW.name().equals(actionCommand)
               || Command.NEW_TAB.name().equals(actionCommand)
               || Command.NEW.name().equals(actionCommand))
         {
            throw new IllegalStateException(
                  "DictoraryPanel: decideOnTable, SELECTED_TAB "
                        + actionCommand);
         }
         if (Command.SELECT_ALL.name().equals(actionCommand))
         {
            return;
         }
         if (Command.DELETE.name().equals(actionCommand)
               || Command.CLEAR.name().equals(actionCommand)
               || Command.SAVE.name().equals(actionCommand))
         {
            clearTable();
            tablePanel.validate();
            tablePanel.repaint();
            return;
         }
         if (Language.GERMAN.name().equals(actionCommand)
               || Language.HEBREW.name().equals(actionCommand)
               || Command.SELECTED_TAB.name().equals(actionCommand))
         {
            clearTable();
            tableModel = Data.findTranslations(
                  Language.valueOf(
                        languageGroup.getSelection().getActionCommand()),
                  null, null, null, null, Command.ALL_SELECTED);
         }
         break;

      case SEARCH_TAB:
         if (Command.SELECTED_TAB.name().equals(actionCommand)
               || Command.NEW_TAB.name().equals(actionCommand)
               || Command.NEW.name().equals(actionCommand))
         {
            throw new IllegalStateException(
                  "DictoraryPanel: decideOnTable, KIND_TAB " + actionCommand);
         }
         if (Language.GERMAN.name().equals(actionCommand)
               || Language.HEBREW.name().equals(actionCommand)
               || Command.DELETE.name().equals(actionCommand)
               || Command.CLEAR.name().equals(actionCommand)
               || Command.SELECT_ALL.name().equals(actionCommand)
               || Command.SAVE.name().equals(actionCommand))
         {
            clearTable();
            tablePanel.validate();
            tablePanel.repaint();
            return;
         }
         if (Command.SEARCH_GERMAN.name().equals(actionCommand))
         {
            clearTable();
            tableModel = Data.findTranslations(
                  Language.valueOf(
                        languageGroup.getSelection().getActionCommand()),
                  searchPhraseGerman.getText().trim(), null,
                  SearchType.valueOf(searchTypeGroupGerman.getSelection()
                        .getActionCommand()),
                  null, null);
         }
         if (Command.SEARCH_HEBREW.name().equals(actionCommand))
         {
            clearTable();
            tableModel = Data.findTranslations(
                  Language.valueOf(
                        languageGroup.getSelection().getActionCommand()),
                  searchPhraseHebrew.getText().trim(), null,
                  SearchType.valueOf(searchTypeGroupHebrew.getSelection()
                        .getActionCommand()),
                  null, null);
         }
         // chapter: not on search tab
         // ExpressionKind: not on search tab
      }

      if (tableModel.getRowCount() == 0)
      {
         EmptyNotification.display();
         tablePanel.validate();
         tablePanel.repaint();
         return;
      }

      doShowTable(tableModel);
   }

   private void clearTable()
   {
      wordPanel.removeAll();
      wordPanel.validate();
      wordPanel.repaint();
      stopTableEditing();
      tablePanel.removeAll();
   }

   private void stopTableEditing()
   {
      if (table != null && table.isEditing())
      {
         table.getCellEditor().stopCellEditing();
      }
   }

   private void doShowTable(ExpressionTableModel tableModel)
   {
      table = new ExpressionTable(tableModel,
            Language.valueOf(languageGroup.getSelection().getActionCommand()),
            false);
      table.getSelectionModel().addListSelectionListener(event -> {
         if (event.getValueIsAdjusting())
         {
            return;
         }
         wordPanel.displayWord(((Expression) table.getModel()
               .getValueAt(table.getSelectedRow(), 0)).getHebrew());
      });
      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setOpaque(false);
      scrollPane.getViewport().setOpaque(false);
      scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());

      tablePanel.add(scrollPane, BorderLayout.CENTER);
      tablePanel.validate();
      tablePanel.repaint();
   }

   private void initRadioButtonPanel(JPanel vertical1)
   {
      searchTypeGroup = new ButtonGroup();

      initSearchButtonGroup(searchTypeGroup);

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.setBackground(Color.WHITE);
      horizontal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

      JPanel vertical2 = new JPanel();
      vertical2.setLayout(new TotemLayout(vertical2, 15));
      vertical2.setOpaque(false);

      JPanel vertical3 = new JPanel();
      vertical3.setLayout(new TotemLayout(vertical3, 15));
      vertical3.setOpaque(false);

      int counter = 0;
      Enumeration<AbstractButton> enumeration = searchTypeGroup.getElements();
      while (enumeration.hasMoreElements())
      {
         AbstractButton button = enumeration.nextElement();
         if (counter < 8)
         {
            vertical2.add(button);
            counter++;
         }
         else
         {
            vertical3.add(button);
         }
      }

      horizontal.add(vertical2);
      horizontal.add(vertical3);

      vertical1.add(horizontal);
   }

   private void initSearchButtonGroup(ButtonGroup searchTypeGroup)
   {
      Font font = Main.getGermanFont(16F);
      for (ExpressionKind kind : ExpressionKind.getValues())
      {
         JRadioButton radioButton = new JRadioButton(kind.toString());
         radioButton.setActionCommand(kind.name());
         radioButton.addActionListener(event -> {
            decideOnTable(kind.name());
         });
         radioButton.setFont(font);
         searchTypeGroup.add(radioButton);
         radioButtons.add(radioButton);
      }
   }

   private void initLanguageButtonGroup(ButtonGroup languageTypeGroup)
   {
      Font font = Main.getGermanFont(20F);
      JRadioButton german = new JRadioButton("Deutsch");
      german.setActionCommand(Language.GERMAN.name());
      german.setFont(font);
      german.setSelected(true);
      languageTypeGroup.add(german);

      JRadioButton hebrew = new JRadioButton("Hebräisch");
      hebrew.setActionCommand(Language.HEBREW.name());
      hebrew.setFont(font);
      languageTypeGroup.add(hebrew);
   }

}
