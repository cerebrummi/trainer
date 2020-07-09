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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.InfoTextField;
import vokabeltrainer.KeyboardHebrew;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.HebrewDocument;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Binjan;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.ExpressionKind;
import vokabeltrainer.types.Gender;
import vokabeltrainer.types.Numerus;

public class ExpressionEditorView extends JDialog
      implements ExpressionEditorViewConnector
{
   private static final int WIDTH_INFO_PANEL = 250;

   private static final int WIDTH_INPUT_PANEL = Settings.getKeyboardWidth();

   private static final long serialVersionUID = 5853498340870217732L;

   private Expression expression;
   private JTextField german;
   private JTextField hebrewInLatin;
   private InfoTextField hebrew;
   private JComboBox<Gender> genderHebrew;
   private JComboBox<Numerus> numerusHebrew;
   private JComboBox<ExpressionKind> kind;
   private JComboBox<Binjan> binjan;
   private InfoTextField newSearchwordGerman;
   private JList<String> searchwordsJListGerman;
   private Set<String> searchwordsSetGerman;
   private InfoTextField newSearchwordHebrew;
   private JList<String> searchwordsJListHebrew;
   private Set<String> searchwordsSetHebrew;
   private KeyboardHebrew keyboard;
   private JPanel layout;
   private JButton deleteSearchwordButtonGerman;
   private JButton deleteSearchwordButtonHebrew;
   private boolean save;
   private ExpressionKind oldKind;
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
   private JButton infoExpressionKindButton;

   @SuppressWarnings("unused")
   private ExpressionEditorControllerConnector connector;

   public ExpressionEditorView(ExpressionEditorControllerConnector connector)
   {
      super(Common.getjFrame(), "Cerebrummi©",
            Dialog.ModalityType.APPLICATION_MODAL);
      this.connector = connector;
      save = false;
      setSize(1036, 680);
      layout = new BackgroundPanelTiled();
      layout.setBorder(
            BorderFactory.createLineBorder(Settings.getGreen(), 15, false));
      layout.setLayout(new TrainLayout(layout, 15));
      getContentPane().add(layout);

      initGuiFields();
      initInput();
      initInfosLeft();
      initInfosRight();

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

      genderHebrew = new JComboBox<>();
      genderHebrew.setBorder(new TitledBorder("Geschlecht Hebräisch"));
      genderHebrew.setModel(Gender.getHebrewModel());
      genderHebrew.setFont(germanfont);
      genderHebrew.setEditable(false);
      genderHebrew.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      genderHebrew.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      numerusHebrew = new JComboBox<>(Numerus.getModelHebrew());
      numerusHebrew.setFocusable(false);
      numerusHebrew.setBorder(new TitledBorder("Numerus Hebräisch"));
      numerusHebrew.setFont(germanfont);
      numerusHebrew.setEditable(false);
      numerusHebrew.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      numerusHebrew.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      kind = new JComboBox<>(ExpressionKind.getModel());
      kind.setBorder(new TitledBorder("Wortart"));
      kind.setFont(germanfont);
      kind.setEditable(false);
      kind.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 50, 50));
      kind.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 50, 50));

      binjan = new JComboBox<>(Binjan.values());
      binjan.setBorder(new TitledBorder("Binjan"));
      binjan.setFont(germanfont);
      binjan.setEditable(false);
      binjan.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      binjan.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      binjan.setMaximumRowCount(9);

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
            FocusTextField listComponent = new FocusTextField(value);
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

      deleteSearchwordButtonGerman = new JButton("lösche Suchwort Deutsch");
      deleteSearchwordButtonGerman.setFocusable(false);
      deleteSearchwordButtonGerman.setFont(Main.getGermanFont(16F));
      deleteSearchwordButtonGerman
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteSearchwordButtonGerman
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 40));

      newSearchwordHebrew = new InfoTextField("Neues Suchwort Hebräisch  ",
            "Bitte hineinklicken oder  ", "mit der Tabulatortaste auswählen.  ",
            "Dann hebräische Tastatur benutzen.  ", "Danach ENTER drücken!  ");
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
            FocusTextField listComponent = new FocusTextField(value);
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

      keyboard = new KeyboardHebrew(hebrew, components, 70);

      chapter = new JComboBox<String>();
      chapter.setMaximumRowCount(20);
      chapter.setBorder(new TitledBorder(this.chapterTitle));
      chapter.setOpaque(false);
      chapter.setBackground(new Color(0, 0, 0, 0));
      chapter.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      chapter.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
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
      vertical.add(genderHebrew);
      vertical.add(binjan);
      vertical.add(newSearchwordGerman);
      JScrollPane scrollPane = new JScrollPane(searchwordsJListGerman);
      scrollPane.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 220));
      scrollPane.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      vertical.add(scrollPane);
      vertical.add(deleteSearchwordButtonGerman);
      layout.add(vertical);
   }

   private void initInfosRight()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.add(numerusHebrew);

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setLayout(new TrainLayout(horizontal));
      horizontal.add(kind);
      horizontal.add(infoExpressionKindButton);
      vertical.add(horizontal);

      vertical.add(newSearchwordHebrew);
      JScrollPane scrollPane = new JScrollPane(searchwordsJListHebrew);
      scrollPane.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 220));
      scrollPane.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      vertical.add(scrollPane);
      vertical.add(deleteSearchwordButtonHebrew);
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
         save = true;
         if (testForCompletness())
         {
            saveExpression();
            this.dispose();
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
      expression.setGenderHebrew((Gender) genderHebrew.getSelectedItem());
      expression.setNumerusHebrew((Numerus) numerusHebrew.getSelectedItem());
      expression.setBinjan((Binjan) binjan.getSelectedItem());
      expression.setKind((ExpressionKind) kind.getSelectedItem());
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
      expression.setChapter(
            cleanTextWithoutComma((String) chapter.getSelectedItem()));
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
      this.oldKind = expression.getKind();
      this.expression = expression;
      this.german.setText(expression.getGerman());
      this.hebrewInLatin.setText(expression.getHebrewInLatin());
      this.hebrew.setText(expression.getHebrew());
      this.genderHebrew.setSelectedItem(expression.getGenderHebrew());
      this.numerusHebrew.setSelectedItem(expression.getNumerusHebrew());
      this.kind.setSelectedItem(expression.getKind());
      this.binjan.setSelectedItem(expression.getBinjan());

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
      this.chapter.setModel(Data.getChapterComboBoxModel());
      if (expression.getChapter().isEmpty())
      {
         chapter.setSelectedIndex(chapter.getItemCount() - 1);
      }
      else
      {
         this.chapter.setSelectedItem(expression.getChapter());
      }
      this.chapter.setEditable(true);
   }

   public Expression getExpression()
   {
      return expression;
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

   public boolean isSave()
   {
      return save;
   }

   public void setSave(boolean save)
   {
      this.save = save;
   }
   
   public boolean isKindChanged()
   {
      return this.oldKind != this.expression.getKind();
   }

   public ExpressionKind getOldKind()
   {
      return oldKind;
   }
}
